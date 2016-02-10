package ck.solo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AmountTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AmountType.list(params), model: [amountTypeCount: AmountType.count()]
    }

    def show(AmountType amountType) {
        respond amountType
    }

    def create() {
        respond new AmountType(params)
    }

    @Transactional
    def save(AmountType amountType) {
        if (amountType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (amountType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond amountType.errors, view: 'create'
            return
        }

        amountType.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'amountType.label', default: 'AmountType'), amountType.id])
                redirect amountType
            }
            '*' { respond amountType, [status: CREATED] }
        }
    }

    def edit(AmountType amountType) {
        respond amountType
    }

    @Transactional
    def update(AmountType amountType) {
        if (amountType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (amountType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond amountType.errors, view: 'edit'
            return
        }

        amountType.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'amountType.label', default: 'AmountType'), amountType.id])
                redirect amountType
            }
            '*' { respond amountType, [status: OK] }
        }
    }

    @Transactional
    def delete(AmountType amountType) {

        if (amountType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        amountType.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'amountType.label', default: 'AmountType'), amountType.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'amountType.label', default: 'AmountType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
