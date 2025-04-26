// 2025.04.12 조승찬
document.addEventListener("DOMContentLoaded", () => {

    const allFiles = [];  // 파일 추가시 기존 파일 유지되게 하기 위한 배열
    document.body.addEventListener("click", (e) => {

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
        if (e.target.closest(".flog-button-30")) {
            document.querySelector(".flog-div-176").style.display = "block";
            //document.querySelector(".feelog-header-mainDiv").style.zIndex = "100";
            document.querySelector(".flog-nav-6").style.zIndex = "100";
            // 첨부 파일 보관 배열 클리어
            allFiles.splice(0, allFiles.length);
        }

        // 2025.04.26 조승찬 파일 첨부 처리
        // 모달창 이미지 추가 버튼 클릭시
        if (e.target.closest(".flog-button-37")) {
            const input = document.createElement("input");
            input.type = "file";
            input.accept = "image/*";
            input.multiple = true;

            // 최대 5개 :: <ul class="FeelogStack-root flog-ul-7"> 에 data-count 추가해서 체크하는거로 수정
            input.addEventListener("change", (e) => {

                // 파일 추가시 기존 파일에 추가되는 형식으로 모여서 서버로 전송
                const files = e.target.files;

                Array.from(files).forEach(newFile => {
                    // 중복이 있는지 확인하고 없을 때 추가
                    const isDup = allFiles.some(existingFile => {
                        return existingFile.name == newFile.name && existingFile.size == newFile.size;
                    });

                    if (!isDup) { // 중복이 없을 때만 추가
                        if (allFiles.length >= 5) {
                            alert("최대 5개의 이미지만 업로드할 수 있습니다.");
                            return;
                        }  else {
                            allFiles.push(newFile);
                        }
                    }
                });

                const formData = new FormData();
                allFiles.forEach( file => {
                    formData.append("files", file);
                });

                // 서버로 전송하여 path와 썸네일 생성
                inputFileUpload(formData);

            });

            input.click();
        }

        // 새로 생성된 썸네일 삭제버튼 클릭시
        // 신규 추가된 파일이 추가될 수 있도록 기존 배열에서 삭제처리
        if (e.target.className == "file-cancel"){
            const index = e.target.closest("li").dataset.index; // 클릭된곳의 인덱스 찾아오기
            allFiles.splice(index, 1); // 배열에서 파일 제거
            e.target.closest(".uploadFile").remove()
        }


        // 모달창 x, 취소, 게시 버튼 클릭시 모달 없애고, 헤드 z-index 500으로 원복
        if (
            e.target.closest(".flog-button-36") ||
            e.target.classList.contains("post") ||
            e.target.classList.contains("cancel")
        ) {
            document.querySelector(".flog-div-176").style.display = "none";
            //document.querySelector(".feelog-header-mainDiv").style.zIndex = "10000";
            document.querySelector(".flog-nav-6").style.zIndex = "500";
            // 첨부 파일 보관 배열 클리어
        }

        //2025.04.26 조승찬 :: 게시 클릭시 서버로 전송
        if (e.target.classList.contains("post")) {

            e.preventDefault(); // 폼 제출 방지

            let text =``;
            document.querySelectorAll(".uploadFile").forEach((li, index) => {
              text += `
	            <input type="hidden" name="files[${index}].fileName" value="${li.dataset.fileName}">
	            <input type="hidden" name="files[${index}].filePath" value="${li.dataset.filePath}">
			 `;
            });

            document.querySelector('[name="uploadFile-form"]').insertAdjacentHTML("beforeend", text)
            document.querySelector('[name="uploadFile-form"]').submit();
        }

        // 케밥 버튼 클릭시
        if (e.target.closest(".flog-button-31")) {
            e.preventDefault();

            const kebabButton = e.target.closest(".flog-button-31");

            // 이미 열려 있는 버튼을 눌렀을 경우 기존 모달창 삭제 후 종료
            if (kebabButton.classList.contains("expanded")) {
                document.querySelector(".flog-ul-8").remove();
                kebabButton.classList.remove("expanded");
                return;
            }

            // 기존에 열린 메뉴가 있으면 삭제
            document.querySelector(".flog-ul-8")?.remove();

            // 모든 케밥 버튼에서 expanded 클래스 제거
            document.querySelectorAll(".flog-button-31.expanded").forEach((btn) => {
                btn.classList.remove("expanded");
            });

            // 메뉴 요소 생성
            const menuElement = document.createElement("ul");
            menuElement.setAttribute("role", "menu");
            menuElement.setAttribute("tabindex", "-1");
            menuElement.setAttribute("id", ":r2c:");
            menuElement.setAttribute(
                "class",
                "base-Popper-root FeelogMenu-root Feelog-expanded FeelogMenu-variantPlain FeelogMenu-colorNeutral FeelogMenu-sizeMd flog-ul-8"
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
                    class="FeelogMenuItem-root FeelogMenuItem-colorNeutral FeelogMenuItem-variantPlain flog-li-13"
                >
                    글 수정
                </li>
                <li
                    tabindex="-1"
                    id=":r3t:"
                    role="menuitem"
                    data-last-child=""
                    class="FeelogMenuItem-root FeelogMenuItem-colorNeutral FeelogMenuItem-variantPlain flog-li-14"
                >
                    글 삭제
                </li>`;

            document.body.appendChild(menuElement);
            kebabButton.classList.add("expanded");
        }

        // 글 수정 버튼 클릭시
        if (e.target.classList.contains("flog-li-13")) {
            document.querySelector(".flog-div-176").style.display = "block";
            //document.querySelector(".feelog-header-mainDiv").style.zIndex = "100";
            document.querySelector(".flog-nav-6").style.zIndex = "100";

            // 기존에 열린 메뉴가 있으면 삭제
            document.querySelector(".flog-ul-8")?.remove();

            // 첨부 파일 보관 배열 클리어
            allFiles.splice(0, allFiles.length);

            // 모든 케밥 버튼에서 expanded 클래스 제거
            document.querySelectorAll(".flog-button-31.expanded").forEach((btn) => {
                btn.classList.remove("expanded");
            });


        }

        // 좋아요 버튼 클릭시
        if (e.target.closest(".flog-button-32")) {
            // 기존 버튼 및 다른 요소들 선택
            const currentButton = e.target.closest(".flog-button-32");
            const newLikeButtonHTML = `
                <button type="button" aria-label="좋아요 버튼" class="FeelogButton-root FeelogButton-variantPlain FeelogButton-colorNeutral FeelogButton-sizeSm flog-button-39">
                    <span class="FeelogButton-startDecorator flog-span-42">
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-22">
                            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                        </svg>
                    </span>
                </button>`;

            // 기존 버튼 교체
            currentButton.outerHTML = newLikeButtonHTML;
        }

        // 좋아요 버튼 클릭시(취소)
        if (e.target.closest(".flog-button-39")) {
            // 기존 버튼 및 다른 요소들 선택
            const currentButton = e.target.closest(".flog-button-39");
            const newLikeButtonHTML = `
                    <button type="button" aria-label="좋아요 버튼" class="FeelogButton-root FeelogButton-variantPlain FeelogButton-colorNeutral FeelogButton-sizeSm flog-button-32">
                        <span class="FeelogButton-startDecorator flog-span-41">
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-19">
                                <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
                            </svg>
                        </span>
                    </button>`;

            // 기존 버튼 교체
            currentButton.outerHTML = newLikeButtonHTML;
        }
    });
});
