
/*// 지도 생성 --------------------------------------------------------
var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(35.11363, 129.0377),
    zoom: 16,
    mapOptions
});
//--------------------------------------------------------
*/


// 지도 생성 (+ 일반, 위성 지도 선택할 수 있도록 토글 버튼 생성) -------------------------------------------
var mapOptions = {
    center: new naver.maps.LatLng(35.11363, 129.0377),
    zoom: 16,
    mapTypeControl: true, // 위성 지도 토글 버튼을 표시
    mapTypeControlOptions: {
        style: naver.maps.MapTypeControlStyle.BUTTON,
        position: naver.maps.Position.TOP_RIGHT
    }
};

var map = new naver.maps.Map('map', mapOptions);
//-------------------------------------------------------------------------------------







// 더미 데이터 : 나중에 DB에서 가져올 데이터--------------------------------------------
const locations = [
            // 이렇게 위도, 경도 값 받아오면 됨! DB로 부터
            { place:"신발원", lat: 35.11494, lng: 129.0387 },
            { place:"평산옥", lat: 35.11441, lng: 129.0371 },
            { place:"장성향", lat: 35.11363, lng: 129.0377 },
            //{ place:"BHC치킨", lat: 35.11482, lng: 129.0371 },
];
//--------------------------------------------------------







// 지도 위에 마커 여러개 표시하기--------------------------------------------------------
for (var i = 0; i < locations.length; i++) {
    
        //  마커 아이콘 변경(부트스트랩)
        var markerImage = {
            content: '<i class="bi bi-geo-fill" style="width: 20px; height: 20px;"></i>',
            size: new naver.maps.Size(100, 100),
            anchor: new naver.maps.Point(16, 32),
        };
        


        // 마커 생성 및 부트스트랩 아이콘 적용
        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(locations[i].lat, locations[i].lng),
            //icon: markerImage, // 마커 이미지 설정
        });
        

        var infowindow = new naver.maps.InfoWindow({
            content: 
            // content: '<div style="background-color: red; width: 20px; height: 20px; border-radius: 50%;"></div>'
            '<div class="infoContent">'+ 
        '<h4>'+ locations[i].place + '</h4>'+
            '<div class="countContainer"><div class="rating"><img src="./images/2b50.png" alt="">별점</div><div class="reviewCount"><img src="./images/review.png" alt="">리뷰개수</div><div class="likeCount"><i class="fa-solid fa-heart"></i>찜 개수</div></div>'+
            '<div class="infoContent-addr">주소</div><div class="btnContainer">'+
                '<button>점포 상세페이지</button><button>점포 정보 수정</button><button>폐업 제보</button></div></div>',
			
        });


    // 마커를 클릭했을 때, 인포윈도우창이 뜨고 지도 다른 부분을 다시 클릭하면 인포윈도우창이 꺼짐
    (function(marker, infowindow) {
        naver.maps.Event.addListener(marker, "click", function (e) {
            infowindow.open(map, marker);
        });

        naver.maps.Event.addListener(map, "click", function (mouseEvent) {
            infowindow.close();
        });
    })(marker, infowindow);










    /*// 위,경도 값을 통해 해당 위치의 지번 or 도로명 주소 가져와서 인포윈도우창에 뿌리기
    function searchCoordinateToAddress(latlng) {

        infoWindow.close();

        naver.maps.Service.reverseGeocode({
            coords: latlng,
            orders: [
                naver.maps.Service.OrderType.ADDR,
                naver.maps.Service.OrderType.ROAD_ADDR
            ].join(',')
        }, function(status, response) {
            if (status === naver.maps.Service.Status.ERROR) {
                return alert('Something Wrong!');
            }

            var items = response.v2.results,
                address = '',
                htmlAddresses = [];

            for (var i=0, ii=items.length, item, addrType; i<ii; i++) {
                item = items[i];
                address = makeAddress(item) || '';
                addrType = item.name === 'roadaddr' ? '[도로명 주소]' : '[지번 주소]';

                htmlAddresses.push((i+1) +'. '+ addrType +' '+ address);
            }

            infoWindow.setContent([
                '<div style="padding:10px;min-width:200px;line-height:150%;">',
                '<h4 style="margin-top:5px;">검색 좌표</h4><br />',
                htmlAddresses.join('<br />'),
                '</div>'
            ].join('\n'));

            infoWindow.open(map, latlng);
        });
    }
  */    
}
//--------------------------------------------------------








// GPS 기능 구현 -------------------------------------------------------------------
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
//--------------------------------------------------------------------------------------





// GPS 기능 구현2 : '현재 내 위치를 중심'으로 지도 보기
function gpsHandler() {
    // GPS 기능 구현 -------------------------------------------------------------------
    let latitude = 37.4979517;
    let longitude = 127.0276188;

    // 현재 나의 위치 마커 생성
    const gpsMarker = new naver.maps.Marker({
            position: new naver.maps.LatLng(latitude, longitude),
            map: map,
            icon: {
                content: '<div style="background-color: red; width: 15px; height: 15px; border-radius: 50%;"></div>',
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
                map.setCenter(newLatLng);  // 현재 내 위치가 중심이 되어서 지도가 움직임
            }

            function error() {
                // 에러 처리
            }
        }

        // 일정 주기로 GPS 위치 업데이트
        setInterval(updateLocation, 2000); // 2초마다 업데이트   
}







// id 값이 gpsCancel 인 버튼을 클릭 시 '현재 내 위치 중심으로' 지도 보기 중지 ----------- 수정해야함 !!!(10/12 오전 10시)
function gpsCancelHandler() {
    // GPS 업데이트를 중지하기 위해 setInterval의 반환 값을 변수로 저장
    const updateInterval = setInterval(updateLocation, 2000);
    // 이전에 실행했던 setInterval을 clearInterval을 사용해 중지
    clearInterval(updateInterval);
    // 지도의 중심을 고정
    map.setCenter(37.4979517, 127.0276188);
}












function init() {
// '새 점포 등록' 버튼 클릭 이벤트
let insertStore = document.querySelector('#insertStore');
insertStore.addEventListener('click', insertStoreHandler);

// '현재 내 위치 중심으로 보기' 버튼 클릭 이벤트
let gps = document.querySelector('#gps');
gps.addEventListener('click', gpsHandler);

// '현재 내 위치 중심 해제' 버튼 클릭 이벤트
let gpsCancel = document.querySelector('#gpsCancel');
gpsCancel.addEventListener('click', gpsCancelHandler);
}





// '새 점포 등록' 버튼 클릭 시 ------------------------------------------------
function insertStoreHandler() {

//마커 생성
var marker = new naver.maps.Marker({
    position: new naver.maps.LatLng(37.3595894, 127.105399),
    map: map,
    // icon: {
    //     content: '<div style="background-color: red; width: 15px; height: 15px; border-radius: 50%;"></div>',
    //     anchor: new naver.maps.Point(15, 15)
    // }
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
    var latitude = e.coord.lat();9
    var longitude = e.coord.lng();
    var iwContent = '<div class="iwContent" style="padding:5px;">' +
        '<a href="/naverMapApi/insert.html?lat=' + latitude + '&lng=' + longitude + '" target="_self"><div class="up"><i class="bi bi-shop"></i></div><div class="down">등록하기</div></a></div>';
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
//-------------------------------------------------------------------------



window.addEventListener('load', init);
