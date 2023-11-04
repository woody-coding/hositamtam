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
    let id = document.querySelector('#id').value;
    let passwd = document.querySelector('#passwd').value;
    let msgDiv = document.querySelector('#msg'); // 에러 메시지를 표시할 요소

    if (!id || !passwd) {
        event.preventDefault();
        msgDiv.innerHTML = '아이디와 비밀번호를 모두 입력해야 합니다.';
        // 폼 제출을 중단하고 에러 메시지를 표시
    } else {
        msgDiv.innerHTML = ''; // 에러 메시지를 지웁니다.
    }
}

function deleteHandler(){
	if(confirm('현재 회원을 정말 삭제하시겠습니까?')){
		
		
	// window.location.href = "/finalProject/views/main";
	}else{
		
		
	}
}


function init() {
    let join__form = document.querySelector('#mypageedit_button');
    join__form.addEventListener('click', editHandler);
    
    let signup_button = document.querySelector('#delete_button');
    signup_button.addEventListener('click', deleteHandler);
    
	// userId 변수를 maskUserId 함수에 전달하여 마스킹
    var maskedUserId = maskUserId(userId);
    // 마스킹된 userId를 특정 HTML 요소에 표시
    document.querySelector('maskedUserId').textContent = maskedUserId;
}



window.addEventListener('load', init);
