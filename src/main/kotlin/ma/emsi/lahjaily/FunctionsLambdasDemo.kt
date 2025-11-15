package ma.emsi.lahjaily

object FunctionsLambdasDemo {
    // Paramètre avec valeur par défaut; appel possible avec argument nommé
    private fun bonjour(qui: String = "Tout le monde"): String = "Bonjour $qui"

    // Fonction d'ordre supérieur: reçoit une lambda (op) comme paramètre
    private fun operate(a: Int, b: Int, op: (Int, Int) -> Int): Int = op(a, b)

    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Functions & Lambdas --")

        println(bonjour())
        println(bonjour(qui = "Kotlin")) // argument nommé pour la lisibilité

        // Lambda de type (Int) -> Int ; 'it' = paramètre implicite lorsqu'il y en a un seul
        val times2: (Int) -> Int = { it * 2 }
        println("times2(21) = ${times2(21)}")

        // Trailing lambda: la dernière lambda peut être écrite hors parenthèses
        val sum = operate(2, 3) { x, y -> x + y }
        println("sum = $sum")

        // Corps expression + récursion (type de retour explicite recommandé en récursion)
        fun fact(n: Int): Long = if (n <= 1) 1 else n * fact(n - 1)
        println("fact(5) = ${fact(5)}")
    }
}
