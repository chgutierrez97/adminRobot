<%-- 
    Document   : jsPro
    Created on : 10/07/2019, 01:04:35 AM
    Author     : Christian Gutierrez
--%>

<%@ page session="false"%>
<%@ include file="taglibs.jsp"%>



<!-- Jquery 
================================================== 

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>-->

<script src= "<c:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>

<!-- Poppper 
================================================== -->
<script src= "<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap 
================================================== -->
<script src= "<c:url value="/resources/js/bootstrap.min.js"/>"></script>

<!-- Mask 
================================================== --> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery.mask.js" />"></script>

<!-- Js Propios
================================================== -->
 <script src= "<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
 <script src= "<c:url value="/resources/js/dataTables.bootstrap4.min.js"/>"></script>
<script src= "<c:url value="/resources/js/validaPantalla2y3.js"/>"></script>
<script src= "<c:url value="/resources/js/emulador.js"/>"></script>
<script src= "<c:url value="/resources/js/admin_transaccion.js"/>"></script>
<script src= "<c:url value="/resources/js/sb-admin-2.js"/>"></script>
<script src= "<c:url value="/resources/js/sb-admin-2.min.js"/>"></script>
<script src= "<c:url value="/resources/js/blockUI.js"/>"></script> 
<script type="text/javascript">
    window.addEventListener("submit", () => {
        $.blockUI({ message: '<h4><img src="../robotadmin/resources/images/loader.gif"> Cargando...</h4>' }); 
    })



</script>


