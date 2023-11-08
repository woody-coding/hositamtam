let xhr = new XMLHttpRequest();
let xhr2 = new XMLHttpRequest();
let currentmno = '';
let currentpno = '';
let currentid;
let currentnickname;

// 좋아요 버튼 상태를 나타내는 변수 추가
let likeButtonClickedMap = {};






// 해당 시장의 글을 받아와서 동적으로 구현	(그냥 최신순, 인기글, 시장질문, 사건사고, 일상, 실종/분실)
function postAjaxHandler() {
    if (xhr.readyState === 4 && xhr.status === 200) {
        let allPost = JSON.parse(xhr.responseText);
        console.log(xhr.responseText);

        let posts = '';

        if (allPost.length === 0) {
            posts = '글이 없습니다!';
        } 
        
        for (let i in allPost) {
            posts += '<div id="'+ allPost[i].pno +'" class="personalPcontent">닉네임: ' + allPost[i].nickname + ', 카테고리: ' + allPost[i].pcategory + ', 제목: ' + allPost[i].ptitle + ', 내용: ' + allPost[i].pcontent + 
            ', 이미지: ' + allPost[i].pphoto + ', 작성시간: ' + allPost[i].pregdate + ', 좋아요: ' + allPost[i].plikecount + 
            ', 댓글수: ' + allPost[i].countcomments + '</div>';
        }
        
        document.querySelector('#pcontent').innerHTML = posts;
	}
}



