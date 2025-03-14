package de.shd.project.beverage;

public class Bier extends Beverage implements Alcoholic{


    public Bier(String name, double pricePerLiter, double amount, int temperature) {
        super(name, pricePerLiter, amount, temperature);
    }

    protected Bier(Beverage base, double amount) {
        super(base, amount);
    }

    @Override
    public double getAlcoholStrength() {
        return 15;
    }

    @Override
    protected Beverage createPortion(Beverage beverage, double amount) {
        return null;
    }
}
