package ck.solo

/**
 * Тип препарата - жидкость, трава, мазь, таблетки, гель, порошок и т.п.
 */
class Structure {

    String title

    static belongsTo = [Supplies]

    static constraints = {
    }

    @Override
    public String toString() {
        title
    }
}
