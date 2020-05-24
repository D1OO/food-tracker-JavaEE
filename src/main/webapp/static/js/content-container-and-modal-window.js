$(document).ready(function () {
    setContentContainerTo('/food-diary');
    window.onclick = function (event) {
        if (event.target === $('#modal-window')) {
            $('modal-window').style.display = "none";
        }
    };
});

function setContentContainerTo(...controllerEndpoint) {
    $.get(controllerEndpoint.join(''), function (data) {
        document.getElementById('content-container').innerHTML = data;
    });
}

function setModalContainerTo(name) {
    $('#modalbody > *').css("display", "none");
    document.getElementById(name).style.display = "block";
}

function openAddFoodModalWindow(recordtab) {
    // event.preventDefault();
    let data = $(recordtab).serialize();
    $.get('/adding-entries-modal-window', data, function (data) {
        document.getElementById('modal-window').innerHTML = data;
        $("#modal-window").css("display", "block");
    });
}

function replacePageWith(html) {
    let newDoc = document.open("text/html", "replace");
    newDoc.write(html);
    newDoc.close();
}

function tabClick(tab) {
    $('.recordTab').removeClass("selected-record-button");
    $(event.target).addClass("selected-record-button");
    $('.record-tabs').css("display", "none");
    $(tab).css("display", "block");
}

function closeAddFoodModalWindow() {
    $('#modal-window').css("display", "none");
}

function addedNewEntry(foodDTO, foodName) {
    let data = '&foodDTOJSON=' + foodDTO + '&foodName=' + foodName +
        '&newEntriesJSON=' + getNewEntriesJSONString() + '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/added-entry', data, function (response) {
        $('#new-entries-container').html(response);
    });
}

function saveCreatedFood(element) {
    let data = $('#createfoodform').serialize();
    $.ajax({
        type: "POST",
        url: '/save-new-food',
        data: data,
        statusCode: {
            406: function (response) {
                $('.open-modal')[0].onclick();
                //setErrorBoxesVisible
            }
        },
        success: function () {
            $('.open-modal')[0].onclick();
        }
    });
}

function saveNewEntries() {
    let data = '&newEntriesJSON=' + getNewEntriesJSONString() +
        '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/save-new-entries', data, replacePageWith);
}

function getNewEntriesJSONString() {
    let entriesJSON = [];
    $(".entry").each(function () {
        entriesJSON.push({
            foodName: $(this).find('.foodName').text(),
            quantity: $(this).find('.quantity').val(),
            foodDTOJSON: $(this).find('.foodDTOJSON').val()
        });
    });
    return JSON.stringify(entriesJSON);
}