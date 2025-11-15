package ma.emsi.lahjaily

/**
 * Démo Null Safety:
 * - Types nullable avec `T?`
 * - Appel sûr `?.` et opérateur Elvis `?:`
 * - Assertion non-nulle `!!` (déconseillée sauf certitude)
 */
object NullSafetyDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Null Safety --")

        // Variable nullable: peut contenir une String ou null
        val mayBeNull: String? = if (System.currentTimeMillis() % 2L == 0L) "ok" else null

        // Appel sûr: si mayBeNull == null, l'expression à gauche renvoie null
        // Elvis `?:` fournit une valeur de repli (ici 0)
        
        val len = mayBeNull?.length ?: 0
        println("len=$len (from '$mayBeNull')")

        // `!!` convertit un type nullable en non-null en lançant NPE si la valeur est null
        // À n'utiliser que si on peut prouver que la valeur n'est jamais null ici
        try {
            val force = mayBeNull!!.length
            println("forced len=$force")
        } catch (e: NullPointerException) {
            println("NPE caught via '!!' — as expected when null")
        }
    }
}
