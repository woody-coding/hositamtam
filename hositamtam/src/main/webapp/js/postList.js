function writePostHandler(event) {
	// 로그인되어 있는지 아닌지 세션스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	
	if (!member) {
		writePostLoginCheck();
	    event.preventDefault();
	}
}

function writePostLoginCheck(){
	Swal.fire({
		title: '로그인이 필요한 서비스 입니다.',
		icon: 'warning',
	}).then(function() {
		window.location.href = '/finalProject/views/login';
	});
}

function init() {
	document.querySelector('#writePost').addEventListener('click', writePostHandler);
}

window.addEventListener('load', init);