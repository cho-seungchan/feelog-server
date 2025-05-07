const reportLayout = (() => {
    const showPostReportList = async (reportListData) => {
        const htmlWrap = document.querySelector(".AppLayout_contents")

        htmlWrap.innerHTML = `
                <main class="FundingPage_container_main">
                    <div
                            id="FundingPage_Content"
                            class="FundingPage_box_main"
                    >
                        <section>
                            <div
                                    class="FundingPage_headBanner_section"
                            ></div>
                        </section>
                        <section
                                class="FundingPage_presentation_section"
                        >
                            <div class="FundingPage_content_section">
                                <div class="Funding_wrapper">
                                    <div
                                            class="admin-Wrapper section-Container ulWrap"
                                            style="
                                                    opacity: 1;
                                                    display: block;
                                                "
                                    >
                                        <div class="h3_wrapper">
                                            <h3>포스트 신고 목록</h3>
                                        </div>

                                        <button
                                                class="manageBtn return-post-button"
                                                type="button"
                                        >
                                            삭제 취소
                                        </button>
                                        <button
                                                data-index=1
                                                class="admin-deleteBtn delete-Report"
                                                type="button"
                                        >
                                            게시글 삭제
                                        </button>
                                        <div class="memberList-wrap">
                                            <ul
                                                    class="NoticePage__NoticeListWrapper"
                                            >
                                                <div
                                                        class="mainMemberListDiv"
                                                >
                                                    <label class="">
                                                        <input
                                                                type="checkbox"
                                                                class="checkboxall"
                                                        />
                                                    </label>
                                                    <div
                                                            class="accountDiv"
                                                    >
                                                        번호
                                                    </div>
                                                    <div
                                                            class="nameDiv"
                                                    >
                                                        게시글 제목
                                                    </div>
                                                    <div
                                                            class="statusDiv"
                                                    >
                                                        활동상태
                                                    </div>
                                                     <div
                                                            class="createDateDiv"
                                                    >
                                                        생성날짜
                                                    </div>
                                                </div>
                                            </ul>
                                        </div>
                                        <footer
                                                class="footer-wrapper"
                                                style="display: inline"
                                        >
                                            <div
                                                    class="button-container"
                                                    style="display: flex"
                                            >
                                                <div
                                                        class="button_hover left-button-wrap"
                                                >
                                                </div>
                                                <div id="button-wrap">
                                                </div>
                                                <div
                                                        class="right_button right-button-wrap"
                                                >
                                                </div>
                                            </div>
                                        </footer>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </main>
        `;
        const listWrap = document.querySelector(".NoticePage__NoticeListWrapper");
        const buttonWrap = document.querySelector("#button-wrap");
        const leftButtons = document.querySelector(".left-button-wrap");
        const rightButtons = document.querySelector(".right-button-wrap");
        const pagination = reportListData.pagination;

        leftButtons.innerHTML = `
        <button
            data-index=1
            width="40px"
            height="40px"
            font-size="18px"
            disabled=""
            class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none start-page post-report-buttons"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns start-img img_left_end"
                /></button
            ><button
                data-index=${pagination.page - 1}
                width="40px"
                height="40px"
                font-size="18px"
                disabled=""
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none left-button post-report-buttons"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns left-img img_left_end"
            />
        </button>
        `;
        const startPage = document.querySelector(".start-page")
        const leftButton = document.querySelector(".left-button")
        if (pagination.page !== 1) {
            startPage.classList.remove("status-none");
            startPage.classList.add("status-on");
            startPage.removeAttribute("disabled")
            leftButton.classList.remove("status-none")
            leftButton.classList.add("status-on")
            leftButton.removeAttribute("disabled")
            document.querySelector(".start-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        } else {
            startPage.classList.remove("status-on")
            startPage.classList.add("status-none")
            startPage.setAttribute("disabled", true)
            leftButton.classList.remove("status-on")
            leftButton.classList.add("status-none")
            leftButton.setAttribute("disabled", true)
            document.querySelector(".start-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        rightButtons.innerHTML = `
        <button
                    data-index=${pagination.page + 1}
                    width="40px"
                    height="40px"
                    font-size="18px"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on right-button post-report-buttons"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu right-img"
                /></button
            ><button
                data-index=${pagination.realEnd}
                width="40px"
                height="40px"
                font-size="18px"
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on  last-page post-report-buttons"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu last-img"
            />
        </button>
        `;
        const lastPage = document.querySelector(".last-page");
        const rightButton = document.querySelector(".right-button");

        if (pagination.page !== pagination.realEnd) {
            lastPage.classList.remove("status-none");
            lastPage.classList.add("status-on");
            lastPage.removeAttribute("disabled")
            rightButton.classList.remove("status-none")
            rightButton.classList.add("status-on")
            rightButton.removeAttribute("disabled")
            document.querySelector(".last-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        } else {
            lastPage.classList.remove("status-on")
            lastPage.classList.add("status-none")
            lastPage.setAttribute("disabled", true)
            rightButton.classList.remove("status-on")
            rightButton.classList.add("status-none")
            rightButton.setAttribute("disabled", true)
            document.querySelector(".last-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        reportListData.postReportList.forEach((post) => {
            const newLi = document.createElement("li");
            const createdDate = new Date(post.updatedDate);
            const formatCreatedDate = createdDate.toISOString().split('T')[0];
            let reportStatus = post.reportStatus;

            if (reportStatus === "ACTIVE") {
                reportStatus = "정상";
            } else if (reportStatus === "DELETED") {
                reportStatus = "삭제"
            }

            newLi.innerHTML = `
                    <div class="memberListDiv">
                        <label class="">
                            <input id=${post.id} type="checkbox" class="usersCheckbox">
                        </label>
                        <a href="/post/read?id=${post.postId}" class="postInfo-wrap">
                        <div class="emailDiv">
                            ${post.id}
                        </div>
                        <div class="nameDiv">
                            ${post.postTitle}
                        </div>
                         <div class="statusDiv">
                            ${reportStatus}
                        </div>
                        <div class="createdDateDiv">
                            ${formatCreatedDate}
                        </div>
                        </a>
                    </div>`;
            listWrap.appendChild(newLi);
        })

        text = ``;

        for (let i = pagination.startPage; i <= pagination.endPage; i++) {
            if (pagination.page === i) {
                text += `
                <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="#3397ff"
                    font-size="18px"
                    font-weight="bold"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll thisButton post-report-buttons">${i}
                </button>
                `;
                continue;
            }
            text += `
            <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="black"
                    font-size="18px"
                    font-weight="normal"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll anotherButton post-report-buttons">${i}
            </button>
            `;
        }
        buttonWrap.innerHTML = text;
    }

    const showDiaryReportList = async (reportListData) => {
        console.log(reportListData)
        const htmlWrap = document.querySelector(".AppLayout_contents")

        htmlWrap.innerHTML = `
                <main class="FundingPage_container_main">
                    <div
                            id="FundingPage_Content"
                            class="FundingPage_box_main"
                    >
                        <section>
                            <div
                                    class="FundingPage_headBanner_section"
                            ></div>
                        </section>
                        <section
                                class="FundingPage_presentation_section"
                        >
                            <div class="FundingPage_content_section">
                                <div class="Funding_wrapper">
                                    <div
                                            class="admin-Wrapper section-Container ulWrap"
                                            style="
                                                    opacity: 1;
                                                    display: block;
                                                "
                                    >
                                        <div class="h3_wrapper">
                                            <h3>다이어리 신고 목록</h3>
                                        </div>

                                        <button
                                                class="manageBtn return-diary-button"
                                                type="button"
                                        >
                                            삭제 취소
                                        </button>
                                        <button
                                                data-index=1
                                                class="admin-deleteBtn delete-diary-Report"
                                                type="button"
                                        >
                                            게시글 삭제
                                        </button>
                                        <div class="memberList-wrap">
                                            <ul
                                                    class="NoticePage__NoticeListWrapper"
                                            >
                                                <div
                                                        class="mainMemberListDiv"
                                                >
                                                    <label class="">
                                                        <input
                                                                type="checkbox"
                                                                class="checkboxall"
                                                        />
                                                    </label>
                                                    <div
                                                            class="accountDiv"
                                                    >
                                                        번호
                                                    </div>
                                                    <div
                                                            class="nameDiv"
                                                    >
                                                        게시글 제목
                                                    </div>
                                                    <div
                                                            class="statusDiv"
                                                    >
                                                        활동상태
                                                    </div>
                                                     <div
                                                            class="createDateDiv"
                                                    >
                                                        생성날짜
                                                    </div>
                                                </div>
                                            </ul>
                                        </div>
                                        <footer
                                                class="footer-wrapper"
                                                style="display: inline"
                                        >
                                            <div
                                                    class="button-container"
                                                    style="display: flex"
                                            >
                                                <div
                                                        class="button_hover left-button-wrap"
                                                >
                                                </div>
                                                <div id="button-wrap">
                                                </div>
                                                <div
                                                        class="right_button right-button-wrap"
                                                >
                                                </div>
                                            </div>
                                        </footer>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </main>
        `;
        const listWrap = document.querySelector(".NoticePage__NoticeListWrapper");
        const buttonWrap = document.querySelector("#button-wrap");
        const leftButtons = document.querySelector(".left-button-wrap");
        const rightButtons = document.querySelector(".right-button-wrap");
        const pagination = reportListData.pagination;

        leftButtons.innerHTML = `
        <button
            data-index=1
            width="40px"
            height="40px"
            font-size="18px"
            disabled=""
            class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none start-page diary-report-buttons"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns start-img img_left_end"
                /></button
            ><button
                data-index=${pagination.page - 1}
                width="40px"
                height="40px"
                font-size="18px"
                disabled=""
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none left-button diary-report-buttons"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns left-img img_left_end"
            />
        </button>
        `;
        const startPage = document.querySelector(".start-page")
        const leftButton = document.querySelector(".left-button")
        if (pagination.page !== 1) {
            startPage.classList.remove("status-none");
            startPage.classList.add("status-on");
            startPage.removeAttribute("disabled")
            leftButton.classList.remove("status-none")
            leftButton.classList.add("status-on")
            leftButton.removeAttribute("disabled")
            document.querySelector(".start-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        } else {
            startPage.classList.remove("status-on")
            startPage.classList.add("status-none")
            startPage.setAttribute("disabled", true)
            leftButton.classList.remove("status-on")
            leftButton.classList.add("status-none")
            leftButton.setAttribute("disabled", true)
            document.querySelector(".start-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        rightButtons.innerHTML = `
        <button
                    data-index=${pagination.page + 1}
                    width="40px"
                    height="40px"
                    font-size="18px"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on right-button diary-report-buttons"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu right-img"
                /></button
            ><button
                data-index=${pagination.realEnd}
                width="40px"
                height="40px"
                font-size="18px"
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on  last-page diary-report-buttons"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu last-img"
            />
        </button>
        `;
        const lastPage = document.querySelector(".last-page");
        const rightButton = document.querySelector(".right-button");

        if (pagination.page !== pagination.realEnd) {
            lastPage.classList.remove("status-none");
            lastPage.classList.add("status-on");
            lastPage.removeAttribute("disabled")
            rightButton.classList.remove("status-none")
            rightButton.classList.add("status-on")
            rightButton.removeAttribute("disabled")
            document.querySelector(".last-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        } else {
            lastPage.classList.remove("status-on")
            lastPage.classList.add("status-none")
            lastPage.setAttribute("disabled", true)
            rightButton.classList.remove("status-on")
            rightButton.classList.add("status-none")
            rightButton.setAttribute("disabled", true)
            document.querySelector(".last-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        reportListData.diaryReportList.forEach((post) => {
            const newLi = document.createElement("li");
            const createdDate = new Date(post.updatedDate);
            const formatCreatedDate = createdDate.toISOString().split('T')[0];
            let reportStatus = post.reportStatus;

            if (reportStatus === "ACTIVE") {
                reportStatus = "정상";
            } else if (reportStatus === "DELETED") {
                reportStatus = "삭제"
            }

            newLi.innerHTML = `
                    <div class="memberListDiv">
                        <label class="">
                            <input id=${post.id} type="checkbox" class="usersCheckbox">
                        </label>
                        <a href="/diary/diary-read?id=${post.diaryId}" class="postInfo-wrap">
                        <div class="emailDiv">
                            ${post.id}
                        </div>
                        <div class="nameDiv">
                            ${post.diaryTitle}
                        </div>
                         <div class="statusDiv">
                            ${reportStatus}
                        </div>
                        <div class="createdDateDiv">
                            ${formatCreatedDate}
                        </div>
                        </a>
                    </div>`;
            listWrap.appendChild(newLi);
        })

        text = ``;

        for (let i = pagination.startPage; i <= pagination.endPage; i++) {
            if (pagination.page === i) {
                text += `
                <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="#3397ff"
                    font-size="18px"
                    font-weight="bold"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll thisButton diary-report-buttons">${i}
                </button>
                `;
                continue;
            }
            text += `
            <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="black"
                    font-size="18px"
                    font-weight="normal"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll anotherButton diary-report-buttons">${i}
            </button>
            `;
        }
        buttonWrap.innerHTML = text;
    }

    const showReplyReportList = async (reportListData) => {
        console.log(reportListData)
        const htmlWrap = document.querySelector(".AppLayout_contents")

        htmlWrap.innerHTML = `
                <main class="FundingPage_container_main">
                    <div
                            id="FundingPage_Content"
                            class="FundingPage_box_main"
                    >
                        <section>
                            <div
                                    class="FundingPage_headBanner_section"
                            ></div>
                        </section>
                        <section
                                class="FundingPage_presentation_section"
                        >
                            <div class="FundingPage_content_section">
                                <div class="Funding_wrapper">
                                    <div
                                            class="admin-Wrapper section-Container ulWrap"
                                            style="
                                                    opacity: 1;
                                                    display: block;
                                                "
                                    >
                                        <div class="h3_wrapper">
                                            <h3>댓글 신고 목록</h3>
                                        </div>

                                        <button
                                                class="manageBtn return-reply-button"
                                                type="button"
                                        >
                                            삭제 취소
                                        </button>
                                        <button
                                                data-index=1
                                                class="admin-deleteBtn delete-reply-Report"
                                                type="button"
                                        >
                                            댓글 삭제
                                        </button>
                                        <div class="memberList-wrap">
                                            <ul
                                                    class="NoticePage__NoticeListWrapper"
                                            >
                                                <div
                                                        class="mainMemberListDiv"
                                                >
                                                    <label class="">
                                                        <input
                                                                type="checkbox"
                                                                class="checkboxall"
                                                        />
                                                    </label>
                                                    <div
                                                            class="accountDiv"
                                                    >
                                                        번호
                                                    </div>
                                                    <div
                                                            class="nameDiv"
                                                    >
                                                        댓글내용
                                                    </div>
                                                    <div
                                                            class="statusDiv"
                                                    >
                                                        활동상태
                                                    </div>
                                                     <div
                                                            class="createDateDiv"
                                                    >
                                                        생성날짜
                                                    </div>
                                                </div>
                                            </ul>
                                        </div>
                                        <footer
                                                class="footer-wrapper"
                                                style="display: inline"
                                        >
                                            <div
                                                    class="button-container"
                                                    style="display: flex"
                                            >
                                                <div
                                                        class="button_hover left-button-wrap"
                                                >
                                                </div>
                                                <div id="button-wrap">
                                                </div>
                                                <div
                                                        class="right_button right-button-wrap"
                                                >
                                                </div>
                                            </div>
                                        </footer>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </main>
        `;
        const listWrap = document.querySelector(".NoticePage__NoticeListWrapper");
        const buttonWrap = document.querySelector("#button-wrap");
        const leftButtons = document.querySelector(".left-button-wrap");
        const rightButtons = document.querySelector(".right-button-wrap");
        const pagination = reportListData.pagination;

        leftButtons.innerHTML = `
        <button
            data-index=1
            width="40px"
            height="40px"
            font-size="18px"
            disabled=""
            class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none start-page reply-report-buttons"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns start-img img_left_end"
                /></button
            ><button
                data-index=${pagination.page - 1}
                width="40px"
                height="40px"
                font-size="18px"
                disabled=""
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none left-button reply-report-buttons"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns left-img img_left_end"
            />
        </button>
        `;
        const startPage = document.querySelector(".start-page")
        const leftButton = document.querySelector(".left-button")
        if (pagination.page !== 1) {
            startPage.classList.remove("status-none");
            startPage.classList.add("status-on");
            startPage.removeAttribute("disabled")
            leftButton.classList.remove("status-none")
            leftButton.classList.add("status-on")
            leftButton.removeAttribute("disabled")
            document.querySelector(".start-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        } else {
            startPage.classList.remove("status-on")
            startPage.classList.add("status-none")
            startPage.setAttribute("disabled", true)
            leftButton.classList.remove("status-on")
            leftButton.classList.add("status-none")
            leftButton.setAttribute("disabled", true)
            document.querySelector(".start-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        rightButtons.innerHTML = `
        <button
                    data-index=${pagination.page + 1}
                    width="40px"
                    height="40px"
                    font-size="18px"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on right-button reply-report-buttons"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu right-img"
                /></button
            ><button
                data-index=${pagination.realEnd}
                width="40px"
                height="40px"
                font-size="18px"
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on  last-page reply-report-buttons"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu last-img"
            />
        </button>
        `;
        const lastPage = document.querySelector(".last-page");
        const rightButton = document.querySelector(".right-button");

        if (pagination.page !== pagination.realEnd) {
            lastPage.classList.remove("status-none");
            lastPage.classList.add("status-on");
            lastPage.removeAttribute("disabled")
            rightButton.classList.remove("status-none")
            rightButton.classList.add("status-on")
            rightButton.removeAttribute("disabled")
            document.querySelector(".last-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        } else {
            lastPage.classList.remove("status-on")
            lastPage.classList.add("status-none")
            lastPage.setAttribute("disabled", true)
            rightButton.classList.remove("status-on")
            rightButton.classList.add("status-none")
            rightButton.setAttribute("disabled", true)
            document.querySelector(".last-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        reportListData.replyReportList.forEach((post) => {
            const newLi = document.createElement("li");
            const createdDate = new Date(post.updatedDate);
            const formatCreatedDate = createdDate.toISOString().split('T')[0];
            let reportStatus = post.reportStatus;

            if (reportStatus === "ACTIVE") {
                reportStatus = "정상";
            } else if (reportStatus === "DELETED") {
                reportStatus = "삭제"
            }

            newLi.innerHTML = `
                    <div class="memberListDiv">
                        <label class="">
                            <input id=${post.id} type="checkbox" class="usersCheckbox">
                        </label>
                        <div class="emailDiv">
                            ${post.id}
                        </div>
                        <div class="nameDiv">
                            ${post.replyContent}
                        </div>
                         <div class="statusDiv">
                            ${reportStatus}
                        </div>
                        <div class="createdDateDiv">
                            ${formatCreatedDate}
                        </div>
                    </div>`;
            listWrap.appendChild(newLi);
        })

        text = ``;

        for (let i = pagination.startPage; i <= pagination.endPage; i++) {
            if (pagination.page === i) {
                text += `
                <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="#3397ff"
                    font-size="18px"
                    font-weight="bold"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll thisButton reply-report-buttons">${i}
                </button>
                `;
                continue;
            }
            text += `
            <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="black"
                    font-size="18px"
                    font-weight="normal"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll anotherButton reply-report-buttons">${i}
            </button>
            `;
        }
        buttonWrap.innerHTML = text;
    }

    return {
        showPostReportList: showPostReportList,
        showDiaryReportList: showDiaryReportList,
        showReplyReportList: showReplyReportList
    }
})()