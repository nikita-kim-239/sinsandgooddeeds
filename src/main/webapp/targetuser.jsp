<%@ page language="java" contentType="text/html; charset=utf-8" 
 pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Голосовать</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        
        <table class="center" border="1" cellpadding="5">
            <caption><h2>Дела пользователя ${targetuser}</h2></caption>
            <tr>
                <th>Дата</th>
                <th>Описание</th>
            </tr>
            <c:forEach var="act" items="${acts}">
            <jsp:useBean id="act" class="nikita.kim.model.Act" scope="request"/>
                <tr class="${act.sin ? 'hell':'heaven'}">

                    <td><c:out value="${act.date}"/></td>       
                    <td><c:out value="${act.description}"/></td>
                </tr>
            </c:forEach>
        </table>
        
        <form method="POST" action="usersins">        
        <select name="vote">
            <option value="heaven">В рай</option>
            <option value="hell">В ад</option>            
        </select>
        
        
        <button type="submit">Проголосовать</button>
        </form>    
    </body>
</html>
