const writeChallengeButton = document.querySelector(".add-challenge");
const challengeListButton = document.querySelector(".challenge-list");

writeChallengeButton.addEventListener("click", (e) => {
    htmlWrap.innerHTML = `
    <main class="FundingPage_container_challengeWrite">
        <div id="FundingPage_Content" class="FundingPage_box_challengeWrite">
            <section class="FundingPage_presentation_challengeWrite">
                <ol class="Breadcrumb_container_challengeWrite"></ol>
                <div class="title-container">
                    <h2 class="FundingPage_title_challengeWrite">
                        챌린지 작성
                    </h2>
                </div>
                <div class="FundingPage_content_div01">
                    <section class="Section_container_section01 spacing-9" style="max-width: 1024px">
                        <div class="Section_content_section01">
                            <input type="hidden" name="registered" value="2025-02-12 15:51:48.0">
                        </div>
                    </section>
                    <div class="">
                        <section class="Section_container_section01 spacing-9">
                        </section>
                        <section class="Section_container_section01">
                            <div style="max-width: 1024px">
                                <div class="Section_header__header01">
                                    <h3 class="Section_title_title01">
                                        챌린지 내용
                                        <div>
                                            <button type="button" class="Tooltip_button__button01 Tooltip_withLabel_01" aria-describedby="Tooltip_5"></button>
                                        </div>
                                    </h3>
                                    <div class="Section_guide_01"></div>
                                </div>
                                <div class="Section_content_section01">
                                    <div class="StorySummaryField_container_01">
                                        <textarea name="courseContent" maxlength="1000" placeholder="내용 입력" class="Textarea_textarea_01 challenge-content"></textarea>
                                        <p class="StorySummaryField_text_01 content-count">
                                            0/1000
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section class="Section_container_section01 spacing-9">
                            <div style="max-width: 1024px">
                                <div class="Section_header__header01">
                                    <h3 class="Section_title_title01">
                                        대표 이미지
                                        <span class="BlindText_textHidden_01">필수</span>
                                        <div></div>
                                    </h3>
                                </div>
                                <div class="inputImage_container_01">
                                    <div>
                                        <label class="inputImage_wrap_01 add-imgFile">
                                            <input type="file" name="courseFileName-input" accept=".jpg, .jpeg, .png">
                                            <span>
                                                사진
                                                첨부하기</span>
                                            <div id="courseFile-wrap"></div>
                                        </label>
                                        <div class="inputImage_footer_01"></div>
                                    </div>
                                </div>
                                <div class="ImageList-sc-9v1mt2-0 hGJMVS"></div>
                            </div>
                        </section>
                    </div>
                    <div class="SaveButtonFooter_footer_01 SaveButtonFooter_expand_01">
                        <div class="">
                            <div class="StoryFormPage_saveButtonWrapper_01">
                                <button class="Button_button_01 Button_primary_01 Button_contained_01 Button_md_01 StoryFormPage_submitBtn_01 StoryFormPage_desktop_01 save-button" type="button">
                                    <span><span class="Button_children_01 ">저장</span></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </main>
    `;
})

challengeListButton.addEventListener("click", async (e) => {
    await challengeService.getList(challengeLayout.showList, 1);
})

htmlWrap.addEventListener("keyup", (e) => {
    if (e.target.classList.contains("challenge-content")) {
        const contentCount = document.querySelector(".content-count");
        contentCount.innerText = `${e.target.value.length}/1000`
    }
})

htmlWrap.addEventListener("change", (e) => {
    if (e.target.files[0]) {
        const files = e.target.files[0]; // FileList 객체

        const formData = new FormData();
        formData.append("file", files);
        // 서버로 전송하여 path와 썸네일 생성
        inputFileUpload(formData);
    }
});

