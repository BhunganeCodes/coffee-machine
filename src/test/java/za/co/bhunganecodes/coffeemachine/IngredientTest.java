package za.co.bhunganecodes.coffeemachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.coffeemachine.model.Ingredient;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link Ingredient}.
 *
 * Run with: mvn clean test -Dtest=IngredientTest
 */
@DisplayName("Ingredient")
class IngredientTest {

    private Ingredient milk;
    private Ingredient espresso;

    @BeforeEach
    void setUp() {
        milk     = new Ingredient("Milk", 150.0);
        espresso = new Ingredient("Espresso", 30.0);
    }

    // =========================================================================
    // Constructor & accessors
    // =========================================================================
    @Nested
    @DisplayName("Constructor and accessors")
    class ConstructorTests {

        @Test
        @DisplayName("name() returns the name supplied to the constructor")
        void name_returnsConstructorValue() {
            assertEquals("Milk", milk.name());
        }

        @Test
        @DisplayName("quantity() returns the quantity supplied to the constructor")
        void quantity_returnsConstructorValue() {
            assertEquals(150.0, milk.quantity(), 0.001);
        }

        @Test
        @DisplayName("Constructor stores name correctly for a second ingredient")
        void name_secondIngredient() {
            assertEquals("Espresso", espresso.name());
        }

        @Test
        @DisplayName("Constructor stores quantity correctly for a second ingredient")
        void quantity_secondIngredient() {
            assertEquals(30.0, espresso.quantity(), 0.001);
        }

        @Test
        @DisplayName("Zero quantity is valid and stored correctly")
        void quantity_zeroIsValid() {
            Ingredient water = new Ingredient("Water", 0.0);
            assertEquals(0.0, water.quantity(), 0.001);
        }
    }

    // =========================================================================
    // updateQuantity
    // =========================================================================
    @Nested
    @DisplayName("updateQuantity()")
    class UpdateQuantityTests {

        @Test
        @DisplayName("Updates quantity to a positive value")
        void updateQuantity_positive() {
            milk.updateQuantity(200.0);
            assertEquals(200.0, milk.quantity(), 0.001);
        }

        @Test
        @DisplayName("Updates quantity to zero (boundary)")
        void updateQuantity_zero() {
            milk.updateQuantity(0.0);
            assertEquals(0.0, milk.quantity(), 0.001);
        }

        @Test
        @DisplayName("Throws IllegalArgumentException for a negative quantity")
        void updateQuantity_negative_throwsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> milk.updateQuantity(-1.0));
        }

        @Test
        @DisplayName("Throws IllegalArgumentException for any negative value, not just -1")
        void updateQuantity_largeNegative_throwsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> milk.updateQuantity(-999.99));
        }

        @Test
        @DisplayName("Quantity is unchanged after a failed update")
        void updateQuantity_negative_doesNotChangeQuantity() {
            double original = milk.quantity();
            try {
                milk.updateQuantity(-5.0);
            } catch (IllegalArgumentException ignored) {}
            assertEquals(original, milk.quantity(), 0.001);
        }
    }

    // =========================================================================
    // toString
    // =========================================================================
    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("Returns '<name>: <quantity>ml' format")
        void toString_correctFormat() {
            assertEquals("Milk: 150.0ml", milk.toString());
        }

        @Test
        @DisplayName("toString reflects updated quantity")
        void toString_afterUpdate() {
            milk.updateQuantity(200.0);
            assertEquals("Milk: 200.0ml", milk.toString());
        }

        @Test
        @DisplayName("Works for Espresso ingredient")
        void toString_espresso() {
            assertEquals("Espresso: 30.0ml", espresso.toString());
        }
    }
}
