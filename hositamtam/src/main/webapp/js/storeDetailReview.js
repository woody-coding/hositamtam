// 별점 메기기
document.addEventListener("DOMContentLoaded", function () {
  var stars = document.querySelectorAll(".review__star i");

  stars.forEach(function (star, index) {
    star.addEventListener("click", function () {
      // n번째 별이 클릭됐는지?
      var clickedIndex = index;

      // n번째 별과 그 이전 별 색 변경 !
      for (var i = 0; i <= clickedIndex; i++) {
        stars[i].style.color = "#FCD53F";
      }

      // 재클릭했을 때
      for (var i = clickedIndex + 1; i < stars.length; i++) {
        stars[i].style.color = "";
      }
    });
  });
});

// 별점 누르면 리뷰 창 나오게
document.addEventListener("DOMContentLoaded", function () {
  var reviewStars = document.querySelectorAll(".review__star");
  var reviewForm = document.getElementById("review__form");

  reviewStars.forEach(function (star) {
    star.addEventListener("click", function () {
      reviewForm.style.display = "block";
    });
  });
});
