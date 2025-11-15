package ma.emsi.lahjaily

object Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        val demos = listOf(
            "Variables & Types" to { VariablesTypesDemo.main(emptyArray()) },
            "Null Safety" to { NullSafetyDemo.main(emptyArray()) },
            "Collections" to { CollectionsDemo.main(emptyArray()) },
            "Control Flow" to { ControlFlowDemo.main(emptyArray()) },
            "Functions & Lambdas" to { FunctionsLambdasDemo.main(emptyArray()) },
            "Sequences (lazy)" to { SequencesDemo.main(emptyArray()) },
            "OOP" to { OOPDemo.main(emptyArray()) },
            "Extensions, Data, Enum" to { ExtensionsDataEnumDemo.main(emptyArray()) },
            "Operator Overloading" to { OperatorOverloadingDemo.main(emptyArray()) },
            "Scope Functions" to { ScopeFunctionsDemo.main(emptyArray()) },
        )

        fun printMenu() {
            println("\n=== Kotlin Demos Launcher ===")
            demos.forEachIndexed { idx, (title, _) -> println("${idx + 1}. $title") }
            println("A. Run ALL")
            println("0. Exit")
            print("Select an option: ")
        }

        while (true) {
            printMenu()
            val input = readln().trim()
            when (input.uppercase()) {
                "0" -> return
                "A" -> {
                    demos.forEach { (_, run) ->
                        try { run() } catch (t: Throwable) { t.printStackTrace() }
                        println("\n--- Press Enter to continue ---")
                        readln()
                    }
                }
                else -> {
                    val num = input.toIntOrNull()
                    if (num == null || num !in 1..demos.size) {
                        println("Invalid choice: '$input'")
                    } else {
                        val (title, run) = demos[num - 1]
                        println("\n>>> Running: $title")
                        try { run() } catch (t: Throwable) { t.printStackTrace() }
                        println("\n--- Press Enter to return to menu ---")
                        readln()
                    }
                }
            }
        }
    }
}
