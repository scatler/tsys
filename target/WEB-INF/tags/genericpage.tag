<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="inner_title" fragment="true" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.12.4.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/ui/1.12.1/jquery-ui.js" />"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<div id="col-md-offset-1 col-md-10">
   <t:menu/>

    <jsp:invoke fragment="header"/>

</div>
<div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">
                    <jsp:invoke fragment="inner_title"/>
                </div>
            </div>
            <div class="panel-body">
                <jsp:doBody/>
            </div>
        </div>
</div>

<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>