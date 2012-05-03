
<%@ page import="com.bowerstudios.passhasher.Place" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'place.label', default: 'Place')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-place" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-place" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'place.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="hashTimes" title="${message(code: 'place.hashTimes.label', default: 'Hash Times')}" />
					
						<g:sortableColumn property="encodingChars" title="${message(code: 'place.encodingChars.label', default: 'Encoding Chars')}" />
					
						<g:sortableColumn property="passLength" title="${message(code: 'place.passLength.label', default: 'Pass Length')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'place.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'place.description.label', default: 'Description')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${placeInstanceList}" status="i" var="placeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${placeInstance.id}">${fieldValue(bean: placeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: placeInstance, field: "hashTimes")}</td>
					
						<td>${fieldValue(bean: placeInstance, field: "encodingChars")}</td>
					
						<td>${fieldValue(bean: placeInstance, field: "passLength")}</td>
					
						<td><g:formatDate date="${placeInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: placeInstance, field: "description")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${placeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
