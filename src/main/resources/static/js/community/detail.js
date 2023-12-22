const $editBtn = $('.postcontent-menu-btn');
const $editBtnDetail = $('.edit-btn-detail');
const $updateBtn = $(".modify-btn-li");
const $deleteBtn = $(".delete-btn-detail");
let postId = $("input[name='postId']").val();

$(document).ready(function () {

$editBtn.on('click', function () {
    if($editBtnDetail.css("display") == "none") {
        $editBtnDetail.show();
    } else {
        $editBtnDetail.hide();
    }
})

$('html').click(function(e) {
    if(!$(e.target).hasClass("edit-svg")) {
        console.log("들어")
        $editBtnDetail.hide();
    }
});

    $deleteBtn.on('click', function () {
        $.ajax({
            type : "get",
            url : "/community/board/delete/" + postId,
            success : function (result) {
                console.log("삭제 성공!");
                location.href = "/community/board/list";
            },
            error : function (request, status, error) {
                console.log("삭제 실패!");
            }


        })
    })

    $updateBtn.on('click', function () {
        $.ajax({
            type : "get",
            url : "/community/board/update/" + postId,
            data : { "id" : postId },
            success : function (result) {
                console.log("수정 페이지로 이동!");
                location.href = "/community/board/update/" + postId;
            },
            error : function (request, status, error) {
                console.log("이동 실패!");
            }


        })
    })





})