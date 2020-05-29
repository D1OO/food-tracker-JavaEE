<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Sign Up • Dreamfit"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<main id="main">
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-flex flex-grow-1 justify-content-center align-items-center mt-2"
                     data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%; background: #eeeeee; border: 1px solid #b8b8b8; border-radius: 0.5rem;">
                    <div class="maincontent d-flex flex-nowrap p-lg-3"
                         style="border: 7px solid rgba(171,9,78,0.31); border-radius: 0.5rem; background: rgba(221,252,207,0.25)">
                        <div class="mr-auto">
                            <div class="row d-flex justify-content-start form">
                                <div class="mb-5 mb-md-0">
                                    <h3 class="ml-2" style="text-align: center">Sign up</h3>
                                    <form class="form" id="user-sign-up-form">
                                        <div id="userSavingErrorBox"
                                             class="alert-danger alert col-md-11 mb-3 display-none" role="alert">
                                            <fmt:message key="entries.not-saved"/>
                                        </div>
<%--                                        <div class="col-md-12 mb-3" style="background: #eeeeee">--%>
<%--                                            <div class="loading" style="background: #eeeeee">Loading</div>--%>
<%--                                            <div class="error-message"></div>--%>
<%--                                            <div class="sent-message">filler</div>--%>
<%--                                        </div>--%>
                                        <div class="form-group col-md-11">
                                            <label class="control-label" for="username"><fmt:message
                                                    key="login.email"/></label>
                                            <input class="form-control"
                                                   data-msg="Incorrect email"
                                                   data-rule="email" id="username"
                                                   name="username"/>
                                            <p id="usernameError" class="errorServerValidation"></p>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label" for="password"><fmt:message
                                                    key="login.password"/></label>
                                            <input class="form-control"
                                                   data-msg="Enter a valid password <br>(8+ digit or latin characters)"
                                                   data-rule="pwrd" id="password"
                                                   name="password"
                                                   type="password"/>
                                            <div class="validate"></div>
                                            <p id="passwordError" class="errorServerValidation"></p>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label" for="firstNameEN"><fmt:message
                                                    key="login.first-name.en"/></label>
                                            <input class="form-control"
                                                   data-msg="Enter a valid first name"
                                                   data-rule="firstnameexp" id="firstNameEN"
                                                   name="firstNameEN"/>
                                            <div class="validate"></div>
                                            <p id="firstNameENError" class="errorServerValidation"></p>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label" for="firstNameRU"><fmt:message
                                                    key="login.first-name.ru"/></label>
                                            <input class="form-control"
                                                   data-msg="Enter a valid first name"
                                                   data-rule="firstnameexp" id="firstNameRU"
                                                   name="firstNameRU"/>
                                            <div class="validate"></div>
                                            <p id="firstNameRUError" class="errorServerValidation"></p>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label" for="userProfile.lastName"><fmt:message
                                                    key="login.last-name"/></label>
                                            <input class="form-control"
                                                   data-msg="Enter a valid last name"
                                                   data-rule="lastnameexp" id="userProfile.lastName"
                                                   name="lastName"/>
                                            <div class="validate"></div>
                                            <p id="lastNameError" class="errorServerValidation"></p>
                                        </div>
                                    </form>
                                    <div class="form-group col-md-5">
                                        <button class="pretty-button submit-button d-block w-100" <%--disabled="disabled"--%>
                                                type="submit" onclick="saveNewUser()" disabled="disabled">
                                            <fmt:message key="login.sign-up"/></button>
                                    </div>
                                    <div class="form-group col-md-12 ">
                                        <span class="mr-2"><fmt:message key="login.already-signed-up"/></span>
                                        <a style="color:#34ce57" href="login"><fmt:message key="login.login"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/view/fragments/footer.jsp"/>
<jsp:include page="/view/fragments/scripts.jsp"/>
<script src="static/js/registration.js" type="text/javascript"></script>

</body>
</html>
