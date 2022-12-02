fun main() {
    fun scoreOfShape(shape: String): Int =
        when (shape) {
            "A", "X" -> 1
            "B", "Y" -> 2
            "C", "Z" -> 3
            else -> error("Unknown shape")
        }

    fun scoreOfOutcome(outcome: String): Int =
        when (outcome) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> error("Unknown result")
        }

    fun part1(input: List<String>) = input.sumOf {
        val (opponent, you) = it.split(" ")
        val scoreOfShape = scoreOfShape(you)
        val scoreOfOutcome = when (scoreOfShape(you) - scoreOfShape(opponent)) {
            -1, 2 -> 0
            0 -> 3
            1, -2 -> 6
            else -> error("Unknown result")
        }
        return@sumOf scoreOfShape + scoreOfOutcome
    }

    fun part2(input: List<String>) = input.sumOf {
        val (opponent, outcome) = it.split(" ")
        val scoreOfOutcome = scoreOfOutcome(outcome)
        val scoreOfShape = when (scoreOfOutcome) {
            0 -> (scoreOfShape(opponent) + 1) % 3 + 1
            3 -> scoreOfShape(opponent)
            6 -> scoreOfShape(opponent) % 3 + 1
            else -> error("Unknown outcome")
        }
        return@sumOf scoreOfShape + scoreOfOutcome
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
