function submitHandler(event) {
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

function joinHandler(){
	window.location.href = "/finalProject/views/join";
}

function init() {
    let join__form = document.querySelector('#login__form');
    join__form.addEventListener('submit', submitHandler);
    
    let signup_button = document.querySelector('#signup-button');
    signup_button.addEventListener('click', joinHandler);
}

window.addEventListener('load', init);
