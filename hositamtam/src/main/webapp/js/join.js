function submitHandler(event){
	let passwd = document.querySelector('#passwd'); 
	let repasswd = document.querySelector('#passwd');
	let name = document.querySelector('#name');
	let msgDiv = document.querySelector('#msg');
	let inputCheck = true;
	let msg = '';
	
	msgDiv.innerHTML = '';
	
	if(passwd.value.length < 4){
		msg = '비밀번호는 4글자 이상이어야 합니다!';
		passwd.value = '';
		repasswd.value = '';
		inputCheck = false;
		
	}else if(passwd.value !== repasswd.value){
		msg = '비밀번호와 비밀번호 확인은 서로 일치해야 합니다';
		passwd.value = '';
		repasswd.value = '';
		inputCheck = false;
		
	}else if(name.value.length < 2){
		msg = '이름은 2글자 이상이어야 합니다';
		name.value = '';
		inputCheck = false;
	}
	
	
	if(!inputCheck){
		event.preventDefault();
		msgDiv.innerHTML = msg;
	}
	
}

function modifyHandler(event){
	let id = event.target.getAttribute('id');
	let url = '/myProject/step2/modify?id=' + id;
	
	location.href= url;
}

function init(){
	let insertMember = document.querySelector('#insertMember');
	let listTable = document.querySelector('#listTable');
	
	insertMember.addEventListener('submit', submitHandler);
	listTable.addEventListener('click', modifyHandler);
}

window.addEventListener('load', init);
