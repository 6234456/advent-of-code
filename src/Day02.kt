import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val arr = input.map { it.trim().split(" ").filter { x -> x.isNotBlank() }.map { x -> x.toInt() } }

        fun judge(l: List<Int>): Boolean {
            val t = l.zip(l.drop(1)).map { (a, b) -> a - b }
            val pos = t.first() > 0
            if (t.drop(1).any{ it > 0 != pos }) return false
            if (t.any { abs(it) == 0 || abs(it) > 3 }) return false
            return true
        }

        return arr.filter { judge(it) }.size
    }

    fun part2(input: List<String>): Int {
        val arr = input.map { it.trim().split(" ").filter { x -> x.isNotBlank() }.map { x -> x.toInt() } }

        fun judge(l: List<Int>): Boolean {
            val t = l.zip(l.drop(1)).map { (a, b) -> a - b }
            val pos = t.first() > 0
            if (t.drop(1).any { it > 0 != pos }) return false
            if (t.any { abs(it) == 0 || abs(it) > 3 }) return false
            return true
        }

        fun judge2(l: List<Int>): Boolean {
            for(i in 1..l.size){
               if (judge(l.take(i-1)+l.drop(i)))
                   return true
            }

            return false
        }

        return arr.filter { judge2(it) }.size
    }

    val testInput1 = readInput("Test02")
    check(part1(testInput1) == 2)

    val testInput2 = readInput("Test02")
    check(part2(testInput2) == 4)

    val input = readInput("Data02")
    part1(input).println()
    part2(input).println()
}
