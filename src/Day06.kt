fun main() {
    fun distinctCharIndex(input: String, numDistinct: Int): Int {
        var buffer = ""
        for ((index, signal) in input.withIndex()) {
            if (buffer.length < numDistinct) {
                buffer += signal
            } else {
                buffer += signal
                buffer = buffer.removeRange(0,1)
                if( buffer.toSet().size == buffer.length) {
                    return index +1
                }
            }
        }
        return 0
    }
    fun part1(input: String): Int {
        return distinctCharIndex(input, 4)
    }

    fun part2(input: List<String>): Int {
        return distinctCharIndex(input[0], 14)
    }
    val testInput = readInput("Day06_test")
    check(part1(testInput[0]) == 7)

    val input = readInput("Day06")
    println(part1(input[0]))


    check(part2(testInput) == 19)
    println(part2(input))
}