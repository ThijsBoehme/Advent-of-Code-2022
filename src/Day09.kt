import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main() {
    data class Coordinate(var x: Double, var y: Double) {
        fun move(direction: String) {
            when (direction) {
                "D" -> y -= 1
                "U" -> y += 1
                "L" -> x -= 1
                "R" -> x += 1
                else -> error("invalid direction")
            }
        }
    }

    fun moveKnots(first: Coordinate, second: Coordinate) {
        if (max(abs(first.x - second.x), abs(first.y - second.y)) > 1) {
            second.x += sign(first.x - second.x)
            second.y += sign(first.y - second.y)
        }
    }

    fun part1(input: List<String>): Int {
        val head = Coordinate(0.0, 0.0)
        val tail = Coordinate(0.0, 0.0)

        val visitedCoordinates = mutableSetOf(tail.copy())

        input.forEach { line ->
            val (direction, amountString) = line.split(" ")

            repeat(amountString.toInt()) {
                head.move(direction)
                moveKnots(head, tail)
                visitedCoordinates.add(tail.copy())
            }
        }

        return visitedCoordinates.size
    }

    fun part2(input: List<String>): Int {
        val head = Coordinate(0.0, 0.0)
        val knot1 = Coordinate(0.0, 0.0)
        val knot2 = Coordinate(0.0, 0.0)
        val knot3 = Coordinate(0.0, 0.0)
        val knot4 = Coordinate(0.0, 0.0)
        val knot5 = Coordinate(0.0, 0.0)
        val knot6 = Coordinate(0.0, 0.0)
        val knot7 = Coordinate(0.0, 0.0)
        val knot8 = Coordinate(0.0, 0.0)
        val tail = Coordinate(0.0, 0.0)

        val visitedCoordinates = mutableSetOf(tail.copy())

        input.forEach { line ->
            val (direction, amountString) = line.split(" ")

            repeat(amountString.toInt()) {
                head.move(direction)

                moveKnots(head, knot1)
                moveKnots(knot1, knot2)
                moveKnots(knot2, knot3)
                moveKnots(knot3, knot4)
                moveKnots(knot4, knot5)
                moveKnots(knot5, knot6)
                moveKnots(knot6, knot7)
                moveKnots(knot7, knot8)
                moveKnots(knot8, tail)

                visitedCoordinates.add(tail.copy())
            }
        }

        return visitedCoordinates.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)

    val testInput2 = readInput("Day09_test2")
    check(part2(testInput2) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
