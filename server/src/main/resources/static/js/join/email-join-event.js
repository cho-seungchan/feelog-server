// 2025.04.04 조승찬

document.addEventListener("DOMContentLoaded", () => {
    let emailFlag = false,
        passwordFlag = false,
        nickNameFlag = false;
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
                } else if (e.target.id == ":r1:" && !passwordFlag) {
                    helperTextDiv.setAttribute("id", ":r1:-helper-text");
                    helperTextDiv.textContent = "비밀번호를 입력해 주세요.";
                    e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-focused");
                    e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.add("Feelog-error");
                } else if (e.target.id == ":r2:" && !nickNameFlag) {
                    helperTextDiv.setAttribute("id", ":r1:-helper-text");
                    helperTextDiv.textContent = "닉네임을 입력해 주세요.";
                    e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-focused");
                    e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.add("Feelog-error");
                }

                e.target.closest(".flog-div-49").appendChild(helperTextDiv);
            }
        });
    });

    // 비밀번호 숨기기 표시하기 버튼 클릭 이벤트
    const toggleBtn = document.querySelector(".FeelogIconButton-root");
    const input = toggleBtn.closest(".flog-div-50").querySelector("input");
    const svg = toggleBtn.querySelector("svg");

    // 두 상태의 SVG 데이터
    const ICON_EYE = {
        viewBox: "0 0 24 24",
        path: "M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z",
    };

    const ICON_EYE_OFF = {
        viewBox: "0 0 20 20",
        path: "m1.328 2.363 3.148 2.334C5.94 3.59 7.764 2.71 10.01 2.71c2.604 0 4.72 1.204 6.25 2.637 1.53 1.4 2.539 3.092 3.027 4.264.098.26.098.553 0 .814-.423 1.041-1.302 2.539-2.604 3.841l2.926 2.283c.358.26.423.748.13 1.074-.26.358-.749.423-1.074.13L.384 3.567c-.358-.26-.423-.749-.13-1.074.26-.358.749-.423 1.074-.13Zm4.417 3.343 1.498 1.172a4.159 4.159 0 0 1 2.767-1.042c2.278 0 4.166 1.888 4.166 4.167 0 .716-.162 1.367-.488 1.92l1.758 1.367c1.14-1.106 1.92-2.376 2.311-3.287-.423-.977-1.302-2.344-2.572-3.516-1.334-1.237-3.06-2.213-5.175-2.213-1.66 0-3.06.585-4.265 1.432Zm6.673 5.24c.13-.292.196-.618.196-.943a2.612 2.612 0 0 0-2.604-2.604h-.065c.032.195.065.358.065.52 0 .358-.098.651-.228.944l2.636 2.084Zm1.693 5.307a8.521 8.521 0 0 1-4.101 1.041c-2.637 0-4.753-1.172-6.283-2.604-1.53-1.432-2.539-3.125-3.027-4.264a1.167 1.167 0 0 1 0-.814 13 13 0 0 1 1.595-2.67l1.204.977a10.21 10.21 0 0 0-1.27 2.084c.456.976 1.303 2.376 2.572 3.548 1.335 1.237 3.06 2.18 5.209 2.18.976 0 1.888-.194 2.734-.553l1.367 1.075Zm-2.864-2.246c-.391.097-.814.162-1.27.162a4.175 4.175 0 0 1-4.167-4.166c0-.065.033-.163.033-.26l1.823 1.432a2.522 2.522 0 0 0 1.758 1.367l1.823 1.465Z",
    };

    toggleBtn.addEventListener("click", () => {
        const isPassword = input.type === "password";

        // toggle input type
        input.type = isPassword ? "text" : "password";

        // toggle aria-label
        toggleBtn.setAttribute("aria-label", isPassword ? "비밀번호 숨기기" : "비밀번호 표시하기");

        // toggle icon path & viewBox
        const iconData = isPassword ? ICON_EYE_OFF : ICON_EYE;
        svg.setAttribute("viewBox", iconData.viewBox);
        svg.querySelector("path").setAttribute("d", iconData.path);
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
            // 세가지 입력이 모두 완성된 경우 회원가입 버튼 활성화
            if (passwordFlag && nickNameFlag) {
                document.querySelector(".flog-button-11").removeAttribute("disabled");
                document.querySelector(".flog-button-11").classList.remove("Feelog-disabled");
            }
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

    // 입력된 비밀번호 유효성 확인 : 대/소문자, 숫자, 특수문자 중 2가지 이상의 조합으로 공백 없이 10자
    const passwordRegex = /^(?=.*[A-Za-z].*)(?=.*\d.*|.*[\W_].*)[A-Za-z\d\W_]{10,}$/;
    document.querySelector("#\\:r1\\:").addEventListener("keyup", (e) => {
        // 비번 정상 입력 :: 비번 플래그 true, Feelog-error 클래스명 삭제, 에러메세지 삭제(.Feelog-error.flog-div-52)
        if (passwordRegex.test(document.querySelector("#\\:r1\\:").value)) {
            passwordFlag = true;
            if (e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-error");
            }
            if (e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-focused")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-focused");
            }
            if (e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52")) {
                e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52").remove();
            }
            // 세가지 입력이 모두 완성된 경우 회원가입 버튼 활성화
            if (emailFlag && nickNameFlag) {
                document.querySelector(".flog-button-11").removeAttribute("disabled");
                document.querySelector(".flog-button-11").classList.remove("Feelog-disabled");
            }
            // 비번 비정상 입력 :: 비번 플래그 false, Feelog-error 클래스명 추가, 에러메세지 추가(.Feelog-error.flog-div-52)
        } else {
            passwordFlag = false;
            if (!e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.add("Feelog-error");
            }
            if (!e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52")) {
                const helperTextDiv = document.createElement("div");
                helperTextDiv.setAttribute("aria-live", "assertive");
                helperTextDiv.setAttribute("class", "FeelogFormHelperText-root Feelog-error flog-div-52");
                helperTextDiv.setAttribute("id", ":r1:-helper-text");
                helperTextDiv.textContent = "비밀번호를 입력해 주세요.";
                e.target.closest(".flog-div-49").appendChild(helperTextDiv);
            }
            if (!document.querySelector(".flog-button-11").classList.contains("Feelog-disabled")) {
                document.querySelector(".flog-button-11").classList.add("Feelog-disabled");
                document.querySelector(".flog-button-11").setAttribute("disabled", "");
            }
        }
    });

    // 입력된 닉네임 유효성 확인 : 1자 이상 입력하면 정상
    document.querySelector("#\\:r2\\:").addEventListener("keyup", (e) => {
        // 닉네님 정상 입력 :: 닉네임 플래그 true, Feelog-error 클래스명 삭제, 에러메세지 삭제(.Feelog-error.flog-div-52)
        if (e.target.value.length > 0) {
            nickNameFlag = true;
            if (e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-error");
            }
            if (e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-focused")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.remove("Feelog-focused");
            }
            if (e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52")) {
                e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52").remove();
            }
            // 세가지 입력이 모두 완성된 경우 회원가입 버튼 활성화
            if (emailFlag && passwordFlag) {
                document.querySelector(".flog-button-11").removeAttribute("disabled");
                document.querySelector(".flog-button-11").classList.remove("Feelog-disabled");
            }
            // 닉네임 비정상 입력 :: 닉네임 플래그 false, Feelog-error 클래스명 추가, 에러메세지 추가(.Feelog-error.flog-div-52)
        } else {
            nickNameFlag = false;
            if (!e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.contains("Feelog-error")) {
                e.target.closest(".flog-div-49").querySelector(".flog-div-50").classList.add("Feelog-error");
            }
            if (!e.target.closest(".flog-div-49").querySelector(".Feelog-error.flog-div-52")) {
                const helperTextDiv = document.createElement("div");
                helperTextDiv.setAttribute("aria-live", "assertive");
                helperTextDiv.setAttribute("class", "FeelogFormHelperText-root Feelog-error flog-div-52");
                helperTextDiv.setAttribute("id", ":r1:-helper-text");
                helperTextDiv.textContent = "닉네임을 입력해 주세요.";
                e.target.closest(".flog-div-49").appendChild(helperTextDiv);
            }
            if (!document.querySelector(".flog-button-11").classList.contains("Feelog-disabled")) {
                document.querySelector(".flog-button-11").classList.add("Feelog-disabled");
                document.querySelector(".flog-button-11").setAttribute("disabled", "");
            }
        }
    });

    // 뒤로가기 버튼 클릭 :: 회원가입 첫 페이지로 이동
    document.querySelector(".flog-p-10").addEventListener("click", () => {
        window.location.href = "../join/join.html";
    });

    // 로그인 버튼 이벤트 :: 서버에서 결과 받아서 뿌리는 거로 변경 필요
    document.querySelector(".flog-button-11").addEventListener("click", (e) => {
        e.preventDefault();
        document.querySelector(".flog-div-40").style.display = "block";
    });

    // 모달창 확인, x 버튼 클릭 이벤트
    document.querySelector(".flog-button-10").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
    document.querySelector(".flog-svg-6").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
});
