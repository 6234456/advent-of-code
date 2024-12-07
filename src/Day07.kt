import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val day = 7

    fun part1(input: List<String>): ULong {

        fun judge(res: ULong, values:List<ULong>): Boolean {
            val n = values.size
            fun dfs(i: Int, sum:ULong):Boolean {
                if (i == n)
                    return sum == res

                val v = values[i]
                return dfs(i + 1, sum + v) || dfs(i + 1, sum * v)
            }

            return dfs(1, values[0])  // skip the first index and step over the last index to simplify the codes
        }


        return input.sumOf {
            val tmp = it.split(":").filterNot { it.isBlank() }.flatMap{
                it.split(" ").filterNot { it.isBlank() }.map { it.toULong() }}
            val res = tmp[0]
            val values = tmp.drop(1)

            if (judge(res, values)) { res } else 0UL
        }
    }

    fun part2(input: List<String>): ULong {
        fun combine(sum:ULong, v: ULong):ULong {
            var t = v
            var s = sum
            while (t > 0UL) {
                t /= 10UL
                s *= 10UL
            }
            return s + v
        }

       fun judge(res: ULong, values:List<ULong>): Boolean {
            val n = values.size
            fun dfs(i: Int, sum:ULong):Boolean {
                if (i == n)
                    return sum == res
                val v = values[i]

                return dfs(i + 1, sum + v) || dfs(i + 1, sum * v) || dfs(i + 1, combine(sum, v))
            }

            return dfs(1, values[0])
        }


        return input.sumOf {
            val tmp = it.split(":").filterNot { it.isBlank() }.flatMap{
                it.split(" ").filterNot { it.isBlank() }.map { it.toULong() }}
            val res = tmp[0]
            val values = tmp.drop(1)

            if (judge(res, values)) {
                res
            }
            else 0UL
        }
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 3749UL)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 11387UL)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
