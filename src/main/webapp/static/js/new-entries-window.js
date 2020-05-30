$(document).ready(function () {
    $(".modal-remove-entry").click(function () {
        event.preventDefault();
        $(this).closest('.entry').remove();

        data ='&newEntriesJSON=' + getNewEntriesJSONString() +
            '&newEntriesDTOJSON=' + $('#new-entries-list').val();

        $.post('/removed-entry', data, function (response) {
            $('#new-entries-container').html(response);

        });
    });
});