import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val day = 4

    fun part1(input: List<String>): Long {
        return 0L
    }

    fun part2(input: List<String>): Long {
       return 0L
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 0L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 0L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
