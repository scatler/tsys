<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>

<%--<%= request.getSession().getAttribute("isAdmin") %>--%>
<t:genericpage>
    <h2>${message}</h2>
    <form action="/logout" method="post">
        <input value="Logout" type="submit">
    </form>
</t:genericpage>

