<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@attribute name="title" required="true" %>
<div class="form-group">

    <!-- Button -->
    <div class="col-md-offset-3 col-md-9">
        <form:button cssClass="btn btn-primary">${title}</form:button>
    </div>
</div>