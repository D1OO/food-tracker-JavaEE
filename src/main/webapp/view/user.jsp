<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Your Nutrition Diary • Dreamfit"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<main id="main">
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-inline-block" data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%">
                    <div id="modal-window" style="display: none"></div>
                    <div class="maincontent d-flex" style="height: 100%">
                        <div class="d-inline-flex" style="width: 100%">
                            <div class="d-flex flex-column tabs mx-2 flex-grow-1" style="width: 20%">
                                <button class="pretty-button menu-pr-button bg  my-2"
                                        onclick="setContentContainerTo('food-diary')"><fmt:message key="diary"/>
                                </button>
                                <button class="pretty-button  menu-pr-button bg my-2"
                                        onclick="setContentContainerTo('profile')"><fmt:message key="profile"/>
                                </button>
                            </div>
                            <div class="maincontent  d-flex flex-grow-1  m-2" id="content-container"
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
<script id="change-lang-param" src="static/js/content-container-and-modal-window.js" type="text/javascript"></script>

</body>
</html>