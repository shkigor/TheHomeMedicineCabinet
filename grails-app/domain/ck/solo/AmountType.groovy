package ck.solo

/**
 * Вид хранения препарата - пачка, бутылка, таблетка, ампула и т.п.
 */
class AmountType {
    String title

    static belongsTo = [Supplies]

    static constraints = {
    }

    @Override
    public String toString() {
        title
    }
}
