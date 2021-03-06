<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Users</title>
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
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>jobTitle</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.firstName} ${user.lastName}"/></td>
                    <td><c:out value="${user.jobTitle}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>