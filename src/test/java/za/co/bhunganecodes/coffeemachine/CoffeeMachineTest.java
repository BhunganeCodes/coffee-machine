package za.co.bhunganecodes.coffeemachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.coffeemachine.model.Ingredient;
import za.co.bhunganecodes.coffeemachine.model.Order;
import za.co.bhunganecodes.coffeemachine.model.Order.OrderStatus;
import za.co.bhunganecodes.coffeemachine.model.Recipe;
import za.co.bhunganecodes.coffeemachine.service.CoffeeMachine;
import za.co.bhunganecodes.coffeemachine.service.DripMachine;
import za.co.bhunganecodes.coffeemachine.service.EspressoMachine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link CoffeeMachine}, {@link EspressoMachine}, and {@link DripMachine}.
 * <p>
 * Because {@code CoffeeMachine} is abstract, it is tested through its concrete subclasses.
 * </p>
 *
 * Run with: mvn clean test -Dtest=CoffeeMachineTest
 */
@DisplayName("CoffeeMachine (via EspressoMachine & DripMachine)")
class CoffeeMachineTest {

    private EspressoMachine espresso;
    private DripMachine drip;
    private Recipe latte;
    private Recipe filterCoffee;

    @BeforeEach
    void setUp() {
        espresso = new EspressoMachine("Barista Pro 3000");
        drip     = new DripMachine("Morning Brew Station");

        latte = new Recipe("Latte", new ArrayList<>(List.of(
                new Ingredient("Espresso", 30.0),
                new Ingredient("Milk", 150.0)
        )));

        filterCoffee = new Recipe("Filter Coffee", new ArrayList<>(List.of(
                new Ingredient("Ground Coffee", 15.0),
                new Ingredient("Water", 250.0)
        )));
    }

    // =========================================================================
    // machineName()
    // =========================================================================
    @Nested
    @DisplayName("machineName()")
    class MachineNameTests {

        @Test
        @DisplayName("EspressoMachine returns its name correctly")
        void machineName_espresso() {
            assertEquals("Barista Pro 3000", espresso.machineName());
        }

        @Test
        @DisplayName("DripMachine returns its name correctly")
        void machineName_drip() {
            assertEquals("Morning Brew Station", drip.machineName());
        }
    }

    // =========================================================================
    // Inheritance
    // =========================================================================
    @Nested
    @DisplayName("Inheritance structure")
    class InheritanceTests {

        @Test
        @DisplayName("EspressoMachine is an instance of CoffeeMachine")
        void espresso_isInstanceOfCoffeeMachine() {
            assertInstanceOf(CoffeeMachine.class, espresso);
        }

        @Test
        @DisplayName("DripMachine is an instance of CoffeeMachine")
        void drip_isInstanceOfCoffeeMachine() {
            assertInstanceOf(CoffeeMachine.class, drip);
        }
    }

    // =========================================================================
    // addRecipe() & getRecipe()
    // =========================================================================
    @Nested
    @DisplayName("addRecipe() and getRecipe()")
    class RecipeCatalogueTests {

        @Test
        @DisplayName("getRecipe() returns the recipe after it has been added")
        void getRecipe_afterAdd() {
            espresso.addRecipe(latte);
            assertNotNull(espresso.getRecipe("Latte"));
        }

        @Test
        @DisplayName("getRecipe() returns the correct Recipe object")
        void getRecipe_correctObject() {
            espresso.addRecipe(latte);
            assertSame(latte, espresso.getRecipe("Latte"));
        }

        @Test
        @DisplayName("getRecipe() returns null for an unknown recipe name")
        void getRecipe_unknownName_returnsNull() {
            assertNull(espresso.getRecipe("Cappuccino"));
        }

        @Test
        @DisplayName("Multiple recipes can be added and retrieved independently")
        void addRecipe_multiple() {
            espresso.addRecipe(latte);
            espresso.addRecipe(filterCoffee);
            assertSame(latte,         espresso.getRecipe("Latte"));
            assertSame(filterCoffee,  espresso.getRecipe("Filter Coffee"));
        }
    }

