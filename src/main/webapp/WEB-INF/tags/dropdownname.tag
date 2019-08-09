<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Drop-down menu" pageEncoding="UTF-8" %>
<%@attribute name="listCategory" required="true" type="java.util.ArrayList" %>
<%@attribute name="title" %>
<%@attribute name="name" required="true"  %>

<div>
    <label for="station_id" class="col-lg-2 control-label">${title}</label>
    <select class="form-control" name="${name}" id="station_id">
        <c:forEach items="${listCategory.iterator()}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
</div>
