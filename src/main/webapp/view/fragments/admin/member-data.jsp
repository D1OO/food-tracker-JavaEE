<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="user-tabs mt-3 mb-3">
    <div class="userinfo d-inline-flex">
        <div class="m-4 d-flex"><h2>${member.firstName} ${member.lastName}</h2></div>
        <label class="h5 control-label m-auto"
               style="margin-left: 2rem !important;margin-right: 0.5rem !important;"><fmt:message
                key="profile.age"/></label>
        <label class="h5 m-auto">${member.userProfileDTO.age}</label>
        <label class="h5 control-label m-auto"
               style="margin-left: 2rem !important;margin-right: 0.5rem !important;"><fmt:message
                key="profile.height"/></label>
        <label class="h5 m-auto">${member.userProfileDTO.height}</label>
        <div class="h5 mb-4 m-4 d-flex align-items-center">${member.username}</div>
    </div>
</div>
<div class="userDiary">
    <div class="tabs row d-flex ml-2 mb-3" style="width: 100%">
        <button class="pretty-button m4 ml-3 nextb ${prevWeekDay != null ? 'visible' : 'unvisible'}"
                onclick="loadFromServerIntoContentContainer('/food-diary?d=${prevWeekDay}')">
            <fmt:message key="diary.previous"/>
        </button>
        <c:forEach var="record" items="${paginatedWeeklyRecords}" varStatus="loop">
            <button class="pretty-button m4 recordTab ml-3  ${loop.first ? 'selected-record-button' : ''}"
                    onclick="tabClick('#dailyRecord${loop.index}')">
                    ${record.dateHeader}
            </button>
        </c:forEach>
        <button class="pretty-button m4 ml-3 nextb"
                onclick='loadFromServerIntoContentContainer("/food-diary?d=${nextWeekDay}")'>
            <fmt:message key="diary.next"/>
        </button>
    </div>

    <c:forEach var="rec" items="${paginatedWeeklyRecords}" varStatus="loop">
        <div id="dailyRecord${loop.index}" class="record-tabs container m2  flex-grow-1
             ${loop.first ? 'display-block' : 'display-none'}" style="max-height: available">
            <c:choose>
                <c:when test="${rec.totalCalories > 0}">
                    <div class="tab-container">
                        <div class="row ml-4 align-items-center">
                            <h4 class="mr-2"><fmt:message key="daily-cals-norm"/></h4>
                            <h4 class="logged-in-as">${rec.percentage}%</h4>
                            <h4 class="float-right ml-1"> (${rec.totalCalories}/${rec.dailyCaloriesNorm}</h4>
                            <h4 class="font-rubick">)</h4>
                        </div>
                        <div>
                            <table class="col-8 daily-record-table table" style="width: 450px; font-size: 1.2em; ">
                                <thead style="font-size: 0.9em; font-weight: normal; text-align: right">

                                <th colspan="2" style="text-align: left"></th>
                                <th class="bg" id="first"><fmt:message key="user.fat"/></th>
                                <th class="bg"><fmt:message key="user.carbs"/></th>
                                <th class="bg"><fmt:message key="user.prot"/></th>
                                <th class="bg"><fmt:message key="user.calories"/></th>

                                </thead>
                                <tbody>
                                <tr style="background: rgba(42,0,212,0.16)">
                                    <td><fmt:message key="diary.total"/></td>
                                    <td></td>
                                    <td>${rec.totalFats}</td>
                                    <td>${rec.totalProt}</td>
                                    <td>${rec.totalCarbs}</td>
                                    <td>${rec.totalCalories}</td>
                                </tr>
                                <c:forEach var="entry" items="${rec.entries}">
                                    <tr>
                                        <td style="text-align: right; width: 100px">${entry.food.name}</td>
                                        <td style=" vertical-align: middle; text-align: left; color: #747478; font-size: 0.7em">${entry.quantity}g</td>
                                        <td style="text-align: right; width: 100px">${entry.entryFats}</td>
                                        <td>${entry.entryCarbs}</td>
                                        <td>${entry.entryProt}</td>
                                        <td style="background: #e2bfaf   ;font-weight: bold">${entry.entryCalories}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <p class="m-4 " style="font-style: italic; color: #787a7a; font-size: 1.2em">
                            <fmt:message key="group.list-is-empty"/>
                        </p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>
</div>