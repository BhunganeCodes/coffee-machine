package za.co.bhunganecodes.coffeemachine.service;

import za.co.bhunganecodes.coffeemachine.model.Order;

/**
 * A concrete {@link CoffeeMachine} that brews orders using high-pressure
 * espresso extraction.
 * <p>
 * This class demonstrates <b>inheritance</b>: it extends the abstract
 * {@code CoffeeMachine} and provides a machine-specific implementation
 * of the {@link #brew(Order)} method.
 * </p>
 */
public class EspressoMachine extends CoffeeMachine {

    /**
     * Constructs an EspressoMachine with the given display name.
     *
     * @param machineName the display name of this machine
     */
    public EspressoMachine(String machineName) {
        // TODO Step 5a: Call super(machineName) to initialise the base class
    }

    /**
     * Brews the given order using high-pressure espresso extraction.
     * <p>
     * Prints to standard output:<br>
     * {@code "[machineName] brewing [recipeName] for [customerName] using high-pressure espresso extraction."}
     * </p>
     *
     * @param order the order to brew
     */
    @Override
    protected void brew(Order order) {
        // TODO Step 5b: Print the brew message in the format above
        //               Hint: machineName() is inherited from CoffeeMachine
        //                     order.recipe().name() gives the recipe name
        //                     order.customer() gives the customer name
    }
}
