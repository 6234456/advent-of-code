import kotlin.math.abs

fun main() {
    val day = 3

    fun part1(input: List<String>): Int {
        val arr = input.map { it.trim().split(" ").filter { x -> x.isNotBlank() }.map { x -> x.toInt() } }
        return arr.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 2)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 4)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
