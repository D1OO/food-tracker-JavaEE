function saveNewUser() {
    clearErrorMessages();
    $.ajax({
        type: "POST",
        url: 'sign-up',
        data: $('#user-sign-up-form').serialize(),
        statusCode: {
            500: function (response) {
                $("#userSavingErrorBox").show(200);
            }
        },
        success: function (response) {
            if (response.url != null) {
                window.location.replace(response.url);
                // window.history.pushState("empty", "", "/login?signed-up");
                // $("html").html(response);
                // replacePageWith(response);
            } else {
                $.each(response, function (errorKey, errorMessage) {
                    $("#" + errorKey).text(errorMessage);
                });
            }
        }
    });
}

function clearErrorMessages() {
    $('.errorServerValidation').text("");
    $("#userSavingErrorBox").hide(200);
}

function replacePageWith(html) {
    let newDoc = document.open("text/html", "replace");
    newDoc.write(html);
    newDoc.close();
}

