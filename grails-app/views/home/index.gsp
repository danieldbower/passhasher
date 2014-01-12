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
		<h2>
			Places
			<g:link action="edit" id="${userInstance.id}" class="ui-icon ui-icon-plusthick">add place</g:link>
		</h2>
		<table class="places">
			<thead>
				<tr>
					<th width="15%">Name</th>
					<th>Notes</th>
					<th class="advanced">Strength</th>
					<th class="advanced">Length</th>
					<th class="advanced">Allowed Characters</th>
					<th width="5%" class="placeButtons">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${userInstance.places}" var="place">
				<tr>
					<td class="placeName"
						onclick="hashit('${ place.name }', ${ place.hashTimes }, '${ place.encodingChars }', ${ place.passLength })">
						${place.name}
					</td>
					<td class="placeDesc">
						${ place.description }
					</td>
					<td class="placeHashTimes advanced">
						${ place.hashTimes }
					</td>
					<td class="placePassLength advanced">
						${ place.passLength }
					</td>
					<td class="placeEncodingChars advanced">
						${ place.encodingChars }
					</td>
				</tr>
				</g:each>
			</tbody>
		</table>

		<div class="buttons">
	</g:if>
	<g:else>

		<p class="anonymous">Either sign in (at the top right) to access
			your saved list of places,
		<div class="OR">or</div>
		<label for="placeInput">Enter a place</label>
		<input type="text" id="placeInput"
			value="ex: facebook or http://facebook.com" size="36" />
		</p>

		<p class="advanced anonymous">
			<label for="hashTimesInput">Number of times to hash the value
				- More times is arguably more secure, but takes longer</label> 
			<input type="text" id="hashTimesInput" value="${defaultHashTimes}" />
		</p>

		<p class="advanced anonymous">
			<label for="passLengthInput">Length of the password to
				generate</label> 
			<input type="text" id="passLengthInput" value="${defaultPassLength}" />
		</p>

		<p class="advanced anonymous">
			<label for="encodingInput">The characters that may be used in
				the output</label> <input type="text" id="encodingInput"
				value="${defaultEncodingChars}" />
		</p>

		<div class="buttons">
			<button
				onclick="hashit($('#placeInput').val(), $('#hashTimesInput').val(), $('#encodingInput').val(), $('#passLengthInput').val())">hash
				it</button>
	</g:else>
	<button title="reset form" onclick="resetHasher('blank')" class="reset">reset</button>

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
