
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n/messages"/>

<html>
<jsp:include page="/view/fragments/head.jsp">
    <jsp:param name="title" value="Login • Dreamfit"/>
</jsp:include>
<body>
<jsp:include page="/view/fragments/navbar.jsp"/>

<main id="main" >
    <div class="site-section pb-0" id="bg" style="height: 100%">
        <div class="container" id="cont" style=" height: 100%">
            <div class="d-inline-block" style="height: 100%; width: 100%">
                <div class="d-inline-block" data-aos="fade-zoom-in" data-aos-offset="0"
                     style="height: 100%; width: 100%">
                    <div id="modal-window" style="display: none"></div>
                        <div class="maincontent d-flex" style="height: 100%">
                            <div class="d-inline-flex" style="width: 100%">
                                <div class="d-flex flex-column tabs mx-2 flex-grow-1" style="width: 20%">
                                    <button class="pretty-button menu-pr-button bg  my-2"
                                            onclick="setContentContainerTo('food-diary')"><fmt:message key="diary"/>
                                    </button>
                                    <button class="pretty-button  menu-pr-button bg my-2"
                                            onclick="setContentContainerTo('profile')"><fmt:message key="profile"/>
                                    </button>
                                </div>
                                <div class="maincontent  d-flex flex-grow-1  m-2" id="content-container" style="border-radius: 5px"></div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/view/fragments/footer.jsp"/>
<div>
    <!-- Vendor JS Files -->
    <script src="static/vendor/jquery/jquery.min.js"></script>
    <script src="static/vendor/jquery/jquery-migrate.min.js"></script>
    <script src="static/vendor/jquery/jquery-form-plugin.js"></script>
    <script src="static/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="static/vendor/easing/easing.min.js"></script>
    <script src="static/vendor/isotope/isotope.pkgd.min.js"></script>
    <script src="static/vendor/aos/aos.js"></script>
    <script src="static/vendor/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Main JS File -->
    <script src="static/js/main.js"></script>
    <script src="static/js/login-form-validation.js"></script>

    <script id="change-lang-param" type="text/javascript">

        $(document).ready(function () {
            AOS.init({
                easing: 'ease',
                duration: 1000,
            });

            $(".locale").click(function (event) {
                var selectedOption = event.target.id;
                if (selectedOption != '') {
                    location.replace('?lang=' + selectedOption);
                }
            });

            $('.slink-container').hover(function () {
                $(this).css("text-decoration", "underline");
            }, function () {
                // on mouseout, reset the background colour
                $(this).css("text-decoration", "none");
            });

            $('.form-control').keyup(function () {
                if (allFilled()) {
                    $('.pretty-button').removeAttr('disabled')
                } else {
                    $('.pretty-button').prop("disabled", true);
                }
            });

            function allFilled() {
                var filled = true;
                $('.form-control').each(function () {
                    if ($(this).val() == '') filled = false;
                });
                return filled;
            }

            $('.submit-button').hover(function () {
                if (allFilled()) {
                    $('.submit-button').removeAttr('disabled')
                } else {
                    $('.submit-button').prop("disabled", true);
                }
            });
        });
    </script>
</div>
</body>
</html>