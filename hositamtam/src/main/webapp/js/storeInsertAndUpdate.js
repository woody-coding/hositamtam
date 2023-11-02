// 버튼 클릭 시 배경색 변경
// document.addEventListener("DOMContentLoaded", function () {
//   var buttons = document.querySelectorAll(
//     ".store__type__button, .store__payment__button, .store__category__button, .store__category__button2"
//   );

//   buttons.forEach(function (button) {
//     button.addEventListener("click", function () {
//       var isActive = button.classList.contains("active");

//       buttons.forEach(function (btn) {
//         btn.classList.remove("active");
//       });

//       if (!isActive) {
//         button.classList.add("active");
//       }
//     });
//   });
// });

// 사진 파일 미리보기
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
