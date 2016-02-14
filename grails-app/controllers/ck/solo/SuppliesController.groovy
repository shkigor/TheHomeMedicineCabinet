package ck.solo

import grails.converters.JSON
import grails.converters.XML

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SuppliesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Supplies.list(params), model: [suppliesCount: Supplies.count()]
    }

    def getFull(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        def json = new groovy.json.JsonBuilder()
//        respond json { Supplies.list(params) } as JSON
//        render Supplies.list(params)  as JSON
//        Supplies r = Supplies.findByTitleEn("Validol")
//        render(contentType: "application/json") {
//            r as JSON
//        }
//        render 'supplies':Supplies.findByTitleEn("Validol") as JSON
//        render 'supplies':Supplies.list(params)  as JSON
//                {"total":"4","rows":[{"id":"19881","firstname":";jlkdfoaj ew","lastname":" klaksd flaks","phone":"","email":""},{"id":"19882","firstname":"1231","lastname":"12312312","phone":"123123123123","email":"asdasdf@test.s"},{"id":"19884","firstname":"123","lastname":"12312","phone":"","email":""},{"id":"19885","firstname":"sfdsdfsst","lastname":"etertert","phone":"645646","email":""}]}
        print Supplies.findByTitleEn("Validol") as JSON
//        render '{"total":"2","rows":[{"title":"11111111111111"}, {"title":"22222222222222"}]}'
        render '[{"title":"11111111111111"}, {"title":"22222222222222"}]'
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
