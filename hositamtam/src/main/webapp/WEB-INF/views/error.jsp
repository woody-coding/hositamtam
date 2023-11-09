<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>호시탐탐</title>

   

    <!-- CSS -->
    <link rel="stylesheet" href="/finalProject/css/error.css" />

    <!-- JavaScript -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
      
  </head>
  <body>
   
<header class="mainHeader">
  <div class="mainHeader__logo">
    <a href="/finalProject/views/main"><img class="mainHeader__logo__img" src="../images/logo.ico" alt="logo" /></a>
  </div>
 </header>
 <div class="section">
  <div class="error">
    <img  src="../images/err.png">
    <div class="msg">
      <h1>잘못된 접근입니다</h1>
      <p>해당 시장이 없거나, <br>
      검색창에 입력이 없어서 시장을 찾을수가 없었습니다.</p>
      <button class="prevPage">이전 페이지로</button>
    </div>
    
    </div>
 </div>
  </body>
</html>