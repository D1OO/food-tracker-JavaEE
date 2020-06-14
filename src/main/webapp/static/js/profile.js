function updateUserProfile() {
    clearErrorMessages();
    $.ajax({
        type: "POST",
        url: '/update-profile',
        data: $('#update-profile-form').serialize(),
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
            window.location.replace("profile?save=success");
        }
    });
}

function clearErrorMessages() {
    $('.errorServerValidation').text("");
    $("#userSavingErrorBox").hide(200);
}
