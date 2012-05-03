package com.bowerstudios.passhasher

import org.springframework.dao.DataIntegrityViolationException


class PlaceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [placeInstanceList: Place.list(params), placeInstanceTotal: Place.count()]
    }

    def create() {
        Place placeInstance = new Place(params)
		return [placeInstance]
    }

    def save() {
        def placeInstance = new Place(params)
        if (!placeInstance.save(flush: true)) {
            render(view: "create", model: [placeInstance: placeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'place.label', default: 'Place'), placeInstance.id])
        redirect(action: "show", id: placeInstance.id)
    }

    def show() {
        def placeInstance = Place.get(params.id)
        if (!placeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
            redirect(action: "list")
            return
        }

        [placeInstance: placeInstance]
    }

    def edit() {
        def placeInstance = Place.get(params.id)
        if (!placeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
            redirect(action: "list")
            return
        }

        [placeInstance: placeInstance]
    }

    def update() {
        def placeInstance = Place.get(params.id)
        if (!placeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (placeInstance.version > version) {
                placeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'place.label', default: 'Place')] as Object[],
                          "Another user has updated this Place while you were editing")
                render(view: "edit", model: [placeInstance: placeInstance])
                return
            }
        }

        placeInstance.properties = params

        if (!placeInstance.save(flush: true)) {
            render(view: "edit", model: [placeInstance: placeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'place.label', default: 'Place'), placeInstance.id])
        redirect(action: "show", id: placeInstance.id)
    }

    def delete() {
        def placeInstance = Place.get(params.id)
        if (!placeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
            redirect(action: "list")
            return
        }

        try {
            placeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'place.label', default: 'Place'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'place.label', default: 'Place'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
