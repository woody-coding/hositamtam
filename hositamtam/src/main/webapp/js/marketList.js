let xhr = new XMLHttpRequest();
let locations = [];
let markers = [];
let mapOptions = {};
let map;
let currentMno;

// 초기화 함수
function init() {


    // 점포 정보 클릭 시, 해당 점포와 연동된 지도 상의 인포윈도우창 띄우기
    document.querySelector('#marketContent').addEventListener('click', function(event) {
        if (event.target.getAttribute('class') === 'mkName') {
			currentMno = event.target.getAttribute('id');
            openInfo();
        }
    });

	const keyCate = window.localStorage.getItem('KeywordAndCateno');
	const KeywordAndCateno = JSON.parse(keyCate);

	

	if(KeywordAndCateno.msg) {
				
		window.location.href = '/finalProject/views/error';
	}

    else if (KeywordAndCateno.keyword) {

        let currentKeyword = KeywordAndCateno.keyword;
        
        document.querySelector('#howGetMarket').innerHTML = "'"+ currentKeyword + "'(으)로 조회한 결과입니다.";	
        
        
        xhr.onreadystatechange = marketAjaxHandler;
        
        let param = '?command=getMarketListBySearch&keyword=' + currentKeyword;
        xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
        xhr.send();
    }
    
    else if(KeywordAndCateno.cateno) {
		
		let currentCateno = KeywordAndCateno.cateno + '';
		
		
		
		let currentCate = '';
		
		document.querySelector('#howGetMarket').innerHTML = '';
		
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
		
		currentCateno = currentCateno * 1;		
		if(currentCateno < 1 || currentCateno > 9) {
			window.location.href = '/finalProject/views/error';
		} else {
			document.querySelector('#howGetMarket').innerHTML = "'" + currentCate + "'(으)로 조회한 결과입니다.";

		}
		
		
		xhr.onreadystatechange = marketAjaxHandler;
		
	    let param = '?command=getMarketListByItem&cateno=' + currentCateno;
	    xhr.open('GET', '../ajaxController/toAjaxController.jsp' + param, true);
	    xhr.send();
	}
}


// 해당 점포 정보에 연동된 인포윈도우창을 지도 상에서 띄우기
function openInfo() {
	
    for (var i = 0; i < locations.length; i++) {
        if (parseFloat(currentMno) === locations[i].mno) {
			
            let marker = markers[i]; // 이미 생성된 마커를 가져옵니다.
            
	         var infowindow = new naver.maps.InfoWindow({
	       /*     content: '<div>' + locations[i].mname + '</div><a href="/finalProject/views/store?mno=' + locations[i].mno + '">이동하기!</a>'
	     	 */ content:    '<div class="goMarket">' +
        '<div class="row">' +
            '<p class="mkName" id="'+ locations[i].mno +'">' + locations[i].mname + '</p>' +
            '<p id="mkaddr">' + locations[i].maddr + '</p>' +
            '<p class="mkEtc"><i class="fa-solid fa-restroom"></i> ' + locations[i].mtoilet + ' <i class="fa-solid fa-square-parking"></i> ' + locations[i].mparking + '<p>' +
            '<a href="/finalProject/views/store?mno=' + locations[i].mno + '">이동하기!</a>' +
        '</div>' +
    '</div>',

  
		 	});
		 	
            infowindow.open(map, marker);
            
            naver.maps.Event.addListener(map, "click", function (mouseEvent) {
                infowindow.close();
            });
        }
    }
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
			
			window.location.href = '/finalProject/views/error';
        }
        
		// 지도 생성
		mapOptions = {  			//35.21003 129.0689
		    center: new naver.maps.LatLng(35.17132, 129.0666),
		    zoom: 12,
		    mapTypeControl: false,
		    mapTypeControlOptions: {
		        style: naver.maps.MapTypeControlStyle.BUTTON,
		        position: naver.maps.Position.BOTTOM_LEFT,
		    }
		};
		
		
		map = new naver.maps.Map('map', mapOptions);

        let marketContents ='';
        
        
        if (allMarketList.length === 0) {
           	window.location.href = '/finalProject/views/error';
        } 
        
        
		for(let i=0; i < allMarketList.length; i++) {
			
			if(allMarketList[i].mtel === null) {
				allMarketList[i].mtel = '';
			}
			
			
			marketContents += '<div id="'+ allMarketList[i].mno +'" class="mkcontainer">' +
					            '<div class="row">' +
					                '<p class="mkName" id="'+ allMarketList[i].mno +'">' + allMarketList[i].mname + '</p>' +
					                '<p id="mkaddr">' + allMarketList[i].maddr + '</p>' +
					                '<p class="mkEtc"><i class="fa-solid fa-restroom"></i> ' + allMarketList[i].mtoilet + ' <i class="fa-solid fa-square-parking"></i> ' + allMarketList[i].mparking + '<p>' +
					            '</div>' +
					        '</div>'
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


// 지도 위에 마커를 표시하는 함수
function showMarkers() {
    for (var i = 0; i < locations.length; i++) {
        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(locations[i].mlat, locations[i].mlng)
        });

        var infowindow = new naver.maps.InfoWindow({
       /*     content: '<div>' + locations[i].mname + '</div><a href="/finalProject/views/store?mno=' + locations[i].mno + '">이동하기!</a>'
      */ content: '<div class="goMarket">' +
        '<div class="row">' +
            '<p class="mkName" id="'+ locations[i].mno +'">' + locations[i].mname + '</p>' +
            '<p id="mkaddr">' + locations[i].maddr + '</p>' +
            '<p class="mkEtc"><i class="fa-solid fa-restroom"></i> ' + locations[i].mtoilet + ' <i class="fa-solid fa-square-parking"></i> ' + locations[i].mparking + '<p>' +
            '<a href="/finalProject/views/store?mno=' + locations[i].mno + '">이동하기!</a>' +
        '</div>' +
    '</div>',
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