let count = 1;
let flag = false;

// 1쿠폰 선택
const $firstContainerWrap = $('.first-container-wrap');
const $firstContainerWrapSpan = $('.first-step-number');
const $firstContainerWrapH2 = $('.first-step-title');


//왼쪽 화살표
const $leftWrap = $('.left-arrow-wrap');
const $leftBtn = $('.left-arrow-wrap button');

//오른쪽 화살표
const $rightWrap = $('.right-arrow-wrap');
const $rightBtn = $('.right-arrow-wrap button');

// 지역 버튼들
const $locationBtns = $('.location-category');


//쿠폰들
const $coupons = $('.shop-list li');

//쿠폰 내용
const $couponsContents = $('.content-box')

//쿠폰 정보 담을 input 히든 태그
const $couponValue = $('.coupon-id');
const $couponPrice = $('.coupon-price');

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
const $pricePtag = $('.total-price');

// ================상품권 리스트 넣어줄 항목부분===================
const $shopList = $('.shop-list');

/*--------------------------------------*/

$(document).ready(function () {
    let purchaseRequestDTO;

    getGiftCardList();

    function getGiftCardList() {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/shops/",
            dataType: "json",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (giftCards) {
                let text = "";
                console.log("통신성공" + "OK");
                console.log(giftCards);
                if (giftCards.length > 0) {
                    $.each(giftCards, function (i) {
                        text += '<li id=' +
                            giftCards[i].id +
                            ' name=' +
                            giftCards[i].giftCardPrice +
                            '       >\n' +
                            '        <div class="gift-card">\n' +
                            '            <a href="#">\n' +
                            '                <header style="background-image: url(/files/' +
                            giftCards[i].files[0].filePath + "/" +
                            giftCards[i].files[0].fileUuid + "_" +
                            giftCards[i].files[0].fileName + ');\n' +
                            '                                " >\n' +
                            '                </header>\n' +
                            '                <div class="content-box">\n' +
                            '                    <div class="content">' +
                            giftCards[i].giftCardTitle +
                            '                    </div>\n' +
                            '                    <div class="name">상품권</div>\n' +
                            '                    <div class="location">' +
                            giftCards[i].giftCardRegion + '\n' +
                            '                        <span class="address-dot">.</span>\n' +
                            '                        <span>' +
                            giftCards[i].giftCardRegionDetail +
                            '                        </span></div>\n' +
                            '                    <div class="price">가격 : ' +
                            giftCards[i].giftCardPrice +
                            '                           point</div>\n' +
                            '                </div>\n' +
                            '            </a>\n' +
                            '        </div>\n' +
                            '    </li>'
                    })
                } else {
                    text += "해당 지역의 상품권이 존재하지 않습니다."
                }


                $shopList.html(text);
            }
        });
    }

    function getGiftCardListByRegion(region) {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/shops/" + region,
            dataType: "json",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (giftCards) {
                let text = "";
                console.log("통신성공" + "OK");
                console.log(giftCards);
                if(giftCards.length > 0){
                    $.each(giftCards, function (i) {
                        text += '<li id=' +
                            giftCards[i].id +
                            ' name=' +
                            giftCards[i].giftCardPrice +
                            '       >\n' +
                            '        <div class="gift-card">\n' +
                            '            <a href="#">\n' +
                            '                <header style="background-image: url(/files/' +
                            giftCards[i].files[0].filePath + "/" +
                            giftCards[i].files[0].fileUuid + "_" +
                            giftCards[i].files[0].fileName + ');\n' +
                            '                                " >\n' +
                            '                </header>\n' +
                            '                <div class="content-box">\n' +
                            '                    <div class="content">' +
                            giftCards[i].giftCardTitle +
                            '                    </div>\n' +
                            '                    <div class="name">상품권</div>\n' +
                            '                    <div class="location">' +
                            giftCards[i].giftCardRegion + '\n' +
                            '                        <span class="address-dot">.</span>\n' +
                            '                        <span>' +
                            giftCards[i].giftCardRegionDetail +
                            '                        </span></div>\n' +
                            '                    <div class="price">가격 : ' +
                            giftCards[i].giftCardPrice +
                            '                           point</div>\n' +
                            '                </div>\n' +
                            '            </a>\n' +
                            '        </div>\n' +
                            '    </li>'
                    })
                } else {
                    text += "해당 지역의 상품권이 존재하지 않습니다."
                }

                $shopList.html(text);
            }
        });
    }

    function purchaseGiftCard(purchaseRequestDTO){
        $.ajax({
            url: `/api/shops/`,
            type: `post`,
            data		:  JSON.stringify(purchaseRequestDTO),
            contentType : "application/json",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function(data){
                showWarnModal("쿠폰 구매가 완료되었습니다.")
                $(".modal").on("click", function () {
                    location.href = data
                })
                console.log("성공")
            }
        })
    }

//쿠폰 선택시 선택효과및 밑에 input value에 쿠폰 id 값 넣기
    $(document).on('click', '.shop-list li', function (e) {

        const thisContent = $(this).closest("li").find('.content-box').find("div");
        e.preventDefault();

        //다시 클릭하면 선택 풀리기
        if (thisContent.css("color") == "rgb(12, 72, 220)") {
            console.log("들어옴");
            $('.content-box div').css("color", "#333")
            $couponValue.val("");
            $couponPrice.val("0");
            $pricePtag.text("0Point");
            qty = 1;
            $qtySpan.text(qty);
            return;
        }

        //다른 쿠폰 선택할 때 마다 초기화
        $('.content-box div').css("color", "#333")
        $couponValue.val("");
        $couponPrice.val("0");
        $pricePtag.text("0Point");
        qty = 1;

        //선택 쿠폰 색 효과
        thisContent.css("color", "#0c48dc");
        // 밑에 input 히든에 선택 쿠폰 pk id 값 담기
        $couponValue.val($(this).attr("id"));
        $couponPrice.val($(this).attr("name"));
        $pricePtag.text($couponPrice.val()+"Point");
        console.log($couponValue.val());
        console.log($couponPrice.val());

        $qtySpan.text(qty);
    })

