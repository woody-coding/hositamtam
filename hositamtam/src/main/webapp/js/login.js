function loginHandler(event) {
    event.preventDefault();

    // 사용자가 입력한 아이디와 비밀번호 값을 가져옴
    let id = document.querySelector('#id').value;
    let passwd = document.querySelector('#passwd').value;

    // 서버에 로그인 요청을 보냄
    fetch('/views/loginMember', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id, passwd }), // 아이디와 비밀번호를 JSON 형태로 전송
    })
    .then(response => {
        if (response.status === 200) {
            // 로그인 성공 시, 메인 페이지로 이동
            window.location.href = '/views/main';
        } else {
            // 로그인 실패 시, 에러 메시지를 표시
            document.querySelector('.login__error').textContent = '아이디 혹은 패스워드가 틀렸습니다.';
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function init() {
    let login__form = document.querySelector('#login__form');
    login__form.addEventListener('submit', loginHandler);
    
    let signupButton = document.querySelector('#signup-button');
    signupButton.addEventListener('click', function() {
        // 회원가입 페이지로 이동
        window.location.href = '/views/join';
    });
}

window.addEventListener('load', init);