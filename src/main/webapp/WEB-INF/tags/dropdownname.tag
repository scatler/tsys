<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Drop-down menu" pageEncoding="UTF-8" %>
<%@attribute name="listCategory" required="true" type="java.util.ArrayList" %>
<%@attribute name="title" %>
<%@attribute name="name" required="true" %>

<div class="form-group">

    <label for="select" class="col-md-3 control-label">${title}</label>
    <div class="col-md-9">
        <select class="form-control" name="${name}" id="select">
            <option value="">Select line</option>
            <c:forEach items="${listCategory.iterator()}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
    </div>

</div>
