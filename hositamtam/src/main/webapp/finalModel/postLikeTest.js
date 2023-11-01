






function init() {
	
		const memberInfo = window.localStorage.getItem('memberInfo');
   
		if (memberInfo) {
		    const member = JSON.parse(memberInfo);
		    //console.log('id:', member.id);
		    //console.log('nickname:', member.nickname);
		    
		    currentId = member.id;
		    currentNickname = member.nickname;
		} else {
		    console.log('로컬 스토리지에 멤버 정보가 없습니다.');
		}
	




		document.querySelector('#post').addEventListener.('click', function(event) {
       		if (event.target.className === 'postLikeClass') {
			
	            let currentPno = event.target.getAttribute('id');
				xhr.onreadystatechange = postLikeAjaxHandler;
				
		        let param = '?command=updateLike&pno=' + currentPno + '&id=' + currentId;
		        xhr.open('GET', 'toAjaxController.jsp' + param, true);
		        xhr.send();
	        }
		});

}


window.addEventListener('load', init);