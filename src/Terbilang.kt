/**
 * Program terbilang menggunakan kotlin.
 *
 * Cara penggunaan:
 * terbilang.terbilang(100L)
 *
 * signature:
 * terbilang.terbilang(angka: Long): String
 *
 * Created by david on 4/15/17.
 */

object terbilang {
    val satuan: Array<String> = arrayOf("nol", "satu", "dua", "tiga", "empat",
            "lima", "enam", "tujuh", "delapan", "sembilan")
    val suffix: Map<Long, String> = mapOf(10L to "puluh", 100L to "ratus", 1000L to "ribu",
            1000000L to "juta", 1000000000L to "milyar", 1000000000000L to "triliun")

    fun terbilang(angka: Long): String = when(angka) {
        in 0L..9L -> satuan[angka.toInt()]
        in 11L..19L -> (satuan[(angka % 10L).toInt()] + " belas")
                        .replace("satu belas", "sebelas")
        else -> {
            val batas: Long? = try {suffix.keys.sortedByDescending {it}.filter {angka >= it}.first()}
                               catch (e: NoSuchElementException) { null }
            (if (batas != null ) "${terbilang(angka / batas)} ${suffix[batas]} ${if (angka % batas > 0L) terbilang(angka % batas) else ""} "
                            else "").replace("satu puluh", "sepuluh")
                                    .replace("satu ratus", "seratus")
                                    .replace("satu ribu", "seribu").trim()
        }
    }
}

fun main(args: Array<String>) {
    val n = 0L..9L
    val puluhan = 10L..99L
    val ratusan = listOf(100L, 500L, 702L)
    val ribuan = listOf(1000L, 2000L, 372159L)
    val jutaan = listOf(1000000L, 2000000L, 12759247L)
    val milyar = listOf(3000000000L, 79296467392L, 932658259587L)
    val triliun = listOf(1000000000000L, 716005407201000L, 857689000128256L)


    n.forEach { println("$it: ${terbilang.terbilang(it)}") }
    puluhan.forEach{ println("$it: ${terbilang.terbilang(it)}")}
    ratusan.forEach{ println("$it: ${terbilang.terbilang(it)}")}
    ribuan.forEach{ println("$it: ${terbilang.terbilang(it)}")}
    jutaan.forEach{ println("$it: ${terbilang.terbilang(it)}")}
    milyar.forEach{ println("$it: ${terbilang.terbilang(it)}")}
    triliun.forEach{ println("$it: ${terbilang.terbilang(it)}")}
}