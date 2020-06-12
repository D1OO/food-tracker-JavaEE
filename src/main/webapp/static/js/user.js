function addedNewEntry(foodDTO, foodName) {
    const data = '&foodDTOJSON=' + foodDTO + '&foodName=' + foodName +
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
            foodDTOJSON: $(this).find('.foodDTOJSON').val()
        });
    });
    return JSON.stringify(entriesJSON);
}

function acceptInvite(sender, time) {
    $.ajax({
        type: "POST",
        url: '/profile/accept-invitation',
        data: {sender: sender, time: time},
        statusCode: {
            500: function () {
                window.location.href = '/error';
            }
        },
        success: function () {
            $('.notifications-container').slideUp();
        }
    });
}

function declineInvite(sender, time) {
    $.ajax({
        type: "POST",
        url: '/profile/decline-invitation',
        data: {sender: sender, time: time},
        statusCode: {
            500: function () {
                window.location.href = '/error';
            }
        },
        success: function (response) {
            $('.notifications-container').slideUp();
        }
    });
}

var doOnce = false;

function assignSearchListener() {
    if (doOnce === false) {
        $('#search').keyup(function () {
            $.ajax({
                type: "POST",
                url: '/search',
                data: {name: $(this).val()},
                statusCode: {
                    500: function (response) {
                        $("#userSavingErrorBox").show(200);
                    }
                },
                success: function (response) {
                    $('#addfoodcontainer').html(response);
                }
            });
        });
        doOnce = true;
    }
}