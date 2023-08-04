$(document).ready(function() {
    // 텍스트 영역의 높이를 자동으로 조절하는 함수
    function autoResizeTextarea() {
        // 모든 텍스트 영역 요소를 선택합니다
        $('.AutoTextarea_AutoTextarea').each(function() {
            var $this = $(this);

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

var input = document.querySelector('input')
var tagify = new Tagify(input);

// 태그가 추가되면 이벤트 발생
tagify.on('add', function() {
    console.log(tagify.value); // 입력된 태그 정보 객체
})
