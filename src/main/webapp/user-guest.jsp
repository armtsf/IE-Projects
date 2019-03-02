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
            <li><c:out value="skills: "/>
                <ul>
                    <c:forEach var="skill" items="${user.skills}">
                        <li><c:out value="${skill.skillName.name}: '${skill.endorsementCount()}'"/>
                            <form action="endorse" method="POST">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input type="hidden" name="skill" value="${skill.skillName.name}"/>
                                <button>Endorse</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
    </body>
</html>