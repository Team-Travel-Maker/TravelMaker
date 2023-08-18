const $shopList = $('.shop-list');

$(document).ready(function () {
    console.log("여기 오는겨?")
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/giftCard",
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
                    text += '<li>' +
                        '        <div class="card">\n' +
                        '                <header style="background-image: url(/files/' +
                                            giftCards[i].filePath + '/' +
                                            giftCards[i].fileUuid + '_' +
                                            giftCards[i].fileName + ');">' +
                        '                                \n' +
                        '                </header>\n' +
                        '                <div class="body">\n' +
                        '                    <div class="card-theme">' +
                                            giftCards[i].giftCardTitle +
                        '                    </div>\n' +
                        '                    <div class="card-userName">받는 사람 : ' +
                                            giftCards[i].recipientName +
                        '                    </div>\n' +
                        '                    <div class="card-date">구매 날짜 : ' +
                                            giftCards[i].updatedDate.replace("T", " ").replace(/\..*/,'') +
                        '                    </div>\n' +
                        '                    <div class="card-location"><span>' +
                                            giftCards[i].giftCardRegion + ' • ' + giftCards[i].giftCardRegionDetail +
                        '                    </span></div>\n' +
                        '                    <div class="card-pay">' +
                                            giftCards[i].payTotalCount + '매 - ' + giftCards[i].payTotalPrice + 'point' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '        </div>\n' +
                        '    </li>'
                })
            } else {
                text += "해당 지역의 상품권이 존재하지 않습니다."
            }


            $shopList.html(text);
        }
    });
})