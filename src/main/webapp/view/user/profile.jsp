<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="content-container" data-aos="fade-zoom-in" data-aos-offset="0">
    <div class="h2 mb-4 m-4 font-rubick d-flex">${userFirstName}</div>
    <form class="form" method="post" style="width: 100%" action="update-profile">
<%--        <input id="profileId" name="profileId" th:value="${#authentication.getPrincipal().getId()}" type="hidden"/>--%>
        <div class="row" style="width: 100%;">
            <div class="col-6">
                <div class="form-group col-md-11">
                    <label class="control-label" for="firstName">login.first-name</label>
                    <input class="form-control"
                           data-msg="Enter a valid first name"
                           data-rule="firstnameexp" id="firstName"
                           name="firstName"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                    >
                    <label class="control-label" for="lastName">login.last-name</label>
                    <input class="form-control"
                           data-msg="Enter a valid last name"
                           data-rule="lastnameexp" id="lastName"
                          name="lastName"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                    >
                    <label class="control-label" for="firstNameUa">First name UA</label>
                    <p style="font-size: 0.9em;">(not required)</p>
                    <input class="form-control"
                           data-msg="Enter a valid first name (ukrainian letters)"
                           data-rule="firstnameuaexp" id="firstNameUa"
                          name="firstNameUa"/>
                    <div class="validate"></div>
                </div>
            </div>
            <div class="col-6">
                <div class="form-group col-md-11"
                    >
                    <label class="control-label" for="weight">Weight</label>
                    <input class="form-control"
                           data-msg="Enter a valid first name"
                           data-rule="firstnameexp" id="weight"
                          name="weight"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                   >
                    <label class="control-label" for="height">Height</label>
                    <input class="form-control"
                           data-msg="Enter a valid last name"
                           data-rule="lastnameexp" id="height"
                           name="height"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11"
                  >
                    <label class="control-label" for="age">Age</label>
                    <!--                        <p style="font-size: 0.9em;">(not required)</p>-->
                    <input class="form-control"
                           data-msg="Enter a valid first name (ukrainian letters)"
                           data-rule="firstnameuaexp" id="age"
                           name="age"/>
                    <div class="validate"></div>
                </div>
                <div class="form-group col-md-11">
                    <label class="control-label" for="lifestyle">Lifestyle</label>
                    <select name="department">
                        <c:forEach var="item" items="${dept}">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                    <select class="form-control" name="lifestyle" id="lifestyle">
                        <option value=""> </option>
                        <c:forEach var="item" items="${lifestyleEnum}">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
<%--                        <option th:each="colorOpt : ${T(net.shvdy.sbproject.entity.UserProfile.Lifestyle).values()}"--%>
<%--                                th:text="${colorOpt}" th:value="${colorOpt}"></option>--%>
                    </select>
                    <div class="validate"></div>
                </div>

            </div>
        </div>
        <div class="container m2 mb-4">
            <button class="pretty-button" id="myBtn">Save changes</button>
        </div>
    </form>
</div>