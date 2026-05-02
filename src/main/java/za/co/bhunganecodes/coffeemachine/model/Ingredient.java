package za.co.bhunganecodes.coffeemachine.model;

/**
 * Represents a single ingredient used in a coffee recipe.
 * <p>
 * Examples: "Milk" at 150.0 ml, "Espresso" at 30.0 ml.
 * </p>
 *
 * <b>Encapsulation rule:</b> both fields must be {@code private}; access
 * is provided only through the methods listed below.
 */
public class Ingredient {

    // TODO Step 1a: Declare a private String field called `name`
    private String name;

    // TODO Step 1b: Declare a private double field called `quantity`
    private double quantity;

    /**
     * Constructs an Ingredient with the given name and quantity.
     *
     * @param ingredientName     the display name of the ingredient (e.g. "Milk")
     * @param ingredientQuantity the volume in millilitres (e.g. 150.0)
     */
    public Ingredient(String ingredientName, double ingredientQuantity) {
        // TODO Step 1c: Assign ingredientName  → name
        // TODO Step 1d: Assign ingredientQuantity → quantity
        this.name = ingredientName;
        this.quantity = ingredientQuantity;
    }

    /**
     * Returns the ingredient name.
     *
     * @return the name of this ingredient
     */
    public String name() {
        // TODO Step 1e: Return name
        return name;
    }

    /**
     * Returns the quantity in millilitres.
     *
     * @return the quantity of this ingredient
     */
    public double quantity() {
        // TODO Step 1f: Return quantity
        return quantity;
    }

    /**
     * Updates the quantity of this ingredient.
     *
     * @param newQuantity the new quantity in millilitres
     * @throws IllegalArgumentException if {@code newQuantity} is negative
     */
    public void updateQuantity(double newQuantity) {
        // TODO Step 1g: If newQuantity < 0, throw new IllegalArgumentException with a meaningful message
        // TODO Step 1h: Otherwise, assign newQuantity → quantity
        if (newQuantity < 0) throw new IllegalArgumentException("The new quantity cannot be less than 0.");
        this.quantity = newQuantity;
    }

    /**
     * Returns a human-readable representation of this ingredient.
     * <p>
     * Format: {@code "Milk: 150.0ml"}
     * </p>
     *
     * @return formatted string summary
     */
    @Override
    public String toString() {
        // TODO Step 1i: Return "<name>: <quantity>ml"  e.g. "Milk: 150.0ml"
        return String.format("%s: %.1fml", name, quantity);
    }
}
