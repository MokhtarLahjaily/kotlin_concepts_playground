plugins {
    kotlin("jvm") version "2.2.20"
}

group = "ma.emsi.lahjaily"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

// Reuse the main runtimeClasspath provided by Java/Kotlin plugin
val sourceSetsContainer = the<org.gradle.api.tasks.SourceSetContainer>()
val mainSourceSet = sourceSetsContainer.getByName("main")

fun registerRunTask(name: String, mainClassName: String, descriptionText: String) {
    tasks.register<org.gradle.api.tasks.JavaExec>(name) {
        group = "application"
        description = descriptionText
        classpath = mainSourceSet.runtimeClasspath
        mainClass.set(mainClassName)
        standardInput = System.`in`
    }
}

registerRunTask(
    name = "runVariablesTypesDemo",
    mainClassName = "ma.emsi.lahjaily.VariablesTypesDemo",
    descriptionText = "Run Variables & Types demo"
)
registerRunTask(
    name = "runNullSafetyDemo",
    mainClassName = "ma.emsi.lahjaily.NullSafetyDemo",
    descriptionText = "Run Null Safety demo"
)
registerRunTask(
    name = "runCollectionsDemo",
    mainClassName = "ma.emsi.lahjaily.CollectionsDemo",
    descriptionText = "Run Collections demo"
)
registerRunTask(
    name = "runControlFlowDemo",
    mainClassName = "ma.emsi.lahjaily.ControlFlowDemo",
    descriptionText = "Run Control Flow demo"
)
registerRunTask(
    name = "runFunctionsLambdasDemo",
    mainClassName = "ma.emsi.lahjaily.FunctionsLambdasDemo",
    descriptionText = "Run Functions & Lambdas demo"
)
registerRunTask(
    name = "runSequencesDemo",
    mainClassName = "ma.emsi.lahjaily.SequencesDemo",
    descriptionText = "Run Sequences (lazy) demo"
)
registerRunTask(
    name = "runOOPDemo",
    mainClassName = "ma.emsi.lahjaily.OOPDemo",
    descriptionText = "Run OOP demo"
)
registerRunTask(
    name = "runExtensionsDataEnumDemo",
    mainClassName = "ma.emsi.lahjaily.ExtensionsDataEnumDemo",
    descriptionText = "Run Extensions, Data, Enum demo"
)
registerRunTask(
    name = "runOperatorOverloadingDemo",
    mainClassName = "ma.emsi.lahjaily.OperatorOverloadingDemo",
    descriptionText = "Run Operator Overloading demo"
)
registerRunTask(
    name = "runScopeFunctionsDemo",
    mainClassName = "ma.emsi.lahjaily.ScopeFunctionsDemo",
    descriptionText = "Run Scope Functions demo"
)

registerRunTask(
    name = "runLauncher",
    mainClassName = "ma.emsi.lahjaily.Launcher",
    descriptionText = "Run interactive Kotlin demos launcher"
)