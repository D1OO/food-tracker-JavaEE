function inviteMember() {
    $('.invite-section').slideDown();
}

function sendInvite() {
    $.ajax({
        type: "POST",
        url: '/group/send-invitation',
        data: {receiver_email: $('#inviteemail').val()},
        statusCode: {
            500: function (response) {
                window.location.href = '/error';
            }
        },
        success: function (response) {
            $('.notifications-container').slideUp();
        }
    });
    // $('.invite-section').slideDown();
}

function userTabClick(tab) {
    $('.invite-section').slideUp();
    $('.recordTab').removeClass("selected-record-button");
    $(event.target).addClass("selected-record-button");
    $('.record-tabs').css("display", "none");
    $(tab).css("display", "block");
}

function saveCreatedArticle() {
    clearErrorMessages();
    $.ajax({
        type: "POST",
        url: '/save-new-article',
        contentType: false,
        processData: false,
        data: new FormData($('#createarticleform')[0]),
        statusCode: {
            500: function () {
                $("#articleSavingErrorBox").show(200);
            },
            400: function (response) {
                $.each(response.responseJSON, function (errorKey, errorMessage) {
                    $("#" + errorKey).text(errorMessage);
                });
            }
        },
        success: function () {
            closeAddFoodModalWindow();
            loadFromServerIntoContentContainer('feed');
        }
    });
}