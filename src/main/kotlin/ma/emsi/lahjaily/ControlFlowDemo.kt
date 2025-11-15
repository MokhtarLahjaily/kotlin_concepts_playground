package ma.emsi.lahjaily

object ControlFlowDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Control Flow --")

        // if est une expression: renvoie une valeur
        val score = (0..20).random()
        val grade = if (score >= 10) "OK" else "KO"
        println("score=$score -> grade=$grade")

        // when expressif: valeurs, ranges, conditions
        val note = when (score) {
            in 16..20 -> "A"
            in 14..15 -> "B"
            in 10..13 -> "C"
            else -> "D"
        }
        println("score=$score -> note=$note")

        // for sur un range
        for (i in 1..5) print("$i ")
        println()

        // repeat répète n fois sans gérer d'indice
        repeat(3) { println("repeat #$it") }

        // Boucle avec index et valeur via withIndex()
        println("\n-- Boucle withIndex --")
        val pets = arrayOf("dog", "cat", "canary")
        for ((index, element) in pets.withIndex()) {
            println("Item at $index is $element")
        }
    }
}
