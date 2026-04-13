# Évaluateur d'expressions

Un moteur d’évaluation d’expressions mathématiques développé en Java, capable d’analyser et de calculer des expressions sous forme de chaînes de caractères tout en respectant la priorité des opérateurs.

---

## 🚀 Fonctionnalités

* Support des opérateurs de base : `+`, `-`, `*`, `/`
* Gestion des nombres négatifs
* Respect de la priorité des opérateurs
* Support des parenthèses `()`
* Support de l’exponentiation `^`
* Support de fonctions comme `sqrt()`
* Gestion des espaces dans les expressions

---

## 🧠 Architecture

Le projet est structuré en plusieurs composants :

* **Tokenizer** : transforme la chaîne de caractères en liste de tokens
* **Parser** : analyse les tokens via un parser récursif (recursive descent)
* **Calculator** : point d’entrée qui orchestre l’évaluation

---

## 📌 Exemple d’utilisation

```java
double result = Calculator.evaluate("2 + 3 * 4");
//System.out.println(result); // 14
```

---

## 🧪 Exécution des tests

```bash
mvn test
```

---

## ⚙️ Choix de conception

* Séparation des responsabilités (Tokenizer / Parser / Calculator)
* Implémentation d’un parser récursif pour plus de lisibilité et extensibilité
* Gestion des erreurs via des exceptions personnalisées
* Code structuré et maintenable

---

## ⚠️ Limitations

* Support limité aux opérations définies (extensible facilement)
* Pas d’interface utilisateur (console uniquement)

---
