/*
function insertHandler(e) {
	
	let memberId = localStorage.getItem('memberId');
	if(!memberId) {
		e.preventDefault();
		
		location.href = '/finalProject/views/login';	
	} else {
	}
}


function clickHandler(event) {
	let id = event.target.getAttribute('id');
    
	if (id == "updateOrHide") {
		updateOrHideHandler();
	} else {
		let url = '/finalProject/views/toPostDetail?pno=' + id;
		location.href = url;
	}
}

function updateOrHideHandler() {
	alert('수정하는 창');
}


function init() {
	let postlistTable = document.querySelector('#postlistTable');
	let insert = document.querySelector('#insert');

	postlistTable.addEventListener('click', clickHandler);
	insert.addEventListener('submit', insertHandler);
}

window.addEventListener('load', init);

*/






