<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container flex-column d-flex food-diary-container" data-aos="fade-zoom-in" data-aos-offset="0"
     style="width: 100%">
    <div class=" mb-4 m-4 font-rubick " style="width: 100%;">
        <h2><fmt:message key="last-news"/></h2>
    </div>
    <div class="d-flex flex-column content-container">
        <c:forEach var="article" items="${paginatedArticles}" varStatus="loop">
            <button role="link"
                    onclick="loadFromServerIntoContentContainer('/article?id=${article.articleId}')"
                    class="article-block d-inline-flex flex-grow-1 justify-content-left row mx-md-3 mb-2">
                <img class="m-2" src="data:image/jpg;base64,${article.base64Image}"
                     style="border-radius: 0.2rem; object-fit: cover; width: 200px; height: 120px"/>
                <div class="col-8">
                    <h4 class="mt-2" style="text-align: left">${article.titleLocalisation}</h4>
                    <p class="float-left" style="color: gray">${article.date}</p>
                </div>
            </button>
        </c:forEach>
        <c:if test="${user.role eq 'ADMIN'}">
            <div class="tabs container m2 mb-4">
                <button class="pretty-button open-modal" style="width: 12em; "
                        onclick="openCreateArticleModalWindow()">
                    <fmt:message key="add-new"/>
                </button>
            </div>
        </c:if>
    </div>
</div>