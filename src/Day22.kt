fun main() {
    val day = 22

    data class Tuple4(val x: Long, val y: Long, val z: Long, val w: Long)

    fun mix(value:Long, secretNumber: Long): Long {
        return value xor secretNumber
    }

    fun prune(secretNumber: Long): Long {
        return secretNumber % 16777216L
    }

    fun process1(secretNumber: Long): Long {
        val s1 = prune(mix(secretNumber * 64L, secretNumber))
        val s2 = prune(mix(s1 / 32L, s1))
        return prune(mix(s2 * 2048L, s2))
    }

    fun process2(secretNumber: Long): List<Pair<Long, Long>> {
        val l = mutableListOf<Long>()
        var v = secretNumber
        repeat(2000) {
            l.add(v % 10L)
            v = process1(v)
        }
        return l.zip(l.drop(1)).map { (l1, l2) -> l2 to l2 - l1 }
    }

    fun part1(input: List<String>): Long {
        return input.map {
            var v = it.toLong()
            repeat(2000, {
                v = process1(v)
            })
            v
        }.sum()
    }


    fun part2(input: List<String>): Long {
        val ans: MutableMap<Tuple4, Long> = mutableMapOf()

        for (i in input){
            val cache: MutableSet<Tuple4> = mutableSetOf()
            val lst = process2(i.toLong())
            for (j in 0 .. lst.size-4){
                val triple = Tuple4(lst[j].second, lst[j+1].second, lst[j+2].second,lst[j+3].second)
                if (!cache.contains(triple)){
                    ans[triple] = ans.getOrDefault(triple, 0) + lst[j+3].first
                    cache.add(triple)
                }
            }
        }

       return ans.values.max()
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    //check(part1(testInput1) == 37327623L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 23L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
