<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Admin | Dashboard</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="{{ asset ('lte/plugins/fontawesome-free/css/all.min.css')}}">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css')}}">
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="{{ asset ('lte/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css')}}">
  <!-- iCheck -->
  <link rel="stylesheet" href="{{ asset ('lte/plugins/icheck-bootstrap/icheck-bootstrap.min.css')}}">
  <!-- JQVMap -->
  <link rel="stylesheet" href="{{ asset ('lte/plugins/jqvmap/jqvmap.min.css')}}">
  <!-- Theme style -->
  <link rel="stylesheet" href="{{ asset ('lte/dist/css/adminlte.min.css')}}">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="{{ asset ('lte/plugins/overlayScrollbars/css/OverlayScrollbars.min.css')}}">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="{{ asset ('lte/plugins/daterangepicker/daterangepicker.css')}}">
  <!-- summernote -->
  <link rel="stylesheet" href="{{ asset ('lte/plugins/summernote/summernote-bs4.min.css')}}">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body class="hold-transition sidebar-mini layout-fixed sidebar-collapse">
<div class="wrapper">

  <!-- Preloader -->
  <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="{{ asset('lte/dist/img/AdminLTELogo.png')}}" alt="AdminLTELogo" height="60" width="60">
  </div>

  
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
 
    <!-- ... Sidebar content ... -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
      <!-- Brand Logo -->
    <div class="row">
        <div class="col flex-column">
            <button class="btn btn-dark ml-auto" id="toggleSidebar">
              <i class="fas fa-bars"  style="opacity: .8"></i>   
          </button>
        </div>
        <div class="col">
          <a  class="brand-link">
            <img src="{{ asset('lte/dist/img/AdminLTELogo.png')}}" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
            <span class="brand-text font-weight-light">Admin</span>
          </a>
        </div>
    </div>
      <!-- Sidebar -->
      <div class="sidebar">
        <!-- Sidebar Menu -->
        <nav class="mt-2">
          <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            <!-- Add icons to the links using the .nav-icon class
                 with font-awesome or any other icon font library -->
            <li class="nav-item menu-open">
              <a href="{{route('dashboard')}}" class="nav-link  activate">
                <i class="nav-icon fas fa-tachometer-alt"></i>
                <p>
                  Dashboard
                </p>
              </a>
            <li class="nav-item menu-open">
              <a href="{{route('user')}}" class="nav-link ">
                <i class="nav-icon fas fa-users"></i>
                <p>
                  Data Pengguna
                </p>
              </a>
            </li>
            
            <li class="nav-item menu-open">
              <a href="{{route('transaksi')}}" class="nav-link ">
                <i class="nav-icon fas fa-shopping-cart"></i>
                <p>
                  Transaksi
                </p>
              </a>
            </li> 
            <li class="nav-item menu-open">
              <a href="{{route('logout')}}" class="nav-link ">
                @csrf
                @method('DELETE')
                <i class="nav-icon fas fa-power-off"></i>
                <p>
                 Logout
                </p>
              </a>
            </li>
          </ul>
        </nav>
        <!-- /.sidebar-menu -->
      </div>
      <!-- /.sidebar -->
      
    </aside>
    <!-- Button to toggle sidebar -->
    


 

  @yield('content')
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <strong>Copyright &copy; 2023 <a href="">Admin Ticketing</a>.</strong>
    All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="{{asset ('lte/plugins/jquery/jquery.min.js')}}"></script>
<!-- jQuery UI 1.11.4 -->
<script src="{{ asset('plugins/jquery-ui/jquery-ui.min.js')}}"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="{{ asset ('lte/plugins/bootstrap/js/bootstrap.bundle.min.js')}}"></script>
<!-- ChartJS -->
<script src="{{ asset ('lte/plugins/chart.js/Chart.min.js')}}"></script>
<!-- Sparkline -->
<script src="{{ asset ('lte/plugins/sparklines/sparkline.js')}}"></script>
<!-- JQVMap -->
<script src="{{ asset ('lte/plugins/jqvmap/jquery.vmap.min.js')}}"></script>
<script src="{{ asset ('lte/plugins/jqvmap/maps/jquery.vmap.usa.js')}}"></script>
<!-- jQuery Knob Chart -->
<script src="{{ asset ('lte/plugins/jquery-knob/jquery.knob.min.js')}}"></script>
<!-- daterangepicker -->
<script src="{{ asset ('lte/plugins/moment/moment.min.js')}}"></script>
<script src="{{ asset ('lte/plugins/daterangepicker/daterangepicker.js')}}"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="{{ asset ('lte/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js')}}"></script>
<!-- Summernote -->
<script src="{{ asset ('lte/plugins/summernote/summernote-bs4.min.js')}}"></script>
<!-- overlayScrollbars -->
<script src="{{ asset ('lte/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js')}}"></script>
<!-- AdminLTE App -->
<script src="{{ asset ('lte/dist/js/adminlte.js')}}"></script>
<!-- AdminLTE for demo purposes -->
<script src="{{ asset ('lte/dist/js/demo.js')}}"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="{{ asset ('lte/dist/js/pages/dashboard.js')}}"></script>
<script>
  $(document).ready(function() {
    // Move logout link to the bottom of the sidebar menu
    $('ul.nav-sidebar').append($('ul.nav-sidebar li:last-child'));
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.css">

<script>
  $(document).ready(function () {
      // Toggle sidebar when the toggle button is clicked
      $('#toggleSidebar').click(function () {
          $('body').toggleClass('sidebar-collapse');
      });
  });
</script>

<script>
  // Check if there is a success message in the session
  const successMessage = "{{ session('success') }}";
  if (successMessage) {
      // Display the success toast notification
      Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: successMessage,
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true
      });
  }
</script>
<script>
  // Fungsi ini akan dijalankan saat pengguna memilih file
  function handleFileSelect(event) {
      document.getElementById('csvForm').submit(); // Mengirimkan form saat file terpilih
  }

  // Mendapatkan elemen input file
  const fileInput = document.querySelector('input[name="csv_file"]');
  
  // Mendengarkan peristiwa 'change' pada input file
  fileInput.addEventListener('change', handleFileSelect);
</script>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    axios.get('/daily-users')
      .then(function (response) {
        var data = response.data;

        var labels = Object.keys(data);
        var values = Object.values(data);

        var ctx = document.getElementById('userChart').getContext('2d');
        var userChart = new Chart(ctx, {
          type: 'line',
          data: {
            labels: labels,
            datasets: [{
              label: 'Jumlah Pengguna',
              data: values,
              borderColor: 'blue',
              backgroundColor: 'rgba(0, 0, 255, 0.2)',
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      })
      .catch(function (error) {
        <p>Data tidak Ada</p>
        console.error(error);
      });
  });
</script>




</body>
</html>