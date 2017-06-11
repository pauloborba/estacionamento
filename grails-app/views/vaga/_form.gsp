<%@ page import="sistemadevagasdeestacionamento.Vaga" %>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="vaga.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" required="" value="${vagaInstance?.numero}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'setor', 'error')} required">
	<label for="setor">
		<g:message code="vaga.setor.label" default="Setor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="setor" from="${vagaInstance.constraints.setor.inList}" required="" value="${vagaInstance?.setor}" valueMessagePrefix="vaga.setor"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'preferenceType', 'error')} required">
	<label for="preferenceType">
		<g:message code="vaga.preferenceType.label" default="Preference Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="preferenceType" from="${vagaInstance.constraints.preferenceType.inList}" required="" value="${vagaInstance?.preferenceType}" valueMessagePrefix="vaga.preferenceType"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'ocupada', 'error')} ">
	<label for="ocupada">
		<g:message code="vaga.ocupada.label" default="Ocupada" />

	</label>
	<g:checkBox name="ocupada" value="${vagaInstance?.ocupada}" />

</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'reservas', 'error')} ">
	<label for="reservas">
		<g:message code="vaga.reservas.label" default="Reservas" />

	</label>

	<ul class="one-to-many">
		<g:each in="${vagaInstance?.reservas?}" var="r">
			<li><g:link controller="reserva" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
		</g:each>
		<li class="add">
			<g:link controller="reserva" action="create" params="['vaga.id': vagaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reserva.label', default: 'Reserva')])}</g:link>
		</li>
	</ul>


</div>
