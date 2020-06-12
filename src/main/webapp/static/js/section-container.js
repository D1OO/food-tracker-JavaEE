$(document).ready(function () {
    const section = $('#sectionToFetchWithAJAX').val();
    setContentContainerToEndpoint(section === '' ? 'feed' : section);
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

function loadFromServerIntoContentContainer(section) {
    const state = {"endpoint": section};
    window.history.pushState(state, "Dreamfit", section);
    setContentContainerToEndpoint(section);
}

function setContentContainerToEndpoint(section) {
    $.ajax({
        type: "GET",
        url: section,
        data: {AJAXrequest: true},
        statusCode: {
            404: function () {
                window.location.replace('/not-found');
            }
        },
        error: function () {
            window.location.replace('/error');
        },
        success: function (response) {
            $('#content-container').html(response);
        }
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

function clearErrorMessages() {
    $('.errorServerValidation').text("");
    $("#foodSavingErrorBox").hide(100);
    $("#articleSavingErrorBox").hide(100);
    $("#foodSavedSuccessBox").hide(100);
}