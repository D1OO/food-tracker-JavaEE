<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dreamfit • Processing logout...</title>
</head>
<body>
<script>
    window.history.pushState(null, "Dreamfit", 'logout');
    window.location = 'login?logout'
</script>
</body>
</html>
