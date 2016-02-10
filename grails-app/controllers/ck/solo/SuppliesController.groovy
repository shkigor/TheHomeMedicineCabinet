package ck.solo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SuppliesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Supplies.list(params), model: [suppliesCount: Supplies.count()]
    }

    def show(Supplies supplies) {
        respond supplies
    }

    def create() {
        respond new Supplies(params)
    }

    @Transactional
    def save(Supplies supplies) {
        if (supplies == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (supplies.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond supplies.errors, view: 'create'
            return
        }

        supplies.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'supplies.label', default: 'Supplies'), supplies.id])
                redirect supplies
            }
            '*' { respond supplies, [status: CREATED] }
        }
    }

    def edit(Supplies supplies) {
        respond supplies
    }

    @Transactional
    def update(Supplies supplies) {
        if (supplies == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (supplies.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond supplies.errors, view: 'edit'
            return
        }

        supplies.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'supplies.label', default: 'Supplies'), supplies.id])
                redirect supplies
            }
            '*' { respond supplies, [status: OK] }
        }
    }

    @Transactional
    def delete(Supplies supplies) {

        if (supplies == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        supplies.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'supplies.label', default: 'Supplies'), supplies.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplies.label', default: 'Supplies'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
