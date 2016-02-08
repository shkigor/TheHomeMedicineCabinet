package ck.solo

/**
 * Медицинские запасы - содержимое домашней аптечки
 */
class Supplies {
    String titleUk              // название укр.
    String titleRu              // название рус.
    String titleEn              // название анг.
    SuppliesType suppliesType   // жидкость, трава, мазь, таблетки, гель, порошок и т.п.
    Float percent               // раствор 1%, 2% и т.п.
    Integer amount              // кол-во
    AmountType amountType       // пачка, бутылка, таблетка, ампула и т.п.
    Float capacity              // емкость - 100мл, 0.75г и т.п.
    CapacityType capacityType   // г., мл. и т.п.
    Date endDate                // конечная дата хранения
    String instructions         // инструкция
    UseType useType             // применение - под язык, внутренно, наружно и т.п.
    Manufacturer manufacturer   // производитель

    static belongsTo = [SuppliesType, AmountType, UseType, Manufacturer, Prescription]

    // рекомендация - промывание ран, сердечные заболевания, ангина, отравление и т.п.
    static hasMany = [prescription: Prescription]

    static constraints = {
        titleRu blank: false
        titleUk nullable: true
        titleEn nullable: true
        suppliesType blank: false
        amount nullable: true
        amountType nullable: true
        capacity nullable: true
        capacityType nullable: true
        endDate nullable: true
        instructions nullable: true
        useType nullable: true
        manufacturer nullable: true
        percent nullable: true
    }
}
