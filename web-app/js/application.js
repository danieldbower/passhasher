if (typeof jQuery !== 'undefined') {
	(function($) {
		$("#tabs").tabs()
		$('.notices').fadeOut(7000);
		hideAdvanced()
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

function resetHasher(val) {
	$("#hashedPass").text(val)
	$("#master").val("")
}

function showAdvanced() {
	$(".advanced").show()
	$(".expandAdvanced").hide()
}

function hideAdvanced() {
	$(".advanced").hide()
	$(".expandAdvanced").show()
}