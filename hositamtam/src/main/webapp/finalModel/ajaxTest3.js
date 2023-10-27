let xhr = new XMLHttpRequest();
let currentmno = '';


// 해당 시장의 글을 받아와서 동적으로 구현
function postAjaxHandler() {
    if (xhr.readyState === 4 && xhr.status === 200) {
        let allPost = JSON.parse(xhr.responseText);
        console.log(xhr.responseText);

        let posts = '';

        if (allPost.length === 0) {
            posts = '글이 없습니다!';
        } 
        
        for (let i in allPost) {
            posts += '<h4>글번호: ' + allPost[i].pno + ', 내용: ' + allPost[i].pcontent + ', 이미지: ' + allPost[i].pphoto +
            ', 닉네임: ' + allPost[i].nickname + ', 제목: ' + allPost[i].ptitle +
            ', 카테고리: ' + allPost[i].pcategory + ', 작성시간: ' + allPost[i].pregdate +
            ', 좋아요: ' + allPost[i].plikecount +
            ', 댓글수: ' + allPost[i].countcomments + '</h4>';
        }
        
        document.querySelector('#pcontent').innerHTML = posts;
	}
}

// --------------------------------------------------------------------






// 해당 시장의 '인기글'을 받아와서 동적으로 구현
function hotPostAjaxHandler() {
    if (xhr.readyState === 4 && xhr.status === 200) {
        let allPost = JSON.parse(xhr.responseText);
        console.log(xhr.responseText);

        let posts = '';

        if (allPost.length === 0) {
            posts = '글이 없습니다!';
        }

        for (let i in allPost) {
            posts += '<h4>글번호: ' + allPost[i].pno + ', 내용: ' + allPost[i].pcontent + ', 이미지: ' + allPost[i].pphoto +
            ', 닉네임: ' + allPost[i].nickname + ', 제목: ' + allPost[i].ptitle +
            ', 작성시간: ' + allPost[i].pregdate + ', 좋아요: ' + allPost[i].plikecount + 
            ', 댓글수: ' + allPost[i].countcomments + '</h4>';
        }

        document.querySelector('#pcontent').innerHTML = posts;
    }
}







// '인기글' 관련 데이터 요청
function hotHandler() {
    xhr.onload = hotPostAjaxHandler;

    let param = '?command=hot&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}





// ----------------------------------------------------------------------


// 해당 mno에 해당되는 시장의 모든 글 요청 : 클릭 이벤트가 발생한 버튼으로 부터 id값 즉, mno를 받아와서 파라미터로 담아서 컨트롤러로 보내기
function getPostHandler() {
    xhr.addEventListener('load', postAjaxHandler); // 수정: xhr.onload 대신 xhr.addEventListener 사용

    //currentmno = event.target.getAttribute('id'); // 수정: event.target 대신 this 사용

    let param = '?command=getPost&mno=' + currentmno;
    xhr.open('GET', 'toAjaxController.jsp' + param, true);
    xhr.send();
}



// mno(시장 고유번호), mname(시장이름)을 받아서 시장 별 버튼을 동적으로 생성 ==> 그 버튼을 클릭했을 때 클릭 이벤트 발생 ==> getPostHandler에서 처리
function marketAjaxHandler() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
        let allMarket = JSON.parse(xhr.responseText);

        let markets = '';

        for (let i in allMarket) {
            markets += '<button id=' + allMarket[i].mno + ' class="markets">' + allMarket[i].mname + '</button>';
        }

        document.querySelector('#markets').innerHTML = markets;
        
        
		
		
		
		// 인기글 등 글 카테고리 버튼을 클릭했을 때의 이벤트 정의는 여기서 하나!!???
		document.querySelector('#hot').addEventListener('click', hotHandler);
	}
}



// mno(시장 고유번호), mname(시장이름) 요청
function whatMarketHandler() {
	xhr.onload = marketAjaxHandler;
	
	let param = '?command=getMarket';
	xhr.open('GET', 'toAjaxController.jsp' + param, true);
	xhr.send();
}

function buttonHandler(event) {
	currentmno = event.target.getAttribute('id');
	getPostHandler();
}


function init() {
	document.querySelector('#whatMarket').addEventListener('click', whatMarketHandler);
	document.querySelector('#markets').addEventListener('click', buttonHandler);
}

window.addEventListener('load', init);
