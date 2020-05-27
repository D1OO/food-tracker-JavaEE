<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="modal" id="myModal" data-aos="fade-zoom-in" data-aos-offset="" style="margin-top: -6rem;">
    <div class="modal-content">
        <div class="modal-header">
            <p class="p-1 mt-2 mb-2" style="size:4em"><fmt:message key="article.create-article"/></p>
            <span class="close mt-0" onclick="closeAddFoodModalWindow()">&times;</span>
        </div>
        <div class="modal-body container" id="modalbody">
            <div class="mt-4" id="createfoodcontainer">
                <form class="form" id="createarticleform" method="post" enctype="multipart/form-data"
                      style="width: 100%" action="save-new-article">
                    <input name="authorId" value="${user.userId}" type="hidden">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group col-md-11">
                                <label class="control-label" for="name"><fmt:message key="article.name"/></label>
                                <input class="form-control"
                                       data-msg="Enter a valid first name"
                                       data-rule="firstnameexp" id="name"
                                       name="title"/>
                                <div class="validate"></div>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group col-md-11 col">
                                <label class="control-label" for="article-image"><fmt:message
                                        key="article.image"/></label>
                                <input class="form-control-file"
                                       data-msg="Enter a valid last name"
                                       data-rule="lastnameexp" id="article-image"
                                       type="file"
                                       name="image"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label" for="fats"><fmt:message key="article.text"/></label>
                        <textarea class="form-control"
                                  data-msg="Enter a valid first name"
                                  data-rule="firstnameexp" id="fats"
                                  type="text" style="width: 740px; height: 380px"
                                  name="text"></textarea>
                        <div class="validate"></div>
                    </div>
                </form>
                <%--                <!--                <p class="error-validation"-->--%>
                <%--                <!--                   th:each="error : ${#fields.errors('global')}"-->--%>
                <%--                <!--                   th:if="${#fields.hasGlobalErrors()}"-->--%>
                <%--                <!--                   th:text="${error}">Validation error</p>-->--%>

                <%--                <!--                <div class="form-group col-md-5">-->--%>
                <%--                <!--                    <input class="pretty-button d-block w-100" disabled="disabled" type="submit" value="SIGN UP">-->--%>
                <%--                <!--                </div>-->--%>
                <%--                <!--                <div class="form-group col-md-12">-->--%>
                <%--                <!--                    <span>Already signed up? <a style="color:#34ce57" th:href="@{/login}">LOGIN</a></span>-->--%>
                <%--                <!--                </div>-->--%>

                <%--                <!--                <div class="alert alert-info col-md-12 mb-3" role="alert" th:if="${logout}">You've been logged-->--%>
                <%--                <!--                    out-->--%>
                <%--                <!--                    successfully.-->--%>
                <%--                <!--                </div>-->--%>

                <%--                <!--                <div class="col-md-12 mb-3" style="background: #eeeeee">-->--%>
                <%--                <!--                    <div class="loading" style="background: #eeeeee">Loading</div>-->--%>
                <%--                <!--                    <div class="error-message"></div>-->--%>
                <%--                <!--                    <div class="sent-message">filler</div>-->--%>
                <%--                <!--                </div>-->--%>
            </div>
        </div>
        <div class="modal-footer d-flex justify-content-center">
            <button class="pretty-button" onclick="saveCreatedArticle()" style="width: 40%">
                <fmt:message key="user.save"/>
            </button>
        </div>
    </div>
</div>