// JavaScript 코드
var userId = "${userId}";
console.log(userId);

function maskUserId(userId) {
    if (userId.length <= 3) {
        return userId;  // userId의 길이가 3자리 이하이면 그대로 반환
        
    } else {
		
        return userId.substr(0, 2) + '*'.repeat(userId.length - 3);
    }
}

function editHandler(event) {
     
}

function deleteHandler(event){
	if(confirm('현재 회원을 정말 삭제하시겠습니까?')){
		 document.getElementById('deleteForm').submit();
		
	}else{
		
		event.preventDefault();
	}
}


function init() {
    let mypageedit_button = document.querySelector('#mypageedit_button');
    mypageedit_button.addEventListener('click', editHandler);
    
    let delete_button = document.querySelector('#delete_button');
    delete_button.addEventListener('click', deleteHandler);
    
	// userId 변수를 maskUserId 함수에 전달하여 마스킹
    var maskedUserId = maskUserId(userId);
    // 마스킹된 userId를 특정 HTML 요소에 표시
    document.querySelector('maskedUserId').textContent = maskedUserId;
}



window.addEventListener('load', init);
