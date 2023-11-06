// 닉네임 수정을 요청하는 JavaScript 함수
function changeNicknameHandler(event) {
    
    
    
}


// 비밀번호 수정을 요청하는 JavaScript 함수
function changePasswdHandler(event) {
    
    
    
}

// 최종 수정하기 요청하는 JavaScript 함수
function updateHandler(event){
	
	
}



function init() {
    // 닉네임 수정 버튼 클릭 시
    document.querySelector('#nickname_check').addEventListener('click', changeNicknameHandler);
    
    // 비밀번호 수정 버튼 클릭 시
    document.querySelector('#passwd_check').addEventListener('click', changePasswdHandler);
    
    // 수정하기 버튼 클릭 시
    document.querySelector('#update_button').addEventListener('click', updateHandler);
    
}

window.addEventListener('load', init);
