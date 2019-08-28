import java.util.ArrayList
import kotlin.streams.toList

/**
 *   Created by hong2 on 29/08/2019
 *   Time : 1:57 AM
 */

fun main(args: Array<String>) {
    val input = arrayOf(intArrayOf(1, 2, 3), intArrayOf(8, 9, 4), intArrayOf(7, 6, 5))
    extractArray(input)
}

private fun extractArray(input: Array<IntArray>) {

    val output = ArrayList<Int>()

    for (i in input.indices) {
        for (j in 0 until input[i].size) {
            output.add(input[i][j])
        }
    }

    println(output.stream().sorted().toList())
}