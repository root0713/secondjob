<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="text-center">
	<div class="pop-header">
		<span class="number" id="prod_seq">NO. <c:out value="${result.prod_seq}"/></span>
		<p class="location" id="adress"><c:out value="${result.address1}"/></p>
		<p class="pop-title" id="prod_title"><c:out value="${result.prod_title}"/></p>
		<a href="#" class="pop-btn" onclick="closePop();">닫기</a>
	</div>
	<div class="pop_cont">
		<!--content //-->
		<div class="pop-price">
			<ul>
				<c:choose>
					<c:when test="${ result.prod_type == '01'}"><li><span class="title">분양가</span><span class="price" id="deposit"><c:out value="${result.sale_price}"/>만원</span></li> </c:when>
					<c:when test="${ result.prod_type == '02'}"><li><span class="title">매매가</span><span class="price" id="deposit"><c:out value="${result.sale_price}"/>만원</span></li> </c:when>
					<c:when test="${ result.prod_type == '03'}"><li><span class="title">전세금</span><span class="price" id="deposit"><c:out value="${result.deposit}"/>만원</span></li> </c:when>
					<c:otherwise>
						<li><span class="title">보증금</span><span class="price" id="deposit"><c:out value="${result.month_price}"/>만원</span></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ result.prod_type == '04'}">
						<li><span class="title">월세</span><span class="price lineh-12" id="month_price"><c:out value="${result.month_price}"/>만원<br><em>(관리비 <c:out value="${result.mntnc_includ_yn == 'Y' ? '포함':'불포함'}"/>)</em></span></li>
						<li><span class="title">관리비</span><span class="price lineh-12" id="mntnc_fee"><c:out value="${result.mntnc_fee}"/>만원<c:if test="${result.mntnc_content != '' }"><br><em>(<c:out value="${result.mntnc_content}"/>)</em></c:if></span></li>
					</c:when>
					<c:otherwise>
						<li><span class="title">월세</span><span class="price lineh-12" id="month_price">-</span></li>
						<li><span class="title">관리비</span><span class="price lineh-12" id="mntnc_fee">-</span></li>							
					</c:otherwise>
				</c:choose>				
			</ul>
		</div>
		<!--gallery -->
		<div class="gallery">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Wrapper for slides -->
				<div class="carousel-inner big-pic">
					<c:forEach var="list" items="${ result.attachFileDtoList }" varStatus="stauts">
						<div class="item <c:if test="${stauts.index ==0 }">active</c:if>"><img alt="" width="898" height="414" src="data:image/jpg;base64,<c:out value='${ list.att_file_display}'/>" /><!-- 898*414 --> </div><!-- End Item -->
					</c:forEach>
				</div><!-- End Carousel Inner -->
				<ul class="nav nav-pills nav-justified small-pic">
					<c:forEach var="list" items="${ result.attachFileDtoList }" varStatus="stauts">
						<li data-target="#myCarousel" data-slide-to="${ stauts.index}" <c:if test="${stauts.index ==0 }">class="active"</c:if>><a href="#"><img alt="" width="78" height="78" src="data:image/jpg;base64,<c:out value='${ list.att_file_display}'/>" /></a></li><!-- 78*78 -->
					</c:forEach>
				</ul>	
			</div><!-- End Carousel -->
		</div>
		<!--gallery -->

		<div class="pop-item-info">
			<div class="left-info">
				<ul class="option">
					<li class="fy"><img src="../../../service/img/icon_opt_py.png" alt="평수" id="common_area"/><p><c:out value="${result.common_area}"/>평</p><span>(<c:out value="${result.m_common_area}"/>m<sub>2</sub>)</span></li>
					<li class="floor"><img src="../../../service/img/icon_opt_floor.png" alt="층수" id="floor"/><p><c:out value="${result.floor_cnt}"/>층</p><span>(총 <c:out value="${result.total_floor_cnt}"/>층)</span></li>
					<li class="car"><img src="../../../service/img/icon_opt_car.png" alt="주차대수" id="parking_cnt"/><p><c:out value="${result.parking_cnt}"/>대</p></li>
				</ul>
				<div class="tag" id="options">
                  	<%
                  		String[] optioTitles = {"엘레베이터", "독립화장실", "수도", "가스", "주차가능", "무료주차", "냉방기구", "난방기구"};
                  	%>
               		<c:set var="optionTitles" value="<%=optioTitles %>" scope="session"/>				
					<c:forEach begin="0" end="${fn:length(result.options)}" varStatus="stauts">
						<c:if test="${fn:substring(result.options,stauts.index,stauts.index+1) eq 1 }">
							<span>${optionTitles[stauts.index]}</span>
						</c:if>
					</c:forEach>				
				</div>
			</div>
			<div class="right-info">
				<div class="text-box">
					<c:out value="${result.prod_content}"/>
				</div>
			</div>
		</div>
              <!--// content-->
	</div>
</div>
