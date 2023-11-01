
document.getElementById('writePost').addEventListener('click', function () {
  document.querySelector('.black-bg').classList.add('show-modal');
});


document.getElementById('close').addEventListener('click', function () {
  document.querySelector('.black-bg').classList.remove('show-modal');
  document.querySelector('.black-bg-modify').classList.remove('show-modal');
  // 추가적인 초기화 작업을 수행할 수도 있습니다.
});

document.getElementById('postModify').addEventListener('click', function () {
  document.querySelector('.black-bg-modify').classList.add('show-modal');
});



var count = 0;

document.getElementById('btnDown').addEventListener('click', function () {
  document.querySelector('.mkNameList').classList.add('show-mkNameList');
});

document.getElementById('btnUp').addEventListener('click', function () {
  document.querySelector('.mkNameList').classList.remove('show-mkNameList');
});

var tabButtons = document.querySelectorAll('.tab-button');
var tabContents = document.querySelectorAll('.tab-content');

for (let i = 0; i < tabButtons.length; i++) {
  tabButtons[i].addEventListener('click', function () {
      for (let j = 0; j < tabButtons.length; j++) {
          tabButtons[j].classList.remove('orange');
          tabContents[j].classList.remove('show');
      }
      tabButtons[i].classList.add('orange');
      tabContents[i].classList.add('show');
  });
}

document.querySelector('.moreContents').addEventListener('click', function () {
  document.querySelector('.commentBox').classList.add('show-more');
  document.querySelector('.commentList').classList.add('show-more');
});

document.getElementById('delete').addEventListener('click', function () {
  confirm('정말 삭제하시겠습니까?');
});


// 파일 업로드 하고 미리보기
document.getElementById("image").addEventListener("change", function (event) {
  setThumbnail(event);
});

function setThumbnail(event) {
  var reader = new FileReader();

  reader.onload = function(event) {
      var img = document.createElement("img");
      img.setAttribute("src", event.target.result);

      var container = document.querySelector("div#image_container");
      
      // 기존 미리보기와 파일 삭제
      while (container.firstChild) {
          container.removeChild(container.firstChild);
      }

      container.appendChild(img);
  };

  reader.readAsDataURL(event.target.files[0]);
}


//
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

