package com.bowerstudios.passhasher
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

import javax.servlet.http.HttpServletResponse

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Secured('permitAll')
class LoginController {

	GooglePlusAuthService googlePlusAuthService
	
	/**
	 * Dependency injection for the authenticationTrustResolver.
	 */
	def authenticationTrustResolver

	/**
	 * Dependency injection for the springSecurityService.
	 */
	SpringSecurityService springSecurityService

	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index(){
		if (springSecurityService.isLoggedIn()) {
			redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
		}
		else {
			log.debug "User is not logged in, redirecting to auth page"
			redirect action: 'auth', params: params
		}
	}

	/**
	 * Show the login page.
	 */
	def auth(){

		def config = SpringSecurityUtils.securityConfig

		if (springSecurityService.isLoggedIn()) {
			redirect uri: config.successHandler.defaultTargetUrl
			return
		}
	
		return [clientId: grailsApplication.config.gplus.clientId,
				approvalPrompt: ((params.switchAccounts)?'force':'auto'),
				gPlusLogoutUrl: googlePlusAuthService.gPlusLogoutUrl()]
	}
	
	/**
	 * When a user successfully authenticates with Google+, they are redirected
	 * here with their Access Token.  We use that token to pull the current
	 * user's profile so that we can verify it is the user
	 */
	def gplus(){

		log.debug("Google Plus sign in starting")

		withForm {
			String code = params.codeInput
			log.debug "Code: ${code}"
			
			if(!code){
				flash.message = 'Invalid Code Received, please try again.'
				redirect(action:'auth')
				return
			}
			
			Map verifyTokenResult = googlePlusAuthService.verifyToken(code)
			if(verifyTokenResult.success){
				log.debug("Successfully verified Google Plus Token")
				if(googlePlusAuthService.verifyProfileDomain(verifyTokenResult)){
					if(googlePlusAuthService.finalAuth(
								verifyTokenResult.email, verifyTokenResult.accessToken)){
						flash.message = "Welcome ${verifyTokenResult.email}"
						redirect(uri:"/")
						return
					}else{
						flash.message = "Your user account (${verifyTokenResult.email}) has not been setup.  Please try again later, or contact support."
						redirect(action:'auth')
						return
					}
				}else{
					boolean revokeTokenSuccess =
							googlePlusAuthService.revokeToken(verifyTokenResult.accessToken)
					render(view:"glogout", model:[
							domain:verifyTokenResult.domain,
							email: verifyTokenResult.email,
							org:grailsApplication.config.gplus.org,
							revokeTokenSuccess: revokeTokenSuccess])
					return
				}
			}else{
				flash.message = 'Login Failed'
				log.error("Login Failed: ${verifyTokenResult}")
				redirect(action:'auth')
			}
	
		}.invalidToken {
			flash.message = 'Login Failed'
			log.warn("Invalid CSRF Token")
			redirect(action:'auth')
		}
	}

	/**
	 * Show denied page.
	 */
	def denied(){
		if (springSecurityService.isLoggedIn() &&
				authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: 'full', params: params
		}
	}

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	def authfail(){

		def username = session[UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		String msg = ''
		def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
		if (exception) {
			if (exception instanceof AccountExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.expired")
			}
			else if (exception instanceof CredentialsExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.passwordExpired")
			}
			else if (exception instanceof DisabledException) {
				msg = g.message(code: "springSecurity.errors.login.disabled")
			}
			else if (exception instanceof LockedException) {
				msg = g.message(code: "springSecurity.errors.login.locked")
			}
			else {
				msg = g.message(code: "springSecurity.errors.login.fail")
			}
		}

		if (springSecurityService.isAjax(request)) {
			render([error: msg] as JSON)
		}
		else {
			flash.message = msg
			redirect action: 'auth', params: params
		}
	}
}
