let xhr = new XMLHttpRequest();
let currentPno;
let currentId;


function plikecountHandler() {
		if (xhr.readyState === 4 && xhr.status === 200) {
		
		const postLike = JSON.parse(xhr.responseText);
		let postLikeCount = postLike[0].plikecount;


		if (postLike[0].likeStatus === 'o') {
			// 좋아요했을 때는 빨갛게~
			//document.querySelector('#plikecountUpdate').style.color = '#E6007E';
		}
		
		
		document.querySelector('#plikecountView').innerHTML = '좋아요 ' + postLikeCount + '개';
    }

}



// [현재 접속한 id가 해당 pno글에 좋아요를 했는지 안했는지 여부 판단 + 해당 pno글의 최신 좋아요 개수 가져오기] 데이터 비동기 처리
function plikecountStatusHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		
		const postLike = JSON.parse(xhr.responseText);
		let postLikeCount = postLike[0].plikecount;

		document.querySelector('#plikecountView').innerHTML = '좋아요 ' + postLikeCount + '개';
    }
}





function commentInsertHandler(event) {
	let msg = document.querySelector('#msg');
	
	// 로그인되어 있는지 아닌지 로컬스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	
	if(!member) {
		writeCommentsLoginCheck();
	    event.preventDefault();
	}
	
	if(member) {
		let commentValue = document.querySelector('#commentContent').value;
		
		if(commentValue.length < 5 || commentValue.length > 100) {
			msg.style.color = 'red';
			msg.innerHTML = '댓글 내용은 5글자 이상, 100글자 이하여야 합니다.';
			event.preventDefault();
		}
	}
}

function writeCommentsLoginCheck(){
	Swal.fire({
		title: '로그인이 필요한 서비스 입니다.',
		icon: 'warning',
	}).then(function() {
		window.location.href = '/finalProject/views/login';
	});
}




function init() {
	
	// 현재 글의 고유 번호 pno 값 가져오기
	const postNumber = window.localStorage.getItem('postNumber');
	const postNo = JSON.parse(postNumber);
	currentPno = postNo.pno;
	
	
	
	// 로그인되어 있는지 아닌지 로컬스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	
	

		
	xhr.onreadystatechange = plikecountStatusHandler;
	
    let param = '?command=updateLikeStatus&pno=' + currentPno;
    xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
    xhr.send();		
	
	

	
	document.querySelector('#plikecountUpdate').addEventListener('click', function() {

		if (member) {
			currentId = member.id;
			
			xhr.onreadystatechange = plikecountHandler;
			
		    let param = '?command=updateLike&pno=' + currentPno + '&id=' + currentId;
		    xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
		    xhr.send();		
		}
		else {
		    postLikeButtonLoginCheck();
		}
	});
	
	
	document.querySelector('#commentInsert').addEventListener('click', commentInsertHandler);
}

function postLikeButtonLoginCheck(){
	Swal.fire({
		title: '로그인이 필요한 서비스 입니다.',
		icon: 'warning',
	}).then(function() {
		window.location.href = '/finalProject/views/login';
	});
}



window.addEventListener('load', init);