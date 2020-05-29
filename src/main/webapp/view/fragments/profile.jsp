<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container" data-aos="fade-zoom-in" data-aos-offset="0">
    <div class="h2 mb-4 m-4 font-rubick d-flex">${user.username}</div>
    <form class="form" method="post" style="width: 100%" action="update-profile">
        <%--        <input id="profileId" name="profileId" th:value="${#authentication.getPrincipal().getId()}" type="hidden"/>--%>
        <div class="row" style="width: 100%;">
            <div class="col-6">
                <div class="form-group col-md-11">
                    <label class="control-label" for="firstName"><fmt:message key="login.first-name.en"/></label>
                    <input class="form-control"
                           data-msg="Enter a valid first name"
                           data-rule="firstnameexp" id="firstName"
                           name="firstNameEN"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                >
                    <label class="control-label" for="lastName"><fmt:message key="login.last-name"/></label>
                    <input class="form-control"
                           data-msg="Enter a valid last name"
                           data-rule="lastnameexp" id="lastName"
                           name="lastName"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                >
                    <label class="control-label" for="firstNameRu"><fmt:message key="login.first-name.ru"/></label>
                    <p style="font-size: 0.9em;">(not required)</p>
                    <input class="form-control"
                           data-msg="Enter a valid first name (cyrillic letters)"
                           data-rule="firstnameuaexp" id="firstNameRu"
                           name="firstNameRU"/>
                    <div class="validate"></div>
                </div>
            </div>
            <div class="col-6">
                <div class="form-group col-md-11"
                >
                    <label class="control-label" for="weight"><fmt:message key="profile.weight"/></label>
                    <input class="form-control"
                           data-msg="Enter a valid first name"
                           data-rule="firstnameexp" id="weight"
                           name="weight"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                >
                    <label class="control-label" for="height"><fmt:message key="profile.height"/></label>
                    <input class="form-control"
                           data-msg="Enter a valid last name"
                           data-rule="lastnameexp" id="height"
                           name="height"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                >
                    <label class="control-label" for="age"><fmt:message key="profile.age"/></label>
                    <!--                        <p style="font-size: 0.9em;">(not required)</p>-->
                    <input class="form-control"
                           data-msg="Enter a valid first name (ukrainian letters)"
                           data-rule="firstnameuaexp" id="age"
                           name="age"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11">
                    <label class="control-label" for="lifestyle"><fmt:message key="profile.lifestyle"/></label>
                    <select class="form-control" name="lifestyle" id="lifestyle">
                        <option value=""></option>
                        <c:forEach var="item" items="${lifestyleEnum}">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                    <div class="validate"></div>
                </div>

            </div>
        </div>
        <div class="container m2 mb-4">
            <button class="pretty-button" id="myBtn"><fmt:message key="profile.save"/></button>
        </div>
    </form>
</div>