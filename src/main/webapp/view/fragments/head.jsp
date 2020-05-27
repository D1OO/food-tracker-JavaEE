<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="utf-8">
    <title>${param.title}</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Food Tracker Java EE JavaEE Servlet" name="keywords">
    <meta content="Dmitriy Storozhenko's Food Tracker on pure JavaEE" name="description">

    <!-- Favicons -->
    <link href="${pageContext.request.contextPath}/static/img/shvdyprojecticon.png" rel="icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Press+Start+2P:400|Raleway:400,700&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Rubik&display=swap" rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <!-- Vendor CSS Files -->
    <!--    <link href="vendor/icofont/icofont.min.css" rel="stylesheet">-->
    <!--    <link href="vendor/line-awesome/css/line-awesome.min.css" rel="stylesheet">-->
    <link href="${pageContext.request.contextPath}/static/vendor/aos/aos.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/vendor/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://unpkg.com/flickity@2/dist/flickity.min.css">

    <!-- Template Main CSS File -->
    <style><%@include file="/static/css/style.css" %></style>
    <style><%@include file="/static/css/food-modal.css" %></style>

    <!-- =======================================================
      Template Name: MyPortfolio
      Template URL: https://bootstrapmade.com/myportfolio-bootstrap-portfolio-website-template/
      Author: BootstrapMade.com
      Author URL: https://bootstrapmade.com/
    ======================================================= -->
</head>