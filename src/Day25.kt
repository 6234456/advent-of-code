fun main() {
    val day = 25

    fun part1(input: List<String>): Long {
        val locks: MutableList<List<Int>> = mutableListOf()
        val keys: MutableList<List<Int>> = mutableListOf()
        var start = 0
        fun process(s: Int, e: Int) {
            val t = input.slice(s..e)
            val raw = (0..4).map { x ->
                t.map { y ->
                    y[x]
                }
            }


            if (input[s].all { it == '#' }) {
                // lock
                locks.add(
                    raw.map {
                        val v = it.indexOfFirst { it == '.' }
                        if (v == -1) it.size - 1 else v - 1
                    }
                )
            } else {
                // key
                keys.add(
                    raw.map {
                        val v = it.indexOfFirst { it == '#' }
                        5 - (if (v == -1) it.size - 1 else v - 1)
                    }
                )
            }

        }
        input.forEachIndexed { index, s ->
            if (s.isBlank()) {
                process(start, index - 1)
                start = index + 1
            } else if (index == input.size - 1) {
                process(start, index)
                start = index + 1
            }
        }

            var ans = 0L

            fun fit(key: List<Int>, lock: List<Int>): Boolean {
                return key.zip(lock).all { it.first + it.second < 6 }
            }

            for (k in keys) {
                for (v in locks) {
                    if (fit(k, v)) {
                        ans++
                    }
                }
            }


            return ans
        }

    fun part2(input: List<String>): Long {
       return 0L
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 3L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 0L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
