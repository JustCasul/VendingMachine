package de.shd.project.beverage;

public class Sprite extends Beverage{

    public Sprite(String name, double pricePerLiter, double amount) {
        super(name, pricePerLiter, amount);
    }

    protected Sprite(Beverage base, double amount) {
        super(base, amount);
    }

    @Override
    protected Beverage createPortion(Beverage beverage, double amount) {
        return null;
    }
}
