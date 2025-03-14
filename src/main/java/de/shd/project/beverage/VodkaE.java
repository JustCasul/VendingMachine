package de.shd.project.beverage;

public class VodkaE extends Beverage implements Caffeinated,Alcoholic{

    public VodkaE(String name, double pricePerLiter, double amount) {
        super(name, pricePerLiter, amount);
    }

    protected VodkaE(Beverage base, double amount) {
        super(base, amount);
    }

    @Override
    public double getAlcoholStrength() {
        return 0;
    }

    @Override
    protected Beverage createPortion(Beverage beverage, double amount) {
        return null;
    }

    @Override
    public double getCaffeineStrength() {
        return 0;
    }
}
