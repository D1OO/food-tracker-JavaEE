function userTabClick(username) {
    $('.invite-section').slideUp();
    $('.userTab').removeClass("selected-record-button");
    $(event.target).addClass("selected-record-button");
    loadMemberData(username, null);
}

function loadMemberData(memberUsername, date) {
    $.ajax({
        type: "POST",
        url: '/group/show-member',
        data: {username: memberUsername, date: date === null ? undefined : date},
        statusCode: {
            500: function () {
                $("#articleSavingErrorBox").show(200);
            }
        },
        success: function (response) {
            const container = $('#selected-user-data');
            container.fadeOut(200);
            setTimeout(function () {
                container.html(response);
            }, 200);
            container.fadeIn(100);
        }
    });
}

function inviteMember() {
    $('#selected-user-data').fadeOut(100);
    $('.invite-section').slideToggle();
}

function sendInvite() {
    $.ajax({
        type: "POST",
        url: '/group/send-invitation',
        data: {receiver_email: $('#inviteemail').val()},
        statusCode: {
            500: function () {
                window.location.href = '/error';
            },
            400: function () {
                $('#inviteemail').css("border", "1px solid red");
            }
        },
        success: function () {
            $('#send-success').fadeIn(1000);
            setTimeout(function () {
                $('#send-success').fadeOut(1000);
            }, 3000);
        }
    });
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