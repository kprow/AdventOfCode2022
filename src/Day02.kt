fun main() {
    var scoreSum2 = 0
    fun part1(input: List<String>): Int {

        var scoreSum = 0
        scoreSum2 = 0
        var yours = YourHand.X
        var theirs = OpponentHand.A
        var result = YourResult.X
        for (game in input) {
            theirs = OpponentHand.valueOf(game[0].toString())
            yours = YourHand.valueOf(game[2].toString())
            result = YourResult.valueOf(game[2].toString())
            scoreSum += yours.score() + yours.playResult(theirs)
            scoreSum2 += result.resultValue() + result.playShapeValue(theirs)
        }
        return scoreSum
    }

    fun part2(input: List<String>): Int {
        part1(input)
        return scoreSum2
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))

    check(part2(testInput) == 12)
    println(part2(input))
}
enum class YourHand(val shape: String) {
    X("Rock") {
        override fun score() = 1
        override fun playResult(theirs: OpponentHand): Int {
            return when(theirs) {
                OpponentHand.A -> 3
                OpponentHand.B -> 0
                OpponentHand.C -> 6
            }
        }
    },
    Y("Paper"){
        override fun score() = 2
        override fun playResult(theirs: OpponentHand): Int {
            return when(theirs) {
                OpponentHand.A -> 6
                OpponentHand.B -> 3
                OpponentHand.C -> 0
            }
        }
    },
    Z("Scissors") {
        override fun score() = 3
        override fun playResult(theirs: OpponentHand): Int {
            return when(theirs) {
                OpponentHand.A -> 0
                OpponentHand.B -> 6
                OpponentHand.C -> 3
            }
        }
    };
    abstract fun score(): Int
    abstract fun playResult(theirs: OpponentHand): Int
}
enum class OpponentHand(val shape: String) {
    A("Rock"), B("Paper"), C("Scissors")
}
enum class YourResult(val result: String) {
    X("Lose") {
        override fun playShapeValue(theirs: OpponentHand): Int {
            return when(theirs) {
                OpponentHand.A -> YourHand.Z.score()
                OpponentHand.B -> YourHand.X.score()
                OpponentHand.C -> YourHand.Y.score()
            }
        }
        override fun resultValue() = 0
    },
    Y("Draw"){
        override fun playShapeValue(theirs: OpponentHand): Int {
            return when(theirs) {
                OpponentHand.A -> YourHand.X.score()
                OpponentHand.B -> YourHand.Y.score()
                OpponentHand.C -> YourHand.Z.score()
            }
        }
        override fun resultValue() = 3
    },
    Z("Win") {
        override fun playShapeValue(theirs: OpponentHand): Int {
            return when(theirs) {
                OpponentHand.A -> YourHand.Y.score()
                OpponentHand.B -> YourHand.Z.score()
                OpponentHand.C -> YourHand.X.score()
            }
        }
        override fun resultValue() = 6
    };
    abstract fun playShapeValue(theirs: OpponentHand): Int
    abstract fun resultValue(): Int
}