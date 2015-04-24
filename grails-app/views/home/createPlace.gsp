<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
</head>
<body>
	<div id="app">
		<h2>Create Place</h2>
		<div class="newPlaceForm">
			<g:hasErrors bean="${place}">
				<ul class="errors" role="alert">
					<g:eachError bean="${place}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form name="newPlaceForm" method="POST">
				<fieldset class="form">
					<div class="formField">
						<label for="nameInput">Place Name</label>
						<input type="text" name="name" id="nameInput" 
							value="${place.name}" size="36" />
					</div>
					
					<div class="formField">
						<label for="descriptionInput">Description</label>
						<textarea name="description" id="descriptionInput">${place.description}</textarea> 
					</div>
					
					<div class="formField">
						<label for="passLengthInput">Length of the password to generate</label> 
						<input type="text" name="passLength" id="passLengthInput" 
							value="${place.passLength}" />
					</div>
					
					<div class="formField">
						<label for="hashTimesInput">Number of times to hash the value
							- More times is arguably more secure, but takes longer</label> 
						<input type="text" id="hashTimesInput" name="hashTimes" 
							value="${place.hashTimes}" />
					</div>
					
					<div class="formField">
						<label for="encodingInput">The characters that may be used in the output</label> 
						<input type="text" id="encodingInput" name="encodingChars"
							value="${place.encodingChars}" size="60"/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="savePlace" 
							value="Create" />
				</fieldset>
			</g:form>
		</div>
	</div>
</body>
</html>
