package ma.emsi.lahjaily

object CollectionsDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Collections --")

        // List immuable (non modifiable)
        val profs = listOf("Leo", "Sergey", "Valentin")
        // MutableList: modifiable (ajout/suppression/modif)
        val mut = mutableListOf(1, 2, 3)
        mut += 4 // équivaut à add(4)
        println("profs=$profs, mut=$mut")

        // Array: taille fixe, éléments potentiellement hétérogènes; concat avec +
        val arr = arrayOf(1, "two", 3.0)
        val arr2 = arrayOf(4)
        println("arr+arr2 = ${(arr + arr2).joinToString()}")

        // Pair et Triple: immuables; accès via first/second, toList(), destructuring
        val pair = "k" to 1
        val triple = Triple(1, 2, 3)
        println("pair=(${pair.first}, ${pair.second}), triple=$triple")

        // Set: unicité des éléments (les doublons sont éliminés)
        val s = setOf(1, 2, 2, 3)
        // Map mutable: ajouter/mettre à jour via l'opérateur []
        val m = mutableMapOf("a" to 1, "b" to 2)
        m["c"] = 3
        println("set=$s, map=$m")

        // Ranges: .. inclusif, ..< exclusif; step pour l'incrément
        val r1 = 1..5
        val r2 = 0..<10 step 3
        println("r1=${r1.joinToString()} | r2=${r2.joinToString()}")
        println("3 in r1? ${3 in r1}")
    }
}
