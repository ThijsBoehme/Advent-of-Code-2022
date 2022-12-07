/*
Followed along with the Kotlin by JetBrains livestream, with some personal adjustments
 */

private data class Move(
    val quantity: Int,
    val source: Int,
    val target: Int,
) {
    companion object {
        fun of(line: String): Move {
            return line.split(" ")
                .filterIndexed { index, _ -> index % 2 == 1 }
                .map { it.toInt() }
                .let { Move(it[0], it[1] - 1, it[2] - 1) }
        }
    }
}

private class Day05 {
    fun numberOfStacks(lines: List<String>) =
        lines.dropWhile { it.contains("[") }
            .first()
            .split(" ")
            .filter { it.isNotBlank() }
            .maxOf { it.toInt() }

    fun populateStacks(lines: List<String>, onCharacterFound: (Int, Char) -> Unit) {
        lines.filter { it.contains("[") }
            .map { line ->
                line.mapIndexed { index, c ->
                    if (c.isLetter()) {
                        val stackNumber = index / 4
                        val value = line[index]
                        onCharacterFound(stackNumber, value)
                    }
                }
            }
    }
}

fun main() {
    fun parse(input: List<String>): Pair<List<ArrayDeque<Char>>, List<Move>> {
        val day05 = Day05()
        val numberOfStacks = day05.numberOfStacks(input)
        val stacks = List(numberOfStacks) { ArrayDeque<Char>() }

        day05.populateStacks(input) { stackNumber, value -> stacks[stackNumber].addLast(value) }

        val moves = input.filter { it.contains("move") }
            .map { Move.of(it) }
        return Pair(stacks, moves)
    }

    fun part1(input: List<String>): String {
        val (stacks, moves) = parse(input)

        moves.forEach { step ->
            repeat(step.quantity) {
                stacks[step.target].addFirst(stacks[step.source].removeFirst())
            }
        }

        return stacks.map { it.first() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val (stacks, moves) = parse(input)
        moves.forEach { step ->
            stacks[step.source].take(step.quantity)
                .asReversed()
                .forEach {
                    stacks[step.target].addFirst(it)
                    stacks[step.source].removeFirst()
                }
        }

        return stacks.map { it.first() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
