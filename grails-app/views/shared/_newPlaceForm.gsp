<h2>Create Place</h2>
<div class="newPlaceForm">
	<g:form name="newPlaceForm">
		<g:render template="/shared/placeFormFields" />
		<g:submitToRemote value="Create Place" 
			onSuccess="addNewPlaceToList(data)" onFailure="addNewPlaceFailure(data)"
			url="[controller: 'place', action: 'create']"/>
	</g:form>
</div>