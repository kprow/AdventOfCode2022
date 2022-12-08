fun main() {

    fun part1(input: List<String>): Int {
        var files = mutableListOf<File>()
        var dirs = mutableListOf<Directory>()
        for ((index, ls) in input.withIndex()) {
            val lsResult = ls.split(" ")
            if (lsResult[0].toIntOrNull() != null) {
               files += File(lsResult[1], lsResult[0].toInt(), index)
            }
            if (lsResult[1] == "cd" && lsResult[2] != "..") {
                dirs += Directory(lsResult[2], index)
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
//        check(directoryContents["a"]!!.size == 3)
//        check(directoryContents["e"]!!.size == 1)
//        check(directoryContents["d"]!!.size == 4)
//        check(dirs.size == 4)
        dirs.forEach { it.findChild(input) }
        val dirsMap = dirs.associateBy(keySelector = {it.name}, valueTransform = {it})
        var dirSize = mutableMapOf<String, Int>()

        for (dir in dirs) {
            val size = dir.findDirSize(dirsMap, directoryContents)
            dirSize[dir.name] = size
        }

        val eSize = dirs[2].findDirSize(dirsMap, directoryContents)
        check(eSize == 584)
        val aSize = dirs[1].findDirSize(dirsMap, directoryContents)
        check(aSize == 94853)

        var sumOfDirSizeUnderTenK = 0
        for ((dirName, size) in dirSize) {
            if (size < 100000) {
                sumOfDirSizeUnderTenK += size
            }
        }

        return sumOfDirSizeUnderTenK
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
    var children = mutableListOf<String>()
    fun findChild(input: List<String>) {
        for (n in inputIndex + 1..input.size - 1) {
            val endDirContentsFind = "$ cd "
            if (input[n].startsWith(endDirContentsFind)) {
                return
            } else {
                val dirFind = "dir "
                if (input[n].startsWith(dirFind)) {
                    children += input[n].split(dirFind)[1]
                }
            }
        }
    }
    fun findDirSize(map: Map<String, Directory>, dirContents: Map<String, List<File>>): Int {
        if (map[name]!!.children.isEmpty()) {
            return dirContents[name]!!.sumOf { it.size }
        } else {
            val childDirs = map[name]!!.children.map { map[it]!! }
            return childDirs.sumOf { it.findDirSize(map, dirContents) } + dirContents[name]!!.sumOf { it.size }
        }
        return 0
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