/*for (let i = 0; i < $('.tab-button').length; i++){

  $('.tab-button').eq(i).on('click', function(){
    $('.tab-button').removeClass('blue');
    $('.tab-button').eq(i).addClass('blue');
    
  })

};*/
/*document.addEventListener('DOMContentLoaded', function() {
  const tabButtons = document.querySelectorAll('.tab-button');
  
  tabButtons.forEach(button => {
    button.addEventListener('click', () => {
      // 모든 탭 버튼에서 'blue' 클래스 제거
      tabButtons.forEach(btn => btn.classList.remove('blue'));
      // 클릭한 탭 버튼에 'blue' 클래스 추가
      button.classList.add('blue');
    });
  });
});*/

document.addEventListener('DOMContentLoaded', function() {
  const tabButtons = document.querySelectorAll('.tab-button');

  tabButtons.forEach(button => {
    button.addEventListener('click', function(event) {
      // 모든 탭 버튼에서 'blue' 클래스 제거
      tabButtons.forEach(btn => btn.classList.remove('blue'));
      // 클릭한 탭 버튼에 'blue' 클래스 추가
      button.classList.add('blue');
    });
  });
  
  
});





function writePostHandler(event) {
	// 로그인되어 있는지 아닌지 세션스토리지 존재 유무로 판단하기
	const memberId = window.sessionStorage.getItem('memberId');
	const member = JSON.parse(memberId);
	
	if (!member) {
		alert('로그인이 필요한 서비스 입니다.');
	    event.preventDefault();
	    window.location.href = '/finalProject/views/login';
	}
}





/*
var count = 1;
function dropDownHandler() {
  if(count % 2 == 1){
  document.querySelector('.mkNameList').classList.add('show-mkNameList');
  }
  else {
    document.querySelector('.mkNameList').classList.remove('show-mkNameList');

  }
  count++;

}
*/
var radioButtons = document.querySelectorAll('input[name="category"]');

// 라디오 버튼에 이벤트 리스너 추가
radioButtons.forEach(function(radioButton) {
    radioButton.addEventListener('change', function() {
        // 모든 라벨에서 'selected' 클래스 제거
        document.querySelectorAll('.postCategory-radio label').forEach(function(label) {
            label.classList.remove('selected');
        });

        // 선택된 라벨에 'selected' 클래스 추가
        if (this.checked) {
            this.parentElement.classList.add('selected');
        }
    });
});



function init() {
document.querySelector('#writePost').addEventListener('click', writePostHandler);

document.querySelector("#whatMarket").addEventListener("click",dropDownHandler);  

}


 
window.addEventListener('load', init);

