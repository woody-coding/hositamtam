let xhr = new XMLHttpRequest();
let locations = [];


// 지도 생성
var mapOptions = {  			//35.21003 129.0689
    center: new naver.maps.LatLng(35.17132, 129.0666),
    zoom: 12,
    mapTypeControl: true,
    mapTypeControlOptions: {
        style: naver.maps.MapTypeControlStyle.BUTTON,
        position: naver.maps.Position.TOP_RIGHT
    }
};



var map = new naver.maps.Map('map', mapOptions);




// 지도 위에 표시되는 마커 배열
var markers = [];




// 초기화 함수
function init() {
	
	// 로그인되어 있는지 아닌지
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
	
	
	
	
    // URL에서 검색어 파라미터 추출
    const searchParams = new URLSearchParams(location.search);
    const keywordParam = searchParams.get('query');
    const catenoParam = searchParams.get('cateno');

    if (keywordParam) {

        let currentKeyword = decodeURIComponent(keywordParam);
        
        document.querySelector('#mkSearchName').innerHTML = "'"+ currentKeyword + "' (으)로 검색된 결과입니다.";	
        
        
        xhr.onreadystatechange = marketAjaxHandler;
        
        let param = '?command=getMarketListBySearch&keyword=' + currentKeyword;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.send();
    }
    
    else if(catenoParam) {
		// 카테고리(취급품목) 클릭했을 때 해당 시장 정보들 비동기 요청
		//alert(location.search.substring(8).trim());
		let currentCateno = catenoParam;
		let currentCate = '';
		
		if(currentCateno === '1') {
			currentCate = '농산물';
		} else if(currentCateno === '2') {
			currentCate = '음식점';
		} else if(currentCateno === '3') {
			currentCate = '가공식품';
		} else if(currentCateno === '4') {
			currentCate = '수산물';
		} else if(currentCateno === '5') {
			currentCate = '축산물';
		} else if(currentCateno === '6') {
			currentCate = '가정용품';
		} else if(currentCateno === '7') {
			currentCate = '의류';
		} else if(currentCateno === '8') {
			currentCate = '신발';
		} else if(currentCateno === '9') {
			currentCate = '기타';
		}
		
		document.querySelector('#howGetMarket').innerHTML = "취급품목 '"+ currentCate + "'에 특화된 전통시장 목록 입니다.";
		
		
		
		xhr.onreadystatechange = marketAjaxHandler;
		
	    let param = '?command=getMarketListByItem&cateno=' + currentCateno;
	    xhr.open('GET', 'toAjaxController.jsp' + param, true);
	    xhr.send();
	}

	
	
	




/*    
    const marketCategory = window.localStorage.getItem('marketCategory');
    const marketSearchKeyword = window.localStorage.getItem('marketSearchKeyword');
		
		if (marketCategory) {
        const marketCateno = JSON.parse(marketCategory);
        console.log('cateno:', marketCateno.cateno);
        let currentCateno = marketCateno.cateno;

        let param = '?command=getMarketListByItem&cateno=' + currentCateno;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.onreadystatechange = marketAjaxHandler;
        xhr.send();
        
        // 로컬 스토리지에서 cateno 값 삭제
        window.localStorage.removeItem('marketCategory');
        
    } 
    
    else if (marketSearchKeyword) {
        const marketKeyword = JSON.parse(marketSearchKeyword);
        console.log('keyword:', marketKeyword.keyword);
        let currentKeyword = marketKeyword.keyword;

        let param = '?command=getMarketListBySearch&keyword=' + currentKeyword;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.onreadystatechange = marketAjaxHandler;
        xhr.send();
        
        // 로컬 스토리지에서 keyword 값 삭제
        window.localStorage.removeItem('marketSearchKeyword');
    }
*/
}



