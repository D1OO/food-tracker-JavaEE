<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="article-full d-flex flex-column m-4" data-aos="fade-zoom-in" data-aos-offset="0">
    <h4 class="mt-2">${article.titleLocalisation}</h4>
    <div class="d-inline-flex justify-content-between">
        <div class="d-inline-flex ml-2"><p style="color: gray">by</p>
            <p class="ml-2 font-medium">${article.authorName}</p></div>
        <p class="font-medium" style="color: gray">${article.date}</p>
    </div>
    <img class="m-2" src="data:image/jpg;base64,${article.base64Image}"
         style="border:none; border-radius: 0.2rem; object-fit: cover; width: 100%; height: 250px;
          margin-bottom: 2.5rem!important;"/>
    <p class="m-2">${article.textLocalisation}</p>
</div>


