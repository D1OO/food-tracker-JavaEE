<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container  container justify-content-center align-items-center" data-aos="fade-zoom-in"
     data-aos-offset="0" style="width: 100%; height: 100%">
    <div class="d-flex flex-grow-1 justify-content-center align-items-center" style="width: 100%; height: 100%">
        <button class="pretty-button" onclick="setContentContainerToEndpoint('/profile')" style="font-size: 1rem">
            Complete your profile
        </button>
        <h4 class="ml-2">to proceed</h4>
    </div>
</div>