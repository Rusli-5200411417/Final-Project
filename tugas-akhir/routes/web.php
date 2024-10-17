<?php

use App\Http\Controllers\web\AuthController;
use App\Http\Controllers\web\HomeController;
use App\Http\Controllers\web\NewPasswordController;
use App\Http\Controllers\web\UserController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    if (auth()->check()) {
        return redirect()->route('dashboard');
    } else {
        return redirect()->route('login');
    }
});

Route::group(['middleware' => ['guest:sanctum']], function () {
    Route::get('/handle-success', [NewPasswordController::class, 'handleSuccess'])->name('handle');
    Route::get('/login', [AuthController::class, 'login'])->name('login');
    Route::post('/login', [AuthController::class, 'loginPost']);
});

Route::group(['prefix' => 'user'], function () {
    // Route::get('/daily-users', [UserController::class, 'dailyUsers'])->name('daily-users');
    Route::get('/', [UserController::class, 'tampilUser'])->name('user');
    Route::get('/new-user', [UserController::class, 'newUser'])->name('new-user');
});

Route::group(['middleware' => ['auth:sanctum']], function () {
    Route::get('/logout', [AuthController::class, 'logout'])->name('logout');
    Route::get('/dashboard', [HomeController::class, 'index'])->name('dashboard');
    Route::get('/transaksi', [HomeController::class, 'transaction'])->name('transaksi');
    Route::put('/content/{id}', [HomeController::class, 'update'])->name('content.update');
    Route::get('/sort', [HomeController::class, 'sortBy'])->name('transactions.sort');
    Route::delete('/hapus/{id}', [HomeController::class, 'hapus'])->name('hapus');


    Route::group(['prefix' => 'user'], function () {
        // Route::get('/daily-users', [UserController::class, 'dailyUsers'])->name('daily-users');
        Route::get('/', [UserController::class, 'tampilUser'])->name('user');
        Route::get('/new-user', [UserController::class, 'newUser'])->name('new-user');
    });

});
