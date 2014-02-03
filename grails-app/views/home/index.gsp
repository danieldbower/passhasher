<!doctype html>
<html>
<head>
<meta name="layout" content="main" />
</head>
<body>
	<h1>Generate your passwords with a Hash</h1>

	<div class="masterPassword">
		<label id="masterLabel" for="master">Master Password</label> <input
			type="password" id="master" title="Enter your Master Password">
		<label id="password-strength" for="master"></label>
	</div>

	<g:if test="${userInstance}">
		<g:render template="/shared/placeList"/>

		<g:form name="newPlaceForm">
			<h2>Create New</h2>
			<g:render template="/shared/placeFormFields" />
			<g:submitToRemote value="Create Place" 
				onSuccess="addNewPlaceToList(data)" onFailure="addNewPlaceFailure(data)"
				url="[controller: 'place', action: 'create']"/>
			<button onclick="hashit($('#nameInput').val(), $('#hashTimesInput').val(), $('#encodingInput').val(), $('#passLengthInput').val())">hash it</button>
		</g:form >
	</g:if>

	<g:else>
		<p class="anonymous">Either sign in (at the top right) to access
			your saved list of places,
		<div class="OR">or</div>
		<g:render template="/shared/placeFormFields"/>
	</g:else>
	
	<div class="buttons">
		<g:if test="${!userInstance}">
			<button onclick="hashit($('#nameInput').val(), $('#hashTimesInput').val(), $('#encodingInput').val(), $('#passLengthInput').val())">hash it</button>
		</g:if>
		<button title="reset form" onclick="resetHasher('blank')"
			class="reset">reset</button>
		<button title="hide advanced" onclick="hideAdvanced()"
			class="makeAdvancedToggle advanced">simple</button>
		<button title="show advanced" onclick="showAdvanced()"
			class="makeAdvancedToggle expandAdvanced">advanced</button>
	</div>


	<div id="hashedPassContainer">
		<span id="hashedPassLabel">Hashed Pass:</span> <span id="hashedPass">blank</span>
	</div>
</body>
</html>
