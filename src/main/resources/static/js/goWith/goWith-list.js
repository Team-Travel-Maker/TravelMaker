// // function reqList(){
// //     let sendData = {
// //         posts : [
// //             { localNo : 1, title : "서울가자", content : "같이가자", name: "정범진" },
// //             { localNo : 2, title : "경기가자", content : "같이가자", name: "정범진" },
// //             { localNo : 3, title : "충남가자", content : "같이가자", name: "정범진" },
// //             { localNo : 4, title : "부산가자", content : "같이가자", name: "정범진" }
// //         ]
// //     }
// // }
//
// $(document).ready(function() {
//     let selectLocal = $(".Button-local span:first").text();
//     console.log(selectLocal);
//
//     console.log("지역 : " + selectLocal);
//     $(".Button-local").on("click", function() {
//
//         let local = $(this).find("span.Button-label").text()
//
//         console.log("지역 : " + local);
//
//         $.ajax({
//             type : 'post',           // 타입 (get, post, put 등등)
//             url : '/api/goWith/goWith-list',           // 요청할 서버url
//             async : true,            // 비동기화 여부 (default : true)
//             headers : {              // Http header
//                 "Content-Type" : "application/json",
//                 "X-HTTP-Method-Override" : "POST"
//             },
//             dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
//             contentType: "application/json; charset=utf-8",
//
//             data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
//                 "local" : local,
//             }),
//             success : function(result) { // 결과 성공 콜백함수
//
//                 console.log("성공 여부" + result);
//             },
//             error : function(request, status, error) { // 결과 에러 콜백함수
//                 console.log("에러 : " + error)
//             }
//         })
//
//
//     })
// })
//
//
const $goWithList = $('.postItem');
console.log($goWithList);

$(document).ready(function() {
    let currentPage = 0;
    let currentRegion = null;

    function loadGoWithList() {
        $.get(`/api/goWith/goWith-list?region=${currentRegion}&page=${currentPage}&size=10`)
            .done(data => {
                const goWithList = data.content;
                const hasNextPage = data.hasNext;

                // goWithList.forEach(goWith => {
                //     const item = document.createElement("div");
                //     item.textContent = goWith.title;
                //     document.getElementById("goWithList").appendChild(item);
                // });

                if (hasNextPage) {
                    currentPage++;
                    loadGoWithList(); // 더 많은 데이터를 가져올 경우 재귀 호출로 처리
                }
            })
            .fail(error => {
                console.error("Error loading GoWith list:", error);
            });
    }

    // 지역 버튼 클릭 이벤트 처리
    $(".Button-local").on("click", function() {
        const local = $(this).find("span.Button-label").text();
        currentRegion = local;
        currentPage = 0;

        $("#goWithList").empty(); // 기존 목록 초기화
        loadGoWithList(); // 선택한 지역의 데이터 로드
    });
});