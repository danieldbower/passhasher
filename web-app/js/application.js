if (typeof jQuery !== 'undefined') {
	(function($) {
		$("#tabs").tabs();

		$('.notices').fadeOut(7000);

		hideAdvanced();

		$('#places').dataTable();

		$('#master').passwordStrength();

		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

$(document).ready(function() {
} );

function hashit(placename, times, encodingChars, lengthLimit) {
	// pull the master password and the place
	masterPass = $("#master").val()
	if (masterPass == "") {
		alert('Please enter your Master Password')
		return
	}

	toHash = masterPass + placename

	// encode to utf8
	hashed = str2rstr_utf8(toHash)

	// hash the specified number of times
	for (i = 0; i < times; i++) {
		hashed = rstr_sha256(hashed)
		// hashed = rstr_md5(hashed)
	}

	// encode to hex
	encoded = rstr2any(hashed, encodingChars)

	chopped = encoded.substring(0, lengthLimit)

	// set field on page
	$("#hashedPass").text(chopped)

	// give focus to field and highlight text?
}

//Reset all input fields
function resetHasher(val) {
	$("#hashedPass").text(val)
	$("#master").val("")
	clearNewPlaceForm()
}

function showAdvanced() {
	$(".advanced").show()
	$(".expandAdvanced").hide()
}

function hideAdvanced() {
	$(".advanced").hide()
	$(".expandAdvanced").show()
}

//Resets new place form
function clearNewPlaceForm() {
	$(':input','.formField').not(':button, :submit, :reset, :hidden').val('')
}

//success function when we add a new place
function addNewPlaceToList(data) {
	clearNewPlaceForm()

	/*   starting from http://jsfiddle.net/Dq37V/
	var arr = [];
	arr.push(row.id);

	$("#places tr").each(function(){
		arr.push(this.id);
	});
	*/
}

//displays an error message when we fail to add a new place via ajax
function addNewPlaceFailure(data) {
	
}
