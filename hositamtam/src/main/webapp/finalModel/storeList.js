let xhr = new XMLHttpRequest();
let locations = [];

// 위도, 경도 초기 값을 설정할 변수
let mlat;
let mlng;

// 지도 생성 (+ 일반, 위성 지도 선택할 수 있도록 토글 버튼 생성)
var mapOptions = {
    center: new naver.maps.LatLng(mlat, mlng), // 초기 좌표 설정
    zoom: 16,
    mapTypeControl: true, // 위성 지도 토글 버튼을 표시
    mapTypeControlOptions: {
        style: naver.maps.MapTypeControlStyle.BUTTON,
        position: naver.maps.Position.TOP_RIGHT
    }
};

var map = new naver.maps.Map('map', mapOptions);

var markers = [];

function init() {
    const marketMno = window.localStorage.getItem('marketMno');

    if (marketMno) {
        const marketmno = JSON.parse(marketMno);
        console.log('mno:', marketmno.mno);
        let currentMno = marketmno.mno;
		xhr.onreadystatechange = storeAjaxHandler;
		
        let param = '?command=getStoreInMarket&mno=' + currentMno;
        xhr.open('GET', 'toAjaxController.jsp' + param, true);
        xhr.send();

        // 로컬 스토리지에서 cateno 값 삭제
        window.localStorage.removeItem('marketMno');
    }
}

// '새 점포 등록' 버튼 클릭 이벤트
let insertStore = document.querySelector('#insertStore');
insertStore.addEventListener('click', insertStoreHandler);

// '새 점포 등록' 버튼 클릭 시
function insertStoreHandler() {
    //마커 생성
    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(37.3595894, 127.105399),
        map: map,
    });

    var marker; // 클릭 이벤트로 생성한 마커를 저장할 변수
    var infowindow; // 인포윈도우 컨텐츠를 저장할 변수
    var prevClickCoord; // 이전 클릭한 위치를 저장할 변수
    var clickEventListener; // 클릭 이벤트 리스너

    // 클릭 이벤트 핸들러
    function handleMapClick(e) {
        if (marker) {
            marker.setMap(null); // 이전 마커 삭제
            if (infowindow) {
                infowindow.close(); // 이전 인포윈도우 닫기
            }
        }
        marker = new naver.maps.Marker({
            position: e.coord,
            map: map
        });
        prevClickCoord = e.coord;

        // 마커를 클릭했을 때 인포윈도우를 생성하고 표시(위도, 경도 값 a태그 파라미터로 넣어주기)
        var latitude = e.coord.lat();
        var longitude = e.coord.lng();
        var iwContent = '<div class="iwContent" style="padding:5px;">' +
            '<a href="/toAjaxController.jsp?command=insertStore&slat=' + latitude + '&slng=' + longitude + '" target="_self"><div class="up"><i class="bi bi-shop"></i></div><div class="down">등록하기</div></a></div>';
        infowindow = new naver.maps.InfoWindow({
            content: iwContent
        });
        infowindow.open(map, marker);
    }

    // 마우스 우클릭 이벤트 처리
    naver.maps.Event.addListener(map, 'rightclick', function (e) {
        if (clickEventListener) {
            // 클릭 이벤트 리스너가 존재하면 삭제
            naver.maps.Event.removeListener(clickEventListener);
            clickEventListener = null;
        }
        if (marker) {
            marker.setMap(null); // 클릭 이벤트로 생성한 마커 삭제
            if (infowindow) {
                infowindow.close(); // 인포윈도우 닫기
            }
            marker = null;
        }
    });

    // 클릭 이벤트 리스너 등록
    clickEventListener = naver.maps.Event.addListener(map, 'click', handleMapClick);
}

function storeAjaxHandler() {
    if (xhr.readyState === 4 && xhr.status === 200) {
        // 기존 마커들을 모두 제거
        removeMarker();

        const allStoreList = JSON.parse(xhr.responseText);

        for (let i in allStoreList) {
            mlat = parseFloat(allStoreList[i].mlat);
            mlng = parseFloat(allStoreList[i].mlng);
            const sno = parseFloat(allStoreList[i].sno);
            const sname = allStoreList[i].sname;
            const slat = parseFloat(allStoreList[i].slat);
            const slng = parseFloat(allStoreList[i].slng);
            const stype = allStoreList[i].stype;
            const sphoto = allStoreList[i].sphoto;
            const sfavoritecount = parseFloat(allStoreList[i].sfavoritecount);
            const scategory = allStoreList[i].scategory;
            const nickname = allStoreList[i].nickname;

            const location = {
                mlat: mlat,
                mlng: mlng,
                sno: sno,
                sname: sname,
                slat: slat,
                slng: slng,
                stype: stype,
                sphoto: sphoto,
                sfavoritecount: sfavoritecount,
                scategory: scategory,
                nickname: nickname
            };

            locations.push(location);
        }

        // mlat과 mlng 값을 업데이트하고 지도의 초기 좌표를 설정
        map.setCenter(new naver.maps.LatLng(mlat, mlng));

        // 마커를 다시 생성하고 표시
        showMarkers();
    }
}

// GPS 기능 구현
{
    let latitude;
    let longitude;

    // 현재 나의 위치에 GPS전용 마커 생성
    const gpsMarker = new naver.maps.Marker({
        position: new naver.maps.LatLng(latitude, longitude),
        map: map,
        icon: {
            content: '<div style="background-color: red; width: 20px; height: 20px; border-radius: 50%;"></div>',
            anchor: new naver.maps.Point(15, 15)
        }
    });

    // GPS 위치 업데이트 함수
    function updateLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(success, error);
        }

        function success(position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
            const newLatLng = new naver.maps.LatLng(latitude, longitude);
            gpsMarker.setPosition(newLatLng);
        }

        function error() {
            // 에러 처리
        }
    }

    // 일정 주기로 GPS 위치 업데이트
    setInterval(updateLocation, 2000); // 2초마다 업데이트
}

// 지도 위에 마커를 표시하는 함수
function showMarkers() {
    for (var i = 0; i < locations.length; i++) {
        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(locations[i].slat, locations[i].slng)
        });

        var infowindow = new naver.maps.InfoWindow({
            content: '<div>' + locations[i].sname + '</div><a href="/finalModel/toAjaxController.jsp?command=getStoreInMarket&mno=' + locations[i].mno + '">이동하기!</a>'
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
