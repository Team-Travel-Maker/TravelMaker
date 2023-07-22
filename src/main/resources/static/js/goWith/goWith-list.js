// 더미데이터
// function reqList(){
//     let sendData = {
//         posts : [
//             { localNo : 1, title : "서울가자", content : "같이가자", name: "정범진" },
//             { localNo : 2, title : "경기가자", content : "같이가자", name: "정범진" },
//             { localNo : 3, title : "충남가자", content : "같이가자", name: "정범진" },
//             { localNo : 4, title : "부산가자", content : "같이가자", name: "정범진" }
//         ]
//     }
// }

// $(document).ready(function() {
//     let selectLocal = $(".Button-local span:first").text();
//
//     console.log("지역 : " + selectLocal);
//     $(".Button-local").on("click", function() {
//
//         let local = $(this).find("span.Button-label").text()
//
//         console.log("지역 : " + local);
//
//
//
//
//
//         $.ajax({
//             type : 'post',           // 타입 (get, post, put 등등)
//             url : '/goWith/goWith-list',           // 요청할 서버url
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
