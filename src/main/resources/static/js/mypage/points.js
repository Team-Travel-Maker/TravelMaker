const $pointsTbody = $('.points-tbody');
const type = {
    "All" : "전체",
    "EARN" : "적립",
    "USE" : "사용"
}

$(document).ready(function () {
    getAllPoints();

    function getAllPoints() {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/points",
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (points) {
                console.log("통신성공" + "OK");
                console.log(points);
                setPointHtml(points)
            }
        });
    }

    function getPointsByType(pointType) {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/pointsByType?pointType=" + pointType,
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (points) {
                console.log("통신성공" + "OK");
                console.log(points);
                setPointHtml(points);
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

    $(document).on('change', '.point-type', function(e) {
        const pointType = $(this).val();
        if (pointType=="ALL") {
            getAllPoints();
        } else {
            getPointsByType(pointType);
        }
        console.log("선택 바뀜");
    })
})