<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="top-container container">
    <nav class="navbar navbar-light custom-navbar pt-2">
        <div class="container px-0">
            <div class="align-self-start d-inline-flex mt-sm-1">
                <span class="date-label p-lg-2 mr-lg-5 ">${localizedDate}</span>
            </div>
            <div class="d-flex flex-grow-1 justify-content-center align-items-end "
                 style="position: relative; left: -2%;">
                <a href="${pageContext.request.contextPath}/" style="text-decoration: none">
                    <h1 style="font-family:'Run Medium',serif;font-weight:normal;font-size:42px;">dreamfit</h1></a>
            </div>
            <div class="align-self-start d-inline-flex mt-sm-1">
                <div class="navbar-right mr-4">
                    <c:if test="${userRole eq 'GUEST'}">
                        <li class=" list-inline-item">
                            <a class="pretty-button bg" href="/login"><fmt:message key="sign-in"/></a>
                            <a class="pretty-button bg signupButton"
                               href="${pageContext.request.contextPath}/registration"><fmt:message
                                    key="create-account"/></a>
                        </li>
                    </c:if>
                    <c:if test="${userRole ne 'GUEST'}">
                        <div class="d-inline-flex">
                            <c:if test="${userRole eq 'ADMIN'}">
                                <div class="logged-in-as" style="background-color: #e2cd51; color: black !important;">
                                    PRO
                                </div>
                            </c:if>
                            <div class="logged-in-as mr-3 ml-3"
                                 style="font-size: 1.1em; max-width: 560px">${user.firstName} ${user.lastName}</div>
                            <a class="pretty-button bg signupButton" href="logout"><fmt:message key="logout"/></a>
                        </div>
                    </c:if>
                </div>
                <a class="burger" data-target="#main-navbar" data-toggle="collapse" href="#">
                    <span></span>
                </a>
            </div>
        </div>
    </nav>
</div>