function ajaxTestHandler() {
	document.querySelector('#ajaxViewTest').innerHTML = '짜잔~!!';
}

function init() {
	document.querySelector('#ajaxButton').addEventListener('click', ajaxTestHandler);	
}

window.addEventListener('load', init);