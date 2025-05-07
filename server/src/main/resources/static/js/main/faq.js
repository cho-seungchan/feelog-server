document.addEventListener("DOMContentLoaded", () => {
    // 로그인 했을 때 드롭다운
    const dropdown = document.querySelector(".user-info.dropdown");
    if (dropdown) {
        const menu = dropdown.querySelector(".dropdown-menu");
        dropdown.addEventListener("mouseenter", () => {
            menu.style.display = "block";
        });
        dropdown.addEventListener("mouseleave", () => {
            menu.style.display = "none";
        });
    }

    // 로그인 안 했을 때 로그인 메뉴 호버 효과 (옵션: 필요시 확장)
    const userNav = document.querySelector(".user-nav");
    if (userNav) {
        const signIn = userNav.querySelector(".sign-in");
        if (signIn) {
            signIn.addEventListener("mouseenter", () => {
                signIn.style.textDecoration = "underline";
            });
            signIn.addEventListener("mouseleave", () => {
                signIn.style.textDecoration = "none";
            });
        }
    }
});
