package com.bowerstudios.passhasher
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class LogoutController {

	GooglePlusAuthService googlePlusAuthService
	SpringSecurityService springSecurityService
	
	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
	
	def done(){
		if (springSecurityService.isLoggedIn()) {
			redirect action: 'index'
			return
		}
		
		if(grailsApplication.config.gplus.logOutOfGoogleImmediately){
			redirect(uri:googlePlusAuthService.gPlusLogoutUrl())
			return
			
		}else{
			return [gPlusLogoutUrl:googlePlusAuthService.gPlusLogoutUrl()]
		}
	}
}
