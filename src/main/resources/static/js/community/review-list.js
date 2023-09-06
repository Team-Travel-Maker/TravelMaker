const $categoryBtns = $('.category-Item');
const writeBtn = $(".postWriteBtn-writeBtn");
const detailBtn = $(".goToDetail");
let memberId = $("input[name='memberId']").val();
let date = $("input[name='createTime']");


$(document).ready(function () {
    $(".selected").addClass("category-btn-list")
    console.log($categoryBtns);

    $categoryBtns.on('click',function(){
        $categoryBtns.removeClass("category-btn-list");
        if(!$(this).hasClass("category-btn-list")){
            $(this).addClass("category-btn-list");
        }
    });

    writeBtn.on('click', function () {
        console.log("글쓰기 버튼 클릭")
        $.ajax({
            type : 'get',           // 타입 (get, post, put 등등)
            url : '/community/board/write',           // 요청할 서버url
            async : true,            // 비동기화 여부 (default : true)
            headers : {              // Http header
                "Content-Type" : "application/json",
            },
            dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
            contentType: "application/json; charset=utf-8",

            data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
            }),
            success : function(result) { // 결과 성공 콜백함수
                console.log("성공 여부" + result);
            },
            error : function(request, status, error) { // 결과 에러 콜백함수
                console.log("에러 : " + error)
            }
        })
    })

    console.log(memberId);

//    사이드 메뉴(나의 커뮤니티로 이동)
    detailBtn.on('click', function (e) {
        $.ajax({
            type : 'get',           // 타입 (get, post, put 등등)
            url : '/community/board/detail',           // 요청할 서버url
            async : true,            // 비동기화 여부 (default : true)
            headers : {              // Http header
                "Content-Type" : "application/json",
            },
            dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
            contentType: "application/json; charset=utf-8",

            data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
            }),
            success : function(result) { // 결과 성공 콜백함수
                console.log("성공 여부" + result);
            },
            error : function(request, status, error) { // 결과 에러 콜백함수
                console.log("에러 : " + error)
            }
        })
    })


//     function SimpleDateTimeFormat(date, pattern) {
//         let dateString = pattern.replace(/(yyyy|MM|dd|HH|mm|ss|SSS)/g, function(match) {
//             let matchString = "";
//             switch(match) {
//                 case "yyyy":
//                     matchString = date.getFullYear();
//                     break;
//                 case "MM":
//                     matchString = date.getMonth() + 1;
//                     break;
//                 case "dd":
//                     matchString = date.getDate();
//                     break;
//                 case "HH":
//                     matchString = date.getHours();
//                     break;
//                 case "mm":
//                     matchString = date.getMinutes();
//                     break;
//                 case "ss":
//                     matchString = date.getSeconds();
//                     break;
//                 case "SSS":
//                     matchString = date.getMilliseconds();
//                     break;
//                 default :
//                     matchString = match;
//                     break;
//             }
//             if (match == "SSS") {
//                 if (matchString < 10) {
//                     matchString = "00" + matchString;
//                 } else if (matchString < 100) {
//                     matchString = "0" + matchString;
//                 }
//             } else {
//                 if ((typeof(matchString) == "number" && matchString < 10)) {
//                     matchString = "0" + matchString;
//                 }
//             }
//             return matchString;
//         });
//
//         return dateString;
//     }
//
// //    글 작성 경과시간 표시
//     function elapsedTime(date) {
//         // 초 (밀리초)
//         const seconds = 1;
//         // 분
//         const minute = seconds * 60;
//         // 시
//         const hour = minute * 60;
//         // 일
//         const day = hour * 24;
//
//         let today = new Date();
//         let elapsedTime = Math.trunc((today.getTime() - date.getTime()) / 1000);
//
//         let elapsedText = "";
//
//         if (elapsedTime < seconds) {
//             elapsedText = "방금 전";
//         } else if (elapsedTime < minute) {
//             elapsedText = elapsedTime + "초 전";
//         } else if (elapsedTime < hour) {
//             elapsedText = Math.trunc(elapsedTime / minute) + "분 전";
//         } else if (elapsedTime < day) {
//             elapsedText = Math.trunc(elapsedTime / hour) + "시간 전";
//         } else if (elapsedTime < (day * 15)) {
//             elapsedText = Math.trunc(elapsedTime / day) + "일 전";
//         } else {
//             elapsedText = SimpleDateTimeFormat(date, "yyyy.M.d");
//         }
//
//         return elapsedText;
//     }


    // 모든 게시글의 작성 시간 요소를 선택
        $("input[name='createTime']").each(function() {
            let createTime = $(this).val();
            let writeTime = new Date(createTime); // 작성 시간을 Date 객체로 파싱

            console.log("==============================================================")

            let currentTime = new Date(); // 현재 시간을 가져옴
            let timeDiff = currentTime - writeTime; // 시간 차이 계산 (밀리초 단위)

            // 시간 차이를 경과 시간으로 변환 (초, 분, 시간 등)
            let elapsedTime = calculateElapsedTime(timeDiff);

            // 해당 게시글의 경과 시간을 표시할 요소를 선택하고 텍스트 설정
            $(this).siblings(".postElapsedTime").text(elapsedTime);

        })

    // 시간 차이를 경과 시간으로 변환하는 함수
    function calculateElapsedTime(timeDiff) {
        let seconds = Math.floor(timeDiff / 1000);
        let minutes = Math.floor(seconds / 60);
        let hours = Math.floor(minutes / 60);
        let days = Math.floor(hours / 24);

        if (days > 0) {
            return days + "일 전";
        } else if (hours > 0) {
            return hours + "시간 전";
        } else if (minutes > 0) {
            return minutes + "분 전";
        } else {
            return "방금 전";
        }

    }



















})



