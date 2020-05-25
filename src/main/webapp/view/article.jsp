<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="article-full d-flex flex-column m-4" data-aos="fade-zoom-in" data-aos-offset="0">
    <div class="">
        <h4 class="mt-2">${title}</h4>
        <p class="float-right" style="color: gray">${date}</p>
    </div>
    <img class="m-2" src="data:image/jpg;base64,${base64Image}"
         style="border: 1px solid black; border-radius: 0.2rem; object-fit: cover; width: 100%; height: 250px"/>
    <p>${text}</p>
</div>


