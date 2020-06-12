$(document).ready(function () {
    function allFilled() {
        let filled = true;
        $('.form-control.required').each(function () {
            if ($(this).val() == '') filled = false;
        });
        return filled;
    }

    $('.form-control.required').keyup(function () {
        if (allFilled()) {
            $('.pretty-button').removeAttr('disabled')
        } else {
            $('.pretty-button').prop("disabled", true);
        }
    });

    $('.submit-button').hover(function () {
        if (allFilled()) {
            $('.submit-button').removeAttr('disabled')
        } else {
            $('.submit-button').prop("disabled", true);
        }
    });
});