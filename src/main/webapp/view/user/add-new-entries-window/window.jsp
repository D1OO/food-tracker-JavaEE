<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="modal" id="myModal"  data-aos="fade-zoom-in" data-aos-offset="">
    <div class="modal-content">
        <div class="modal-header">
            <p class="p-1 mt-2 mb-2" style="size:4em"><fmt:message key="user.meals-to-add"/></p>
            <span class="close mt-0" onclick="closeAddFoodModalWindow()">&times;</span>
        </div>
        <div class="modal-body container" id="modalbody">
            <div class="m-2 tabs" id="addfoodcontainer" style="display: block">
                <div class="d-inline-flex flex-wrap">
                    <c:forEach var="food" items="${userFood}" varStatus="loop">
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
                        <jsp:include page="/view/user/add-new-entries-window/new-entries-list.jsp"/>
                    </div>
                </div>
                <button class="pretty-button switch-content" onclick="setModalContainerTo('createfoodcontainer')"
                        style="width: 40%"><fmt:message key="user.create-new"/>
                </button>
            </div>
            <div class="mt-4" id="createfoodcontainer" style="display: none">
                <form class="form" id="createfoodform" method="post" style="width: 100%" action="save-new-food">
                    <input name="profileId" value="${userId}" type="hidden">
                    <div class="row">
                        <div class="col-3">
                            <div class="form-group col-md-11">
                                <label class="control-label" for="name"><fmt:message key="user.food-name"/></label>
                                <input class="form-control"
                                       data-msg="Enter a valid first name"
                                       data-rule="firstnameexp" id="name"
                                       name="newFoodName"/>
                                <div class="validate"></div>
                            </div>
                            <div class="form-group col-md-11">
                                <label class="control-label" for="calories"><fmt:message key="user.calories"/></label>
                                <input class="form-control"
                                       data-msg="Enter a valid last name"
                                       data-rule="lastnameexp" id="calories"
                                       name="newFoodCalories"/>
                                <div class="validate"></div>
                            </div>
                            <div class="form-group col-md-11">
                                <label class="control-label" for="proteins"><fmt:message key="user.proteins"/></label>
                                <input class="form-control"
                                       data-msg="Enter a valid first name (ukrainian letters)"
                                       data-rule="firstnameuaexp" id="proteins"
                                       name="newFoodProteins"/>
                                <div class="validate"></div>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="form-group col-md-11">
                                <label class="control-label" for="fats"><fmt:message key="user.fats"/></label>
                                <input class="form-control"
                                       data-msg="Enter a valid first name"
                                       data-rule="firstnameexp" id="fats"
                                       name="newFoodFats"/>
                                <div class="validate"></div>
                            </div>
                            <div class="form-group col-md-11">
                                <label class="control-label" for="carbohydrates"><fmt:message key="user.carbs"/></label>
                                <input class="form-control"
                                       data-msg="Enter a valid last name"
                                       data-rule="lastnameexp" id="carbohydrates"
                                       name="newFoodCarbohydrates"/>
                                <div class="validate"></div>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="m-2 d-flex flex-column col-5 tabs">
                    <button class="pretty-button mt-2" id="myBtn" onclick="saveCreatedFood(this)">
                        <fmt:message key="user.save"/>
                    </button>
                    <button class="pretty-button switch-content mt-2" onclick="setModalContainerTo('addfoodcontainer')">
                        <fmt:message key="user.back"/>
                    </button>
                </div>
<%--                <!--                <p class="error-validation"-->--%>
<%--                <!--                   th:each="error : ${#fields.errors('global')}"-->--%>
<%--                <!--                   th:if="${#fields.hasGlobalErrors()}"-->--%>
<%--                <!--                   th:text="${error}">Validation error</p>-->--%>

<%--                <!--                <div class="form-group col-md-5">-->--%>
<%--                <!--                    <input class="pretty-button d-block w-100" disabled="disabled" type="submit" value="SIGN UP">-->--%>
<%--                <!--                </div>-->--%>
<%--                <!--                <div class="form-group col-md-12">-->--%>
<%--                <!--                    <span>Already signed up? <a style="color:#34ce57" th:href="@{/login}">LOGIN</a></span>-->--%>
<%--                <!--                </div>-->--%>

<%--                <!--                <div class="alert alert-info col-md-12 mb-3" role="alert" th:if="${logout}">You've been logged-->--%>
<%--                <!--                    out-->--%>
<%--                <!--                    successfully.-->--%>
<%--                <!--                </div>-->--%>

<%--                <!--                <div class="col-md-12 mb-3" style="background: #eeeeee">-->--%>
<%--                <!--                    <div class="loading" style="background: #eeeeee">Loading</div>-->--%>
<%--                <!--                    <div class="error-message"></div>-->--%>
<%--                <!--                    <div class="sent-message">filler</div>-->--%>
<%--                <!--                </div>-->--%>
            </div>
        </div>
        <div class="modal-footer d-flex justify-content-center">
            <button class="pretty-button" onclick="saveNewEntries()" style="width: 40%">
                <fmt:message key="user.save"/>
            </button>
        </div>
    </div>
</div>