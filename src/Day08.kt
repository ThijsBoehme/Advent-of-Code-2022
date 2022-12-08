fun main() {
    fun part1(rows: List<String>): Int {
        val size = rows.size
        val grid = rows.map { it.toList().map { tree -> tree.digitToInt() } }
        return grid.mapIndexed { rowNumber, row ->
            row.mapIndexed { columnNumber, tree ->
                val visibleFromLeft = tree > (row.take(columnNumber).maxOrNull() ?: -1)
                val visibleFromRight = tree > (row.takeLast(size - columnNumber - 1).maxOrNull() ?: -1)
                val visibleFromTop = tree > (grid.take(rowNumber).maxOfOrNull { it[columnNumber] } ?: -1)
                val visibleFromBottom =
                    tree > (grid.takeLast(size - rowNumber - 1).maxOfOrNull { it[columnNumber] } ?: -1)
                visibleFromLeft
                        || visibleFromRight
                        || visibleFromTop
                        || visibleFromBottom
            }.count { it }
        }.sum()
    }

    fun part2(rows: List<String>): Int {
        val size = rows.size
        val grid = rows.map { it.toList().map { tree -> tree.digitToInt() } }
        return grid.mapIndexed { rowNumber, row ->
            row.mapIndexed { columnNumber, tree ->
                var viewDistanceLeft = if (columnNumber == 0) 0
                else row.take(columnNumber).takeLastWhile { it < tree }.size
                if (row.take(columnNumber).any { it >= tree }) viewDistanceLeft += 1

                var viewDistanceRight = if (columnNumber == size - 1) 0
                else row.takeLast(size - columnNumber - 1).takeWhile { it < tree }.size
                if (row.takeLast(size - columnNumber - 1).any { it >= tree }) viewDistanceRight += 1

                var viewDistanceTop = if (rowNumber == 0) 0
                else grid.take(rowNumber).takeLastWhile { it[columnNumber] < tree }.size
                if (grid.take(rowNumber).any { it[columnNumber] >= tree }) viewDistanceTop += 1

                var viewDistanceBottom = if (rowNumber == size - 1) 0
                else grid.takeLast(size - rowNumber - 1).takeWhile { it[columnNumber] < tree }.size
                if (grid.takeLast(size - rowNumber - 1).any { it[columnNumber] >= tree }) viewDistanceBottom += 1

                viewDistanceLeft * viewDistanceRight * viewDistanceTop * viewDistanceBottom
            }.max()
        }.max()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
