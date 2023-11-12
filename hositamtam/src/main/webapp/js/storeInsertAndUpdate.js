// 항목 선택시 버튼색 변경
document.addEventListener("DOMContentLoaded", function () {
  var typeButtons = document.querySelectorAll(".store__type__button1, .store__type__button2");
  var paymentButtons = document.querySelectorAll(".store__payment__button1, .store__payment__button2, .store__payment__button3");

  // 점포 형태 선택시 버튼색 변경(단일)
  typeButtons.forEach(function (button) {
    button.addEventListener("click", function () {
      var isActive = button.classList.contains("active");

      // 다른 버튼 클릭시 원래 색상으로 변경
      typeButtons.forEach(function (btn) {
        btn.classList.remove("active");
      });

      if (!isActive) {
        button.classList.add("active");
      }
    });
  });

  // 결제 방식 선택시 버튼색 변경(다중)
  paymentButtons.forEach(function (button) {
    button.addEventListener("click", function () {
      var isActive = button.classList.contains("active");

      if (!isActive) {
        button.classList.add("active");
      }
      // 재클릭시 선택 해제
      else button.classList.remove("active");
    });
  });
});


// 파일 선택시 사진 미리보기
document.addEventListener("DOMContentLoaded", function () {
  var fileInput = document.querySelector(".store__photo__input");
  var previewImage = document.createElement("img");
  previewImage.setAttribute("class", "store__photo__preview");
  var previewContainer = document.querySelector(".store__update__form");

  fileInput.addEventListener("change", function () {
    var file = fileInput.files[0];
    var reader = new FileReader();

    reader.onloadend = function () {
      previewImage.src = reader.result;
    };

    if (file) {
      reader.readAsDataURL(file);
      previewContainer.appendChild(previewImage);
    } else {
      // 파일 선택이 해제될 때 미리보기 삭제
      previewContainer.removeChild(previewImage);
    }
  });
});

// 최소 하나의 결제방식이 필수로 선택되어야 함
 function validatePayment() {
        var checkboxes = document.getElementsByName('paytype');
        
        // 최소한 하나의 체크박스가 선택되었는지 확인합니다.
        var isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
        
        // 최소한 하나가 선택되지 않았다면 알림창을 띄우고 false를 반환합니다.
        if (!isChecked) {
            alert("적어도 하나 이상의 결제 방식을 선택해주세요.");
            return false;
        }
        return true;
    }

    // 폼이 제출될 때 validatePayment 함수를 실행합니다.
    document.querySelector('form').addEventListener('submit', validatePayment);
