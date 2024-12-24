fun main() {
    val day = 24

    fun part1(input: List<String>): Long {
        val blank = input.indexOfFirst { it.isBlank() }
        val values = input.take(blank).associate {
            val x = it.split(":").map { it.trim() }
            x.first() to x[1].toInt()
        }.toMutableMap()

        val connection = input.drop(blank+1).associate {
            val x = it.split("->")
            val y = x.first().trim().split(" ")
            x.last().trim() to Triple(y[0], y[2], y[1])
        }

        fun dfs(v:String):Int{
            if (values.containsKey(v)) return values[v]!!

            val (e1, e2, op) = connection[v]!!
            val v1 = dfs(e1)
            val v2 = dfs(e2)
            val res = when (op) {
                "AND" -> v1 and v2
                "OR" -> v1 or v2
                "XOR" -> v1 xor v2
                else -> -1
            }

            values[v] = res
            return res
        }

        return connection.keys.filter { it.startsWith("z") }.sorted().map{
            dfs(it)
        }.foldRight(0L) { v, acc ->
            acc * 2L + v
        }
    }

    fun part2(input: List<String>): Long {
        val blank = input.indexOfFirst { it.isBlank() }
        val connection = input.drop(blank+1).associate {
            val x = it.split("->")
            val y = x.first().trim().split(" ")
            x.last().trim() to Triple(y[0], y[2], y[1])
        }

        fun check(key:String, order:String, ignoreOp:Boolean = false):Boolean{
           val (e1, e2, op) = connection[key]!!
            return (ignoreOp || op == "XOR") && e1.drop(1) == order && e2.drop(1) == order
        }

        fun getMismatch(order:String, ignoreOp: Boolean = false):String{
            val lvl1 = connection.filterValues {
                val (e1, e2, op) = it
                e1.drop(1) == order && e2.drop(1) == order && op == "XOR"
            }.keys.first()

            return if (ignoreOp) lvl1 else connection.filterValues {
                val (e1, e2, op) = it
                op == "XOR" && (e1 == lvl1 || e2 == lvl1)
            }.keys.firstOrNull() ?: ""
        }

        println((1..44).map { it.toString().padStart(2, '0') }.flatMap { x ->
            val (e1, e2, op) = connection["z$x"]!!
            if(op != "XOR" || (!check(e1, x) && !check(e2, x))){
                if (op != "XOR")
                    listOf("z$x",getMismatch(x))
                else{
                    if (check(e1, x, true)) listOf(e1, getMismatch(x, true)) else listOf(e2,getMismatch(x, true))
                }
            }
            else{
                listOf()

            }
        }.sorted().joinToString(","))

        return 0L
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    //check(part1(testInput1) == 2024L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    //check(part2(testInput2) == 0L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
