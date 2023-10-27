

// for (let i = 0; i < $(".tab-button").length; i++) {
//   $(".tab-button")
//     .eq(i)
//     .on("click", function () {
//       $(".tab-button").removeClass("orange");
//       $(".tab-button").eq(i).addClass("orange");
//       $(".tab-content").removeClass("show");
//       $(".tab-content").eq(i).addClass("show");
//     });
// }

// for (let i = 0; i < $(".tab-button").length; i++) {
//   $(".tab-button")
//     .eq(i)
//     .on("click", function () {
//       탭열기(i);
//     });
// }

// function 탭열기(구멍) {
//   $(".tab-button").removeClass("orange");
//   $(".tab-button").eq(구멍).addClass("orange");
//   $(".tab-content").removeClass("show");
//   $(".tab-content").eq(구멍).addClass("show");
// }

// $(".list").click(function (e) {
//   if (e.target == document.querySelectorAll(".tab-button")[0]) {
//     탭열기(0);
//   }
//   if (e.target == document.querySelectorAll(".tab-button")[1]) {
//     탭열기(1);
//   }
//   if (e.target == document.querySelectorAll(".tab-button")[2]) {
//     탭열기(2);
//     w;
//   }
// });

// $(".list").click(function (e) {
//   탭열기(e.target.dataset.id);
// });

//array 자료형
// var car = ['소나타' , 5000, 'white'];
// console.log(car[1]);

// //object 자료형 이 좋다
// var car2 = {name: '소나타', value: 5000};
// var car = { name : '소나타', price : 50000, year : 2030 };
// console.log(car2.name);

// var car2 = { name: "소나타", price: [50000, 3000, 4000] };
// var shirts = [95, 100, 105];
// var pants = [28, 30, 32];

// $(".product").html(car2.name);
// $(".price").html(car2.price[0]);

// $(".form-select")
//   .eq(0)
//   .on("input", function () {
//     var value = $(".form-select").eq(0).val();
//     if (value == "셔츠") {
//       $(".form-select").eq(1).removeClass("form-hide");
//       $(".form-select").eq(1).html("");
//       shirts.forEach(function (a) {
//         $(".form-select").eq(1).append(`<option>${a}</option>`);
//       });
//     } else if (value == "바지") {
//       $(".form-select").eq(1).removeClass("form-hide");
//       $(".form-select").eq(1).html("");
//       pants.forEach(function (a) {
//         $(".form-select").eq(1).append(`<option>${a}</option>`);
//       });
//     } else if (value == "모자") {
//       $(".form-select").eq(1).addClass("form-hide");
//     }
//   });

// var products = [
//   { id: 0, price: 70000, title: "Blossom Dress" },
//   { id: 1, price: 50000, title: "Springfield Shirt" },
//   { id: 2, price: 60000, title: "Black Monastery" },
// ];

// products.forEach((a, i) => {
//   var html = `<div class="col-sm-4">
// <img src="https://via.placeholder.com/600" class="w-100">
// <h5>${products[i].title}</h5>
// <p>가격 : ${products[i].price}</p>
// <button class="buy">구매</button>
// </div>`;
//   $(".row").append(html);
// });

// var arr32 = [12, 3, 4];
// var newarr = JSON.stringify(arr32);

// localStorage.setItem("num", newarr);
// var 꺼낸거 = localStorage.getItem("num");
// console.log(JSON.parse(꺼낸거));

// var newcart = [];
// var cartproduct = JSON.parse(newcart);
//1.cart arr로 저장 2.cart항목있으면 arr 수정하고
// $(".buy").on("click", function (e) {
//   var title = $("e.target").siblings("h5").text();
//   if (localStorage.getItem("cart") != null) {
//     JSON.parse(localStorage.getItem("cart"));
//     JSON.parse(localStorage.getItem("cart")).push(title);
//     localStorage.setItem(
//       "cart",
//       JSON.stringify(JSON.parse(localStorage.getItem("cart")))
//     );
//   } else {
//     localStorage.setItem("cart", JSON.stringify([title]));
//   }
// });

// $(".buy").click(function (e) {
//   var title = $(e.target).siblings("h5").text();
//   if (localStorage.getItem("cart") != null) {
//     var 꺼낸거 = JSON.parse(localStorage.cart);
//     꺼낸거.push(title);
//     localStorage.setItem("cart", JSON.stringify(꺼낸거));
//   } else {
//     localStorage.setItem("cart", JSON.stringify([title]));
//   }
// });

// for (let i = 0; i < $(".card-title").length; i++) {

// $(".card-title").eq(0).html(products[0].title);
// $(".card-title").eq(1).html(products[1].title);
// $(".card-title").eq(2).html(products[2].title);
// $(".card-price")
//   .eq(0)
//   .html("가격 : " + products[0].price);
// $(".card-price")
//   .eq(1)
//   .html("가격 : " + products[1].price);
// $(".card-price")
//   .eq(2)
//   .html("가격 : " + products[2].price);

// console.log(products[0].title);
// var a = "<p>안녕<p>";
// $("#test").append(a);

// var html1 = `<div class="col-sm-4">
// <img src="https://via.placeholder.com/600" class="w-100">
// <h5>${products[1].title}</h5>
// <p>가격 : ${products[1].price}</p>
// </div>`;
// var html2 = `<div class="col-sm-4">
// <img src="https://via.placeholder.com/600" class="w-100">
// <h5>${products[2].title}</h5>
// <p>가격 : ${products[2].price}</p>
//  </div>`;

// for (let i = 0; i < products.length; i++) {
//   $(".row").append(html);
// }
// $("#price").on("click", function () {
//   products.sort(function (a, b) {
//     return a.price - b.price;
//   });

//   $(".row").html("");

//   products.forEach((a, i) => {
//     var html = `<div class="col-sm-4">
//   <img src="https://via.placeholder.com/600" class="w-100">
//   <h5>${products[i].title}</h5>
//   <p>가격 : ${products[i].price}</p>
//   <button class="buy">구매</button>
//   </div>`;
//     $(".row").append(html);
//   });
// });

// var 어레이 = ["나", "다", "가"];
// 어레이.sort(function (a, b) {
//   if (a < b) {
//     return 1;
//   } else {
//     return -1;
//   }
// });
// console.log(어레이);

// $("#sort1").on("click", function () {
//   products.sort(function (a, b) {
//     if (a.title < b.title) {
//       return 1;
//     } else {
//       return -1;
//     }
//   });
//   console.log(products);

//   $(".row").html("");

//   products.forEach((a, i) => {
//     var html = `<div class="col-sm-4">
//   <img src="https://via.placeholder.com/600" class="w-100">
//   <h5>${products[i].title}</h5>
//   <p>가격 : ${products[i].price}</p>
//   </div>`;
//     $(".row").append(html);
//   });
// });
// $("#filter").on("click", function () {
//   var newProduct = products.filter(function (a) {
//     return a.price <= 60000;
//   });

//   console.log(products);

//   $(".row").html("");

//   newProduct.forEach((a, i) => {
//     var html = `<div class="col-sm-4">
//   <img src="https://via.placeholder.com/600" class="w-100">
//   <h5>${products[i].title}</h5>
//   <p>가격 : ${products[i].price}</p>
//   </div>`;
//     $(".row").append(html);
//   });
// });
