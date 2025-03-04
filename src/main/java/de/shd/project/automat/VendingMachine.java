package de.shd.project.automat;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.shd.project.beverage.Beverage;
import de.shd.project.supplier.BeverageSupplier;
import de.shd.project.container.Bottle;


/**
 * <h3>Repräsentation eines Getränkeautomats</h3>
 * Dieser soll wie folgt funktionieren:
 * <p>
 * Über den {@link BeverageSupplier} (Getränkezulieferer) kann der Automat mit ein Sortiment von Getränken bestückt werden. Hierbei wird eine
 * Menge des Getränks {@link Beverage#getAmount()} in den Automaten abgefüllt. Dieser lagert das Getränk in einem Behälter analog zu:<br>
 * <br><img src="vendingmachine.png" width="242" height="271"></img>
 * </p>
 * <p>
 * Die Getränke werden über {@link #beverages} verwaltet. Die "Behälter" des Getränkeautomats werden nur befüllt, wenn die {@link #restock()}
 * Methode aufgerufen wird.
 * </p>
 *
 * @author Christoph Gragert (cgr@shd.de)
 */
public class VendingMachine implements VendingMachineFunctionality
{
   /**
    * Getränke, mit denen der Automat befüllt wurde.
    */
   private Set<Beverage> beverages = new HashSet<>();
   /**
    * Getränkezulieferer
    */
   private BeverageSupplier beverageSupplier;

   public void setBeverageSupplier(BeverageSupplier beverageSupplier)
   {
      this.beverageSupplier = beverageSupplier;
   }

   public void restock()
   {
      beverages = beverageSupplier.supplyBeverages();
   }

   public Set<Beverage> getBeverages()
   {
      return beverages;
   }

   @Override
   public List<String> displayNamesOfAllBeverages() {
      return beverages.stream().map(Beverage::getName).collect(Collectors.toList());
   }

   @Override
   public Optional<Beverage> findBeverage(String name) {
       return beverages.stream().filter(beverage -> beverage.getName().equals(name)).findFirst();
   }

   @Override
   public boolean isBeverageListed(String name) {
      return (beverages.stream().anyMatch(beverage -> beverage.getName().equals(name)));
   }

   @Override
   public double calculatePriceForOneBottleOf(String name) {
      return findBeverage(name).map(e -> Bottle.PRICE_FOR_BOTTLE + (e.getPricePerLiter() * Math.min(e.getAmount(), Bottle.MAX_AMOUNT_IN_LITER))).orElseThrow(VendingMachineException::new);
   }

   @Override
   public VendingMachinePurchase buyBeverage(String name, double money) {
      return null;
   }

   @Override
   public double calculatePriceForBottlesOf(String name, int numberOfBottles) {
      return 0;
   }

   @Override
   public double calculateTotalValueOfAllBeverages() {
      return 0;
   }

   @Override
   public Set<Beverage> getAllAlcoholicBeverages() {
      return Set.of();
   }

   @Override
   public Set<Beverage> getAllCaffeinatedBeverages() {
      return Set.of();
   }

   @Override
   public double calculateTotalValueOfAllAlcoholicBeverages() {
      return 0;
   }

   @Override
   public Set<String> displayNamesOfAllAlcoholicBeverages() {
      return Set.of();
   }

   @Override
   public List<String> displayNamesOfAllNonAlcoholicBeverages() {
      return List.of();
   }

   @Override
   public Map<Class<? extends Beverage>, List<Beverage>> getBeveragesGroupedByClass() {
      return Map.of();
   }

   @Override
   public String displayAllBeverageNamesSeparatedByComma() {
      return "";
   }

   @Override
   public List<Beverage> getAllBeveragesWithAmountBelowThreshold(int threshold) {
      return List.of();
   }

   @Override
   public List<Beverage> getOnlyColdBeverages() {
      return List.of();
   }

   @Override
   public List<Beverage> getOnlyHotBeverages() {
      return List.of();
   }

   @Override
   public double calculateAverageTemperatureOfAllBeverages() {
      return 0;
   }

   @Override
   public double calculateAverageAlcoholicStrengthOfAllAlcoholicBeverages() {
      return 0;
   }

   @Override
   public Map<String, Beverage> getAllBeveragesMappedByName() {
      return Map.of();
   }

   @Override
   public double getMultipliedAmountsOfBeverages() {
      return 0;
   }

   @Override
   public List<Beverage> getTopFiveBeveragesWithTheLeastAmountOrderedByAmountDescending() {
      return List.of();
   }

   @Override
   public List<Beverage> getListByFilter(Predicate<Beverage> filter) {
      return List.of();
   }

   @Override
   public List<Double> getListOfCurrentAmountsOfBeverages() {
      return List.of();
   }

   @Override
   public List<Beverage> findAllAffordableBeverages(double budget) {
      return List.of();
   }
}
