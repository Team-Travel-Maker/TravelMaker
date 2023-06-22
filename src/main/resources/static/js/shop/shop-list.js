//왼쪽 화살표
const leftWrap= $('.left-arrow-wrap');
const leftBtn = $('.left-arrow-wrap button');

//오른쪽 화살표
const rightWrap= $('.right-arrow-wrap');
const rightBtn = $('.right-arrow-wrap button');

//버튼들
const locationBtns =$('.location-category');

// 버튼 클릭
locationBtns.on("click", function(){
    locationName.text($(this).text())
    locationBtns.removeClass("now-button");
    if(!$(this).hasClass("now-button")){
        $(this).addClass("now-button");
    }
})

//지역이름
const locationName = $('.location-name');

//화살표 hover

rightWrap.hover(
    function(){
    rightBtn.css("background", "#f7f7f7")
    },function(){
    rightBtn.css("background", "#fff")
})

leftWrap.hover(
    function(){
    leftBtn.css("background", "#f7f7f7")
    },function(){
    leftBtn.css("background", "#fff")
})

//왼쪽 화살표 클릭
leftWrap.on("click", function(){
    leftWrap.hide();
    rightWrap.show();
    locationBtns.css("transform", `translateX(20px)`)
})

//오른쪽 화살표 클릭
rightWrap.on("click", function(){
    rightWrap.hide();
    leftWrap.show();
    locationBtns.css("transform", `translateX(-460px)`)
})