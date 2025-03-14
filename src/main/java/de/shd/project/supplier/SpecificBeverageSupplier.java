package de.shd.project.supplier;

import de.shd.project.beverage.*;

import java.util.Set;

public class SpecificBeverageSupplier implements BeverageSupplier{

    @Override
    public Set<Beverage> supplyBeverages() {
        return Set.of(new Cola("Cola Zero", 0.1, 5, 1), new Cola("Cola", 0.89, 1, 1),
                      new Sprite("Sprite Zero", 20, 1, 1), new Sprite("Sprite", 0.89, 1, 30),
                      new Vodka("Gorbatschov", 2, 1, 2), new Vodka("Absolut", 3, 1, 2),
                      new VodkaE("Vodka mit RedBull", 4, 1, 2), new VodkaE("Vodka mit Monster", 50, 1, 2),
                      new RedBull("RedBull lila", 2.5, 1, 6), new RedBull("RedBull white", 2.5, 1, 6));
    }

}
