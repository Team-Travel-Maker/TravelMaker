$(document).ready(function() {
    $('#cType').val({"REVIEW": "일반 후기"})

    // 텍스트 영역의 높이를 자동으로 조절하는 함수
    function autoResizeTextarea() {
        // 모든 텍스트 영역 요소를 선택합니다
        $('.AutoTextarea_AutoTextarea').each(function() {
            let $this = $(this);

            // 스크롤 높이를 임시로 0으로 설정한 후, 스크롤 높이를 콘텐츠 높이에 맞게 조절합니다
            $this.css('height', 0);
            $this.css('height', $this[0].scrollHeight + 'px');
        });
    }

    // 페이지 로드 시와 텍스트 영역 내용이 변경될 때 자동으로 autoResizeTextarea 함수를 호출합니다
    autoResizeTextarea();

});


//등록하기 버튼 색 들어오게하기
const btn = $('.Button_root');

$('.AutoTextarea_AutoTextarea').on('input',function(){

    const write1 = $('.AutoTextarea_AutoTextarea').eq(0).val().length;
    const write2 = $('.AutoTextarea_AutoTextarea').eq(2).val().length;
    if(write1 && write2){
        btn.css('backgroundColor','#36f');
        btn.css('color','#fff');
        btn.prop("disabled", false);
    }else if(!write1 || !write2){
        btn.css('backgroundColor', '');
        btn.css('color', '');
    }
});


//첨부파일
function openFile(){
    document.getElementById('upload1').click();

}
// 파일 선택 이벤트 처리 함수
document.getElementById('upload1').addEventListener('change', handleFileSelect);
function handleFileSelect(event) {
    // 선택한 파일의 정보를 가져옵니다
    const file = event.target.files[0];
}

let input = document.querySelector('input')
let tagify = new Tagify(input);

// 태그가 추가되면 이벤트 발생
tagify.on('add', function() {
    console.log(tagify.value); // 입력된 태그 정보 객체
})

// 글 등록 버튼
const writeForm = $(".rt-btn");

//
let form = new FormData();

$(document).ready(function() {
    $("button.rt-btn").on("click", function (form) {
        let communityType = $("#communityType option:selected").val();
        let postTitle = $('[name="postTitle"]').val();
        let postContent = $('[name="postContent"]').val();
        // console.log(`${communityType},${postTitle}, ${postContent}`)

        let postDTO = {
            communityType : communityType,
            postTitle : postTitle,
            postContent : postContent
        }

        console.log(JSON.stringify(postDTO))
        $.ajax({
            type : 'post',           // 타입 (get, post, put 등등)
            url : '/api/communities/board/write',           // 요청할 서버url
            async : true,            // 비동기화 여부 (default : true)
            // enctype: "text",
            processData : false,
            contentType : 'application/json',
            data : JSON.stringify(postDTO),
            success : function(result) { // 결과 성공 콜백함수
                console.log("성공 여부" + result);
            },
            error : function(request, status, error) { // 결과 에러 콜백함수
                console.log("에러 : " + error)
            }
        })

    });
});