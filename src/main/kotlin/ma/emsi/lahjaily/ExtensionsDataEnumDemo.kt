package ma.emsi.lahjaily

// data class: génère equals, hashCode, toString, copy, destructuring
data class Point(val x: Int, val y: Int)

// enum: type sûr avec un ensemble fini de constantes
enum class Level { LOW, MEDIUM, HIGH }

// Extension function sur String
fun String.words(): List<String> = trim().split(" ")

object ExtensionsDataEnumDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Extensions, Data, Enum --")

        val p1 = Point(1, 2)
        // copy permet de dupliquer en changeant certaines propriétés
        val p2 = p1.copy(y = 3)
        println("p1=$p1 p2=$p2 equal? ${p1 == p2}")

        // when + ranges pour mapper un Int à une valeur d'enum
        val lev = when ((0..100).random()) {
            in 0..33 -> Level.LOW
            in 34..66 -> Level.MEDIUM
            else -> Level.HIGH
        }
        println("level=$lev")

        // Utilisation de l'extension définie plus haut
        val s = "hello kotlin world".words()
        println("words=$s")
    }
}
