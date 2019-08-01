<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>RENTFREE</title>

<link rel="stylesheet" href="<c:url value='/service/css/base.css'/>">
<link rel="stylesheet" href="<c:url value='/service/css/common.css'/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.0.0/css/bootstrap-slider.min.css">
<style type="text/css">
.js-load {
    display: none;
}
.js-load.active {
    display: block;
}
.is_comp.js-load:after {
    display: none;
}
.btn-wrap, .lists, .main {
    display: block;
}
.main {
    max-width: 640px;
    margin: 0 auto;
}
.lists {
    margin-bottom: 4rem;
}
.lists__item {
    padding: 20px;
    background: #EEE;
}
.lists__item:nth-child(2n) {
    background: #59b1eb;
    color: #fff;
}
.btn-wrap {
    text-align: center;
}
</style>
</head>

<body>
<div id="wrap">
	<!-- skip navigation -->
	<div id="u_skip">
		<dl>
			<dt class="blind">바로가기 메뉴</dt>
			<dd><a href="#container">본문으로 바로가기</a></dd>
		</dl>
	</div>
	<!-- //skip navigation -->
	<!-- header -->
	<header id="header">
		<h1><a href="#"><img src="../../../service/img/img_main_logo.png" alt="RENTFREE"/></a></h1>
	</header>
	<!-- //header -->
	<main id="container">
		<!-- section 01 -->
		<section class="visual">
			<a class="search" data-toggle="collapse" href="#search-box-wrap" role="button" aria-expanded="false" aria-controls="search-box-wrap">검색 조건설정</a>
			<div class="search-wrap" id="search-box-wrap">
				<form name="frm" id="frm" action="<c:url value='/service/list'/>" method="post" >
				<div class="search-box">
					<div class="search-item">
						<div class="left-area">
							<div class="item">
								<h3>지역</h3>
								<div class="select-area" id="srch_address">
									<select id="srch_address_first" name="srch_address_first">
									   <option value="">시/도</option>
									<option value="강원도">강원도</option><option value="경기">경기</option><option value="경상남도">경상남도</option><option value="경상북도">경상북도</option><option value="광주광역시">광주광역시</option><option value="대구광역시">대구광역시</option><option value="대전광역시">대전광역시</option><option value="부산광역시">부산광역시</option><option value="서울특별시">서울특별시</option><option value="세종특별자치시">세종특별자치시</option><option value="울산광역시">울산광역시</option><option value="인천광역시">인천광역시</option><option value="전라남도">전라남도</option><option value="전라북도">전라북도</option><option value="제주특별자치도">제주특별자치도</option><option value="충청남도">충청남도</option><option value="충청북도">충청북도</option>
									</select>
									<select id="srch_address_second" name="srch_address_second">
									    <option value="">구/시</option>
									
									<option value="강릉시" data-chained="강원도">강릉시</option><option value="고성군" data-chained="강원도">고성군</option><option value="동해시" data-chained="강원도">동해시</option><option value="삼척시" data-chained="강원도">삼척시</option><option value="속초시" data-chained="강원도">속초시</option><option value="양구군" data-chained="강원도">양구군</option><option value="양양군" data-chained="강원도">양양군</option><option value="영월군" data-chained="강원도">영월군</option><option value="원주시" data-chained="강원도">원주시</option><option value="인제군" data-chained="강원도">인제군</option><option value="정선군" data-chained="강원도">정선군</option><option value="철원군" data-chained="강원도">철원군</option><option value="춘천시" data-chained="강원도">춘천시</option><option value="태백시" data-chained="강원도">태백시</option><option value="평창군" data-chained="강원도">평창군</option><option value="홍천군" data-chained="강원도">홍천군</option><option value="화천군" data-chained="강원도">화천군</option><option value="횡성군" data-chained="강원도">횡성군</option><option value="가평군" data-chained="경기">가평군</option><option value="고양시 덕양구" data-chained="경기">고양시 덕양구</option><option value="고양시 일산동구" data-chained="경기">고양시 일산동구</option><option value="고양시 일산서구" data-chained="경기">고양시 일산서구</option><option value="과천시" data-chained="경기">과천시</option><option value="광명시" data-chained="경기">광명시</option><option value="광주시" data-chained="경기">광주시</option><option value="구리시" data-chained="경기">구리시</option><option value="군포시" data-chained="경기">군포시</option><option value="김포시" data-chained="경기">김포시</option><option value="남양주시" data-chained="경기">남양주시</option><option value="동두천시" data-chained="경기">동두천시</option><option value="부천시" data-chained="경기">부천시</option><option value="성남시 분당구" data-chained="경기">성남시 분당구</option><option value="성남시 수정구" data-chained="경기">성남시 수정구</option><option value="성남시 중원구" data-chained="경기">성남시 중원구</option><option value="수원시 권선구" data-chained="경기">수원시 권선구</option><option value="수원시 영통구" data-chained="경기">수원시 영통구</option><option value="수원시 장안구" data-chained="경기">수원시 장안구</option><option value="수원시 팔달구" data-chained="경기">수원시 팔달구</option><option value="시흥시" data-chained="경기">시흥시</option><option value="안산시 단원구" data-chained="경기">안산시 단원구</option><option value="안산시 상록구" data-chained="경기">안산시 상록구</option><option value="안성시" data-chained="경기">안성시</option><option value="안양시 동안구" data-chained="경기">안양시 동안구</option><option value="안양시 만안구" data-chained="경기">안양시 만안구</option><option value="양주시" data-chained="경기">양주시</option><option value="양평군" data-chained="경기">양평군</option><option value="여주시" data-chained="경기">여주시</option><option value="연천군" data-chained="경기">연천군</option><option value="오산시" data-chained="경기">오산시</option><option value="용인시 기흥구" data-chained="경기">용인시 기흥구</option><option value="용인시 수지구" data-chained="경기">용인시 수지구</option><option value="용인시 처인구" data-chained="경기">용인시 처인구</option><option value="의왕시" data-chained="경기">의왕시</option><option value="의정부시" data-chained="경기">의정부시</option><option value="이천시" data-chained="경기">이천시</option><option value="파주시" data-chained="경기">파주시</option><option value="평택시" data-chained="경기">평택시</option><option value="포천시" data-chained="경기">포천시</option><option value="하남시" data-chained="경기">하남시</option><option value="화성시" data-chained="경기">화성시</option><option value="거제시" data-chained="경상남도">거제시</option><option value="거창군" data-chained="경상남도">거창군</option><option value="고성군" data-chained="경상남도">고성군</option><option value="김해시" data-chained="경상남도">김해시</option><option value="남해군" data-chained="경상남도">남해군</option><option value="밀양시" data-chained="경상남도">밀양시</option><option value="사천시" data-chained="경상남도">사천시</option><option value="산청군" data-chained="경상남도">산청군</option><option value="양산시" data-chained="경상남도">양산시</option><option value="의령군" data-chained="경상남도">의령군</option><option value="진주시" data-chained="경상남도">진주시</option><option value="창녕군" data-chained="경상남도">창녕군</option><option value="창원시 마산합포구" data-chained="경상남도">창원시 마산합포구</option><option value="창원시 마산회원구" data-chained="경상남도">창원시 마산회원구</option><option value="창원시 성산구" data-chained="경상남도">창원시 성산구</option><option value="창원시 의창구" data-chained="경상남도">창원시 의창구</option><option value="창원시 진해구" data-chained="경상남도">창원시 진해구</option><option value="통영시" data-chained="경상남도">통영시</option><option value="하동군" data-chained="경상남도">하동군</option><option value="함안군" data-chained="경상남도">함안군</option><option value="함양군" data-chained="경상남도">함양군</option><option value="합천군" data-chained="경상남도">합천군</option><option value="경산시" data-chained="경상북도">경산시</option><option value="경주시" data-chained="경상북도">경주시</option><option value="고령군" data-chained="경상북도">고령군</option><option value="구미시" data-chained="경상북도">구미시</option><option value="군위군" data-chained="경상북도">군위군</option><option value="김천시" data-chained="경상북도">김천시</option><option value="문경시" data-chained="경상북도">문경시</option><option value="봉화군" data-chained="경상북도">봉화군</option><option value="상주시" data-chained="경상북도">상주시</option><option value="성주군" data-chained="경상북도">성주군</option><option value="안동시" data-chained="경상북도">안동시</option><option value="영덕군" data-chained="경상북도">영덕군</option><option value="영양군" data-chained="경상북도">영양군</option><option value="영주시" data-chained="경상북도">영주시</option><option value="영천시" data-chained="경상북도">영천시</option><option value="예천군" data-chained="경상북도">예천군</option><option value="울릉군" data-chained="경상북도">울릉군</option><option value="울진군" data-chained="경상북도">울진군</option><option value="의성군" data-chained="경상북도">의성군</option><option value="청도군" data-chained="경상북도">청도군</option><option value="청송군" data-chained="경상북도">청송군</option><option value="칠곡군" data-chained="경상북도">칠곡군</option><option value="포항시 남구" data-chained="경상북도">포항시 남구</option><option value="포항시 북구" data-chained="경상북도">포항시 북구</option><option value="광산구" data-chained="광주광역시">광산구</option><option value="남구" data-chained="광주광역시">남구</option><option value="동구" data-chained="광주광역시">동구</option><option value="북구" data-chained="광주광역시">북구</option><option value="서구" data-chained="광주광역시">서구</option><option value="남구" data-chained="대구광역시">남구</option><option value="달서구" data-chained="대구광역시">달서구</option><option value="달성군" data-chained="대구광역시">달성군</option><option value="동구" data-chained="대구광역시">동구</option><option value="북구" data-chained="대구광역시">북구</option><option value="서구" data-chained="대구광역시">서구</option><option value="수성구" data-chained="대구광역시">수성구</option><option value="중구" data-chained="대구광역시">중구</option><option value="대덕구" data-chained="대전광역시">대덕구</option><option value="동구" data-chained="대전광역시">동구</option><option value="서구" data-chained="대전광역시">서구</option><option value="유성구" data-chained="대전광역시">유성구</option><option value="중구" data-chained="대전광역시">중구</option><option value="강서구" data-chained="부산광역시">강서구</option><option value="금정구" data-chained="부산광역시">금정구</option><option value="기장군" data-chained="부산광역시">기장군</option><option value="남구" data-chained="부산광역시">남구</option><option value="동구" data-chained="부산광역시">동구</option><option value="동래구" data-chained="부산광역시">동래구</option><option value="부산진구" data-chained="부산광역시">부산진구</option><option value="북구" data-chained="부산광역시">북구</option><option value="사상구" data-chained="부산광역시">사상구</option><option value="사하구" data-chained="부산광역시">사하구</option><option value="서구" data-chained="부산광역시">서구</option><option value="수영구" data-chained="부산광역시">수영구</option><option value="연제구" data-chained="부산광역시">연제구</option><option value="영도구" data-chained="부산광역시">영도구</option><option value="중구" data-chained="부산광역시">중구</option><option value="해운대구" data-chained="부산광역시">해운대구</option><option value="강남구" data-chained="서울특별시">강남구</option><option value="강동구" data-chained="서울특별시">강동구</option><option value="강북구" data-chained="서울특별시">강북구</option><option value="강서구" data-chained="서울특별시">강서구</option><option value="관악구" data-chained="서울특별시">관악구</option><option value="광진구" data-chained="서울특별시">광진구</option><option value="구로구" data-chained="서울특별시">구로구</option><option value="금천구" data-chained="서울특별시">금천구</option><option value="노원구" data-chained="서울특별시">노원구</option><option value="도봉구" data-chained="서울특별시">도봉구</option><option value="동대문구" data-chained="서울특별시">동대문구</option><option value="동작구" data-chained="서울특별시">동작구</option><option value="마포구" data-chained="서울특별시">마포구</option><option value="서대문구" data-chained="서울특별시">서대문구</option><option value="서초구" data-chained="서울특별시">서초구</option><option value="성동구" data-chained="서울특별시">성동구</option><option value="성북구" data-chained="서울특별시">성북구</option><option value="송파구" data-chained="서울특별시">송파구</option><option value="양천구" data-chained="서울특별시">양천구</option><option value="영등포구" data-chained="서울특별시">영등포구</option><option value="용산구" data-chained="서울특별시">용산구</option><option value="은평구" data-chained="서울특별시">은평구</option><option value="종로구" data-chained="서울특별시">종로구</option><option value="중구" data-chained="서울특별시">중구</option><option value="중랑구" data-chained="서울특별시">중랑구</option><option value="남구" data-chained="울산광역시">남구</option><option value="동구" data-chained="울산광역시">동구</option><option value="북구" data-chained="울산광역시">북구</option><option value="울주군" data-chained="울산광역시">울주군</option><option value="중구" data-chained="울산광역시">중구</option><option value="강화군" data-chained="인천광역시">강화군</option><option value="계양구" data-chained="인천광역시">계양구</option><option value="남동구" data-chained="인천광역시">남동구</option><option value="동구" data-chained="인천광역시">동구</option><option value="미추홀구" data-chained="인천광역시">미추홀구</option><option value="부평구" data-chained="인천광역시">부평구</option><option value="서구" data-chained="인천광역시">서구</option><option value="연수구" data-chained="인천광역시">연수구</option><option value="옹진군" data-chained="인천광역시">옹진군</option><option value="중구" data-chained="인천광역시">중구</option><option value="강진군" data-chained="전라남도">강진군</option><option value="고흥군" data-chained="전라남도">고흥군</option><option value="곡성군" data-chained="전라남도">곡성군</option><option value="광양시" data-chained="전라남도">광양시</option><option value="구례군" data-chained="전라남도">구례군</option><option value="나주시" data-chained="전라남도">나주시</option><option value="담양군" data-chained="전라남도">담양군</option><option value="목포시" data-chained="전라남도">목포시</option><option value="무안군" data-chained="전라남도">무안군</option><option value="보성군" data-chained="전라남도">보성군</option><option value="순천시" data-chained="전라남도">순천시</option><option value="신안군" data-chained="전라남도">신안군</option><option value="여수시" data-chained="전라남도">여수시</option><option value="영광군" data-chained="전라남도">영광군</option><option value="영암군" data-chained="전라남도">영암군</option><option value="완도군" data-chained="전라남도">완도군</option><option value="장성군" data-chained="전라남도">장성군</option><option value="장흥군" data-chained="전라남도">장흥군</option><option value="진도군" data-chained="전라남도">진도군</option><option value="함평군" data-chained="전라남도">함평군</option><option value="해남군" data-chained="전라남도">해남군</option><option value="화순군" data-chained="전라남도">화순군</option><option value="고창군" data-chained="전라북도">고창군</option><option value="군산시" data-chained="전라북도">군산시</option><option value="김제시" data-chained="전라북도">김제시</option><option value="남원시" data-chained="전라북도">남원시</option><option value="무주군" data-chained="전라북도">무주군</option><option value="부안군" data-chained="전라북도">부안군</option><option value="순창군" data-chained="전라북도">순창군</option><option value="완주군" data-chained="전라북도">완주군</option><option value="익산시" data-chained="전라북도">익산시</option><option value="임실군" data-chained="전라북도">임실군</option><option value="장수군" data-chained="전라북도">장수군</option><option value="전주시 덕진구" data-chained="전라북도">전주시 덕진구</option><option value="전주시 완산구" data-chained="전라북도">전주시 완산구</option><option value="정읍시" data-chained="전라북도">정읍시</option><option value="진안군" data-chained="전라북도">진안군</option><option value="서귀포시" data-chained="제주특별자치도">서귀포시</option><option value="제주시" data-chained="제주특별자치도">제주시</option><option value="계룡시" data-chained="충청남도">계룡시</option><option value="공주시" data-chained="충청남도">공주시</option><option value="금산군" data-chained="충청남도">금산군</option><option value="논산시" data-chained="충청남도">논산시</option><option value="당진시" data-chained="충청남도">당진시</option><option value="보령시" data-chained="충청남도">보령시</option><option value="부여군" data-chained="충청남도">부여군</option><option value="서산시" data-chained="충청남도">서산시</option><option value="서천군" data-chained="충청남도">서천군</option><option value="아산시" data-chained="충청남도">아산시</option><option value="예산군" data-chained="충청남도">예산군</option><option value="천안시 동남구" data-chained="충청남도">천안시 동남구</option><option value="천안시 서북구" data-chained="충청남도">천안시 서북구</option><option value="청양군" data-chained="충청남도">청양군</option><option value="태안군" data-chained="충청남도">태안군</option><option value="홍성군" data-chained="충청남도">홍성군</option><option value="괴산군" data-chained="충청북도">괴산군</option><option value="단양군" data-chained="충청북도">단양군</option><option value="보은군" data-chained="충청북도">보은군</option><option value="영동군" data-chained="충청북도">영동군</option><option value="옥천군" data-chained="충청북도">옥천군</option><option value="음성군" data-chained="충청북도">음성군</option><option value="제천시" data-chained="충청북도">제천시</option><option value="증평군" data-chained="충청북도">증평군</option><option value="진천군" data-chained="충청북도">진천군</option><option value="청주시 상당구" data-chained="충청북도">청주시 상당구</option><option value="청주시 서원구" data-chained="충청북도">청주시 서원구</option><option value="청주시 청원구" data-chained="충청북도">청주시 청원구</option><option value="청주시 흥덕구" data-chained="충청북도">청주시 흥덕구</option><option value="충주시" data-chained="충청북도">충주시</option>
									</select>					
							</div>
							</div>
							<div class="item">
		                        <h3>층수</h3>
	                        	<input type="hidden" name="srch_floor_cnt" id="srch_floor_cnt" value="${params.srch_floor_cnt }">
		                        <div class="mb60">
		                           <span  class="option floor <c:if test="${params.srch_floor_cnt == '' }">select</c:if>"  onclick="$('#srch_floor_cnt').val(''); $('.floor').removeClass('select'); $(this).addClass('select');" >전체</span>
		                           <span  class="option floor <c:if test="${params.srch_floor_cnt == '0' }">select</c:if>" onclick="$('#srch_floor_cnt').val('0'); $('.floor').removeClass('select'); $(this).addClass('select');" >지하</span>
		                           <span  class="option floor <c:if test="${params.srch_floor_cnt == '1' }">select</c:if>" onclick="$('#srch_floor_cnt').val('1'); $('.floor').removeClass('select'); $(this).addClass('select');" >1층</span>
		                           <span  class="option floor <c:if test="${params.srch_floor_cnt == '2' }">select</c:if>" onclick="$('#srch_floor_cnt').val('2'); $('.floor').removeClass('select'); $(this).addClass('select');" >2층이상</span>
								</div>
							</div>
							
							<div class="item">
		                        <h3>주차</h3>
	                        	<input type="hidden" name="srch_parking_cnt" id="srch_parking_cnt" value="${params.srch_parking_cnt }">
		                        <div class="mb60">
		                           <span  class="option parking <c:if test="${params.srch_parking_cnt == '' }">select</c:if>"  onclick="$('#srch_parking_cnt').val(''); $('.parking').removeClass('select'); $(this).addClass('select');" >필요없음</span>
		                           <span  class="option parking <c:if test="${params.srch_parking_cnt == '0' }">select</c:if>" onclick="$('#srch_parking_cnt').val('0'); $('.parking').removeClass('select'); $(this).addClass('select');" >1대</span>
		                           <span  class="option parking <c:if test="${params.srch_parking_cnt == '1' }">select</c:if>" onclick="$('#srch_parking_cnt').val('1'); $('.parking').removeClass('select'); $(this).addClass('select');" >2대</span>
		                           <span  class="option parking <c:if test="${params.srch_parking_cnt == '2' }">select</c:if>" onclick="$('#srch_parking_cnt').val('2'); $('.parking').removeClass('select'); $(this).addClass('select');" >3대이상</span>
								</div>
							</div>
						</div>
						<div class="right-area">
							<div class="item">
								<h3>보증금<span id="range_srch_deposit">0 ~ 2.5억원</span></h3>
								<div class="slider-wrapper range-area">
									<input class="input-range "  id="srch_deposit" name="srch_deposit" type="text" data-slider-step="1000" data-slider-value="0, 25000" data-slider-min="0" data-slider-max="50000" data-slider-range="true" data-slider-tooltip_split="true" />
									<div class="range-line"></div>
									<div class="range-num"><span class="fl">0</span><span class="fr">5억</span></div>
									<input type="hidden" name="srch_min_deposit" id="srch_deposit_stt" value="<c:out value="${list.srch_min_deposit}"/>"/>
									<input type="hidden" name="srch_max_deposit" id="srch_deposit_end" value="<c:out value="${list.srch_max_deposit}"/>"/>
								</div>
							</div>
							<div class="item mt22">
								<h3>월세<span id="range_srch_month_price" >0 ~ 1000만원</span></h3>
								<div class="slider-wrapper range-area">
									<input class="input-range " id="srch_month_price" name="srch_month_price"  type="text" data-slider-step="25" data-slider-value="0, 1000" data-slider-min="0" data-slider-max="2000" data-slider-range="true" data-slider-tooltip_split="true" />
									<div class="range-line"></div>
									<div class="range-num"><span class="fl">0</span><span class="fr">2천만</span></div>
									<input type="hidden" name="srch_min_price" id="srch_month_price_stt" value="<c:out value="${list.srch_min_price}"/>"/>
									<input type="hidden" name="srch_max_price" id="srch_month_price_end" value="<c:out value="${list.srch_max_price}"/>"/>
								</div>
							</div>
							<div class="item mt22">
								<h3>면적<span id="range_srch_common_area">0 ~ 250PY</span></h3>
								<div class="slider-wrapper range-area">
									<input class="input-range " id="srch_common_area" name="srch_common_area" type="text" data-slider-step="5" data-slider-value="0, 250" data-slider-min="0" data-slider-max="500" data-slider-range="true" data-slider-tooltip_split="true" />
									<div class="range-line"></div>
									<div class="range-num"><span class="fl">0</span><span class="fr">500PY</span></div>
									<input type="hidden" name="srch_min_area" id="srch_common_area_stt" value="<c:out value="${list.srch_min_area}"/>"/>
									<input type="hidden" name="srch_max_area" id="srch_common_area_end" value="<c:out value="${list.srch_max_area}"/>"/>
								</div>
							</div>
						</div>
					</div>
					<div class="search-btn" onclick="javascript:search();">해당조건으로 검색</div>
				</div>
				</form>
			</div>
		</section>
		<!-- //section 01 -->
		<!-- section 02 -->
		<section class="for-sale-list">
			<h2>오늘의 매물</h2>
			<div class="list-wrap">
				<ul class="lists">
					<c:forEach var="list" items="${ result }" varStatus="stauts">
					<li class="js-load"><!-- 01 -->
						<a href="#" onclick="openProdDetail(${list.prod_seq});">
							<div class="pic"><img alt="" width="312" height="198" src="data:image/jpg;base64,<c:out value='${ list.attachFileDtoList[0].att_file_display}'/>" alt="매물이미지" /></div><!--312*198-->
							
							<div class="info-box">
								<div class="title"><c:out value="${list.prod_title}"/></div>
								<div class="address"><c:out value="${list.address1 != null ? list.address1 : list.address2}"/>&nbsp;</div>
								<c:choose>
									<c:when test="${ list.prod_type == '01'}"><div class="price">분양가  <c:out value="${list.sale_price}"/>만원</div>	</c:when>
									<c:when test="${ list.prod_type == '02'}"><div class="price">매매가  <c:out value="${list.sale_price}"/>만원</div>	</c:when>
									<c:when test="${ list.prod_type == '03'}"><div class="price">전세금  <c:out value="${list.deposit}"/>만원</div>	</c:when>
									<c:otherwise>
										<div class="price"><c:out value="${list.month_price}"/>만원<span class="month">/월</span></div>									
									</c:otherwise>
								</c:choose>	
							</div>
							<ul class="option">
								<li class="fy"><img src="../../../service/img/icon_opt_py.png" alt="평수"/>${list.total_floor_cnt}평</li>
								<li class="floor"><img src="../../../service/img/icon_opt_floor.png" alt="층수"/>${list.floor_cnt}층</li>
								<li class="car"><img src="../../../service/img/icon_opt_car.png" alt="주차대수"/>${list.parking_cnt}대</li>
							</ul>
						</a>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div class="more-btn"><a>더보기</a></div>
		</section>
		<!-- //section 02 -->
		<!-- section 03 -->
	    <section class="cont-area01-wrap">
			<p class="mb30"><img src="../../../service/img/cont01.png" alt="빠르고 쉽고 안전하다! 필요한 사람들끼리 모여 원하는 조건으로 계약"/></p>
			<p><img src="../../../service/img/cont02.png" alt="RENTFREE는 임대인에게는 상가/사무실 공실률 해소,신도시 공실상가 문제해결,쉬운공간 등록할수 있으며 임차인에게는 단기간 공간이 필요할때 보증금/권리금 부담NO,빠른공감탐색을 할수 있는 서비스입니다."/></p>
		</section>
		<!-- //section 03 -->
		<!-- section 04 -->
	    <section class="cont-area02-wrap">
			<p class="mb70"><img src="../../../service/img/cont03.png" alt="사업은 어떻게 될지 모르는데 부동산에서는 2년 이상의 장기 계약만을 강요합니다.렌트프리는 이런분들을 위해 존재합니다"/></p>
			<p><img src="../../../service/img/cont04.png" alt="처음 사업을 시작하는 예비창업자,일시적으로 조직을 확대한 중견기업,단기 물류 창고가 필요한 사업주,2년, 3년 계약이 부담스러우신 분"/></p>
		</section>
		<!-- //section 04 -->
		<!-- section 05 -->
	    <section class="cont-area03-wrap">
			<p><img src="../../../service/img/cont05.jpg" alt="이제는 렌트프리에서 보증금과 권리금 부담이 없는 당신만의 공간을 찾아 보세요."/></p>
		</section>
		<!-- //section 06 -->
		<!-- section 06 -->
	    <section class="request-wrap">
			<h2>의뢰 하기</h2>
			<p><span>010-4658-5557</span>로 전화 주시면 즉시 응대해 드립니다.<br>성명과 연락처를 남겨주시면 요청하신 조건을 피드백을 드립니다.</p>
			<form name="requestFrm" id="requestFrm" action="<c:url value='/service/request'/>" method="post" >
				<div class="form-box">
					<div class="input-box">
						<div class="name box">
							<span>성명</span>
							<span class="line"></span>
							<input type="text" placeholder="입력하세요" name="req_name" id="req_name">
						</div>
						<div class="phone box">
							<span>연락처</span>
							<span class="line"></span>
							<input type="text" placeholder="숫자만 입력하세요" name="req_tel" id="req_tel">
						</div>
					</div>
				    <div class="detail box">
						<span>요청사항</span>
						<textarea placeholder="예산, 희망지역, 옵션 등 요구사항을 남겨주세요" name="req_content" id="req_content"></textarea>
					</div>
					<div class="check-box">
						<input type="checkbox" id="agree" name="전체동의" />
						<label for="agree"><span>개인정보 수집 및 이용에 동의</span></label>
					</div>
				</div>
			</form>
			<div class="request-btn"><a onclick="requestCreate();">문의 남기기</a></div>
		</section>
		<!-- //section 05 -->
		
	</main>
	<!-- footer -->
	<footer id="footer">
		<div class="footer-wrap">
			<ul class="site-info">
				<li>상호 : 렌트프리</li>
				<li>대표자 : 이재민</li>
				<li>사업자번호 : 272-51-00344</li>
				<li>주소 : 서울특별시 서초구 서초동 1308-4 실버오피스텔 607호</li>
				<li>연락처 : 010-4658-5557</li>
			</ul>
			<div class="agree"><a href="#" onclick="layer_open('privacy-layer');return false;">개인정보수집 및 이용약관보기</a></div>
		</div>
	</footer>
	<!-- //footer -->
