
<%@ page import="sistemadevagasdeestacionamento.Vaga" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'vaga.label', default: 'Vaga')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-vaga" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
	<ul>
		<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
	</ul>
</div>
<div id="show-vaga" class="content scaffold-show" role="main">
	<h1><g:message code="default.show.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<ol class="property-list vaga">

		<g:if test="${vagaInstance?.numero}">
			<li class="fieldcontain">
				<span id="numero-label" class="property-label"><g:message code="vaga.numero.label" default="Numero" /></span>

				<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${vagaInstance}" field="numero"/></span>

			</li>
		</g:if>

		<g:if test="${vagaInstance?.setor}">
			<li class="fieldcontain">
				<span id="setor-label" class="property-label"><g:message code="vaga.setor.label" default="Setor" /></span>

				<span class="property-value" aria-labelledby="setor-label"><g:fieldValue bean="${vagaInstance}" field="setor"/></span>

			</li>
		</g:if>

		<g:if test="${vagaInstance?.preferenceType}">
			<li class="fieldcontain">
				<span id="preferenceType-label" class="property-label"><g:message code="vaga.preferenceType.label" default="Preference Type" /></span>

				<span class="property-value" aria-labelledby="preferenceType-label"><g:fieldValue bean="${vagaInstance}" field="preferenceType"/></span>

			</li>
		</g:if>

		<g:if test="${vagaInstance?.ocupada}">
			<li class="fieldcontain">
				<span id="ocupada-label" class="property-label"><g:message code="vaga.ocupada.label" default="Ocupada" /></span>

				<span class="property-value" aria-labelledby="ocupada-label"><g:formatBoolean boolean="${vagaInstance?.ocupada}" /></span>

			</li>
		</g:if>
		<g:if test="${vagaInstance?.maintenance}">
			<li class="fieldcontain">
				<span id="maintenance-label" class="property-label"><g:message code="vaga.maintenance.label" default="Maintenance" /></span>

				<span class="property-value" aria-labelledby="maintenance-label"><g:formatBoolean boolean="${vagaInstance?.maintenance}" /></span>

			</li>

			<td><g:link action="terminarManutencao" id="${vagaInstance.id}"> Desinterditar </g:link> </td>

		</g:if>

		<td><g:link action="manutencao" id="${vagaInstance.id}"> Interditar </g:link> </td>


		<g:if test="${vagaInstance?.reservas}">
			<li class="fieldcontain">
				<span id="reservas-label" class="property-label"><g:message code="vaga.reservas.label" default="Reservas" /></span>

				<g:each in="${vagaInstance.reservas}" var="r">
					<span class="property-value" aria-labelledby="reservas-label"><g:link controller="reserva" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
				</g:each>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource:vagaInstance, action:'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="edit" action="edit" resource="${vagaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
			<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
		</fieldset>
	</g:form>
</div>
</body>
</html>