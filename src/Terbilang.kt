/**
 * Created by david on 4/15/17.
 */

object terbilang {
    val satuan: Array<String> = arrayOf("nol", "satu", "dua", "tiga", "empat",
            "lima", "enam", "tujuh", "delapan", "sembilan")
    val suffix: Map<Long, String> = mapOf(10L to "puluh", 100L to "ratus", 1000L to "ribu",
            1000000L to "juta", 1000000000L to "milyar", 1000000000000L to "triliun")

    /*suffix.keys.sortedByDescending { it }.forEach {
    }*/
    fun terbilang(angka: Long): String {
        return when {
            angka in 0L..9L -> satuan[angka.toInt()]
            angka in 11L..19L -> if (angka == 11L) "sebelas" else satuan[(angka % 10L).toInt()] + " belas"
            angka == 10L -> "sepuluh"
            else -> {
                var terbilang = ""
                for (i in suffix.keys.sortedByDescending { it }) {
                    if (angka >= i) {
                        terbilang = "${terbilang(angka / i)} ${suffix.get(i)} ${if (angka % i > 0L) terbilang(angka % i) else ""} "
                        break
                    }
                }
                return terbilang.trim()
            }
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

    // terbilang(1) --> satu
    // terbilang(10) --> sepuluh
    // terbilang(19) --> sembilan belas
    // terbilang(329) --> tiga ratus dua puluh sembilan
}