let menuBtn = document.querySelector(".AppLayout_expandNavButton");
let nav = document.querySelector(".AppNavbarLayout_container");
let div = document.querySelector(".AppLayout_contents");

menuBtn.addEventListener("click", function () {
    nav.classList.toggle("active");
    menuBtn.classList.toggle("active");
    div.classList.toggle("active");
});


// 관리자 목록 화면

