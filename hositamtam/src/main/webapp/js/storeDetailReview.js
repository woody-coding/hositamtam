let xhr = new XMLHttpRequest();
let currentSno;
let currentId;
let msg = document.querySelector('#msg');



/*function needLogin() {
  Swal.fire({
    title: "로그인이 필요하신 서비스입니다.",
    text: "제보 후 다시 되돌릴 수 없습니다. 신중하세요.",
    icon: "warning",
    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
    confirmButtonColor: "#3085D6", // confrim 버튼 색깔 지정
    cancelButtonColor: "#d33", // cancel 버튼 색깔 지정
    confirmButtonText: "진행", // confirm 버튼 텍스트 지정
    cancelButtonText: "취소", // cancel 버튼 텍스트 지정
    reverseButtons: true, // 버튼 순서 거꾸로
  }).then((result) => {
    // 만약 Promise리턴을 받으면,
    if (result.isConfirmed) {
      // 만약 모달창에서 confirm 버튼을 눌렀다면
      Swal.fire("감사합니다. 정상적으로 제보가 접수되었습니다!", "success");
    }
  });
}*/









function slikecountHandler() {
		if (xhr.readyState === 4 && xhr.status === 200) {
		
		const storeLike = JSON.parse(xhr.responseText);
		let storeLikeCount = storeLike[0].sfavoritecount;

		document.querySelector('#storeLikeCount').innerHTML = storeLikeCount;
    }
}






function slikecountStatusHandler() {
		if (xhr.readyState === 4 && xhr.status === 200) {
		
		const storeLike = JSON.parse(xhr.responseText);
		let storeLikeCount = storeLike[0].sfavoritecount;

		document.querySelector('#storeLikeCount').innerHTML = storeLikeCount;
    }
}



function reviewButton(event) {
	let reviewInput = document.querySelector('.review__input').value;
	
	if(reviewInput.length < 5 || reviewInput.length > 500) {
		msg.style.color = 'red';
		msg.innerHTML = '리뷰는 5글자 이상, 500글자 이하여야 합니다.';
		event.preventDefault();
	}
}



function reviewInput() {
	let reviewInput = document.querySelector('.review__input').value;
	
	if(reviewInput.length >= 5 && reviewInput.length <= 500) {
		msg.style.color = 'green';
		msg.innerHTML = '등록 가능한 리뷰입니다.';
	}
	else {
		msg.style.color = 'red';
		msg.innerHTML = '리뷰는 5글자 이상, 500글자 이하여야 합니다.';
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

	
	
	
	// 비회원이든 회원이든 최초 페이지 로드되었을 때 최신 찜 수가 떠있어야함
    xhr.onreadystatechange = slikecountStatusHandler;

    let param = '?command=updateLikeStoreStatus&sno=' + currentSno;
    xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
    xhr.send();
	
	
	document.querySelector('.review__button').addEventListener('click', reviewButton);
	document.querySelector('.review__input').addEventListener('keyup', reviewInput);
	
	
	document.querySelector('.storeDetail__like').addEventListener('click', function() {
		
		
	    if (member) {
			currentId = member.id;
	        
	        xhr.onreadystatechange = slikecountHandler;
	
	        let param = '?command=updateLikeStore&sno=' + currentSno + '&id=' + currentId;
	        xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
	        xhr.send();
	    } else if(member === null) {
			alert('로그인이 필요한 서비스입니다.');
			window.location.href = '/finalProject/views/login';
/*        		if (needLogin()) {
			
					window.location.href = '/finalProject/views/login';
				}*/ 
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