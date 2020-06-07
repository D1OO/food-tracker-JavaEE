<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div id="items">
    <input id="new-entries-list" value='${newEntriesDTO}' type="hidden">
    <form id="new-entries-form" method="post" action="save-new-entries">
        <table style="width: 80%">
            <input name="newEntriesDTO.recordId"
                   value="${newEntriesDTO.recordId}" type="hidden"/>
            <input name="newEntriesDTO.profileId"
                   value="${newEntriesDTO.profileId}" type="hidden"/>
            <input name="newEntriesDTO.recordDate"
                   value="${newEntriesDTO.recordDate}" type="hidden"/>
            <c:forEach var="entry" items="${newEntriesDTO.entries}" varStatus="loop">
                <tr align="center" class="row align-items-top m-1 entry" id="fff">
                    <td class="foodlabel col-9 row m-1"
                        style="max-width: 40%; justify-content: end">
                        <p class="mr-2 foodName" style="text-align: right">${entry.foodName}</p>
                    </td>
                    <td class="col-3 row m-1">
                        <input class="form-control mr-2 quantity" placeholder="quantity(g)"
                               style="width: 60%" name="newEntriesDTO.entries[${loop.index}].quantity"
                               value="${newEntriesDTO.entries[loop.index].quantity}">
                        <input class="form-control foodDTOJSON"
                               name="newEntriesDTO.entries[${loop.index}].foodDTOJSON"
                               value='${newEntriesDTO.entries[loop.index].foodDTOJSON}'
                               type="hidden">
                        <label class="justify-content-center"><fmt:message key="food.grams"/></label>
                    </td>
                    <td>
                        <a class="modal-remove-entry mt-2" type="button">x</a>
                    </td>
                    <td class="col-3" style=" font-size:0.85rem; color: darkred">
                        <p>${newEntriesDTO.entries[loop.index].quantityError}</p>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>

<script src="static/js/new-entries-window.js" type="text/javascript"></script>
