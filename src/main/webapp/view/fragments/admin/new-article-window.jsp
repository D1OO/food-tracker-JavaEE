<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="modal" id="myModal" data-aos="fade-zoom-in" style="z-index: 5;">
    <div class="modal-content">
        <div class="modal-header">
            <p class="p-1 mt-2 mb-2" style="size:4em"><fmt:message key="article.create-article"/></p>
            <span class="close mt-0" onclick="closeAddFoodModalWindow()">&times;</span>
        </div>
        <div class="modal-body container" id="modalbody">
            <div class="mt-4">
                <div id="articleSavingErrorBox" class="alert-danger alert col-md-11 mb-3 display-none" role="alert">
                    <fmt:message key="article.not-saved"/>
                </div>
                <form class="form" id="createarticleform" enctype="multipart/form-data" style="width: 100%">
                    <input name="authorId" value="${user.userId}" type="hidden">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group col-md-11">
                                <label class="control-label"><fmt:message key="article.titleEN"/></label>
                                <input class="form-control" name="titleEN"/>
                                <p id="titleENError" class="errorServerValidation"></p>
                            </div>
                            <div class="form-group col-md-11">
                                <label class="control-label"><fmt:message key="article.titleRU"/></label>
                                <input class="form-control" name="titleRU"/>
                                <p id="titleRUError" class="errorServerValidation"></p>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group col-md-11 col">
                                <label class="control-label"><fmt:message
                                        key="article.image"/></label>
                                <input class="form-control-file"
                                       type="file"
                                       name="image"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label"><fmt:message key="article.textEN"/></label>
                        <textarea class="form-control"
                                  type="text" style="width: 740px; height: 380px" name="textEN"></textarea>
                        <p id="textENError" class="errorServerValidation"></p>
                    </div>
                    <div class="form-group col-md-11">
                        <label class="control-label"><fmt:message key="article.textRU"/></label>
                        <textarea class="form-control"
                                  type="text" style="width: 740px; height: 380px" name="textRU"></textarea>
                        <p id="textRUError" class="errorServerValidation"></p>
                    </div>
                </form>
            </div>
        </div>
        <div class="modal-footer d-flex justify-content-center">
            <button class="pretty-button" onclick="saveCreatedArticle()" style="width: 40%">
                <fmt:message key="user.save"/>
            </button>
        </div>
    </div>
</div>