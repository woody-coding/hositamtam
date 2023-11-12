// Header 섹션을 아래로 스크롤시 투명하게 처리
document.addEventListener("DOMContentLoaded", function () {
  var header = document.querySelector(".mainHeader");

  header.style.opacity = 1;

  window.addEventListener("scroll", function () {
    if (window.scrollY > 300) {
      header.style.opacity = 0;
    } else {
      header.style.opacity = 1;
    }
  });
});