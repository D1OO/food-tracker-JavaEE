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
                <tr align="center" class="row align-items-center m-1 entry">
                    <td class="foodlabel col-9 row m-1"
                        style="max-width: 40%; justify-content: end">
                        <p class="mr-2 foodName" style="text-align: right">${entry.foodName}</p>
                    </td>
                    <td class="col-3 row m-1 align-items-center">
                        <input class="form-control mr-2 quantity" placeholder="quantity"
                               style="width: 60%" name="newEntriesDTO.entries[${loop.index}].quantity"
                               value="${newEntriesDTO.entries[loop.index].quantity}">
                        <input class="form-control foodJSON"
                               name="newEntriesDTO.entries[${loop.index}].foodJSON"
                               value='${newEntriesDTO.entries[loop.index].foodJSON}' type="hidden">
                        <p><fmt:message key="food.grams"/></p>
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
