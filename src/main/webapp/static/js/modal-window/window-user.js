function openAddFoodModalWindow(recordtabIndex) {
    const data = $('#data-food-modal-window' + recordtabIndex).serialize();
    $.get('/adding-entries-modal-window', data, function (data) {
        document.getElementById('modal-window').innerHTML = data;
        $("#modal-window").css("display", "block");
    });
}

function addedNewEntry(food, foodName) {
    const data = '&foodJSON=' + food + '&foodName=' + foodName +
        '&newEntriesJSON=' + getNewEntriesJSONString() + '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/added-entry', data, function (response) {
        $('#new-entries-container').html(response);
    });
}

function saveNewEntries() {
    const data = '&newEntriesJSON=' + getNewEntriesJSONString() +
        '&newEntriesDTOJSON=' + $('#new-entries-list').val();
    $.ajax({
        type: "POST",
        url: '/save-new-entries',
        data: data,
        statusCode: {
            500: function (response) {
                $("#entriesSavingErrorBox").show(200);
            }
        },
        success: function (response) {
            if (response.length > 0) {
                $('#new-entries-container').html(response);
            } else {
                closeAddFoodModalWindow();
                $("#entriesSavedSuccessBox").show(200);
                loadFromServerIntoContentContainer('food-diary');
            }
        }
    });
}

function saveCreatedFood() {
    clearErrorMessages();
    $.ajax({
        type: "POST",
        url: '/save-new-food',
        data: $('#createfoodform').serialize(),
        statusCode: {
            500: function () {
                $("#foodSavingErrorBox").show(200);
            },
            400: function (response) {
                $.each(response.responseJSON, function (errorKey, errorMessage) {
                    $("#" + errorKey).text(errorMessage);
                });
            }
        },
        success: function () {
            $("#foodSavedSuccessBox").show(200);
        }
    });
}

function getNewEntriesJSONString() {
    const entriesJSON = [];
    $(".entry").each(function () {
        entriesJSON.push({
            foodName: $(this).find('.foodName').text(),
            quantity: $(this).find('.quantity').val(),
            foodJSON: $(this).find('.foodJSON').val()
        });
    });
    return JSON.stringify(entriesJSON);
}