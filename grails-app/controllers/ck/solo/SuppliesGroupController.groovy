package ck.solo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SuppliesGroupController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SuppliesGroup.list(params), model: [suppliesGroupCount: SuppliesGroup.count()]
    }

    def show(SuppliesGroup suppliesGroup) {
        respond suppliesGroup
    }

    def create() {
        respond new SuppliesGroup(params)
    }

    @Transactional
    def save(SuppliesGroup suppliesGroup) {
        if (suppliesGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (suppliesGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond suppliesGroup.errors, view: 'create'
            return
        }

        suppliesGroup.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'suppliesGroup.label', default: 'SuppliesGroup'), suppliesGroup.id])
                redirect suppliesGroup
            }
            '*' { respond suppliesGroup, [status: CREATED] }
        }
    }

    def edit(SuppliesGroup suppliesGroup) {
        respond suppliesGroup
    }

    @Transactional
    def update(SuppliesGroup suppliesGroup) {
        if (suppliesGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (suppliesGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond suppliesGroup.errors, view: 'edit'
            return
        }

        suppliesGroup.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'suppliesGroup.label', default: 'SuppliesGroup'), suppliesGroup.id])
                redirect suppliesGroup
            }
            '*' { respond suppliesGroup, [status: OK] }
        }
    }

    @Transactional
    def delete(SuppliesGroup suppliesGroup) {

        if (suppliesGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        suppliesGroup.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'suppliesGroup.label', default: 'SuppliesGroup'), suppliesGroup.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'suppliesGroup.label', default: 'SuppliesGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
