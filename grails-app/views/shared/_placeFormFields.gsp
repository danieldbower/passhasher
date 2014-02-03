<div class="formField">
	<label for="nameInput">Place Name</label>
	<input type="text" name="name" id="nameInput" 
		value="ex: facebook or http://facebook.com" size="36" />
</div>

<div class="formField">
	<label for="descriptionInput">Description</label>
	<textarea name="description" id="desccriptionInput">ex: Description of place and possibly username</textarea> 
</div>

<div class="formField advanced">
	<label for="passLengthInput">Length of the password to generate</label> 
	<input type="text" name="passLength" id="passLengthInput" 
		value="${defaultPassLength}" />
</div>

<div class="formField advanced">
	<label for="hashTimesInput">Number of times to hash the value
		- More times is arguably more secure, but takes longer</label> 
	<input type="text" id="hashTimesInput" name="hashTimes" 
		value="${defaultHashTimes}" />
</div>

<div class="formField advanced">
	<label for="encodingInput">The characters that may be used in the output</label> 
	<input type="text" id="encodingInput" name="encodingChars"
		value="${defaultEncodingChars}" size="60"/>
</div>