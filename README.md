# ☕ Coffee Machine — Java OOP Assessment

> **Package:** `za.co.bhunganecodes.coffeemachine`  
> **Assessment weight:** Implementation 60% · UML 25% · Long Question 15%  
> **Pass mark:** 60%

---

## Project overview

A Java simulation of a coffee machine that stores recipes and processes customer orders through a queue-based system. The project demonstrates **Encapsulation** and **Inheritance** as core OOP principles.

---

## Project structure

```
coffee-machine/
├── pom.xml
├── README.md
├── answers.txt
├── uml.pdf                          ← export your draw.io diagram here
└── src/
    ├── main/java/za/co/bhunganecodes/coffeemachine/
    │   ├── Main.java
    │   ├── model/
    │   │   ├── Ingredient.java      ← Step 1
    │   │   ├── Recipe.java          ← Step 2
    │   │   └── Order.java           ← Step 3
    │   └── service/
    │       ├── CoffeeMachine.java   ← Step 4 (abstract)
    │       ├── EspressoMachine.java ← Step 5
    │       └── DripMachine.java     ← Step 5
    └── test/java/za/co/bhunganecodes/coffeemachine/
        ├── IngredientTest.java
        ├── RecipeTest.java
        ├── OrderTest.java
        └── CoffeeMachineTest.java
```

---

## Getting started

### Prerequisites

| Tool | Minimum version |
|------|----------------|
| Java | 17 |
| Maven | 3.8+ |

### Clone and build

```bash
git clone <your-repo-url>
cd coffee-machine
mvn clean compile
```

### Run all tests

```bash
mvn clean test
```

### Run a single test class

```bash
mvn clean test -Dtest=IngredientTest
mvn clean test -Dtest=RecipeTest
mvn clean test -Dtest=OrderTest
mvn clean test -Dtest=CoffeeMachineTest
```

### Run the demo entry point

```bash
mvn compile exec:java -Dexec.mainClass="za.co.bhunganecodes.coffeemachine.Main"
```

---

## Implementation guide (TDD workflow)

This project follows **spec-driven development** and **TDD (Test-Driven Development)**. The tests are written first; your job is to make them pass by implementing the `TODO` steps in each class.

Work through the steps **in order** — later classes depend on earlier ones.

### Step 1 — `Ingredient`

`src/main/java/.../model/Ingredient.java`

- Declare private fields: `name` (String) and `quantity` (double)
- Implement the constructor, accessors, `updateQuantity(double)`, and `toString()`
- `updateQuantity` must throw `IllegalArgumentException` for negative values
- `toString` format: `"Milk: 150.0ml"`

```bash
mvn clean test -Dtest=IngredientTest
```

---

### Step 2 — `Recipe`

`src/main/java/.../model/Recipe.java`

- Declare private fields: `name` (String) and `ingredients` (List<Ingredient>)
- Constructor must store a **copy** of the incoming list — not the reference
- `ingredients()` must return a **defensive copy**
- Implement `addIngredient`, and `toString` (name + each ingredient on its own line)

```bash
mvn clean test -Dtest=RecipeTest
```

> **Why defensive copies?** If you store or return the original list reference, any caller can mutate your recipe's internal state silently. Defensive copies protect encapsulation.

---

### Step 3 — `Order`

`src/main/java/.../model/Order.java`

- The `OrderStatus` enum is already declared for you: `PENDING`, `IN_PROGRESS`, `COMPLETED`
- Declare private fields: `orderId`, `customerName`, `recipe`, `status`
- Constructor sets `status` to `PENDING`
- Implement all accessors and `updateStatus(OrderStatus)`

```bash
mvn clean test -Dtest=OrderTest
```

---

### Step 4 — `CoffeeMachine` (abstract class)

`src/main/java/.../service/CoffeeMachine.java`

- Declare private fields: `machineName`, `recipes` (HashMap), `orderQueue` (ArrayList), `orderCounter`
- `placeOrder` uses `++orderCounter` so the first order gets ID 1
- `processNextOrder` finds the first PENDING order → sets IN_PROGRESS → calls `brew()` → sets COMPLETED → returns it
- Return unmodifiable collections from `getAllRecipes()` and `orderQueue()`
- `brew(Order)` is abstract — subclasses implement it

---

### Step 5 — `EspressoMachine` & `DripMachine`

`src/main/java/.../service/EspressoMachine.java`  
`src/main/java/.../service/DripMachine.java`

- Both `extend CoffeeMachine`
- Constructor calls `super(machineName)`
- Each implements `brew(Order)` printing a machine-specific message:
  - Espresso: `"[machineName] brewing [recipeName] for [customerName] using high-pressure espresso extraction."`
  - Drip: `"[machineName] brewing [recipeName] for [customerName] using slow drip-filter method."`

```bash
mvn clean test -Dtest=CoffeeMachineTest
```

---

## Running all tests (final check)

```bash
mvn clean test
```

Your goal: **all tests green** before submission.

---

## UML class diagram

Produce your diagram using **draw.io only**. Export as `uml.pdf` and place it in the root of this project.

Your diagram must show:
- All **6 classes** with fields, types, and access modifiers (`+` / `-` / `#`)
- All **methods** with return types and parameters
- **Inheritance arrows** (EspressoMachine → CoffeeMachine, DripMachine → CoffeeMachine)
- **Association arrows** (Recipe → Ingredient, Order → Recipe, CoffeeMachine → Recipe, CoffeeMachine → Order)

---

## Long question

Answer the long question in `answers.txt`. Do not change the format of that file.

---

## Submission checklist

- [ ] All TODO comments replaced with working implementations
- [ ] `mvn clean compile` passes with no errors
- [ ] `mvn clean test` passes with all tests green
- [ ] `uml.pdf` placed in project root (exported from draw.io)
- [ ] `answers.txt` completed
- [ ] Project pushed to your submission repository
