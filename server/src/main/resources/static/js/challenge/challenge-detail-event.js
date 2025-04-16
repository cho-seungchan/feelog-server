//  2025.04.08 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 맵 보여주기 :: 카카오맵 처리하기
    let geocoder = new kakao.maps.services.Geocoder();
    geocoder.addressSearch("올림픽로 19-2", (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
            let coords = new kakao.maps.LatLng(35.409476, 127.396059);
            // let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            let mapContainer = document.getElementById("map"), // 지도를 표시할 div
                mapOption = {
                    center: coords,
                    level: 3, // 지도의 확대 레벨
                };
            // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
            let map = new kakao.maps.Map(mapContainer, mapOption);
            // 결과값으로 받은 위치를 마커로 표시합니다
            let marker = new kakao.maps.Marker({
                map: map,
                position: coords,
            });
            // map.relayout();
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);

            // 화면 확장 축소
            document.querySelector("#fullMap").addEventListener("click", (e) => {
                if (mapContainer.style.position === "fixed") {
                    mapContainer.style.position = "relative";
                    mapContainer.style.width = "100%";
                    mapContainer.style.height = "30vh";
                    mapContainer.style.zIndex = "";
                    document.querySelector("#fullMap").style.position = "absolute";
                } else {
                    mapContainer.style.position = "fixed";
                    mapContainer.style.top = "0";
                    mapContainer.style.left = "0";
                    mapContainer.style.width = "100%";
                    mapContainer.style.height = "100vh";
                    mapContainer.style.zIndex = "1000"; // 맵이 다른 요소 위에 오도록 설정
                    document.querySelector("#fullMap").style.position = "fixed";
                }
                map.relayout();
                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            });
            // 화면 확장 축소
        }
    });
});
