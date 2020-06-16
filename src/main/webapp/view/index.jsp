<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Dreamfit • Nutrition tracking"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>
<main class="maincontent">
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-flex flex-grow-1 mt-2" data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%;">
                    <div class=" p-lg-3 d-flex flex-grow-1 index-block">
                        <div class="d-flex flex-grow-1 flex-column mb-5 mb-md-0 align-content-md-start"
                             style="margin-top: 10%">
                            <h1 style="text-align: center">Track your nutrition, fitness & health data.</h1>
                            <h2 style="text-align: center">Log your diet, exercise, biometrics and notes.</h2>
                            <c:if test="${userRole eq 'GUEST'}">
                                <div class="form-group d-inline-flex justify-content-center mt-5">
                                    <a class="btn-success p-3 pl-5 pr-5"
                                       style="border-radius: 0.2rem; color:rgb(251,251,255); text-decoration: none"
                                       href="registration"><fmt:message key="login.sign-up"/></a>
                                </div>
                            </c:if>
                            <c:if test="${userRole ne 'GUEST'}">
                                <div class="form-group d-inline-flex justify-content-center mt-5">
                                    <a class="btn-success p-3 pl-5 pr-5"
                                       style="border-radius: 0.2rem; color:rgb(251,251,255); text-decoration: none"
                                       href="feed"><fmt:message key="home"/></a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="/view/fragments/footer.jsp"/>
<jsp:include page="/view/fragments/scripts.jsp"/>
</body>
</html>