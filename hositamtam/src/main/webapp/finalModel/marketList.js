let xhr = new XMLHttpRequest();
let locations = [];



// 지도 생성
var mapOptions = {
    center: new naver.maps.LatLng(35.21003, 129.0689),
    zoom: 11,
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
        
        //alert(decodeURIComponent(keywordParam));
        
        xhr.onreadystatechange = marketAjaxHandler;
        
        let param = '?command=getMarketListBySearch&keyword=' + currentKeyword;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.send();
    }
    else if(catenoParam) {
		// 카테고리(취급품목) 클릭했을 때 해당 시장 정보들 비동기 요청
		//alert(location.search.substring(8).trim());
		let currentCateno = catenoParam;
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
					            '<div class="col-9">' +
					                '<p id="mkName">' + allMarketList[i].mname + '</p>' +
					                '<p id="mkaddr">' + allMarketList[i].maddr + '</p>' +
					                '<span>화장실 ' + allMarketList[i].mtoilet + '</span>주차장 ' + allMarketList[i].mparking + '</span>' +
					            '</div>' +
					        '</div>'
		}

		
		
		document.querySelector('#marketContent').innerHTML = marketContents;
        
        
        
        
        
        
        for (let i in allMarketList) {
			const marketErrorMsg = allMarketList[i].marketErrorMsg;
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
				marketErrorMsg: marketErrorMsg,
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




// 지도 위에 마커를 표시하는 함수
function showMarkers() {
    for (var i = 0; i < locations.length; i++) {
        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(locations[i].mlat, locations[i].mlng)
        });

        var infowindow = new naver.maps.InfoWindow({
            content: '<div>' + locations[i].mname + '</div><a href="storeList.jsp?mno=' + locations[i].mno + '">이동하기!</a>'
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




// 페이지 로딩 시 초기화 함수 호출
window.addEventListener('load', init);
