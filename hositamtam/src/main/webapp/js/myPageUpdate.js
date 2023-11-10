var xhr = new XMLHttpRequest();

// 닉네임, 비밀번호 중복 확인 및 에러 처리 상태 변수
var nicknameValid = false;
var passwdValid = false;
var currentId;



function ajaxNicknameChangeHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    var response = JSON.parse(xhr.responseText);
	    if (response.isDuplicate === true) {
	        // 중복된 닉네임일 경우 메시지 표시
	        nicknameValid = false;
	        document.querySelector('#error_msg').innerHTML = '이미 사용 중인 닉네임입니다. <br/>다른 닉네임을 입력해주세요.';
	        
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
    var newNickname = document.getElementById('change__nickname').value;

    // AJAX를 사용하여 서버에 중복 확인 요청
    xhr.onreadystatechange = ajaxNicknameChangeHandler;
    
    xhr.open('POST', '/finalProject/ajaxController/memberAjaxController.jsp?command=checkNickname&nickname=' + newNickname, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.send(newNickname);
}


//===========================================================================================================


function ajaxPasswdChangeHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    var response = JSON.parse(xhr.responseText);
	    if (response[0].isDuplicate === true) {
	        // 전에 사용하던 비밀번호일 경우 메시지 표시
	        passwdValid = false;
	        document.querySelector('#error_msg').innerHTML = '이전에 사용하시던 비밀번호입니다. <br/>다른 비밀번호를 입력해주세요.';
	        
	    } else {
	        // 중복되지 않은 닉네임일 경우 닉네임 사용가능 표시
	        passwdValid = true;
	        document.querySelector('#error_msg').innerHTML = '변경 가능한 비밀번호입니다.';
        }

	}
}

// 닉네임 중복 확인 버튼 클릭 시 이벤트 처리
function checkPasswdHandler() {
	let newPassword = document.querySelector('#change__password').value;
	let confirmPasswd = document.querySelector('#confirm__password').value;
	
	if(8 <= newPassword.length && 8 <= confirmPasswd.length && newPassword.length <= 16 && confirmPasswd.length <= 16 && newPassword === confirmPasswd) {
		
	    // AJAX를 사용하여 서버에 중복 확인 요청
	    xhr.onreadystatechange = ajaxPasswdChangeHandler;
	    
	    xhr.open('POST', '/finalProject/ajaxController/memberAjaxController.jsp?command=checkPassword&password=' + newPassword + '&id=' + currentId, true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	    xhr.send(newPassword, currentId);
	}
	else {
		if(newPassword.length < 8 || newPassword.length > 16) {
			document.querySelector('#error_msg').innerHTML = '비밀번호는 8자리 이상, 16자리 이하여야 합니다.';
		} else {
			document.querySelector('#error_msg').innerHTML = '비밀번호가 일치하지 않습니다.';
		}
        
	}
}


//===========================================================================================================

function memberUpdateHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		
	    var response = JSON.parse(xhr.responseText);
	    
	    if (response[0].isUpdate === true) {
	        // 업데이트 성공 시
	        alert('회원 정보가 정상적으로 수정되었습니다. 재로그인을 해주세요!');
	        window.sessionStorage.removeItem('memberId');
	        window.location.href = '/finalProject/views/logout';	        
	    } else {
	        // 업데이트 실패 시
	        alert('회원 정보 수정에 실패하였습니다.');
	        window.location.href = '/finalProject/views/myPageUpdate';	  
	        
	    }
	}
}




// 최종 수정하기 요청하는 JavaScript 함수
function updateHandler() {
	let param = '';
	
    if (nicknameValid === true || passwdValid === true) {
        let newNickname = document.getElementById('change__nickname').value;
        let newPassword = document.getElementById('change__password').value;

	    // AJAX를 사용하여 서버에 회원 정보 수정 요청
	    xhr.onreadystatechange = memberUpdateHandler;
	    
	    
	    if(newNickname.length === 0 && newPassword.length !== 0) {
			param = '?command=changeProfile&password=' + newPassword + '&id=' + currentId;
		} 
		else if (newNickname.length !== 0 && newPassword.length === 0) {
			param = '?command=changeProfile&nickname=' + newNickname + '&id=' + currentId;
		}
		else if (newNickname.length !== 0 && newPassword.length !== 0) {
			param = '?command=changeProfile&password=' + newPassword + '&nickname=' + newNickname + '&id=' + currentId;
		}
	    

	    xhr.open('POST', '/finalProject/ajaxController/memberAjaxController.jsp' + param, true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	    xhr.send(newPassword, newNickname);
	}
	else {
		alert('닉네임 혹은 비밀번호를 먼저 수정해주세요!');
		window.location.href = '/finalProject/views/myPageUpdate';
	}
}




document.addEventListener('click', function(event) {
    // 클릭된 엘리먼트의 ID를 가져옴
    var clickedElementId = event.target.id;
    
    // 만약 클릭된 엘리먼트가 '#error_msg'가 아니라면 해당 메시지를 숨김
    if (clickedElementId !== 'change__password' && clickedElementId !== 'confirm__password') {
        document.querySelector('#error_msg').innerHTML = '';
    }
});



function init() {
	
	// 로그인되어 있는지 아닌지 세션스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	   
	if (member) {
	    currentId = member.id;
	}

	
	document.querySelector('#change__password').addEventListener('keyup', checkPasswdHandler);
	
    // 닉네임 중복확인 버튼 클릭 시
    document.querySelector('#nickname_check').addEventListener('click', checkNicknameHandler);

	// 비밀번호 입력후 확인란 포커스 맞춰질때 비밀번호 중복확인
	document.querySelector('#confirm__password').addEventListener('keyup', checkPasswdHandler);

    // 수정하기 버튼 클릭 시
    document.querySelector('#update_button').addEventListener('click', updateHandler);
}

window.addEventListener('load', init);
