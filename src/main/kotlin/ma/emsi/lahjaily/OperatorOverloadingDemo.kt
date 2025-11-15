package ma.emsi.lahjaily

data class Vec(val x: Int, val y: Int) {
    // Surcharge de l'opérateur + via 'operator fun plus'
    operator fun plus(o: Vec) = Vec(x + o.x, y + o.y)
}

object OperatorOverloadingDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Operator Overloading --")
        val a = Vec(1, 2)
        val b = Vec(3, 4)
        val c = a + b // appelle implicitement a.plus(b)
        println("$a + $b = $c")
    }
}
