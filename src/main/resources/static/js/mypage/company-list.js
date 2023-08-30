const $storeList = $('.store-list');
$(document).ready(function () {
    const allId = [];
    var currentPage = 1;
    var totalPages = 0;

    getStoreWithPage(0);

    function getStoreWithPage(page) {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/storeListWithPage?page=" + page,
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                setHtml(result.content);
                totalPages = result.totalPages;
                currentPage = page + 1;
                updatePageNumbers(totalPages, currentPage);
            }
        });
    }

    function setHtml(stores) {
        let text = "";
        if (stores.length > 0) {
            $.each(stores, function (i) {
                allId.push(stores[i].id+"");
                text += '<tr class="store-tr" id="' +
                            stores[i].id +
                    '"' +
                    '           >\n' +
                    '        <td class="checkbox-line">\n' +
                    '            <input type="checkbox" class="storeCheckbox" name="check">\n' +
                    '        </td>\n' +
                    '        <td>\n' +
                                stores[i].storeTitle +
                    '           \n' +
                    '        </td>\n' +
                    '        <td>' +
                                stores[i].storeType.name +
                    '        </td>\n' +
                    '        <td>' +
                                stores[i].address.address +
                    '        <br>\n' +
                                stores[i].address.addressDetail.split("|")[1] +
                    '        </td>\n' +
                    '        <td>' +
                                stores[i].updatedDate.replace("T", " ").replace(/\..*/,'') +
                    '        </td>\n' +
                    '        <td>' +
                                stores[i].storeStatus.name +
                    '        </td>\n' +
                    '        <td class="image-wrap">\n';
                if (stores[i].files.length == 0) {
                    text +=
                    '            <img src="/images/mypage/대표이미지없음.png" style="width: 130px; height: 100px">\n';
                } else {
                    text +=
                    '            <img src="/api/files/display?fileName=' +
                                    stores[i].files[0].filePath + "/" +
                                    stores[i].files[0].fileUuid + "_" +
                                    stores[i].files[0].fileName +
                    '           " style="width: 130px; height: 100px">\n'
                }
                    text +=
                    '        </td>\n' +
                    '    </tr>';
            })
        } else {
            text += '<tr style="cursor:default"><td colspan="7"> 등록한 업체가 존재하지 않습니다. </td></tr>'
        }

        $storeList.html(text);
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
            getStoreWithPage(currentPage - 1)
        });
    }

    const selectedValues = [];

    $(document).on('click', 'input[name=check]', function(e) {
        const thisContent = $(this).closest("tr").attr("id");

        if (e.target.checked) {
            selectedValues.push(thisContent);
        } else {
            const index = selectedValues.indexOf(thisContent);
            if (index !== -1) {
                selectedValues.splice(index, 1);
            }
        }

        // 삭제를 위해 선택된 업체들의 Id
        console.log('Selected values:', selectedValues);

        const total = $("input[name=check]").length;
        const checked = $("input[name=check]:checked").length;

        if(total != checked) $("#allSelect").prop("checked", false);
        else $("#allSelect").prop("checked", true);

        // checkbox를 클릭할 때 event.stopPropagation()로 이벤트 버블링을 중지시키
        // (tr클릭 이벤트는 작동되지 않음)
        e.stopPropagation();
    });

    $(document).on('click', '.store-tr', function(e) {
        console.log($(this).attr("id"));
        window.location.href = "/mypage/company/edit?storeId="+$(this).attr("id");
    })

    $("#allSelect").click(function() {
        if($("#allSelect").is(":checked")) {
            $("input[name=check]").prop("checked", true);
            selectedValues.splice(0, selectedValues.length);
            allId.forEach(id => selectedValues.push(id))
        } else {
            $("input[name=check]").prop("checked", false);
            selectedValues.splice(0, selectedValues.length);
        }
    });

    $("#delete-btn").on('click', function() {
        if(selectedValues.length == 0) {
            showWarnModal("삭제 할 업체를 하나 이상 선택해 주세요.")
            return;
        }
        $.ajax({
            type: "DELETE", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/store",
            data: {"storeIdList" : JSON.stringify(selectedValues)},
            dataType: "text",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function(url){
                console.log(url)
                showWarnModal("선택한 업체가 삭제되었습니다.")
                $(".modal").on("click", function () {
                    location.href = url
                })
                console.log("통신성공" + "OK")
            }
        });
    })

    $('#registration-btn').on('click', function () {
        window.location.href = "/mypage/company/registration"
    })
});