</div>

	
<!-- Layer -->
<div class="layer">
	<div class="bg"></div>
	<!-- #개인정보 -->
	<div id="privacy-layer" class="pop-layer" style="display:none;">
		<div class="text-center">
			<div class="pop-header"><h1>개인정보수집 및 이용약관</h1><a href="#" class="pop-btn">닫기</a></div>
			<div class="pop_cont">
				<!--content //-->
                <div class="ctxt">
					<p>[개인정보수집 및 이용약관]</p>
					<p>렌트프리는 정보통신망 이용촉진 및 정보보호 등에 관한 법률을 준수하며, 고객님의 개인정보를 수집하고 
					소중하게 다루고 있습니다.</p><br>

					<p>1. 개인정보 수집 항목</p>
					<p> - 이름, 전화번호</p><br>

					<p>2. 수집목적</p>
					<p>- 고객식별, 문의응대, 서비스 품질 향상</p><br>

					<p>3. 보유 및 이용기간</p>
					<p>수집목적이 달성되면 지체없이 모든 개인정보를 파기합니다. </p>
					<p>단, 관계법령에서 일정 기간 정보의 보관을 규정한 경우에 한해 분리 보관 후 파기합니다. </p>

                </div>
                <!--// content-->
			</div>
		</div>
	</div>
	<!-- /#개인정보 -->
	<!-- #매물정보 -->
	<!-- #매물정보 -->
	<div id="sale-info-layer" class="pop-layer" style="display:none;">
		<div class="text-center">
			<div class="pop-header">
				<span class="number"></span>
				<p class="location"></p>
				<p class="pop-title"></p>
				<a href="#" class="pop-btn">닫기</a>
			</div>
			<div class="pop_cont">
				<!--content //-->
				<div class="pop-price">
					<ul>
						<li><span class="title"></span><span class="price"></span></li>
						<li><span class="title"></span><span class="price lineh-12"><br><em></em></span></li>
						<li><span class="title"></span><span class="price lineh-12"><br><em></em></span></li>
					</ul>
				</div>
				<!--gallery -->
				<div class="gallery">
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<!-- Wrapper for slides -->
						<div class="carousel-inner big-pic">
						</div><!-- End Carousel Inner -->
						<ul class="nav nav-pills nav-justified small-pic">
						</ul>
					</div><!-- End Carousel -->
				</div>
				<!--gallery -->

				<div class="pop-item-info">
					<div class="left-info">
						<ul class="option">
						</ul>
						<div class="tag">
						</div>
					</div>
					<div class="right-info">
						<div class="text-box">
						</div>
					</div>
				</div>
                <!--// content-->
			</div>
		</div>
	</div>
	<!-- /#매물정보 -->
