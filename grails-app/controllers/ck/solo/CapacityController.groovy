package ck.solo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CapacityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Capacity.list(params), model: [capacityCount: Capacity.count()]
    }

    def show(Capacity capacity) {
        respond capacity
    }

    def create() {
        respond new Capacity(params)
    }

    @Transactional
    def save(Capacity capacity) {
        if (capacity == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (capacity.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond capacity.errors, view: 'create'
            return
        }

        capacity.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'capacity.label', default: 'Capacity'), capacity.id])
                redirect capacity
            }
            '*' { respond capacity, [status: CREATED] }
        }
    }

    def edit(Capacity capacity) {
        respond capacity
    }

    @Transactional
    def update(Capacity capacity) {
        if (capacity == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (capacity.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond capacity.errors, view: 'edit'
            return
        }

        capacity.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'capacity.label', default: 'Capacity'), capacity.id])
                redirect capacity
            }
            '*' { respond capacity, [status: OK] }
        }
    }

    @Transactional
    def delete(Capacity capacity) {

        if (capacity == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        capacity.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'capacity.label', default: 'Capacity'), capacity.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'capacity.label', default: 'Capacity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
