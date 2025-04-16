// 지도 생성
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

const addressInput = document.querySelector("#coursePath");

addressInput.addEventListener("keyup", (e) => {
    if (e.key === "Enter") {
        let geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch(addressInput.value, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
                let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
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
                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            } else {
                alert("🚫 주소를 찾을 수 없습니다. 다시 입력해주세요.");
            }
        });
    }
});

console.log(getInfo());
