<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@attribute name="title" required="true" %>
<div class="form-group">
    <div class="col-sm-4">
        <form:button cssClass="btn btn-primary">${title}</form:button>
    </div>
</div>