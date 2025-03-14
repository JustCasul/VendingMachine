package de.shd.project;

import de.shd.project.automat.VendingMachine;
import de.shd.project.beverage.Beverage;
import de.shd.project.supplier.BeverageSupplier;
import de.shd.project.supplier.DoubleVendingMachineSupplier;
import de.shd.project.supplier.SpecificBeverageSupplier;
import de.shd.project.supplier.VendingMachineSupplier;
import de.shd.project.vendor.Vendor;

public class Main {
    static VendingMachine vendingMachine = new VendingMachine();
    static SpecificBeverageSupplier specificBeverageSupplier = new SpecificBeverageSupplier();

    public static void main(String[] args) {
        //Vending machine test
        vendingMachine.setBeverageSupplier(specificBeverageSupplier);
        vendingMachine.restock();
        vendingMachine.displayNamesOfAllBeverages().forEach(System.out::println);
        System.out.println(" ");
        System.out.println("Cola Zero: ");
        vendingMachine.findBeverage("Cola Zero").stream().map(Beverage::getName).forEach(System.out::println); //klappt
        System.out.println(" ");
        System.out.println("Is Cola Zero listed? ");
        System.out.println(vendingMachine.isBeverageListed("Cola Zero")); //klappt
        System.out.println(" ");
        System.out.println("Vodka mit RedBull bottle price: ");
        System.out.println(vendingMachine.calculatePriceForOneBottleOf("Vodka mit RedBull")); //klappt
        System.out.println(" ");
        System.out.println("Buy RedBull: ");
        System.out.println(vendingMachine.buyBeverage("RedBull lila", 20)); //klappt
        System.out.println(" ");
        System.out.println("10 Vodka Gorbatschov bottle price: ");
        System.out.println(vendingMachine.calculatePriceForBottlesOf("Gorbatschov", 5)); //klappt
        System.out.println(" ");
        System.out.println("Value of all bottles in the machine: ");
        System.out.println(vendingMachine.calculateTotalValueOfAllBeverages()); //klappt
        System.out.println(" ");
        System.out.println("All alcoholic beverages in the machine: ");
        vendingMachine.getAllAlcoholicBeverages().stream().map(Beverage::getName).forEach(System.out::println); //klappt
        System.out.println(" ");
        System.out.println("All caffeinated beverages in the machine: ");
        vendingMachine.getAllCaffeinatedBeverages().stream().map(Beverage::getName).forEach(System.out::println); //klappt
        System.out.println(" ");
        System.out.println("Value of all alcoholic bottles in the machine: ");
        System.out.println(vendingMachine.calculateTotalValueOfAllAlcoholicBeverages()); //klappt
        System.out.println(" ");
        System.out.println("Names of all alcoholic beverages in the machine: ");
        System.out.println(vendingMachine.displayNamesOfAllAlcoholicBeverages()); //klappt
        System.out.println(" ");
        System.out.println("Names of all non alcoholic beverages in the machine: ");
        System.out.println(vendingMachine.displayNamesOfAllNonAlcoholicBeverages()); //klappt
        System.out.println(" ");
        System.out.println("Display all beverages grouped by class: ");
        System.out.println(vendingMachine.getBeveragesGroupedByClass()); //klappt
        System.out.println(" ");
        System.out.println("Display all beverages seperated by comma: ");
        System.out.println(vendingMachine.displayAllBeverageNamesSeparatedByComma()); //klappt
        System.out.println(" ");
        System.out.println("All beverages with amount below threshold: ");
        vendingMachine.getAllBeveragesWithAmountBelowThreshold(2).stream().map(Beverage::getName).forEach(System.out::println); //klappt
        System.out.println(" ");
        System.out.println("Cold beverages: ");
        System.out.println(vendingMachine.getOnlyColdBeverages()); //klappt
        System.out.println(" ");
        System.out.println("Hot beverages: ");
        System.out.println(vendingMachine.getOnlyHotBeverages()); //klappt
        System.out.println(" ");
        System.out.println("Average temperature of beverages: ");
        System.out.println(vendingMachine.calculateAverageTemperatureOfAllBeverages()); //klappt
        System.out.println(" ");
        System.out.println("Average alcohol Strength: ");
        System.out.println(vendingMachine.calculateAverageAlcoholicStrengthOfAllAlcoholicBeverages()); //klappt
        System.out.println(" ");
        System.out.println("all beverages mapped by name: ");
        System.out.println(vendingMachine.getAllBeveragesMappedByName()); //klappt
        System.out.println(" ");
        System.out.println("get multiplied amount of beverages: ");
        System.out.println(vendingMachine.getMultipliedAmountsOfBeverages()); //klappt
        System.out.println(" ");
        System.out.println("top 5beverages with least amount: ");
        System.out.println(vendingMachine.getTopFiveBeveragesWithTheLeastAmountOrderedByAmountDescending()); //klappt
        System.out.println(" ");
        System.out.println("beverages by filter: ");
        System.out.println(vendingMachine.getListByFilter(beverage -> beverage.getPricePerLiter() < 2.0)); //klappt
        System.out.println(" ");
        System.out.println("list of current amount of beverages: ");
        System.out.println(vendingMachine.getListOfCurrentAmountsOfBeverages()); //klappt
        System.out.println(" ");
        System.out.println("all affordable beverages: ");
        System.out.println(vendingMachine.findAllAffordableBeverages(5)); //klappt

        //spacer
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        //Vendor test
        Vendor vendor = new Vendor();
        DoubleVendingMachineSupplier vendingMachineSupplier = new DoubleVendingMachineSupplier();
        vendor.supplyVendingMachines(vendingMachineSupplier);
        vendor.restock();

        System.out.println("Beverages of all vending machines: ");
        vendor.getBeveragesOfAllVendingMachines().stream().map(Beverage::getName).forEach(System.out::println);
        System.out.println(" ");
        System.out.println("Beverages of all vending machines: ");
        System.out.println(vendor.countNumberOfBeveragesOfAllVendingMachines());
        System.out.println(" ");
        System.out.println("Beverages of all vending machines(distinct): ");
        System.out.println(vendor.countNumberOfBeveragesDistinctOfAllVendingMachines());
        System.out.println(" ");
        System.out.println("Venidng machine with most beverages: ");
        System.out.println(vendor.getVendingMachineWithTheMostBeverages());
        System.out.println(" ");
        System.out.println("chapest non acloholic beverage: ");
        System.out.println(vendor.findTheCheapestNonAlcoholicBeverage().getName());

    }
}
