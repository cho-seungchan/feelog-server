// 2025.04.05 조승찬

document.addEventListener("DOMContentLoaded", () => {
    let emailFlag = false;
    // 입력창 포커스(커서 위치) 이벤트
    document.querySelectorAll(".FeelogInput-input").forEach((input) => {
        // 처음 포커스 되었을 때( 에러 문구도 없고, classlist에 Feelog-focused도 없을 경우) 파란색으로 창을 표시
        input.addEventListener("focus", (e) => {
            if (
                !e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error") &&
                !e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-focused")
            ) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.add("Feelog-focused");
            }
        });

        // 포커스가 옮겨지고, 입력값이 올바르지 못 할 경우 경우 빨간색 메세지 표시
        input.addEventListener("blur", (e) => {
            if (!e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error")) {
                const helperTextDiv = document.createElement("div");
                helperTextDiv.setAttribute("aria-live", "assertive");
                helperTextDiv.setAttribute("class", "FeelogFormHelperText-root Feelog-error flog-div-52");
                if (e.target.id == ":r0:" && !emailFlag) {
                    helperTextDiv.setAttribute("id", ":r0:-helper-text");
                    helperTextDiv.textContent = "이메일을 입력해 주세요.";
                    e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-focused");
                    e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.add("Feelog-error");
                }
                e.target.closest(".flog-div-49").appendChild(helperTextDiv);
            }
        });
    });

    // 이메일 입력창 입력 이벤트
    // 입력된 이메일 유효성 확인
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    document.querySelector("#\\:r0\\:").addEventListener("keyup", (e) => {
        // 이메일 정상 입력 :: 이메일 플래그 true, Feelog-error,Feelog-focused 클래스명 삭제, 에러메세지 삭제(.Feelog-error.flog-div-52)
        if (emailRegex.test(document.querySelector("#\\:r0\\:").value)) {
            emailFlag = true;
            if (e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-error");
            }
            if (e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-focused")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-focused");
            }
            if (e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52")) {
                e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52").remove();
            }
            // 입력이 모두 완성된 경우 회원가입 버튼 활성화
            document.querySelector(".flog-button-11").removeAttribute("disabled");
            document.querySelector(".flog-button-11").classList.remove("Feelog-disabled");
            // 이메일 비정상 입력 :: 이메일 플래그 false, Feelog-error 클래스명 추가, 에러메세지 추가(.Feelog-error.flog-div-52)
        } else {
            emailFlag = false;
            if (!e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.add("Feelog-error");
            }
            if (!e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52")) {
                const helperTextDiv = document.createElement("div");
                helperTextDiv.setAttribute("aria-live", "assertive");
                helperTextDiv.setAttribute("class", "FeelogFormHelperText-root Feelog-error flog-div-52");
                helperTextDiv.setAttribute("id", ":r0:-helper-text");
                helperTextDiv.textContent = "이메일을 입력해 주세요.";
                e.target.closest(".flog-div-49").appendChild(helperTextDiv);
            }
            if (!document.querySelector(".flog-button-11").classList.contains("Feelog-disabled")) {
                document.querySelector(".flog-button-11").classList.add("Feelog-disabled");
                document.querySelector(".flog-button-11").setAttribute("disabled", "");
            }
        }
    });

    // 이메일 보내기 클릭 이벤트 ::
    document.querySelector(".flog-button-11").addEventListener("click", (e) => {
        e.preventDefault();
        // 입력 정보
        const email = document.querySelector("input[name='email']").value;
        const data = { memberEmail: email };
        requestPasswordIssue(data);

    });

    // 뒤로가기 버튼 클릭 :: 로그인 첫 페이지로 이동
    document.querySelector(".flog-p-10").addEventListener("click", () => {
        window.location.href = "/login/login";
    });


    // 모달창 확인, x 버튼 클릭 이벤트
    document.querySelector(".flog-button-10").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
    document.querySelector(".flog-svg-6").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
});
