// 페이지 스크롤
var mHtml = $("html");
var page = 1;


mHtml.animate({scrollTop : 0},10);

$(window).on("wheel", function(e) {
if(mHtml.is(":animated")) return;
if(e.originalEvent.deltaY > 0) {
  if(page == 4) return;
  page++;
} else if(e.originalEvent.deltaY < 0) {
  if(page == 1) return;
  page--;
}
var posTop =(page-1) * $(window).height();
mHtml.animate({scrollTop : posTop});
})
// 
function displayHandler() {
  document.querySelector('.listCategory').classList.add('show-list');

}
function displayCloseHandler() {
  document.querySelector('.listCategory').classList.remove('show-list');

}

   function init() {
document.querySelector('#mkList').addEventListener("click", displayHandler);
document.querySelector('.close').addEventListener("click", displayCloseHandler);
  
}

window.addEventListener('load', init);

