//  2025.04.06 조승찬
document.addEventListener("DOMContentLoaded", async () => {
    // 타이틀(댓글, 커뮤니티, 관리자알림) 클릭 이벤트
    document.querySelectorAll(".FeelogListItem-variantPlain").forEach((title) => {
        title.addEventListener("click", (e) => {
            // 타이틀이 새로 선택되면 타이틀 진하게, 밑줄 표현 되도록 수정
            if (!e.target.classList.contains("selected")) {
                // 기존에 선택되었던 타이틀 원상 복귀
                document.querySelector(".selected.flog-a-7").querySelector(".flog-p-5").classList.add("flog-p-4");
                document.querySelector(".selected.flog-a-7").querySelector(".flog-p-5").classList.remove("flog-p-5");
                document.querySelector(".selected.flog-a-7").classList.add("flog-a-6");
                document.querySelector(".selected.flog-a-7").classList.remove("selected", "flog-a-7");
                // 클릭된 타이틀에 진하게, 밑줄 표현 클래스명 추가
                e.target.closest(".FeelogListItem-variantPlain").querySelector(".flog-p-4").classList.add("flog-p-5");
                e.target
                    .closest(".FeelogListItem-variantPlain")
                    .querySelector(".flog-p-4")
                    .classList.remove("flog-p-4");
                e.target.closest(".FeelogListItem-variantPlain").classList.add("selected", "flog-a-7");
                e.target.closest(".FeelogListItem-variantPlain").classList.remove("flog-a-6");
            }
        });
    });

    // 2025.04.24  조승찬 ::  페이징 처리
    document.querySelector(".pagination-container").addEventListener("click", function (e) {
        const pageLink = e.target.closest(".change-page"); // 가장 가까운 .change-page 요소 찾기
        if (!pageLink) return; // 클릭한 요소가 .change-page가 아니면 무시

        e.preventDefault(); // 기본 이벤트 막기

        const pageValue = pageLink.getAttribute("href"); // href 값 가져오기
        if (pageValue) {
            e.preventDefault(); // 기본 동작 방지
            document.querySelector(".notifyAdminList").value = pageValue;
            document.forms["pageForm"].submit(); // 폼 제출
        }
    });
    // 2025.04.24  조승찬 ::  페이징 처리

    // 구직정보
    async function getFindJobList(callback) {
        const response = await fetch("/myPage/findJobList")
        const jobListData = await response.json()
        if (callback) {
            callback(jobListData)
        }
    }

    document.querySelector(".joy-3ys6m0").addEventListener("click", async (e) => {
        if (e.target.closest(".find-button")) {
            const jobListWrap = document.querySelector(".flog-div-61");
            const anotherTarget = document.querySelector(".joy-90lb7v")
            const target = e.target.closest(".find-button");

            anotherTarget.classList.replace("joy-90lb7v", "joy-14fbtec");
            target.classList.replace("joy-14fbtec", "joy-90lb7v")
            jobListWrap.innerHTML = ``;

            await getFindJobList((jobListData) => {
                jobListData.forEach((job) => {
                    const newDiv = document.createElement("div")
                    const newHr = document.createElement("hr");
                    newHr.classList.add("FeelogDivider-root", "FeelogDivider-horizontal", "flog-hr-2")
                    newDiv.classList.add("FeelogBox-root", "flog-div-62");
                    newDiv.innerHTML = `
                <span class="FeelogBadge-root flog-span-4">
                    <a class="FeelogLink-root FeelogLink-colorPrimary FeelogLink-body-md FeelogLink-underlineNone flog-a-18">
                        <div class="FeelogAvatar-root FeelogAvatar-variantSoft FeelogAvatar-colorNeutral FeelogAvatar-sizeLg flog-div-63">
                            <img alt="" src="/images/favicon.ico" loading="lazy" class="FeelogAvatar-img flog-img-1">
                        </div>
                    </a>
                    <span class="FeelogBadge-badge FeelogBadge-invisible FeelogBadge-anchorOriginTopLeft FeelogBadge-variantSolid FeelogBadge-colorDanger FeelogBadge-sizeSm flog-span-11">
                    </span>
                </span>
                <div class="FeelogStack-root flog-article-1">
                    <h3 class="FeelogTypography-root FeelogTypography-h4 flog-h3-1">${job.title}</h3>
                    <p class="FeelogTypography-root FeelogTypography-body-md flog-p-6">${job.nation}, ${job.companyName}</p>
                    <p class="FeelogTypography-root FeelogTypography-body-md flog-p-6">상세 : ${job.industry}</p>
                    <p class="FeelogTypography-root FeelogTypography-body-md flog-p-6">자격요건 : ${job.lang}, ${job.visaNm}, ${job.careerStyle}</p>
                    <p class="FeelogTypography-root FeelogTypography-body-md flog-p-6">모집일 : ${job.startDate} ~ ${job.endDate}, 모집인원 : ${job.recruitCount}명</p>
                </div>
                `;
                    jobListWrap.appendChild(newDiv)
                    jobListWrap.appendChild(newHr)
                })
            })
        }
    })
});
