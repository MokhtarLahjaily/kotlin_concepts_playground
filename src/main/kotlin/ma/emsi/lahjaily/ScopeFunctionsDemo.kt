package ma.emsi.lahjaily

object ScopeFunctionsDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        println("-- Scope functions: let, run, with, apply, also --")

        // let: 'it' est l'objet, renvoie le résultat de la lambda
        val len = "kotlin".let { it.uppercase() }.length
        println("let -> length=$len")

        // run: 'this' dans le contexte; renvoie le résultat de la lambda
        val result = run { val t = 2; t * t }
        println("run -> $result")

        // with(obj) { ... }: 'this' = obj; renvoie le résultat
        val msgLen = with("hey") { length + 1 }
        println("with -> $msgLen")

        // apply: configure l'objet et renvoie l'objet (this)
        val sb = StringBuilder().apply { append("Hi ").append("there") }
        println("apply -> $sb")

        // also: effets secondaires; renvoie l'objet (it)
        val list = mutableListOf(1, 2).also { it.add(3) }
        println("also -> $list")
    }
}
