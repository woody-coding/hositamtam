// JavaScript 코드
var userId = "${userId}";
console.log(userId);

function maskUserId(userId) {
    if (userId.length <= 3) {
        return userId;  // userId의 길이가 3자리 이하이면 그대로 반환
        
    } else {
		
        return userId.substr(0, 2) + '*'.repeat(userId.length - 3);
    }
}

function editHandler(event) {
     
}

/*function deleteHandler(event){
	if(confirm('정말 탈퇴하시겠습니까? 신중하게 결정해주세요. (탈퇴후 기존 아이디로 재가입 불가)')){
		 document.getElementById('deleteForm').submit();
		
	}else{
		
		event.preventDefault();
	}
}*/

function deleteHandler(event){
	if(confirm('정말 탈퇴하시겠습니까? 신중하게 결정해주세요. (탈퇴후 기존 아이디로 재가입 불가)')){
		 document.getElementById('deleteForm').submit();
		
	}else{
		
		event.preventDefault();
	}
}



function init() {
/*    let mypageedit_button = document.querySelector('#mypageedit_button');
    mypageedit_button.addEventListener('click', editHandler);*/
    
    let delete_button = document.querySelector('#delete_button');
    delete_button.addEventListener('click', deleteHandler);
    
	// userId 변수를 maskUserId 함수에 전달하여 마스킹
/*    var maskedUserId = maskUserId(userId);
    // 마스킹된 userId를 특정 HTML 요소에 표시
    document.querySelector('maskedUserId').textContent = maskedUserId;*/
}



window.addEventListener('load', init);




// 더보기, 접기 버튼
document.addEventListener("DOMContentLoaded", function () {
  var storeMoreButton = document.querySelector(".myStore__more");
  var storeLessButton = document.querySelector(".myStore__less");
  var storeHidden = document.querySelectorAll(".myStore__tr:nth-child(n + 7)");
  var postMoreButton = document.querySelector(".myPost__more");
  var postLessButton = document.querySelector(".myPost__less");
  var postHidden = document.querySelectorAll(".myPost__tr:nth-child(n + 7)");
  var reviewMoreButton = document.querySelector(".myReview__more");
  var reviewLessButton = document.querySelector(".myReview__less");
  var reviewHidden = document.querySelectorAll(".myReview__tr:nth-child(n + 7)");
  var storeLikeMoreButton = document.querySelector(".myStoreLike__more");
  var storeLikeLessButton = document.querySelector(".myStoreLike__less");
  var storeLikeHidden = document.querySelectorAll(".myStoreLike__tr:nth-child(n + 7)");

  // 내가 등록한 점포 더보기
  storeMoreButton.addEventListener("click", function () {
    storeHidden.forEach(function (row) {
      row.style.display = "table-row";
    });

    storeMoreButton.style.display = "none";
    storeLessButton.style.display = "block";
  });

  // 내가 등록한 점포 접기
  storeLessButton.addEventListener("click", function () {
    storeHidden.forEach(function (row) {
      row.style.display = "none";
    });

    storeLessButton.style.display = "none";
    storeMoreButton.style.display = "block";
  });

  // 내가 작성한 글 더보기
  postMoreButton.addEventListener("click", function () {
    postHidden.forEach(function (row) {
      row.style.display = "table-row";
    });

    postMoreButton.style.display = "none";
    postLessButton.style.display = "block";
  });

  // 내가 작성한 글 접기
  postLessButton.addEventListener("click", function () {
    postHidden.forEach(function (row) {
      row.style.display = "none";
    });

    postLessButton.style.display = "none";
    postMoreButton.style.display = "block";
  });

  // 내가 작성한 리뷰 더보기
  reviewMoreButton.addEventListener("click", function () {
    reviewHidden.forEach(function (row) {
      row.style.display = "table-row";
    });

    reviewMoreButton.style.display = "none";
    reviewLessButton.style.display = "block";
  });

  // 내가 작성한 리뷰 접기
  reviewLessButton.addEventListener("click", function () {
    reviewHidden.forEach(function (row) {
      row.style.display = "none";
    });

    reviewLessButton.style.display = "none";
    reviewMoreButton.style.display = "block";
  });

  // 내가 찜한 점포 더보기
  storeLikeMoreButton.addEventListener("click", function () {
    storeLikeHidden.forEach(function (row) {
      row.style.display = "table-row";
    });

    storeLikeMoreButton.style.display = "none";
    storeLikeLessButton.style.display = "block";
  });

  // 내가 찜한 점포 접기
  storeLikeLessButton.addEventListener("click", function () {
    storeLikeHidden.forEach(function (row) {
      row.style.display = "none";
    });

    storeLikeLessButton.style.display = "none";
    storeLikeMoreButton.style.display = "block";
  });
});


