package za.co.bhunganecodes.coffeemachine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a coffee recipe composed of a name and a list of {@link Ingredient}s.
 * <p>
 * <b>Design principle – Defensive Copies:</b><br>
 * The constructor must store a <em>mutable copy</em> of the supplied list, not the
 * reference itself. If you stored the reference, any caller who still holds the
 * original list could mutate your recipe without your knowledge.<br>
 * Similarly, {@link #ingredients()} must return a <em>defensive copy</em> so
 * callers cannot call {@code .add()} / {@code .remove()} on the internal list.
 * </p>
 */
public class Recipe {

    // TODO Step 2a: Declare a private String field called `name`

    // TODO Step 2b: Declare a private List<Ingredient> field called `ingredients`

    /**
     * Constructs a Recipe with the given name and initial ingredient list.
     * <p>
     * <b>Important:</b> store a <em>copy</em> of {@code ingredientList}, not
     * the reference – see the class-level Javadoc for why.
     * </p>
     *
     * @param name           the display name of the recipe (e.g. "Latte")
     * @param ingredientList the initial list of ingredients
     */
    public Recipe(String name, List<Ingredient> ingredientList) {
        // TODO Step 2c: Assign name → this.name
        // TODO Step 2d: Store a NEW ArrayList copy of ingredientList into this.ingredients
        //               Hint: new ArrayList<>(ingredientList)
    }

    /**
     * Returns the recipe name.
     *
     * @return the name of this recipe
     */
    public String name() {
        // TODO Step 2e: Return name
        return null;
    }

    /**
     * Returns a <em>defensive copy</em> of the ingredient list.
     * <p>
     * Callers receive their own copy and cannot mutate the internal list.
     * </p>
     *
     * @return a new list containing the same ingredients as the internal list
     */
    public List<Ingredient> ingredients() {
        // TODO Step 2f: Return new ArrayList<>(ingredients)
        return null;
    }

    /**
     * Appends an ingredient to this recipe's internal list.
     *
     * @param ingredient the ingredient to add
     */
    public void addIngredient(Ingredient ingredient) {
        // TODO Step 2g: Add ingredient to the internal ingredients list
    }

    /**
     * Returns a human-readable summary of this recipe.
     * <p>
     * Format:
     * <pre>
     * Latte
     * Espresso: 30.0ml
     * Milk: 150.0ml
     * </pre>
     * (Recipe name on the first line, each ingredient on its own line.)
     * </p>
     *
     * @return formatted multi-line string
     */
    @Override
    public String toString() {
        // TODO Step 2h: Build and return a string: name + newline + each ingredient.toString() on its own line
        //               Hint: Use a StringBuilder or String.join / stream
        return null;
    }
}
