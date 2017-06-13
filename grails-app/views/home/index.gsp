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
        <li><g:link class="reminded" controller="user" name="reminder" action="reminder" id="${userInstance.id}"><g:message code="default.home.reminder"/></g:link></li>
        <li><g:link class="sugest" controller="user" name="sugest" action="sugest" id="${userInstance.id}"><g:message code="default.home.sugest"/></g:link></li>
    </ul>
</div>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div>

    <h4>Olá, ${userInstance.firstName}</h4>
    <br>
    <br> Escolha uma das opções:
<g:link controller="vaga" action="index"> Lista de Vagas</g:link>
</div>
</body>
</html>