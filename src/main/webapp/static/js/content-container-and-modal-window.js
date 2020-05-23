$(document).ready(function () {
    setContentContainerTo('/food-diary');
    $('.recordTab').css("background", "#e2dbff");
    window.onclick = function (event) {
        if (event.target === $('#modal-window')) {
            $('modal-window').style.display = "none";
        }
    };

    $(".removeButton").click(function () {
        event.preventDefault();
        $(this).closest('.entry').remove();

        data ='&newEntriesJSON=' + getNewEntriesJSONString() +
            '&newEntriesDTOJSON=' + $('#new-entries-list').val();

        $.post('/removed-entry', data, function (response) {
            $('#new-entries-container').html(response);

        });
    });
});

function setContentContainerTo(controllerEndpoint) {
    $.get(controllerEndpoint, function (data) {
        document.getElementById('content-container').innerHTML = data;
    });
}

function setModalContainerTo(name) {
    $('#modalbody > *').css("display", "none");
    document.getElementById(name).style.display = "block";
}

function openAddFoodModalWindow(recordtab) {
    event.preventDefault();
    let data = $(recordtab).serialize();
    $.get('/adding-entries-modal-window', data, function (data) {
        document.getElementById('modal-window').innerHTML = data;
        $("#modal-window").css("display", "block");
    });
}

function addedNewEntry(foodDTO, foodName) {
    let data = '&foodDTOJSON=' + foodDTO + '&foodName=' + foodName +
        '&newEntriesJSON=' + getNewEntriesJSONString() + '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/added-entry', data, function (response) {
        $('#new-entries-container').html(response);
    });
}



function saveCreatedFood() {
    let data = $('#createfoodform').serialize();
    $.post('/save-new-food', data, replacePageWith);
}


function saveNewEntries() {
    let data = '&newEntriesJSON=' + getNewEntriesJSONString() +
        '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/save-new-entries', data, replacePageWith);
}

function replacePageWith(html) {
    let newDoc = document.open("text/html", "replace");
    newDoc.write(html);
    newDoc.close();
}

function tabClick(tab) {
    $('.recordTab').css("background", "#e2dbff");
    $(event.target).css("background", "linear-gradient(338deg, rgba(213, 95, 147, 0.62) 10%, rgba(233, 232, 148, 0.73) 100%)");
    $('.record-tabs').css("display", "none");
    $(tab).css("display", "block");
}

function closeAddFoodModalWindow() {
    $('#modal-window').css("display", "none");
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