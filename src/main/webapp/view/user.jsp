<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Dreamfit • ${user.firstNameLocalisation} ${user.lastName}"/>
</jsp:include>
<body>

<main id="main">
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <jsp:include page="/view/fragments/navbar.jsp"/>
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div id="modal-window" style="display: none"></div>
                <div class="d-inline-block" data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%">
                    <jsp:include page="/view/fragments/header-carousel-news.jsp"/>
                    <div class="maincontent d-flex">
                        <div class="d-inline-flex" style="width: 100%">
                            <input id="sectionToFetchWithAJAX" value="${sectionToFetchWithAJAX}" type="hidden">
                            <div class="d-flex flex-column tabs mx-2" style="width: 20%">
                                <div class="menu d-flex flex-column">
                                    <button class="pretty-button  menu-pr-button bg my-2"
                                            onclick="loadFromServerIntoContentContainer('feed')">
                                        <fmt:message key="my-feed"/>
                                    </button>
                                    <button class="pretty-button menu-pr-button bg  my-2"
                                            onclick="loadFromServerIntoContentContainer('food-diary')">
                                        <fmt:message key="diary"/>
                                    </button>
                                    <button class="pretty-button  menu-pr-button bg my-2"
                                            onclick="loadFromServerIntoContentContainer('profile')">
                                        <fmt:message key="profile"/>
                                    </button>
                                </div>
                            </div>
                            <div class="maincontent m-2" id="content-container"
                                 style="border-radius: 5px"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="/view/fragments/footer.jsp"/>
<jsp:include page="/view/fragments/scripts.jsp"/>
<script src="static/js/user.js" type="text/javascript"></script>
</body>
</html>