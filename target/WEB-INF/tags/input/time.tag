<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@tag description="Drop-down menu" %>
<%@attribute name="field" required="true" %>
<%@attribute name="title" %>
<%@attribute name="idtime" %>
<script src="https://unpkg.com/imask"></script>
<c:set var="bindError"><form:errors path="${field}"/></c:set>
<div class="input-group bootstrap-timepicker timepicker">
    <form:input id="${idtime}" path="${field}" cssClass="form-control timepicker" placeholder= "00:00:00"/>
    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
</div>
<c:if test="${not empty bindError}">
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        <form:errors path="${field}"/>
    </div>
</c:if>
<script>
    var element = document.getElementById('${idtime}');
    var maskOptions = {
        mask: '00:00:00'
    };
    var mask = IMask(element, maskOptions);
</script>
