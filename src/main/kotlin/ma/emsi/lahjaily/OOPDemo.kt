package ma.emsi.lahjaily

// 'open' permet l'héritage; par défaut les classes sont final
open class A(open val x: Int)
class B(override val x: Int) : A(x)

// Interface: contrat de méthodes/propriétés
interface Clickable { fun click(): String }
// Classe abstraite: non instanciable, force l'impl de membres abstract
abstract class Base { abstract fun run(): String }

class Impl : Base(), Clickable {
    override fun run() = "running"
    override fun click() = "clicked"
}

class Personne(val age: Int, var fullName: String) {
    // Bloc init exécuté après la construction (validation, initialisation)
    init { require(age >= 0) { "age >= 0" } }

    // Constructeur secondaire qui délègue au primaire via this(...)
    constructor() : this(0, "Unknown")
}

// Singleton: une seule instance pour l'application
object Registry { val items = mutableListOf<String>() }

class Util {
    // Visibilité: internal (module) et private (classe)
    internal val internalProp = "Visible dans le module"
    private val privateProp = "Visible dans la classe Util"
    fun exposePrivate(): String = privateProp

    // 'companion object' remplace les membres statiques à la Java
    companion object {
        fun of(x: Int) = x * 2
    }
}

object OOPDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- OOP: classes, inheritance, interfaces, abstract, object, companion --")
        val b = B(5)
        println("B.x=${b.x}")

        val o: Clickable = Impl()
        println(o.click())

        val p = Personne(22, "Ada Lovelace")
        println("Personne: ${p.fullName}, age=${p.age}")

        Registry.items += listOf("A", "B")
        println("Registry: ${Registry.items}")

        println("Util.of(21) = ${Util.of(21)}")

        println("\n-- Visibilité --")
        val u = Util()
        // internal: accessible dans le même module
        println("internalProp: ${u.internalProp}")
        // private: non accessible directement, exposé via une méthode
        println("private via method: ${u.exposePrivate()}")
    }
}
