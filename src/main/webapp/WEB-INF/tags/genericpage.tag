<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="inner_title" %>
<%@attribute name="error" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.12.4.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/ui/1.12.1/jquery-ui.js" />"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<body>
<div id="col-md-offset-1 col-md-3">
    <t:menu/>
    <jsp:invoke fragment="header"/>
</div>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                ${inner_title}
            </div>
        </div>
        <div class="panel-body">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error:</span>
                        ${error}
                </div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error:</span>
                        ${success}
                </div>
            </c:if>
            <jsp:doBody/>
        </div>
    </div>
</div>

<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>