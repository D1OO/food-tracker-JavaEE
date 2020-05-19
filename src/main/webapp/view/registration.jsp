<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Localized messages -->
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>
<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Sign-Up"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>
<div class="maincontent d-flex flex-nowrap p-lg-3" style="height:100%; background: #eeeeee">
    <%--    <img class="pagepic" th:src="@{/img/signuppic.jpg}">--%>
    <div class="mr-auto">
        <div class="row d-flex justify-content-start form">
            <div class=" mb-5 mb-md-0">
                <form class="form" method="post" action="sign-up">
                    <%--                    <p class="error-validation"--%>
                    <%--                       th:each="error : ${#fields.errors('global')}"--%>
                    <%--                       th:if="${#fields.hasGlobalErrors()}"--%>
                    <%--                       th:text="${error}">Validation error</p>--%>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="username"><fmt:message key="login.email"/></label>
                        <input class="form-control"
                               data-msg="Incorrect email"
                               data-rule="email" id="username"
                               name="username"/>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="password"><fmt:message key="login.password"/></label>
                        <input class="form-control"
                               data-msg="Enter a valid password <br>(8+ digit or latin characters)"
                               data-rule="pwrd" id="password"
                               name="password"
                               type="password"/>
                        <div class="validate"></div>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="userProfile.firstName"><fmt:message
                                key="login.first-name"/></label>
                        <input class="form-control"
                               data-msg="Enter a valid first name"
                               data-rule="firstnameexp" id="userProfile.firstName"
                               name="firstName"/>
                        <div class="validate"></div>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="userProfile.lastName"><fmt:message
                                key="login.last-name"/></label>
                        <input class="form-control"
                               data-msg="Enter a valid last name"
                               data-rule="lastnameexp" id="userProfile.lastName"
                               name="lastName"/>
                        <div class="validate"></div>
                    </div>

                    <div class="form-group col-md-5">
                        <button class="pretty-button submit-button d-block w-100" <%--disabled="disabled"--%> type="submit">
                            <fmt:message key="login.sign-up"/></button>
                    </div>
                    <div class="form-group col-md-12 ">
                        <span class="mr-2"><fmt:message key="login.already-signed-up"/></span>
                        <a style="color:#34ce57" href="login"><fmt:message key="login.login"/></a>
                    </div>

                    <%--                    <div class="alert alert-info col-md-12 mb-3" role="alert" th:if="${logout}">You've been logged--%>
                    <%--                        out--%>
                    <%--                        successfully.--%>
                    <%--                    </div>--%>
                    <%--                    <div class="alert alert-danger col-md-12 mb-3" role="alert" th:if="${error}">Invalid Username or--%>
                    <%--                        Password!--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-md-12 mb-3" style="background: #eeeeee">--%>
                    <%--                        <div class="loading" style="background: #eeeeee">Loading</div>--%>
                    <%--                        <div class="error-message"></div>--%>
                    <%--                        <div class="sent-message">filler</div>--%>
                    <%--                    </div>--%>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
