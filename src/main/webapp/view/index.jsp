
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

<main id="main" >
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-inline-block" data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%">
                    <img style="max-width: 100%" src="static/img/bg7.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/view/fragments/footer.jsp"/>
<jsp:include page="/view/fragments/scripts.jsp"/>

</body>
</html>