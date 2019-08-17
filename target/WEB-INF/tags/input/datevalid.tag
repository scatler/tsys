<%@tag description="Date picker" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@attribute name="field" required="true" %>
<%@attribute name="title" required="true" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>

<div class="form-group">
    <label for="datepicker" class="col-sm-4 control-label">${title}</label>
      <div class="col-sm-4">
            <form:input class="form-control" path = "${field}" type="text" id="datepicker" readonly="true"/>
            <form:errors path="${field}" cssClass="error" />
      </div>
</div>