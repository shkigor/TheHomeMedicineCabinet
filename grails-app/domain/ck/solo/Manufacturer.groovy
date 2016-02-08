package ck.solo

/**
 * Производитель
 */
class Manufacturer {
    String name
    String address
    String manUrl
    String phone

    static constraints = {
        name blank: false
        address nullable: true
        phone nullable: true
        manUrl url: true, nullable: true
    }


    @Override
    public String toString() {
        name
    }
}
