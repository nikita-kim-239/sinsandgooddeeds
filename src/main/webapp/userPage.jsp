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
        
        <p class="center">${votesToHeaven} человек проголосовало,за то что вы попадете в рай</p>
        <br/>
        <p class="center">${votesToHell} человек проголосовало,за то что вы попадете в ад</p>
        <br/>
        <a class="center" href="create">Создать</a>
        <br/>
        <a class="center" href="logout">Выйти</a>
        <br/>
        <table class="center" border="1" cellpadding="5">
            <caption><h2>Грехи</h2></caption>
            <tr>
                <th>Дата</th>
                <th>Описание</th>
            </tr>
            <c:forEach var="act" items="${acts}">
            <jsp:useBean id="act" class="nikita.kim.model.Act" scope="request"/>
                <tr class="${act.sin ? 'hell':'heaven'}">

                    <td><c:out value="${act.acted}"/></td>       
                    <td><c:out value="${act.description}"/></td>
                </tr>
            </c:forEach>
        </table>
        <a class="center" href="users">Пользователи</a>
        
        
        
    </body>
</html>
