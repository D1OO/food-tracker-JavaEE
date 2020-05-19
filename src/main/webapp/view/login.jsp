<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Localized messages -->
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>
<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>
<body>
<%--<jsp:include page="/WEB-INF/view/fragments/navbar.jsp"/>--%>
<%@ include file="/view/fragments/navbar.jsp" %>
<div class="maincontent d-flex flex-nowrap p-lg-3" style="height:100%; background: #eeeeee">
    <%--                <img class="pagepic" th:src="@{/img/loginpic.jpg}">--%>
    <div class="mr-auto">
        <div class="row d-flex justify-content-start form">
            <div class=" mb-5 mb-md-0">
                <%--                            <div class="alert alert-info col-md-11 mb-3" role="alert" th:if="${logout}">--%>
                <%--                                You've been logged out--%>
                <%--                                successfully.--%>
                <%--                            </div>--%>
                <%--                            <div class="alert alert-danger col-md-11 mb-3" role="alert" th:if="${error}">--%>
                <%--                                Invalid Username or--%>
                <%--                                Password!--%>
                <%--                            </div>--%>
                <%--                            <div class="alert alert-success col-md-11 mb-3" role="alert" th:if="${signedup}">--%>
                <%--                                Signed up successfully!--%>
                <%--                            </div>--%>
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

                    <%--                                <div class="col-md-11 mb-3" style="background: #eeeeee">--%>
                    <%--                                    <div class="loading">Loading</div>--%>
                    <%--                                    <div class="error-message"></div>--%>
                    <%--                                    <div class="sent-message">fifler</div>--%>
                    <%--                                </div>--%>


                    <div class="col-md-6 form-group">
                        <input class="pretty-button d-block w-100 submit-button" <%--disabled="disabled"--%> type="submit"
                               value="SIGN IN">
                    </div>

                    <div class="col-md-12 form-group">
                        <span>Don't have an account? <a style="color:#34ce57" href="registration">SIGN UP</a></span>
                    </div>
                    <%--                                <div class="col-md-11 mb-2" style="color: #a55444; font-size: 0.8em">--%>
                    <%--                                    <div class="alert alert-danger col-md-12 mb-3" role="alert"--%>
                    <%--                                         th:if="${error}">Invalid Username or Password!</div>--%>
                    <%--                                </div>--%>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>



