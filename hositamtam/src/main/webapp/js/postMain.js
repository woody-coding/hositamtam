// $('#writePost').on('click', function(){
//     $('.black-bg').addClass('show-modal');
//   });
// $('#close').on('click',function(){

// $('.black-bg').removeClass('show-modal');
// });

//   $('#postModify').on('click', function(){
//   $('.black-bg-modify').addClass('show-modal');
// });
// $('#close').on('click',function(){
//     $('.black-bg-modify').removeClass('show-modal');
//   });


//   var count = 0;

//   $('#btnDown').on('click', function(){
//   $('.mkNameList').addClass('show-mkNameList');
// });

      
// $('#btnUp').on('click', function(){
//   $('.mkNameList').removeClass('show-mkNameList');
// });

// //for문 사용
// for (let i = 0; i < $(".tab-button").length; i++) {
//   $(".tab-button")
//     .eq(i)
//     .on("click", function () {
//       $(".tab-button").removeClass("orange");
//       $(".tab-button").eq(i).addClass("orange");
//       $(".tab-content").removeClass("show");
//       $(".tab-content").eq(i).addClass("show");
//     });
// }
// $(".moreContents").on("click", function() {
//   $(".commentBox").addClass("show-more");
//   $(".commentList").addClass("show-more");

// });

// $("#delete").on("click", function() {
//   confirm("정말 삭제하시겠습니까?")
// })
document.getElementById('writePost').addEventListener('click', function () {
  document.querySelector('.black-bg').classList.add('show-modal');
});

document.getElementById('close').addEventListener('click', function () {
  document.querySelector('.black-bg').classList.remove('show-modal');
});

document.getElementById('postModify').addEventListener('click', function () {
  document.querySelector('.black-bg-modify').classList.add('show-modal');
});

document.getElementById('close').addEventListener('click', function () {
  document.querySelector('.black-bg-modify').classList.remove('show-modal');
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
