<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    ​<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
    <!-- Favicon -->
     <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

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
    <link rel="stylesheet" href="../css/postMain.css" />

    <title>post</title>
    <script defer src="../js/postMain.js"></script>
    <script>
    
	    // 검색어 입력 필드에서 Enter 키를 눌렀을 때 검색 실행
	    document.getElementById("searchInput").addEventListener("keyup", function (event) {
	        if (event.key === "Enter") { // Enter 키가 눌렸을 때
	        
	            const searchInput = document.querySelector("#searchInput").value;
	            const encodedSearchInput = encodeURIComponent(searchInput);
	            const newURL = "market.jsp?command=search&query=" + encodedSearchInput;
	            window.location.href = newURL;
	        }
	  	  });
		}

		window.addEventListener('load', init);
    
    </script>
</head>

<body>
   <%@ include file="navi.jsp" %>
      
  <div  class="section" id="section1">
        
      <div class="black-bg">
        <div class="white-bg container mt-5">


          <form action="">
            <div class="postCategory row">
                <h4>카테고리</h4>
              
              <div class="left">
                <label><input type="checkbox" name="" value="">시장질문</label>
                <label><input type="checkbox" name="" value="">사건사고</label>
                <label><input type="checkbox" name="" value="">일상</label>
                <label><input type="checkbox" name="" value="">실종/분실</label>

              </div>
          
            </div>
            <div class="postHead row"><input type="text" placeholder="시끌시끌 제목을 입력하세요."></div>
            <div class="postPicture row">
              <h4>사진</h4>
              <div class="postPictureArea"></div>
            <button class="btn btn-secondary" id="submit">파일선택</button>

            </div>
            <div class="submitBox">
              <button class="btn btn-primary" id="submit">등록하기</button>
            <button class="btn btn-secondary" id="close">닫기</button>
             
            </div>
          </form>
          
        </div>
      </div>  
      <div class="black-bg-modify">
        <div class="white-bg container mt-5">
          <form action="">
            <div class="postCategory row">
                <h4>카테고리</h4>
              
              <div class="left">
                <label><input type="checkbox" name="" value="">시장질문</label>
                <label><input type="checkbox" name="" value="">사건사고</label>
                <label><input type="checkbox" name="" value="">일상</label>
                <label><input type="checkbox" name="" value="">실종/분실</label>

              </div>
          
            </div>
            <div class="postHead row"><input type="text" placeholder="시끌시끌 제목을 입력하세요."></div>
            <div class="postPicture row">
              <h4>사진</h4>
              <div class="postPictureArea"></div>
            <button class="btn btn-secondary" id="submit">파일선택</button>

            </div>
            <div class="submitBox">
              <button class="btn btn-primary" id="submit">수정하기</button>
            <button class="btn btn-secondary" id="close">닫기</button>
             
            </div>
          </form>
          
        </div>
      </div>    
      <div class="container mt-5">
      <span id="mkName">시장번호 ${post.ptitle}의 페이지 입니다</span>
      <button id="whatMarket">시장 선택하기</button>
	<hr/>
	<div id="markets"></div>
	<hr/>
        <%-- <div id="mkNameBox">
             
            <button id="btnDown"><i class="bi bi-chevron-down"></i></button>
            <button id="btnUp"><i class="bi bi-chevron-up"></i></button>
         
            <div class="mkNameList">
              <p>ㄱ<span>가락시장</span><span>가락시장</span><span>가락시장</span></p>
              <p>ㄴ<span>나락시장</span><span>나락시장</span><span>나락시장</span></p>
              <p>ㄷ<span></span></p>
              <p>ㄹ<span></span></p>
              <p>ㅁ<span></span></p>
              <p>ㅂ<span></span></p>
              <p>ㅅ<span></span></p>
              <p>ㅇ<span></span></p>
              <p>ㅈ<span></span></p>
              <p>ㅊ<span></span></p>
              <p>ㅋ<span></span></p>
              <p>ㅌ<span></span></p>
              <p>ㅍ<span></span></p>
              <p>ㅎ<span></span></p>

            </div>
              <div id="btnPost">
                <button id="writePost"><i class="bi bi-pencil-square"></i>글쓰기</button>

            </div>
            
        </div> --%>
        <!-- <ul class="list"> -->
    
         <!--  <li class="tab-button" data-id="5" style="display: none;"></li> -->
          
        <!-- </ul> -->
                 <form  method="GET" action="/finalProject/views/postCatrgoryList">
	<label for="pCategory"></label>
	<button id="pCategory" name="pCategory" value="hot">인기글</button>
	<button id="pCategory" name="pCategory" value="que">시장질문</button>
	<button id="pCategory" name="pCategory" value="acc">사건사고</button>
	<button id="pCategory" name="pCategory" value="day">일상</button>
	<button id="pCategory" name="pCategory" value="lost">실종/분실</button>
	</form>
   <table id="postlistTable">
		<tr>
			<th>번호:</th>
			<th>제목:</th>
			<th>내용:</th>
			<th>작성일자:</th>
			<th>사진:</th>
			<th>좋아요 수:</th>
			<th>작성자:</th>
			<th>댓글 수:</th>
			<th>카테고리:</th>
		</tr>
	<c:forEach items="${postList}" var="post" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${post.ptitle}</td>
			<td>${post.pcontent}</td>
			<td>${post.pregdate}</td>
			<td>${post.pphoto}</td>
			<td>${post.plikecount}</td>
			<td>${post.nickname}</td>
			<td>${post.countcomments}</td>
			<td>${post.pcategory}</td>
		</tr>
	</c:forEach>
        <!-- <div class="tab-content">
            <div id="tab-contentBox">
                <p id="tabCategory">인기글</p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
            <div id="tab-contentBox">
                <p id="tabCategory">인기글</p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
            <div id="tab-contentBox">
                <p id="tabCategory">인기글 </p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
        </div>
        <div class="tab-content ">
            <div id="tab-contentBox">
                <p id="tabCategory">시장질문 </p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
            <div id="tab-contentBox">
                <p id="tabCategory">시장질문 </p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
            <div id="tab-contentBox">
                <p id="tabCategory">시장질문 </p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
        </div>
        <div class="tab-content">
          <div id="tab-contentBox">
            <p id="tabCategory">사건사고 </p>
            <p>꽃순이네 앞 사고</p>
            <p>오토바이 전복</p>
        </div>
        <div id="tab-contentBox">
            <p id="tabCategory">사건사고 </p>
            <p>꽃순이네 어디있나요?</p>
            <p>아는사람 댓글로 알려주세요</p>
        </div>
        <div id="tab-contentBox">
            <p id="tabCategory">사건사고 </p>
            <p>꽃순이네 어디있나요?</p>
            <p>아는사람 댓글로 알려주세요</p>
        </div>
        </div>
        <div class="tab-content ">
            <div id="tab-contentBox">
                <p id="tabCategory">일상 </p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
            <div id="tab-contentBox">
                <p id="tabCategory">일상 </p>
                <p>꽃순이네 어디있나요?</p>
                <p>아는사람 댓글로 알려주세요</p>
            </div>
            <div id="tab-contentBox" class="row">
              
                <div id="textBox" class="col-8">
                  <p id="tabCategory">일상 </p>
                  <button class="moreContents">더보기</button>
                  <p>꽃순이네 어디있나요?</p>
                  <p>아는사람 댓글로 알려주세요! <br>너무너무 가고 싶어요 ㅜ  <br>1등으로 알려주시는분 칭찬해드릴게요!</p>
                  
              </div>    
            <div id="imgBox" class="col-4">
              <img src="../images/부산레디.PNG" style="width: 360px;">
              <div id="likeComment">
                <i class="bi bi-hand-thumbs-up">1</i>
                <i class="bi bi-chat-left-dots">2</i>   
              </div> 
            </div>
              
                
                <button type="submit" id="postModify">수정</button>
                
              <div class="commentBox row">
                  <div class="row">
                      <form action="">
                        <p>댓글</p>
                        <div class="row">
                          <div  class="col-8">
                            <div  id="comment">
                                <input type="text" id="commentInput" placeholder="댓글을 입력하세요">
                            </div>                    
                        </div>
                        <div class="col-4">
                            <button type="submit" id="submit" >작성</button>    
                      </div>
                        </div>
                        
                      </form>
      
                  </div>
                  
              </div>  
            </div>
          
            <div class="commentList row">

                <div id="commentListP" class="col-8">
                    <p>쩝쩝박사 <span id="commentDate">(2023-10-17 11:12:30)</span></p>
                    <p>1번가 앞에 있어요 </p>
                    
                </div>
                <div class="col-4">
                  <form action="">
                    <button type="submit" id="submit">수정</button>
                    <button type="submit" id="delete">삭제</button>
                  </form>
                    
                </div>
                
            </div>
        </div>
        <div class="tab-content">
            <div id="tab-contentBox">
                <p id="tabCategory">실종/분실 </p>
                <p>지갑분실</p>
                <p>사례하겠습니다.</p>
            </div>
          </div>
          <div class="tab-content show">
            <div class="firstShow">
            </div>
        </div>
            
        </div>
  
    </div> -->
  
 <!-- Footer -->
  

	</div>
<%--  <%@ include file="footer.jsp" %> --%>
  



	<%@ include file="footer.jsp" %>

</body>

</html>