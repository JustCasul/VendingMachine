package de.shd.project.beverage;

public class RedBull extends Beverage implements Caffeinated {

    public RedBull(String name, double pricePerLiter, double amount) {
        super(name, pricePerLiter, amount);
    }

    public RedBull(Beverage base, double amount) {
        super(base, amount);
    }

    @Override
    protected Beverage createPortion(Beverage beverage, double amount){
        return new RedBull(beverage, amount);
    }

    @Override
    public double getCaffeineStrength(){
        return 120;
    }
}
