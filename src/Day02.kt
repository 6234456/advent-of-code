fun main() {
    fun part1(input: List<String>): Int {
        val arr = input.map { it.trim().split(" ").filter { x -> x.isNotBlank() }.map { x -> x.toInt() } }

        return arr.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput1 = readInput("Test02")
    check(part1(testInput1) == 0)

    val testInput2 = readInput("Test02")
    check(part2(testInput2) == 0)

    val input = readInput("Data02")
    part1(input).println()
    part2(input).println()
}
