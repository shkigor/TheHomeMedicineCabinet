package ck.solo

import grails.test.mixin.*
import spock.lang.*

@TestFor(SuppliesController)
@Mock(Supplies)
class SuppliesControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.suppliesList
        model.suppliesCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.supplies != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def supplies = new Supplies()
        supplies.validate()
        controller.save(supplies)

        then: "The create view is rendered again with the correct model"
        model.supplies != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        supplies = new Supplies(params)

        controller.save(supplies)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/supplies/show/1'
        controller.flash.message != null
        Supplies.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def supplies = new Supplies(params)
        controller.show(supplies)

        then: "A model is populated containing the domain instance"
        model.supplies == supplies
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def supplies = new Supplies(params)
        controller.edit(supplies)

        then: "A model is populated containing the domain instance"
        model.supplies == supplies
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/supplies/index'
        flash.message != null

        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def supplies = new Supplies()
        supplies.validate()
        controller.update(supplies)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.supplies == supplies

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        supplies = new Supplies(params).save(flush: true)
        controller.update(supplies)

        then: "A redirect is issued to the show action"
        supplies != null
        response.redirectedUrl == "/supplies/show/$supplies.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/supplies/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def supplies = new Supplies(params).save(flush: true)

        then: "It exists"
        Supplies.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(supplies)

        then: "The instance is deleted"
        Supplies.count() == 0
        response.redirectedUrl == '/supplies/index'
        flash.message != null
    }
}
