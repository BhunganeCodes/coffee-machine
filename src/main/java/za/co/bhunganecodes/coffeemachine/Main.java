package za.co.bhunganecodes.coffeemachine;

import za.co.bhunganecodes.coffeemachine.model.Ingredient;
import za.co.bhunganecodes.coffeemachine.model.Order;
import za.co.bhunganecodes.coffeemachine.model.Recipe;
import za.co.bhunganecodes.coffeemachine.service.DripMachine;
import za.co.bhunganecodes.coffeemachine.service.EspressoMachine;

import java.util.List;

/**
 * Entry point for the Coffee Machine simulation.
 * <p>
 * This class is provided for manual testing and demonstration purposes.
 * Your grade is determined entirely by the automated JUnit tests — you do
 * not need to modify this file, but feel free to experiment here.
 * </p>
 */
public class Main {

    public static void main(String[] args) {

        // --- Set up recipes ---
        Recipe latte = new Recipe("Latte", List.of(
                new Ingredient("Espresso", 30.0),
                new Ingredient("Milk", 150.0)
        ));

        Recipe filterCoffee = new Recipe("Filter Coffee", List.of(
                new Ingredient("Ground Coffee", 15.0),
                new Ingredient("Water", 250.0)
        ));

        // --- Espresso Machine demo ---
        EspressoMachine espresso = new EspressoMachine("Barista Pro 3000");
        espresso.addRecipe(latte);

        System.out.println("=== " + espresso.machineName() + " ===");
        Order o1 = espresso.placeOrder("Alice", "Latte");
        Order o2 = espresso.placeOrder("Bob", "Latte");

        System.out.println("Queue size: " + espresso.orderQueue().size());
        espresso.processNextOrder();
        espresso.processNextOrder();

        System.out.println("Order 1 status: " + o1.status());
        System.out.println("Order 2 status: " + o2.status());

        System.out.println();

        // --- Drip Machine demo ---
        DripMachine drip = new DripMachine("Morning Brew Station");
        drip.addRecipe(filterCoffee);

        System.out.println("=== " + drip.machineName() + " ===");
        drip.placeOrder("Carol", "Filter Coffee");
        drip.processNextOrder();
    }
}
