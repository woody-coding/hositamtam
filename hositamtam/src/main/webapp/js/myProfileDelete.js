document.addEventListener("DOMContentLoaded", function () {
  var deleteButton = document.querySelector(".myprofile__delete__button");

  deleteButton.addEventListener("click", function () {
    var confirmDelete = confirm("정말 회원 탈퇴를 진행하시겠습니까?");

    if (confirmDelete) {
      // '예'를 눌렀을 때 처리 방식
    }
  });
});
