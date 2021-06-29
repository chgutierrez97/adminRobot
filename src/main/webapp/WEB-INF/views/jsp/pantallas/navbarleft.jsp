<!-- El siguiente div estaba en el proyecto abierto por algo. -->
<!-- <div id="wrapper"> -->

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center"rel="home" href="#" title="Version 1.0.2">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-dice-d20"></i>
            </div>
            <div class="sidebar-brand-text mx-3" >  <sup >AST-Probot IC</sup></div>
        </a>
        <!-- Nav Item - Pages Collapse Menu -->
        <sec:authorize access="hasRole('ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="adm_userList">
                    <i class="fas fa-users"></i>
                    <span>Administración Usuarios</span></a>
            </li>

        </sec:authorize>
     

        <sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
             <!-- Divider -->
        <hr class="sidebar-divider">
        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSimu" aria-expanded="true" aria-controls="collapseTwo">
                <i class="far fa-play-circle"></i>
                <span>Simulación</span>
            </a>
            <div id="collapseSimu" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Simulador</h6> 
                    <a class="collapse-item" href="simuladorOnLine">En Linea</a>
                    <a class="collapse-item" href="simuladorOffLine">Fuera de Linea</a>
                </div>
            </div>
        </li>
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
        <!-- Nav Item - Transacciones -->

        <li class="nav-item">
            <a class="nav-link" href="expresiones">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Expresiones Regulares</span></a>
        </li>
        </sec:authorize>
       
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
        <li class="nav-item">
            <a class="nav-link" href="cancelaciones">
                <i class=" far fa-eye"></i>
                <span>Cancelaciones</span></a>
        </li>
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
    </ul>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars" ></i>
                </button>

                <!-- Topbar Search -->
                <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <!-- <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2"> 
                        -->
                        <div class="input-group-append">
                            <!-- <button class="btn btn-primary" type="button">
                              <i class="fas fa-search fa-sm"></i>
                            </button> -->
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">
                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><c:out value="${username}"></c:out></span>
                            <i class="fas fa-user"></i>
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<c:url value="/newLogoutPage " />">
                               <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a> 
                        </div>
                    </li>

                </ul>
            </nav>
