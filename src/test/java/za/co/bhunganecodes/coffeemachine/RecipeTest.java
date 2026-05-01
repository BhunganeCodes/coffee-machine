package za.co.bhunganecodes.coffeemachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.coffeemachine.model.Ingredient;
import za.co.bhunganecodes.coffeemachine.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link Recipe}.
 *
 * Run with: mvn clean test -Dtest=RecipeTest
 */
@DisplayName("Recipe")
class RecipeTest {

    private Ingredient espresso;
    private Ingredient milk;
    private Recipe latte;

    @BeforeEach
    void setUp() {
        espresso = new Ingredient("Espresso", 30.0);
        milk     = new Ingredient("Milk", 150.0);
        latte    = new Recipe("Latte", new ArrayList<>(List.of(espresso, milk)));
    }

    // =========================================================================
    // Constructor & name()
    // =========================================================================
    @Nested
    @DisplayName("Constructor and name()")
    class ConstructorTests {

        @Test
        @DisplayName("name() returns the name supplied to the constructor")
        void name_returnsConstructorValue() {
            assertEquals("Latte", latte.name());
        }

        @Test
        @DisplayName("Constructs correctly with an empty ingredient list")
        void constructor_emptyList() {
            Recipe plain = new Recipe("Plain", new ArrayList<>());
            assertEquals("Plain", plain.name());
            assertTrue(plain.ingredients().isEmpty());
        }
    }

    // =========================================================================
    // ingredients() — defensive copy
    // =========================================================================
    @Nested
    @DisplayName("ingredients() — defensive copy")
    class IngredientsTests {

        @Test
        @DisplayName("Returns all ingredients initially provided")
        void ingredients_containsAll() {
            List<Ingredient> result = latte.ingredients();
            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Returned list contains the correct ingredient names")
        void ingredients_correctNames() {
            List<String> names = latte.ingredients()
                    .stream()
                    .map(Ingredient::name)
                    .toList();
            assertTrue(names.contains("Espresso"));
            assertTrue(names.contains("Milk"));
        }

        @Test
        @DisplayName("Modifying the returned list does NOT affect the internal list")
        void ingredients_defensiveCopy_externalMutationIgnored() {
            List<Ingredient> copy = latte.ingredients();
            copy.add(new Ingredient("Sugar", 5.0));
            // Internal list should still have only 2 ingredients
            assertEquals(2, latte.ingredients().size());
        }

        @Test
        @DisplayName("Mutating the original constructor list does NOT affect internal list")
        void ingredients_defensiveCopy_constructorListMutationIgnored() {
            List<Ingredient> originalList = new ArrayList<>(List.of(espresso));
            Recipe cappuccino = new Recipe("Cappuccino", originalList);
            originalList.add(milk); // mutate the list passed in
            // Internal list should still have only 1 ingredient
            assertEquals(1, cappuccino.ingredients().size());
        }
    }

    // =========================================================================
    // addIngredient()
    // =========================================================================
    @Nested
    @DisplayName("addIngredient()")
    class AddIngredientTests {

        @Test
        @DisplayName("Increases ingredient count by one")
        void addIngredient_increasesCount() {
            Ingredient sugar = new Ingredient("Sugar", 5.0);
            latte.addIngredient(sugar);
            assertEquals(3, latte.ingredients().size());
        }

        @Test
        @DisplayName("Added ingredient is present in the list")
        void addIngredient_ingredientIsPresent() {
            Ingredient vanilla = new Ingredient("Vanilla Syrup", 10.0);
            latte.addIngredient(vanilla);
            boolean found = latte.ingredients().stream()
                    .anyMatch(i -> i.name().equals("Vanilla Syrup"));
            assertTrue(found);
        }

        @Test
        @DisplayName("Can add multiple ingredients sequentially")
        void addIngredient_multipleAdds() {
            latte.addIngredient(new Ingredient("Caramel Syrup", 15.0));
            latte.addIngredient(new Ingredient("Cinnamon", 2.0));
            assertEquals(4, latte.ingredients().size());
        }
    }

    // =========================================================================
    // toString()
    // =========================================================================
    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("Starts with the recipe name")
        void toString_startsWithName() {
            assertTrue(latte.toString().startsWith("Latte"));
        }

        @Test
        @DisplayName("Contains each ingredient's toString representation")
        void toString_containsIngredients() {
            String result = latte.toString();
            assertTrue(result.contains("Espresso: 30.0ml"),
                    "Expected 'Espresso: 30.0ml' in: " + result);
            assertTrue(result.contains("Milk: 150.0ml"),
                    "Expected 'Milk: 150.0ml' in: " + result);
        }

        @Test
        @DisplayName("Each ingredient appears on its own line after the recipe name")
        void toString_multiLineFormat() {
            String result = latte.toString();
            String[] lines = result.split("\n");
            // First line is the recipe name; subsequent lines are ingredients
            assertTrue(lines.length >= 3,
                    "Expected at least 3 lines (name + 2 ingredients), got: " + lines.length);
        }
    }
}
