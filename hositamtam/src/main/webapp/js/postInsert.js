function postInsertButtonHandler(event) {
	let ptitle = document.querySelector('#ptitle').value;
	let pcontent = document.querySelector('#myTextarea').value;
	let msg = document.querySelector('#msg');
	
	if(ptitle.length < 5 || ptitle.length > 20) {
		msg.style.color = 'red';
		msg.innerHTML = '제목은 5글자 이상, 20글자 이하여야 합니다.';
		event.preventDefault();
	}
	
	if(pcontent.length < 5 || pcontent.length > 500) {
		msg.style.color = 'red';
		msg.innerHTML = '내용은 5글자 이상, 500글자 이하여야 합니다.';
		event.preventDefault();
	}
}




function init() {
	document.querySelector('#postInsertButton').addEventListener('click', postInsertButtonHandler);
}









window.addEventListener('load', init);