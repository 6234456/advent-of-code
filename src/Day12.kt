fun main() {
    val day = 12

    fun part1(input: List<String>): Long {
        val y = input.size
        val x = input[0].length
        val visited = Array<BooleanArray>(y){BooleanArray(x) { false }}

        fun dfs(x0:Int, y0:Int, target:Char):Pair<Int, Int>{
            var perimeter = 0
            if (x0 >= x) {
                perimeter += 1
            }
            if (y0 >= y) {
                perimeter += 1
            }
            if (x0 < 0) {
                perimeter += 1
            }
            if (y0 < 0) {
                perimeter += 1
            }
            if (x0 < x && y0 < y && x0 >= 0 && y0 >= 0 && input[y0][x0] != target) {
                perimeter += 1
            }
            if (x0 < x && y0 < y && x0 >= 0 && y0 >= 0 && !visited[y0][x0] && input[y0][x0] == target) {
                visited[y0][x0] = true

                val right = dfs(x0+1, y0, target)
                val left = dfs(x0-1, y0, target)
                val up = dfs(x0, y0+1, target)
                val down = dfs(x0, y0-1, target)

                return listOf(right, left, up, down).fold(1 to 0){acc, pair ->
                    acc.first + pair.first to acc.second + pair.second
                }
            }

            return 0 to perimeter

        }

        var ans = 0
        for (i in 0 until y) {
            for (j in 0 until x) {
                val (x, y) = dfs(j, i, input[i][j])
                ans += x * y
            }
        }
        return ans.toLong()
    }

    fun part2(input: List<String>): Long {
        val y = input.size
        val x = input[0].length
        val visited = Array<BooleanArray>(y){BooleanArray(x) { false }}

        fun dfs(x0:Int, y0:Int, target:Char):Pair<Int, Int>{
           var perimeter = 0
           if (x0 < x && y0 < y && x0 >= 0 && y0 >= 0 && !visited[y0][x0] && input[y0][x0] == target) {
                visited[y0][x0] = true

                val right = dfs(x0+1, y0, target)
                val left = dfs(x0-1, y0, target)
                val up = dfs(x0, y0+1, target)
                val down = dfs(x0, y0-1, target)

                return listOf(right, left, up, down).fold(1 to 0){acc, pair ->
                    acc.first + pair.first to acc.second + pair.second
                }
            }

           return 0 to perimeter

        }

        var ans = 0
        for (i in 0 until y) {
            for (j in 0 until x) {
                val (x, y) = dfs(j, i, input[i][j])
                ans += x * y
            }
        }
        return ans.toLong()
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 1930L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 1206L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
