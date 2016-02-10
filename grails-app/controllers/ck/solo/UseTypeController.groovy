package ck.solo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UseTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UseType.list(params), model: [useTypeCount: UseType.count()]
    }

    def show(UseType useType) {
        respond useType
    }

    def create() {
        respond new UseType(params)
    }

    @Transactional
    def save(UseType useType) {
        if (useType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (useType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond useType.errors, view: 'create'
            return
        }

        useType.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'useType.label', default: 'UseType'), useType.id])
                redirect useType
            }
            '*' { respond useType, [status: CREATED] }
        }
    }

    def edit(UseType useType) {
        respond useType
    }

    @Transactional
    def update(UseType useType) {
        if (useType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (useType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond useType.errors, view: 'edit'
            return
        }

        useType.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'useType.label', default: 'UseType'), useType.id])
                redirect useType
            }
            '*' { respond useType, [status: OK] }
        }
    }

    @Transactional
    def delete(UseType useType) {

        if (useType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        useType.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'useType.label', default: 'UseType'), useType.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'useType.label', default: 'UseType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
