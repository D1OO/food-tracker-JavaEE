<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<div class="footer mt-4 container">
    <div class="container  d-inline-flex  flex-grow-1">
        <div class="row-cols-sm-1">
            <div class="d-flex"><p>Developed and designed by </p>
                <p style="color: black; margin-left: 0.5em"> shvdy &copy;</p></div>
            <p>All Rights Reserved</p>
        </div>
        <div class="d-inline-flex justify-content-end flex-grow-1">
            <div CLASS="slink-container d-inline-flex align-items-center">
                <a href="https://github.com/D1OO/Spring-Boot-Project"><i class="sicon" id="ghicon"></i></a>
                <a class="slink align-self-center" href="https://github.com/D1OO/Spring-Boot-Project">github</a>
            </div>
            <div CLASS="slink-container d-inline-flex align-items-center">
                <a href="https://t.me/liveloveasap"><i class="sicon" id="tgicon"></i></a>
                <a class="slink align-self-center" href="https://t.me/liveloveasap">telegram</a>
            </div>
            <div CLASS="slink-container d-inline-flex align-items-center">
                <i class="sicon" id="mailicon"></i>
                <p class="slink align-self-center">100dmytro@gmail.com</p>
            </div>
            <div class="ml-5 float-right">
                <div class="locale">
                    <a class="pretty-button bg mx-2" href="javascript:void(0)" id="ru">ru</a>
                    <a class="pretty-button bg " href="javascript:void(0)" id="en">en</a>
                </div>
            </div>
        </div>
    </div>
</div>
