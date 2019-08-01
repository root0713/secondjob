<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!-- https://startbootstrap.com/previews/sb-admin-2/ -->
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
							<input type="button" value="문의 수정" class="btn btn-info" onclick="create();"/>
						</div>
					</nav>
					<!-- End of Topbar -->
					<!-- Begin Page Content -->
					<form name="frm" id="frm" action="<c:url value='/admin/request/update'/>" method="post" >
					
						<div class="container-flud">
							<!-- DataTales Example -->
							<div class="card shadow mb-4">
								<div class="card-body">
									<div class="table-responsive">
										<div class="container-flud">
											<div class="row">
												<div class="col">
													<fmt:parseDate value="${result.req_date}" var="req_date" pattern="yyyy-MM-dd HH:mm:ss"/>
													<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
														<!-- Brand/logo -->
														<span class="navbar-text">접수정보 ( 접수번호 : <c:out value='${result.req_seq}'/> )</span>
														<input type="hidden" name="req_seq" id="req_seq" value="<c:out value='${result.req_seq}'/>"/>													
													</nav>
													<div class="form-group">
														<label for="req_date">접수일자</label>&nbsp;&nbsp;
														<input type="text" class="form-control" id="req_date" readonly="readonly" value="<fmt:formatDate value="${req_date}" pattern="yyyy.MM.dd"/>">
													</div>
													<div class="form-group">
														<label for="req_datetime">접수시간</label>&nbsp;&nbsp;
														<input type="text" class="form-control" id="req_datetime" readonly="readonly" value="<fmt:formatDate value="${req_date}" pattern="HH:mm:ss"/>">
													</div>
													<div class="form-group">
														<label for="req_name">성명</label>&nbsp;&nbsp;
														<input type="text" class="form-control" id="req_name" readonly="readonly" value="<c:out value='${result.req_name}'/>">
													</div>
													<div class="form-group">
														<label for="req_tel">연락처</label>&nbsp;&nbsp;
														<input type="text" class="form-control" id="req_tel" readonly="readonly" value="<c:out value='${result.req_tel}'/>">
													</div>
													<div class="form-group">
														<label for="req_content">내용</label>
														<textarea class="form-control" rows="5" id="req_content" readonly="readonly"><c:out value='${result.req_content}'/></textarea>
													</div>
													<div class="form-group">
														<label for="req_content">처리결과</label>
														<select class="form-control" id="proc_yn" name="proc_yn">
															<option value="Y" <c:if test="${result.proc_yn == 'Y'}">selected</c:if>>처리완료</option>
															<option value="N" <c:if test="${result.proc_yn == 'N'}">selected</c:if>>미처리</option>
															<option value="P" <c:if test="${result.proc_yn == 'P'}">selected</c:if>>처리중</option>
														</select>
													</div>
												</div>
												<div class="col">
													<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
														<!-- Brand/logo -->
														<span class="navbar-text">고객관리</span>
													</nav>
													<div class="form-group" >
														<div class="form-inline">
															<label for="contrt_type">MEMO</label>&nbsp;&nbsp;
															<input type="button" value="추가" class="btn btn-success" onclick="addRequestHistoryInfo();"/>
														</div>
														<div class="form-horizontal" id="historyInfo">
															<c:choose>
																<c:when test="${ empty result.requestHistoryDtoList }">
																	<div class="row">
																		<div class="colsm-3">
																			<input type="text" class="form-control form-control-sm" placeholder="담당자" id="hist_name" name="requestHistoryDtoList[0].hist_name" value="">&nbsp;&nbsp;
																		</div>
																		<div class="col"><textarea class="form-control" rows="5" id="req_hist_content" name="requestHistoryDtoList[0].hist_content"></textarea></div>
																	</div>
																</c:when>
																<c:otherwise>
																	<c:forEach var="list" items="${ result.requestHistoryDtoList }" varStatus="status">
																		<input type="hidden" name="requestHistoryDtoList[${status.index}].hist_seq" id="hist_seq" value="<c:out value='${list.hist_seq}'/>"/>
																		<div class="row">
																			<div class="colsm-3">
																				<input type="text" class="form-control form-control-sm" placeholder="담당자" id="hist_name" name="requestHistoryDtoList[${status.index}].hist_name" readonly="readonly" value="<c:out value="${list.hist_name}"/>">&nbsp;&nbsp;
																				<input type="text" class="form-control" id="req_hist_date" readonly="readonly" value="<c:out value='${list.hist_date}'/>">&nbsp;&nbsp;
																			</div>
																			<div class="col"><textarea class="form-control" rows="5" id="req_hist_content" name="requestHistoryDtoList[${status.index}].hist_content" readonly="readonly"><c:out value='${list.hist_content}'/></textarea></div>
																		</div>
																	</c:forEach>															
																</c:otherwise>
															</c:choose>
														</div>
													</div>	
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					
					</form>
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
		<!-- Bootstrap core JavaScript-->
		<script src="<c:url value='/vendor/jquery/jquery.min.js'/>"></script>
		<script src="<c:url value='/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
		<!-- Core plugin JavaScript-->
		<script src="<c:url value='/vendor/jquery-easing/jquery.easing.min.js'/>"></script>
		<!-- Custom scripts for all pages-->
		<script src="<c:url value='/js/sb-admin-2.min.js'/>"></script>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
		<script src="<c:url value='/js/bootstrap-datepicker.kr.js'/>" charset="UTF-8"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
			    console.log( "ready!" );
			});
			
			function goToForm(){
				location.href= "<c:url value='/admin/product/form'/>";
			}

			function goToProduct(){
				location.href= "<c:url value='/admin/product/list'/>";
			}

			function goToRequest(){
				location.href= "<c:url value='/admin/request/list'/>";
			}
			
			function create(){
				$('#frm').submit();
			}
			
			
			function addRequestHistoryInfo(){
				var index = ($('#historyInfo .row').length + 1).toString();
				console.log('historyInfo id : ' + index);
				$('#historyInfo').append('<div class="row"><div class="colsm-3"><input type="text" class="form-control form-control-sm" placeholder="담당자" d="hist_name_'+index+'" name="requestHistoryDtoList['+index+'].hist_name" value="">&nbsp;&nbsp;</div><div class="col"><textarea class="form-control" rows="5" id="hist_content_'+index+'" name="requestHistoryDtoList['+index+'].hist_content"></textarea></div></div>');
			}

		</script>
	</body>
</html>