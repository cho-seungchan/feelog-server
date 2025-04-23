// 2025.04.05 조승찬
document.addEventListener("DOMContentLoaded", () => {
    // 파일 첨부 이벤트
    // 1. 버튼 클릭 → input 클릭 트리거(클릭된 것으로 처리)
    document.querySelector(".flog-button-14").addEventListener("click", () => {
        document.getElementById(":R19ukvf9ul7rlqakq:").click();
    });

    // 2. 파일 선택 → 파일명 표시
    document.getElementById(":R19ukvf9ul7rlqakq:").addEventListener("change", (e) => {
        if (document.getElementById(":R19ukvf9ul7rlqakq:").files.length > 0) {
            document.querySelector(".flog-p-17").textContent = `${
                document.getElementById(":R19ukvf9ul7rlqakq:").files[0].name
            }`;

            // 2025.04.22 조승찬 :: 파일 첨부 이벤트 보완
            const file = e.target.files[0];

            // multipart/form-data 형식으로 데이터를 자동 처리
            const formData = new FormData();
            formData.append("file", file);
            // 서버로 전송하여 path와 썸네일 생성
            inputFileUpload(formData);

        } else {
            document.querySelector(".flog-p-17").textContent = "파일을 선택해 주세요.";
        }
    });

    // 채널 제목 설명 url 포커스 색상 변경 처리
    document.querySelector("#\\:R1b9ukvf9ul7rlqakq\\:").addEventListener("focus", (e) => {
        e.target.closest(".flog-div-83").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R1b9ukvf9ul7rlqakq\\:").addEventListener("blur", (e) => {
        e.target.closest(".flog-div-83").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R1r9ukvf9ul7rlqakq\\:").addEventListener("focus", (e) => {
        e.target.closest(".flog-div-85").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R1r9ukvf9ul7rlqakq\\:").addEventListener("blur", (e) => {
        e.target.closest(".flog-div-85").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R2b9ukvf9ul7rlqakq\\:").addEventListener("focus", (e) => {
        e.target.closest(".flog-div-83").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R2b9ukvf9ul7rlqakq\\:").addEventListener("blur", (e) => {
        e.target.closest(".flog-div-83").classList.toggle("Feelog-focused");
    });

    // 새채널 만들기 버튼 이벤트
    document.querySelector(".flog-button-15").addEventListener("click", (e) => {

        e.preventDefault();

        // 타이틀 미입력 시
        if (document.querySelector(".flog-input-1").value.trim() == "") {
            document.querySelector(".flog-div-43").textContent = "채널 제목을 입력하세요";
            document.querySelector(".flog-div-40").style.display = "block";
            return;
        }

        const inputField = document.querySelector(".flog-input-4");
        const channelUrl = inputField.value.trim();
        const regex = /^[a-z0-9\-]{6,45}$/; // 최소 6자, 최대 45자, 영문 소문자/숫자/대시(-)만 허용

        if (!regex.test(channelUrl)) {
            document.querySelector(".flog-div-43").textContent = "URL은 작성 규칙을 준수하세요.";
            document.querySelector(".flog-div-40").style.display = "block";
            return;
        }

        // 이미지 파일 정보를 input(파라미터) 값으로 저장
        if (document.querySelector(".flog-div-78 .uploadFile")){

            const inputFileName = document.createElement("input");
            inputFileName.type = "hidden";
            inputFileName.name = `channelFileName`;
            inputFileName.value = document.querySelector(".flog-div-78").querySelector(".uploadFile").dataset.fileName;
            document.querySelector(".flog-form-1").appendChild(inputFileName);

            const inputFilePath = document.createElement("input");
            inputFilePath.type = "hidden";
            inputFilePath.name = `channelFilePath`;
            inputFilePath.value = document.querySelector(".flog-div-78").querySelector(".uploadFile").dataset.filePath;
            document.querySelector(".flog-form-1").appendChild(inputFilePath);

        }

        document.querySelector(".flog-form-1").submit();
    });

    // 모달창 확인, x 버튼 클릭 이벤트
    document.querySelector(".flog-button-10").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
    document.querySelector(".flog-svg-6").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
});
