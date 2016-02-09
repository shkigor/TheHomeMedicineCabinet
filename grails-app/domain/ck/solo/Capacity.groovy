package ck.solo

/**
 *  Емкость
 */
class Capacity {

    Float dimension     // 100, 0.75 и т.п.
    String abbreviation // г., мл. и т.п.

    static belongsTo = [Supplies]

    static constraints = {
        dimension nullable: false
        abbreviation nullable: false
    }

    @Override
    public String toString() {
        "$dimension $abbreviation"
    }
}
