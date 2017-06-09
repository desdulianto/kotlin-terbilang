import java.math.BigDecimal

/**
 * Terbilang dengan menggunakan extension function
 *
 * Created by Des Dulianto <desdulianto@gmail.com> on 5/25/17.
 */

fun Long.terbilang(): String {
    val satuan = setOf("nol", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan")
    val suffix: Map<Long, String> = mapOf(1000000000000L to "triliun",
                                             1000000000L to "milyar" ,
                                                1000000L to "juta"   ,
                                                   1000L to "ribu"   ,
                                                    100L to "ratus"  ,
                                                     10L to "puluh"   )
    return when {
        this < 0L -> "negatif ${Math.abs(this).terbilang()}"
        this.toInt() in 0..9 -> satuan.elementAt(this.toInt())
        this.toInt() in 11..19 -> "${satuan.elementAt((this % 10L).toInt())} belas".replace("satu", "se")
        else -> {
            val batas = try { suffix.keys.first { this >= it } } catch (e: NoSuchElementException) { null }
            batas?.let {
                "${(this / batas).terbilang()} ${suffix[batas]} ${if (this % batas > 0L) (this % batas).terbilang() else "" } "
                        .replace("satu puluh", "sepuluh")
                        .replace("satu ratus", "seratus")
                        .replace("satu ribu", "seribu").trim()
            } ?: ""
        }
    }
}

fun Int.terbilang(): String = this.toLong().terbilang()

fun Short.terbilang(): String = this.toLong().terbilang()

fun Byte.terbilang(): String = this.toLong().terbilang()

fun main(args: Array<String>) {
    // long
    val nLong = listOf(Long.MIN_VALUE+1L) + (0L..100L).toList() + listOf(500L, 702L, 1000L, 2000L, 372159L, 1000000L,
            2000000L, 12759247L, 1000000L, 2000000L, 12759247L, 3000000000L, 79296467392L, 932658259587L,
            1000000000000L, 716005407201000L, 857689000128256L)
    nLong.forEach { println("$it: ${it.terbilang()}")}

    // int
    val nInt: List<Int> = listOf(Int.MIN_VALUE) + (0..100).toList() +
            listOf(500, 702, 1_000, 2_000, 372_159, 1_000_000, 2_000_000, 12_759_247) +
            listOf(2_000_000_000, 2_122_574_224, Int.MAX_VALUE)
    nInt.forEach { println("$it: ${it.terbilang()}")}
}
