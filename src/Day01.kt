import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val arr = input.map { it.trim().split(" ").filter { x -> x.isNotBlank() }.map { x -> x.toInt() } }
        val arr1 = arr.map { it[0] }
        val arr2 = arr.map { it[1] }

        return arr1.sorted().zip(arr2.sorted()).sumOf { (a, b) -> abs(a - b) }
    }

    fun part2(input: List<String>): Int {
        val arr = input.map { it.trim().split(" ").filter { x -> x.isNotBlank() }.map { x -> x.toInt() } }
        val arr1 = arr.map { it[0] }
        val arr2 = arr.map { it[1] }.groupingBy { it }.eachCount()

        return arr1.sumOf { it * arr2.getOrElse(it) { 0 } }
    }

    val testInput = readInput("Test01")
    check(part1(testInput) == 11)

    val testInput2 = readInput("Test01")
    check(part2(testInput2) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Data01")
    part1(input).println()
    part2(input).println()
}
