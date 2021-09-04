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
        <title>Новый грех</title>
    </head>
    <body>
        <form method="POST" action="sinForm">
        <input type="date" name="date">Дата</input>
        <br/>
        <input type="text" name="description">Описание</input>
        <br/>
        <button type="submit">Создать</button>
    </form>
    </body>
</html>