// 버튼 클릭
    $locationBtns.on("click", function () {
        //locationName.text($(this).text())
        $locationBtns.removeClass("now-button");
        if (!$(this).hasClass("now-button")) {
            $(this).addClass("now-button");
        }

        //선택된 쿠폰 초기화
        $('.content-box div').css("color", "#333")
        $couponValue.val("");
        $couponPrice.val("0");
        $pricePtag.text("0Point");
        qty = 1;

        const $region = $(this).text().trim().replaceAll(" ", "");
        console.log($region);

        if ($region == '전국') {
            getGiftCardList();
        } else {
            getGiftCardListByRegion($region);
        }
        $qtySpan.text(qty);
    })

//지역이름
//const locationName = $('.location-name');

//화살표 hover

    $rightWrap.hover(
        function () {
            $rightBtn.css("background", "#f7f7f7")
        }, function () {
            $rightBtn.css("background", "#fff")
        })

    $leftWrap.hover(
        function () {
            $leftBtn.css("background", "#f7f7f7")
        }, function () {
            $leftBtn.css("background", "#fff")
        })

//왼쪽 화살표 클릭
    $leftWrap.on("click", function () {

        if (!flag) {
            count -= 2;
        }

        flag = true;

        $rightWrap.show();
        console.log("왼쪽" + count);
        $locationBtns.css("transform", `translateX(${-110 * count}px)`)
        count--;
        console.log(count);
        if (count == -1) {
            $leftWrap.hide();
        }
    })

//오른쪽 화살표 클릭
    $rightWrap.on("click", function () {


        if (flag) {
            count += 2;
        }

        if (count == -1) {
            count = 1;
        }

        flag = false;

        $leftWrap.show();
        console.log("오른쪽" + count);
        $locationBtns.css("transform", `translateX(${-110 * count}px)`)
        count++;
        if (count == 5) {
            $rightWrap.hide();
        }

    })


// 수량버튼
    $qtyBtn.on("click", function (e) {
        if ($couponValue.val() == "") {
            showWarnModal("쿠폰을 선택해주세요");
            return;
        }
        console.log(e.target.value);
        if (e.target.value == '+') {
            $qtySpan.text(++qty);
        } else {
            if (qty == 1) {
                return
            }
            ;
            $qtySpan.text(--qty)
        }

        $pricePtag.text((qty * $couponPrice.val()) + "point");

    })


    $purchaseBtn.on("click", function () {

        if ($couponValue.val() == "") {
            showWarnModal("쿠폰을 선택해주세요");
            return;
        }


        if ($deliveryInput.eq(1).val() == "") {
            showWarnModal("받는 사람을 입력해주세요");
            $deliveryInput.eq(1).css("border-color", "red");
            $message.eq(0).show();
            return;
        }

        if ($deliveryInput.eq(2).val() == "") {
            showWarnModal("우편번호와 주소을 입력해주세요");
            $deliveryInput.eq(2).css("border-color", "red");
            $deliveryInput.eq(3).css("border-color", "red");
            $message.eq(1).show();
            $message.eq(2).show();
            return;
        }

        if ($deliveryInput.eq(4).val() == "") {
            showWarnModal("상세주소를 입력해주세요");
            $deliveryInput.eq(4).css("border-color", "red");
            $message.eq(3).show();
            return;
        }

        if ($deliveryInput.eq(5).val() == "") {
            showWarnModal("휴대폰 번호를 입력해주세요");
            $deliveryInput.eq(5).css("border-color", "red");
            $message.eq(4).show();
            return;
        }

        if (!fn_mbtlnumChk($deliveryInput.eq(5).val())) {
            showWarnModal("올바른 휴대폰 번호가 아닙니다.");
            $deliveryInput.eq(5).css("border-color", "red");
            $message.eq(4).show();
            return;
        }

        if ($deliveryInput.eq(6).val() == "") {
            showWarnModal("배송 시 주의사항을 입력해주세요");
            $deliveryInput.eq(6).css("border-color", "red");
            $message.eq(5).show();
            return;
        }

        if ($deliveryInput.val() != "") {
            purchaseRequestDTO = {
                "recipientName" : $deliveryInput.eq(1).val(),
                "payTotalPrice" : qty * $couponPrice.val(),
                "payTotalCount" : qty,
                "address" : {
                    "address" : $deliveryInput.eq(3).val(),
                    "addressDetail" : $deliveryInput.eq(4).val(),
                    "postcode" : $deliveryInput.eq(2).val()
                },
                "memberId" : 32, //#TODO 나중에 세션으로 변경
                "giftCardId" : Number($couponValue.val())
            };

            purchaseGiftCard(purchaseRequestDTO);
            //showWarnModal("쿠폰 구매가 완료되었습니다.")
        }

    })

});


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
        oncomplete: function (data) {
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
            $deliveryInput.eq(2).css("border-color", "#e1e2e3");
            $deliveryInput.eq(3).css("border-color", "#e1e2e3");
        }
    }).open();
}

$deliveryInput.on("keyup", function () {
    const thisMsg = $(this).closest("div.has-error").find('.input-error');
    console.log(thisMsg);
    thisMsg.hide();
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
function fn_mbtlnumChk(mbtlnum) {
    var regExp = /^010\d{8}$/;
    if (!regExp.test(mbtlnum)) {
        return false;
    }
    return true;
}

