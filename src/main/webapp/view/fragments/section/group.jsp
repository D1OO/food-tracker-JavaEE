<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container" data-aos="fade-zoom-in" data-aos-offset="0">
    <div class="mt-3 d-flex flex-column">
        <div class="d-inline-flex">
            <c:forEach var="member" items="${group}" varStatus="loop">
                <button class="pretty-button m4 userTab ml-3"
                        onclick="userTabClick('${member.username}')">
                        ${member.userProfile.firstNameLocalisation} ${member.userProfile.lastName}
                </button>
            </c:forEach>
            <button class="pretty-button m4 ml-3"
                    onclick="inviteMember()"
                    style="border:none; border-radius: 100%; width: 2.4rem; background-color: #99ce9c">
                <fmt:message key="invite-member"/>
            </button>
        </div>
        <div class="invite-section" style="display: none">
            <div class="d-inline-flex mt-4 align-items-center" style="width: 100%">
                <input class="form-control col-5 ml-4 align-self-center" id="inviteemail"
                       placeholder="e-mail"/>
                <button class="pretty-button m4 recordTab ml-3"
                        onclick="sendInvite()" style="background-color: #99ce9c">
                    <fmt:message key="invite-member-button"/>
                </button>
                <p class="ml-2" id="send-success" style="color: green; display: none">Success</p>
            </div>
        </div>
        <div id="selected-user-data"></div>
    </div>

</div>