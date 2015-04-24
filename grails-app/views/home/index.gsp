<!doctype html>
<html>
<head>
<meta name="layout" content="main" />
</head>
<body>
	<div id="app">
		<h1>Generate your passwords with a Hash</h1>
	
		<div class="masterPassword">
			<label id="masterLabel" for="master">Master Password</label> 
			<input type="password" id="master" title="Enter your Master Password">
			<label id="password-strength" for="master"></label>
		</div>
	
		<div id="hashedPassContainer">
			<button title="reset form" onclick="resetHasher('blank')"
				class="reset">reset</button>
			<span id="hashedPassLabel">Hashed Pass:</span> <span id="hashedPass">blank</span>
		</div>
	
		<g:if test="${userInstance}">
			<g:render template="/shared/placeList"/>
		</g:if>
	
		<ul>
			<li><g:link action="createPlace">Create New Place</g:link></li>
			<li><g:link action="about">About</g:link></li>
			<li><g:link action="todo">Features and Todo</g:link></li>
		</ul>
	</div>
</body>
</html>
