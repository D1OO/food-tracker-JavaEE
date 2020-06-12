function openCreateArticleModalWindow() {
    $.get('/new-article-window', function (response) {
        document.getElementById('modal-window').innerHTML = response;
        $("#modal-window").css("display", "block");
    });
}