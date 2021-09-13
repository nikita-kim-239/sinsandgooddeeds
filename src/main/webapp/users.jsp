<%@ page language="java" contentType="text/html; charset=utf-8" 
 pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        
        <table border="1" cellpadding="5">
            <caption><h2>Пользователи</h2></caption>
            <tr>
                <th>Id</th>
                <th>Имя</th>
            </tr>
            <c:forEach var="user" items="${users}">
            <jsp:useBean id="user" class="nikita.kim.model.User" scope="request"/>
                <tr>

                    <td><c:out value="${user.id}"/></td>       
                    <td><a href="usersins?targetuser=${user.id}"><c:out value="${user.name}"/></a></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
