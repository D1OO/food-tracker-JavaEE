function setModalContainerTo(name) {
    clearErrorMessages();
    $('#modalbody > *').css("display", "none");
    document.getElementById(name).style.display = "block";
}

function closeAddFoodModalWindow() {
    $('#modal-window').css("display", "none");
}