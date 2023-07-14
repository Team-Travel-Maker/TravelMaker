$(document).ready(function() {
    let selectLocal = $(".Button-local span:first").text();

    console.log("지역 : " + selectLocal);
    $(".Button-local").on("click", function() {

        let local = $(this).find("span.Button-label").text()

        console.log("지역 : " + local);

        $.ajax({
            type : 'post',           // 타입 (get, post, put 등등)
            url : '/goWith/goWith-list',           // 요청할 서버url
            async : true,            // 비동기화 여부 (default : true)
            headers : {              // Http header
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
            data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
                "local" : local,
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
