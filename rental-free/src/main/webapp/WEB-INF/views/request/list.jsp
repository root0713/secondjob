<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>RENTFREE</title>
	<!-- Custom fonts for this template -->
	<link href="<c:url value='/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<!-- Custom styles for this template -->
	<link href="<c:url value='/css/sb-admin-2.min.css'/>" rel="stylesheet">
	
	<!-- Custom styles for this page -->
	<link href="<c:url value='/vendor/datatables/dataTables.bootstrap4.min.css'/>" rel="stylesheet">
    <style type="text/css">
/* Hiding the checkbox, but allowing it to be focused */
.badgebox
{
    opacity: 0;
}

.badgebox + .badge
{
    /* Move the check mark away when unchecked */
    text-indent: -999999px;
    /* Makes the badge's width stay the same checked and unchecked */
	width: 27px;
}

.badgebox:focus + .badge
{
    /* Set something to make the badge looks focused */
    /* This really depends on the application, in my case it was: */
    
    /* Adding a light border */
    box-shadow: inset 0px 0px 5px;
    /* Taking the difference out of the padding */
}

.badgebox:checked + .badge
{
    /* Move the check mark back when checked */
	text-indent: 0;
}
        
    </style>
</head>
<body id="page-top">
  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Admin </div>
      </a>

      <!-- Nav Item - Tables -->
      <li class="nav-item active">
        <a class="nav-link" href="javascript:goToProduct();">
          <i class="fas fa-fw fa-table"></i>
          <span>매물관리</span></a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="javascript:goToRequest();">
          <i class="fas fa-fw fa-table"></i>
          <span>문의관리</span></a>
      </li>      

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">
	<form name="srchFrm" id="srchFrm" action="<c:url value='/admin/request/list'/>" >
	<div class="container">
		<div class="row">
		    <div class="">
		       <div class="filter-sidebar">
		        <div class="form-group">
		        	<input type="text" placeholder="성명" class="form-control" id="srch_req_name" name="srch_req_name" value="${params.srch_req_name}"/>
	             </div>
	             <div class="form-group">
		        	<input type="text" placeholder="연락처" class="form-control" id="srch_req_tel" name="srch_req_tel" value="${params.srch_req_tel}"/>
	             </div>
	            <hr>
	            <button type="btn" class="btn btn-primary" form="srchFrm">SEARCH</button>
	            <div class="pb-3"></div>
	          </div>
		    </div>
		</div>
	</div>
	</form>

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
				<colgroup>
					<col width="9%">
					<col width="9%">
					<col width="9%">
					<col width="9%">
					<col width="9%">
					
					<col>
					<col width="9%">
				</colgroup>                
                  <thead>
                    <tr>
                      <th>접수번호</th>
                      <th>접수일자</th>
                      <th>접수시간</th>
                      <th>성명</th>
                      <th>연락처</th>
                      
                      <th>요청사항</th>
                      <th>처리여부</th>
                    </tr>
                  </thead>
                  <tbody>
					<c:if test="${ empty result }">
						<tr>
							<td colspan="7" align="center">조회된 결과가 없습니다.</td>
						</tr>
					</c:if>
 					<c:forEach var="list" items="${ result }" varStatus="stauts">
	 					<fmt:parseDate value="${list.req_date}" var="req_date" pattern="yyyy-MM-dd HH:mm:ss"/>
	                    <tr>
	                      <td><a href="#" onclick="goToForm(<c:out value='${list.req_seq}'/>);"><c:out value="${list.req_seq}"/></a></td>
	                      <td><fmt:formatDate value="${req_date}" pattern="yyyy.MM.dd"/></td>
	                      <td><fmt:formatDate value="${req_date}" pattern="HH:mm:ss"/></td>
	                      <td><c:out value="${list.req_name}"/></td>
	                      <td><c:out value="${list.req_tel}"/></td>
	                      
	                      <td><c:out value="${list.req_content}"/></td>
	                      <td>
	                      	<c:choose>
	                      		<c:when test="${list.proc_yn == 'Y' }">처리완료</c:when>
	                      		<c:when test="${list.proc_yn == 'N' }">미처리</c:when>
	                      		<c:when test="${list.proc_yn == 'P' }">처리중</c:when>
	                      	</c:choose>
	                      </td>
	                    </tr>
					</c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
	<script type="text/javascript">
		function goToForm(req_seq){
			var url = "<c:url value='/admin/request/updateForm'/>?req_seq="+req_seq;
			location.href= url;
		}

		function updateServiceYN(prod_seq, obj){
			console.log('prod_seq : '+ prod_seq);
			console.log('obj : '+ obj.checked);

			$('#frm').find('input[name="prod_seq"]').val(prod_seq);
			$('#frm').find('input[name="service_yn"]').val(obj.checked ? 'Y' : 'N');

			$('#frm').submit();
		}
		
		function goToProduct(){
			location.href= "<c:url value='/admin/product/list'/>";
		}

		function goToRequest(){
			location.href= "<c:url value='/admin/request/list'/>";
		}
	</script>
	<!-- Bootstrap core JavaScript-->
	<script src="<c:url value='/vendor/jquery/jquery.min.js'/>"></script>
	<script src="<c:url value='/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
	
	<!-- Core plugin JavaScript-->
	<script src="<c:url value='/vendor/jquery-easing/jquery.easing.min.js'/>"></script>
	
	<!-- Custom scripts for all pages-->
	<script src="<c:url value='/js/sb-admin-2.min.js'/>"></script>
	
	<!-- Page level plugins -->
	<script src="<c:url value='/vendor/datatables/jquery.dataTables.min.js'/>"></script>
	<script src="<c:url value='/vendor/datatables/dataTables.bootstrap4.min.js'/>"></script>
	
	<!-- Page level custom scripts -->
	<script src="<c:url value='/js/demo/datatables-demo.js'/>"></script>
	
</body>

</html>