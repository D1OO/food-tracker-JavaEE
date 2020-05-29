$(document).ready(function () {
    window.onpopstate = function (e) {
        if (e.state != null)
            setContentContainerToEndpoint(e.state.endpoint);
        else
            window.location.href = document.location;
    };

    const param = new URLSearchParams(window.location.search);
    if (param.has('signedup'))
        $("#signupSuccessBox").show(200);
    else if (param.has('logout'))
        $("#logoutBox").show(200);
    else if (param.has('error'))
        $("#wrongCredentialsBox").show(200);
});
