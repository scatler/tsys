<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="genForm" required="true" type="com.scatler.rrweb.dto.forms.AbstractCrudForm" %>

<c:if test="${genForm.resultsSize() > 0}">
<div class="row">
    <div >
        <h1>${genForm.userFriendlyEditTypeName}</h1>

        <%-- <a href="${genForm.createLink}" class="btn_styled">Create</a>--%>
        <table class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
                <c:forEach var="col" items="${genForm.arrayOfColumnNames}">
                    <th>${col}</th>
                </c:forEach>
                <!-- Add two more for delete and edit -->
                <c:if test="${genForm.showButtons()}">
                    <th></th>
                    <th></th>
                </c:if>
            </tr>
            </thead>
            <c:if test="${genForm.resultsSize() == 0}">
                <tr colspan="${genForm.numberOfFields}">
                    <td><spring:message code="common.crud.norecords"/></td>
                </tr>
            </c:if>
            <c:if test="${genForm.resultsSize() > 0}">
                <c:forEach var="row" begin="0" end="${genForm.resultsSize() - 1}">
                    <tr>

                        <c:forEach var="column" begin="0"
                                   end="${genForm.numberOfFields - 1}">
                            <td>${genForm.getDataAt(row, column)}</td>

                        </c:forEach>

                        <!-- Add delete and edit buttons -->
                        <c:if test="${genForm.showButtons()}">
                            <th>
                                <a id="edit-row" class="btn_styled"
                                   href="${genForm.getEditLink(row)}">Buy a ticket</a>
                            </th>
<%--                            <th>
                                <a id="delete-row" class="btn_styled"
                                   href="${genForm.deleteLink}?id=${genForm.getId(row)}"></a>
                            </th>--%>
                        </c:if>

                    </tr>
                </c:forEach>
            </c:if>

        </table>
    </div>
</div>
</c:if>
