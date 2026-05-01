package za.co.bhunganecodes.coffeemachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.coffeemachine.model.Ingredient;
import za.co.bhunganecodes.coffeemachine.model.Order;
import za.co.bhunganecodes.coffeemachine.model.Order.OrderStatus;
import za.co.bhunganecodes.coffeemachine.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link Order}.
 *
 * Run with: mvn clean test -Dtest=OrderTest
 */
@DisplayName("Order")
class OrderTest {

    private Recipe latte;
    private Order order;

    @BeforeEach
    void setUp() {
        latte = new Recipe("Latte", new ArrayList<>(List.of(
                new Ingredient("Espresso", 30.0),
                new Ingredient("Milk", 150.0)
        )));
        order = new Order(1, "Alice", latte);
    }

    // =========================================================================
    // Constructor & accessors
    // =========================================================================
    @Nested
    @DisplayName("Constructor and accessors")
    class ConstructorTests {

        @Test
        @DisplayName("orderId() returns the ID supplied to the constructor")
        void orderId_returnsConstructorValue() {
            assertEquals(1, order.orderId());
        }

        @Test
        @DisplayName("customer() returns the customer name supplied to the constructor")
        void customer_returnsConstructorValue() {
            assertEquals("Alice", order.customer());
        }

        @Test
        @DisplayName("recipe() returns the Recipe supplied to the constructor")
        void recipe_returnsConstructorValue() {
            assertSame(latte, order.recipe());
        }

        @Test
        @DisplayName("Status defaults to PENDING after construction")
        void status_defaultsPending() {
            assertEquals(OrderStatus.PENDING, order.status());
        }

        @Test
        @DisplayName("Different order IDs are stored correctly")
        void orderId_multipleOrders() {
            Order order2 = new Order(42, "Bob", latte);
            assertEquals(42, order2.orderId());
        }
    }

    // =========================================================================
    // updateStatus()
    // =========================================================================
    @Nested
    @DisplayName("updateStatus()")
    class UpdateStatusTests {

        @Test
        @DisplayName("Updates status to IN_PROGRESS")
        void updateStatus_toInProgress() {
            order.updateStatus(OrderStatus.IN_PROGRESS);
            assertEquals(OrderStatus.IN_PROGRESS, order.status());
        }

        @Test
        @DisplayName("Updates status to COMPLETED")
        void updateStatus_toCompleted() {
            order.updateStatus(OrderStatus.COMPLETED);
            assertEquals(OrderStatus.COMPLETED, order.status());
        }

        @Test
        @DisplayName("Status transitions correctly: PENDING → IN_PROGRESS → COMPLETED")
        void updateStatus_fullLifecycle() {
            assertEquals(OrderStatus.PENDING, order.status());
            order.updateStatus(OrderStatus.IN_PROGRESS);
            assertEquals(OrderStatus.IN_PROGRESS, order.status());
            order.updateStatus(OrderStatus.COMPLETED);
            assertEquals(OrderStatus.COMPLETED, order.status());
        }

        @Test
        @DisplayName("Status can be set back to PENDING (no lifecycle enforcement)")
        void updateStatus_backToPending() {
            order.updateStatus(OrderStatus.IN_PROGRESS);
            order.updateStatus(OrderStatus.PENDING);
            assertEquals(OrderStatus.PENDING, order.status());
        }
    }

    // =========================================================================
    // OrderStatus enum
    // =========================================================================
    @Nested
    @DisplayName("OrderStatus enum")
    class OrderStatusEnumTests {

        @Test
        @DisplayName("Enum has exactly three values")
        void enum_hasThreeValues() {
            assertEquals(3, OrderStatus.values().length);
        }

        @Test
        @DisplayName("Enum contains PENDING")
        void enum_containsPending() {
            assertEquals(OrderStatus.PENDING, OrderStatus.valueOf("PENDING"));
        }

        @Test
        @DisplayName("Enum contains IN_PROGRESS")
        void enum_containsInProgress() {
            assertEquals(OrderStatus.IN_PROGRESS, OrderStatus.valueOf("IN_PROGRESS"));
        }

        @Test
        @DisplayName("Enum contains COMPLETED")
        void enum_containsCompleted() {
            assertEquals(OrderStatus.COMPLETED, OrderStatus.valueOf("COMPLETED"));
        }
    }

    // =========================================================================
    // toString()
    // =========================================================================
    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("Contains the order ID")
        void toString_containsOrderId() {
            assertTrue(order.toString().contains("1"),
                    "Expected orderId '1' in: " + order.toString());
        }

        @Test
        @DisplayName("Contains the customer name")
        void toString_containsCustomerName() {
            assertTrue(order.toString().contains("Alice"),
                    "Expected 'Alice' in: " + order.toString());
        }

        @Test
        @DisplayName("Contains the recipe name")
        void toString_containsRecipeName() {
            assertTrue(order.toString().contains("Latte"),
                    "Expected 'Latte' in: " + order.toString());
        }

        @Test
        @DisplayName("Contains the current status")
        void toString_containsStatus() {
            assertTrue(order.toString().contains("PENDING"),
                    "Expected 'PENDING' in: " + order.toString());
        }

        @Test
        @DisplayName("toString reflects updated status")
        void toString_updatedStatus() {
            order.updateStatus(OrderStatus.COMPLETED);
            assertTrue(order.toString().contains("COMPLETED"),
                    "Expected 'COMPLETED' after update in: " + order.toString());
        }
    }
}
