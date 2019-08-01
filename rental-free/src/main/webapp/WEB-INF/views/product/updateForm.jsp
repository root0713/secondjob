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
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
	    <style type="text/css">
	        .datepicker {
	            font-size: 0.875em;
	        }
	        .datepicker td, .datepicker th {
	            width: 1.5em;
	            height: 1.5em;
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
							<input type="button" value="물건 수정" class="btn btn-info" onclick="create();"/>
						</div>
					</nav>
					<!-- End of Topbar -->
					<!-- Begin Page Content -->
					<form name="frm" id="frm" action="<c:url value='/admin/product/update'/>" method="post" enctype="multipart/form-data">
					
					<div class="container-flud">
						<!-- DataTales Example -->
						<div class="card shadow mb-4">
							<div class="card-body">
								<div class="table-responsive">
									<div class="container-flud">
										<div class="row">
											<div class="col-sm-6">
												<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
													<!-- Brand/logo -->
													<span class="navbar-text">물건정보 ( 물건번호 : <c:out value='${result.prod_seq}'/> )</span>
													<input type="hidden" name="prod_seq" id="prod_seq" value="<c:out value='${result.prod_seq}'/>"/>													
												</nav>
												<div class="form-group">
													&nbsp;&nbsp;
													<input type="text" class="form-control form-control-sm" id="address1" name="prod_title" placeholder="물건이름" value="<c:out value='${result.prod_title}'/>">
												</div>												
												<div class="form-group">
													<div class="form-inline">
														<label for="email">지번주소</label>&nbsp;&nbsp;
														<input type="button" value="주소찾기" class="btn btn-success" onclick="getAddress();"/>
													</div>
													<input type="text" class="form-control form-control-sm" id="address1" name="address1" placeholder="지번주소" readonly="readonly" value="<c:out value='${result.address1}'/>">
													<input type="text" class="form-control form-control-sm" id="address2" name="address2" placeholder="도로" readonly="readonly" value="<c:out value='${result.address2}'/>">
													<input type="text" class="form-control form-control-sm" id="address3" name="address3" placeholder="상세주소" value="<c:out value='${result.address3}'/>">
												</div>
												<div class="form-group">
													<div class="form-inline">
														<label for="exclusive_area">전용면적</label>&nbsp;&nbsp;
														<input type="text" class="col form-control form-control-sm" id="m_exclusive_area" name="m_exclusive_area" placeholder="제곱미터" value="<c:out value='${result.m_exclusive_area}'/>">&nbsp;&nbsp;
														<input type="button" value="변환" class="btn btn-success" onclick="transArea('exclusive_area');"/>&nbsp;&nbsp;
														<input type="text" class="col form-control form-control-sm" id="exclusive_area" name="exclusive_area" placeholder="PY" value="<c:out value='${result.exclusive_area}'/>">
													</div>
												</div>
												<div class="form-group">
													<div class="form-inline">
														<label for="common_area">분양면적</label>&nbsp;&nbsp;
														<input type="text" class="col form-control form-control-sm" id="m_common_area" name="m_common_area" placeholder="제곱미터" value="<c:out value='${result.m_common_area}'/>">&nbsp;&nbsp;
														<input type="button" value="변환" class="btn btn-success" onclick="transArea('common_area');"/>&nbsp;&nbsp;
														<input type="text" class="col form-control form-control-sm" id="common_area" name="common_area" placeholder="PY" value="<c:out value='${result.common_area}'/>">
													</div>
												</div>
												<div class="form-group">
													<div class="form-inline">
														<input type="text" class="col form-control form-control-sm" id="total_floor_cnt" name="total_floor_cnt" placeholder="총층수" value="<c:out value='${result.total_floor_cnt}'/>">&nbsp;&nbsp;
														<input type="text" class="col form-control form-control-sm" id="floor_cnt" name="floor_cnt" placeholder="층수" value="<c:out value='${result.floor_cnt}'/>">&nbsp;&nbsp;
														<input type="text" class="col form-control form-control-sm" id="parking_cnt" name="parking_cnt" placeholder="주차대수" value="<c:out value='${result.parking_cnt}'/>">&nbsp;&nbsp;
													</div>
												</div>												
											</div>
											<div class="col-sm-6">
												<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
													<!-- Brand/logo -->
													<span class="navbar-text">옵션정보</span>
												</nav>
												<c:set var="options" value="${ result.options}"/>
												<div class="form-group">
													<div class="form-inline">
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch0" name="option" value="${fn:substring(options,0,1)}" <c:if test="${fn:substring(options,0,1) == '1'}">checked</c:if>>
																<label class="custom-control-label" for="switch0">엘레베이터</label>
															</div>
														</div>&nbsp;&nbsp;
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch1" name="option" value="${fn:substring(options,1,2)}" <c:if test="${fn:substring(options,1,2) == 1}">checked</c:if>>
																<label class="custom-control-label" for="switch1">독립화장실</label>
															</div>
														</div>&nbsp;&nbsp;
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch2" name="option" value="${fn:substring(options,2,3)}" <c:if test="${fn:substring(options,2,3) == 1}">checked</c:if>>
																<label class="custom-control-label" for="switch2">수도</label>
															</div>
														</div>&nbsp;&nbsp;
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch3" name="option" value="${fn:substring(options,3,4)}" <c:if test="${fn:substring(options,3,4) == 1}">checked</c:if>>
																<label class="custom-control-label" for="switch3">가스</label>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="form-inline">
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch4" name="option" value="${fn:substring(options,4,5)}" <c:if test="${fn:substring(options,4,5) == 1}">checked</c:if>>
																<label class="custom-control-label" for="switch4">주차가능</label>
															</div>
														</div>&nbsp;&nbsp;
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch5" name="option" value="${fn:substring(options,5,6)}" <c:if test="${fn:substring(options,5,6) == 1}">checked</c:if>>
																<label class="custom-control-label" for="switch5">무료주차</label>
															</div>
														</div>&nbsp;&nbsp;
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch6" name="option" value="${fn:substring(options,6,7)}" <c:if test="${fn:substring(options,6,7) == 1}">checked</c:if>>
																<label class="custom-control-label" for="switch6">냉방기구</label>
															</div>
														</div>&nbsp;&nbsp;
														<div class="form-group">
															<div class="custom-control custom-switch">
																<input type="checkbox" class="custom-control-input" id="switch7" name="option" value="${fn:substring(options,7,8)}" <c:if test="${fn:substring(options,7,8) == 1}">checked</c:if>>
																<label class="custom-control-label" for="switch7">난방기구</label>
															</div>
														</div>
													</div>
												</div>
												<input type="hidden" name="options" id="options" />
												<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
													<!-- Brand/logo -->
													<span class="navbar-text">이미지정보</span>&nbsp;&nbsp;<input type="button" value="추가" class="btn btn-success" onclick="addAttachFileInfo();"/>
												</nav>
												<div id="attachInfo">
													<c:choose>
														<c:when test="${ empty result.attachFileDtoList }">
															<div class="form-group" >
																<input type="file" class="input-file" id="attachFileDtoList[0].att_file" name="attachFileDtoList[0].att_file">
															</div>															
														</c:when>
														<c:otherwise>
															<c:forEach var="list" items="${ result.attachFileDtoList }" varStatus="status" >
																<div class="form-group">
																	<input type="file" class="input-file" id="AttachFileDtoList[${status.index}].att_file" name="AttachFileDtoList[${status.index}].att_file" disabled="disabled">파일 아이디 : <c:out value="${list.att_seq}"/>
																	<img alt="" src="data:image/jpg;base64,<c:out value='${ list.att_file_display}'/>" width="40px" height="40px"/>
																</div>
															</c:forEach>
														</c:otherwise>
													</c:choose>			
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
													<!-- Brand/logo -->
													<span class="navbar-text">Contract Point</span>
												</nav>
												<div class="form-group" >
													<div class="form-inline">
														<label for="contrt_type">연락처</label>&nbsp;&nbsp;
														<input type="button" value="추가" class="btn btn-success" onclick="addContractInfo();"/>
													</div>
													<div class="form-horizontal" id="contractInfo">
														<c:forEach var="list" items="${ result.contractDtoList }" varStatus="status">
															<div class="form-inline">
																<select class="form-control form-control-sm" id="contrt_type" name="contractDtoList[${status.index}].contrt_type">
																	<option value="01" <c:if test="${list.contrt_type == '01'}">selected</c:if>>소유자</option>
																	<option value="02" <c:if test="${list.contrt_type == '02'}">selected</c:if>>관리인</option>
																	<option value="03" <c:if test="${list.contrt_type == '03'}">selected</c:if>>대리인</option>
																	<option value="04" <c:if test="${list.contrt_type == '04'}">selected</c:if>>중개인</option>
																	<option value="05" <c:if test="${list.contrt_type == '05'}">selected</c:if>>거주자</option>
																	<option value="06" <c:if test="${list.contrt_type == '06'}">selected</c:if>>임차인</option>
																	<option value="07" <c:if test="${list.contrt_type == '07'}">selected</c:if>>기타</option>
																</select>&nbsp;&nbsp;
																<input type="text" class="form-control form-control-sm" placeholder="회원명" id="contrt_name" name="contractDtoList[${status.index}].contrt_name" value="<c:out value="${list.contrt_name}"/>">&nbsp;&nbsp;
																<input type="text" class="form-control form-control-sm" placeholder="연락처" id="ccontrt_tel" name="contractDtoList[${status.index}].contrt_tel" value="<c:out value="${list.contrt_tel}"/>">
															</div>
														</c:forEach>
													</div>
												</div>
												<div class="form-group">
													<div class="form-inline">
														<label for="unusual">특이사항</label>&nbsp;&nbsp;
														<input type="text" class="col form-control form-control-sm" id="unusual" name="unusual" value="<c:out value="${result.unusual}"/>">&nbsp;&nbsp;
													</div>
												</div>
												<div class="form-group">
													<div class="form-inline">
														<label for="move_date">입주가능일</label>&nbsp;&nbsp;
														<input type="text" class="form-control form-control-sm" placeholder="날짜" id="move_date" name="move_date" readonly="readonly" value="<c:out value="${result.move_date}"/>">&nbsp;&nbsp;
														<input type="text" class="form-control form-control-sm" placeholder="현재상태" id="status" name="status" value="<c:out value="${result.status}"/>">&nbsp;&nbsp;
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
													<!-- Brand/logo -->
													<span class="navbar-text">상세설명</span>
												</nav>&nbsp;&nbsp;
												<div class="form-group">
													<textarea class="form-control" rows="5" id="prod_content" name="prod_content"><c:out value="${result.prod_content}"/></textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
													<!-- Brand/logo -->
													<span class="navbar-text">가격정보</span>
												</nav>
												
												<div class="form-group">
													<div class="form-check-inline">
														<label class="form-check-label" for="rdo_prod_type_01">
														<input type="radio" class="form-check-input" id="rdo_prod_type_01" name="prod_type" value="01" <c:if test="${result.prod_type == '01'}">checked</c:if>>분양
														</label>
													</div>&nbsp;&nbsp;
													<div class="form-check-inline">
														<label class="form-check-label" for="rdo_prod_type_02">
														<input type="radio" class="form-check-input" id="rdo_prod_type_02" name="prod_type" value="02" <c:if test="${result.prod_type == '02'}">checked</c:if>>매매
														</label>
													</div>&nbsp;&nbsp;
													<div class="form-check-inline">
														<label class="form-check-label" for="rdo_prod_type_03">
														<input type="radio" class="form-check-input" id="rdo_prod_type_03" name="prod_type" value="03" <c:if test="${result.prod_type == '03'}">checked</c:if>>전세
														</label>
													</div>&nbsp;&nbsp;
													<div class="form-check-inline">
														<label class="form-check-label" for="rdo_prod_type_04">
														<input type="radio" class="form-check-input" id="rdo_prod_type_04" name="prod_type" value="04" <c:if test="${result.prod_type == '04'}">checked</c:if>>월세
														</label>
													</div>&nbsp;&nbsp;
												</div>
												<div class="form-group">
													<div class="form-inline">
														<label for="sale_price">매각금액</label>&nbsp;
														<input type="text" class="form-control form-control-sm" id="sale_price" name="sale_price" value="<c:out value="${result.sale_price}"/>">만원&nbsp;
													</div>
												</div>
												<div class="form-group">
													<div class="form-inline">
														<label for="deposit">보증금</label>&nbsp;
														<input type="text" class="form-control form-control-sm" id="deposit" name="deposit" value="<c:out value="${result.deposit}"/>">만원&nbsp;&nbsp;&nbsp;&nbsp;
														<label for="month_price">월세</label>&nbsp;
														<input type="text" class="form-control form-control-sm" id="month_price" name="month_price" value="<c:out value="${result.month_price}"/>">만원&nbsp;
													</div>
												</div>
												<div class="form-group">
													<div class="form-inline">
														<label for="rdo_mntnc_includ_yn_01">관리비</label>&nbsp;
														<div class="form-check-inline">
															<label class="form-check-label" for="rdo_mntnc_includ_yn_Y">
															<input type="radio" class="form-check-input" id="rdo_mntnc_includ_yn_Y" name="mntnc_includ_yn" value="Y" <c:if test="${result.mntnc_includ_yn == 'Y'}">checked</c:if>>포함
															</label>
														</div>&nbsp;&nbsp;
														<div class="form-check-inline">
															<label class="form-check-label" for="rdo_mntnc_includ_yn_N">
															<input type="radio" class="form-check-input" id="rdo_mntnc_includ_yn_N" name="mntnc_includ_yn" value="N" <c:if test="${result.mntnc_includ_yn == 'N'}">checked</c:if>>별도
															</label>
														</div>&nbsp;&nbsp;
													</div>
													<div class="form-group">
														<div class="form-inline">
															<input type="text" class="form-control form-control-sm" placeholder="관리비" id="mntnc_fee" name="mntnc_fee"  value="<c:out value="${result.mntnc_fee}"/>">만원&nbsp;
															<input type="text" class="form-control form-control-sm" placeholder="관리비 불포함 내역" id="mntnc_content" name="mntnc_content" value="<c:out value="${result.mntnc_content}"/>">&nbsp;
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
													<!-- Brand/logo -->
													<span class="navbar-text">물건이력관리</span>
												</nav>
												<div class="form-group">
													<div class="form-inline">
														<label for="email">MEMO</label>&nbsp;&nbsp;
														<input type="button" value="추가" class="btn btn-success" onclick="addHistoryInfo();"/>
													</div>
													<div class="form-horizontal" id="historyInfo">
														<c:choose>
															<c:when test="${ empty result.productHistoryDto }">
																<div class="form-horizontal">
																	<div class="form-inline">
																		<input type="text" class="form-control col-md-offset-1" placeholder="담당자" id="hist_name" name="productHistoryDto[0].hist_name" value=" ">&nbsp;&nbsp;
																		<textarea class="form-control  col-md-offset-1" rows="3" cols="40" id="hist_content" name="productHistoryDto[0].hist_content"></textarea>
																	</div>
																</div>															
															</c:when>
															<c:otherwise>
																<c:forEach var="list" items="${ result.productHistoryDto }" varStatus="status">
																	<div class="form-inline">
																		<input type="text" class="form-control col-md-offset-1" placeholder="담당자" id="hist_name" name="productHistoryDto[${status.index}].hist_name" value="<c:out value="${list.hist_name}"/>">&nbsp;&nbsp;
																		<textarea class="form-control  col-md-offset-1" rows="3" cols="40" id="hist_content" name="productHistoryDto[${status.index}].hist_content">&nbsp;<c:out value="${list.hist_content}"/></textarea>
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
			    $('#move_date').datepicker({
			        weekStart: 1,
			        daysOfWeekHighlighted: "6,0",
			        autoclose: true,
			        todayHighlight: true,
			        format: "yyyy-mm-dd",
		            language: "kr"
			    });
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
				setOptionsValue();
				if(!$('#exclusive_area').val()){
					transArea('exclusive_area');
				}
				if(!$('#common_area').val()){
					transArea('common_area');
				}
				$('#frm').submit();
			}

			function setOptionsValue(){
				var optionsValue = '';
			    $('input:checkbox[name="option"]').each(function(i) {
			    	this.value = (Number(this.checked));
			    	optionsValue += String(this.value);
			    });
			    $('#options').val(optionsValue);
			}

			function addAttachFileInfo(){
				var index = ($('#attachInfo .form-group').length).toString();
				console.log('attachInfo index : ' + index);
				$('#attachInfo').append('<div class="form-group" ><input type="file" class="input-file" id="attachFileDtoList['+index+'].att_file" name="attachFileDtoList['+index+'].att_file"></div>');
			}
			
			function addContractInfo(){
				var index = ($('#contractInfo .form-inline').length).toString();
				console.log('contract index : ' + index);
				$('#contractInfo').append('<div class="form-inline"><select class="form-control form-control-sm" id="contrt_type_'+index+'" name="contractDtoList['+index+'].contrt_type"><option value="01">소유자</option><option value="02">관리인</option><option value="03" >대리인</option><option value="04">중개인</option><option value="05">거주자</option><option value="06">임차인</option><option value="07">기타</option></select>&nbsp;&nbsp;<input type="text" class="form-control form-control-sm" placeholder="회원명" id="contrt_name_'+index+'" name="contractDtoList['+index+'].contrt_name">&nbsp;&nbsp;<input type="text" class="form-control form-control-sm" placeholder="연락처" id="contrt_tel_'+index+'" name="contractDtoList['+index+'].contrt_tel"></div>');
			}

			function addHistoryInfo(){
				var index = ($('#historyInfo .form-inline').length).toString();
				console.log('historyInfo id : ' + index);
				$('#historyInfo').append('<div class="form-inline"><input type="text" class="form-control col-md-offset-1" placeholder="담당자" id="hist_name_'+index+'" name="productHistoryDto['+index+'].hist_name">&nbsp;&nbsp;<textarea class="form-control  col-md-offset-1" rows="3" cols="40" id="hist_content_'+index+'" name="productHistoryDto['+index+'].hist_content"></textarea></div>');
			}
			
			
		    function getAddress() {
		    	var width = 500; //팝업의 너비
		    	var height = 600; //팝업의 높이
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
		                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var roadAddr = data.roadAddress; // 도로명 주소 변수
		                var extraRoadAddr = ''; // 참고 항목 변수
	
		                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                    extraRoadAddr += data.bname;
		                }
		                // 건물명이 있고, 공동주택일 경우 추가한다.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                if(extraRoadAddr !== ''){
		                    extraRoadAddr = ' (' + extraRoadAddr + ')';
		                }
	
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                
		                document.getElementById("address2").value = roadAddr;
		                document.getElementById("address1").value = data.jibunAddress;
		                
		                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
		                if(roadAddr !== ''){
		                    document.getElementById("address3").value = extraRoadAddr;
		                } else {
		                    document.getElementById("address3").value = '';
		                }
		            }
		        }).open({ 
			        autoClose: true
			        , popupName: 'postcodePopup'
					, left: (window.screen.width / 2) - (width / 2)
					, top: (window.screen.height / 2) - (height / 2)
				});
		    }

		    function transArea(eid){
		    	$('#'+eid).val(Math.round(parseFloat($('#m_'+eid).val()) / 3.3058 * 100)/100);
			}
		</script>
	</body>
</html>