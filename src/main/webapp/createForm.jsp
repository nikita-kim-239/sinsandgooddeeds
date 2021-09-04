<%-- 
    Document   : sinForm
    Created on : 04.09.2021, 13:06:58
    Author     : Никита
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый поступок</title>
    </head>
    <body>
        <form method="POST" action="create">
        <input type="date" name="date">Дата</input>
        <br/>
        <select name="sin">
            <option value="true">Грех</option>
            <option value="false">Доброе дело</option>            
        </select>
        <br/>
        <input type="text" name="description">Описание</input>
        <br/>
        <button type="submit">Создать</button>
    </form>
    </body>
</html>
