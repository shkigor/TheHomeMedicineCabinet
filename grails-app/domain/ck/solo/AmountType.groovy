package ck.solo

/**
 * Вид фасовки препарата - пачка, бутылка, таблетка, ампула и т.п.
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
