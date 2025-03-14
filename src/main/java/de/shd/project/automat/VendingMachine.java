package de.shd.project.automat;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.shd.project.beverage.Alcoholic;
import de.shd.project.beverage.Beverage;
import de.shd.project.beverage.Caffeinated;
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
      return beverages.stream().anyMatch(beverage -> beverage.getName().equals(name));
   }

   @Override
   public double calculatePriceForOneBottleOf(String name) {
      return findBeverage(name).map(beverage -> Bottle.PRICE_FOR_BOTTLE + Bottle.MAX_AMOUNT_IN_LITER * beverage.getPricePerLiter()).orElseThrow(VendingMachineException::new);
   }

   @Override
   public VendingMachinePurchase buyBeverage(String name, double money) {

      return findBeverage(name)
         .map(beverage ->
         {
            Bottle<Beverage> bottle = new Bottle<>(beverage);
            return new VendingMachinePurchase(bottle, money - bottle.getPrice());
         }).orElseThrow(VendingMachineException::new);
   }

   @Override
   public double calculatePriceForBottlesOf(String name, int numberOfBottles) {
      return calculatePriceForOneBottleOf(name) * numberOfBottles;
   }

   @Override
   public double calculateTotalValueOfAllBeverages() {
      return getBeverages().stream().map(beverage -> beverage.getPricePerLiter() * beverage.getAmount()).mapToDouble(Double::doubleValue).sum();
   }

   @Override
   public Set<Beverage> getAllAlcoholicBeverages() {
      return getBeverages().stream().filter(Alcoholic.class::isInstance).collect(Collectors.toSet());
   }

   @Override
   public Set<Beverage> getAllCaffeinatedBeverages() {
      return getBeverages().stream().filter(Caffeinated.class::isInstance).collect(Collectors.toSet());
   }

   @Override
   public double calculateTotalValueOfAllAlcoholicBeverages() {
      return getAllAlcoholicBeverages().stream().mapToDouble(beverage -> beverage.getPricePerLiter() * beverage.getAmount()).sum();
   }

   @Override
   public Set<String> displayNamesOfAllAlcoholicBeverages() {
      return Set.of(getAllAlcoholicBeverages().stream().map(Beverage::getName).collect(Collectors.joining(", ")));
   }

   @Override
   public List<String> displayNamesOfAllNonAlcoholicBeverages() {
      return List.of(getBeverages().stream().filter(alkohol -> !(alkohol instanceof Alcoholic)).map(Beverage::getName).collect(Collectors.joining(", ")));
   }

   @Override
   public Map<Class<? extends Beverage>, List<Beverage>> getBeveragesGroupedByClass() {
      return beverages.stream().collect(Collectors.groupingBy(Beverage::getClass));
   }

   @Override
   public String displayAllBeverageNamesSeparatedByComma() {
      return getBeverages().stream().map(Beverage::getName).collect(Collectors.joining(", "));
   }

   @Override
   public List<Beverage> getAllBeveragesWithAmountBelowThreshold(int threshold) {
      return beverages.stream().filter(beverage -> beverage.getAmount() < threshold).toList();
   }

   @Override
   public List<Beverage> getOnlyColdBeverages() {
      return beverages.stream().filter(beverage -> beverage.getTemperature() <= 4).toList();
   }

   @Override
   public List<Beverage> getOnlyHotBeverages() {
      return beverages.stream().filter(beverage -> beverage.getTemperature() >= 8).toList();
   }

   @Override
   public double calculateAverageTemperatureOfAllBeverages() {
      return beverages.stream().mapToDouble(Beverage::getTemperature).average().orElseThrow(VendingMachineException::new);
   }

   @Override
   public double calculateAverageAlcoholicStrengthOfAllAlcoholicBeverages() {
      return beverages.stream().filter(Alcoholic.class::isInstance).map(Alcoholic.class::cast).mapToDouble(Alcoholic::getAlcoholStrength).average().orElseThrow(VendingMachineException::new);
   }

   @Override
   public Map<String, Beverage> getAllBeveragesMappedByName() {
      return beverages.stream().collect(Collectors.toMap(Beverage::getName, beverage -> beverage));
   }

   @Override
   public double getMultipliedAmountsOfBeverages() {
      return beverages.stream().mapToDouble(Beverage::getAmount).reduce(1,(a,b)-> a * b);
   }

   @Override
   public List<Beverage> getTopFiveBeveragesWithTheLeastAmountOrderedByAmountDescending() {
      return beverages.stream().sorted(Comparator.comparingDouble(Beverage::getAmount)).limit(5).sorted(Comparator.comparingDouble(Beverage::getAmount).reversed()).toList();
   }

   @Override
   public List<Beverage> getListByFilter(Predicate<Beverage> filter) {
      return beverages.stream().filter(filter).toList();
   }

   @Override
   public List<Double> getListOfCurrentAmountsOfBeverages() {
      return beverages.stream().map(Beverage::getAmount).toList();
   }

   @Override
   public List<Beverage> findAllAffordableBeverages(double budget) {
      return beverages.stream().filter(beverage -> (Bottle.PRICE_FOR_BOTTLE + Bottle.MAX_AMOUNT_IN_LITER * beverage.getPricePerLiter()) <= budget).toList();
   }
}
