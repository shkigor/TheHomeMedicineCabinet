package ck.solo

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class StructureService {

    def getFlatListAsJSON(params) {
        return Structure.list(params) as JSON
    }
}
