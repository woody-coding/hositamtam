var xhr = new XMLHttpRequest();
var idValid = false;
var nicknameValid = false;
let msg = document.querySelector('#msg');
var okId;
var okNickname;



// 비밀번호와 비밀번호 확인 조건 =====================================================================================
function passwdHandler() {
	
	let passwd = document.querySelector('#passwd').value;
	let repasswd = document.querySelector('#repasswd').value;
	
	if(8 <= passwd.length && 8 <= repasswd.length && passwd.length <= 16 && repasswd.length <= 16 && passwd === repasswd) {
        document.querySelector('.join__error').style.color = 'green';
		document.querySelector('.join__error').innerHTML = '사용가능한 비밀번호 입니다.';
	}
	else {
		if(passwd.length < 8 || repasswd.length > 16) {
	        document.querySelector('.join__error').style.color = 'red';
			document.querySelector('.join__error').innerHTML = '비밀번호는 8자리 이상, 16자리 이하여야 합니다.';
		} else {
	        document.querySelector('.join__error').style.color = 'red';
			document.querySelector('.join__error').innerHTML = '비밀번호가 일치하지 않습니다.';
		}
	}
}


//===========================================================================================================








//==== id ==================================================================================================

function idCheckHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		let msg = document.querySelector('#msg');
		let response = JSON.parse(xhr.responseText);
		
		if(response[0].isIdDuplicate === true) {
			idValid = false;
			msg.style.color = 'red';
			msg.innerHTML = '이미 사용 중인 아이디입니다. <br/>다른 아이디를 입력해주세요.';
		}
		else {
			okId = document.querySelector('#id').value;
			idValid = true;
			msg.style.color = 'green';
			msg.innerHTML = '사용 가능한 아이디 입니다.';
		}
	}	
}



function idCheck() {
	let msg = document.querySelector('#msg');
	let id = document.querySelector('#id').value;
	
    if(id.length >= 4 && id.length <= 12) {
	    xhr.onreadystatechange = idCheckHandler;
	    
	    xhr.open('POST', '/finalProject/ajaxController/memberAjaxController.jsp?command=isIdDuplicate&id=' + id, true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	    xhr.send(id);
	}
	else {
		msg.style.color = 'red';
		msg.innerHTML = '아이디는 4자리 이상, 12자리 이하여야 합니다.';
	}
	
}


//===========================================================================================================






//== nickname =================================================================================================


function nicknameCheckHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		let msg = document.querySelector('#msg');
		let response = JSON.parse(xhr.responseText);
		
		if(response[0].isNicknameDuplicate === true) {
			nicknameValid = false;
			msg.style.color = 'red';
			msg.innerHTML = '이미 사용 중인 닉네임입니다. <br/>다른 닉네임을 입력해주세요.';
		}
		else {
			okNickname = document.querySelector('#nickname').value;
			nicknameValid = true;
			msg.style.color = 'green';
			msg.innerHTML = '사용 가능한 닉네임 입니다.';
		}
	}
}


function nicknameCheck() {
	let msg = document.querySelector('#msg');
	let nickname = document.querySelector('#nickname').value;
	
	if(nickname.length >= 2 && nickname.length <= 8) {
	    xhr.onreadystatechange = nicknameCheckHandler;
	    
	    xhr.open('POST', '/finalProject/ajaxController/memberAjaxController.jsp?command=isNicknameDuplicate&nickName=' + nickname, true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	    xhr.send(nickname);
	}
	else {
		msg.style.color = 'red';
		msg.innerHTML = '닉네임은 2자리 이상, 8자리 이하여야 합니다.';
	}
}

//===========================================================================================================






//== 회원가입 버튼 =============================================================================================

function joinButtonHandler() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		joinSuccess();
	}
}

//== 회원가입 성공시 얼럿 =================================================================
function joinSuccess(){
	Swal.fire({
		title: '성공적으로 가입되었습니다 !',
		text: '감사합니다.',
		icon: 'success',
	}).then(function() {
		window.location.href = '/finalProject/views/login';
	});
}



