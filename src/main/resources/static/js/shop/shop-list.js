let count = 1;
let flag = false;

// 1쿠폰 선택
const $firstContainerWrap = $('.first-container-wrap');
const $firstContainerWrapSpan = $('.first-step-number');
const $firstContainerWrapH2 = $('.first-step-title');


//왼쪽 화살표
const $leftWrap= $('.left-arrow-wrap');
const $leftBtn = $('.left-arrow-wrap button');

//오른쪽 화살표
const $rightWrap= $('.right-arrow-wrap');
const $rightBtn = $('.right-arrow-wrap button');

// 지역 버튼들
const $locationBtns =$('.location-category');


//쿠폰들
const $coupons =$('.shop-list li');

//쿠폰 내용
const $couponsContents = $('.content-box')

//쿠폰 정보 담을 input 히든 태그
const $couponValue = $('.coupon-id');

// 쿠폰 안에 내용 div 태그들
const $couponsContentDivs = $('.content-box div')

// 다음 버튼
const $nextBtn = $('.next-btn button')

// 2 배송입력 태그
const $secondContent = $('.second-input-content');

// 필수입력 span
const $topContainerSpan = $('.top-container');

// 2 배송정보
const $secondTitleBtn = $('.title-btn');
const $secondTitleH2 = $('.title-btn h2');
const $numberTwo = $('.second-number');

// 배송정보 입력 input 태그들
const $deliveryInput = $('.input-wrap input');
// 오류 메세지
const $message = $('.input-error');

//구매하기 버튼
const $purchaseBtn = $('.coupon-buy-btn');

//수량 버튼
const $qtyBtn = $('.qty-btn');
// 수량
const $qtySpan = $('.qty-btn-label');
let qty = 1;

//총가격
const $pricePtag =$('.total-price');

/*--------------------------------------*/

// 버튼 클릭
$locationBtns.on("click", function(){
    locationName.text($(this).text())
    $locationBtns.removeClass("now-button");
    if(!$(this).hasClass("now-button")){
        $(this).addClass("now-button");
    }

    //선택된 쿠폰 초기화
    $couponsContentDivs.css("color", "#333")
    $couponValue.val("");

})

//지역이름
const locationName = $('.location-name');

//화살표 hover

$rightWrap.hover(
    function(){
    $rightBtn.css("background", "#f7f7f7")
    },function(){
    $rightBtn.css("background", "#fff")
})

$leftWrap.hover(
    function(){
    $leftBtn.css("background", "#f7f7f7")
    },function(){
    $leftBtn.css("background", "#fff")
})

//왼쪽 화살표 클릭
$leftWrap.on("click", function(){

    if(!flag){
        count-=2;
    }

    flag = true;

    $rightWrap.show();
    console.log("왼쪽" + count);
    $locationBtns.css("transform", `translateX(${-110*count}px)`)
    count--;
    console.log(count);
    if(count == -1){
        $leftWrap.hide();
    }
})

//오른쪽 화살표 클릭
$rightWrap.on("click", function(){


    if(flag){
        count+=2;
    }

    if(count == -1){
        count = 1;
    }

    flag = false;

    $leftWrap.show();
    console.log("오른쪽" + count);
    $locationBtns.css("transform", `translateX(${-110 * count}px)`)
    count++;
    if(count == 5){
        $rightWrap.hide();
    }

})

//쿠폰 선택시 선택효과및 밑에 input value에 쿠폰 id 값 넣기
$coupons.on("click", function(e){
  const thisContent = $(this).closest("li").find('.content-box').find("div");
    e.preventDefault();

    //다시 클릭하면 선택 풀리기
    if(thisContent.css("color") == "rgb(12, 72, 220)"){
        console.log("들어옴");
        $couponsContentDivs.css("color", "#333")
        $couponValue.val("");
        return;
    }

    //다른 쿠폰 선택할 때 마다 초기화
    $couponsContentDivs.css("color", "#333")
    $couponValue.val("");

    //선택 쿠폰 색 효과
    thisContent.css("color", "#0c48dc");
    // 밑에 input 히든에 선택 쿠폰 pk id 값 담기
    $couponValue.val($(this).attr("id"));

})


// 수량버튼
$qtyBtn.on("click", function(e) {
    console.log(e.target.value);
    if(e.target.value == '+'){
        $qtySpan.text(++qty);
    }else{
        if(qty == 1){return};
        $qtySpan.text(--qty)
    }

    $pricePtag.text(qty * 10_000 + "point");

})



// focus blur 효과
$deliveryInput.on("focus", function () {
    $(this).css("border-color", "rgb(12, 72, 220)")
})

$deliveryInput.on("blur", function () {
    $(this).css("border-color", "#e1e2e3")
})

// 우편 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }


            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zip-code').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address-detail").focus();
            $message.eq(1).hide();
            $message.eq(2).hide();
            $deliveryInput.eq(2).css("border-color","#e1e2e3");
            $deliveryInput.eq(3).css("border-color","#e1e2e3");
        }
    }).open();
}

$deliveryInput.on("keyup", function () {
    const thisMsg= $(this).closest("div.has-error").find('.input-error');
    console.log(thisMsg);
    thisMsg.hide();
})

$purchaseBtn.on("click", function () {

    if($couponValue.val() == ""){
        showWarnModal("쿠폰을 선택해주세요");
        return;
    }


    if($deliveryInput.eq(1).val() == ""){
        showWarnModal("받는 사람을 입력해주세요");
        $deliveryInput.eq(1).css("border-color","red");
        $message.eq(0).show();
        return;
    }

    if($deliveryInput.eq(2).val() == ""){
        showWarnModal("우편번호와 주소을 입력해주세요");
        $deliveryInput.eq(2).css("border-color","red");
        $deliveryInput.eq(3).css("border-color","red");
        $message.eq(1).show();
        $message.eq(2).show();
        return;
    }

    if($deliveryInput.eq(4).val() == ""){
        showWarnModal("상세주소를 입력해주세요");
        $deliveryInput.eq(4).css("border-color","red");
        $message.eq(3).show();
        return;
    }

    if($deliveryInput.eq(5).val() == ""){
        showWarnModal("휴대폰 번호를 입력해주세요");
        $deliveryInput.eq(5).css("border-color","red");
        $message.eq(4).show();
        return;
    }

    if(!fn_mbtlnumChk($deliveryInput.eq(5).val())){
        showWarnModal("올바른 휴대폰 번호가 아닙니다.");
        $deliveryInput.eq(5).css("border-color","red");
        $message.eq(4).show();
        return;
    }

    if($deliveryInput.eq(6).val() == ""){
        showWarnModal("배송 시 주의사항을 입력해주세요");
        $deliveryInput.eq(6).css("border-color","red");
        $message.eq(5).show();
        return;
    }

    if($deliveryInput.val() != ""){
        showWarnModal("쿠폰 구매가 완료되었습니다.")
    }

})

// 휴대폰 번호 검사
$deliveryInput.eq(5).on('input', function () {
    let numericVal = $(this).val().replace(/[^0-9]/g, '');

    if (numericVal.length > 11) {
        numericVal = numericVal.substr(0, 11); // 최대 11자리까지만 유지
    }

    $(this).val(numericVal);
});

// 휴대폰 정규식
function fn_mbtlnumChk(mbtlnum){
    var regExp = /^010\d{8}$/;
    if(!regExp.test(mbtlnum)){
        return false;
    }
    return true;
}

