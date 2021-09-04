<%-- 
    Document   : userPage
    Created on : 04.09.2021, 11:33:25
    Author     : Никита
--%>

<%@ page language="java" contentType="text/html; charset=utf-8" 
 pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<!DOCTYPE html>
<html>
    <head>
        
        <title>Страница пользователя</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        
        <a href="sinForm">Новый грех</a>
        <br/>
        <a href="logout">Выйти</a>
        <br/>
        <table border="1" cellpadding="5">
            <caption><h2>Грехи</h2></caption>
            <tr>
                <th>Дата</th>         
                <th>Описание</th>
            </tr>
            <c:forEach var="sin" items="${sins}">
            <jsp:useBean id="sin" class="nikita.kim.model.Sin" scope="request"/>
                <tr>

                    <td><c:out value="${sin.date}"/></td>       
                    <td><c:out value="${sin.description}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
