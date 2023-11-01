// ID 중복 검사 수행
function checkIdDuplicate() {
  var id = document.getElementById("id").value;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/finalProject/views/join", true); // URL 설정
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      if (response.isDuplicate) {
        document.getElementById("msg").innerHTML = "아이디가 이미 사용 중입니다.";
      } else {
        document.getElementById("msg").innerHTML = "사용 가능한 아이디입니다.";
      }
    }
  };
  xhr.send("checkType=id&value=" + encodeURIComponent(id)); // 데이터 전송
}

// nickname 중복 검사 수행
function checkNicknameDuplicate() {
  var nickname = document.getElementById("nickname").value;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/finalProject/views/join", true); // URL 설정
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      if (response.isDuplicate) {
        document.getElementById("msg").innerHTML = "닉네임이 이미 사용 중입니다.";
      } else {
        document.getElementById("msg").innerHTML = "사용 가능한 닉네임입니다.";
      }
    }
  };
  xhr.send("checkType=nickname&value=" + encodeURIComponent(nickname)); // 데이터 전송
}
  
    // 클라이언트 측에서 비밀번호 일치 확인 수행
   function checkPasswordMatch() {
    var passwd = document.getElementById("passwd").value;
    var repasswd = document.getElementById("repasswd").value;

    if (passwd === repasswd) {
      document.getElementById("msg").innerHTML = "비밀번호가 일치합니다.";
    } else {
      document.getElementById("msg").innerHTML = "비밀번호가 일치하지 않습니다.";
    }
  }


  window.onload = function() {
  // 중복 확인 버튼 클릭 시 이벤트 리스너 등록
  document.getElementById("id_check").addEventListener("click", function() {
    try {
      checkIdDuplicate();
    } catch (error) {
      document.getElementById("msg").textContent = "오류 발생: " + error.message;
    }
  });

  document.getElementById("nickname_check").addEventListener("click", function() {
    try {
      checkNicknameDuplicate();
    } catch (error) {
      document.getElementById("msg").textContent = "오류 발생: " + error.message;
    }
  });

  // 비밀번호 일치 확인
  document.getElementById("passwd").addEventListener("blur", checkPasswordMatch);
  document.getElementById("repasswd").addEventListener("blur", checkPasswordMatch);
};

/* function submitHandler(event){
	let id = document.querySelector('#id');
	let nickname = document.querySelector('#nickname');
	let passwd = document.querySelector('#passwd'); 
	let repasswd = document.querySelector('#repasswd');
	let msgDiv = document.querySelector('#msg');
	let inputCheck = true;
	let msg = '';
	
	msgDiv.innerHTML = '';
	
	if(passwd.value !== repasswd.value){
		msg = '비밀번호와 비밀번호 확인은 서로 일치해야 합니다!';
		passwd.value = '';
		repasswd.value = '';
		inputCheck = false;
	}
	
	if (!inputCheck) {
        event.preventDefault();
        msgDiv.innerHTML = msg;
        
    } else {
        let idCheckValue = 'duplicate';
        let nicknameCheckValue = 'duplicate';

        if (idCheckValue === 'duplicate') {
            event.preventDefault();
            msgDiv.innerHTML = '아이디가 이미 사용중입니다.';
        } else if (nicknameCheckValue === 'duplicate') {
            msgDiv.innerHTML = '닉네임이 이미 사용 중입니다.';
        } else {
            // 중복 확인이 모두 통과한 경우 폼을 제출
            document.querySelector('#join__form').submit();
        }
    }
}

function init() {
    let join__form = document.querySelector('#join__form');
    join__form.addEventListener('submit', submitHandler);
}

window.addEventListener('load', init);

*/



/* // 중복 확인 버튼 클릭 시 처리
document.getElementById('id_check').addEventListener('click', function () {
    let id = document.querySelector('#id').value;
    if (id) {
        checkDuplicate('id', id);
    } else {
        alert('아이디를 입력하세요.');
    }
});

document.getElementById('nickname_check').addEventListener('click', function () {
    let nickname = document.querySelector('#nickname').value;
    if (nickname) {
        checkDuplicate('nickname', nickname);
    } else {
        alert('닉네임을 입력하세요.');
    }
});

// 중복 확인 AJAX 호출
function checkDuplicate(field, value) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/finalProject/checkDuplicate?field=' + field + '&value=' + value, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const response = xhr.responseText;
            if (response === 'duplicate') {
                alert('이미 사용 중인 ' + (field === 'id' ? '아이디' : '닉네임') + '입니다.');
            } else if (response === 'unique') {
                alert('사용 가능한 ' + (field === 'id' ? '아이디' : '닉네임') + '입니다.');
            } else {
                alert('서버 에러: ' + response);
            }
        }
    };
    xhr.send();
} */
