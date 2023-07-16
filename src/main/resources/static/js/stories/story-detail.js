const $editBtn = $('.edit-btn');
const $editBtnDetail = $('.edit-btn-detail');

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