/*
Followed along with the Kotlin by JetBrains livestream, with some personal adjustments
 */

fun main() {
    lateinit var sizes: MutableList<Int>

    data class Tree(val name: String, var parent: Tree? = null) {
        var size: Int = 0
        val children: MutableList<Tree> = mutableListOf()
    }

    fun recursiveSizeOfDirectory(directory: Tree): Int {
        val size = directory.size + directory.children.sumOf { recursiveSizeOfDirectory(it) }
        sizes.add(size)
        return size
    }

    fun parseToTree(input: List<String>): Tree {
        val parsedInput = input.map { it.replace("$ ", "") }
            .filter { it != "ls" }

        val root = Tree("/")
        var currentDirectory = root

        parsedInput.forEach { line ->
            val (command, argument) = line.split(" ")
            when (command) {
                "cd" -> currentDirectory = when (argument) {
                    "/" -> root
                    ".." -> currentDirectory.parent!!
                    else -> currentDirectory.children.first { it.name == argument }
                }

                "dir" -> currentDirectory.children.add(Tree(argument, currentDirectory))

                else -> currentDirectory.size += command.toInt()
            }
        }
        return root
    }

    fun part1(input: List<String>): Int {
        val root = parseToTree(input)

        sizes = mutableListOf()
        recursiveSizeOfDirectory(root)
        return sizes.filter { it <= 100_000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val root = parseToTree(input)

        sizes = mutableListOf()
        recursiveSizeOfDirectory(root)
        return sizes.filter { it >= 30_000_000 - (70_000_000 - sizes.max()) }.min()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