function joinButton(event) {
	event.preventDefault();
	
    let id = document.querySelector('#id').value;
    let nickname = document.querySelector('#nickname').value;
    let passwd = document.querySelector('#passwd').value;
    let repasswd = document.querySelector('#repasswd').value;
    let birth = document.querySelector('#birthdate').value;
    let gender = document.querySelector('#gender').value;
    let msg = document.querySelector('#msg');
    
    
    
    //alert('id : ' + id + '/  nickname : ' + nickname + '/  passwd : ' + passwd + '/  birth : ' + birth + '/  gender : ' + gender);
    
    
    
    
    let passCheck = false;
    let birthCheck = false;
    
	if(passwd.length >= 8 && passwd.length <= 16 && repasswd.length >= 8 && repasswd.length <= 16 && passwd === repasswd) {
		passCheck = true;
	}
	
	if(birth.length > 0) {
		birthCheck = true;
	}
	
	
	if(nicknameValid && idValid && passCheck && birthCheck) {
	    xhr.onreadystatechange = joinButtonHandler;
	    
	    xhr.open('POST', '/finalProject/ajaxController/memberAjaxController.jsp?command=isJoin&id=' + id + '&nickName=' + nickname + '&passwd=' + passwd + '&birthdate=' + birth + '&gender=' + gender, true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	    xhr.send(id, nickname, passwd, birth, gender);
	}
	else if(!idValid) {
		msg.style.color = 'red';
		msg.innerHTML = '먼저 아이디 중복 확인을 해주세요.';
	}
	else if(!nicknameValid) {
		msg.style.color = 'red';
		msg.innerHTML = '먼저 닉네임 중복 확인을 해주세요.';
	}
	else if(!passCheck) {
		msg.style.color = 'red';
		msg.innerHTML = '비밀번호는 8자리 이상, 16자리 이하여야 합니다.';
	}
	else if(!birthCheck) {
		msg.style.color = 'red';
		msg.innerHTML = '생년월일을 선택해주세요.';
	}
}


//===========================================================================================================




function idCheckConst() {
	let msg = document.querySelector('#msg');
	let id = document.querySelector('#id').value;
	
	if(okId !== id) {
		idValid = false;
	}
	
    if(id.length >= 4 && id.length <= 12) {
		msg.style.color = 'red';
		msg.innerHTML = '아이디 중복확인을 해주세요.';
	}
	else {
		msg.style.color = 'red';
		msg.innerHTML = '아이디는 4자리 이상, 12자리 이하여야 합니다.';
	}
}




function nicknameCheckConst() {
	let msg = document.querySelector('#msg');
	let nickname = document.querySelector('#nickname').value;
	
	if(okNickname !== nickname) {
		nicknameValid = false;
	}
	
	if(nickname.length >= 2 && nickname.length <= 8) {
		msg.style.color = 'red';
		msg.innerHTML = '닉네임 중복확인을 해주세요.';
	}
	else {
		msg.style.color = 'red';
		msg.innerHTML = '닉네임은 2자리 이상, 8자리 이하여야 합니다.';
	}
}








function init() {
	
    // 아이디 이벤트
    document.querySelector('#id').addEventListener('keyup', idCheckConst);
    document.querySelector('#id_check').addEventListener('click', idCheck);
    
    // 닉네임 이벤트
    document.querySelector('#nickname').addEventListener('keyup', nicknameCheckConst);
    document.querySelector('#nickname_check').addEventListener('click', nicknameCheck);
    
    // 비밀번호 이벤트
   	document.querySelector('#passwd').addEventListener('keyup', passwdHandler);
   	document.querySelector('#repasswd').addEventListener('keyup', passwdHandler);
   	
   	// '회원가입' 버튼 이벤트
   	document.querySelector('#join_button').addEventListener('click', joinButton);
}


window.addEventListener('load', init);
