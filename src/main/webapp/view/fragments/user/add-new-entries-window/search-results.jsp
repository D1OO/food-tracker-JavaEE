<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>


<div class="d-inline-flex flex-wrap" data-aos="fade-zoom-in" data-aos-offset="">
    <div id="entriesSavingErrorBox" class="alert-danger alert col-md-11 mb-3 display-none" role="alert">
        <fmt:message key="entries.not-saved"/>
    </div>
    <c:forEach var="food" items="${foodList}" varStatus="loop">
        <div class="mb-1">
            <div class="user_food m-2">
                <a rel="group" title=""></a>
                <div class="label">
                    <div class="label-text">
                        <button class="pretty-button foodlink" name='${food}'
                                onclick="addedNewEntry(this.getAttribute('name'), '${food.name}')">${food.name}</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div>
    <div id="new-entries-container">
        <jsp:include page="/view/fragments/user/add-new-entries-window/new-entries-list.jsp"/>
    </div>
</div>
<button class="pretty-button modal-create-food" onclick="setModalContainerTo('createfoodcontainer')">
    <fmt:message key="user.create-new"/>
</button>
