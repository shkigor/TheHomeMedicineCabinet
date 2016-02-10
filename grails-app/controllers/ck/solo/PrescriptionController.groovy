package ck.solo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PrescriptionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Prescription.list(params), model: [prescriptionCount: Prescription.count()]
    }

    def show(Prescription prescription) {
        respond prescription
    }

    def create() {
        respond new Prescription(params)
    }

    @Transactional
    def save(Prescription prescription) {
        if (prescription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (prescription.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond prescription.errors, view: 'create'
            return
        }

        prescription.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'prescription.label', default: 'Prescription'), prescription.id])
                redirect prescription
            }
            '*' { respond prescription, [status: CREATED] }
        }
    }

    def edit(Prescription prescription) {
        respond prescription
    }

    @Transactional
    def update(Prescription prescription) {
        if (prescription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (prescription.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond prescription.errors, view: 'edit'
            return
        }

        prescription.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'prescription.label', default: 'Prescription'), prescription.id])
                redirect prescription
            }
            '*' { respond prescription, [status: OK] }
        }
    }

    @Transactional
    def delete(Prescription prescription) {

        if (prescription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        prescription.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'prescription.label', default: 'Prescription'), prescription.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'prescription.label', default: 'Prescription'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
