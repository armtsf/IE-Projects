<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>
    </head>
    <body>
        <c:if test="${not empty msg}">
                <h1><c:out value="${msg}"/></h1>
        </c:if>
        <ul>
            <li><c:out value="id: ${user.id}"/></li>
            <li><c:out value="first name: ${user.firstName}"/></li>
            <li><c:out value="last name: ${user.lastName}"/></li>
            <li><c:out value="jobTitle: ${user.jobTitle}"/></li>
            <li><c:out value="bio: ${user.bio}"/></li>
            <li><c:out value="skills: "/>
                <ul>
                    <c:forEach var="skill" items="${user.skills}">
                        <li><c:out value="${skill.skillName.name}: '${skill.endorsementCount()}'"/>
                            <form action="delete" method="POST">
                                <input type="hidden" name="skill" value="${skill.skillName.name}"/>
                                <button>Delete</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
         <form action="add" method="POST">
            <select name="skill">
                <c:forEach var="skillName" items="${skills}">
                    <option value="${skillName.name}"><c:out value="${skillName.name}"/></option>
                </c:forEach>
            </select>
            <button>Add</button>
        </form>
    </body>
</html>