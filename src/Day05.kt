fun main() {


    fun part1(input: List<String>, stackListLocation: Int): String {
        val stack = Stack(input[stackListLocation])
        for (i in 0..stackListLocation - 1) {
            val row = input[i]
            for (x in 1..row.length step 4) {
                val stackNum = ((x - 1) / 4) + 1
                if (row[x].isLetter()) {
                    stack.stacks[stackNum] = stack.stacks[stackNum] + row[x]
                }
            }
        }
        for (i in stackListLocation + 2 .. input.count() -1 ) {
            val moveInput = input[i].replace("move ", "").replace("from ", "").replace("to ", "")
            val move = moveInput.split(" ")
            val amount = move[0].toInt()
            val from = move[1].toInt()
            val to = move[2].toInt()

            for(char in 0..amount-1) {
                stack.stacks[to] = stack.stacks[from]!![char] + stack.stacks[to]!!
            }
            stack.stacks[from] = stack.stacks[from]!!.removeRange(0, amount)
//            println("---")
//            println(stack.stacks[1])
//            println(stack.stacks[2])
//            println(stack.stacks[3])
        }
        var topOfEachStack = ""
        for (current in stack.stackNumbers) {
            if (!stack.stacks[current.digitToInt()]!!.isBlank()) {
                topOfEachStack += stack.stacks[current.digitToInt()]!![0].toString()
            }
        }

        return topOfEachStack
    }

    fun part2(input: List<String>, stackListLocation: Int): String {
        val stack = Stack(input[stackListLocation])
        for (i in 0..stackListLocation - 1) {
            val row = input[i]
            for (x in 1..row.length step 4) {
                val stackNum = ((x - 1) / 4) + 1
                if (row[x].isLetter()) {
                    stack.stacks[stackNum] = stack.stacks[stackNum] + row[x]
                }
            }
        }
        for (i in stackListLocation + 2 .. input.count() -1 ) {
            val moveInput = input[i].replace("move ", "").replace("from ", "").replace("to ", "")
            val move = moveInput.split(" ")
            val amount = move[0].toInt()
            val from = move[1].toInt()
            val to = move[2].toInt()

            val rangeToMove = IntRange(0, amount-1)
            stack.stacks[to] = stack.stacks[from]!!.substring(rangeToMove) + stack.stacks[to]!!
            stack.stacks[from] = stack.stacks[from]!!.removeRange(rangeToMove)
//            println("---")
//            println(stack.stacks[1])
//            println(stack.stacks[2])
//            println(stack.stacks[3])
        }
        var topOfEachStack = ""
        for (current in stack.stackNumbers) {
            if (!stack.stacks[current.digitToInt()]!!.isBlank()) {
                topOfEachStack += stack.stacks[current.digitToInt()]!![0].toString()
            }
        }

        return topOfEachStack
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput, 3) == "CMZ")

    val input = readInput("Day05")
    println(part1(input, 8))

    check(part2(testInput, 3) == "MCD")
    println(part2(input, 8))
}
class Stack {
    var stackNumbers: String = ""
    var stacks = mutableMapOf<Int, String>()
    constructor(stackList: String) {
        stackNumbers = stackList.filter { it.isDigit() }
        for (stack in stackNumbers) {
            stacks[stack.digitToInt()] = ""
        }
    }
}
