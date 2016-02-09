package ck.solo

/**
 *  Применение - под язык, внутренно, наружно и т.п.
 */
class UseType {

    String title

    static belongsTo = [Supplies]

    static constraints = {
    }

    @Override
    public String toString() {
        title
    }
}
