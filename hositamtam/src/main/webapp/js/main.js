// Header 섹션을 아래로 스크롤시 투명하게 처리
document.addEventListener("DOMContentLoaded", function () {
  var header = document.querySelector(".mainHeader");

  header.style.opacity = 1;

  window.addEventListener("scroll", function () {
    if (window.scrollY > 10) {
      header.style.opacity = 0;
    } else {
      header.style.opacity = 1;
    }
  });
});

// 페이지 스크롤
var mHtml = $("html");
var page = 1;
var lastPage = $(".section").length;

mHtml.animate({scrollTop : 0},10);

$(window).on("wheel", function(e) {
if(mHtml.is(":animated")) return;
if(e.originalEvent.deltaY > 0) {
  if(page == lastPage) return;
  page++;
} else if(e.originalEvent.deltaY < 0) {
  if(page == 1) return;
  page--;
}
var posTop =(page-1) * $(window).height();
mHtml.animate({scrollTop : posTop});
})
// 

const swiper = new Swiper('.swiper', {
  // Optional parameters
  direction: 'horizontal',
  loop: true,


  // Navigation arrows
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },

  
});



/*var count = 0;
function dropDownHandler() {
  if(count % 2 == 1){
  document.querySelector('.dropDownMenu').classList.add('show-dropDown');
  }
  else {
    document.querySelector('.dropDownMenu').classList.remove('show-dropDown');

  }
  count++;

}*/











function displayHandler() {
  document.querySelector('.listCategory').classList.add('show-list');

}
function displayCloseHandler() {
  document.querySelector('.listCategory').classList.remove('show-list');

}
document.addEventListener("DOMContentLoaded", function () {
  var video = document.getElementById("myVideo");

  // 시작 시간 (초)
  var startTime = 5.5;

  // 종료 시간 (초)
  var endTime = 155;

  // 비디오가 메타데이터를 로드하면 설정합니다.
  video.addEventListener("loadedmetadata", function () {
      video.currentTime = startTime;
  });

  // 비디오가 재생 중일 때 종료 시간을 확인하고 재생 중지합니다.
  video.addEventListener("timeupdate", function () {
      if (video.currentTime >= endTime) {
          video.pause();
          video.currentTime = startTime; // 다시 시작 시간으로 이동
          video.play(); // 자동 재생
      }
  });
});



function goPostHandler() {
	window.location.href = '/finalProject/views/post';
}


   function init() {
document.querySelector('#mkList').addEventListener("click", displayHandler);
document.querySelector('.close').addEventListener("click", displayCloseHandler);
//document.querySelector('.btnDrop').addEventListener('click',dropDownHandler);
document.querySelector('#goPost').addEventListener('click', goPostHandler);
}
 
window.addEventListener('load', init);

