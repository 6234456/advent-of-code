import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val day = 3

    val reg= """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
    val reg_d = """do\(\)""".toRegex()
    val reg_dnt = """don't\(\)""".toRegex()


    fun part1(input: List<String>): Long {
        return input.sumOf {
           s ->
            reg.findAll(s).fold(0L){ acc: Long, matchResult: MatchResult ->
                acc + matchResult.groupValues.drop(1).map { it.toLong() }.reduce { acc1, acc2 -> acc1 * acc2 }
            }
        }
    }

    fun part2(input: List<String>): Long {
        val inputString = input.reduce { acc, s ->  acc + s }
        var flag = true

        val flags =  ArrayDeque<Pair<Int, Boolean>>().apply {
            this.addAll(
                (reg_d.findAll(inputString).map {
                        matchResult ->
                    Pair(matchResult.groups[0]!!.range.first, true)
                } + (
                        reg_dnt.findAll(inputString).map {
                                matchResult ->
                            Pair(matchResult.groups[0]!!.range.first, false)
                        })).sortedBy { it.first }
            )
        }

        return reg.findAll(inputString).sumOf{
            val index = it.range.first
            while (flags.isNotEmpty() && flags.first().first < index){
                flag = flags.removeFirst().second
            }

            if (flag){
                return@sumOf it.groupValues[2].toLong() * it.groupValues[1].toLong()
            }
            return@sumOf 0L
        }
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 161L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 48L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
