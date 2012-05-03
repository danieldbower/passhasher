
<%@ page import="com.bowerstudios.passhasher.Place" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'place.label', default: 'Place')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-place" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-place" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list place">
			
				<g:if test="${placeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="place.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${placeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${placeInstance?.hashTimes}">
				<li class="fieldcontain">
					<span id="hashTimes-label" class="property-label"><g:message code="place.hashTimes.label" default="Hash Times" /></span>
					
						<span class="property-value" aria-labelledby="hashTimes-label"><g:fieldValue bean="${placeInstance}" field="hashTimes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${placeInstance?.encodingChars}">
				<li class="fieldcontain">
					<span id="encodingChars-label" class="property-label"><g:message code="place.encodingChars.label" default="Encoding Chars" /></span>
					
						<span class="property-value" aria-labelledby="encodingChars-label"><g:fieldValue bean="${placeInstance}" field="encodingChars"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${placeInstance?.passLength}">
				<li class="fieldcontain">
					<span id="passLength-label" class="property-label"><g:message code="place.passLength.label" default="Pass Length" /></span>
					
						<span class="property-value" aria-labelledby="passLength-label"><g:fieldValue bean="${placeInstance}" field="passLength"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${placeInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="place.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${placeInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${placeInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="place.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${placeInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${placeInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="place.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${placeInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${placeInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="place.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${placeInstance?.user?.id}">${placeInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${placeInstance?.id}" />
					<g:link class="edit" action="edit" id="${placeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
