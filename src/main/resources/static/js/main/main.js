/*슬라이드 배너*/
const banner = document.querySelector("div.banner-list");
let count = 1;


// 슬라이드 함수
function autoSlide(){
    banner.style.transition = "transform 0.5s"
    count ++;
    if(count == 5){
        banner.style.transform = `translate(-${782 + 1084 * count}px)`;
        setTimeout(function(){
            banner.style.transition = "transform 0s"
            banner.style.transform = "translate(-1866px)";
        }, 500);
        count = 1;
    }else {
        // 왼쪽으로 -782(고정값) + 1084 * count 만큼 이동한다.
        banner.style.transform = `translate(-${782 + 1084 * count}px)`;
    }
}

banner.style.transform = "translate(-1866px)";

// 4초마다 슬라이드 함수 실행
let inter = setInterval(autoSlide, 4000);

// prev, next button 구현
const arrows = document.querySelectorAll(".arrow");
let arrowButtonCheck = true;
arrows.forEach(arrow => {
    arrow.addEventListener("click", function(){
        if(arrowButtonCheck){
            arrowButtonCheck = false;
            clearInterval(inter);
            banner.style.transition = "transform 0.5s"
            let arrowName = arrow.className;
            if(arrowName == 'arrow top-banner-prev-button'){
                count--;
                if(count == 0){
                    banner.style.transform = `translate(-782px)`;
                    setTimeout(function(){
                        banner.style.transition = "transform 0s"
                        banner.style.transform = `translate(-${782 + 1084*4}px)`;
                    }, 500);
                    count = 4;
                }else{
                    banner.style.transform = `translate(-${782 + 1084 * count}px)`;
                }
            }else{
                count++;
                if(count == 5) {
                    banner.style.transform = `translate(-${782 + 1084*count}px)`;
                    setTimeout(function(){
                        banner.style.transition = "transform 0s"
                        banner.style.transform = "translate(-1866px)";
                    }, 500);
                    count = 0;
                }else{
                    banner.style.transform = `translate(-${782 + 1084*count}px)`;
                }
            }
            inter = setInterval(autoSlide, 4000);
            setTimeout(function(){
                arrowButtonCheck = true;
            }, 500);
        }
    });
});
