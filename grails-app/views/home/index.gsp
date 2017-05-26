<%@ page import="sistemadevagasdeestacionamento.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Home</title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" id = "logout" href="/SistemaDeVagasDeEstacionamento/">Logout</a></li>

                <li><a class="reminder" controller="user" action="reminded" id="${userInstance.id}">Reminded</a></li>
            </ul>
        </div>

        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>

        <div>

            <h4>Olá, ${userInstance.firstName}</h4>
            <br>
            <br> Escolha uma das opções:
        <g:link controller="vaga" action="index">Fazer login</g:link>
        </div>
    </body>
</html>
