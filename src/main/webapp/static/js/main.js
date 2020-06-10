$(document).ready(function () {
    const section = document.getElementById('sectionToFetchWithAJAX').value;
    setContentContainerToEndpoint(section ? section : 'feed');
    window.onpopstate = function (e) {
        if (e.state != null)
            setContentContainerToEndpoint(e.state.endpoint);
        else
            window.location.href = document.location;
    };

    const param = new URLSearchParams(window.location.search);
    if (param.has('save-success'))
        $("#updateSuccessBox").show(200);
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
    const container = $(tab);
    $('.recordTab').removeClass("selected-record-button");
    $(event.target).addClass("selected-record-button");

    container.removeClass("record-tabs");

    $('.record-tabs').fadeOut(200);
    container.addClass("record-tabs");
    setTimeout(function () {
        container.fadeIn(100);
    }, 200);


}

function closeAddFoodModalWindow() {
    $('#modal-window').css("display", "none");
}

function clearErrorMessages() {
    $('.errorServerValidation').text("");
    $("#foodSavingErrorBox").hide(100);
    $("#articleSavingErrorBox").hide(100);
    $("#foodSavedSuccessBox").hide(100);
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