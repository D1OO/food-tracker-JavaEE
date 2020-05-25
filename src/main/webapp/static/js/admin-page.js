$(document).ready(function () {
    setContentContainerToEndpoint('/feed');
    window.onpopstate = function (e) {
        if (e.state != null)
            setContentContainerToEndpoint(e.state.endpoint);
        else
            window.location.href = document.location;
    }
});

function loadFromServerIntoContentContainer(...endpoint) {
    const state = { "endpoint": endpoint.join('') };
    window.history.pushState(state,"Dreamfit", endpoint);
    setContentContainerToEndpoint(endpoint.join(''));
}

function setContentContainerToEndpoint(...controllerEndpoint) {
    $.get(controllerEndpoint.join(''), function (data) {
        document.getElementById('content-container').innerHTML = data;
    });
}

function setContentContainerToHtml(html) {
    // $(html).css('display', 'block');
    document.getElementById('content-container').innerHTML = html;
}

function setModalContainerTo(name) {
    $('#modalbody > *').css("display", "none");
    document.getElementById(name).style.display = "block";
}

function openAddFoodModalWindow(recordtab) {
    // event.preventDefault();
    const data = $(recordtab).serialize();
    $.get('/adding-entries-modal-window', data, function (data) {
        document.getElementById('modal-window').innerHTML = data;
        $("#modal-window").css("display", "block");
    });
}

function openCreateArticleModalWindow() {
    $.get('/new-article-window', function (response) {
        document.getElementById('modal-window').innerHTML = response;
        $("#modal-window").css("display", "block");
    });
}

function replacePageWith(html) {
    let newDoc = document.open("text/html", "replace");
    newDoc.write(html);
    newDoc.close();
}

function tabClick(tab) {
    $('.recordTab').removeClass("selected-record-button");
    $(event.target).addClass("selected-record-button");
    $('.record-tabs').css("display", "none");
    $(tab).css("display", "block");
}

function closeAddFoodModalWindow() {
    $('#modal-window').css("display", "none");
}

function addedNewEntry(foodDTO, foodName) {
    const data = '&foodDTOJSON=' + foodDTO + '&foodName=' + foodName +
        '&newEntriesJSON=' + getNewEntriesJSONString() + '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/added-entry', data, function (response) {
        $('#new-entries-container').html(response);
    });
}

function saveCreatedFood(element) {
    const data = $('#createfoodform').serialize();
    $.ajax({
        type: "POST",
        url: '/save-new-food',
        data: data,
        statusCode: {
            406: function (response) {
                $('.open-modal')[0].onclick();
                //setErrorBoxesVisible
            }
        },
        success: function (response) {
            $('.open-modal')[0].onclick();
        }
    });
}

function saveCreatedArticle() {
    var form = $('#createarticleform')[0];
    var formData = new FormData(form);
    $.ajax({
        type: "POST",
        url: '/save-new-article',
        contentType: false,
        processData: false,
        data: formData,
        statusCode: {
            406: function (response) {
                $('.open-modal')[0].onclick();
                //setErrorBoxesVisible
            }
        },
        success: function (response) {
            closeAddFoodModalWindow();
            setContentContainerToHtml(response);
        }
    });
}


function saveNewEntries() {
    const data = '&newEntriesJSON=' + getNewEntriesJSONString() +
        '&newEntriesDTOJSON=' + $('#new-entries-list').val();

    $.post('/save-new-entries', data, replacePageWith);
}

function getNewEntriesJSONString() {
    let entriesJSON = [];
    $(".entry").each(function () {
        entriesJSON.push({
            foodName: $(this).find('.foodName').text(),
            quantity: $(this).find('.quantity').val(),
            foodDTOJSON: $(this).find('.foodDTOJSON').val()
        });
    });
    return JSON.stringify(entriesJSON);
}