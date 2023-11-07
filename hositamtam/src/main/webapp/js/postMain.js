var count = 0;
function dropDownHandler() {
  if(count % 2 == 1){
  document.querySelector('.mkNameList').classList.add('show-mkNameList');
  }
  else {
    document.querySelector('.mkNameList').classList.remove('show-mkNameList');

  }
  count++;

}


   function init() {

document.querySelector("#whatMarket").addEventListener("click",dropDownHandler);  
}
 
window.addEventListener('load', init);

