package de.shd.project.beverage;

import de.shd.project.supplier.BeverageSupplier;

public class Cola extends Beverage {

    public Cola(String name, double pricePerLiter, double amount, int temperature) {
        super(name, pricePerLiter, amount, temperature);
    }

    public Cola(Beverage base, double amount) {
        super(base, amount);
    }

    @Override
    protected Beverage createPortion(Beverage beverage, double amount) {
        return new Cola(beverage, amount);
    }
}
