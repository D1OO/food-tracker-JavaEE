<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container flex-column d-flex food-diary-container" data-aos="fade-zoom-in" data-aos-offset="0"
     style="width: 100%">
    <div class=" mb-4 m-4 font-rubick " style="width: 100%;">
        <h2><fmt:message key="food-diaty"/></h2>
        <h1>${dailyRecord.recordDate}</h1>
    </div>
    <div class="tabs row d-flex ml-2 mb-3" style="width: 100%">
        <c:forEach var="item" items="${data}" varStatus="loop">
            <button class="pretty-button m4 recordTab ml-3" th:each="rec,stat : ${data}"
                    onclick="'tabClick(\'.dailyRecord' + ${loop.index} +'\')'">${dateName}</button>
        </c:forEach>
        <button class="pretty-button m4 ml-3 nextb" style="background: rgba(40,0,169,0.36)"
                onclick="setContentContainerTo('/food-diary?d=' + ${lastDate})">
            <fmt:message key="diary.next"/>
        </button>
    </div>

    <c:forEach var="rec" items="${data}" varStatus="loop">
        <div class="record-tabs container m2  flex-grow-1 " style="max-height: available"
            <%--  th:style="${stat.first ? 'display:block' : 'display:none'}"--%>>
            <h2>${loop.index}</h2>

            <c:choose>
                <c:when test="${profileFilled}">
                    <div class="tab-container">
                        <div class="row ml-4 align-items-center">
                            <h4 class="mr-2"><fmt:message key="daily-cals-norm"/></h4>
                            <h4 class="logged-in-as">${recPercentage}%</h4>
                            <h4 class="float-right"> (${recTotalCalories}/${dailyCalsNorm}</h4>
                            <h4 class="font-rubick">)</h4>
                        </div>
                        <div>
                            <table class="col-8 table" style="width: 450px; font-size: 1.2em">
                                <thead style="font-size: 0.9em; font-weight: normal; text-align: right">

                                <th colspan="2" style="text-align: left">
                                </th>
                                <th class="bg" id="first"><fmt:message key="user.fat"/></th>
                                <th class="bg"><fmt:message key="user.carbs"/></th>
                                <th class="bg"><fmt:message key="user.prot"/></th>
                                <th class="bg"><fmt:message key="user.calories"/></th>

                                </thead>
                                <tbody style="text-align: right">
                                <tr>
                                    <td><fmt:message key="diary.total"/></td>
                                    <td></td>
                                    <td>${rec.getTotalFats()}</td>
                                    <td>${rec.getTotalProteins()}</td>
                                    <td>${rec.getTotalCarbs()}</td>
                                    <td>${rec.getTotalCalories()}</td>
                                </tr>
                                <c:forEach var="food" items="${rec.entries}">
                                    <tr>
                                        <td style="text-align: right; width: 100px">${food.food.name}</td>
                                        <td style=" vertical-align: middle; text-align: left; color: #747478; font-size: 0.7em">${food.quantity}g"</td>
                                        <td>${food.food.fats * food.quantity/100}</td>
                                        <td>${food.food.carbohydrates * food.quantity/100}</td>
                                        <td>${food.food.proteins * food.quantity/100}</td>
                                        <td style="background: #e2bfaf   ;font-weight: bold">${food.food.calories * food.quantity/100}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <p class="m-4 " style="font-style: italic; color: #787a7a; font-size: 1.2em"></p>
                    </div>
                </c:otherwise>
            </c:choose>

            <div class="tabs container m2 mb-4">
                <form method="post" class="data-food-modal-window${loop.index}">
                    <input id="profileId" name="profileId" value="${userId}" type="hidden">
                    <input id="recordId" name="recordId" value="${rec.recordId}" type="hidden">
                    <input id="recordDate" name="recordDate" value="${rec.recordDate}" type="hidden">
                </form>
                <button class="pretty-button" style="width: 12em; "
                        onclick="'openAddFoodModalWindow(\'.data-food-modal-window' + ${loop.index} +'\')'">
                    <fmt:message key="user.empy-list"/>
                </button>
            </div>
        </div>
    </c:forEach>
</div>