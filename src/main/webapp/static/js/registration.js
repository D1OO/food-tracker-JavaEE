function saveNewUser() {
    clearErrorMessages();
    $.ajax({
        type: "POST",
        url: 'sign-up',
        data: $('#user-sign-up-form').serialize(),
        statusCode: {
            500: function () {
                $("#userSavingErrorBox").show(200);
            },
            400: function (response) {
                $.each(response.responseJSON, function (errorKey, errorMessage) {
                    $("#" + errorKey).text(errorMessage);
                });
            }
        },
        success: function () {
            window.location.replace("login?signedup");
        }
    });
}

function clearErrorMessages() {
    $('.errorServerValidation').text("");
    $("#userSavingErrorBox").hide(200);
}
