<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;

class AuthController extends Controller
{
    public function login(Request $request)   {
        $validasi   = Validator::make($request->all(), [
          'email' => 'required_without:username',
          'password' => 'required|min:6',
        ]);
    
        if ($validasi->fails()) {
          return $this->error($validasi->errors()->first());
        }
    
        $user   =   $user = User::where(function ($query) use ($request) {
                    $query->where('email', $request->input('email'));
                   })->first();
        
        if ($user) {
    
            if (password_verify($request->password, $user->password)) {
              return  $this->success($user);
            } else {
              return  $this->error("Password salah");
            }
            
        }
        return $this->error("User tidak ditemukan");
      }  
    
      public function register (Request $request) {
        $validasi   = Validator::make($request->all(), [
          'email' => 'required|unique:users',
          'name' => 'required',
          'password' => 'required|min:6',
        ]);
    
        if ($validasi->fails()) {
          return $this->error($validasi->errors()->first());
        }
    
    
      $user = User::create(array_merge($request->all(), [
          'password' => bcrypt($request->password),
          'id_user'=> md5($request->name), 
          'role' => 'user'
      ]));
    
        if  ($user)  {
          return $this->success($user, 'Selamat Datang  '. $user->nama);
        } else  {
          return $this->error("Terjadi Kesalahan");
        }
    
      }
    
      public function update(Request $request, $id) {
        $user = User::find($id);
    
        if (!$user) {
            return $this->error("Tidak ada user");
        }
        // Simpan data lama sebelum pembaruan
        $oldData = $user->toArray();
    
        // Lakukan pembaruan data
        $user->update($request->all());
    
        // Cek apakah atribut yang mempengaruhi kebutuhan_kalori berubah
        $attributesAffectingCalories = ['usia', 'jenis_kelamin', 'name'];
    
        foreach ($attributesAffectingCalories as $attribute) {
            if ($request->has($attribute) && $request->$attribute !== $oldData[$attribute]) {
                break;
            }
        }
    

    
        return $this->success($user);
    }

    public function success($data, $message = "success") {
        return  response()->json([
          'code'  =>  200,
          'message' =>  $message,
          'data'  => $data
        ]);
      }
    
      public function error($message) {
        return  response()->json([
          'ok'  => false,
          'error_code'  =>  400,
          'description' =>  $message
        ],  400);
      }
}
