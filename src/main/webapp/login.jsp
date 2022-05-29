<%-- 
    Document   : login
    Created on : 29/05/2022, 2:33:41 a. m.
    Author     : estev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <form action="login" method="post">
                Cedula
                <input type="text" name="cedula">
                <br/>
                Clave
                <input type="password" name="clave">
                <br/>
                Soy
                <br/>
                <input type="Radio" name="tipo" value= "emprendedor"checked>Emprendedor
                <br/>
                <input type="Radio" name= "tipo"value="donante">Donanate
                <br/>
                <p><input type="submit" value="Enviar"></p>
            </form>
        </body>
    </html>
</f:view>
