# Programmation Mobile et IA — Révision Kotlin (Partie I)

Auteur: Synthèse à partir des supports fournis
Promo: MIAGE 2025–2026

---

## Plan rapide
- Historique, objectifs, écosystème Kotlin
- Variables, types, Strings, `Unit`
- Null-safety (`?`, `?.`, `?:`, `!!`)
- Collections (List/MutableList, Array, Pair/Triple, Set/Map), Ranges
- Contrôle de flux (`if`, `when`, boucles, `repeat`)
- Fonctions (params par défaut, nommés, corps expression, lambdas, trailing lambda)
- Séquences, filtrage paresseux vs immédiat
- POO (classes, constructeurs, `init`, propriétés, visibilité, héritage, interfaces, abstraites)
- Extensions, Data classes, Enum, Singleton `object`, `companion object`
- Modules, fichiers, packages
- Surcharge d’opérateurs
- Fonctions de portée: `let`, `run`, `with`, `apply`, `also`
- Pièges et bonnes pratiques

---

## 1) Kotlin en bref
- Créé par JetBrains (2011), v1.0 (2016). Officiel Android (Google, 2017). Version actuelle évoquée: 2.2.20.
- Objectifs: productivité, concision, fonctionnalités modernes, interop Java, Android-first.
- Caractéristiques: OO + fonctionnel, typage statique avec inférence, null-safety, interop Java.
- Cibles: JVM, Android, serveur (Ktor), multiplateforme (iOS/Web).

---

## 2) Variables et types
- `val` immuable (référence immuable), `var` mutable. Un `val` peut référencer un objet mutable.
- Inférence de type: le compilateur déduit quand possible. Type obligatoire si non initialisé.
- Primitifs (immutables): `Int`, `Long`, `Short`, `Byte`, `Float`, `Double`, `Char`, `Boolean`.
- `Unit`: type de retour d’une fonction « sans retour » explicite.
- Strings: immuables, Unicode, échappements (`\n`), multilignes (`""" ... """`), concat `+`, interpolation `${expr}`.

Exemple:
```kotlin
val age = 22           // inféré: Int
var nom: String = "Leo"
val multi = """
    Bonjour,\n$nom
""".trimIndent()
val s = "Nom: ${nom.uppercase()}"
```

---

## 3) Null-safety
- Par défaut, une variable ne peut pas être `null`. Rendre nullable avec `T?`.
- Opérateurs:
  - `?.` appel sûr: évalue à `null` si receveur `null`.
  - `?:` Elvis: valeur de repli si `null`.
  - `!!` assertion non-nulle: peut lancer `NullPointerException` — à réserver aux cas sûrs/exceptionnels.

Exemples:
```kotlin
val x: String? = getNullable()
val len = x?.length ?: 0    // 0 si x == null
val must = x!!.length       // NPE si x == null
```

Bonnes pratiques:
- Préférer `?.` + `?:` aux `!!`.
- Modéliser le « peut-être absent » avec `T?` plutôt qu’avec sentinelles (p. ex. `-1`).

---

## 4) Collections et ranges
### Listes
- Immuables: `listOf(…)` → `List<T>`.
- Mutables: `mutableListOf(…)` → `MutableList<T>` (ajout/suppression/modif autorisés).

### Arrays
- `arrayOf(…)`, taille fixe, toujours mutables, éléments potentiellement hétérogènes.
- Opérateur `+` concatène deux `Array`.

### Paires et triples
- `Pair<A,B>` (immuable), accès `first`, `second`, conversion `toList()`.
- `Triple<A,B,C>` disponible également.

### Ensembles et dictionnaires
- `Set` (immuable) / `MutableSet` (mutable): unicité des éléments, non ordonné.
- Maps: `mapOf`, `mutableMapOf` (équivalents Kotlin aux `HashMap`, etc.).

### Ranges
- Intervalle contigu: `a..b` (inclusif), `a..<b` (exclusif). Utiles avec `in`/`!in`, `for`, `when`.
- `step` et `downTo` disponibles. `random()` pour un élément aléatoire.

Exemples:
```kotlin
val profs = listOf("Leo", "Sergey")
val m = mutableMapOf("k" to 1, "v" to 2)
val r1 = 1..5           // 1,2,3,4,5
val r2 = 0..<10 step 2  // 0,2,4,6,8
if (3 in r1) println("in range")
```

---

## 5) Contrôle de flux
### `if` expression
- Retourne une valeur: celle de la dernière expression du bloc choisi.

```kotlin
val grade = if (score >= 10) "OK" else "KO"
```

### `when` expression
- Plus expressif qu’un `switch`. Supporte constantes, ranges, conditions.

```kotlin
val note = when (score) {
    in 16..20 -> "A"
    in 14..15 -> "B"
    in 10..13 -> "C"
    else -> "D"
}
```

### Boucles
- `for` sur ranges/collections; `while`, `do-while` disponibles.
- `repeat(n) { ... }` pour répéter N fois sans indice.

---

## 6) Fonctions et lambdas
- Définition: `fun name(args): Retour { ... }`.
- Arguments:
  - Nommés (`param = valeur`), valeurs par défaut.
  - Placer d’abord les requis positionnels, puis les optionnels.
- Corps expression (concise) avec `=` quand une seule expression.
- Type de retour déduit, sauf en récursion (spécifier explicitement).

```kotlin
fun bonjour(qui: String = "Tout le monde"): String = "Bonjour $qui"
val msg = bonjour(qui = "Kotlin")
```

### Fonctions de première classe et lambdas
- Fonctions passables en paramètre/retour, stockables en variables.
- Lambda: `{ params -> body }`. Identifiant implicite `it` pour un seul param.
- Trailing lambda: dernière lambda hors des parenthèses d’appel.

