$(document).ready(function () {
    AOS.init({
        easing: 'ease',
        duration: 1000,
    });

    $(".locale").click(function (event) {
        let selectedOption = event.target.id;
        if (selectedOption != '') {
            location.replace('/lang?lang=' + selectedOption);
        }
    });

    $('.slink-container').hover(function () {
        $(this).css("text-decoration", "underline");
    }, function () {
        $(this).css("text-decoration", "none");
    });

    $('.form-control.required').keyup(function () {
        if (allFilled()) {
            $('.pretty-button').removeAttr('disabled')
        } else {
            $('.pretty-button').prop("disabled", true);
        }
    });

    function allFilled() {
        let filled = true;
        $('.form-control.required').each(function () {
            if ($(this).val() == '') filled = false;
        });
        return filled;
    }

    $('.submit-button').hover(function () {
        if (allFilled()) {
            $('.submit-button').removeAttr('disabled')
        } else {
            $('.submit-button').prop("disabled", true);
        }
    });
});