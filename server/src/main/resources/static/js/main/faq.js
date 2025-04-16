document.addEventListener("DOMContentLoaded", () => {
    const dropdown = document.querySelector(".user-info.dropdown");
    const menu = dropdown.querySelector(".dropdown-menu");

    dropdown.addEventListener("mouseenter", () => {
        menu.style.display = "block";
    });

    dropdown.addEventListener("mouseleave", () => {
        menu.style.display = "none";
    });
});
