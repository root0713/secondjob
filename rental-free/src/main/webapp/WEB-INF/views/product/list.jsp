<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<form name="frm" id="frm" action="<c:url value='/admin/product/updateService'/>" method="post" >
	<input type="hidden" id="prod_seq" name="prod_seq" value=""/>
	<input type="hidden" id="service_yn" name="service_yn" value=""/>
</form>	
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
	<form name="srchFrm" id="srchFrm" action="<c:url value='/admin/product/list'/>" method="psot" >
	<div class="container">
		<div class="row">
		    <div class="">
		       <div class="filter-sidebar">
		        <div class="form-group">
		        	<input type="text" placeholder="소재지" class="form-control" id="srch_address" name="srch_address" value="${params.srch_address}"/>
	             </div>
	             <div class="form-group">
		        	<input type="text" placeholder="소유자" class="form-control" id="srch_contrt_name" name="srch_contrt_name" value="${params.srch_contrt_name}"/>
	             </div>
	             <div class="form-group">
		        	<input type="text" placeholder="연락처" class="form-control" id="srch_contrt_tel" name="srch_contrt_tel" value="${params.srch_contrt_tel}"/>
	             </div>
	             <div class="form-group">
		        	<input type="text" placeholder="담당자" class="form-control" id="srch_hist_name" name="srch_hist_name" value="${params.srch_hist_name}"/>
	             </div>
	            <div class="form-group">
	                <select class="form-control" id="srch_service_yn" name="srch_service_yn">
	                  <option value="" <c:if test="${!empty params.srch_service_yn }">selected</c:if>>전체</option>
	                  <option value="Y" <c:if test="${params.srch_service_yn == 'Y' }">selected</c:if>>서비스 ON</option>
	                  <option value="N" <c:if test="${params.srch_service_yn == 'N' }">selected</c:if>>서비스 OFF</option>
	                </select>
	             </div>
	            <div class="form-group">
	                <select class="form-control" id="srch_prod_type" name="srch_prod_type">
	                  <option value="" <c:if test="${!empty params.srch_prod_type }">selected</c:if>>전체</option>
	                  <option value="01" <c:if test="${params.srch_prod_type == '01'}">selected</c:if>>분양</option>
	                  <option value="02" <c:if test="${params.srch_prod_type == '02'}">selected</c:if>>매매</option>
	                  <option value="03" <c:if test="${params.srch_prod_type == '03'}">selected</c:if>>전세</option>
	                  <option value="04" <c:if test="${params.srch_prod_type == '04'}">selected</c:if>>월세</option>
	                </select>
	             </div>
	             <div class="form-group">
		        	<input type="text" placeholder="최소면적" id="srch_min_area" name="srch_min_area" class="col-md-5" value="${params.srch_min_area}"/>
		        	~ <input type="text" placeholder="최대면적"  id="srch_max_area" name="srch_max_area" class="col-md-5" value="${params.srch_max_area}"/>
	             </div>
	             <div class="form-group">
		        	<input type="text" placeholder="최소보증금" id="srch_min_deposit" name="srch_min_deposit" class="col-md-5" value="${params.srch_min_deposit}"/>
		        	~ <input type="text" placeholder="최대보증금"  id="srch_max_deposit" name="srch_max_deposit" class="col-md-5" value="${params.srch_max_deposit}"/>
	             </div>
	             <div class="form-group">
		        	<input type="text" placeholder="최소월세" id="srch_min_price" name="srch_min_price" class="col-md-5" value="${params.srch_min_price}"/>
		        	~ <input type="text" placeholder="최대월세"  id="srch_min_price" name="srch_min_price" class="col-md-5" value="${params.srch_min_price}"/>
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

        <!-- Topbar -->
        <nav class="navbar bg-white static-top shadow">
		<div>
		  <a href="#" onclick="javascript:goToForm('');"><input type="button" value="물건 등록" class="btn btn-info"/></a>
		  <!-- 
          <input type="button" value="Excel Down" class="btn btn-success"/>
           -->
		</div>
        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
				<colgroup>
					<col width="8%">
					<col width="8%">
					<col width="8%">
					<col width="15%">
					<col>
					
					<col width="15%">
					<col width="8%">
					<col width="8%">
				</colgroup>                
                  <thead>
                    <tr>
                      <th>물건번호</th>
                      <th>서비스</th>
                      <th>매물종류</th>
                      <th>주소1</th>
                      <th>물건명</th>
                      
                      <th>면적</th>
                      <th>옵션정보</th>
                    </tr>
                  </thead>
                  <tbody>
					<c:if test="${ empty result }">
						<tr>
							<td colspan="10" align="center">조회된 결과가 없습니다.</td>
						</tr>
					</c:if>
 					<c:forEach var="list" items="${ result }" varStatus="stauts">
		                    <tr>
		                      <td><a href="#" onclick="goToForm(<c:out value='${list.prod_seq}'/>);"><c:out value="${list.prod_seq}"/></a></td>
		                      <td>
								  <div class="custom-control custom-switch" align="center">
								    <input type="checkbox" class="custom-control-input" id="service_yn_${stauts.index}" <c:if test="${list.service_yn == 'Y'}">checked</c:if> onchange="updateServiceYN(<c:out value='${list.prod_seq}'/>,this);">
								    <label class="custom-control-label" for="service_yn_${stauts.index}"></label>
								  </div>	                      
		                      </td>
		                      <td>
		                      	<c:choose>
		                      		<c:when test="${list.prod_type == '01' }">분양</c:when>
		                      		<c:when test="${list.prod_type == '02' }">매매</c:when>
		                      		<c:when test="${list.prod_type == '03' }">전세</c:when>
		                      		<c:when test="${list.prod_type == '04' }">월세</c:when>
		                      	</c:choose>
		                      </td>
		                      <td><c:out value="${list.address1}"/></td>
		                      <td><c:out value="${list.prod_title}"/></td>
		                      
		                      <td><c:out value="${list.common_area}"/>&nbsp;py</td>
		                      	<%
		                      		String[] optioTitles = {"엘레베이터", "독립화장실", "수도", "가스", "주차가능", "무료주차", "냉방기구", "난방기구"};
		                      	%>
		                   		<c:set var="optionTitles" value="<%=optioTitles %>" scope="session"/>
								<c:forEach begin="0" end="${fn:length(list.options)}" varStatus="stauts">
									<c:if test="${fn:substring(list.options,stauts.index,stauts.index+1) eq 1 }">
										<c:set var="optionContent" value="${optionContent} | ${optionTitles[stauts.index]}"/>
									</c:if>
								</c:forEach>		                      
		                      <td data-toggle="tooltip" data-animation="true" title="${optionContent}" onclick="alert('${optionContent}');">
		                      	Options
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
		function goToForm(prod_seq){
			var url = "<c:url value='/admin/product/createForm'/>";
			if(prod_seq != ''){
				url = "<c:url value='/admin/product/updateForm'/>?prod_seq="+prod_seq;
			}
			
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