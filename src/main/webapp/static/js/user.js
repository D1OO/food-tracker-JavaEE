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