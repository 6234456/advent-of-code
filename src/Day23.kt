fun main() {
    val day = 23
    data class Node(val value:String, var next: String)

    class UnionFinder {
        val map = mutableMapOf<String, Node>()
        val sz = mutableMapOf<String, Int>()

        fun addPair(pair: String): UnionFinder {
            val s = pair.split("-").map {
                val n = Node(it, it)
                if (!map.containsKey(it)) {
                    map[it] = n
                    sz[it] = 1
                }
                return@map n
            }

            return union(s[0], s[1])
        }

        fun union(node: Node, target: Node): UnionFinder {
            val nodeValue = find(node)
            val targetValue = find(target)
            if (targetValue != nodeValue) {
               if (sz[targetValue]!! < sz[nodeValue]!!) {
                  map[targetValue]!!.next = nodeValue
                   sz[nodeValue] = sz[nodeValue]!! + 1
                   sz[targetValue] = sz[targetValue]!! - 1
               }else{
                   map[nodeValue]!!.next = targetValue
                   sz[nodeValue] = sz[nodeValue]!! - 1
                   sz[targetValue] = sz[targetValue]!! + 1
               }
            }
            return this
        }

        fun find(node: Node): String {
            var tmp  = node
            while (tmp.value != tmp.next) {
                tmp = map[tmp.next]!!
            }
            return tmp.value
        }

    }

    fun part1(input: List<String>): Long {
        val unionFinder = UnionFinder()
        input.forEach {
            unionFinder.addPair(it)
        }

        println(unionFinder.map.filterValues { it.value == it.next }.map { it.key })


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
