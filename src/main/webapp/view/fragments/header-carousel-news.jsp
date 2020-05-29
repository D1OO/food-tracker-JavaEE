<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="carousel" data-flickity='{ "autoPlay": true, "pageDots": false, "wrapAround": true }'>
    <c:forEach var="news" items="${headerNews}" varStatus="loop">
        <div class=" carousel-cell"
             style="background-image: url('data:image/jpg;base64,${news.base64Image}');
                     background-size: cover">
            <a class="carousel-link" rel="group" role="link"
               onclick="loadFromServerIntoContentContainer('/read-article?id=${news.articleId}')">
                <h6 style="background-color: white; border-radius: 1rem; padding: 6px 10px 6px 10px;">${news.titleLocalisation}</h6>
            </a>
        </div>
    </c:forEach>
</div>