fun main() {
    fun isVisibleLeft(row: String, x: Int, value: Int): Boolean {
        var isVisible = true
        for (left in x -1 downTo 0 ) {
            if (row[left].toString().toInt() >= value) {
                isVisible = false
            }
        }
        return isVisible
    }
    fun isVisibleRight(row: String, x: Int, value: Int): Boolean {
        var isVisible = true
        for (right in x +1..row.length -1 ) {
            if (row[right].toString().toInt() >= value) {
                isVisible = false
            }
        }

        return isVisible
    }
    fun isVisibleTop(input: List<String>, x: Int, y: Int, value: Int): Boolean {
        var isVisible = true
        for (top in y -1 downTo 0) {
            if (input[top][x].toString().toInt() >= value) {
                isVisible = false
            }
        }
        return isVisible
    }

    fun isVisibleBottom(input: List<String>, x: Int, y: Int, value: Int): Boolean {
        var isVisible = true
        for (bottom in y +1..input.size -1) {
            if (input[bottom][x].toString().toInt() >= value) {
                isVisible = false
            }
        }
        return isVisible
    }
    fun part1(input: List<String>, size: Int): Int {
        var visibleCount = 0
        for((y, row) in input.withIndex()) {
            for ((x, value) in row.withIndex()) {
//                print(x.toString() + "," + y.toString() + "=" + value + "|")
                if( x == 0 || y == 0 || x == size - 1 || y == size - 1) {
                    visibleCount += 1
                } else if (isVisibleLeft(row, x, value.toString().toInt())) {
                    visibleCount += 1
                } else if (isVisibleRight(row, x, value.toString().toInt())) {
                    visibleCount += 1
                } else if (isVisibleTop(input, x, y, value.toString().toInt())) {
                    visibleCount += 1
                } else if (isVisibleBottom(input, x, y, value.toString().toInt())) {
                    visibleCount +=1
                }
            }
//            println()
        }
        println(visibleCount.toString() + "Part1")
        return visibleCount
    }

    fun scenicScoreLeft(row: String, x: Int, value: Int): Int {
        var score = 0
        for (left in x -1 downTo 0 ) {
            if (row[left].toString().toInt() < value) {
                score += 1
            }
            if (row[left].toString().toInt() >= value) {
                score += 1
                break
            }
        }
        return score
    }

    fun scenicScoreRight(row: String, x: Int, value: Int): Int {
        var score = 0
        for (right in x +1..row.length -1 ) {
            if (row[right].toString().toInt() < value) {
                score += 1
            }
            if (row[right].toString().toInt() >= value) {
                score += 1
                break
            }
        }
        return score
    }

    fun scenicScoreTop(input: List<String>, x: Int, y: Int, value: Int): Int {
        var score = 0
        for (top in y -1 downTo 0) {
            if (input[top][x].toString().toInt() < value) {
                score +=1
            }
            if (input[top][x].toString().toInt() >= value) {
                score += 1
                break
            }
        }
        return score
    }

    fun scenicScoreBottom(input: List<String>, x: Int, y: Int, value: Int): Int {
        var score = 0
        for (bottom in y +1..input.size -1) {
            if (input[bottom][x].toString().toInt() < value) {
                score +=1
            }
            if (input[bottom][x].toString().toInt() >= value) {
                score += 1
                break
            }
        }
        return score
    }

    fun part2(input: List<String>, size: Int): Int {
        var scenicScore = 0
        for((y, row) in input.withIndex()) {
            for ((x, value) in row.withIndex()) {
                if (x == 0 || y == 0 || x == size - 1 || y == size - 1) {
                    // do nothing
                } else {
                    val left = scenicScoreLeft(row, x, value.toString().toInt())
                    val right = scenicScoreRight(row, x, value.toString().toInt())
                    val top = scenicScoreTop(input, x, y, value.toString().toInt())
                    val bottom = scenicScoreBottom(input, x, y, value.toString().toInt())
                    if (x == 2 && y == 3) {
                        println(value)
                        println(top)
                        println(left)
                        println(bottom)
                        println(right)
                    }
                    val currentSenicScore = left * right * top * bottom
                    if (currentSenicScore > scenicScore) { scenicScore = currentSenicScore }
                }
            }
        }
        return scenicScore
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput, 5) == 21)

    val input = readInput("Day08")
    println(part1(input, 99))

    check(part2(testInput, 5) == 8)
    println(part2(input, 99))
}