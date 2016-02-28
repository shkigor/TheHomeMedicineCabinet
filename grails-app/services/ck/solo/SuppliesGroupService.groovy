package ck.solo

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class SuppliesGroupService {

    def getFlatListAsJSON(params) {
        return SuppliesGroup.list(params) as JSON
    }
}
