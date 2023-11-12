<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
System.out.println("JOKE storeInsertAndUpdate.jsp");
String slat = request.getParameter("slat");
String slng = request.getParameter("slng");
String mno = request.getParameter("mno");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>호시탐탐</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />

    <!-- G-Market Fonts -->
    <link
      href="https://webfontworld.github.io/gmarket/GmarketSans.css"
      rel="stylesheet"
    />

    <!-- Font Awesome -->
    <script
      src="https://kit.fontawesome.com/89490613c7.js"
      crossorigin="anonymous"
    ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="../css/loginHeader.css" />
    <link rel="stylesheet" href="../css/footer.css" />
    <link rel="stylesheet" href="../css/storeInsertAndUpdate.css" />

    <!-- JavaScript -->
    <script defer src="../js/storeInsertAndUpdate.js"></script>
  </head>
  <body>
    <!-- Header -->
	<%@ include file="navi.jsp" %>
    <!-- Main -->
    <!-- Store Insert And Update -->
  	<section id="store__update" class="section">

		<div class="max-container">
			<div class="storeDetail__info">
				<c:choose>
					<c:when test="${update eq true}">
						<!-- 수정화면 -->
						<form id="store__update__form" action="/finalProject/storeUpdate" method="POST">
							<input type="hidden" name="sno" value="${store.sno}"/>
							<label for="store__name" class="store__label"> 
							<input
								class="store__input" type="text" id="store__name" name="sname" value="${store.sname}"
								placeholder="점포의 이름을 지어주세요 !" />
								<button class="change__location">위치 수정</button>
							</label>
							<div class="storeDetail__storeType">
							    <span class="storeDetail__storeType__title">
							        <h4 class="storeDetail__h4">점포 형태</h4>
							    </span>
							    <input type="radio" name="stype" value="좌판" <c:if test="${store.stype eq '좌판'}">checked="checked"</c:if>><span>좌판</span>
							    <input type="radio" name="stype" value="매장" <c:if test="${store.stype eq '매장'}">checked="checked"</c:if>><span>매장</span>
							</div>
							<div class="storeDetail__payment">
					        <span class="storeDetail__payment__title">
					        <h4 class="storeDetail__h4">결제 방식</h4></span>
					        
					        <!-- 모든 결제 방식 리스트에서 매장 결제방식에 속한 리스트 비교하여 체크된 상태로 만들기 -->
					        <!-- 
					        <c:forEach var="payment" items="${paymentList}">
					        	<c:forEach var="storePayment" items="${storePaymentList}">
					        		<c:choose>
							        	<c:when test="${payment.payno eq storePayment.payno}">
							        		<input type="checkbox" name="paytype" value="${payment.payno}" checked="checked">${payment.paytype}
							        	</c:when>
						        		<c:otherwise>
								        	<input type="checkbox" name="paytype" value="${payment.payno}">${payment.paytype}
						        		</c:otherwise>
					        		</c:choose>
					        	</c:forEach>
					        </c:forEach>
						-->
						
						<!-- 모든 결제 방식 리스트 -->
						<c:forEach var="payment" items="${paymentList}">
					       <input type="checkbox" name="paytype" value="${payment.payno}">${payment.paytype}
				        </c:forEach>
					        
					        
					      </div>
					      <div class="storeDetail__category">
					        <span class="storeDetail__category__title">
					        <h4 class="storeDetail__h4">취급 품목</h4></span>
					        <input class="category__input" type="text"
										id="store__category__contents" name="scategory"
										placeholder="점포의 취급품목을 알려주세요 !" value="${store.scategory}" />
					      </div>
					      <div class="storeDetail__photo">
					        <!-- 등록된 점포 사진 -->
					        <img src="../images/${store.sphoto}" style="width:300px; height: 150px;"/>
					      </div>

							<input type="submit" class="store__update__button" value="수정하기">
						</form>
					</c:when>
					<c:otherwise>
						<!-- 등록화면 -->
						<form id="store__update__form" action="/finalProject/storeInsert"
							method="POST">
							<input type="hidden" name="mno" value="<%=mno%>" /> <input
								type="hidden" name="slat" value="<%=slat%>" /> <input
								type="hidden" name="slng" value="<%=slng%>" /> <label
								for="store__name" class="store__label"> <input
								class="store__input" type="text" id="store__name" name="sname"
								placeholder="점포의 이름을 지어주세요 !" />
								<button class="change__location">위치 수정</button>
							</label>
							<div class="store__update__form">
								<div class="store__type">
									<h2 class="store__type__title siau__h2">점포 형태</h2>
									<input type="radio" name="stype" value="좌판">좌판 <input
										type="radio" name="stype" value="매장">매장
								</div>
								<div class="store__payment">
									<h2 class="store__payment__title siau__h2">결제 방식</h2>
									<input type="checkbox" name="paytype" value="1">현금 <input
										type="checkbox" name="paytype" value="2">카드 <input
										type="checkbox" name="paytype" value="3">계좌이체
								</div>
								<div class="store__category">
									<h2 class="store__category__title siau__h2">취급 품목</h2>
									<label for="store__category__contents" class="category__label">
										<input class="category__input" type="text"
										id="store__category__contents" name="scategory"
										placeholder="점포의 취급품목을 알려주세요 !" />
									</label>
								</div>
								<div class="store__photo">
									<h2 class="store__photo__title siau__h2">점포 사진</h2>
									<label class="store__photo__label"> <input type="file"
										name="sphoto" class="store__photo__input" />
									</label>
								</div>
							</div>

							<input type="submit" class="store__update__button" value="등록하기">
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>

    <!-- Footer -->
<!--     <footer id="information" class="section"> -->
<!--       <div class="information__located"> -->
<!--         <div class="max-container"> -->
<!--           <h2 class="information__title">&copy; TMI - All rights reserved</h2> -->
<!--           <div class="information__contents"> -->
<!--             <p class="information__title"> -->
<!--               Creator <br />팀장: 안효철 &nbsp;&nbsp; 팀원: 김동영 <br /> -->
<!--               팀원: 김진성 &nbsp;&nbsp; 팀원: 남경인 <br /> -->
<!--               팀원: 석신성 &nbsp;&nbsp; 팀원: 주영진 -->
<!--             </p> -->
<!--             <p class="information__title"> -->
<!--               <br /> -->
<!--               <i class="fa-brands fa-github"></i> -->
<!--               https://github.com/wlstjd3398/TMI.git -->
<!--             </p> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </footer> -->
  </body>
</html>
