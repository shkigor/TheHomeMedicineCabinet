package ck.solo

/**
 * Предписывание, рекомендация - промывание ран, сердечные заболевания, ангина, отравление и т.п.
 */
class Prescription {
    String title

    static hasMany = [supplies : Supplies]
    static constraints = {
    }

    @Override
    public String toString() {
        title
    }
}
