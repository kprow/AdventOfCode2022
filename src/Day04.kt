fun main() {
    class SectionAssignment {
        val start: Int
        val end: Int
        val sequence: IntArray
        constructor(assignment: String) {
            val startEnd = assignment.split("-")
            this.start = startEnd[0].toInt()
            this.end = startEnd[1].toInt()
            this.sequence = IntRange(start, end).step(1).toList().toIntArray()
        }
        fun contains(section: SectionAssignment): Boolean {
            if (start <= section.start && end >= section.end) {
                return true
            }
            return false
        }

        fun hasOverlap(section: SectionAssignment): Boolean {
            var bothSequences = sequence + section.sequence
            if (bothSequences.distinct().count() < sequence.count() + section.sequence.count()) {
                return true
            }
            return false
        }
    }
    fun part1(input: List<String>): Int {
        var fullyContainedCount = 0
        for (pair in input) {
            val assignmentStrings = pair.split(",")
            val first = SectionAssignment(assignmentStrings[0])
            val second = SectionAssignment(assignmentStrings[1])
            if (first.contains(second) || second.contains(first)) {
                fullyContainedCount += 1
            }
        }
        return fullyContainedCount
    }

    fun part2(input: List<String>): Int {
        var overlapCount = 0
        for (pair in input) {
            val assignmentStrings = pair.split(",")
            val first = SectionAssignment(assignmentStrings[0])
            val second = SectionAssignment(assignmentStrings[1])
            if (first.hasOverlap(second)) {
                overlapCount += 1
            }
        }
        return overlapCount
    }
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)


    val input = readInput("Day04")
    println(part1(input))

    check(part2(testInput) == 4)
    println(part2(input))
}