// 태그 슬라이드 버튼으로 스크롤 이동

// $(document).ready(function () {
//
//     $(".tagSlide-rightArrow").click(function (e) {
//         $(".tagSlide").scrollLeft($(".tagSlide").scrollLeft() + 680);
//         $(".tagSlide-arrow").css("display", "flex");
//         $(".tagSlide-rightArrow").css("display", "none");
//     })
//
//     $(".tagSlide-leftArrow").click(function (e) {
//         $(".tagSlide").scrollLeft($(".tagSlide").scrollLeft() - 680);
//         $(".tagSlide-leftArrow").css("display", "none");
//         $(".tagSlide-rightArrow").css("display", "flex");
//     })
//
// })



let count = 1;
let flag = false;

//왼쪽 화살표
const $leftWrap= $('.left-arrow-wrap');
const $leftBtn = $('.left-arrow-wrap button');

//오른쪽 화살표
const $rightWrap= $('.right-arrow-wrap');
const $rightBtn = $('.right-arrow-wrap button');

// 지역 버튼들
const $locationBtns =$('.location-category');

//지역이름
const locationName = $('.location-span');

// 버튼 클릭
$locationBtns.on("click", function(){
    locationName.text($(this).text())
    $locationBtns.removeClass("now-button");
    if(!$(this).hasClass("now-button")){
        $(this).addClass("now-button");
    }
})

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
    $locationBtns.css("transform", `translateX(${-300*count}px)`)
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
    $locationBtns.css("transform", `translateX(${-300 * count}px)`)
    count++;
    if(count == 3){
        $rightWrap.hide();
    }

})