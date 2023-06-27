// 1쿠폰 선택
const $firstContainerWrap = $('.first-container-wrap');
const $firstContainerWrapSpan = $('.first-step-number');
const $firstContainerWrapH2 = $('.first-step-title');


// 첫번째 내용 박스
const $firstContent = $('.first-input-content');

// 첫번째 수정
const $edit = $('.first-edit');

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
    $leftWrap.hide();
    $rightWrap.show();
    $locationBtns.css("transform", `translateX(20px)`)
})

//오른쪽 화살표 클릭
$rightWrap.on("click", function(){
    $rightWrap.hide();
    $leftWrap.show();
    $locationBtns.css("transform", `translateX(-460px)`)
})




//쿠폰 선택시 선택효과및 밑에 input value에 쿠폰 id 값 넣기
$coupons.on("click", function(e){
  const thisContent = $(this).closest("li").find('.content-box').find("div");
    e.preventDefault();

    //다른 쿠폰 선택할 때 마다 초기화
    $couponsContentDivs.css("color", "#333")
    $couponValue.val("");

    //선택 쿠폰 색 효과
    thisContent.css("color", "#0c48dc");
    // 밑에 input 히든에 선택 쿠폰 pk id 값 담기
    $couponValue.val($(this).attr("id"));
})

//쿠폰 선택해야 다음 버튼 누를 수 있게하기


$nextBtn.on("click", function () {
    if($couponValue.val() == ""){
        showWarnModal("쿠폰을 선택해주세요");
        return;
    }

    if($couponValue.val() != ""){

        //1 비활성화
        $firstContent.hide();
        $secondTitleBtn.eq(0).removeClass("active");
        $secondTitleBtn.eq(0).addClass("no-active");
        $firstContainerWrap.removeClass("active");
        $firstContainerWrapSpan.removeClass("active");
        $firstContainerWrapH2.removeClass("active");
        $firstContainerWrap.addClass("no-active");
        $firstContainerWrapSpan.addClass("no-active");
        $firstContainerWrapH2.addClass("no-active");

        //1 edit 활성화
        $edit.show();
        
        //2 배송정보 활성화
        $secondTitleBtn.eq(1).removeClass("no-active");
        $secondTitleBtn.eq(1).addClass("active");
        $secondTitleH2.eq(1).removeClass("no-active");
        $secondTitleH2.eq(1).addClass("active");
        $numberTwo.removeClass("no-active");
        $numberTwo.addClass("active");
        
        $secondContent.show();
        $topContainerSpan.show();
    }
})

// edit 버튼효과
$edit.on("click", function () {

    //1 비활성화
    $firstContent.show();
    $secondTitleBtn.eq(0).removeClass("no-active");
    $secondTitleBtn.eq(0).addClass("active");
    $firstContainerWrap.removeClass("no-active");
    $firstContainerWrapSpan.removeClass("no-active");
    $firstContainerWrapH2.removeClass("no-active");
    $firstContainerWrap.addClass("active");
    $firstContainerWrapSpan.addClass("active");
    $firstContainerWrapH2.addClass("active");

    //1 edit 활성화
    $edit.hide();

    //2 배송정보 활성화
    $secondTitleBtn.eq(1).removeClass("active");
    $secondTitleBtn.eq(1).addClass("no-active");
    $secondTitleH2.eq(1).removeClass("active");
    $secondTitleH2.eq(1).addClass("no-active");
    $numberTwo.removeClass("active");
    $numberTwo.addClass("no-active");

    $secondContent.hide();
    $topContainerSpan.hide();

})
