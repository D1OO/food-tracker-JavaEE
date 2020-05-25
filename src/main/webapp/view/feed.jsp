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

            <div class="article-block d-inline-flex flex-grow-1 justify-content-between row mx-md-3 mb-2"
                 style="background-color:rgba(228,228,228,0.2);">
                <div class="col-7">
                    <h4 class="mt-2">${article.title}</h4>
                    <p class="float-right" style="color: gray">${article.date}</p>
                </div>
                <img class="m-2" src="data:image/jpg;base64,${article.base64Image}"
                     style="border: 1px solid black; border-radius: 0.2rem; object-fit: cover; width: 200px; height: 120px"/>

            </div>
        </c:forEach>
    </div>
    <%--    <div class="tabs row d-flex ml-2 mb-3" style="width: 100%">--%>
    <%--        <button class="pretty-button m4 ml-3 nextb ${prevWeekDay != null ? 'visible' : 'unvisible'}"--%>
    <%--                style="background: rgba(40,0,169,0.36)"--%>
    <%--                onclick="setContentContainerTo('/food-diary?d=${prevWeekDay}')">--%>
    <%--            <fmt:message key="diary.previous"/>--%>
    <%--        </button>--%>
    <%--        <c:forEach var="record" items="${paginatedWeeklyRecords}" varStatus="loop">--%>
    <%--            <button class="pretty-button m4 recordTab ml-3  ${loop.first ? 'selected-record-button' : ''}"--%>
    <%--                    onclick="tabClick('#dailyRecord${loop.index}')">--%>
    <%--                    ${record.dateHeader}--%>
    <%--            </button>--%>
    <%--        </c:forEach>--%>
    <%--        <button class="pretty-button m4 ml-3 nextb" style="background: rgba(40,0,169,0.36)"--%>
    <%--                onclick='setContentContainerTo("/food-diary?d=${nextWeekDay}")'>--%>
    <%--            <fmt:message key="diary.next"/>--%>
    <%--        </button>--%>
    <%--    </div>--%>

    <%--    <c:forEach var="rec" items="${paginatedWeeklyRecords}" varStatus="loop">--%>
    <%--        <div id="dailyRecord${loop.index}" class="record-tabs container m2  flex-grow-1--%>
    <%--             ${loop.first ? 'display-block' : 'display-none'}" style="max-height: available"--%>
    <%--             data-aos="fade-zoom-in" data-aos-offset="0">--%>
    <%--            <c:choose>--%>
    <%--                <c:when test="${rec.totalCalories > 0}">--%>
    <%--                    <div class="tab-container">--%>
    <%--                        <div class="row ml-4 align-items-center">--%>
    <%--                            <h4 class="mr-2"><fmt:message key="daily-cals-norm"/></h4>--%>
    <%--                            <h4 class="logged-in-as">${rec.percentage}%</h4>--%>
    <%--                            <h4 class="float-right"> (${rec.totalCalories}/${rec.dailyCaloriesNorm}</h4>--%>
    <%--                            <h4 class="font-rubick">)</h4>--%>
    <%--                        </div>--%>
    <%--                        <div>--%>
    <%--                            <table class="col-8 table" style="width: 450px; font-size: 1.2em">--%>
    <%--                                <thead style="font-size: 0.9em; font-weight: normal; text-align: right">--%>

    <%--                                <th colspan="2" style="text-align: left">--%>
    <%--                                </th>--%>
    <%--                                <th class="bg" id="first"><fmt:message key="user.fat"/></th>--%>
    <%--                                <th class="bg"><fmt:message key="user.carbs"/></th>--%>
    <%--                                <th class="bg"><fmt:message key="user.prot"/></th>--%>
    <%--                                <th class="bg"><fmt:message key="user.calories"/></th>--%>

    <%--                                </thead>--%>
    <%--                                <tbody style="text-align: right">--%>
    <%--                                <tr style="background: rgba(42,0,212,0.16)">--%>
    <%--                                    <td><fmt:message key="diary.total"/></td>--%>
    <%--                                    <td></td>--%>
    <%--                                    <td>${rec.totalFats}</td>--%>
    <%--                                    <td>${rec.totalProt}</td>--%>
    <%--                                    <td>${rec.totalCarbs}</td>--%>
    <%--                                    <td>${rec.totalCalories}</td>--%>
    <%--                                </tr>--%>
    <%--                                <c:forEach var="entry" items="${rec.entries}">--%>
    <%--                                    <tr>--%>
    <%--                                        <td style="text-align: right; width: 100px">${entry.food.name}</td>--%>
    <%--                                        <td style=" vertical-align: middle; text-align: left; color: #747478; font-size: 0.7em">${entry.quantity}g</td>--%>
    <%--                                        <td style="text-align: right; width: 100px">${entry.entryFats}</td>--%>
    <%--                                        <td>${entry.entryCarbs}</td>--%>
    <%--                                        <td>${entry.entryProt}</td>--%>
    <%--                                        <td style="background: #e2bfaf   ;font-weight: bold">${entry.entryCalories}</td>--%>
    <%--                                    </tr>--%>
    <%--                                </c:forEach>--%>
    <%--                                </tbody>--%>
    <%--                            </table>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                </c:when>--%>
    <%--                <c:otherwise>--%>
    <%--                    <div>--%>
    <%--                        <p class="m-4 " style="font-style: italic; color: #787a7a; font-size: 1.2em">--%>
    <%--                            <fmt:message key="user.empy-list"/>--%>
    <%--                        </p>--%>
    <%--                    </div>--%>
    <%--                </c:otherwise>--%>
    <%--            </c:choose>--%>

    <%--            <div class="tabs container m2 mb-4">--%>
    <%--                <form method="post" class="data-food-modal-window${loop.index}">--%>
    <%--                    <input id="profileId" name="profileId" value="${user.userId}" type="hidden">--%>
    <%--                    <input id="recordId" name="recordId" value="${rec.recordId}" type="hidden">--%>
    <%--                    <input id="recordDate" name="recordDate" value="${rec.recordDate}" type="hidden">--%>
    <%--                </form>--%>
    <%--                <button class="pretty-button open-modal" style="width: 12em; "--%>
    <%--                        onclick="openAddFoodModalWindow('.data-food-modal-window' + ${loop.index})">--%>
    <%--                    <fmt:message key="add-new"/>--%>
    <%--                </button>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </c:forEach>--%>
    <div class="tabs container m2 mb-4">
        <button class="pretty-button open-modal" style="width: 12em; "
                onclick="openNewArticleModalWindow()">
            <fmt:message key="add-new"/>
        </button>
    </div>
</div>