```kotlin
val times2: (Int) -> Int = { it * 2 }
listOf(1,2,3).map { it * it }
```

---

## 7) Séquences et filtrage paresseux
- Collections: opérations « eager » (immédiates) par défaut.
- `asSequence()` active l’évaluation paresseuse (lazy) — utile pour grandes données, moins d’allocations intermédiaires; conversion finale `toList()` force l’évaluation.

```kotlin
val res = (1..1_000_000)
    .asSequence()
    .filter { it % 2 == 0 }
    .map { it * it }
    .take(5)
    .toList()           // évaluation forcée ici
```

---

## 8) Classes et objets (POO)
### Déclaration et constructeur principal
```kotlin
class Personne(val age: Int, var fullName: String) {
    init { require(age >= 0) }
}
// Pas de `new`
val p = Personne(22, "Ada Lovelace")
```

- Arguments sans `val/var` ne deviennent pas des propriétés.
- Bloc `init` pour l’initialisation.

### Constructeurs secondaires
```kotlin
class C(val a: Int) {
    constructor() : this(0) { /* code */ }
}
```

### Propriétés
- Liées aux champs, `val` (lecture seule) / `var` (lecture/écriture).
- Getters/setters customisables si nécessaire.

### Visibilité
- `public` (défaut), `private`, `protected` (héritage), `internal` (module).
- En fichier top-level: `private` limite au fichier, `internal` au module.

### Héritage, interfaces, classes abstraites
- Les classes sont `final` par défaut; marquer `open` pour autoriser l’héritage; utiliser `override` pour redéfinir.
- Interfaces: contrat de méthodes/propriétés, héritage multiple d’interfaces.
- Abstraites: `abstract` (non instanciables), membres `abstract` à surcharger.

```kotlin
open class A(open val x: Int)
class B(override val x: Int): A(x)

interface Clickable { fun click() }
abstract class Base { abstract fun run() }
```

### Extensions
- Ajouter des fonctions/propriétés à une classe sans héritage.

```kotlin
fun String.mots(): List<String> = this.trim().split(" ")
```

### Data classes
- `data class` génère `toString`, `equals`, `hashCode`, `copy`, destructuring.

```kotlin
data class Point(val x: Int, val y: Int)
val p2 = Point(1,2).copy(y = 3)
```

### Enums
```kotlin
enum class Role { ADMIN, USER, GUEST }
```

### Singleton et `companion object`
- `object` crée un singleton.
- `companion object` remplace les membres statiques (partagés par toutes les instances).

```kotlin
object Registry { val items = mutableListOf<String>() }
class Util { companion object { fun of(x: Int) = x * 2 } }
```

### Modules, fichiers, packages
- Pas de lien imposé fichier↔classe. Fonctions et variables top-level autorisées.
- `package` pour définir l’espace de noms; `import` pour importer.
- Module = module IntelliJ, module Maven/Gradle source set.

---

## 9) Surcharge d’opérateurs
- Déclarer avec `operator` dans la fonction correspondante (`plus`, `times`, …).

```kotlin
data class Vec(val x: Int, val y: Int) {
    operator fun plus(o: Vec) = Vec(x + o.x, y + o.y)
}
```

---

## 10) Fonctions de portée (scope functions)
Résumé d’usage:
- `let`: « prends l’objet, fais quelque chose, renvoie le résultat ». Référence: `it`.
- `run`: « exécute un bloc dans le contexte, renvoie le résultat ». Référence: `this`.
- `with(obj) { ... }`: idem `run` mais en forme fonctionnelle, renvoie le résultat.
- `apply`: configure l’objet et renvoie l’objet lui-même. Référence: `this` (optionnel d’écrire `this`).
- `also`: effets secondaires sur l’objet et renvoie l’objet original. Référence: `it`.

Exemples:
```kotlin
val s = "kotlin".let { it.uppercase() }              // renvoie String
val r = run { val t = 2; t * t }                      // renvoie 4
val msgLen = with("hey") { length + 1 }              // renvoie 4
val b = StringBuilder().apply { append("Hi ") }      // renvoie StringBuilder
val list = mutableListOf(1,2).also { it.add(3) }      // renvoie la liste
```

Bonnes pratiques:
- `apply` pour builder/configuration, `also` pour log/debug, `let` pour chaîner transformations, `with`/`run` pour grouper des appels.

---

## 11) Pièges & bonnes pratiques
- Éviter `!!` autant que possible.
- Préférer immutabilité (`val` + structures immuables) et transformations pures.
- Nommer clairement les paramètres avec valeurs par défaut; utiliser arguments nommés pour la lisibilité.
- Sur grosses collections, préférer `asSequence()` pour pipelines complexes; matérialiser en `toList()` seulement au besoin.
- Rappels Android: interop Java systématique, mais privilégier idiomes Kotlin (null-safety, data classes, extensions).

---

## 12) Révisions éclair (code snippets utiles)
```kotlin
// when avancé
val res = when {
    x == null -> 0
    x in 1..10 -> 1
    x % 2 == 0 -> 2
    else -> -1
}

// default + nommé
fun connect(url: String, timeoutMs: Long = 2_000, secure: Boolean = true) {}
connect("/api", secure = false)

// destructuring data class
val (vx, vy) = Vec(2, 3)

// extension + safe call + elvis
data class User(val email: String?)
fun User.domain(): String? = email?.substringAfter('@')
val dom = user.domain() ?: "unknown"
```

---

## 13) Pour aller plus loin
- Docs Kotlin: https://kotlinlang.org/docs/home.html
- Ktor serveur: https://ktor.io/
- Android + Kotlin: https://developer.android.com/kotlin
