class UrlMappings {

    static mappings = {
        "/login/auth" {
            controller = 'openId'
            action = 'auth'
        }
        "/login/openIdCreateAccount" {
            controller = 'openId'
            action = 'createAccount'
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"home")
        "500"(view:'/error')
    }
}
