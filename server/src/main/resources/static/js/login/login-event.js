// 25.04.05 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 로그인 버튼 클릭 이벤트
    document.querySelectorAll(".flog-a-13").forEach((button) => {
        button.addEventListener("click", (e) => {
            // 이메일로그인
            if (e.target.closest("button.flog-a-13")) {
                window.location.href = "/login/email-login";
            }
        });
    });

    // 로그인 상태 유지 체크 :: 클래스 명 변경, svg태그 생성/삭제
    document.querySelector(".FeelogCheckbox-input.flog-input-3").addEventListener("change", (e) => {
        // svg 태그가 없으면 클래스명 변경, svg 태그 생성
        // svg 태그가 있으면 클래스명 원상복구, svg 태그 삭제
        if (document.querySelector(".FeelogSvgIcon-root.FeelogSvgIcon-sizeMd.flog-svg-6") == null) {
            // 클래스명 변경
            document.querySelector(".FeelogCheckbox-action.flog-span-8").className =
                "FeelogCheckbox-action Feelog-checked flog-span-8";
            document.querySelector(".FeelogCheckbox-checkbox.flog-span-7").className =
                "FeelogCheckbox-checkbox Feelog-checked flog-span-10";
            document.querySelector(
                ".FeelogCheckbox-root.FeelogCheckbox-variantOutlined.FeelogCheckbox-colorNeutral.FeelogCheckbox-sizeSm.flog-span-6"
            ).className =
                "FeelogCheckbox-root Feelog-checked FeelogCheckbox-variantSolid FeelogCheckbox-colorPrimary FeelogCheckbox-sizeSm flog-span-9";

            // <svg> 요소 생성
            const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
            svg.setAttribute("focusable", "false");
            svg.setAttribute("aria-hidden", "true");
            svg.setAttribute("viewBox", "0 0 24 24");
            svg.setAttribute("data-testid", "CheckIcon");
            svg.classList.add("FeelogSvgIcon-root", "FeelogSvgIcon-sizeMd", "flog-svg-6");

            // <path> 요소 생성
            const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
            path.setAttribute(
                "d",
                "M9 16.17 5.53 12.7a.9959.9959 0 0 0-1.41 0c-.39.39-.39 1.02 0 1.41l4.18 4.18c.39.39 1.02.39 1.41 0L20.29 7.71c.39-.39.39-1.02 0-1.41a.9959.9959 0 0 0-1.41 0L9 16.17z"
            );

            // <path>를 <svg> 안에 추가
            svg.appendChild(path);

            // <svg>를 추가
            document.querySelector(".FeelogCheckbox-checkbox.Feelog-checked.flog-span-10").appendChild(svg);
        } else {
            // 클래스명 원상복구
            document.querySelector(".FeelogCheckbox-action.Feelog-checked.flog-span-8").className =
                "FeelogCheckbox-action flog-span-8";
            document.querySelector(".FeelogCheckbox-checkbox.Feelog-checked.flog-span-10").className =
                "FeelogCheckbox-checkbox flog-span-7";
            document.querySelector(
                ".FeelogCheckbox-root.Feelog-checked.FeelogCheckbox-variantSolid.FeelogCheckbox-colorPrimary.FeelogCheckbox-sizeSm.flog-span-9"
            ).className =
                "FeelogCheckbox-root FeelogCheckbox-variantOutlined FeelogCheckbox-colorNeutral FeelogCheckbox-sizeSm flog-span-6";

            // <svg> 요소 삭제
            if (document.querySelector(".FeelogSvgIcon-root.FeelogSvgIcon-sizeMd.flog-svg-6") != null) {
                document.querySelector(".FeelogSvgIcon-root.FeelogSvgIcon-sizeMd.flog-svg-6").remove();
            }
        }
    });
});
