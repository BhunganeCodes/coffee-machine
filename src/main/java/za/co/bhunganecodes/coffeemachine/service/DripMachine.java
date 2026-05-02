package za.co.bhunganecodes.coffeemachine.service;

import za.co.bhunganecodes.coffeemachine.model.Order;

/**
 * A concrete {@link CoffeeMachine} that brews orders using the slow
 * drip-filter method.
 * <p>
 * This class demonstrates <b>inheritance</b>: it extends the abstract
 * {@code CoffeeMachine} and provides a machine-specific implementation
 * of the {@link #brew(Order)} method.
 * </p>
 */
public class DripMachine extends CoffeeMachine {

    /**
     * Constructs a DripMachine with the given display name.
     *
     * @param machineName the display name of this machine
     */
    public DripMachine(String machineName) {
        // TODO Step 5c: Call super(machineName) to initialise the base class
        super(machineName);
    }

    /**
     * Brews the given order using the slow drip-filter method.
     * <p>
     * Prints to standard output:<br>
     * {@code "[machineName] brewing [recipeName] for [customerName] using slow drip-filter method."}
     * </p>
     *
     * @param order the order to brew
     */
    @Override
    protected void brew(Order order) {
        // TODO Step 5d: Print the brew message in the format above
        //               Hint: machineName() is inherited from CoffeeMachine
        //                     order.recipe().name() gives the recipe name
        //                     order.customer() gives the customer name
        System.out.printf("%s brewing %s for %s using high-pressure espresso extraction.", machineName(), order.recipe().name(), order.customer());
    }
}
