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

            </ul>
        </div>
        <div>

            <h4>Olá, ${userInstance.firstName}</h4>
            <br>
            <br> Escolha uma das opções:
        <g:link controller="vaga" action="index"> Lista de Vagas</g:link>
        </div>
</html>
