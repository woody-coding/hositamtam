let xhr = new XMLHttpRequest();



















function init() {
	document.querySelector('#plikecountUpdate').addEventListener('click', function() {
		xhr.onreadystatechange = storeAjaxHandler;
		
	    let param = '?command=getStoreInMarket&mno=' + currentMno;
	    xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
	    xhr.send();		
	})
}








window.addEventListener('load', init);