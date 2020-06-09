<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container" data-aos="fade-zoom-in" data-aos-offset="0">
    <div class="mt-3">
        <c:forEach var="member" items="${group}" varStatus="loop">
            <button class="pretty-button m4 recordTab ml-3  ${loop.first ? 'selected-record-button' : ''}"
                    onclick="tabClick('#user${loop.index}')">
                    ${member.userProfile.firstNameLocalisation} ${member.userProfile.lastName}
            </button>
        </c:forEach>
        <button class="pretty-button m4 recordTab ml-3"
                onclick="inviteMember()" style="border-radius: 100%; width: 2.4rem; background-color: #99ce9c">
            <fmt:message key="invite-member"/>
        </button>
        <div class="invite-section" style="display: none">

        </div>
        <c:forEach var="member" items="${group}" varStatus="loop">
            <div id="user${loop.index}" class="record-tabs mt-3 mb-3" style="display: none">
                <div class="userinfo d-inline-flex">
                    <div class="h2 mb-4 m-4 d-flex">${member.username}</div>
                    <div class="h5 m-auto d-flex">${member.userProfile.firstNameLocalisation} ${member.userProfile.lastName}</div>
                    <label class="h5 control-label m-auto" style="margin-left: 2rem !important;"><fmt:message
                            key="profile.age"/></label>
                    <label class="h5 m-auto">${member.userProfile.age}</label>
                    <label class="h5 control-label m-auto" style="margin-left: 2rem !important;"><fmt:message
                            key="profile.height"/></label>
                    <label class="h5 m-auto">${member.userProfile.height}</label>
                </div>
                <div class="container m2 mb-4 tabs">
                    <button class="modal-save-food pretty-button" onclick="updateUserProfile()"><fmt:message
                            key="profile.save"/></button>
                </div>
            </div>
        </c:forEach>
    </div>

</div>