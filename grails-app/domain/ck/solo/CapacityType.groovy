package ck.solo

/**
 *  г., мл. и т.п.
 */
class CapacityType {
    String title

    static belongsTo = [Supplies]

    static constraints = {
    }

    @Override
    public String toString() {
        title
    }
}
