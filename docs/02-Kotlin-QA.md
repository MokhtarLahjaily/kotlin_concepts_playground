# Kotlin — Q&A / Flashcards (Révision)

Format: Questions courtes avec réponses concises et exemples.

---

**Q1. Quelle différence entre `val` et `var` ?**

- `val`: référence immuable; `var`: mutable. Un `val` peut référencer un objet mutable.

**Q2. À quoi sert `Unit` ?**

- Type de retour d’une fonction sans valeur de retour utile.

**Q3. Comment rendre un type nullable ?**

- Ajouter `?` : `String?`. Accéder avec `?.`, fournir un défaut avec `?:`.

**Q4. Quand utiliser `!!` ?**

- Exceptionnellement lorsque l’on est absolument sûr du non-null; sinon risque de NPE.

**Q5. `listOf` vs `mutableListOf` ?**

- `listOf` immuable; `mutableListOf` modifiable (ajout/suppression/modif).

**Q6. Quand préférer `Array` à `List` ?**

- Taille fixe, accès indexé performant, compat interop Java spécifique.

**Q7. `Pair` et `Triple` servent à quoi ?**

- Grouper 2/3 valeurs immuables sans créer une classe dédiée; destructuring possible.

**Q8. Quelle différence entre `..` et `..<` ?**

- `..` inclusif; `..<` exclusif sur la borne supérieure.

**Q9. Exemple d’un `if` expression ?**

- `val msg = if (ok) "OK" else "KO"`.

**Q10. Quand préfèrer `when` ?**

- En remplacement lisible de suites de `if/else` sur valeurs/ranges/conditions.

**Q11. `repeat(n)` fait quoi ?**

- Répète un bloc `n` fois sans gérer d’indice.

**Q12. Paramètres par défaut et nommés — conseil ?**

- Mettre d’abord requis, puis optionnels avec noms clairs; appeler avec arguments nommés si nécessaire.

**Q13. Corps expression — exemple ?**

- `fun sq(n:Int) = n*n`

**Q14. Qu’est-ce qu’une lambda ?**

- Fonction anonyme: `{ params -> body }`, `it` pour un seul paramètre.

**Q15. Trailing lambda — exemple ?**

- `list.filter { it > 0 }` (lambda en dehors des parenthèses si dernier argument).

**Q16. Séquences — pourquoi ?**

- Évaluation paresseuse pour pipelines lourds, moins d’allocations; terminer par `toList()`.

**Q17. `open` et `override` — pourquoi ?**

- Classes/méthodes final par défaut; utiliser `open` pour hériter, `override` pour redéfinir.

**Q18. Différence interface vs classe abstraite ?**

- Interface: contrat; abstraite: base avec impl possibles et membres abstraits/états.

**Q19. Extension function — exemple ?**

- `fun String.isUpper(): Boolean = this == uppercase()`.

**Q20. Data class — avantages ?**

- Génère `toString`, `equals`, `hashCode`, `copy`, destructuring.

**Q21. `enum class` — cas d’usage ?**

- Ensemble fini de constantes nommées avec type sûr.

**Q22. `object` — pourquoi ?**

- Singleton, une seule instance globale.

**Q23. `companion object` remplace quoi ?**

- Les membres statiques à la Java; partagé entre toutes les instances.

**Q24. Visibilité `internal` — portée ?**

- Visible dans le module (Gradle source set / module IDE).

**Q25. Surcharge d’opérateurs — exemple ?**

- `operator fun plus(o:Vec) = Vec(x+o.x, y+o.y)`.

**Q26. `let` — renvoie quoi ?**

- Le résultat de la lambda; référence: `it`.

**Q27. `run` vs `with` ?**

- `run`: extension (this). `with(obj) {}`: fonction qui prend `obj`, renvoie résultat.

**Q28. `apply` vs `also` ?**

- `apply`: configure et renvoie l’objet (this). `also`: effets et renvoie l’objet (it).

**Q29. Piège courant avec null-safety ?**

- Abus de `!!` et mauvaise modélisation du nullable vs optionnel.

**Q30. Eager vs Lazy — différence pratique ?**

- Eager calcule immédiatement et matérialise; Lazy via `asSequence()` ne calcule qu’au besoin.

---

**Q31. Origine du nom « Kotlin » ?**

- Le langage est nommé d’après l’île de Kotlin, près de Saint‑Pétersbourg (JetBrains).

**Q32. Tailles des types primitifs (en bits) ?**

- `Byte`: 8, `Short`: 16, `Int`: 32, `Long`: 64, `Float`: 32, `Double`: 64, `Char`: 16 (Unicode), `Boolean`: 8.

**Q33. Collections Java et interopérabilité ?**

- Toutes les collections Java existent en Kotlin (interop totale): `HashMap`, `HashSet`, `LinkedList`, `ArrayList`, etc.

**Q34. Type d’un Array hétérogène ?**

- `arrayOf("Julien", 34, 3.14)` est de type `Array<Any>` (éléments de types différents → haut type `Any`).

**Q35. Récupérer index + élément dans une boucle ?**

- `for ((i, e) in list.withIndex()) { ... }` — destructuring de `IndexedValue`.

**Q36. `typealias` pour fonctions et références `::` ?**

- `typealias FonctionReelle = (Double) -> Double` pour nommer un type fonction. On peut passer des références: `::sin`, `::cos`.

**Q37. Propriétés calculées et setter personnalisé ?**

- Propriété calculée (sans backing field): `val isSquare get() = width == height` (calcul à chaque appel). Setter custom: `var area: Int set(value) { /* met à jour width/height, pas un champ area */ }`.

**Q38. Risque avec l’opérateur `!!` ?**

- Lève une `NullPointerException` si la valeur est `null`. À réserver aux cas exceptionnels où le non‑null est garanti.

