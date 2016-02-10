package ck.solo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StructureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Structure.list(params), model: [structureCount: Structure.count()]
    }

    def show(Structure structure) {
        respond structure
    }

    def create() {
        respond new Structure(params)
    }

    @Transactional
    def save(Structure structure) {
        if (structure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (structure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond structure.errors, view: 'create'
            return
        }

        structure.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'structure.label', default: 'Structure'), structure.id])
                redirect structure
            }
            '*' { respond structure, [status: CREATED] }
        }
    }

    def edit(Structure structure) {
        respond structure
    }

    @Transactional
    def update(Structure structure) {
        if (structure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (structure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond structure.errors, view: 'edit'
            return
        }

        structure.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'structure.label', default: 'Structure'), structure.id])
                redirect structure
            }
            '*' { respond structure, [status: OK] }
        }
    }

    @Transactional
    def delete(Structure structure) {

        if (structure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        structure.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'structure.label', default: 'Structure'), structure.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'structure.label', default: 'Structure'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
