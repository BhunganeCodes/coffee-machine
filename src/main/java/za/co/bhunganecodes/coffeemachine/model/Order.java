package za.co.bhunganecodes.coffeemachine.model;

/**
 * Represents a customer order that links a customer name to a {@link Recipe}
 * and tracks the lifecycle of the order via {@link OrderStatus}.
 * <p>
 * Lifecycle: {@code PENDING → IN_PROGRESS → COMPLETED}
 * </p>
 */
public class Order {

    /**
     * Represents the possible states of an {@link Order}.
     */
    public enum OrderStatus {
        /** Order has been placed but not yet started. */
        PENDING,
        /** Order is currently being brewed. */
        IN_PROGRESS,
        /** Order has been fully brewed and is ready. */
        COMPLETED
    }

    // TODO Step 3a: Declare a private int field called `orderId`
    private int orderId;

    // TODO Step 3b: Declare a private String field called `customerName`
    private String customerName;

    // TODO Step 3c: Declare a private Recipe field called `recipe`
    private Recipe recipe;

    // TODO Step 3d: Declare a private OrderStatus field called `status`
    private OrderStatus status;

    /**
     * Constructs an Order with the given ID, customer name, and recipe.
     * The status defaults to {@link OrderStatus#PENDING}.
     *
     * @param orderId      the unique numeric identifier for this order
     * @param customerName the name of the customer who placed the order
     * @param recipe       the recipe the customer ordered
     */
    public Order(int orderId, String customerName, Recipe recipe) {
        // TODO Step 3e: Assign orderId → this.orderId
        // TODO Step 3f: Assign customerName → this.customerName
        // TODO Step 3g: Assign recipe → this.recipe
        // TODO Step 3h: Set this.status to OrderStatus.PENDING
        this.orderId = orderId;
        this.customerName = customerName;
        this.recipe = recipe;
        this.status = OrderStatus.PENDING;
    }

    /**
     * Returns the unique order ID.
     *
     * @return orderId
     */
    public int orderId() {
        // TODO Step 3i: Return orderId
        return orderId;
    }

    /**
     * Returns the customer's name.
     *
     * @return customerName
     */
    public String customer() {
        // TODO Step 3j: Return customerName
        return customerName;
    }

    /**
     * Returns the recipe associated with this order.
     *
     * @return the Recipe
     */
    public Recipe recipe() {
        // TODO Step 3k: Return recipe
        return recipe;
    }

    /**
     * Returns the current status of this order.
     *
     * @return the OrderStatus
     */
    public OrderStatus status() {
        // TODO Step 3l: Return status
        return status;
    }

    /**
     * Updates the status of this order.
     *
     * @param newStatus the new {@link OrderStatus} to set
     */
    public void updateStatus(OrderStatus newStatus) {
        // TODO Step 3m: Assign newStatus → this.status
        this.status = newStatus;
    }

    /**
     * Returns a human-readable summary of this order.
     * <p>
     * Example: {@code "Order #1 | Customer: Alice | Recipe: Latte | Status: PENDING"}
     * </p>
     *
     * @return formatted string summary
     */
    @Override
    public String toString() {
        // TODO Step 3n: Return a formatted string including orderId, customerName, recipe.name(), and status
        //               e.g. "Order #1 | Customer: Alice | Recipe: Latte | Status: PENDING"
        return String.format("Order #%d | Customer: %s | Recipe: %s | Status: %s", orderId, customerName, recipe.toString(), status.toString());
    }
}
