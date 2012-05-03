<%@ page import="com.bowerstudios.passhasher.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'places', 'error')} ">
	<label for="places">
		<g:message code="user.places.label" default="Places" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.places?}" var="p">
    <li><g:link controller="place" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="place" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'place.label', default: 'Place')])}</g:link>
</li>
</ul>

</div>

