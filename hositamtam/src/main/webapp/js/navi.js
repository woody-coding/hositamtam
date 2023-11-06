
function displayHandler() {
  document.querySelector('.listCategory').classList.add('show-list');

}
function displayCloseHandler() {
  document.querySelector('.listCategory').classList.remove('show-list');

}
   function init() {
	    // 검색어 입력 필드에서 Enter 키를 눌렀을 때 검색 실행
	        document.getElementById("searchInput").addEventListener("keyup", function (event) {
	            if (event.key === "Enter") { // Enter 키가 눌렸을 때
	            
	                const searchInput = document.getElementById("searchInput").value;
	                const encodedSearchInput = encodeURIComponent(searchInput);
	                const newURL = "market.jsp?command=search&query=" + encodedSearchInput;
	                window.location.href = newURL;
	            }
	        });
document.querySelector('#categoryOpen').addEventListener("click", displayHandler);
document.querySelector('.close').addEventListener("click", displayCloseHandler);
}
 
window.addEventListener('load', init);


	