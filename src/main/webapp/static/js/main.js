$(document).ready(function () {
    const section = document.getElementById('sectionToFetchWithAJAX').value;
    setContentContainerToEndpoint(section ? section : 'feed');
    window.onpopstate = function (e) {
        if (e.state != null)
            setContentContainerToEndpoint(e.state.endpoint);
        else
            window.location.href = document.location;
    };

    $(document).ready(function () {
        const param = new URLSearchParams(window.location.search);
        if (param.has('save-success'))
            $("#updateSuccessBox").show(200);
    });
});

function loadFromServerIntoContentContainer(...endpoint) {
    const state = {"endpoint": endpoint.join('')};
    window.history.pushState(state, "Dreamfit", endpoint);
    setContentContainerToEndpoint(endpoint.join(''));
}

function setContentContainerToEndpoint(...controllerEndpoint) {
    $.get(controllerEndpoint.join(''), {AJAXrequest: true}, function (data) {
        document.getElementById('content-container').innerHTML = data;
    });
}

function setModalContainerTo(name) {
    clearErrorMessages();
    $('#modalbody > *').css("display", "none");
    document.getElementById(name).style.display = "block";
}

function openAddFoodModalWindow(recordtabIndex) {
    const data = $('#data-food-modal-window' + recordtabIndex).serialize();
    $.get('/adding-entries-modal-window', data, function (data) {
        document.getElementById('modal-window').innerHTML = data;
        $("#modal-window").css("display", "block");
    });
}

function openCreateArticleModalWindow() {
    $.get('/new-article-window', function (response) {
        document.getElementById('modal-window').innerHTML = response;
        $("#modal-window").css("display", "block");
    });
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
    const data = '&foodDTOJSON=' + foodDTO + '&foodName=' + foodName +
        '&newEntriesJSON=' + getNewEntriesJSONString() + '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/added-entry', data, function (response) {
        $('#new-entries-container').html(response);
    });
}

function saveCreatedFood() {
    clearErrorMessages();
    $.ajax({
        type: "POST",
        url: '/save-new-food',
        data: $('#createfoodform').serialize(),
        statusCode: {
            500: function (response) {
                $("#foodSavingErrorBox").show(200);
            },
            200: function (response) {
                $("#foodSavedSuccessBox").show(200);
            }
        },
        success: function (errorsMap) {
            $.each(errorsMap, function (errorKey, errorMessage) {
                $("#" + errorKey).text(errorMessage);
            });
        }
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

function saveCreatedArticle() {
    $.ajax({
        type: "POST",
        url: '/save-new-article',
        contentType: false,
        processData: false,
        data: new FormData($('#createarticleform')[0]),
        statusCode: {
            500: function (response) {
                $('.open-modal')[0].onclick();
                //setErrorBoxesVisible
            }
        },
        success: function (response) {
            closeAddFoodModalWindow();
            loadFromServerIntoContentContainer('feed');
        }
    });
}

function clearErrorMessages() {
    $('.errorServerValidation').text("");
    $("#foodSavingErrorBox").hide(100);
    $("#foodSavedSuccessBox").hide(100);
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