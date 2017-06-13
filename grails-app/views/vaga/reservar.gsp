
<%@ page import="sistemadevagasdeestacionamento.Reserva" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'reserva.label', default: 'Reserva')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-reserva" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
	<ul>
		<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
	</ul>
</div>
<div id="list-reserva" class="content scaffold-list" role="main">
	<h1><g:message code="default.list.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<table>
		<thead>
		<tr>

			<th><g:message code="reserva.usuario.label" default="Usuario" /></th>

			<th><g:message code="reserva.vaga.label" default="Vaga" /></th>

			<g:sortableColumn property="entrada" title="${message(code: 'reserva.entrada.label', default: 'Entrada')}" />

			<g:sortableColumn property="saida" title="${message(code: 'reserva.saida.label', default: 'Saida')}" />

		</tr>
		</thead>
		<tbody>
		<g:each in="${reservaInstanceList}" status="i" var="reservaInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

				<td><g:link action="show" id="${reservaInstance.id}">${fieldValue(bean: reservaInstance, field: "usuario")}</g:link></td>

				<td>${fieldValue(bean: reservaInstance, field: "vaga")}</td>

				<td><g:formatDate date="${reservaInstance.entrada}" /></td>

				<td><g:formatDate date="${reservaInstance.saida}" /></td>

			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${reservaInstanceCount ?: 0}" />
	</div>
</div>
</body>
</html>