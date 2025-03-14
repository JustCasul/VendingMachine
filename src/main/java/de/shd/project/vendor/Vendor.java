package de.shd.project.vendor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.shd.project.automat.VendingMachine;
import de.shd.project.automat.VendingMachineException;
import de.shd.project.beverage.Alcoholic;
import de.shd.project.beverage.Beverage;
import de.shd.project.container.Bottle;
import de.shd.project.supplier.VendingMachineSupplier;


/**
 * <h3>Repräsentation für einen Verkäufer/Sammlung von Getränkeautomaten</h3>
 * <p>
 * Analog zu {@link VendingMachine#beverageSupplier} gibt für den Verkäufer auch einen {@link #vendingMachineSupplier}, der ihn mit
 * einer Auswahl an unterschiedlichen Getränkeautomaten beliefert. Die Automaten werden in {@link #vendingMachines} verwaltet. Das Sortiment an
 * Getränkeautomaten wird nur gefüllt, wenn {@link #restock()} aufgerufen wird.
 * </p>
 *
 * @author Christoph Gragert (cgr@shd.de)
 */
@SuppressWarnings("JavadocReference")
public class Vendor implements VendorFunctionality
{
   private List<VendingMachine> vendingMachines = new ArrayList<>();
   private VendingMachineSupplier vendingMachineSupplier;

   public void supplyVendingMachines(final VendingMachineSupplier vendingMachineSupplier)
   {
      this.vendingMachineSupplier = vendingMachineSupplier;
   }

   public void restock()
   {
      vendingMachines = vendingMachineSupplier.supplyVendingMachines();
      vendingMachines.forEach(VendingMachine::restock);
   }

   @Override
   public Set<Beverage> getBeveragesOfAllVendingMachines() {
      return vendingMachines.stream().flatMap(vendingMachine -> vendingMachine.getBeverages().stream()).collect(Collectors.toSet());
   }

   @Override
   public long countNumberOfBeveragesOfAllVendingMachines() {
      return vendingMachines.stream().flatMap(vendingMachine -> vendingMachine.getBeverages().stream()).toList().size();

   }

   @Override
   public long countNumberOfBeveragesDistinctOfAllVendingMachines() {
      return getBeveragesOfAllVendingMachines().size();
   }

   @Override
   public VendingMachine getVendingMachineWithTheMostBeverages() {
      return vendingMachines.stream().max(Comparator.comparingInt(vendingMachine -> vendingMachine.getBeverages().size())).orElseThrow(VendingMachineException::new);
   }

   @Override
   public Beverage findTheCheapestNonAlcoholicBeverage() {
      return vendingMachines.stream().flatMap(vendingMachine -> vendingMachine.getBeverages().stream()).filter(alkohol -> !(alkohol instanceof Alcoholic)).min(Comparator.comparing(beverage -> Bottle.PRICE_FOR_BOTTLE + Bottle.MAX_AMOUNT_IN_LITER * beverage.getPricePerLiter())).orElseThrow(VendingMachineException::new);
   }
}
