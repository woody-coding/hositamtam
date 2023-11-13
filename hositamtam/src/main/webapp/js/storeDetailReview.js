let xhr = new XMLHttpRequest();
let currentSno;
let currentId;
let msg = document.querySelector('#msg');
let currentMno;



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


/* 경인 별

// 선택된 별의 개수 업데이트
function updateStars(starCount) {
    // 별 개수를 히든 필드에 설정
    document.getElementById('rrating').value = starCount;

    // 별 디자인 업데이트
    for (var i = 1; i <= 5; i++) {
        var starElement = document.querySelector('.review__star' + i);
        if (i <= starCount) {
            starElement.classList.add('selected');
        } else {
            starElement.classList.remove('selected');
        }
    }
}
*/








function storeModify() {
	// 로그인되어 있는지 아닌지 세션스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	
	if(member) {
		window.location.href = '/finalProject/views/storeUpdate?sno=' + currentSno + '&mno=' + currentMno;
	}
	else if(!member) {
		storeDetailUpdateLoginCheck();
	}
}

function storeDetailUpdateLoginCheck(){
	Swal.fire({
		title: '로그인이 필요한 서비스 입니다.',
		icon: 'warning',
	}).then(function() {
		window.location.href = '/finalProject/views/login';
	});
}






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
	// 로그인되어 있는지 아닌지 세션스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	
	if(!member) {
		msg.style.color = 'red';
		msg.innerHTML = '로그인이 필요한 서비스 입니다.';
		event.preventDefault();
	}
	
	
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
	// 현재 페이지의 URL에서 쿼리 스트링을 가져옴
	let queryString = window.location.search;
	
	// URLSearchParams를 사용하여 쿼리 스트링을 파싱
	let urlParams = new URLSearchParams(queryString);
	
	// mno 파라미터 값을 얻음
	currentMno = urlParams.get('mno');
	
	
	
	

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
			storeDetailLikeLoginCheck();
			
/*        		if (needLogin()) {
			
					window.location.href = '/finalProject/views/login';
				}*/ 
	    }
	});
	
	
	function storeDetailLikeLoginCheck(){
		Swal.fire({
			title: '로그인이 필요한 서비스 입니다.',
			icon: 'warning',
		}).then(function() {
			window.location.href = '/finalProject/views/login';
		});
	}
	
	
	document.querySelector('.storeDetail__modify').addEventListener('click', storeModify);

}




    function updateRating(value) {
        // 레인지 값을 히든 필드에 업데이트
        document.getElementById('rrating').value = value;

        // 레인지 값 표시 업데이트
        document.getElementById('ratingValue').innerText = value;
    }









window.addEventListener('load', init);