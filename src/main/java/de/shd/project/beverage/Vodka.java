package de.shd.project.beverage;

public class Vodka extends Beverage implements Alcoholic {


    public Vodka(String name, double pricePerLiter, double amount, int temperature) {
        super(name, pricePerLiter, amount, temperature);
    }

    protected Vodka(Beverage base, double amount) {
        super(base, amount);
    }

    @Override
    public double getAlcoholStrength() {
        return 40;
    }

    @Override
    protected Beverage createPortion(Beverage beverage, double amount) {
        return null;
    }
}
