package ma.emsi.lahjaily

/**
 * Démo des variables et types en Kotlin :
 * - Différence val/var (immutabilité de la référence vs mutabilité)
 * - Inférence de type vs type explicite
 * - Strings: concaténation, interpolation, chaînes multilignes
 * - Type Unit pour fonctions sans valeur de retour utile
 */
object VariablesTypesDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Variables & Types --")

        // val = référence immuable (ne peut pas repointer ailleurs)
        val immuable = 42
        // var = variable mutable (peut changer de valeur)
        var mutable = "Kotlin"
        println("val immuable=$immuable, var mutable=$mutable")
        // on peut modifier le contenu d'une var
        mutable = mutable.uppercase()
        println("mutable uppercased=$mutable")

        // Inférence de type: le compilateur déduit 'Double' pour pi
        val pi = 3.14159 // -> Double
        // Spécifier explicitement un type (utile lorsque non initialisé)
        val answer: Int = 42
        println("pi=$pi (${pi::class.simpleName}), answer=$answer")

        // Strings: interpolation `${expr}` et concaténation
        val name = "Leo"
        val hello = "Hello, $name! length=${name.length}"

        // Chaîne multilignes (triple quotes). Ici \n est un caractère dans la chaîne
        val multi = """
            Bonjour,\n$name
        """.trimIndent()
        println(hello)
        println(multi)

        // Unit = type de retour "vide" (équivalent void dans l'usage)
        fun log(msg: String): Unit { println("[LOG] $msg") }
        log("done")
    }
}