    // =========================================================================
    // getAllRecipes()
    // =========================================================================
    @Nested
    @DisplayName("getAllRecipes()")
    class GetAllRecipesTests {

        @Test
        @DisplayName("Returns empty map when no recipes have been added")
        void getAllRecipes_empty() {
            assertTrue(espresso.getAllRecipes().isEmpty());
        }

        @Test
        @DisplayName("Returns map with correct number of entries")
        void getAllRecipes_count() {
            espresso.addRecipe(latte);
            espresso.addRecipe(filterCoffee);
            assertEquals(2, espresso.getAllRecipes().size());
        }

        @Test
        @DisplayName("Returned map is unmodifiable")
        void getAllRecipes_unmodifiable() {
            espresso.addRecipe(latte);
            assertThrows(UnsupportedOperationException.class,
                    () -> espresso.getAllRecipes().put("Hack", latte));
        }
    }

    // =========================================================================
    // placeOrder()
    // =========================================================================
    @Nested
    @DisplayName("placeOrder()")
    class PlaceOrderTests {

        @BeforeEach
        void addRecipe() {
            espresso.addRecipe(latte);
        }

        @Test
        @DisplayName("Returns a non-null Order")
        void placeOrder_returnsOrder() {
            assertNotNull(espresso.placeOrder("Alice", "Latte"));
        }

        @Test
        @DisplayName("Returned order has status PENDING")
        void placeOrder_statusPending() {
            Order o = espresso.placeOrder("Alice", "Latte");
            assertEquals(OrderStatus.PENDING, o.status());
        }

        @Test
        @DisplayName("Returned order has the correct customer name")
        void placeOrder_correctCustomer() {
            Order o = espresso.placeOrder("Alice", "Latte");
            assertEquals("Alice", o.customer());
        }

        @Test
        @DisplayName("Returned order references the correct recipe")
        void placeOrder_correctRecipe() {
            Order o = espresso.placeOrder("Alice", "Latte");
            assertEquals("Latte", o.recipe().name());
        }

        @Test
        @DisplayName("First order has ID 1")
        void placeOrder_firstId_isOne() {
            Order o = espresso.placeOrder("Alice", "Latte");
            assertEquals(1, o.orderId());
        }

        @Test
        @DisplayName("Order IDs increment with each placed order")
        void placeOrder_incrementingIds() {
            Order o1 = espresso.placeOrder("Alice", "Latte");
            Order o2 = espresso.placeOrder("Bob",   "Latte");
            Order o3 = espresso.placeOrder("Carol",  "Latte");
            assertEquals(1, o1.orderId());
            assertEquals(2, o2.orderId());
            assertEquals(3, o3.orderId());
        }

        @Test
        @DisplayName("Order is added to the queue after placement")
        void placeOrder_addedToQueue() {
            espresso.placeOrder("Alice", "Latte");
            assertEquals(1, espresso.orderQueue().size());
        }

