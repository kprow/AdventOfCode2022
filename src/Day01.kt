fun main() {
    var maxCalories = 0
    var currentCalories = 0
    var caloriesPerElf = arrayOf<Int>()

    fun part1(input: List<String>): Int {

        for (cal in input) {
            if (cal == "") {
                if (currentCalories > maxCalories) {
                    maxCalories = currentCalories
                }
                caloriesPerElf += currentCalories
                currentCalories = 0
            } else {
                currentCalories += cal.toInt()
            }
        }
        caloriesPerElf += currentCalories
        return maxCalories
    }

    fun part2(input: List<String>): Int {
        caloriesPerElf.sort()
        return caloriesPerElf.takeLast(3).sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
