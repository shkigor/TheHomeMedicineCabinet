package ck.solo

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class SuppliesService {

    def getFlatListAsJSON(params) {

        def returnList = []
        Supplies.list(params).each {
            returnList.add([
                    'title'     : it.title,
                    'structure' : it.structure.title,
                    'amountType': it.amountType.title,
                    'capacity'  : it.capacity.toString()
            ])
        }
        return returnList as JSON
    }
}