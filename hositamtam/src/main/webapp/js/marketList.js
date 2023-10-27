
        // 지도 생성 (+ 일반, 위성 지도 선택할 수 있도록 토글 버튼 생성) -------------------------------------------
        var mapOptions = {
            center: new naver.maps.LatLng(35.21003, 129.0689),
            zoom: 11,
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
                    { place:"부평깡통시장", lat: 35.10187, lng: 129.0259 },
                    { place:"망미종합시장", lat: 35.17191, lng: 129.1036 },
                    { place:"자갈치시장", lat: 35.09665, lng: 129.0306 },
                    //{ place:"BHC치킨", lat: 35.11482, lng: 129.0371 },
        ];
        //--------------------------------------------------------
        
        
        
        // 지도 위에 마커 여러개 표시하기--------------------------------------------------------
        for (var i = 0; i < locations.length; i++) {
            
                /* 마커 아이콘 변경(부트스트랩)
                var markerImage = {
                    //content: '<i class="bi bi-shop" style="width: 20px; height: 20px;"></i>',
                    size: new naver.maps.Size(100, 100),
                    anchor: new naver.maps.Point(16, 32),
                };
                */
        
        
                // 마커 생성  (및 부트스트랩 아이콘 적용)
                var marker = new naver.maps.Marker({
                    map: map,
                    position: new naver.maps.LatLng(locations[i].lat, locations[i].lng),
                    //icon: markerImage, // 마커 이미지 설정
                });
                
        
                var infowindow = new naver.maps.InfoWindow({
                    content: '<div>' + locations[i].place + '</div><a href="/apiTest/naverMapApi.html">이동하기!</a>'
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
        
        // 카카오 목록 나오는 거 일단 그대로 가져옴
        // 장소 검색 객체를 생성합니다
        var ps = new naver.maps.services.Places();  
        
        // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
        var infowindow = new naver.maps.InfoWindow({zIndex:1});
        
        // 키워드로 장소를 검색합니다
        searchPlaces();
        
        // 키워드 검색을 요청하는 함수입니다
        function searchPlaces() {
        
            var keyword = document.getElementById('keyword').value;
        
            if (!keyword.replace(/^\s+|\s+$/g, '')) {
                alert('키워드를 입력해주세요!');
                return false;
            }
        
            // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
            ps.keywordSearch( keyword, placesSearchCB); 
        }
        
        // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
        function placesSearchCB(data, status, pagination) {
            if (status === naver.maps.services.Status.OK) {
        
                // 정상적으로 검색이 완료됐으면
                // 검색 목록과 마커를 표출합니다
                displayPlaces(data);
        
                // 페이지 번호를 표출합니다
                displayPagination(pagination);
        
            } else if (status === naver.maps.services.Status.ZERO_RESULT) {
        
                alert('검색 결과가 존재하지 않습니다.');
                return;
        
            } else if (status === naver.maps.services.Status.ERROR) {
        
                alert('검색 결과 중 오류가 발생했습니다.');
                return;
        
            }
        }
        
        // 검색 결과 목록과 마커를 표출하는 함수입니다
        function displayPlaces(places) {
        
            var listEl = document.getElementById('placesList'), 
            menuEl = document.getElementById('menu_wrap'),
            fragment = document.createDocumentFragment(), 
            bounds = new naver.maps.LatLngBounds(), 
            listStr = '';
            
            // 검색 결과 목록에 추가된 항목들을 제거합니다
            removeAllChildNods(listEl);
        
            // 지도에 표시되고 있는 마커를 제거합니다
            removeMarker();
            
            for ( var i=0; i<places.length; i++ ) {
        
                // 마커를 생성하고 지도에 표시합니다
                var placePosition = new naver.maps.LatLng(places[i].y, places[i].x),
                    marker = addMarker(placePosition, i), 
                    itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
        
                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                bounds.extend(placePosition);
        
                // 마커와 검색결과 항목에 mouseover 했을때
                // 해당 장소에 인포윈도우에 장소명을 표시합니다
                // mouseout 했을 때는 인포윈도우를 닫습니다
                (function(marker, title) {
                    naver.maps.event.addListener(marker, 'mouseover', function() {
                        displayInfowindow(marker, title);
                    });
        
                    naver.maps.event.addListener(marker, 'mouseout', function() {
                        infowindow.close();
                    });
        
                    itemEl.onmouseover =  function () {
                        displayInfowindow(marker, title);
                    };
        
                    itemEl.onmouseout =  function () {
                        infowindow.close();
                    };
                })(marker, places[i].place_name);
        
                fragment.appendChild(itemEl);
            }
        
            // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
            listEl.appendChild(fragment);
            menuEl.scrollTop = 0;
        
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
        }
        
        // 검색결과 항목을 Element로 반환하는 함수입니다
        function getListItem(index, places) {
        
            var el = document.createElement('li'),
            itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                        '<div class="info">' +
                        '   <h5>' + places.place_name + '</h5>';
        
            if (places.road_address_name) {
                itemStr += '    <span>' + places.road_address_name + '</span>' +
                            '   <span class="jibun gray">' +  places.address_name  + '</span>';
            } else {
                itemStr += '    <span>' +  places.address_name  + '</span>'; 
            }
                         
              itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                        '</div>';           
        
            el.innerHTML = itemStr;
            el.className = 'item';
        
            return el;
        }
        
        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        function addMarker(position, idx, title) {
            var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
                imageSize = new naver.maps.Size(36, 37),  // 마커 이미지의 크기
                imgOptions =  {
                    spriteSize : new naver.maps.Size(36, 691), // 스프라이트 이미지의 크기
                    spriteOrigin : new naver.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                    offset: new naver.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                },
                markerImage = new naver.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                    marker = new naver.maps.Marker({
                    position: position, // 마커의 위치
                    image: markerImage 
                });
        
            marker.setMap(map); // 지도 위에 마커를 표출합니다
            markers.push(marker);  // 배열에 생성된 마커를 추가합니다
        
            return marker;
        }
        
        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        function removeMarker() {
            for ( var i = 0; i < markers.length; i++ ) {
                markers[i].setMap(null);
            }   
            markers = [];
        }
        
        // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
        function displayPagination(pagination) {
            var paginationEl = document.getElementById('pagination'),
                fragment = document.createDocumentFragment(),
                i; 
        
            // 기존에 추가된 페이지번호를 삭제합니다
            while (paginationEl.hasChildNodes()) {
                paginationEl.removeChild (paginationEl.lastChild);
            }
        
            for (i=1; i<=pagination.last; i++) {
                var el = document.createElement('a');
                el.href = "#";
                el.innerHTML = i;
        
                if (i===pagination.current) {
                    el.className = 'on';
                } else {
                    el.onclick = (function(i) {
                        return function() {
                            pagination.gotoPage(i);
                        }
                    })(i);
                }
        
                fragment.appendChild(el);
            }
            paginationEl.appendChild(fragment);
        }
        
        // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
        // 인포윈도우에 장소명을 표시합니다
        function displayInfowindow(marker, title) {
            var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';
        
            infowindow.setContent(content);
            infowindow.open(map, marker);
        }
        
         // 검색결과 목록의 자식 Element를 제거하는 함수입니다
        function removeAllChildNods(el) {   
            while (el.hasChildNodes()) {
                el.removeChild (el.lastChild);
            }
        }
        
        
        
        
        function init() {
        
        }
        
        
        window.addEventListener('load', init);