<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@tag description="Drop-down menu" pageEncoding="UTF-8" %>
<%@attribute name="listCategory" required="true" type="java.util.ArrayList" %>
<%@attribute name="title" %>
<div class="form-group">
    <label for="station_id" class="col-lg-2 control-label">${title}</label>
    <select class="form-control" name="id" id="station_id">
        <c:forEach items="${listCategory.iterator()}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
</div>
