
const $storyBuyWrap = $('.theme-wrap');

$(window).scroll(function () {
    let height = $(document).scrollTop();
    console.log(height);
    if(height > 1500){
        $storyBuyWrap.addClass("not-fixed");
        $storyBuyWrap.removeClass("fixed");
        $storyBuyWrap.removeClass("story-buy-wrap");
    }else{
        $storyBuyWrap.removeClass("not-fixed");
        $storyBuyWrap.addClass("fixed");
        $storyBuyWrap.addClass("story-buy-wrap");
    }
})


