import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val day = 8

    fun part1(input: List<String>): Long {
        val group:MutableMap<Char, MutableList<Pair<Int, Int>>> = input.foldIndexed(mutableMapOf()){
            y, acc, s ->
            s.foldIndexed(acc){ x, acc0, c ->
                if (c != '.'){
                    if (acc0.containsKey(c)){
                        acc0[c]!!.add(x to y)
                    }else{
                        acc0[c] = mutableListOf(x to y)
                    }
                }
                acc0
            }
        }

        val y_ = input.size
        val x_ = input[0].length

        fun getAntinodesLocation(p1: Pair<Int,Int>,p2: Pair<Int,Int>): Pair<Pair<Int,Int>,Pair<Int,Int>>{
            if (p1.second > p2.second || (p1.second == p2.second && p1.first > p2.first))
                { return getAntinodesLocation(p2, p1)}

            val delta = (p2.first - p1.first) to (p2.second - p1.second)

            return ((p1.first - delta.first) to (p1.second - delta.second)) to ((p2.first + delta.first) to (p2.second + delta.second))

        }

        val ans:MutableSet<Pair<Int,Int>> = mutableSetOf()

        fun add2Ans(p:Pair<Int,Int>){
            if(p.first in 0..<x_ && p.second >= 0 && p.second < y_){
                ans.add(p)
            }
        }

        group.values.forEach {
            l ->
            for (i in l.indices) {
                for (j in i+1 until l.size) {
                    val v = getAntinodesLocation(l[i], l[j])
                    add2Ans(v.first)
                    add2Ans(v.second)
                }
            }
        }

        return ans.size.toLong()
    }

    fun part2(input: List<String>): Long {
        val group:MutableMap<Char, MutableList<Pair<Int, Int>>> = input.foldIndexed(mutableMapOf()){
                y, acc, s ->
            s.foldIndexed(acc){ x, acc0, c ->
                if (c != '.'){
                    if (acc0.containsKey(c)){
                        acc0[c]!!.add(x to y)
                    }else{
                        acc0[c] = mutableListOf(x to y)
                    }
                }
                acc0
            }
        }

        val y_ = input.size
        val x_ = input[0].length

        val ans:MutableSet<Pair<Int,Int>> = mutableSetOf()

        fun getAntinodesLocation(p1: Pair<Int,Int>,p2: Pair<Int,Int>): List<Pair<Int,Int>>{
            if (p1.second > p2.second || (p1.second == p2.second && p1.first > p2.first))
            { return getAntinodesLocation(p2, p1)}

            val delta = (p2.first - p1.first) to (p2.second - p1.second)
            val ret:MutableList<Pair<Int, Int>> = mutableListOf()
            var times = 0
            while(true){
                val x0 = p1.first - delta.first * times
                val y0 = p1.second- delta.second* times
                if (x0 in 0..<x_ && y0 in 0..<y_){
                    ret.add(Pair(x0, y0))
                    times++
                }else{
                    break
                }
            }

            times = 0
            while(true){
                val x0 = p2.first + delta.first * times
                val y0 = p2.second + delta.second* times
                if (x0 in 0..<x_ && y0 in 0..<y_){
                    ret.add(Pair(x0, y0))
                    times++
                }else{
                    break
                }
            }

            return ret
        }



        group.values.forEach {
                l ->
            for (i in l.indices) {
                for (j in i+1 until l.size) {
                    val v = getAntinodesLocation(l[i], l[j])
                    ans.addAll(v)
                }
            }
        }

        return ans.size.toLong()
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 14L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 34L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
