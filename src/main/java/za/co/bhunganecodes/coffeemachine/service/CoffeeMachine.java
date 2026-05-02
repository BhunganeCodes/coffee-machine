package za.co.bhunganecodes.coffeemachine.service;

import za.co.bhunganecodes.coffeemachine.model.Order;
import za.co.bhunganecodes.coffeemachine.model.Recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for all coffee machines.
 * <p>
 * Manages a catalogue of {@link Recipe}s and a queue of {@link Order}s.
 * The machine-specific brewing step is delegated to concrete subclasses
 * via the abstract {@link #brew(Order)} method.
 * </p>
 *
 * <b>Inheritance rule:</b> this class is {@code abstract} – it cannot be
 * instantiated directly. Subclasses must implement {@link #brew(Order)}.
 */
public abstract class CoffeeMachine {

    // TODO Step 4a: Declare a private String field called `machineName`
    private String machineName;

    // TODO Step 4b: Declare a private Map<String, Recipe> field called `recipes`
    private Map<String, Recipe> recipes;

    // TODO Step 4c: Declare a private List<Order> field called `orderQueue`
    private List<Order> orderQueue;

    // TODO Step 4d: Declare a private int field called `orderCounter` (starts at 0)
    private int orderCount;

    /**
     * Constructs a CoffeeMachine with the given display name.
     * Initialises {@code recipes} as a new {@link HashMap} and
     * {@code orderQueue} as a new {@link ArrayList}.
     *
     * @param machineName the display name of this machine (e.g. "Barista Pro 3000")
     */
    public CoffeeMachine(String machineName) {
        // TODO Step 4e: Assign machineName → this.machineName
        // TODO Step 4f: Initialise this.recipes as new HashMap<>()
        // TODO Step 4g: Initialise this.orderQueue as new ArrayList<>()
        // (orderCounter is 0 by default in Java — no explicit initialisation needed)
        this.machineName = machineName;
        this.recipes = new HashMap<>();
        this.orderQueue = new ArrayList<>();
    }

    // -------------------------------------------------------------------------
    // Concrete methods — implement all of these
    // -------------------------------------------------------------------------

    /**
     * Adds a recipe to the machine's catalogue.
     * Uses the recipe's name as the map key.
     *
     * @param recipe the recipe to add
     */
    public void addRecipe(Recipe recipe) {
        // TODO Step 4h: Put recipe into the recipes map using recipe.name() as the key
        recipes.put(recipe.name(), recipe);
    }

    /**
     * Retrieves a recipe by name.
     *
     * @param recipeName the name to look up
     * @return the matching {@link Recipe}, or {@code null} if not found
     */
    public Recipe getRecipe(String recipeName) {
        // TODO Step 4i: Return the recipe from the map (returns null automatically if not found)
        return recipes.get(recipeName);
    }

    /**
     * Returns an unmodifiable view of the entire recipe catalogue.
     *
     * @return unmodifiable map of recipe name → Recipe
     */
    public Map<String, Recipe> getAllRecipes() {
        // TODO Step 4j: Return Collections.unmodifiableMap(recipes)
        return Collections.unmodifiableMap(recipes);
    }

    /**
     * Places a new order for the given customer and recipe name.
     * <p>
     * The order ID is {@code ++orderCounter} (pre-increment, so the first order is 1).
     * </p>
     *
     * @param customerName the name of the customer
     * @param recipeName   the name of the recipe to order
     * @return the newly created {@link Order}
     * @throws IllegalArgumentException if no recipe with {@code recipeName} is found
     */
    public Order placeOrder(String customerName, String recipeName) {
        // TODO Step 4k: Look up the recipe by recipeName; throw IllegalArgumentException if null
        // TODO Step 4l: Create a new Order with id = ++orderCounter, customerName, and the recipe
        // TODO Step 4m: Add the order to orderQueue
        // TODO Step 4n: Return the order
        if (recipes.get(recipeName) == null) throw new IllegalArgumentException("Recipe not found.");
        Order newOrder = new Order(++orderCount, customerName, recipes.get(recipeName));
        orderQueue.add(newOrder);
        return newOrder;
    }

    /**
     * Processes the next {@link Order.OrderStatus#PENDING} order in the queue.
     * <ol>
     *   <li>Find the first PENDING order in {@code orderQueue}.</li>
     *   <li>Set its status to {@link Order.OrderStatus#IN_PROGRESS}.</li>
     *   <li>Call {@link #brew(Order)} (implemented by the subclass).</li>
     *   <li>Set its status to {@link Order.OrderStatus#COMPLETED}.</li>
     *   <li>Return the completed order.</li>
     * </ol>
     *
     * @return the completed {@link Order}, or {@code null} if no pending orders exist
     */
    public Order processNextOrder() {
        // TODO Step 4o: Stream (or loop) through orderQueue to find the first PENDING order
        // TODO Step 4p: If none found, return null
        // TODO Step 4q: Set status → IN_PROGRESS
        // TODO Step 4r: Call brew(order)
        // TODO Step 4s: Set status → COMPLETED
        // TODO Step 4t: Return the order
        Order firstOrderPending = null;

        for (Order order : orderQueue) {
            if (order.status() == Order.OrderStatus.PENDING) {
                firstOrderPending = order;
                break;
            }
        }
        firstOrderPending.updateStatus(Order.OrderStatus.IN_PROGRESS);
        brew(firstOrderPending);
        firstOrderPending.updateStatus(Order.OrderStatus.COMPLETED);

        return firstOrderPending;
    }

    /**
     * Returns an unmodifiable view of the current order queue.
     *
     * @return unmodifiable list of orders
     */
    public List<Order> orderQueue() {
        // TODO Step 4u: Return Collections.unmodifiableList(orderQueue)
        return Collections.unmodifiableList(orderQueue);
    }

    /**
     * Returns the machine's display name.
     *
     * @return machineName
     */
    public String machineName() {
        // TODO Step 4v: Return machineName
        return machineName;
    }

    // -------------------------------------------------------------------------
    // Abstract method — subclasses must implement this
    // -------------------------------------------------------------------------

    /**
     * Performs the machine-specific brewing step for the given order.
     * <p>
     * Called automatically by {@link #processNextOrder()} after the order
     * status is set to {@code IN_PROGRESS}.
     * </p>
     *
     * @param order the order currently being brewed
     */
    protected abstract void brew(Order order);
}
