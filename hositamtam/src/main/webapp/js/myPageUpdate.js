var xhr = new XMLHttpRequest();
var updateXhr = new XMLHttpRequest();

// 닉네임, 비밀번호 중복 확인 및 에러 처리 상태 변수
var nicknameValid = false;
var passwdValid = false;
var currentId;



if(nicknameValid === true || passwdValid === true) {
	// 수정하기 버튼 클릭 가능 -> DB바꾸기
}
else {
	// 수정하기 버튼 비활성화 => 에러메시지창에 둘중에 하나는 수정해야한다고 알리기
}




function ajaxNicknameChangeHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    var response = JSON.parse(xhr.responseText);
	    if (response.isDuplicate === true) {
	        // 중복된 닉네임일 경우 메시지 표시
	        nicknameValid = false;
	        document.querySelector('#error_msg').innerHTML = '이미 사용 중인 닉네임입니다. 다른 닉네임을 입력해주세요.';
	        
	    } else {
	        // 중복되지 않은 닉네임일 경우 닉네임 사용가능 표시
	        nicknameValid = true;
	        document.querySelector('#error_msg').innerHTML = '사용 가능한 닉네임입니다.';
	        
	    }
	}
}

// 닉네임 중복 확인 버튼 클릭 시 이벤트 처리
function checkNicknameHandler() {
    // 입력한 닉네임 가져오기
    var newNickname = document.getElementById('change-nickname').value;

    // AJAX를 사용하여 서버에 중복 확인 요청
    xhr.onreadystatechange = ajaxNicknameChangeHandler;
    
    xhr.open('POST', '/finalProject/AjaxController.jsp?command=checkNickname&nickname=' + newNickname, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.send(newNickname);
}


//===========================================================================================================


function ajaxPasswdChangeHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    var response = JSON.parse(xhr.responseText);
	    if (response.isDuplicate === true) {
	        // 전에 사용하던 비밀번호일 경우 메시지 표시
	        passwdValid = false;
	        document.querySelector('#error_msg').innerHTML = '전에 사용하던 비밀번호입니다. 다른 비밀번호를 입력해주세요.';
	        
	    } else {
	        // 중복되지 않은 닉네임일 경우 닉네임 사용가능 표시
	        passwdValid = true;
	        document.querySelector('#error_msg').innerHTML = '사용 가능한 비밀번호입니다.';
	        
	    }
	}
}

// 닉네임 중복 확인 버튼 클릭 시 이벤트 처리
function checkPasswdHandler() {
    // 입력한 닉네임 가져오기
    var newPassword = document.getElementById('change-password').value;

    // AJAX를 사용하여 서버에 중복 확인 요청
    xhr.onreadystatechange = ajaxPasswdChangeHandler;
    
    xhr.open('POST', '/finalProject/AjaxController.jsp?command=checkPassword&password=' + newPassword + '&id=' + currentId, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.send(newPassword, currentId);
}


//===========================================================================================================

function memberUpdateHandler() {
	
}




// 최종 수정하기 요청하는 JavaScript 함수
function updateHandler() {
    if (nicknameValid === true || passwdValid === true) {
        let newNickname = document.getElementById('change-nickname').value;
        let newPassword = document.getElementById('change-password').value;


	    // AJAX를 사용하여 서버에 회원 정보 수정 요청
	    xhr.onreadystatechange = memberUpdateHandler;
	    
	    xhr.open('POST', '/finalProject/AjaxController.jsp?command=닉넴이나비번DB에서변경해라&password=' + newPassword + '&id=' + currentId, true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	    xhr.send(newPassword, currentId);




		/*if(newPassword == confirmPassword){
			document.querySelector('#error_msg').innerHTML = '비밀번호와 비밀번호 확인란이 동일합니다';
			// 서버로의 수정 요청 데이터를 준비
       	 	var data = {
            	newNickname: newNickname,
           	 	newPassword: newPassword,
            	confirmPassword: confirmPassword
        	};
		}else{
			document.querySelector('#error_msg').innerHTML = '비밀번호와 비밀번호 확인란이 일치하지 않습니다. 다시 입력해주세요.';
		}*/
		
		
        

        // 서버로 최종 수정 요청을 보내는 AJAX 요청
        
        updateXhr.open('POST', '/finalProject/AjaxController.jsp?command=changeProfile', true);
        updateXhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        updateXhr.onreadystatechange = function () {
            if (updateXhr.readyState === 4 && updateXhr.status === 200) {
                    // 수정 성공 시, 서버 응답 처리
                    var response = JSON.parse(updateXhr.responseText);
                    if (response.success == true) {
                        // 수정 성공 시의 동작을 수행 (예: 메시지 출력 또는 리다이렉트)
                        window.location.href = '/finalProject/views/myPage';
                    } else {
                        // 수정 실패 시의 동작을 수행 (예: 에러 메시지 출력)
                        console.log(response.message);
                    }
                } else {
                    // 서버 응답 오류 처리
                    console.error('서버 응답 오류');
            }
        };

        // 데이터를 JSON 문자열로 변환하여 요청 본문에 추가
        updateXhr.send(JSON.stringify(data));
    } else {
        // 중복 확인을 통과하지 못한 경우 에러 메시지 표시
        document.querySelector('#error_msg').innerHTML = '닉네임 및 비밀번호를 확인하세요.';
    }
}



function init() {
	
	// 로그인되어 있는지 아닌지 세션스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	   
	if (member) {
	    currentId = member.id;
	}

	
	
	
	
    // 닉네임 중복확인 버튼 클릭 시
    document.querySelector('#nickname_check').addEventListener('click', checkNicknameHandler);

	// 비밀번호 입력후 확인란 포커스 맞춰질때 비밀번호 중복확인
	document.querySelector('#confirm__password').addEventListener('focus', checkPasswdHandler);

    // 수정하기 버튼 클릭 시
    document.querySelector('#update_button').addEventListener('click', updateHandler);
}

window.addEventListener('load', init);
