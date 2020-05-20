$(document).ready(function () {
    AOS.init({
        easing: 'ease',
        duration: 1000,
    });

    $(".locale").click(function (event) {
        var selectedOption = event.target.id;
        if (selectedOption != '') {
            location.replace('?lang=' + selectedOption);
        }
    });

    $('.slink-container').hover(function () {
        $(this).css("text-decoration", "underline");
    }, function () {
        // on mouseout, reset the background colour
        $(this).css("text-decoration", "none");
    });

    $('.form-control').keyup(function () {
        if (allFilled()) {
            $('.pretty-button').removeAttr('disabled')
        } else {
            $('.pretty-button').prop("disabled", true);
        }
    });

    function allFilled() {
        var filled = true;
        $('.form-control').each(function () {
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