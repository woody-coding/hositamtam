let currentId = null;
let currenMno = null;

function clickHandler(event) {
	if (event.target.getAttribute('id') == "postOrHide") {
		insertHandler(event)
	} else detailHandler(event)
}

function insertHandler(event) {
	
}

function detailHandler(event) {
	let id = event.target.getAttribute('id');
	let url = '/finalProject/views/toPostDetail?pno=' + id;

	location.href = url;
}

function insertHandler() {
	// 로그인되어 있는지 아닌지 로컬스토리지 존재 유무로 판단하기
	const memberId = window.localStorage.getItem('memberId');
	const member = JSON.parse(memberId);

	if (member) {
		currentId = member.id;
		console.log("현재 접속한 사용자의 id : " + currentId);
		location.href = '/finalProject/views/toPostUpdate?mno=' + currenMno;		

	}
	else {
		alert('로그인이 필요한 서비스입니다.');
		window.location.href = '/finalProject/views/login';
	}
}

function init() {
	window.localStorage.getItem("userId");
	let postlistTable = document.querySelector('#postlistTable');
	let insertPost = document.querySelector('#insertPost');
	let mno = document.querySelector('#mno');
	currenMno = mno;

	postlistTable.addEventListener('click', clickHandler);
	insertPost.addEventListener('submit', insertHandler);
}

window.addEventListener('load', init);









