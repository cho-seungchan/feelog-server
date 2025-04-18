// 2025.04.12 조승찬
document.addEventListener("DOMContentLoaded", () => {
    document.body.addEventListener("click", (e) => {
        console.log("body click  " + e.target.outerHTML);

        // 이미지 이전 버튼 클릭시
        if (e.target.closest(".prev-button")) {
            // 부모와 총 이미지 수 찾기
            const container = e.target.closest(".slider-container");
            // 움직일 판 찾기
            const slider = container.querySelector(".slider");
            // 현재 이미지 인덱스 찾기
            const currentActive = container.querySelector(".swiper-slide.active");
            let currentIndex = parseInt(currentActive.getAttribute("data-index"), 10);

            // 액티브 해제
            currentActive.classList.remove("active");

            currentIndex -= 1;
            // 판 움직이기
            if (window.innerWidth < 744) {
                slider.style.transform = `translate3d(-${(currentIndex - 1) * 330}px, 0, 0)`;
            } else {
                slider.style.transform = `translate3d(-${(currentIndex - 1) * 510}px, 0, 0)`;
            }

            // 액티브 인덱스 새로 지정하기
            const nextActive = container.querySelector(`.slider div[data-index="${currentIndex}"]`);
            nextActive.classList.add("active");

            // 이전 이미지가 없을 경우 이전 버튼 없애기
            if (currentIndex == 1) {
                e.target.closest(".prev-button").classList.add("swiper-button-disabled");
            }
            // 이후 버튼 보이게 하기
            if (
                e.target
                    .closest(".slider-container")
                    .querySelector(".next-button")
                    .classList.contains("swiper-button-disabled")
            ) {
                e.target
                    .closest(".slider-container")
                    .querySelector(".next-button")
                    .classList.remove("swiper-button-disabled");
            }
        }

        // 이미지 이후 버튼 클릭시
        if (e.target.closest(".next-button")) {
            // 부모와 총 이미지 수 찾기
            const container = e.target.closest(".slider-container");
            const totalSlides = parseInt(container.getAttribute("data-total"), 10);
            // 움직일 판 찾기
            const slider = container.querySelector(".slider");
            // 현재 이미지 인덱스 찾기
            const currentActive = container.querySelector(".swiper-slide.active");
            let currentIndex = parseInt(currentActive.getAttribute("data-index"), 10);

            // 액티브 해제
            currentActive.classList.remove("active");

            // 판 움직이기
            if (window.innerWidth < 744) {
                slider.style.transform = `translate3d(-${currentIndex * 330}px, 0, 0)`;
            } else {
                slider.style.transform = `translate3d(-${currentIndex * 510}px, 0, 0)`;
            }

            currentIndex += 1;
            // 액티브 인덱스 새로 지정하기
            const nextActive = container.querySelector(`.slider div[data-index="${currentIndex}"]`);
            nextActive.classList.add("active");

            // 이후 이미지가 없을 경우 이후 버튼 없애기
            if (currentIndex == totalSlides) {
                e.target.closest(".next-button").classList.add("swiper-button-disabled");
            }
            // 이전 버튼 보이게 하기
            if (
                e.target
                    .closest(".slider-container")
                    .querySelector(".prev-button")
                    .classList.contains("swiper-button-disabled")
            ) {
                e.target
                    .closest(".slider-container")
                    .querySelector(".prev-button")
                    .classList.remove("swiper-button-disabled");
            }
        }

        // 게시글 작성 버튼 클릭시
        if (e.target.closest(".joy-cqpx6y")) {
            document.querySelector(".joy-8ijia1").style.display = "block";
            document.querySelector(".feelog-header-mainDiv").style.zIndex = "100";
            document.querySelector(".joy-b8eqj").style.zIndex = "100";
        }

        // 모달창 이미지 추가 버튼 클릭시
        if (e.target.closest(".joy-15s0qw7")) {
            const input = document.createElement("input");
            input.type = "file";
            input.accept = "image/*";
            input.multiple = true;

            // 최대 5개 :: <ul class="MuiStack-root joy-1p6iz8l"> 에 data-count 추가해서 체크하는거로 수정
            input.addEventListener("change", (event) => {
                if (event.target.files.length > 5) {
                    alert("최대 5개의 이미지만 업로드할 수 있습니다.");
                    event.target.value = "";
                }
            });

            input.click();
        }

        // 모달창 x, 취소, 게시 버튼 클릭시 모달 없애고, 헤드 z-index 500으로 원복
        if (
            e.target.closest(".joy-dp90vh") ||
            e.target.classList.contains("post") ||
            e.target.classList.contains("cancel")
        ) {
            document.querySelector(".joy-8ijia1").style.display = "none";
            document.querySelector(".feelog-header-mainDiv").style.zIndex = "10000";
            document.querySelector(".joy-b8eqj").style.zIndex = "500";
        }

        // 케밥 버튼 클릭시
        if (e.target.closest(".joy-ccwyls")) {
            e.preventDefault();

            const kebabButton = e.target.closest(".joy-ccwyls");

            // 이미 열려 있는 버튼을 눌렀을 경우 기존 모달창 삭제 후 종료
            if (kebabButton.classList.contains("expanded")) {
                document.querySelector(".joy-1rhmv1w").remove();
                kebabButton.classList.remove("expanded");
                return;
            }

            // 기존에 열린 메뉴가 있으면 삭제
            document.querySelector(".joy-1rhmv1w")?.remove();

            // 모든 케밥 버튼에서 expanded 클래스 제거
            document.querySelectorAll(".joy-ccwyls.expanded").forEach((btn) => {
                btn.classList.remove("expanded");
            });

            // 메뉴 요소 생성
            const menuElement = document.createElement("ul");
            menuElement.setAttribute("role", "menu");
            menuElement.setAttribute("tabindex", "-1");
            menuElement.setAttribute("id", ":r2c:");
            menuElement.setAttribute(
                "class",
                "base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-1rhmv1w"
            );

            // 케밥 버튼 위치 기준으로 메뉴 위치 계산
            const rect = kebabButton.getBoundingClientRect();
            const top = rect.bottom + window.scrollY;
            const left = rect.right + window.scrollX - 100;

            menuElement.setAttribute(
                "style",
                `position: absolute; top: ${top}px; left: ${left}px; margin: 0px; transform: none;`
            );
            menuElement.setAttribute("data-popper-placement", "bottom-end");

            menuElement.innerHTML = `
                <li
                    tabindex="0"
                    id=":r3r:"
                    role="menuitem"
                    data-first-child=""
                    class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-ax57yv"
                >
                    글 수정
                </li>
                <li
                    tabindex="-1"
                    id=":r3t:"
                    role="menuitem"
                    data-last-child=""
                    class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-16nydwy"
                >
                    글 삭제
                </li>`;

            document.body.appendChild(menuElement);
            kebabButton.classList.add("expanded");
        }

        // 글 수정 버튼 클릭시
        if (e.target.classList.contains("joy-ax57yv")) {
            document.querySelector(".joy-8ijia1").style.display = "block";
            document.querySelector(".feelog-header-mainDiv").style.zIndex = "100";
            document.querySelector(".joy-b8eqj").style.zIndex = "100";

            // 기존에 열린 메뉴가 있으면 삭제
            document.querySelector(".joy-1rhmv1w")?.remove();

            // 모든 케밥 버튼에서 expanded 클래스 제거
            document.querySelectorAll(".joy-ccwyls.expanded").forEach((btn) => {
                btn.classList.remove("expanded");
            });
        }

        // 좋아요 버튼 클릭시
        if (e.target.closest(".joy-1ut0ahw")) {
            // 기존 버튼 및 다른 요소들 선택
            const currentButton = e.target.closest(".joy-1ut0ahw");
            const newLikeButtonHTML = `
                <button type="button" aria-label="좋아요 버튼" class="MuiButton-root MuiButton-variantPlain MuiButton-colorNeutral MuiButton-sizeSm joy-1j1ow5l">
                    <span class="MuiButton-startDecorator joy-9bgpn">
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-1pcjr6d">
                            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                        </svg>
                    </span>
                    1
                </button>`;

            // 기존 버튼 교체
            currentButton.outerHTML = newLikeButtonHTML;
        }

        // 좋아요 버튼 클릭시(취소)
        if (e.target.closest(".joy-1j1ow5l")) {
            // 기존 버튼 및 다른 요소들 선택
            const currentButton = e.target.closest(".joy-1j1ow5l");
            const newLikeButtonHTML = `
                    <button type="button" aria-label="좋아요 버튼" class="MuiButton-root MuiButton-variantPlain MuiButton-colorNeutral MuiButton-sizeSm joy-1ut0ahw">
                        <span class="MuiButton-startDecorator joy-1312rwg">
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-s1s0o1">
                                <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
                            </svg>
                        </span>
                    </button>`;

            // 기존 버튼 교체
            currentButton.outerHTML = newLikeButtonHTML;
        }
    });
});
