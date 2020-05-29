<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Login • Dreamfit"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<main id="main">
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-inline-block" data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%">
                    <div class="maincontent d-flex flex-nowrap p-lg-3" style="height:100%; background: #eeeeee">
                        <img class="pagepic" src="static/img/loginpic.jpg" alt="">
                        <div class="mr-auto">
                            <div class="row d-flex justify-content-start form">
                                <div class=" mb-5 mb-md-0">
                                    <div id="signupSuccessBox" class="alert-success alert col-md-11 mb-3 display-none"
                                         role="alert">
                                        <fmt:message key="signup-success"/>
                                    </div>
                                    <form class="form" method="post" action="log-in">
                                        <div class="col-md-11 form-group">
                                            <label for="email">E-mail</label>
                                            <input class="form-control"
                                                   data-msg="Incorrect email"
                                                   data-rule="email"
                                                   id="email"
                                                   name="username"/>
                                            <div class="validate"></div>
                                        </div>
                                        <div class="col-md-11 form-group">
                                            <label for="password">Password</label>
                                            <input class="form-control"
                                                   data-msg="Enter a valid password <br>((8+ digit or latin characters)"
                                                   data-rule="pwrd"
                                                   id="password"
                                                   name="password"
                                                   type="password">
                                            <div class="validate"></div>
                                        </div>
                                        <div class="col-md-11 mb-3" style="background: #eeeeee">
                                            <div class="loading">Loading</div>
                                            <div class="error-message"></div>
                                            <div class="sent-message">fifler</div>
                                        </div>

                                        <div class="col-md-6 form-group">
                                            <input class="pretty-button d-block w-100 submit-button" disabled="disabled"
                                                   type="submit"
                                                   value="SIGN IN">
                                        </div>

                                        <div class="col-md-12 form-group">
                                            <span>Don't have an account? <a style="color:#34ce57" href="registration"><fmt:message key="login.sign-up"/></a></span>
                                        </div>
                                    </form>
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
<script src="static/js/login.js" type="text/javascript"></script>

</body>
</html>



