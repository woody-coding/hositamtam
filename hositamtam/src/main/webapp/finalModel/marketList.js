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
    
    document.querySelector('#map').addEventListener('click', function(event) {
			        if (event.target.className === 'mnoToStoreList') {
	            const mno = event.target.getAttribute('id');
	            const marketmno = { mno: mno };
	            const marketMno = JSON.stringify(marketmno);
	            window.localStorage.setItem('marketMno', marketMno);
	            
	            const marketMnoData = window.localStorage.getItem('marketMno');
	            console.log(marketMnoData);
	        }
	});
    

}

// marketAjaxHandler 함수
function marketAjaxHandler() {
    if (xhr.readyState === 4 && xhr.status === 200) {
        // 기존 마커들을 모두 제거
        removeMarker();

        const allMarketList = JSON.parse(xhr.responseText);
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

            const location = {
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

            locations.push(location);
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
            content: '<div>' + locations[i].mname + '</div><a class="mnoToStoreList" id="'+ locations[i].mno +'"href="storeController.jsp?command=getStoreInMarket">이동하기!</a>'
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
