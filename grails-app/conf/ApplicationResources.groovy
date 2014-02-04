modules = {
	application {
		resource url:'js/application.js'
	}
	hash {
		resource url:'/js/jshash-2.2/sha256.js', disposition: 'head'
	}
	passwordStrengthMeter {
		resource url:'/js/jquery.passwordStrengthMeter.js', disposition: 'head'
	}
	datatables {
		resource url:'/js/datatables/jquery.dataTables.js', disposition: 'head'
		resource url:'/css/datatables/jquery.dataTables.css'
	}
}