// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self


if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#places').dataTable();

		//$("#tabs").tabs();
		$("#accordion").accordion();

		$('.notices').fadeOut(7000);

		$('#master').passwordStrength();

		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function hashit(placename, times, encodingChars, lengthLimit) {
	// pull the master password and the place
	masterPass = $("#master").val();
	if (masterPass == "") {
		alert('Please enter your Master Password');
		return;
	}

	toHash = masterPass + placename;

	// encode to utf8
	hashed = str2rstr_utf8(toHash);

	// hash the specified number of times
	for (i = 0; i < times; i++) {
		hashed = rstr_sha256(hashed);
		// hashed = rstr_md5(hashed)
	}

	// encode to hex
	encoded = rstr2any(hashed, encodingChars);

	chopped = encoded.substring(0, lengthLimit);

	// set field on page
	$("#hashedPass").text(chopped);

	// give focus to field and highlight text?
}

//Reset all input fields
function resetHasher(val) {
	$("#hashedPass").text(val);
	$("#master").val("");
	clearNewPlaceForm();
}

function setAdvRowVisInPlacesTable(visibility){
	var placesTable = $('#places').dataTable();
	placesTable.fnSetColumnVis(2, visibility);
	placesTable.fnSetColumnVis(3, visibility);
	placesTable.fnSetColumnVis(4, visibility);
}

//Resets new place form
function clearNewPlaceForm() {
	$(':input','.formField').not(':button, :submit, :reset, :hidden').val('');
}

//success function when we add a new place
function addNewPlaceToList(data) {
	clearNewPlaceForm();

	$('#places').dataTable().fnAddData( [data.data.name, data.data.description, data.data.hashTimes, data.data.passLength, data.data.encodingChars ] );
}

//displays an error message when we fail to add a new place via ajax
function addNewPlaceFailure(data) {
	
}