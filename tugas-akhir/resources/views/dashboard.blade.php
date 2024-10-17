@extends('layout.main')
@section('content')

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Dashboard</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Dashboard</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

  
    @php
    $totalUsers = count($user);
    $todayUsers = $user->where('created_at', '>=', now()->startOfDay())->count();
    @endphp 

<div class="container-fluid">
    <!-- Small boxes (Stat box) -->
    <div class="row">
      <div class="col-lg-3 col-6">
        <!-- small box -->
        <div class="small-box bg-info">
          <div class="inner">
            <h3>{{$totalUsers}}</h3>

            <p>Jumlah Penggnua</p>
          </div>
          <div class="icon">
            <i class="ion ion-bag"></i>
          </div>
          <a href="{{route('user')}}" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
        </div>
      </div>
      <!-- ./col -->
      <!-- ./col -->
      <div class="col-lg-3 col-6">
        <!-- small box -->
        <div class="small-box bg-warning">
          <div class="inner">
            <h3>{{$todayUsers}}</h3>

            <p>Pengguna Baru</p>
          </div>
          <div class="icon">
            <i class="ion ion-person-add"></i>
          </div>
          <a href="{{route('new-user')}}" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
        </div>
      </div>
    </div>
    <!-- /.row -->
    <!-- Main row -->
    <div class="container-fluid">
      <div>
          <h1>DATA TEMPAT WISATA</h1>
      </div>
      <div class="row">
          <div class="col-lg-6" name="show-data">
              <section class="connectedSortable">
                  <div class="card">
                      <div class="card-header">
                          <h3 class="card-title">
                              <i class="fas fa-map mr-1"></i>
                              Data Tempat Wisata
                          </h3>
                      </div>
                      <div class="card-body">
                          <div class="tab-content p-0">
                              <div class="row">
                                  <div class="col-6">
                                      <p class="mb-0">Tempat Wisata:</p>
                                      <p class="mb-0">Harga:</p>
                                      <p class="mb-0">Jam Operasional:</p>
                                  </div>
                                  <div class="col-6">
                                      <p class="mb-0">{{ $content ? $content->name : 'N/A' }}</p>
                                      <p class="mb-0">{{ $content ? $content->price : 'N/A' }}</p>
                                      <p class="mb-0">{{ $content ? $content->open_gate . ' - ' . $content->closed_gate : 'N/A' }}</p>
                                  </div>
                                  <div class="col-12">
                                      <p>Deskripsi:</p>
                                      <p>{{ $content ? $content->description : 'N/A' }}</p>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </section>
          </div>
          <div class="col-lg-6" name="edit-data">
              <section class="connectedSortable">
                  <div class="card">
                      <div class="card-header">
                          <h3 class="card-title">
                              <i class="fas fa-paint-brush mr-1"></i>
                              Edit Data
                          </h3>
                      </div>
                      <div class="card-body">
                          <div class="tab-content p-0">
                              <form action="{{ $content ? route('content.update', $content->id) : route('content.update', 0) }}" method="POST">
                                  @csrf
                                  @method('PUT')
                                  <div class="row">
                                      <div class="col-6">
                                          <p class="mb-0">Tempat Wisata:</p>
                                          <input type="text" name="name" class="mb-2 form-control" value="{{ $content ? $content->name : '' }}">
                                      </div>
                                      <div class="col-6">
                                          <p class="mb-0">Harga:</p>
                                          <input type="text" name="price" class="mb-2 form-control" value="{{ $content ? $content->price : '' }}">
                                      </div>
                                      <div class="col-12">
                                          <p class="mb-0">Jam Operasional:</p>
                                          <div class="row">
                                              <div class="col-6">
                                                  <label class="mb-0">Jam Buka:</label>
                                                  <input type="time" name="open_gate" class="mb-2 form-control" value="{{ $content ? $content->open_gate : '' }}">
                                              </div>
                                              <div class="col-6">
                                                  <label class="mb-0">Jam Tutup:</label>
                                                  <input type="time" name="closed_gate" class="mb-2 form-control" value="{{ $content ? $content->closed_gate : '' }}">
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-12">
                                          <p class="mb-0">Deskripsi:</p>
                                          <textarea name="description" class="mb-3 form-control" rows="4">{{ $content ? $content->description : '' }}</textarea>
                                      </div>
                                      <div class="col-12 text-right">
                                     
                                    </div>
                              </form>
                              <div class="col-12 text-right">
                                @if($content && $content->id != null)
                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal-hapus{{$content->id}}">
                                        Delete
                                    </button>
                                @endif
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                            
                            @include('modal.hapus')
                            </div>
                            </div>
                            
                          </div>
                      </div><!-- /.card-body -->
                  </div>
                  
                  <!-- /.card -->
              </section>
          </div>
      </div>
  </div>
       
  </div>
</div>
@endsection
