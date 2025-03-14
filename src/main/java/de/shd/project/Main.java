package de.shd.project;

import de.shd.project.automat.VendingMachine;
import de.shd.project.beverage.Beverage;
import de.shd.project.supplier.BeverageSupplier;
import de.shd.project.supplier.SpecificBeverageSupplier;

public class Main {
    static VendingMachine vendingMachine = new VendingMachine();
    static SpecificBeverageSupplier specificBeverageSupplier = new SpecificBeverageSupplier();

    public static void main(String[] args) {
        vendingMachine.setBeverageSupplier(specificBeverageSupplier);
        vendingMachine.restock();
        vendingMachine.getBeverages().stream().map(Beverage::getName).forEach(System.out::println);
    }
}
