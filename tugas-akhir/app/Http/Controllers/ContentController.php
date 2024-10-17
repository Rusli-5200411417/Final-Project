<?php

namespace App\Http\Controllers;

use App\Models\Content;
use Illuminate\Http\Request;

class ContentController extends Controller
{
    public function showContent(){

        $data = Content::first(); // Mengambil data pertama dari tabel

        // Periksa apakah data ditemukan
        if (!$data) {
            return response()->json([
                'code' => 404,
                'message' => 'Data not found',
                'data' => null
            ], 404);
        }

        // Jika data ditemukan, kirimkan sebagai respons JSON
        return response()->json([
            'code' => 200,
            'message' => 'Data retrieved successfully',
            'data' => $data
        ]);
    }
}
