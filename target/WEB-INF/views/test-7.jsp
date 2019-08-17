<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:form action="/train/addTest" method="POST"  modelAttribute="userListWrapper">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email/login</th>
            <th>Profession</th>
            <th>Select<th>
        </tr>
        </thead>
        <tbody>
        <c:forEach varStatus="us" var="user" items="${userListWrapper.users}" >
            <tr>
                <td><form:input type="text" path="users[${us.index}].firstName"/>${user.firstName}</td>
                <td><form:input type="text" path="users[${us.index}].lastName"/> ${user.lastName}</td>
                <td><form:input type="hidden" path="users[${us.index}].login"/>${user.login}</td>
                <%--<td><form:checkbox path="users[${us.index}].delete" value="${user.delete}"/></td>--%>
                <form:input type="hidden" path="users[${us.index}].id"/>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Delete user(s)" class="btn-danger" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form:form>