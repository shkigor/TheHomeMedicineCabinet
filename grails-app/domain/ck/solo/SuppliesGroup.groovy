package ck.solo

/**
 * Все современные лекарственные средства группируются по следующим принципам:

 1. Терапевтическому применению. Например, препараты для лечения опухолей, снижения артериального давления, противомикробные.
 2. Фармакологическому действию, т.е. вызываемому эффекту (вазодилататоры — расширяющие сосуды, спазмолитики — устраняющие спазм сосудов, анальгетики — снижающие болевое раздражение).
 3. Химическому строению. Группы лекарственных препаратов, сходных по своему строению. Таковы все салицилаты, полученные на основе ацетилсалициловой кислоты — аспирин, салициламид, метилсалицилат и т.д.
 4. Нозологическому принципу. Ряд различныхлекарств, применяемых для лечения строго определенной болезни (например, средства для лечения инфаркта миокарда, бронхиальной астмы и т.д.).

 Принята классификация медикаментов, предложенная академиком МД. Машковским.
 1. Лекарственные препараты, действующие преимущественно на центральную нервную систему: средства для наркоза, снотворные, психотропные препараты (транквилизаторы, нейролептические, седативные средства, антидепрессанты, стимуляторы); противосудорожные (противоэпилептические медикаменты); лекарства для лечения паркинсонизма, анальгетики, жаропонижающие, противовоспалительные препараты, противокашлевые.
 2. Лекарственные средства с действием в области окончания эфферентных (центробежных) нервов: холинолитики, ганглиоблокирующие, курареподобные и др.
 3. Лекарственные средства, действующие преимущественно на чувствительные нервные окончания, в том числе слизистой оболочки и кожи: местноанестезирующие препараты, обволакивающие и адсорбирующие средства, вяжущие, рвотные, отхаркивающие и слабительные.
 4. Лекарства, действующие на сердечнососудистую систему.
 5. Лекарства, усиливающие выделительную функцию почек.
 6. Желчегонные медикаменты.
 7. Препараты, влияющие на мускулатуру матки.
 8. Средства, влияющие на процессы обмена веществ: гормоны, витамины и их аналоги, ферменты, гистамин и антигистаминные препараты, биогенные и пр.
 9. Противомикробные: антибиотики, сульфаниламиды, производные нитрофурана, противотуберкулезные, противосифилитические, противовирусные препараты и т.д., антисептики (группа галогенов, окислители, кислоты и щелочи, спирты, фенолы, красители, дегти, смолы и т.д.).
 10. Препараты для лечения злокачественных новообразований.
 11. Диагностические средства.
 12. Прочие препараты различных фармакологических групп.
 */
class SuppliesGroup {
    String title

    static constraints = {
    }

    @Override
    public String toString() {
        title
    }
}
