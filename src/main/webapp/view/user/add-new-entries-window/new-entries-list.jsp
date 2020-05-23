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
                    <td class="foodlabel  row justify-content-end m-1"
                        style="width: 18%; justify-content: end">
                        <p class="mr-2 foodName" style="text-align: right">${entry.foodName}</p>
                    </td>
                    <td class="d-inline-flex col-sm-auto align-items-top m-1">
                        <input class="form-control mr-2 quantity" placeholder="quantity(g)"
                               style="width: 50%" name="newEntriesDTO.entries[${loop.index}].quantity"
                               value="${newEntriesDTO.entries[loop.index].quantity}">
                        <input class="form-control foodDTOJSON"
                               name="newEntriesDTO.entries[${loop.index}].foodDTOJSON"
                               value='${newEntriesDTO.entries[loop.index].foodDTOJSON}'
                               type="hidden">
                        <label><fmt:message key="food.grams"/></label>
                    </td>
                    <td class="">
                        <a class=" mt-2 removeButton"
                           style="padding: 4px 8px; color: rgba(168,56,59,0.66)"
                           type="button"><fmt:message key="user.remove"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>

<script id="change-lang-param" src="static/js/content-container-and-modal-window.js" type="text/javascript"></script>
