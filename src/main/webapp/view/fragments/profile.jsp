<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container" data-aos="fade-zoom-in" data-aos-offset="0">
    <c:if test="${notifications ne null}">
        <div class="notifications-container">
            <c:forEach var="notif" items="${notifications}" varStatus="loop">
                <div class="notification ml-4 mr-4 mt-4 p-3 d-inline-flex align-items-center"
                     style="background-color: #d8f2e6; border-radius: 0.4rem;">
                    <h5 class="mb-0"><label class="mb-0">${notif.message}</label></h5>
                    <label class="notification-from ml-2 mb-0"><fmt:message key="notification-from"/></label>
                    <label class="ml-2 notification-sender mb-0">
                            ${notif.sender.firstNameLocalisation} ${notif.sender.lastName}</label>
                    <label class="notification-from ml-2 mb-0">at ${notif.dateTime}</label>
                    <button class="modal-save-food pretty-button m4  ml-3"
                            onclick="acceptInvite('${notif.sender.firstNameLocalisation}', '${notif.dateTime}')">
                        accept
                    </button>
                    <button class="modal-back pretty-button m4  ml-3"
                            onclick="declineInvite('${notif.sender.firstNameLocalisation}', '${notif.dateTime}')">
                        decline
                    </button>
                </div>
            </c:forEach>
        </div>
    </c:if>
    <div class="h2 mb-4 m-4 d-flex">${user.username}</div>
    <div class="h5 mb-4 m-4 d-flex">${user.firstNameLocalisation} ${user.userProfileDTO.lastName}</div>
    <form id="update-profile-form" class="form m-4" style="width: 100%">
        <div class="row" style="width: 100%;">
            <div class="col-6">
                <div class="form-group col-md-11">
                    <label class="control-label" for="firstName"><fmt:message key="login.first-name.en"/></label>
                    <input class="form-control"
                           value="${user.userProfileDTO.firstNameEN}"
                           name="firstNameEN"/>
                    <p id="firstNameENError" class="errorServerValidation"></p>
                </div>
                <div class="form-group col-md-11">
                    <label class="control-label" for="lastName"><fmt:message key="login.last-name"/></label>
                    <input class="form-control"
                           value="${user.userProfileDTO.lastName}"
                           name="lastName"/>
                    <p id="lastNameError" class="errorServerValidation"></p>
                </div>
                <div class="form-group col-md-11">
                    <label class="control-label" for="firstNameRu"><fmt:message key="login.first-name.ru"/></label>
                    <input class="form-control"
                           name="firstNameRU"/>
                    <p id="firstNameRUError" class="errorServerValidation"></p>
                </div>
                <div class="form-group col-md-11">
                    <label class="control-label" for="lifestyle"><fmt:message key="profile.lifestyle"/></label>
                    <select class="form-control" name="lifestyle" id="lifestyle">
                        <%--                        <option value="${user.userProfileDTO.lifestyle}">${user.userProfileDTO.lifestyle}</option>--%>
                        <c:forEach var="item" items="${lifestyleEnum}">
                            <option class="option" ${user.userProfileDTO.lifestyle eq item ? 'selected' : ''}
                                    value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-6">
                <div class="form-group col-md-5">
                    <label class="control-label" for="age"><fmt:message key="profile.age"/></label>
                    <input class="form-control"
                           value="${user.userProfileDTO.age}"
                           name="age"/>
                    <p id="ageError" class="errorServerValidation"></p>
                </div>
                <div class="form-group col-md-5">
                    <label class="control-label" for="weight"><fmt:message key="profile.weight"/></label>
                    <input class="form-control"
                           value="${user.userProfileDTO.weight}"
                           name="weight"/>
                    <p id="weightError" class="errorServerValidation"></p>
                </div>
                <div class="form-group col-md-5">
                    <label class="control-label" for="height"><fmt:message key="profile.height"/></label>
                    <input class="form-control"
                           value="${user.userProfileDTO.height}"
                           name="height"/>
                    <p id="heightError" class="errorServerValidation"></p>
                </div>
            </div>
        </div>
    </form>
    <div class="container m2 mb-4 tabs">
        <button class="modal-save-food pretty-button" onclick="updateUserProfile()"><fmt:message key="profile.save"/></button>
    </div>
</div>