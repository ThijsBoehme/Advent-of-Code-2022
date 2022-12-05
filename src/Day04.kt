fun main() {
    fun parse(line: String): Triple<IntRange, IntRange, Set<Int>> {
        val (first, second) = line.split(',')
        val (firstMin, firstMax) = first.split('-')
        val (secondMin, secondMax) = second.split('-')
        val firstRange = firstMin.toInt()..firstMax.toInt()
        val secondRange = secondMin.toInt()..secondMax.toInt()
        val overlap = firstRange intersect secondRange
        return Triple(firstRange, secondRange, overlap)
    }

    fun part1(input: List<String>): Int {
        return input.count {
            val (firstRange, secondRange, overlap) = parse(it)
            overlap == firstRange.toSet() || overlap == secondRange.toSet()
        }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val (_, _, overlap) = parse(it)
            overlap.isNotEmpty()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
