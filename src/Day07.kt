fun main() {

    fun part1(input: List<String>): Int {
        var files = mutableListOf<File>()
        var subDirectories = mutableListOf<Directory>()
        for ((index, ls) in input.withIndex()) {
            val lsResult = ls.split(" ")
            if (lsResult[0].toIntOrNull() != null) {
               files += File(lsResult[1], lsResult[0].toInt(), index)
            }
            val dirFind = "dir"
            if (lsResult[0].startsWith(dirFind)) {
                subDirectories += Directory(lsResult[1], index)
            }
        }
        var directoryContents = mutableMapOf<String, MutableList<File>>()
        files.forEach {
            print(it.size)
            val dir = it.findDirectory(input)
            println(" " + it.name + " " + dir)
            val dirFiles = if (directoryContents[dir] == null) {
                directoryContents[dir] = arrayListOf(it)
            } else {
                directoryContents[dir]!! += it
            }
        }
        check(directoryContents["a"]!!.size == 3)
        check(directoryContents["e"]!!.size == 1)
        check(directoryContents["d"]!!.size == 4)
        check(subDirectories.size == 3)
        subDirectories.forEach { it.findParent(input) }

        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
class Directory(val name: String, val inputIndex: Int) {
    var parent: String = ""
    fun findParent(input: List<String>) {
        for (n in inputIndex downTo 0) {
            val findDirString = "$ cd "
            if (input[n].startsWith(findDirString)) {
                val cdCommand = input[n].split(findDirString)
                parent = cdCommand[1]
                return
            }
        }
    }
}
class File(val name: String, val size: Int, val inputIndex: Int) {
    fun findDirectory(input: List<String>): String {
        for (n in inputIndex downTo 0) {
            val findDirString = "$ cd "
            if (input[n].startsWith(findDirString)) {
                val cdCommand = input[n].split(findDirString)
                return cdCommand[1]
            }
        }
        return ""
    }
}