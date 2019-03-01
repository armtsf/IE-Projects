<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>
    </head>
    <body>
        <ul>
            <li><c:out value="id: ${user.id}"/></li>
            <li><c:out value="first name: ${user.firstName}"/></li>
            <li><c:out value="last name: ${user.lastName}"/></li>
            <li><c:out value="jobTitle: ${user.jobTitle}"/></li>
            <li><c:out value="bio: ${user.bio}"/></li>
        </ul>
    </body>
</html>