htmlWrap.addEventListener("click", async (e) => {
    const imgWrap = document.querySelector(".kZTsQf");
    const challengeContent = document.querySelector(".challenge-content")
    const lists = document.querySelectorAll(".lists")

    if (e.target.classList.contains("benIbu")) {
        e.target.closest(".kZTsQf").remove()
    }

    if (e.target.classList.contains("add-imgFile") && imgWrap) {
        alert("이미지는 하나만 첨부가능합니다.")
        e.preventDefault()
        return;
    }

    if (e.target.closest(".save-button")) {
        const imgContainer = document.querySelector(".kZTsQf");

        if (challengeContent.value === "") {
            alert("내용을 입력해주세요")
            return;
        }

        if (!imgContainer) {
            await challengeService.insertMemberTaskPool({
                memberTaskPoolContent: challengeContent.value
            })

            alert("저장완료")
        } else {
            const uploadFile = document.querySelector(".uploadFile")
            const fileName = uploadFile.getAttribute("data-file-name")
            const filePath = uploadFile.getAttribute("data-file-path")
            console.log(fileName)
            console.log(filePath)

            await challengeService.insertMemberTaskPool({
                memberTaskPoolContent: challengeContent.value,
                memberTaskPoolFilePath: filePath,
                memberTaskPoolFileName: fileName
            })

            alert("저장완료")
            await challengeService.getList(challengeLayout.showList, 1)
        }
    }

    if (e.target.classList.contains("challengeButtons")) {
        await challengeService.getList(challengeLayout.showList, e.target.getAttribute("data-index"))
    }

    if (e.target.closest(".memberTaskPoolListDiv")) {
        const parent = e.target.closest(".memberTaskPoolListDiv");
        const content = parent.querySelector(".titleDiv");
        const targetId = parent.querySelector(".idDiv").innerText.trim();

        htmlWrap.innerHTML = `
        <main class="FundingPage_container_challengeWrite">
            <div id="FundingPage_Content" class="FundingPage_box_challengeWrite">
                <section class="FundingPage_presentation_challengeWrite">
                    <ol class="Breadcrumb_container_challengeWrite"></ol>
                    <div class="title-container">
                        <h2 class="FundingPage_title_challengeWrite">
                            챌린지 조회
                        </h2>
                    </div>
                    <div class="FundingPage_content_div01">
                        <section class="Section_container_section01 spacing-9" style="max-width: 1024px">
                            <div class="Section_content_section01">
                                <input type="hidden" name="registered" value="2025-02-12 15:51:48.0">
                            </div>
                        </section>
                        <div class="">
                            <section class="Section_container_section01 spacing-9">
                            </section>
                            <section class="Section_container_section01">
                                <div style="max-width: 1024px">
                                    <div class="Section_header__header01">
                                        <h3 class="Section_title_title01">
                                            챌린지 내용
                                            <div>
                                                <button type="button" class="Tooltip_button__button01 Tooltip_withLabel_01" aria-describedby="Tooltip_5"></button>
                                            </div>
                                        </h3>
                                        <div class="Section_guide_01"></div>
                                    </div>
                                    <div class="Section_content_section01">
                                        <div class="StorySummaryField_container_01">
                                            <textarea name="courseContent" maxlength="1000" placeholder="내용 입력" class="Textarea_textarea_01 challenge-content">${content.innerText}</textarea>
                                            <p class="StorySummaryField_text_01 content-count">
                                                ${content.innerText.length}/1000
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </section>
                            <section class="Section_container_section01 spacing-9">
                                <div style="max-width: 1024px">
                                    <div class="Section_header__header01">
                                        <h3 class="Section_title_title01">
                                            대표 이미지
                                            <span class="BlindText_textHidden_01">필수</span>
                                            <div></div>
                                        </h3>
                                    </div>
                                    <div class="inputImage_container_01">
                                        <div>
                                            <label class="inputImage_wrap_01 add-imgFile">
                                                <input type="file" name="courseFileName-input" accept=".jpg, .jpeg, .png">
                                                <span>
                                                    사진
                                                    첨부하기</span>
                                                <div id="courseFile-wrap"></div>
                                            </label>
                                            <div class="inputImage_footer_01"></div>
                                        </div>
                                    </div>
                                    <div class="ImageList-sc-9v1mt2-0 hGJMVS"></div>
                                </div>
                            </section>
                        </div>
                        <div class="SaveButtonFooter_footer_01 SaveButtonFooter_expand_01">
                            <div class="">
                                <div class="StoryFormPage_saveButtonWrapper_01">
                                    <button class="Button_button_01 Button_primary_01 Button_contained_01 Button_md_01 StoryFormPage_submitBtn_01 StoryFormPage_desktop_01 delete-button" type="button" style="left: 49%" data-index=${targetId}>
                                        <span><span class="Button_children_01 ">삭제</span></span>
                                    </button>
                                    <button class="Button_button_01 Button_primary_01 Button_contained_01 Button_md_01 StoryFormPage_submitBtn_01 StoryFormPage_desktop_01 update-button" type="button" style="left: 37%" data-index=${targetId}>
                                        <span><span class="Button_children_01 ">수정</span></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>
        `;

        if (parent.querySelector(".fileInfo")) {
            const fileName = parent.querySelector(".fileInfo").getAttribute("data-file-name");
            const filePath = parent.querySelector(".fileInfo").getAttribute("data-file-path");
            const encodedFilePath = encodeURIComponent(`${filePath}/${fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공

            document.querySelector(".hGJMVS").innerHTML = `
            <div class="ImageList__ImageWrapper-sc-9v1mt2-1 kZTsQf">
                <div class="Image__Wrapper-v97gyx-0 gDuKGF uploadFile"
                data-file-name="${fileName}" data-file-path="${filePath}">
                    <div class="Ratio " style="display: block;">
                        <div class="Ratio-ratio " style="height: 0px; position: relative; width: 100%; padding-top: 50%;">
                            <div class="Ratio-content " style="height: 100%; left: 0px; position: absolute; top: 0px; width: 100%;">
                            <img src="/files/display?path=${encodedFilePath}" class="hVNKgp">
                            </div>
                        </div>
                    </div>
                    <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero'%3E %3Cpath fill='%23FFF' fill-opacity='0' d='M0 0h18v18H0z'/%3E %3Cg stroke='%23FFF' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E" class="ImageList__IconDelete-sc-9v1mt2-2 benIbu">
                </div>
            </div>
            `;
        }
    }

    if (e.target.closest(".delete-button")) {
        const id = e.target.closest(".delete-button").getAttribute("data-index");
        await challengeService.deleteMemberTaskPool(id);
        alert("삭제 완료")
        await challengeService.getList(challengeLayout.showList, 1)
    }

    if (e.target.closest(".update-button")) {
        const id = e.target.getAttribute("data-index");
        const updateContent = document.querySelector(".challenge-content");
        const imgWrapper = document.querySelector(".kZTsQf");
        console.log(imgWrapper)

        if (imgWrapper) {
            const uploadFile = document.querySelector(".uploadFile");
            const fileName = uploadFile.getAttribute("data-file-name")
            const filePath = uploadFile.getAttribute("data-file-path")

            await challengeService.updateMemberTaskPool({
                id: id,
                memberTaskPoolContent: updateContent.value,
                memberTaskPoolFilePath: filePath,
                memberTaskPoolFileName: fileName
            })
        } else {
            await challengeService.updateMemberTaskPool({
                id: id,
                memberTaskPoolContent: updateContent.value
            })

        }
        alert("수정완료")

        await challengeService.getList(challengeLayout.showList, 1)
    }
})