fun main() {
    val day = 10

    fun part1(input: List<String>): Long {
        val pos = input.foldIndexed(mutableMapOf<Pair<Int,Int>,Int>()){
            i, acc, s ->
            s.foldIndexed(acc){
                j, acc2, e ->
                acc2[j to i] = e.digitToInt()
                acc2
            }
        }
        fun process(s:Pair<Int,Int>):Long{
            var set = mutableSetOf<Pair<Int,Int>>()
            fun dfs(p:Pair<Int,Int>, target:Int){
                if (p !in pos) return
                val v = pos[p]
                if (target == 9){
                    if (v == 9) set.add(p)
                }else{
                    if (v == target){
                        dfs(p.first to p.second + 1,v+1)
                        dfs(p.first to p.second - 1,v+1)
                        dfs(p.first + 1 to p.second,v+1)
                        dfs(p.first - 1 to p.second,v+1)
                    }
                }
            }

            dfs(s, 0)

            return set.size.toLong()
        }

        return pos.filter { (_,v) -> v == 0 }.map { it.key }.sumOf {
            process(it)
        }
    }

    fun part2(input: List<String>): Long {
       val pos = input.foldIndexed(mutableMapOf<Pair<Int,Int>,Int>()){
            i, acc, s ->
            s.foldIndexed(acc){
                j, acc2, e ->
                acc2[j to i] = e.digitToInt()
                acc2
            }
        }
        fun process(s:Pair<Int,Int>):Long{
            var ans: Long = 0
            fun dfs(p:Pair<Int,Int>, target:Int){
                if (p !in pos) return
                val v = pos[p]
                if (target == 9){
                    if (v == 9) ans++
                }else{
                    if (v == target){
                        dfs(p.first to p.second + 1,v+1)
                        dfs(p.first to p.second - 1,v+1)
                        dfs(p.first + 1 to p.second,v+1)
                        dfs(p.first - 1 to p.second,v+1)
                    }
                }
            }

            dfs(s, 0)

            return ans
        }

        return pos.filter { (_,v) -> v == 0 }.map { it.key }.sumOf {
            process(it)
        }
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 36L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 81L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