// marketAjaxHandler 함수
function marketAjaxHandler() {
    if (xhr.readyState === 4 && xhr.status === 200) {
        // 기존 마커들을 모두 제거
        removeMarker();

        const allMarketList = JSON.parse(xhr.responseText);
        
        
        
        
        
        // marketErrorMsg 값(내용)을 가져옵니다.
        const marketErrorMsg = allMarketList[0].marketErrorMsg;
        
        // 에러 메시지가 있는지 확인하고 화면에 표시
        if (marketErrorMsg) {
			
			// 기존 jsp에 있던 div 태그들 숨기기
			document.querySelector("#map").style.display = "none";
            document.querySelector("#marketContent").style.display = "none";
			
            // 에러 메시지를 표시할 요소 선택
            document.querySelector("#marketErrorMsg").innerHTML = marketErrorMsg;
            
            // 마커 및 기존 컨텐츠를 지우기
            removeMarker();
        }
        
        
        
        
        
        
        
		// 지도 생성
		mapOptions = {  			//35.21003 129.0689
		    center: new naver.maps.LatLng(35.17132, 129.0666),
		    zoom: 12,
		    mapTypeControl: true,
		    mapTypeControlOptions: {
		        style: naver.maps.MapTypeControlStyle.BUTTON,
		        position: naver.maps.Position.TOP_RIGHT
		    }
		};
		
		
		map = new naver.maps.Map('map', mapOptions);





        
        let marketContents ='';
        
        
        if (allMarketList.length === 0) {
            marketContents = '시장 정보가 없습니다!';
        } 
        
        
		for(let i=0; i < allMarketList.length; i++) {
			
			if(allMarketList[i].mtel === null) {
				allMarketList[i].mtel = '';
			}
			
			//marketContents += '<div id="'+ allMarketList[i].mno +'" class="personalMcontent">' + allMarketList[i].mname + '|' + allMarketList[i].mtype + 
			//'|' + allMarketList[i].maddr + '| 화장실 여부: ' + allMarketList[i].mtoilet + '| 주차가능 여부: ' + allMarketList[i].mparking + '|' + allMarketList[i].mtel + '</div>';
			
			marketContents += '<div id="'+ allMarketList[i].mno +'" class="personalMcontent">' +
					            '<div class="mkcontainer" class="row">' +
					            '<div class="col-9">' +
					                '<p id="mkName">' + allMarketList[i].mname + '</p>' +
					                '<p id="mkaddr">' + allMarketList[i].maddr + '</p>' +
					                '<span>화장실 ' + allMarketList[i].mtoilet + '</span>주차장 ' + allMarketList[i].mparking + '</span>' +
					            '</div>' +
					        '</div></div>'
		}

		document.querySelector('#marketContent').innerHTML = marketContents;
        
        
        
        
        
        
        for (let i in allMarketList) {
            const mno = parseFloat(allMarketList[i].mno);
            const mname = allMarketList[i].mname;
            const mtype = allMarketList[i].mtype;
            const maddr = allMarketList[i].maddr;
            const mtoilet = allMarketList[i].mtoilet;
            const mparking = allMarketList[i].mparking;
            const mtel = allMarketList[i].mtel;
            const mupdateday = allMarketList[i].mupdateday;
            const mlat = parseFloat(allMarketList[i].mlat);
            const mlng = parseFloat(allMarketList[i].mlng);

            const mlocation = {
                mno: mno,
                mname: mname,
                mtype: mtype,
                maddr: maddr,
                mlat: mlat,
                mlng: mlng,
                mtoilet: mtoilet,
                mparking: mparking,
                mtel: mtel,
                mupdateday: mupdateday
            };

            locations.push(mlocation);
        }

        // 마커를 다시 생성하고 표시
        showMarkers();
    }
}








	// 현재 페이지에 접속한 사용자가 로그인되어 있는지 아닌지
	const memberIdNick = window.localStorage.getItem('memberIdNickname');
   
	if (memberIdNick) {
	    const member = JSON.parse(memberIdNick);
	    console.log('id:', member.id);
	    console.log('nickname:', member.nickname);
	} 
	else {
	    console.log('로컬 스토리지에 멤버 정보가 없습니다.');
	}









// 초기화 함수
function init() {

    // URL에서 검색어 파라미터 추출
    const searchParams = new URLSearchParams(location.search);
    const keywordParam = searchParams.get('query');
    const catenoParam = searchParams.get('cateno');

    if (keywordParam) {

        let currentKeyword = decodeURIComponent(keywordParam);
        
        document.querySelector('#howGetMarket').innerHTML = "'"+ currentKeyword + "' (으)로 검색된 결과입니다.";	
        
        
        xhr.onreadystatechange = marketAjaxHandler;
        
        let param = '?command=getMarketListBySearch&keyword=' + currentKeyword;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.send();
    }
    
    else if(catenoParam) {
		// 카테고리(취급품목) 클릭했을 때 해당 시장 정보들 비동기 요청
		//alert(location.search.substring(8).trim());
		let currentCateno = catenoParam;
		let currentCate = '';
		
		if(currentCateno === '1') {
			currentCate = '농산물';
		} else if(currentCateno === '2') {
			currentCate = '음식점';
		} else if(currentCateno === '3') {
			currentCate = '가공식품';
		} else if(currentCateno === '4') {
			currentCate = '수산물';
		} else if(currentCateno === '5') {
			currentCate = '축산물';
		} else if(currentCateno === '6') {
			currentCate = '가정용품';
		} else if(currentCateno === '7') {
			currentCate = '의류';
		} else if(currentCateno === '8') {
			currentCate = '신발';
		} else if(currentCateno === '9') {
			currentCate = '기타';
		}
		
		document.querySelector('#howGetMarket').innerHTML = "'" + currentCate + "'에 특화된 전통시장 목록 입니다.";
		
		
		
		xhr.onreadystatechange = marketAjaxHandler;
		
	    let param = '?command=getMarketListByItem&cateno=' + currentCateno;
	    xhr.open('GET', 'toAjaxController.jsp' + param, true);
	    xhr.send();
	}

}









// 지도 위에 마커를 표시하는 함수
function showMarkers() {
    for (var i = 0; i < locations.length; i++) {
        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(locations[i].mlat, locations[i].mlng)
        });

        var infowindow = new naver.maps.InfoWindow({
 content: '<div class="goMarket"><div class="up">' + locations[i].place + '</div><div class="down"><a href="/apiTest/naverMapApi.html">이동하기!</a></div></div>', 
                      maxWidth: 300,
                    // backgroundColor: "#eee",
                    // borderColor: "#2db400",
                    borderWidth: 0,
                    disableAnchor: true, 
                    //정보창 꼬리 제거 
                    // anchorSize: new naver.maps.Size(30, 30),
                    // anchorSkew: true,
                    // anchorColor: "#eee",
                    // pixelOffset: new naver.maps.Point(20, -20)
        });

        (function (marker, infowindow) {
            naver.maps.Event.addListener(marker, "click", function (e) {
                infowindow.open(map, marker);
            });

            naver.maps.Event.addListener(map, "click", function (mouseEvent) {
                infowindow.close();
            });
        })(marker, infowindow);

        markers.push(marker);
    }
}




// 지도 위에 표시되고 있는 마커를 모두 제거하는 함수
function removeMarker() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}




window.addEventListener('load', init);