</div>
<!-- /Layer -->

<script src="<c:url value='/service/js/jquery-1.12.2.min.js'/>"></script>
<script src="<c:url value='/service/js/jquery.chained.min.js'/>"></script>
<script src="<c:url value='/service/js/design.js'/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.0.0/bootstrap-slider.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		/* 동적 HTML 로 생성하면 발생하는 버그는 추후 수정 예정 
		getSidoData();
		*/
		$("#srch_address_second").chained("#srch_address_first");
		
		load('.list-wrap', '6');
		$(".more-btn").on("click", function () {
	        load('.list-wrap', '6');
	    })
	});

	function getSidoData(){
  		var jsonLocation = 'data/sido_data.json';
		var optionString = '';
		var sido = '';
 		$.getJSON(jsonLocation, function(data){
			$.each(data, function(i, item){
				$.each(item.srch_address, function(j, item){
					//$('#srch_address_first')
					//console.log(item.sido);
					sido = item.sido;
					optionString = '<option value="'+sido+'">'+sido+'</option>';
					//<option value="강원도">강원도</option>
					$('#srch_address_first').append(optionString);
					$.each(item.sigun, function(j, secondItem){
							//$('#srch_address_first')	
							//console.log(item2);
							optionString = '<option value="'+secondItem+'" data-chained="'+sido+'">'+secondItem+'</option>';
							//<option value="강릉시" data-chained="강원도">강릉시</option>
							$('#srch_address_second').append(optionString);
						
					});
				});
			});
		});
	}

	function search(){
		console.log($('#srch_deposit').data('slider').getValue());
		$('#srch_deposit_stt').val($('#srch_deposit').data('slider').getValue()[0]);
		$('#srch_deposit_end').val($('#srch_deposit').data('slider').getValue()[1]);
		
		$('#srch_month_price_stt').val($('#srch_month_price').data('slider').getValue()[0]);
		$('#srch_month_price_end').val($('#srch_month_price').data('slider').getValue()[1]);
		
		$('#srch_common_area_stt').val($('#srch_common_area').data('slider').getValue()[0]);
		$('#srch_common_area_end').val($('#srch_common_area').data('slider').getValue()[1]);
		$('#frm').submit();
	}

	function load(id, cnt) {
	    var list = id + " .js-load:not(.active)";
	    var length = $(list).length;
	    var total_cnt;
	    if (cnt < length) {
	        total_cnt = cnt;
	    } else {
	        total_cnt = length;
	        $('.more-btn').hide()
	    }
	    $(list + ":lt(" + total_cnt + ")").addClass("active");
	}

	function openProdDetail(prod_seq){
		$.ajax({ 
			type: "GET", 
			url : "<c:url value='/serivce/ajaxDetail'/>", 
			data: {'prod_seq' : prod_seq}, 
			async: true, 
			success : function(data, status, xhr) { 
				$('#sale-info-layer').html(data);
			}, 
			error: function(jqXHR, textStatus, errorThrown) { 
				alert(jqXHR.responseText); } 
			});
		layer_open('sale-info-layer');return false;
	}

	function requestCreate(){
		if(!$('#agree').is(":checked")){
			alert('개인정보 수집 및 이용에 동의해 주세요');
			return;
		}else{
			if($('#req_name').val() == '' || $('#req_tel').val() == '' || $('#req_content').val() == ''){
				alert('성명과 연락처, 요청사항을 반드시 입력해 주세요');
				return;
			}
		}
		alert('문의가 접수되었습니다. 담당자를 통해 연락드리도록 하겠습니다.');
		$('#requestFrm').submit();
	}

	function closePop(){
		$('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다. 
		$('#sale-info-layer').hide();
	}
		
</script>

</body>
</html>