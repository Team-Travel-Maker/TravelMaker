const $accountWrapper =$('.account-wrapper');

const $modalName = $('.modal-name');
const $modalPassword =$('.modal-password');
const $modalphone = $('.modal-phone');
$accountWrapper.each(function (i, account) {
    $(account).on('click', function () {
        if (i == 1) {
            $modalName.css('display','flex');
            console.log("emfdjdha");
        }
        if( i==2){
            $modalphone.css('display','flex');
        }
        if( i== 4){
            $modalPassword.css('display','flex');
        }
        if( i ==5){
            window.location.href= '/mypage/withdrawal';
        }
    });
});
//취소 버튼
const closebtn = $('.modal-submit-close');
//x버튼
const xbtn = $('.modal-btn');

//클릭하면 modal 안보이게하기
xbtn.on('click',function(){
    $modalName.css('display','none');
    $modalPassword.css('display','none');
    $modalphone.css('display','none');
});

closebtn.on('click',function(){
    $modalName.css('display','none');
    $modalPassword.css('display','none');
    $modalphone.css('display','none');
});








