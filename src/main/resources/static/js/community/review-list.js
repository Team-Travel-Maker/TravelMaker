const $categoryBtns = $('.category-Item');
const writeBtn = $(".postWriteBtn-writeBtn")

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
        $.ajax({
            type : 'get',           // 타입 (get, post, put 등등)
            url : '/api/communities/board/write',           // 요청할 서버url
            async : true,            // 비동기화 여부 (default : true)
            headers : {              // Http header
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
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

















})



