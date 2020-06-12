(function ($) {
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

    });
})(jQuery);