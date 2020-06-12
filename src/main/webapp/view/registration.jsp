<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Dreamfit • Sign Up"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<main id="main">
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-flex flex-grow-1 justify-content-center align-items-center mt-2"
                     data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%; background-image: url('/static/img/271678.jpg'); background-size: cover; border: 1px solid #b8b8b8; border-radius: 0.5rem;">
                    <div class="maincontent d-flex flex-nowrap p-lg-3"
                         style="box-shadow: 0px 0px 45px -5px rgba(0,0,0,0.68); border: 5px solid rgba(171,9,78,0.31); border-radius: 0.5rem; background: white">
                        <div class="mr-auto">
                            <div class="row d-flex justify-content-start form">
                                <div class="mb-5 mb-md-0">
                                    <h3 class="ml-2" style="text-align: center">Sign up</h3>
                                    <form class="form" id="user-sign-up-form">
                                        <div id="userSavingErrorBox"
                                             class="alert-danger alert col-md-11 mb-3 display-none" role="alert">
                                            <fmt:message key="entries.not-saved"/>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label"><fmt:message key="login.email"/></label>
                                            <input class="form-control required" name="username"/>
                                            <p id="usernameError" class="errorServerValidation"></p>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label"><fmt:message key="login.password"/></label>
                                            <input class="form-control required" name="password" type="password"/>
                                            <p id="passwordError" class="errorServerValidation"></p>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label"><fmt:message key="login.first-name"/></label>
                                            <input class="form-control required" name="firstName"/>
                                            <p id="firstNameError" class="errorServerValidation"></p>
                                        </div>
                                        <div class="form-group col-md-11">
                                            <label class="control-label"><fmt:message key="login.last-name"/></label>
                                            <input class="form-control required" name="lastName"/>
                                            <p id="lastNameError" class="errorServerValidation"></p>
                                        </div>
                                    </form>
                                    <div class="form-group col-md-5">
                                        <button class="pretty-button submit-button d-block w-100"
                                                type="submit" onclick="saveNewUser()" disabled="disabled">
                                            <fmt:message key="login.sign-up"/>
                                        </button>
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
<script src="static/js/form-fields-filled-checks.js" type="text/javascript"></script>
</body>
</html>
