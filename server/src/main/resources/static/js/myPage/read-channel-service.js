//  2025.04.23 조승찬

// 2025.04.23 조승찬 :: 입력 파일 전송 후 썸네일을 받아서 화면 생성
function inputFileUpload(formData){
    return fetch("/files/upload", {
        method: "post",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            createThumbnail(data.thumbnail);  // json 방식의 메서드로 부터 직접 @GetMapping("/reply-list/{feedId}") 호출하는 방식
        })
        .catch(error => {
            console.error("파일 데이타 업로드 중 오류", error);
            throw error;
        });
};

