import ck.solo.Capacity
import ck.solo.Supplies

class BootStrap {

    def init = { servletContext ->
        if (!Supplies.count()) createSampleData()
    }
    def destroy = {
    }

    void createSampleData() {
        def capacity1 = new Capacity(dimension: 100, abbreviation: 'мл').save(failOnError: true)
        def capacity2 = new Capacity(dimension: 0.75, abbreviation: 'г').save(failOnError: true)

    }
}
