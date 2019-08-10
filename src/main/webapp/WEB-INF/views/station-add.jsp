<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage>

    <form:form method="POST" modelAttribute="station" action="save">
<ti:input field="name" title="Name"/>
<ti:input field="timezone" title="Timezone"/>
<%--        <t:dropdownname listCategory="${listLines}" name="lineId.id" title="Line#"/>
        <form:errors path="lineId" cssClass="error"/>--%>
<%--<ti:spdd list="${listLines}" title="dfdf"/>--%>

<%--        <form:select path="lineId.id">
            <form:option value="" label="--Select phone"/>
            <form:options items="${listLines}"  itemValue="id" itemLabel="name"  />
        </form:select>
        <form:errors path="lineId.id" cssClass="error"/>--%>
<ti:spdd field="lineId.id" list="${listLines}" title="Select Line#"/>
<ti:btnsubmit title="Save"/>


</form:form>
</t:genericpage>