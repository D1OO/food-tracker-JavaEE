function openAddFoodModalWindow(recordtabIndex) {
    const data = $('#data-food-modal-window' + recordtabIndex).serialize();
    $.get('/adding-entries-modal-window', data, function (data) {
        document.getElementById('modal-window').innerHTML = data;
        $("#modal-window").css("display", "block");
    });
}