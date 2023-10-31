function submitHandler(event){
	let id = documnet.querySelector('#id');
	let nickname = document.querySelector('#nickname');
	let passwd = document.querySelector('#passwd'); 
	let repasswd = document.querySelector('#repasswd');
	let msgDiv = document.querySelector('#msg');
	let inputCheck = true;
	let msg = '';
	
	msgDiv.innerHTML = '';
	
	if(id.value.length > 4 && id.value.length < 12){
		msg = '아이디는 4글자 이상 12글자이어야 합니다!';
		id.value = '';
		inputCheck = false;
		
	}else if(passwd.value.length > 8 && passwd.value.length < 16){
		msg = '비밀번호는 8글자 이상 16글자이어야 합니다!';
		passwd.value = '';
		repasswd.value = '';
		inputCheck = false;
		
	}else if(passwd.value !== repasswd.value){
		msg = '비밀번호와 비밀번호 확인은 서로 일치해야 합니다!';
		passwd.value = '';
		repasswd.value = '';
		inputCheck = false;
		
	}else if(nickname.value.length > 2 && nickname.value.length < 8){
		msg = '이름은 2글자 이상 8글자 이하이어야 합니다!';
		nickname.value = '';
		inputCheck = false;
	}
	
	
	if(!inputCheck){
		event.preventDefault();
		msgDiv.innerHTML = msg;
	}else{
		
		let idCheckValue = '';
		
		if(idCheckValue === 'duplicate'){
			event.preventDefault();
			msgDiv.innerHTML = '아이디가 이미 사용중입니다.';
			
		}else{
			
		let nicknameCheckValue = '';

	        if (nicknameCheckValue === 'duplicate') {
	            event.preventDefault();
	            msgDiv.innerHTML = '닉네임이 이미 사용 중입니다.';
            }
		}
	}
	
}

function init(){
	let insertMember = document.querySelector('#insertMember');
	
	insertMember.addEventListener('submit', submitHandler);
}

window.addEventListener('load', init);
