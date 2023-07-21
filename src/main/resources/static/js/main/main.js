/*슬라이드 배너*/
const banner = document.querySelector(("div.banner-list"));
const image_divs = document.querySelectorAll("div.banner-wrap");
const first_div = document.querySelector("#first-temp");
const last_div = document.querySelector("#last-temp");
const prev = document.querySelector("div.top-banner-prev-button");
const next = document.querySelector("top-banner-next-button.next");
let count = 1;
let check = true;
let clickCheck = false;
let temp;

// image_divs.forEach((image_div, i) => image_div.style.backgroundImage = `url(00${i + 1}.png)`);
// first_div.style.backgroundImage = `url(006.png)`;
// last_div.style.backgroundImage = `url(001.png)`;

let slide = setInterval(() => autoSlide(), 2000);

prev.addEventListener("click", function(){
    if(!check) {return;}
    check = false;
    clearInterval(slide);
    banner.style.transform = `translate(${-1060 * --count}px)`;
    banner.style.transition = "transform 0.7s"

    if(count == 0) {
        setTimeout(() => {
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(-5300px)`;
        }, 700);
        count = 63;
    }
    changeButtonStyle();
    slide = setInterval(() => autoSlide(), 2000);
    setTimeout(() => {check = true}, 700);
});

next.addEventListener("click", function(){
    if(!check) {return;}
    check = false;
    clearInterval(slide);
    banner.style.transform = `translate(${-1060 * ++count}px)`;
    banner.style.transition = "transform 0.7s"

    if(count == 7) {
        setTimeout(() => {
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(-1060px)`;
        }, 700);
        count = 1;
    }
    changeButtonStyle();
    slide = setInterval(() => autoSlide(), 2000);
    setTimeout(() => {check = true}, 700);
});

function autoSlide(){
    check = false;
    banner.style.transform = `translate(${-1060 * ++count}px)`;
    banner.style.transition = "transform 0.7s"

    if(count == 7) {
        setTimeout(() => {
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(-1060px)`;
        }, 700);
        count = 1;
    }
    changeButtonStyle();
    setTimeout(() => {
        check = true;
    }, 700);
}