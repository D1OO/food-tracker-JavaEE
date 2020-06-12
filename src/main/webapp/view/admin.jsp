    <%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Dreamfit Pro • ${user.firstName} ${user.lastName}"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<main id="main">
    <div class="site-section pb-0" id="bg" style="height: 100%">
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
                                            onclick="loadFromServerIntoContentContainer('group')"><fmt:message
                                            key="my-group"/>
                                    </button>
                                    <button class="pretty-button  menu-pr-button bg my-2"
                                            onclick="loadFromServerIntoContentContainer('feed')"><fmt:message
                                            key="manage-feed"/>
                                    </button>
                                    <button class="pretty-button  menu-pr-button bg my-2"
                                            onclick="loadFromServerIntoContentContainer('profile')"><fmt:message
                                            key="profile"/>
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
<script src="static/js/section-container.js" type="text/javascript"></script>
<script src="static/js/admin.js" type="text/javascript"></script>
<script src="static/js/profile.js" type="text/javascript"></script>
<script src="static/js/modal-window/window.js" type="text/javascript"></script>
<script src="static/js/modal-window/window-admin.js" type="text/javascript"></script>
</body>
</html>