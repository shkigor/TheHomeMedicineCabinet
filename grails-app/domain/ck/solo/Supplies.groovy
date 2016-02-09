package ck.solo

/**
 * Медицинские запасы - содержимое домашней аптечки
 */
class Supplies {

    String title                // название
    String titleEn              // название анг.
    SuppliesGroup suppliesGroup // гормоны, витамины, антибиотики, противовирусные препараты, мочегонные
    Structure structure         // жидкость, трава, мазь, таблетки, гель, порошок и т.п.
    Integer amount              // кол-во
    AmountType amountType       // пачка, бутылка, таблетка, ампула и т.п.
    Capacity capacity           // емкость - 100мл, 0.75г и т.п.
    Date endDate                // конечная дата хранения
    String instructions         // инструкция
    UseType useType             // применение - под язык, внутренно, наружно и т.п.
    Manufacturer manufacturer   // производитель
    Float percent               // раствор 1%, 2% и т.п.
    String imagePath

    // рекомендация - промывание ран, сердечные заболевания, ангина, отравление и т.п.
    static hasMany = [prescription: Prescription]

    static constraints = {
        title blank: false
        titleEn nullable: true
        suppliesGroup nullable: true
        structure blank: false
        amount nullable: true
        amountType nullable: true
        capacity nullable: true
        endDate nullable: false
        instructions nullable: true
        useType nullable: true
        manufacturer nullable: true
        percent nullable: true
        imagePath nullable: true
    }
}
