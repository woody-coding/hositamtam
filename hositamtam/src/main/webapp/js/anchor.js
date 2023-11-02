// 마이페이지 사이드 앵커
document.addEventListener("DOMContentLoaded", function () {
  var storeAnc = document.querySelector(".myPage__store");
  var postAnc = document.querySelector(".myPage__post");
  var reviewAnc = document.querySelector(".myPage__review");
  var storelikeAnc = document.querySelector(".myPage__storeLike");
  var arrowUpAnc = document.querySelector(".myPage__arrow-up");

  storeAnc.style.opacity = 0;
  postAnc.style.opacity = 0;
  reviewAnc.style.opacity = 0;
  storelikeAnc.style.opacity = 0;
  arrowUpAnc.style.opacity = 0;

  window.addEventListener("scroll", function () {
    if (window.scrollY > 300) {
      storeAnc.style.opacity = 1;
      postAnc.style.opacity = 1;
      reviewAnc.style.opacity = 1;
      storelikeAnc.style.opacity = 1;
      arrowUpAnc.style.opacity = 1;
    } else {
      storeAnc.style.opacity = 0;
      postAnc.style.opacity = 0;
      reviewAnc.style.opacity = 0;
      storelikeAnc.style.opacity = 0;
      arrowUpAnc.style.opacity = 0;
    }
  });
});

// 점포상세페이지 화살표
document.addEventListener("DOMContentLoaded", function () {
  var arrowUp = document.querySelector(".storeDetail__arrow-up");

  arrowUp.style.opacity = 0;

  window.addEventListener("scroll", function () {
    if (window.scrollY > 100) {
      arrowUp.style.opacity = 1;
    } else {
      arrowUp.style.opacity = 0;
    }
  });
});
