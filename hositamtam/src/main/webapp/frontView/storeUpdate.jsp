<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>호시탐탐</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="/finalProject/images/favicon.ico" type="image/x-icon" />

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
    <link rel="stylesheet" href="/finalProject/css/loginHeader.css" />
    <link rel="stylesheet" href="/finalProject/css/footer.css" />
    <link rel="stylesheet" href="/finalProject/css/storeInsertAndUpdate.css" />

    <!-- JavaScript -->
    <script defer src="/finalProject/js/storeInsertAndUpdate.js"></script>
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
  
  


    <!-- Main -->
    <!-- Store Insert And Update -->
    <section id="store__update" class="section">
      <div class="max-container">
        <div class="store__update">
          <form id="store__update__form">
            <label for="store__name" class="store__label">
              <input
                class="store__input"
                type="text"
                id="store__name"
                maxlength="15"
                placeholder="점포의 이름을 지어주세요! (최대15자)"
              />
              <button class="change__location">위치 수정</button>
            </label>
            <div class="store__update__form">
              <div class="store__type">
                <h2 class="store__type__title siau__h2">점포 형태</h2>
                <button class="store__type__button1" type="button">좌판</button>
                <button class="store__type__button2" type="button">매장</button>
              </div>
              <div class="store__payment">
                <h2 class="store__payment__title siau__h2">결제 방식</h2>
                <button class="store__payment__button1" type="button">
                  현금
                </button>
                <button class="store__payment__button2" type="button">
                  카드
                </button>
                <button class="store__payment__button3" type="button">
                  계좌이체
                </button>
              </div>
              <div class="store__category">
                <h2 class="store__category__title siau__h2">취급 품목</h2>
                <label for="store__category__contents" class="category__label">
                  <input
                    class="category__input"
                    type="text"
                    id="store__category__contents"
                    placeholder="점포의 취급품목을 알려주세요 !"
                  />
                </label>
              </div>
              <div class="store__photo">
                <h2 class="store__photo__title siau__h2">점포 사진</h2>
                <label class="store__photo__label">
                  <input type="file" name="photo" class="store__photo__input" />
                </label>
              </div>
            </div>

            <button class="store__update__button" type="submit">
              등록하기
            </button>
          </form>
        </div>
      </div>
    </section>


	<%@ include file="footer.jsp" %>
  </body>
</html>
