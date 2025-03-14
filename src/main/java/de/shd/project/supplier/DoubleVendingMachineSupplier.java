package de.shd.project.supplier;

import de.shd.project.automat.VendingMachine;
import de.shd.project.beverage.Beverage;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class DoubleVendingMachineSupplier implements VendingMachineSupplier {

    @Override
    public List<VendingMachine> supplyVendingMachines() {
        VendingMachine specificVendingMachine = new VendingMachine();
        VendingMachine cheaperSpecificVendingMachine = new VendingMachine();
        BeverageSupplier specificBeverageSupplier = new SpecificBeverageSupplier();
        BeverageSupplier cheaperSpecificBeverageSupplier = new CheaperSpecificBeverageSupplier();
        specificVendingMachine.setBeverageSupplier(specificBeverageSupplier);
        cheaperSpecificVendingMachine.setBeverageSupplier(cheaperSpecificBeverageSupplier);
        return List.of(specificVendingMachine, cheaperSpecificVendingMachine);
    }

}
