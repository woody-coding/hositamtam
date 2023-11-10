let xhr = new XMLHttpRequest();
let currentSno;
let currentId;





function slikecountHandler() {
		if (xhr.readyState === 4 && xhr.status === 200) {
		
		const storeLike = JSON.parse(xhr.responseText);
		let storeLikeCount = storeLike[0].sfavoritecount;
		
		
		if(storeLike[0].likeStatus === 'x') {
			//검은색 하트
			//document.querySelector('.storeDetail__like').style.color = 'black';
		} else if (storeLike[0].likeStatus === 'o') {
			//빨간색 하트
			//document.querySelector('.storeDetail__like').style.color = 'red';
		}


		document.querySelector('#storeLikeCount').innerHTML = storeLikeCount;
    }
}






function slikecountStatusHandler() {
		if (xhr.readyState === 4 && xhr.status === 200) {
		
		const storeLike = JSON.parse(xhr.responseText);
		let storeLikeCount = storeLike[0].sfavoritecount;
		
		if(storeLike[0].likeStatus === 'x') {
			//검은색 하트
			//document.querySelector('.storeDetail__like').style.color = 'black';
		} else if (storeLike[0].likeStatus === 'o') {
			//빨간색 하트
			//document.querySelector('.storeDetail__like').style.color = 'red';
		}
		

		document.querySelector('#storeLikeCount').innerHTML = storeLikeCount;
    }
}








function init() {
	

	// 찜 버튼
	// 현재 글의 고유 번호 sno 값 가져오기
	const storeNumber = window.localStorage.getItem('storeNumber');
	const storeNo = JSON.parse(storeNumber);
	currentSno = storeNo.sno;
	
	
	
	// 로그인되어 있는지 아닌지 세션스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	
	if (member) {
	    currentId = member.id;
	
	    xhr.onreadystatechange = slikecountStatusHandler;
	
	    let param = '?command=updateLikeStoreStatus&sno=' + currentSno + '&id=' + currentId;
	    xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
	    xhr.send();
	}
	
	document.querySelector('.storeDetail__like').addEventListener('click', function() {
	    if (member) {
	        currentId = member.id;
	
	        xhr.onreadystatechange = slikecountHandler;
	
	        let param = '?command=updateLikeStore&sno=' + currentSno + '&id=' + currentId;
	        xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
	        xhr.send();
	    } else {
	        alert('로그인이 필요한 서비스 입니다.');
	        window.location.href = '/finalProject/views/login';
	    }
	});

	
	
	






}

// 별점 메기기
document.addEventListener("DOMContentLoaded", function () {
  var stars = document.querySelectorAll(".review__star i");

  stars.forEach(function (star, index) {
    star.addEventListener("click", function () {
      // 몇 번째 별이 클릭됐는지?
      var clickedIndex = index;

      // 클릭된 n번째 별과 그 이전 별 색 변경 !
      for (var i = 0; i <= clickedIndex; i++) {
        stars[i].style.color = "#FCD53F";
      }

      // 재클릭했을 때
      for (var i = clickedIndex + 1; i < stars.length; i++) {
        stars[i].style.color = "#e0e0e0";
      }
    });
  });
});




// 별점 누르면 리뷰 창 나오게
document.addEventListener("DOMContentLoaded", function () {
  var reviewStars = document.querySelectorAll(".review__star");
  var reviewForm = document.getElementById("review__form");

  reviewStars.forEach(function (star) {
    star.addEventListener("click", function () {
      reviewForm.style.display = "block";
    });
  });
});

window.addEventListener('load', init);