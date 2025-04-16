const menuButtons = document.querySelectorAll(".menu-button");
const scrapButtons = document.querySelectorAll(".scrap-button");

const div = document.createElement("div");
div.id = "report-button";

let text = ``;
menuButtons.forEach((menuButton) => {
    menuButton.addEventListener("click", (e) => {
        const existingDiv = document.querySelector("#report-button");

        if (existingDiv) {
            existingDiv.remove();
        } else {
            text = `
        <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
            <a tabindex="-1" id=":r2p:" role="menuitem" class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p">
                <p class=" button_text_01">신고하기</p>
            </a>
        </ul>
        `;
            div.innerHTML = text;

            const rect = menuButton.getBoundingClientRect();
            const absoluteTop = rect.bottom + window.scrollY;
            const absoluteLeft = rect.left + window.scrollX;

            div.style.position = "absolute";
            div.style.top = `${absoluteTop}px`;
            div.style.left = `${absoluteLeft}px`;
            console.log("생성");
            document.body.appendChild(div);
        }
    });
});

scrapButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
        const wrap = e.currentTarget; // 항상 button 요소가 됨
        const svg = wrap.querySelector("svg"); // 내부 svg 찾기

        if (!svg) return;

        console.log("현재 svg 클래스:", svg.className);

        if (svg.classList.contains("menuButton_svg_01")) {
            svg.classList.remove("menuButton_svg_01");
            svg.classList.add("joy-1wbk7pq");

            svg.innerHTML = `
                <path d="M4.5 3.875v17.176a.95.95 0 0 0 1.496.777L12 17.625l6.004 4.203a.95.95 0 0 0 1.496-.777V3.875C19.5 2.84 18.66 2 17.625 2H6.375C5.34 2 4.5 2.84 4.5 3.875Z" fill="currentcolor"></path>
            `;
        } else if (svg.classList.contains("joy-1wbk7pq")) {
            console.log("----check----");

            svg.classList.remove("joy-1wbk7pq");
            svg.classList.add("menuButton_svg_01");

            svg.innerHTML = `
                <path d="M4.5 3.875C4.5 2.84 5.34 2 6.375 2v17.242l5.082-3.629a.933.933 0 0 1 1.09 0l5.078 3.63V3.874H6.375V2h11.25c1.035 0 1.875.84 1.875 1.875v17.188a.938.938 0 0 1-1.48.762L12 17.526l-6.02 4.297a.938.938 0 0 1-1.48-.762V3.875Z" fill="currentcolor"></path>
            `;
        }
    });
});
