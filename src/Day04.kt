import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val day = 4

    fun part1(input: List<String>): Long {
        val xLength = input[0].length
        val yLength = input.size
        var ans = 0L
        fun check(y: Int, x: Int) {
           if (input[y][x] != 'X') return
           // horizontal forwards
            if(x < xLength - 3 && input[y].slice(x+1 .. x+3) == "MAS") {
                ans ++
            }
            // horizontal backwards
            if(x >= 3 && input[y].slice(x-3..<x) == "SAM") {
                ans ++
            }
            // vertical forwards
            if(y < yLength - 3 && input.slice(y+1 .. y+3).map { it[x] }.joinToString(separator = "") == "MAS") {
                ans ++
            }
            // vertical backwards
            if(y >= 3 && input.slice(y-3..<y).map { it[x] }.joinToString(separator = "") == "SAM") {
                ans ++
            }

            //diagonal top left
            if(x >= 3 && y >= 3 &&
                input.slice(y-3 ..<y).mapIndexed { index, s ->  s[x-3+index] }.joinToString(separator = "")== "SAM"
                ) {
                ans ++
            }

            //diagonal top right
            if(x < xLength - 3 && y >= 3 &&
                input.slice(y-3 ..<y).mapIndexed { index, s ->  s[x+3-index] }.joinToString(separator = "")== "SAM"
            ) {
                ans ++
            }

            //diagonal bottom left
            if(x >= 3 && y < yLength - 3 &&
                input.slice(y+1 .. y+3).mapIndexed { index, s ->  s[x-1-index] }.joinToString(separator = "")== "MAS"
            ) {
                ans ++
            }

            //diagonal bottom right
            if(x < xLength - 3 && y < yLength - 3 &&
                input.slice(y+1 .. y+3).mapIndexed { index, s ->  s[x+1+index] }.joinToString(separator = "")== "MAS"
            ) {
                ans ++
            }
        }

        for (y in 0 until yLength) {
            // 2, 3, 3, 5, 8, 10
            for (x in 0 until xLength) {
                check(y, x)
            }
        }

        return ans
    }

    fun part2(input: List<String>): Long {
        val xLength = input[0].length
        val yLength = input.size
        var ans = 0L

        fun check(y: Int, x: Int) {
            if (input[y][x] != 'A') return
            val topLeft = input[y-1][x-1]
            val topRight = input[y-1][x+1]
            val bottomRight = input[y+1][x+1]
            val bottomLeft = input[y+1][x-1]

            if( ((topLeft == 'M' && bottomRight == 'S') || (topLeft == 'S' && bottomRight == 'M'))
                && (topLeft == bottomLeft || topLeft == topRight)
                && (bottomRight == bottomLeft || bottomRight == topRight)
            ) {
                ans ++
            }

        }

        for (y in 1 until yLength-1) {
            for (x in 1 until xLength-1) {
                check(y, x)
            }
        }

        return ans
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 18L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 9L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
