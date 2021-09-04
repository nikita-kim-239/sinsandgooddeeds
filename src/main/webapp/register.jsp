

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Регистрация</title>
        <link rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    </head>
    <body>
    <form method="POST" action="register">
        <input type="text" name="name">Имя</input>
        <br/>
        <input type="text" name="login">Логин</input>
        <br/>
        <input type="text" name="password">Пароль</input>
        <br/>
        <button type="submit">Зарегистрироваться</button>
    </form>
    </body>
</html>
