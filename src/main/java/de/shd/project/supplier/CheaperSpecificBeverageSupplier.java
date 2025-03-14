package de.shd.project.supplier;

import de.shd.project.beverage.*;

import java.util.Set;

public class CheaperSpecificBeverageSupplier implements BeverageSupplier {

    @Override
    public Set<Beverage> supplyBeverages() {
        return Set.of(
                new Cola("Cola Zero", 0.89, 5, 1),
                new Cola("Cola", 0.89, 1, 1),
                new Sprite("Sprite Zero", 20, 1, 1),
                new Sprite("Sprite", 0.89, 1, 30),
                new Vodka("Gorbatschov", 2, 1, 2),

                new Bier("Bitburger", 1.5, 1, 0),
                new Bier("Hachenburger", 2.5, 1, 0)
        );
    }

}
