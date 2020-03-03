<div id="wrapper">

    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">


        <li class="nav-item dropdown">

        </li> 

        <sec:authorize access="hasRole('ADMIN')">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <!-- <i class="fas fa-fw fa-folder"></i> -->
                    <span>Administrador</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <h6 class="dropdown-header">Administracion</h6>
                    <a class="dropdown-item" href="siguientePaso2">Creación de Usuarios</a>
                    <a class="dropdown-item" href="siguientePaso2">Admin. Usuarios</a>
                    <div class="dropdown-divider"></div>
                </div>
            </li> 
        </sec:authorize>

        <li class="nav-item">
            <a class="nav-link" href="transacciones">
                <!--  <i class="fas fa-fw fa-chart-area"></i> -->
                <span>Transacción</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="simulador">
                <!--  <i class="fas fa-fw fa-chart-area"></i> -->
                <span>Simulador</span>
            </a>
        </li>


    </ul>
