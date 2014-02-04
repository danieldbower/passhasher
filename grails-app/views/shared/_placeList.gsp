<h2>Places</h2>

<table class="display" id="places">
	<thead>
		<tr>
			<th width="15%">Name</th>
			<th>Notes</th>
			<th class="advanced">Strength</th>
			<th class="advanced">Length</th>
			<th class="advanced">Allowed Characters</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${sortedPlaces}" var="place">
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

<g:form name="newPlaceForm">
	<h2>Create New</h2>
	<g:render template="/shared/placeFormFields" />
	<g:submitToRemote value="Create Place" 
		onSuccess="addNewPlaceToList(data)" onFailure="addNewPlaceFailure(data)"
		url="[controller: 'place', action: 'create']"/>
	<button onclick="hashit($('#nameInput').val(), $('#hashTimesInput').val(), $('#encodingInput').val(), $('#passLengthInput').val())">hash it</button>
</g:form >