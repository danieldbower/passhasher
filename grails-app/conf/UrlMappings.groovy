class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"user", action:"passhasher")
		"/$id?"(controller:"user", action:"passhasher")
		"500"(view:'/error')
	}
}
