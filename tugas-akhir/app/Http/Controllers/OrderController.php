<?php

namespace App\Http\Controllers;

use App\Models\Content;
use App\Models\odel;
use App\Models\Order;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Str;
use Midtrans\Config;

class OrderController extends Controller
{
    
    public function processPayment(Request $request)
    {
        $validator = Validator::make($request->all(),[
            'user_id' => 'required|exists:users,id',
            'total' => 'required',
            'bank' => 'required|in:bca,bni',
        ]);

        if($validator->fails()){
            return response()->json(['message'=> 'invalid','data'=>$validator->errors()]);
        }

        $user = User::findOrFail($request->user_id);
        $name = $user->name;
        $email = $user->email;

        $content = Content::first();
        $price = $content->price;

        if(!$price){
            return response()->json(['message'=>'price not found','data'=>[
                'price' => ['price not in database']
            ]],422);
        }

        try{
            DB::beginTransaction();
            $serverKey = config('midtrans.key');

            $orderId = Str::uuid()->toString();
            $grossAmount = $price * $request->total;

            $response =Http::withBasicAuth($serverKey,'')
                ->post('https://api.sandbox.midtrans.com/v2/charge',[
                    'payment_type' => 'bank_transfer',
                    'transaction_details' =>[
                        'order_id' => $orderId,
                        'gross_amount' =>$grossAmount
                    ],
                    'bank_transfer'=>[
                        'bank' => $request->bank
                    ]
                ]);
                
                // if($response->failed()){
                //     return response()->json(['message'=>'failed charge' ],500);
                // }

                $result = $response->json();
                // if($result['status_code'] != 200){
                //     return response()->json(['message'=> $result],500);
                // }
                
                DB::table('orders')->insert([
                    'id_order' => $orderId,
                    'booking_code' => Str::random(6),
                    'id_user' => $request->user_id,
                    'name' => $name,
                    'email' => $email,
                    'total_ticket'=> $request->total,
                    'total_amount' => $grossAmount,
                    'va' => $result['va_numbers'][0]['va_number'],
                    'bank' => $request-> bank,
                    'status'=> 'unpaid',
                    'created_at'=> now()
                ]);

            DB::commit(); 
           
            return response()->json([
                'data'=> [
                    'va' => $result['va_numbers'][0]['va_number']
                ]
            ]);
            
        }catch(\Exception $e){
            DB::rollBack();
            return response()->json(['message'=> $e->getMessage()],500);
        }
    }

    public function handle(Request $request)
    {
       $serverKey = config('midtrans.key');
       $hasing = hash("sha512",$request->order_id.$request->status_code.$request->gross_amount.$serverKey);

      
       if($hasing == $request->signature_key){
        if($request->transaction_status == 'settlement'){

                // var_dump($request->transaction_status);
                $order = Order::where('id_order', $request->order_id)->first();


               // Pastikan order ditemukan
                if ($order) {
                    // Lakukan perubahan status
                    $order->status = 'paid'; // Gantilah 'new_status' dengan nilai status yang baru
                    $order->save(); // Simpan perubahan

                    // Kirim respons sukses
                    return response()->json(['message' => 'Status updated successfully'], 200);
                } else {
                    // Kirim respons jika order tidak ditemukan
                    return response()->json(['error' => 'Order not found'], 404);
                }
            }
           
        }
    }
        

    public function statusOrder(){
        $serverKey = config('midtrans.key');

        // Mendapatkan semua ID dari tabel 'orders'
        $orders = DB::table('orders')->pluck('id_order')->toArray();


       
        
        // Array untuk menyimpan status transaksi
        $transactionStatuses = [];

        // Panggil API Midtrans untuk setiap order ID
        foreach ($orders as $orderId) {
            $response = Http::withHeaders([
                'Authorization' => 'Basic ' . base64_encode($serverKey . ':')
            ])->get("https://api.sandbox.midtrans.com/v2/{$orderId}/status");

            // Cek apakah response sukses
        if ($response->successful()) {
            // Tambahkan status transaksi ke array
            $transactionStatuses[] = [
                'order_id' => $orderId,
                'transaction_status' => $response->json()['transaction_status']
            ];
        } else {
            // Tambahkan pesan error jika gagal
            $transactionStatuses[] = [
                'order_id' => $orderId,
                'error' => 'Failed to fetch transaction status'
            ];
        }
    }

    // Mengembalikan data status transaksi sebagai JSON
    return response()->json(['data' => $transactionStatuses]);
    }
    
    public function getTransaction(Request $request,$id){

        $order = Order::where('id_user', $id)->get();

        return response()->json([
            'status' => "ok",
            'message' => "get data completed successfully",
            'data' => $order
        ]);
    }

    public function orderById($id){
        
        $orderById =  Order::where("id",$id)->first();

        return response()->json([
            'status' => "ok",
            'message' => "get data completed successfully",
            'data' => $orderById
        ]);

    }

}