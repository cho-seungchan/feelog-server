const writeChallengeButton = document.querySelector(".add-challenge");

writeChallengeButton.addEventListener("click", (e) =>{
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
                            <div style="max-width: 1024px">
                                <div class="Section_header__header01 spacing-4">
                                    <h3 class="Section_title_title01">
                                        챌린지 제목
                                    </h3>
                                </div>
                                <div class="Section_content_section01">
                                    <div class="TextField_textField__filed01 TextField_lg_01">
                                        <label></label>
                                        <div class="TextField_field_01">
                                            <input type="text" name="courseName" placeholder="제목을 입력해 주세요" maxlength="40" class="Input_input__M2Q3Y Input_lg__MDE4M challenge-title" aria-invalid="false">
                                        </div>
                                        <em class="HelperMessage_helperMessage_helper01 title-count">0/40</em>
                                    </div>
                                </div>
                            </div>
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
                                        <textarea name="courseContent" maxlength="1000" placeholder="내용 입력" class="Textarea_textarea_01"></textarea>
                                        <p class="StorySummaryField_text_01">
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
                                        <label class="inputImage_wrap_01">
                                            <input type="file" name="courseFileName-input" accept=".jpg, .jpeg, .png">
                                            <span>
                                                사진
                                                첨부하기</span>
                                            <div id="courseFile-wrap"></div>
                                        </label>
                                        <div class="inputImage_footer_01"></div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section class="Section_container_section01 StoryEditorWrapperItem_container_01" style="max-width: 1024px"></section>
                        <section class="Section_container_section01 StoryEditorWrapperComingItem_container_01" style="max-width: 1024px"></section>
                        <section class="Section_container_section01 TagsField_container_01 spacing-9"></section>
                    </div>
                    <div class="SaveButtonFooter_footer_01 SaveButtonFooter_expand_01">
                        <div class="">
                            <div class="StoryFormPage_saveButtonWrapper_01">
                                <button class="Button_button_01 Button_primary_01 Button_contained_01 Button_md_01 StoryFormPage_submitBtn_01 StoryFormPage_desktop_01 save-button" type="button">
                                    <span><span class="Button_children_01">저장</span></span>
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

htmlWrap.addEventListener("keyup", (e)=>{
    if(e.target.classList.contains("challenge-title")){
        const titleCount = document.querySelector(".title-count");

        titleCount.innerText = `${e.target.value.length}/40`;
    }
})