        @Test
        @DisplayName("Throws IllegalArgumentException for unknown recipe")
        void placeOrder_unknownRecipe_throwsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> espresso.placeOrder("Alice", "Cappuccino"));
        }
    }

    // =========================================================================
    // orderQueue()
    // =========================================================================
    @Nested
    @DisplayName("orderQueue()")
    class OrderQueueTests {

        @Test
        @DisplayName("Queue is empty before any orders are placed")
        void orderQueue_initiallyEmpty() {
            assertTrue(espresso.orderQueue().isEmpty());
        }

        @Test
        @DisplayName("Queue is unmodifiable")
        void orderQueue_unmodifiable() {
            espresso.addRecipe(latte);
            espresso.placeOrder("Alice", "Latte");
            Order dummy = new Order(99, "Hack", latte);
            assertThrows(UnsupportedOperationException.class,
                    () -> espresso.orderQueue().add(dummy));
        }
    }

    // =========================================================================
    // processNextOrder()
    // =========================================================================
    @Nested
    @DisplayName("processNextOrder()")
    class ProcessNextOrderTests {

        @BeforeEach
        void addRecipe() {
            espresso.addRecipe(latte);
        }

        @Test
        @DisplayName("Returns null when there are no pending orders")
        void processNextOrder_noPending_returnsNull() {
            assertNull(espresso.processNextOrder());
        }

        @Test
        @DisplayName("Returns the processed order (not null)")
        void processNextOrder_returnsOrder() {
            espresso.placeOrder("Alice", "Latte");
            assertNotNull(espresso.processNextOrder());
        }

        @Test
        @DisplayName("Processed order has status COMPLETED")
        void processNextOrder_statusCompleted() {
            Order o = espresso.placeOrder("Alice", "Latte");
            espresso.processNextOrder();
            assertEquals(OrderStatus.COMPLETED, o.status());
        }

        @Test
        @DisplayName("Processes orders in FIFO order")
        void processNextOrder_fifoOrder() {
            Order o1 = espresso.placeOrder("Alice", "Latte");
            Order o2 = espresso.placeOrder("Bob",   "Latte");

            Order processed = espresso.processNextOrder();
            assertEquals(o1.orderId(), processed.orderId());
        }

        @Test
        @DisplayName("Second call processes the second order")
        void processNextOrder_secondCall_processesSecondOrder() {
            espresso.placeOrder("Alice", "Latte");
            Order o2 = espresso.placeOrder("Bob", "Latte");

            espresso.processNextOrder();
            Order processed = espresso.processNextOrder();
            assertEquals(o2.orderId(), processed.orderId());
        }

        @Test
        @DisplayName("Returns null after all orders are processed")
        void processNextOrder_allDone_returnsNull() {
            espresso.placeOrder("Alice", "Latte");
            espresso.processNextOrder();
            assertNull(espresso.processNextOrder());
        }

        @Test
        @DisplayName("Skips already-completed orders and returns null when none are pending")
        void processNextOrder_skipsCompleted() {
            espresso.placeOrder("Alice", "Latte");
            espresso.processNextOrder(); // completes it
            // No more pending → null
            assertNull(espresso.processNextOrder());
        }
    }

    // =========================================================================
    // EspressoMachine brew() output
    // =========================================================================
    @Nested
    @DisplayName("EspressoMachine — brew() output")
    class EspressoBrewOutputTests {

        @Test
        @DisplayName("brew() prints the correct espresso extraction message")
        void brew_espressoMessage() {
            espresso.addRecipe(latte);
            espresso.placeOrder("Alice", "Latte");

            // Capture stdout
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            espresso.processNextOrder();
            System.setOut(System.out);

            String output = out.toString().trim();
            assertTrue(output.contains("Barista Pro 3000"),
                    "Expected machine name in output: " + output);
            assertTrue(output.contains("Latte"),
                    "Expected recipe name in output: " + output);
            assertTrue(output.contains("Alice"),
                    "Expected customer name in output: " + output);
            assertTrue(output.contains("high-pressure espresso extraction"),
                    "Expected extraction method in output: " + output);
        }
    }

    // =========================================================================
    // DripMachine brew() output
    // =========================================================================
    @Nested
    @DisplayName("DripMachine — brew() output")
    class DripBrewOutputTests {

        @Test
        @DisplayName("brew() prints the correct drip-filter message")
        void brew_dripMessage() {
            drip.addRecipe(filterCoffee);
            drip.placeOrder("Carol", "Filter Coffee");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            drip.processNextOrder();
            System.setOut(System.out);

            String output = out.toString().trim();
            assertTrue(output.contains("Morning Brew Station"),
                    "Expected machine name in output: " + output);
            assertTrue(output.contains("Filter Coffee"),
                    "Expected recipe name in output: " + output);
            assertTrue(output.contains("Carol"),
                    "Expected customer name in output: " + output);
            assertTrue(output.contains("slow drip-filter method"),
                    "Expected drip method in output: " + output);
        }
    }
}
