/*메인 내용 슬라이드 버튼*/
const leftBtns = document.querySelectorAll('.list-left-btn');
const rightBtns = document.querySelectorAll('.list-right-btn');
const contentBoxes = document.getElementsByClassName('title-list-slick-track');

let list = {};
let rightFlag = false;
let checkflag = false;


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


/*아래 목록 슬라이드 효과*/

/*오른쪽 버튼*/
rightBtns.forEach((rightBtn,index) => {
    if(!rightFlag){ list[`right${index+1}`] = 1; if(index==3){rightFlag=true;}}
    rightBtn.addEventListener("click", function (e) {
        /*가까운 거 찾기*/
       const listTrack = e.currentTarget.parentNode.parentNode.parentNode.querySelector('.title-list-slick-track');
       const leftBtn = e.currentTarget.parentNode.parentNode.parentNode.querySelector('.list-left-btn');
       const counts = rightBtn.classList[2];

       if(checkflag){
           list[`right${counts}`]++;
           checkflag=false;
       }

       leftBtn.classList.remove('not-allow');
       leftBtn.disabled=false;

       listTrack.style.transition = "transform 0.5s"
       listTrack.style.transform = `translate(-${1080 * list[`right${counts}`] }px)`;
        list[`right${counts}`]++
        if( list[`right${counts}`] == 6){
            rightBtn.classList.add('not-allow')
            rightBtn.disabled=true;
        }
    })
})

/*왼쪽 버튼*/
leftBtns.forEach((leftBtn,index) => {
    leftBtn.addEventListener("click", function (e) {
        /*가까운 거 찾기*/
        const listTrack = e.currentTarget.parentNode.parentNode.parentNode.querySelector('.title-list-slick-track');
        const rightBtn = e.currentTarget.parentNode.parentNode.parentNode.querySelector('.list-right-btn');
        const counts = leftBtn.classList[2];

        if(!checkflag){
            list[`right${counts}`]--;
            checkflag=true;
        }

        list[`right${counts}`]--;

        rightBtn.classList.remove('not-allow');
        rightBtn.disabled=false;

        listTrack.style.transition = "transform 0.5s"
        listTrack.style.transform = `translate(-${1080 * (list[`right${counts}`]) }px)`;
        if( list[`right${counts}`] == 0){
            leftBtn.classList.add('not-allow')
            leftBtn.disabled=true;
        }

    })
})