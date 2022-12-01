import java.io.File

fun main() {
    fun sumOfMostWeightCarriedByNElves(input: String, n: Int) = input
        .split("\n\n")
        .map { it.split("\n").map { calories -> calories.toInt() } }
        .sortedByDescending { it.sum() }
        .take(n)
        .sumOf { it.sum() }

    fun part1(input: String) = sumOfMostWeightCarriedByNElves(input, 1)

    fun part2(input: String) = sumOfMostWeightCarriedByNElves(input, 3)

    fun readInput(name: String) = File("src", "$name.txt").readText().trim()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
