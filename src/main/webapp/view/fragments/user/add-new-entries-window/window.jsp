<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="modal" id="myModal" data-aos="fade-zoom-in" data-aos-offset="">
    <div class="modal-content">
        <div class="modal-header d-inline-flex">
            <p class="p-1 mt-2 mb-2" style="size:4em"><fmt:message key="user.meals-to-add"/></p>
            <input class="form-control col-5 ml-4 align-self-center" id="search" onclick="assignSearchListener()"
                   placeholder="search..."/>
            <span class="close mt-0" onclick="closeAddFoodModalWindow()">&times;</span>
        </div>
        <div class="modal-body container" id="modalbody">
            <div class="m-2 tabs" id="addfoodcontainer" style="display: block">
                <jsp:include page="/view/fragments/user/add-new-entries-window/search-results.jsp"/>
            </div>
            <div class="mt-4" id="createfoodcontainer" style="display: none">
                <div id="foodSavingErrorBox" class="alert-danger alert col-md-11 mb-3 display-none" role="alert">
                    <fmt:message key="food.not-saved"/>
                </div>
                <div id="foodSavedSuccessBox" class="alert-success alert col-md-11 mb-3 display-none" role="alert">
                    <fmt:message key="food.saved"/>
                </div>
                <form class="form" id="createfoodform" method="post" style="width: 100%" action="save-new-food">
                    <input name="profileId" value="${user.userId}" type="hidden">
                    <div class="row">
                        <div class="col-3">
                            <div class="form-group col-md-11">
                                <label class="control-label"><fmt:message key="user.food-name"/></label>
                                <input class="form-control" name="newFoodName"/>
                                <p id="newFoodNameError" class="errorServerValidation"></p>
                            </div>
                            <div class="form-group col-md-11">
                                <label class="control-label"><fmt:message key="user.calories"/></label>
                                <input class="form-control" name="newFoodCalories"/>
                                <p id="newFoodCaloriesError" class="errorServerValidation"></p>
                            </div>
                            <div class="form-group col-md-11">
                                <label class="control-label"><fmt:message key="user.proteins"/></label>
                                <input class="form-control" name="newFoodProt"/>
                                <p id="newFoodProtError" class="errorServerValidation"></p>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="form-group col-md-11">
                                <label class="control-label"><fmt:message key="user.fats"/></label>
                                <input class="form-control" name="newFoodFats"/>
                                <p id="newFoodFatsError" class="errorServerValidation"></p>
                            </div>
                            <div class="form-group col-md-11">
                                <label class="control-label"><fmt:message key="user.carbs"/></label>
                                <input class="form-control" name="newFoodCarbohydrates"/>
                                <p id="newFoodCarbohydratesError" class="errorServerValidation"></p>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="m-2 d-flex flex-column col-5 tabs">
                    <button class="modal-save-food pretty-button mt-2" onclick="saveCreatedFood()">
                        <fmt:message key="user.save"/>
                    </button>
                    <button class="modal-back pretty-button mt-2" onclick="setModalContainerTo('addfoodcontainer')">
                        <fmt:message key="user.back"/>
                    </button>
                </div>
            </div>
        </div>
        <div class="modal-footer d-flex justify-content-center">
            <button class="modal-save pretty-button" onclick="saveNewEntries()">
                <fmt:message key="user.save"/>
            </button>
        </div>
    </div>
</div>