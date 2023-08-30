const $pointsTbody = $('.points-tbody');
const type = {
    "All" : "전체",
    "EARN" : "적립",
    "USE" : "사용"
}

let currentType = "ALL";
let hasType = false;

$(document).ready(function () {
    var currentPage = 1;
    var totalPages = 0;
    getAllPoints(0);

    function getAllPoints(page) {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/pointsWithPage?page=" + page,
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                setPointHtml(result.content);
                totalPages = result.totalPages;
                currentPage = page + 1;
                updatePageNumbers(totalPages, currentPage);
            }
        });
    }

    function getPointsByType(pointType, page) {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/pointsByTypeWithPage?page=" + page + "&pointType=" + pointType,
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                setPointHtml(result.content);
                totalPages = result.totalPages;
                currentPage = page + 1;
                updatePageNumbers(totalPages, currentPage);
            }
        });
    }

    function setPointHtml(points) {
        let text = "";
        if (points.length > 0) {
            $.each(points, function (i) {
                const idx = i + 1;
                text += '<tr>\n' +
                    '        <td>\n' +
                                idx +
                    '           \n' +
                    '        </td>\n' +
                    '        <td>' +
                                points[i].pointHistory +
                    '        </td>\n';
                if(points[i].pointCateGoryType.code == "EARN") {
                    text += '    <td>' +
                                    points[i].point +
                        '        </td>\n' +
                        '        <td></td>\n';
                } else {
                    text += '    <td></td>\n' +
                        '        <td>' +
                                    points[i].point +
                        '        </td>\n';
                }
                text +=
                    '        <td>' +
                                points[i].updatedDate.split("T")[0] +
                    '        </td>\n' +
                    '        <td>' +
                                points[i].pointBalance +
                    '        </td>\n' +
                    '    </tr>'
            })
        } else {
            text += '<tr style="cursor:default"><td colspan="7"> 포인트 내역이 존재하지 않습니다. </td></tr>'
        }

        $pointsTbody.html(text);
    }

    function updatePageNumbers(totalPages, currentPage) {
        var maxButtonsToShow = 5; // 표시할 최대 버튼 수
        var paginationContainer = $('.pagination');
        paginationContainer.empty();

        // "<" 버튼 추가 (첫 페이지에서 숨김)
        if (currentPage > 1) {
            paginationContainer.append('<li class="page-item" id="first"><a class="page-link" href="#">First</a></li>');
            paginationContainer.append('<li class="page-item" id="previous"><a class="page-link" href="#">Previous</a></li>');
        }

        // 페이지 번호 버튼 추가
        var startPage = Math.max(1, Math.floor((currentPage - 1) / maxButtonsToShow) * maxButtonsToShow + 1);
        var endPage = Math.min(startPage + maxButtonsToShow - 1, totalPages);

        for (var i = startPage; i <= endPage; i++) {
            paginationContainer.append('<li class="page-item" id="page-' + i + '"><a class="page-link" href="#">' + i + '</a></li>');
        }

        // ">" 버튼 추가 (마지막 페이지에서 숨김)
        if (currentPage < totalPages) {
            paginationContainer.append('<li class="page-item" id="next"><a class="page-link" href="#">Next</a></li>');
            paginationContainer.append('<li class="page-item" id="last"><a class="page-link" href="#">Last</a></li>');
        }

        // 현재 페이지 활성화
        $('#page-' + currentPage).addClass('active');

        paginationContainer.on('click', 'li', function () {
            var clickedPage = $(this).attr('id');

            if (clickedPage === 'first') {
                currentPage = 1;
            } else if (clickedPage === 'last') {
                currentPage = totalPages;
            } else if (clickedPage === 'previous') {
                if (currentPage > 1) {
                    currentPage--;
                }
            } else if (clickedPage === 'next') {
                if (currentPage < totalPages) {
                    currentPage++;
                }
            } else {
                currentPage = parseInt(clickedPage.replace('page-', ''), 10);
            }

            // 페이지 번호 업데이트
            if (hasType) {
                getPointsByType(currentType,currentPage - 1)
            } else {
                getAllPoints(currentPage - 1);
            }
        });
    }

    $(document).on('change', '.point-type', function(e) {
        currentType = $(this).val();
        if (currentType=="ALL") {
            hasType = false;
            getAllPoints(0);
        } else {
            hasType = true;
            getPointsByType(currentType, 0);
        }
        console.log("선택 바뀜");
    })
})