// '인기글' 관련 데이터 요청
function hotHandler() {
    xhr.onload = postAjaxHandler;

    let param = '?command=hot&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}


// '시장질문' 관련 데이터 요청
function queHandler() {
    xhr.onload = postAjaxHandler;

    let param = '?command=que&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}


// '사건사고' 관련 데이터 요청
function accHandler() {
    xhr.onload = postAjaxHandler;

    let param = '?command=acc&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}


// '일상' 관련 데이터 요청
function dayHandler() {
    xhr.onload = postAjaxHandler;

    let param = '?command=day&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}


// '실종/분실' 관련 데이터 요청
function lostHandler() {
    xhr.onload = postAjaxHandler;

    let param = '?command=lost&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}



// 해당 mno에 해당되는 시장의 모든 글 최신순으로 요청 				=>			클릭 이벤트가 발생한 버튼으로 부터 id값 즉, mno를 받아와서 파라미터로 담아서 컨트롤러로 보내기
function getPostHandler() {
    xhr.onload = postAjaxHandler;

    let param = '?command=getPost&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}



// mno(시장 고유번호), mname(시장이름)을 받아서 시장 별 버튼을 동적으로 생성 
function marketAjaxHandler() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
        let allMarket = JSON.parse(xhr.responseText);

        let markets = '';

        for (let i in allMarket) {
            markets += '<button id=' + allMarket[i].mno + ' class="markets">' + allMarket[i].mname + '</button>';
        }

        document.querySelector('#markets').innerHTML = markets;
	}
}



/*
function increaseLikeCountHandler() {
		if (xhr.readyState === 4 && xhr.status === 200) {
        let getPlikecount = JSON.parse(xhr.responseText);

        let plikecounts = '';
        for (let i in getPlikecount) {
            plikecounts += '<button id=' + getPlikecount[i].pno + '>' + getPlikecount[i].plikecount + '</button>';
        }

        document.querySelector('#pcontent').innerHTML = plikecounts;
	}
}
*/


function increaseLikeCountHandler() {
        if (xhr.readyState === 4 && xhr.status === 200) {
        let getPlikecount = JSON.parse(xhr.responseText);

        let plikecounts = '';
        for (let i in getPlikecount) {
            plikecounts += '<button id=' + getPlikecount[i].pno + ' class="plikecount-button">' + getPlikecount[i].plikecount + '</button>';
        }

        document.querySelector('#pcontent').innerHTML = plikecounts;
	console.log(pno);
        likeButtonClickedMap[pno] = false; // 클릭 완료
    }
}




function increaseLikeCount(event) {
	pno = event.target.getAttribute("id");
	
    if (!likeButtonClickedMap[pno]) {
        likeButtonClickedMap[pno] = true; // 해당 pno 게시물에 대한 클릭 중

        xhr.onload = increaseLikeCountHandler;

        let param = '?command=updateLike&pno=' + pno + '&id=' + currentid;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.send();
    }
}




function commentModifyHandler() {
	
}



function commentDeleteHandler() {
	
}




function commentDelete(event) {
		let cno = event.target.getAttribute("cno");
	
	    xhr.onload = commentDeleteHandler;

        let param = '?command=commentDelete&cno=' + cno + '&id=' + currentid;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.send();
}




// 해당 pno에 해당되는 [글 전체 내용 + 댓글들] 동적 구현
function getCommentsAndPnoPostHandler() {
    if (xhr.readyState === 4 && xhr.status === 200 && xhr2.readyState === 4 && xhr2.status === 200) {
        let allPost = JSON.parse(xhr.responseText);
        let allComments = JSON.parse(xhr2.responseText);
		//console.log(xhr.responseText);
		//console.log(xhr2.responseText);
		
        // 개별 게시물 정보를 가져와서 HTML 형식으로 가공
        let postsHTML = '';
    
        for (let i in allPost) {
            postsHTML += '<div>닉네임: ' + allPost[i].nickname + ', 카테고리: ' + allPost[i].pcategory + ', 제목: ' + allPost[i].ptitle + ', 내용: ' + allPost[i].pcontent + 
            ', 이미지: ' + allPost[i].pphoto + ', 작성시간: ' + allPost[i].pregdate + '<button class="plikecounts" id="'+  +'">좋아요' + allPost[i].plikecount +'</button>' + 
            ', 댓글수: ' + allPost[i].countcomments + '</div>';
        }
        
        // 댓글 정보를 가져와서 HTML 형식으로 가공
        let commentsHTML = '<hr/><h4>댓글</h4><br/>';

        for (let i in allComments) {
            commentsHTML += '<div id="'+ allComments[i].cno +'" class="personalCcontent">닉네임: ' + allComments[i].cnickname + ', 내용: ' +
                allComments[i].ccontent + ', 작성시간: ' + allComments[i].cregdate;
                
                
                if(currentnickname === allComments[i].cnickname) {
					commentsHTML += '<button class="cmodify" id="'+ allComments[i].cno +'">수정</button><button class="cdelete" id="'+ allComments[i].cno +'">삭제</button>'
				}
 
                commentsHTML += '</div>';

        }

        // 개별 게시물과 댓글을 #pcontent에 함께 표시
        document.querySelector('#pcontent').innerHTML = postsHTML + commentsHTML;
        
        
        
        // 게시물의 "좋아요" 버튼에 이벤트 리스너 추가
        document.querySelector('.plikecount-button').addEventListener('click', increaseLikeCount);
        document.querySelector('.cdelete').addEventListener('click', commentDelete);
    }
}










// 해당 pno에 해당되는 댓글들 내용 요청
function getComments() {
	xhr2.onload = getCommentsAndPnoPostHandler;

    let param = '?command=getComments&pno=' + currentpno;
    xhr2.open('GET', 'toAjaxController.jsp' + param, true);
    xhr2.send();
}

// 해당 pno에 해당되는 글 전체 내용 요청
function getPnoPost() {
	xhr.onload = getCommentsAndPnoPostHandler;

    let param = '?command=getPnoPost&pno=' + currentpno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}




// mno(시장 고유번호), mname(시장이름) 요청
function whatMarketHandler() {
	xhr.onload = marketAjaxHandler;
	
	let param = '?command=getMarket';
	xhr.open('GET', 'toAjaxController.jsp' + param, true);
	xhr.send();
}



// 동적으로 생성된 시장 버튼을 클릭했을 때		==>		getPostHandler에서 클릭 이벤트 처리
function buttonHandler(event) {			
	currentmno = event.target.getAttribute('id');
	getPostHandler();
	
	document.querySelector('#hot').addEventListener('click', hotHandler);
	document.querySelector('#que').addEventListener('click', queHandler);
	document.querySelector('#acc').addEventListener('click', accHandler);
	document.querySelector('#day').addEventListener('click', dayHandler);
	document.querySelector('#lost').addEventListener('click', lostHandler);
}


function personalPcontentHandler(event) {
	currentpno = event.target.getAttribute('id');
	getComments();
	getPnoPost();
	
	if (event.target.className === 'cmodify') {
	    let cno = event.target.getAttribute("id");
	
	    xhr.onload = commentModifyHandler;
	
	    let param = '?command=commentModify&cno=' + cno + '&id=' + currentid;
	    xhr.open('GET', 'toAjaxController.jsp' + param, true);
	    xhr.send();
	}
	
	
	if (event.target.className === 'cdelete') {
	    let cno = event.target.getAttribute("id");
	
	    xhr.onload = commentDeleteHandler;
	
	    let param = '?command=commentDelete&cno=' + cno + '&id=' + currentid;
	    xhr.open('GET', 'toAjaxController.jsp' + param, true);
	    xhr.send();
	}
}


function init() {
	
	
	const memberInfo = window.localStorage.getItem('memberInfo');
   
	if (memberInfo) {
	    const member = JSON.parse(memberInfo);
	    console.log('id:', member.id);
	    console.log('nickname:', member.nickname);
	    
	    currentid = member.id;
	    currentnickname = member.nickname;
	} else {
	    console.log('로컬 스토리지에 멤버 정보가 없습니다.');
	}
	
	
	
	document.querySelector('#whatMarket').addEventListener('click', whatMarketHandler);
	document.querySelector('#markets').addEventListener('click', buttonHandler);
	document.querySelector('#pcontent').addEventListener('click', personalPcontentHandler);
}

window.addEventListener('load', init);