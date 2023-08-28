const $locationBtns =$('.location-category');

const firstInterestsStrList = $('.interestsStr').val().replaceAll(/\s/g, "").split(",");

console.log("===========이거때문?" + firstInterestsStrList);

const interestsList = [];

$locationBtns.on("click", function(){
    const clickTxt = $(this).find("span").text().trim().replace(/\s/g, "");
    console.log(clickTxt);

    if(!$(this).hasClass("now-button")){
        $(this).addClass("now-button");
        $(this).children().addClass('span-color');
        interestsList.push(clickTxt);
    }else{
        $(this).removeClass("now-button");
        $(this).children().removeClass('span-color');
        interestsList.splice(interestsList.indexOf(clickTxt), 1);
    }

    console.log(interestsList);
});

$(document).ready(function() {
    setButton()
    $('#interests-save-btn').on('click', function () {
        const interestsStr = interestsList.join(", ");
        $.ajax({
            type: "PUT", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/interestsRegion",
            data: JSON.stringify({interestsStr: interestsStr}),
            contentType: "application/json",
            dataType: "text",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                showWarnModal("관심지역을 등록했습니다.")
                $(".modal").on("click", function () {
                    window.location.href = result;
                })
            }
        });
    })
});

function setButton() {
    firstInterestsStrList.forEach((interestsStr) => {
        console.log("==========="+interestsStr);
        if(interestsStr != "notexit"){
            interestsList.push(interestsStr);
            $(".target-span").each(function() {
                // 각 span의 텍스트를 가져와서 공백을 제거.
                const spanText = $(this).text().replace(/\s/g, "");
                // 제거한 문자열과 비교하여 같으면 해당 버튼을 활성화.
                if (spanText === interestsStr) {
                    $(this).addClass('span-color');
                    const $button = $(this).closest(".location-category");
                    $button.addClass("now-button");
                }
            });
        }
    })
}