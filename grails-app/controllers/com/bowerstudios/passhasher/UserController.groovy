package com.bowerstudios.passhasher

import org.springframework.dao.DataIntegrityViolationException

class UserController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def passhasher() {
		Map defaults = [defaultEncodingChars:Place.DEFAULT_ENCODING_CHARS,
					defaultPassLength:Place.DEFAULT_PASS_LENGTH,
					defaultHashTimes:Place.defaultHashTimes()]

		User userInstance = null
		if(params.id && params.id.isBigInteger()){
			userInstance = User.get(params.id)
			if (userInstance){
				return[userInstance: userInstance] + defaults
			}
		}

		return defaults
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[userInstanceList: User.list(params), userInstanceTotal: User.count()]
	}

	def create() {
		[userInstance: new User(params)]
	}

	def save() {
		def userInstance = new User(params)
		if (!userInstance.save(flush: true)) {
			render(view: "create", model: [userInstance: userInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'user.label', default: 'User'),
			userInstance.id
		])
		redirect(action: "show", id: userInstance.id)
	}

	def show() {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[userInstance: userInstance]
	}

	def edit() {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[userInstance: userInstance]
	}

	def update() {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (userInstance.version > version) {
				userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'user.label', default: 'User')]
						as Object[],
						"Another user has updated this User while you were editing")
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
		}

		userInstance.properties = params

		if (!userInstance.save(flush: true)) {
			render(view: "edit", model: [userInstance: userInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'user.label', default: 'User'),
			userInstance.id
		])
		redirect(action: "show", id: userInstance.id)
	}

	def delete() {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			userInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}
}
