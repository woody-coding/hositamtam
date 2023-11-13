let storeNameCheck = false;
let storeCategoryCheck = false;
let storePhotoCheck = false;




// 항목 선택시 버튼색 변경
document.addEventListener("DOMContentLoaded", function () {
  var typeButtons = document.querySelectorAll(".storeType__label1, .storeType__label2");
  var paymentButtons = document.querySelectorAll(".paymentType__label1, .paymentType__label2, .paymentType__label3");

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
            notSelectPayment("적어도 하나 이상의 결제 방식을 선택해주세요.");
            return false;
        }
        return true;
}

	function notSelectPayment(){
		Swal.fire({
			title: '적어도 하나 이상의 <br> 결제 방식을 선택해주세요.',
			icon: 'warning',
		});
	}

    // 폼이 제출될 때 validatePayment 함수를 실행합니다.
    document.querySelector('form').addEventListener('submit', validatePayment);

   
    
    
    
    
    
    
    
function insertAndUpdateButton(event) {
	let storeName = document.querySelector('#store__name').value;
	let storeCategory = document.querySelector('#store__category__contents').value;
	let checkboxes = document.getElementsByName("paytype");
	let msg = document.querySelector('#msg');
	let storePhoto = document.querySelector('.store__photo__input').value;
	let isChecked = false;
	
	
	if(storePhoto.length && storePhoto.length > 0) {
		storePhotoCheck = true;
	}
	
	if(storeName.length >= 2 && storeName.length <= 20) {
		storeNameCheck = true;
	}
	
	if(storeCategory.length >= 2 && storeCategory.length <= 10) {
		storeCategoryCheck = true;
	}
	
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            isChecked = true;
            break;
        }
    }

	
	if(storeNameCheck === false) {
		msg.style.color = 'red';
		msg.innerHTML = '점포명은 2글자 이상, 20글자 이하여야 합니다.';
		event.preventDefault();
	}
	
	if(storeCategoryCheck === false) {
		msg.style.color = 'red';
		msg.innerHTML = '점포 카테고리는 2글자 이상, 10글자 이하여야 합니다.';
		event.preventDefault();
	}
	
    if (isChecked === false) {
		msg.style.color = 'red';
		msg.innerHTML = '1가지 이상 결제방식을 선택해야 합니다.';
		event.preventDefault();
    }
    
    if(storePhotoCheck === false) {
		msg.style.color = 'red';
		msg.innerHTML = '점포 등록 시, 사진은 필수로 등록하셔야 합니다!';
		event.preventDefault();
	}
}
    
     
    
    
    
function init() {
	document.querySelector('.store__button').addEventListener('click', insertAndUpdateButton);
}
    
  
    
window.addEventListener('load', init);    