package ma.emsi.lahjaily

object SequencesDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Sequences (lazy) --")

        // Eager: opérations appliquées immédiatement, résultat matérialisé
        val eager = (1..10).filter { it % 2 == 0 }.map { it * it }
        println("eager=$eager")

        // Lazy: asSequence() construit un pipeline évalué à la demande
        // toList() force l'évaluation terminale
        val lazy = (1..1000)
            .asSequence()
            .filter { it % 2 == 0 }
            .map { it * it }
            .take(5)
            .toList()
        println("lazy first 5 squares of even numbers=$lazy")
    }
}
