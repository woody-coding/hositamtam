var count = 0;
function dropDownHandler() {
  if(count % 2 == 1){
  document.querySelector('.mkNameList').classList.add('show-mkNameList');
  }
  else {
    document.querySelector('.mkNameList').classList.remove('show-mkNameList');

  }
  count++;

}

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

document.querySelector("#whatMarket").addEventListener("click",dropDownHandler);  
}
 
window.addEventListener('load', init);

