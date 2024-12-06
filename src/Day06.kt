fun main() {
    val day = 6

    fun part1(input: List<String>): Long {
        val pos:MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
        var starting = 0 to 0
        var direction = '^'
        val directionMap = mapOf<Char, Pair<Int, Int>>(
            '^' to  (-1 to 0),
            '>' to  (0 to 1),
            '<' to  (0 to -1),
            'v' to  (1 to 0),
        )
        val directionChange = mapOf<Char, Char>(
            '^' to  '>',
            '>' to  'v',
            'v' to  '<',
            '<' to  '^',
        )
        input.forEachIndexed { i, s ->
            s.forEachIndexed { j, c ->
                if (c == '#')
                   pos[Pair(i, j)] = 1
                else{
                    pos[Pair(i, j)] = 0
                    if (c in directionMap) {
                        direction = c
                        starting = i to j
                    }
               }
            }
        }

        val ans:MutableSet<Pair<Int, Int>> = mutableSetOf(starting)

        var delta = directionMap[direction]!!

        fun nextStep():Pair<Int, Int>{
            return (starting.first + delta.first) to (starting.second + delta.second)
        }

        while (true) {
            var next = nextStep()
            if (next !in pos) {
                return ans.size.toLong()
            }
            while (pos[next] == 1) {
                direction = directionChange[direction]!!
                delta = directionMap[direction]!!
                next = nextStep()
            }
            starting = next
            ans.add(starting)
        }

        throw Exception("not reachable")
    }

    fun part2(input: List<String>): Long {
        val pos:MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
        var init = 0 to 0
        var directionInit = '^'
        val directionMap = mapOf<Char, Pair<Int, Int>>(
            '^' to  (-1 to 0),
            '>' to  (0 to 1),
            '<' to  (0 to -1),
            'v' to  (1 to 0),
        )
        val directionChange = mapOf<Char, Char>(
            '^' to  '>',
            '>' to  'v',
            'v' to  '<',
            '<' to  '^',
        )
        input.forEachIndexed { i, s ->
            s.forEachIndexed { j, c ->
                if (c == '#')
                   pos[Pair(i, j)] = 1
                else{
                    pos[Pair(i, j)] = 0
                    if (c in directionMap) {
                        directionInit = c
                        init = i to j
                    }
               }
            }
        }

        var starting = init
        var direction = directionInit

        val points: MutableSet<Pair<Int, Int>> = mutableSetOf(starting)

        var delta = directionMap[direction]!!

        fun nextStep():Pair<Int, Int>{
            return (starting.first + delta.first) to (starting.second + delta.second)
        }

        while (true) {
            var next = nextStep()
            if (next !in pos) {
                break
            }
            while (pos[next] == 1) {
                direction = directionChange[direction]!!
                delta = directionMap[direction]!!
                next = nextStep()
            }
            starting = next
            //path.add(Triple(starting.first, starting.second, direction))
            points.add(starting)
        }

        points

        fun isObstruct(p: Pair<Int, Int>): Boolean{
            val path:MutableSet<Triple<Int, Int, Char>> = mutableSetOf()
            //val v0 =Triple(init.first, init.second, directionInit)
            if (p !in pos || pos[p] == 1) return false
            pos[p] = 1
            starting = init
            direction = directionInit
            delta = directionMap[direction]!!

            while(true){
                if (Triple(starting.first, starting.second, direction) in path){
                    pos[p] = 0
                    return true
                }

                path.add(Triple(starting.first, starting.second, direction))
                var next = nextStep()
                if (next !in pos) {
                    pos[p] = 0
                    return false
                }
                while (pos[next] == 1) {
                    direction = directionChange[direction]!!
                    delta = directionMap[direction]!!
                    next = nextStep()
                }
                starting = next

            }

           throw Exception("not reachable")

        }

        return points.map{
            if (it != init){
                if (isObstruct(it)){
                    //println(it)
                    1
                }
                else
                    0
            }else
                0
        }.sum().toLong()

    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 41L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 6L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
