<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="List of users">
    <input type="button" value="Add User"
           onclick="window.location.href='add'; return false;"
           class="btn btn-primary" />

    <table class="table table-striped table-bordered">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>

        <!-- loop over and print our user -->
        <c:forEach var="user" items="${users}">

            <!-- construct an "update" link with user id -->
            <c:url var="updateLink" value="/user/update">
                <c:param name="userId" value="${user.id}" />
            </c:url>

            <!-- construct an "delete" link with user id -->
            <c:url var="deleteLink" value="/user/delete">
                <c:param name="userId" value="${user.id}" />
            </c:url>

            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>

                <td>
                    <!-- display the update link --> <a href="${updateLink}">Update</a>
                    | <a href="${deleteLink}"
                         onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
                </td>

            </tr>

        </c:forEach>

    </table>
</t:genericpage>