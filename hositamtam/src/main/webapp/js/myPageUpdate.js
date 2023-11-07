var xhr = new XMLHttpRequest();


// 닉네임, 비밀번호 중복 확인 및 에러 처리 상태 변수
var nicknameValid = false;
var passwdValid = false;


 function ajaxNicknameChangeHandler() {
    if (xhr.readyState === 4 && xhr.status === 200) {
        var response = JSON.parse(xhr.responseText);
        if (response.isDuplicate === "success") {
            // 중복된 닉네임일 경우 메시지 표시
            document.getElementById('error_msg').textContent = '이미 사용 중인 닉네임입니다. 다른 닉네임을 입력해주세요.';
            nicknameValid = false;
        } else {
            // 중복되지 않은 닉네임일 경우 메시지 초기화
            document.getElementById('error_msg').textContent = '';
            nicknameValid = true;
        }
    }
}

// 닉네임 중복 확인 버튼 클릭 시 이벤트 처리
function changeNicknameHandler() {
    // 입력한 닉네임 가져오기
    var newNickname = document.getElementById('change-nickname').value;

    // AJAX를 사용하여 서버에 중복 확인 요청
    xhr.onreadystatechange = ajaxNicknameChangeHandler;
    
    xhr.open('GET', '/hositamtam/updateNickname', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.send('newNickname=' + newNickname);
}



// 최종 수정하기 요청하는 JavaScript 함수
function updateHandler() {
    if (nicknameValid || passwdValid) {
        var newNickname = document.getElementById('change-nickname').value;
        var newPassword = document.getElementById('change-password').value;
        var confirmPassword = document.getElementById('confirm__password').value;

        // 서버로의 수정 요청 데이터를 준비
        var data = {
            newNickname: newNickname,
            newPassword: newPassword,
            confirmPassword: confirmPassword
        };

        // 서버로 최종 수정 요청을 보내는 AJAX 요청
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/views/myPageUpdate', true);
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // 수정 성공 시, 서버 응답 처리
                    var response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        // 수정 성공 시의 동작을 수행 (예: 메시지 출력 또는 리다이렉트)
                    } else {
                        // 수정 실패 시의 동작을 수행 (예: 에러 메시지 출력)
                        console.log(response.message);
                    }
                } else {
                    // 서버 응답 오류 처리
                    console.error('서버 응답 오류');
                }
            }
        };

        // 데이터를 JSON 문자열로 변환하여 요청 본문에 추가
        xhr.send(JSON.stringify(data));
    } else {
        // 중복 확인을 통과하지 못한 경우 에러 메시지 표시
        document.getElementById('error_msg').textContent = '닉네임 및 비밀번호를 확인하세요.';
    }
}


function init() {
    // 닉네임 수정 버튼 클릭 시
    document.querySelector('#nickname_check').addEventListener('click', changeNicknameHandler);

    // 수정하기 버튼 클릭 시
    document.querySelector('#update_button').addEventListener('click', updateHandler);
}

window.addEventListener('load', init);
