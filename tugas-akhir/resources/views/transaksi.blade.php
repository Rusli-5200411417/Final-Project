@extends('layout.main')
@section('content')
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Transaksi</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Transaksi</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
              <div class="card-body">
                <table class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Order ID</th>
                    <th>Nama Pengguna</th>
                    <th>Email</th>
                    <th>Jumlah Pembelian</th>
                    <th>Jumlah Pembayaran</th>
                    <th>Bank</th>
                    <th>Waktu Transaksi</th>
                    <th>Waktu Pembayaran</th>
                    <th>Status</th>
                  </tr>
                  </thead>
                  <tbody>
                    @foreach ($transactions->slice(0, 5) as $d)
                  <tr>
                    <td>{{$d->id_order}}</td>
                    <td>{{$d->name}}</td>
                    <td>{{$d->email}}</td>
                    <td>{{$d->total_ticket}}</td>
                    <td>{{$d->total_amount}}</td>
                    <td>{{$d->bank}}</td>
                    <td>{{$d->created_at}}</td>
                    <td> 
                      @if(is_null($d->updated_at))
                          Pembayaran belum dilakukan
                      @else
                          {{ $d->updated_at }}
                      @endif
                    </td>
                    <td class="{{ $d->status == 'paid' ? 'bg-green' : 'bg-red' }}">
                          {{$d->status}}
                    </td>
                  </tr>
                  @endforeach
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
      <div class="row">
        <div class="col-12">
            <ul class="pagination justify-content-end">
                <li class="page-item {{ $transactions->onFirstPage() ? 'disabled' : '' }}">
                    <a class="page-link" href="{{ $transactions->previousPageUrl() }}" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <li class="page-item {{ $transactions->hasMorePages() ? '' : 'disabled' }}">
                    <a class="page-link" href="{{ $transactions->nextPageUrl() }}">Next</a>
                </li>
            </ul>
        </div>
    </div>
    
    
    </section>
    <!-- /.content -->
  </div>
@endsection
