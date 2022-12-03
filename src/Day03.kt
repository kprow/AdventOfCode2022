fun main() {
    fun priority(fromChar: Char): Int {
        return if (fromChar.isLowerCase()) {
            fromChar.code - 96
        } else {
            fromChar.code - 38
        }
    }
    fun part1(input: List<String>): Int {
        var commonItems = arrayOf<Char>()
        var sumOfPriorities = 0
        for (contentsOfRucksack in input) {
            val compartments = contentsOfRucksack.chunked(contentsOfRucksack.length / 2)
            for(item in compartments[0]) {
                if (compartments[1].contains(item)) {
                    commonItems += item
                    break
                }
            }
        }
        for (item in commonItems) {
            sumOfPriorities += priority(item)
        }
        println(commonItems.contentToString())

        return sumOfPriorities
    }

    fun part2(input: List<String>): Int {
        var badges = arrayOf<Char>()
        var sumOfPriorities = 0
        val groups = input.chunked(3)
        for (group in groups) {
            for (item in group[0]) {
                if (group[1].contains(item) && group[2].contains(item)) {
                    badges += item
                    break
                }
            }
        }
        for (item in badges) {
            sumOfPriorities += priority(item)
        }
        return sumOfPriorities
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    println("TEST Passed 157 for test input.")

    val input = readInput("Day03")
    println("Day 3 Part 1: " + part1(input))
    check(part2(testInput) == 70)
    println("TEST Passed 70 for test input.")
    println("Day 3 Part 2: " + part2(input))
}