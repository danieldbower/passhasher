<%@ page import="com.bowerstudios.passhasher.Place" %>



<div class="fieldcontain ${hasErrors(bean: placeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="place.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${placeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: placeInstance, field: 'hashTimes', 'error')} required">
	<label for="hashTimes">
		<g:message code="place.hashTimes.label" default="Hash Times" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="hashTimes" min="25" required="" value="${fieldValue(bean: placeInstance, field: 'hashTimes')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: placeInstance, field: 'encodingChars', 'error')} required">
	<label for="encodingChars">
		<g:message code="place.encodingChars.label" default="Encoding Chars" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="encodingChars" required="" value="${placeInstance?.encodingChars}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: placeInstance, field: 'passLength', 'error')} required">
	<label for="passLength">
		<g:message code="place.passLength.label" default="Pass Length" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="passLength" min="4" required="" value="${fieldValue(bean: placeInstance, field: 'passLength')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: placeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="place.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${placeInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: placeInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="place.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.bowerstudios.passhasher.User.list()}" optionKey="id" required="" value="${placeInstance?.user?.id}" class="many-to-one"/>
</div>

