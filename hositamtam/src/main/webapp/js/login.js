function init() {
  let username = document.querySelector('#id');
  let password = document.querySelector('#passwd');
  let loginForm = document.querySelector('#login__form');
  let errorMsg = document.querySelector('.login__error');

  loginForm.addEventListener('submit', function(event) {
    event.preventDefault(); // 폼 제출을 막습니다.

    let usernameValue = username.value;
    let passwordValue = password.value;

    // 여기에 유효성 검사 로직을 추가합니다.
    if (usernameValue.length === 0 || passwordValue.length === 0) {
      errorMsg.innerHTML = '아이디와 비밀번호를 입력하세요.';
      return;
    }else if(){
		
		
	}else if(){
		
		
	}else{
		
		
	}

    // 유효성 검사를 통과하면 로그인 처리를 수행할 수 있습니다.
    // 여기에서 실제 로그인 처리를 수행합니다.
    // 로그인이 성공하면 리디렉션 또는 다른 작업을 수행할 수 있습니다.
  });
}

window.addEventListener('load', init);
