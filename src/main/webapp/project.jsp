<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Project</title>
    </head>
    <body>
        <ul>
            <li><c:out value="id: ${project.id}"/></li>
            <li><c:out value="title: ${project.title}"/></li>
            <li><c:out value="description: ${project.description}"/></li>
            <li><c:out value="imageUrl: "/><img src="<c:url value="${project.imageURL}"/>" style=\"width: 50px; height: 50px;\"/></li>
            <li><c:out value="budget: ${project.budget}"/></li>
        </ul>
        <form action="bid" method="POST">
            <input type="hidden" name="id" value="${project.id}"/>
            <label for="bidAmount">Bid Amount:</label>
            <input type="number" name="bidAmount">
            <button>Submit</button>
        </form>
    </body>
</html>