<?php

namespace App\Http\Controllers\web;

use App\Http\Controllers\Controller;
use App\Models\Content;
use App\Models\Order;
use App\Models\User;
use Illuminate\Http\Request;

class HomeController extends Controller
{
    public function index(){
        $user = User::where('role', 'user')->get();
        $content = Content::all()->first();
        // var_dump($content);
               return view('dashboard' ,compact('content','user'));
    }

    public function transaction(){
        $transactions = Order::orderBy('created_at', 'desc')->paginate(5);
    
    return view('transaksi', compact('transactions')) ;
    }   
    
    public function update(Request $request, $id)
    {
        $content = Content::find($id);

        if ($content) {
            // Update the existing content
            $content->update($request->all());
        } else {
            // Create new content if not exist
            Content::create($request->all());
        }

        return redirect()->route('dashboard')->with('success', 'Data has been saved successfully!');
    }
    public function hapus($id){
        $del = Content::find($id);

        if($del){
            $del->delete();
        }
        // var_dump("null");
        return redirect()->route('dashboard');
    }

}
