<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage>
    <div class="form-group form-horizontal">
        <form action='<spring:url value="/loginAction"/>' method="post">
            <table>
                <tr>
                    <td>Username</td>
                    <td><input class="form-control" type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input  class="form-control" type="password" name="password"></td>
                </tr>
                <tr>
                    <td>
                        <button class="btn btn-primary" type="submit">Login</button>
                    </td>
                </tr>

            </table>
        </form>
    </div>

    <a class="btn btn-primary" href="<c:url value="/registration"/>" role="button">Register</a>
</t:genericpage>
