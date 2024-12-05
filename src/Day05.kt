import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val day = 5

    fun part1(input: List<String>): Int{
        val index = input.indexOfFirst { it.isBlank() }
        val checker = input.take(index).map {
            it.split("|").filter { it.isNotBlank() }.map { it.trim().toInt() }
        }.fold(mutableMapOf<Int, MutableSet<Int>>()){ acc, (v1, v2)  ->
            if (acc.contains(v1)){
                acc[v1]!!.add(v2)
            }else{
                acc[v1] = mutableSetOf(v2)
            }
            acc
        }
        return input.drop(index+1).map { it.split(",").filter { it.isNotBlank() }.map { it.trim().toInt() } }.sumOf {
            l ->
            for (i in l.indices.reversed()) {
                if (!checker.containsKey(l[i])) continue
                val s = checker[l[i]]!!
                for (j in 0 until i) {
                   if (s.contains(l[j])){
                       return@sumOf 0
                   }
                }
            }
            l[(l.size - 1) / 2]
        }
    }

    fun part2(input: List<String>): Int {
        val index = input.indexOfFirst { it.isBlank() }
        val checker = input.take(index).map {
            it.split("|").filter { it.isNotBlank() }.map { it.trim().toInt() }
        }.fold(mutableMapOf<Int, MutableSet<Int>>()){ acc, (v1, v2)  ->
            if (acc.contains(v1)){
                acc[v1]!!.add(v2)
            }else{
                acc[v1] = mutableSetOf(v2)
            }
            acc
        }
        return input.drop(index+1).map { it.split(",").filter { it.isNotBlank() }.map { it.trim().toInt() } }.sumOf {
                l ->
            for (i in l.indices.reversed()) {
                if (!checker.containsKey(l[i])) continue
                val s = checker[l[i]]!!
                for (j in 0 until i) {
                    if (s.contains(l[j])){
                        return@sumOf l.sortedWith {
                           a, b ->
                            if(checker.containsKey(a) && checker[a]!!.contains(b)) return@sortedWith -1
                            if(checker.containsKey(b) && checker[b]!!.contains(a)) return@sortedWith 1
                            0
                        }[(l.size - 1) / 2]
                    }
                }
            }
            0
        }
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 143)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 123)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
