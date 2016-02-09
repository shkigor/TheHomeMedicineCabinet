import ck.solo.AmountType
import ck.solo.Capacity
import ck.solo.Manufacturer
import ck.solo.Prescription
import ck.solo.Structure
import ck.solo.Supplies
import ck.solo.SuppliesGroup
import ck.solo.UseType

class BootStrap {

    def init = { servletContext ->
        if (!Supplies.count()) createSampleData()
    }
    def destroy = {
    }

    void createSampleData() {
        Capacity capacity1 = new Capacity(dimension: 100, abbreviation: 'мл').save(failOnError: true)
        Capacity capacity2 = new Capacity(dimension: 0.75, abbreviation: 'г').save(failOnError: true)

        UseType useType1 = new UseType(title: 'внутренно').save(failOnError: true)
        UseType useType2 = new UseType(title: 'наружно').save(failOnError: true)

        SuppliesGroup suppliesGroup1 = new SuppliesGroup(title: 'антибиотики').save(failOnError: true)
        SuppliesGroup suppliesGroup2 = new SuppliesGroup(title: 'витамины').save(failOnError: true)

        Structure structure1 = new Structure(title: 'жидкость').save(failOnError: true)
        Structure structure2 = new Structure(title: 'трава').save(failOnError: true)
        Structure structure3 = new Structure(title: 'мазь').save(failOnError: true)
        Structure structure4 = new Structure(title: 'таблетки').save(failOnError: true)

        Prescription prescription1 = new Prescription(title: 'промывание ран').save(failOnError: true)
        Prescription prescription2 = new Prescription(title: 'сердечные заболевания').save(failOnError: true)
        Prescription prescription3 = new Prescription(title: 'грипп').save(failOnError: true)

        Manufacturer manufacturer1 = new Manufacturer(name: 'ПАТ \"Фітофарм\"', address: '84500, Україна, Донецька обл.,м.Артемівськ,вул.Сибірцева,2', manUrl: 'http://www.fitofarm.dn.ua', phone: '(0412) 48-61-84').save(failOnError: true)
        Manufacturer manufacturer2 = new Manufacturer(name: 'ВАТ \"Фармак\"', address: 'Україна, м.Київ, вул.Фрунзе, 63').save(failOnError: true)

        AmountType amountType1 = new AmountType(title: 'бутылка').save(failOnError: true)
        AmountType amountType2 = new AmountType(title: 'пачка').save(failOnError: true)
        AmountType amountType3 = new AmountType(title: 'таблетка').save(failOnError: true)
        AmountType amountType4 = new AmountType(title: 'ампула').save(failOnError: true)

        Supplies supplies1 = new Supplies(
                        title: 'Перекис водню розчин',
                        titleEn: 'Hydrogen peroxide solution',
                        suppliesGroup: suppliesGroup1,
                        structure: structure1,
                        amount: 1,
                        amountType: amountType1,
                        capacity: capacity1,
                        endDate: new Date(),
                        instructions: '<html> <h1>Инструкция Перекис водню розчин</h1> </html>',
                        useType: useType2,
                        manufacturer: manufacturer1,
                        percent: null,
                        imagePath: 'path to image'
        ).save(failOnError: true)

        Supplies supplies2 = new Supplies(
                title: 'Валідол',
                titleEn: 'Validol',
                suppliesGroup: null,
                structure: structure4,
                amount: 10,
                amountType: amountType3,
                capacity: null,
                endDate: new Date(),
                instructions: '<html> <h1>Инструкция Валідол</h1> </html>',
                useType: useType1,
                manufacturer: manufacturer2,
                percent: null,
                imagePath: 'path to image'
        ).save(failOnError: true)

        supplies2.addToPrescription(prescription2).save(failOnError: true)

    }
}
