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
        vendingMachine.findBeverage("Cola Zero").stream().map(Beverage::getName).forEach(System.out::println); //Erwartet: Cola Zero
        System.out.println(" ");
        System.out.println("Is Cola Zero listed? ");
        System.out.println(vendingMachine.isBeverageListed("Cola Zero")); //Erwartet: true
        System.out.println(" ");
        System.out.println("Vodka mit RedBull bottle price: ");
        System.out.println(vendingMachine.calculatePriceForOneBottleOf("Vodka mit RedBull")); //Erwartet: 2.25
        System.out.println(" ");
        System.out.println("Buy RedBull: ");
        System.out.println(vendingMachine.buyBeverage("RedBull lila", 20)); //Erwartet: VendingMachinePurchase[bottle=de.shd.project.container.Bottle@6193b845, change=18.5]
        System.out.println(" ");
        System.out.println("10 Vodka Gorbatschov bottle price: ");
        System.out.println(vendingMachine.calculatePriceForBottlesOf("Gorbatschov", 5)); //Erwartet: 6.25
        System.out.println(" ");
        System.out.println("Value of all bottles in the machine: ");
        System.out.println(vendingMachine.calculateTotalValueOfAllBeverages()); //Erwartet: 85.03
        System.out.println(" ");
        System.out.println("All alcoholic beverages in the machine: ");
        vendingMachine.getAllAlcoholicBeverages().stream().map(Beverage::getName).forEach(System.out::println); //Erwartet: Gorbatschov,Vodka mit Monster,Absolut,Vodka mit RedBull
        System.out.println(" ");
        System.out.println("All caffeinated beverages in the machine: ");
        vendingMachine.getAllCaffeinatedBeverages().stream().map(Beverage::getName).forEach(System.out::println); //Erwartet: Vodka mit Monster,RedBull lila,RedBull white,Vodka mit RedBull
        System.out.println(" ");
        System.out.println("Value of all alcoholic bottles in the machine: ");
        System.out.println(vendingMachine.calculateTotalValueOfAllAlcoholicBeverages()); //Erwartet: 59.0
        System.out.println(" ");
        System.out.println("Names of all alcoholic beverages in the machine: ");
        System.out.println(vendingMachine.displayNamesOfAllAlcoholicBeverages()); //Erwartet: [Gorbatschov, Vodka mit Monster, Absolut, Vodka mit RedBull]
        System.out.println(" ");
        System.out.println("Names of all non alcoholic beverages in the machine: ");
        System.out.println(vendingMachine.displayNamesOfAllNonAlcoholicBeverages()); //Erwartet: [Sprite, Cola Zero, Sprite Zero, RedBull white, Cola, RedBull lila]
        System.out.println(" ");
        System.out.println("Display all beverages grouped by class: ");
        System.out.println(vendingMachine.getBeveragesGroupedByClass()); //Erwartet: {class de.shd.project.beverage.Sprite=[de.shd.project.beverage.Sprite@9401ef04, de.shd.project.beverage.Sprite@6eb5fb42], class de.shd.project.beverage.Cola=[de.shd.project.beverage.Cola@d11ff8a6, de.shd.project.beverage.Cola@202320], class de.shd.project.beverage.Vodka=[de.shd.project.beverage.Vodka@d9a3fb93, de.shd.project.beverage.Vodka@1c38a0ad], class de.shd.project.beverage.VodkaE=[de.shd.project.beverage.VodkaE@2e27fbb2, de.shd.project.beverage.VodkaE@24f3811c], class de.shd.project.beverage.RedBull=[de.shd.project.beverage.RedBull@b812da4c, de.shd.project.beverage.RedBull@60c1d14d]}
        System.out.println(" ");
        System.out.println("Display all beverages seperated by comma: ");
        System.out.println(vendingMachine.displayAllBeverageNamesSeparatedByComma()); //Erwartet: "Gorbatschov, Vodka mit Monster, Sprite, Absolut, Cola Zero, Sprite Zero, Vodka mit RedBull, RedBull white, Cola, RedBull lila"
        System.out.println(" ");
        System.out.println("All beverages with amount below threshold: ");
        vendingMachine.getAllBeveragesWithAmountBelowThreshold(2).stream().map(Beverage::getName).forEach(System.out::println); //Erwartet:Gorbatschov,Vodka mit Monster,Sprite,Absolut,Sprite Zero,Vodka mit RedBull,RedBull white,Cola,RedBull lila
        System.out.println(" ");
        System.out.println("Cold beverages: ");
        System.out.println(vendingMachine.getOnlyColdBeverages()); //Erwartet: [de.shd.project.beverage.Vodka@d9a3fb93, de.shd.project.beverage.VodkaE@2e27fbb2, de.shd.project.beverage.Vodka@1c38a0ad, de.shd.project.beverage.Cola@d11ff8a6, de.shd.project.beverage.Sprite@6eb5fb42, de.shd.project.beverage.VodkaE@24f3811c, de.shd.project.beverage.Cola@202320]
        System.out.println(" ");
        System.out.println("Hot beverages: ");
        System.out.println(vendingMachine.getOnlyHotBeverages()); //Erwartet: [de.shd.project.beverage.Sprite@9401ef04]
        System.out.println(" ");
        System.out.println("Average temperature of beverages: ");
        System.out.println(vendingMachine.calculateAverageTemperatureOfAllBeverages()); //Erwartet: 5.3
        System.out.println(" ");
        System.out.println("Average alcohol Strength: ");
        System.out.println(vendingMachine.calculateAverageAlcoholicStrengthOfAllAlcoholicBeverages()); //Erwartet: 30.0
        System.out.println(" ");
        System.out.println("all beverages mapped by name: ");
        System.out.println(vendingMachine.getAllBeveragesMappedByName()); //Erwartet: {Cola=de.shd.project.beverage.Cola@202320, Vodka mit Monster=de.shd.project.beverage.VodkaE@2e27fbb2, Sprite=de.shd.project.beverage.Sprite@9401ef04, Absolut=de.shd.project.beverage.Vodka@1c38a0ad, Sprite Zero=de.shd.project.beverage.Sprite@6eb5fb42, Gorbatschov=de.shd.project.beverage.Vodka@d9a3fb93, Cola Zero=de.shd.project.beverage.Cola@d11ff8a6, Vodka mit RedBull=de.shd.project.beverage.VodkaE@24f3811c, RedBull white=de.shd.project.beverage.RedBull@b812da4c, RedBull lila=de.shd.project.beverage.RedBull@60c1d14d}
        System.out.println(" ");
        System.out.println("get multiplied amount of beverages: ");
        System.out.println(vendingMachine.getMultipliedAmountsOfBeverages()); //Erwartet: 2.5
        System.out.println(" ");
        System.out.println("top 5beverages with least amount: ");
        System.out.println(vendingMachine.getTopFiveBeveragesWithTheLeastAmountOrderedByAmountDescending()); //Erwartet: [de.shd.project.beverage.Vodka@d9a3fb93, de.shd.project.beverage.VodkaE@2e27fbb2, de.shd.project.beverage.Sprite@9401ef04, de.shd.project.beverage.Vodka@1c38a0ad, de.shd.project.beverage.RedBull@60c1d14d]
        System.out.println(" ");
        System.out.println("beverages by filter: ");
        System.out.println(vendingMachine.getListByFilter(beverage -> beverage.getPricePerLiter() < 2.0)); //Erwartet: [de.shd.project.beverage.Sprite@9401ef04, de.shd.project.beverage.Cola@d11ff8a6, de.shd.project.beverage.Cola@202320]
        System.out.println(" ");
        System.out.println("list of current amount of beverages: ");
        System.out.println(vendingMachine.getListOfCurrentAmountsOfBeverages()); //Erwartet: [1.0, 1.0, 1.0, 1.0, 5.0, 1.0, 1.0, 1.0, 1.0, 0.5]
        System.out.println(" ");
        System.out.println("all affordable beverages: ");
        System.out.println(vendingMachine.findAllAffordableBeverages(5)); //Erwartet: [de.shd.project.beverage.Vodka@d9a3fb93, de.shd.project.beverage.Sprite@9401ef04, de.shd.project.beverage.Vodka@1c38a0ad, de.shd.project.beverage.Cola@d11ff8a6, de.shd.project.beverage.VodkaE@24f3811c, de.shd.project.beverage.RedBull@b812da4c, de.shd.project.beverage.Cola@202320, de.shd.project.beverage.RedBull@60c1d14d]

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
        vendor.getBeveragesOfAllVendingMachines().stream().map(Beverage::getName).forEach(System.out::println); //Erwartet:Gorbatschov,Cola,Bitburger,Vodka mit Monster,Sprite,Absolut,Sprite Zero,Cola Zero,RedBull lila,Hachenburger,RedBull white,Vodka mit RedBull
        System.out.println(" ");
        System.out.println("Beverages of all vending machines: ");
        System.out.println(vendor.countNumberOfBeveragesOfAllVendingMachines()); //Erwartet:17
        System.out.println(" ");
        System.out.println("Beverages of all vending machines(distinct): ");
        System.out.println(vendor.countNumberOfBeveragesDistinctOfAllVendingMachines()); //Erwartet:12
        System.out.println(" ");
        System.out.println("Venidng machine with most beverages: ");
        System.out.println(vendor.getVendingMachineWithTheMostBeverages()); //Erwartet: de.shd.project.automat.VendingMachine@2957fcb0
        System.out.println(" ");
        System.out.println("chapest non acloholic beverage: ");
        System.out.println(vendor.findTheCheapestNonAlcoholicBeverage().getName()); //Erwartet: Cola Zero

    }
}
