function toPostDetailHandler() {
	let hiddenForm = document.querySelector('#hiddenForm');
	let pno = document.querySelector('#pno');
	hiddenForm.setAttribute('action', '/finalProject/views/toPostDetail?pno=' + pno);
	hiddenForm.submit();
}
	

function init() {
	document.querySelector('#toPostDetail').addEventListener('click', toPostDetailHandler);
}

window.addEventListener('load', init);