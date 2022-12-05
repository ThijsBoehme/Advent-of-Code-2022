fun main() {
    fun valueOf(char: Char) =
        if (char.isUpperCase()) char.code - 'A'.code + 27
        else char.code - 'a'.code + 1

    fun part1(input: List<String>): Int {
        return input.sumOf { rucksack ->
            val firstCompartment = rucksack.take(rucksack.length / 2)
            val secondCompartment = rucksack.drop(rucksack.length / 2)
            val intersect = firstCompartment.asIterable().toSet() intersect secondCompartment.asIterable().toSet()
            check(intersect.size == 1)
            val char = intersect.first()
            valueOf(char)
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).sumOf { group ->
            val intersect = group[0].asIterable().toSet() intersect
                    group[1].asIterable().toSet() intersect
                    group[2].asIterable().toSet()
            check(intersect.size == 1)
            val char = intersect.first()
            valueOf(char)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
