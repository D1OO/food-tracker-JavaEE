<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>
<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Food Diary"/>
</jsp:include>
<body>
<%@ include file="/view/fragments/navbar.jsp" %>
<h1>Home</h1>
</body>
</html>
