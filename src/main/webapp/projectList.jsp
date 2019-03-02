<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Projects</title>
        <style>
            table {
                text-align: center;
                margin: 0 auto;
            }
            td {
                min-width: 300px;
                margin: 5px 5px 5px 5px;
                text-align: center;
                }
        </style>
    </head>
    <body>
        <c:if test="${not empty msg}">
            <h1><c:out value="${msg}"/></h1>
        </c:if>
        <table>
            <tr>
                <th>id</th>
                <th>title</th>
                <th>budget</th>
            </tr>
            <c:forEach var="project" items="${projectList}">
                <tr>
                    <td><c:out value="${project.id}"/></td>
                    <td><c:out value="${project.title}"/></td>
                    <td><c:out value="${project.budget}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

