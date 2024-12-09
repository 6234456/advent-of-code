fun main() {
    val day = 9

    fun part1(input: List<String>): Long {
        val line = input.first().trim().flatMapIndexed { index, c ->
            val v = c.digitToInt()
            if (index % 2 == 0) {
                val id = index / 2
                //id.toString().repeat(v).toList().map { it.digitToInt()}
                MutableList<Int>(v){ id }
            }else
                buildList<Int?>(v, {
                    repeat(v){
                        add(null)
                    }
                })
            }.toMutableList()
        var left = 0
        var right = line.size - 1

        while (left < right) {
            while(line[left] != null) left++
            while(line[right] == null) right--
            if (right > left && line[right] != null && (null == line[left])) {
               line[left++] = line[right--]
            }
        }

        return line.take(right+1).mapIndexed { index, i ->
            index.toLong() * i!!.toLong()
        }.sum()
    }

    fun part2(input: List<String>): Long {
        val origin = (listOf(0) + (input[0].map { it.digitToInt() }.runningReduce { acc, i -> acc + i }))
        val blanks = input[0].filterIndexed { index, _ -> index % 2 == 1 }.map { MutableList<Int?>(it.digitToInt()){null} }
        val ans = input.first().trim().flatMapIndexed { index, c ->
            val v = c.digitToInt()
            if (index % 2 == 0) {
                val id = index / 2
                //id.toString().repeat(v).toList().map { it.digitToInt()}
                MutableList<Int>(v){ id }
            }else
                buildList<Int?>(v, {
                    repeat(v){
                        add(null)
                    }
                })
        }.toMutableList()
        val blankCount = blanks.map { it.size }.toMutableList()
        val digitCount =  input[0].filterIndexed { index, _ -> index % 2 == 0 }.map{ c -> c.digitToInt()}

        for (i in digitCount.size-1 downTo 0) {
            for (j in 0 until blankCount.size) {
               if(digitCount[i] <= blankCount[j]){
                   val startPosDigit = origin[i * 2]
                   val startPosBlank = origin[j * 2 + 2] - blankCount[j]

                   if (startPosDigit < startPosBlank){
                      break
                   }

                   for (p in startPosDigit until startPosDigit+digitCount[i]) {
                      ans[p] = null
                   }
                   for (p in startPosBlank until startPosBlank+digitCount[i]) {
                       ans[p] = i
                   }
                   blankCount[j] -= digitCount[i]
                   break
               }
            }
        }

       return ans.mapIndexed { index, i ->  if (i == null) 0L else (i * index).toLong() }.sum()
    }

    val testInput1 = readInput("Test${"%02d".format(day)}")
    check(part1(testInput1) == 1928L)

    val testInput2 = readInput("Test${"%02d".format(day)}")
    check(part2(testInput2) == 2858L)

    val input = readInput("Data${"%02d".format(day)}")
    part1(input).println()
    part2(input).println()
}
