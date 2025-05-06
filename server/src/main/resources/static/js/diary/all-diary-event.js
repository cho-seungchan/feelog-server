diaryService.getDiaryListALlAndSubscribe(diaryLayout.showDiaryListClose)
indexService.getNoticeListMain(indexLayout.showNoticeMain)

const moreButton = document.querySelector(".moreInfo_button_01");
const postContainer = document.querySelector(".diary_wrap_01");
const reportDiv = document.createElement("div");

reportDiv.id = "report-button";

moreButton.addEventListener("click", async (e) => {
    const pageIndex = parseInt(moreButton.getAttribute("data-index"));
    await diaryService.getDiaryListALlAndSubscribe(diaryLayout.showDiaryListClose, pageIndex);
    moreButton.setAttribute("data-index", pageIndex + 1);
})

postContainer.addEventListener("click", (e) => {
    if (e.target.closest(".menu-button")) {
        const existingDiv = document.querySelector("#report-button");
        const diaryId = e.target.closest(".menu-button").getAttribute("data-index")

        if (existingDiv) {
            existingDiv.remove();
        } else {
            text = `
        <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
            <button id=${diaryId} class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p report-post">
                <p class=" button_text_01">신고하기</p>
            </button>
        </ul>
        `;
            reportDiv.innerHTML = text;

            const rect = e.target.closest(".menu-button").getBoundingClientRect();
            const absoluteTop = rect.bottom + window.scrollY;
            const absoluteLeft = rect.left + window.scrollX;

            reportDiv.style.position = "absolute";
            reportDiv.style.top = `${absoluteTop}px`;
            reportDiv.style.left = `${absoluteLeft}px`;
            document.body.appendChild(reportDiv);
        }
    }
})

document.querySelector("body").addEventListener("click", async (e) => {
    if (e.target.closest(".report-post")) {
        if (loginMember == null) {
            alert("로그인 후 이용해주세요")
            window.location.href = "/login/login";
            return;
        }

        const reportListData = await diaryService.getDiaryReportIds();
        const reportDiaryId = e.target.closest(".report-post").id;
        const duplicationId = reportListData.includes(Number(reportDiaryId));

        if (!duplicationId) {
            console.log("들어옴")
            if (confirm("이 게시글을 신고하시겠습니까?")) {
                await reportService.addDiaryReport({
                    memberId: loginMember.id,
                    diaryId: reportDiaryId
                })
            }
        } else {
            alert("이미 신고된 게시글입니다.")
        }
    }
})