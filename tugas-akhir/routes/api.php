<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\ContentController;
use App\Http\Controllers\OrderController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('login', [AuthController::class, 'login']);
Route::post('register', [AuthController::class, 'register']);
Route::put('update-user/{id}', [AuthController::class, 'update']);
Route::get('status',[OrderController::class, 'statusOrder']);
Route::get('content',[ContentController::class,'showContent']);
Route::post('ticket/payment',[OrderController::class, 'processPayment']);
Route::post('handle',[OrderController::class, 'handle']);
Route::get('transaction/{id}',[OrderController::class, 'getTransaction']);
Route::get('getDetail/{id}',[OrderController::class, 'orderById']);
