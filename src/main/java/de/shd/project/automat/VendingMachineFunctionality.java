package de.shd.project.automat;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

import de.shd.project.beverage.Alcoholic;
import de.shd.project.beverage.Beverage;
import de.shd.project.beverage.Caffeinated;
import de.shd.project.beverage.InsufficientBeverageException;


/**
 * Definiert die Funktionalität, die der Getränkeautomat besitzen soll.
 *
 * @author Christoph Gragert (cgr@shd.de)
 */
@SuppressWarnings("unused")
public interface VendingMachineFunctionality
{
   /**
    * Liefert eine Liste der Namen aller Getränke
    */
   List<String> displayNamesOfAllBeverages();

   /**
    * Für den übergebenen Namen wird das erst beste Getränk zurückgegeben.
    * Ein Getränk kann bereits gefunden werden, wenn
    * nur der Anfang des Namens angegeben wird. (Case-Sensitiv)
    *
    * @param name Anfang oder vollständiger Name eines Getränks
    */
   Optional<Beverage> findBeverage(final String name);

   /**
    * Prüft, ob für den angegebenen Namen ein Getränk im Automaten existiert.
    *
    * @param name Name des Getränks (Namen müssen vollständig übereinstimmen)
    * @return true, falls es das Getränk im Automat gibt, ansonsten false.
    */
   boolean isBeverageListed(final String name);

   /**
    * Es wird für den übergebenen Getränkenamen der Preis berechnet.
    * Der Inhalt des Getränks im Getränkeautomat darf sich dabei nicht ändern.
    * <p>
    * Der Preis für eine Flasche berechnet sich auf dem Basispreis der Flasche und dem Preis für die Menge des Getränks, welches sich
    * in der Flasche befindet.<br>
    * Beispiel:
    * <pre>
    *       Flasche: Basispreis: 0.25
    *                Menge in der Flasche: 0.5 Liter
    *       Getränk: Preis pro Liter: 2.50
    *       Rechnung: 0.25 + 0.5 * 2.5 = 1.5
    *    </pre>
    * </p>
    *
    * @param name Name
    * @return Preis für eine Flasche des Getränks
    */
   double calculatePriceForOneBottleOf(final String name);

   /**
    * Für den übergebenen Namen und das Geld soll ein Getränk gekauft werden.
    * Der Vorrat im Getränkeautomat verringert sich um den Inhalt der Flasche.
    *
    * @param name  Name des Getränks (Muss analog zu {@link #findBeverage(String)} nicht
    *              vollständig sein)
    * @param money Betrag der für das Getränk bezahlt wird.
    * @return ein {@link VendingMachinePurchase} in dem die gekaufte Flasche und das Restgeld
    * enthalten ist.
    * @throws InsufficientBeverageException Wird geworfen, falls nicht mehr genug vom
    *                                       Getränk vorhanden ist.
    * @throws VendingMachineException       Wird geworfen, falls für den Namen kein
    *                                       Getränk gefunden werden konnte
    *                                       oder das Geld nicht ausreichend ist.
    *                                       Der Inhalt des Getränkeautomats darf sich
    *                                       in den Fällen nicht ändern.
    */
   VendingMachinePurchase buyBeverage(final String name, final double money);

   /**
    * Berechnet den Preis für eine beliebige Anzahl von Flaschen eines Getränks
    *
    * @param name            Name des Getränks (siehe {@link #findBeverage(String)})
    * @param numberOfBottles Anzahl der Flaschen
    * @return Preis eines Getränks für die angegebene Anzahl von Flaschen
    */
   double calculatePriceForBottlesOf(final String name, final int numberOfBottles);

   /**
    * Berechnet den Gesamtwert von Getränken, die sich aktuell im Getränkeautomat befinden.
    * Es wird nur der Werte der Getränke betrachtet.<br>
    * Beispiel:
    * <pre>
    *       Getränk1: Preis pro Liter: 2.50
    *                Menge im Automaten: 10 Liter
    *       Getränk2: Preis pro Liter: 1.50
    *                Menge im Automaten: 20 Liter
    *       Rechnung: 2.5 * 10 + 1.5 * 20 = 55
    * </pre>
    */
   double calculateTotalValueOfAllBeverages();

   /**
    * Liefert alle alkoholischen Getränke zurück. Ein Getränk zählt als alkoholisch, wenn es {@link Alcoholic} implementiert.
    * Es ist hier unerheblich, ob {@link Alcoholic#getAlcoholStrength()} einen Wert > 0 zurückliefert.
    */
   Set<Beverage> getAllAlcoholicBeverages();

   /**
    * Liefert alle koffeinhaltigen Getränke zurück. Ein Getränk zählt als alkoholisch, wenn es {@link Caffeinated} implementiert.
    * Es ist hier unerheblich, ob {@link Caffeinated#getCaffeineStrength()} einen Wert > 0 zurückliefert.
    */
   Set<Beverage> getAllCaffeinatedBeverages();

   /**
    * Berechnet analog zu {@link #calculateTotalValueOfAllBeverages()} den Wert für alle alkoholischen Getränke
    */
   double calculateTotalValueOfAllAlcoholicBeverages();

   /**
    * Liefert ein Set von Namen aller alkoholischen Getränke zurück.
    */
   Set<String> displayNamesOfAllAlcoholicBeverages();

   /**
    * Liefert eine Liste von Namen aller nicht-alkoholischen Getränke zurück. Hier gilt analog zu {@link #getAllAlcoholicBeverages()}: Ein Getränk
    * ist nicht-alkoholisch, wenn es {@link Alcoholic} nicht implementiert. Der Rückgabewert von {@link Alcoholic#getAlcoholStrength()} spielt hier
    * keine Rolle.
    */
   List<String> displayNamesOfAllNonAlcoholicBeverages();

   /**
    * Liefert eine Map zurück, die alle Getränke nach ihrer Klasse gruppiert.
    */
   Map<Class<? extends Beverage>, List<Beverage>> getBeveragesGroupedByClass();

   /**
    * Liefert einen String zurück der alle Namen von Getränken kommasepariert ausgibt.
    * Ist das Getränk alkoholisch, so soll zusätzlich zum Namen noch "alkoholisch" in
    * Klammen ausgegeben werden.
    * <pre>
    *    Beispiel: Coca Cola, Königsbacher (alkoholisch), Espresso (koffeinhaltig), ...
    * </pre>
    */
   String displayAllBeverageNamesSeparatedByComma();

   /**
    * Liefert eine Liste aller Getränke, deren Inhalt im Getränkeautomat unter der übergebenen
    * Grenze liegt.<br>
    * Beispiel:
    * <pre>
    *       Getränk1: Restmenge 1 Liter
    *       Getränk2: Restmenge 3 Liter
    *       Grenze: 3 Liter
    *       Return: List.of(Getränk1)
    * </pre>
    *
    * @param threshold Grenze unter, der der Inhalt der Getränke liegen soll
    */
   List<Beverage> getAllBeveragesWithAmountBelowThreshold(int threshold);

   /**
    * Liefert alle Beverages zurück die "kalt" sind.
    * (<b>Hinweis:</b> Ihr könnt frei definieren, wann ein Getränk für euch die Bedingung "kalt" erfüllt.)
    */
   List<Beverage> getOnlyColdBeverages();

   /**
    * Liefert alle Beverages zurück die "heiß" sind.
    * (<b>Hinweis:</b> Ihr könnt frei definieren, wann ein Getränk für euch die Bedingung "heiß" erfüllt.)
    */
   List<Beverage> getOnlyHotBeverages();

   /**
    * Berechnet die Durchschnittstemperatur aller Getränke. Eine Mengenverteilung spielt hier keine Rolle.<br>
    * Beispiel:
    * <pre>
    *       Getränk1: 10°C
    *       Getränk2: 20°C
    *       Rechnung: 10 + 20 / 2 = 15
    * </pre>
    */
   double calculateAverageTemperatureOfAllBeverages();

   /**
    * Berechnet den durchschnittlichen Alkoholgehalt aller alkoholischen Getränke.
    * Rechnung analog zu {@link #calculateAverageTemperatureOfAllBeverages()}.
    */
   double calculateAverageAlcoholicStrengthOfAllAlcoholicBeverages();

   /**
    * Liefert eine Map zurück, die dem Namen eines Getränks immer das Getränk selbst zuordnet.
    */
   Map<String, Beverage> getAllBeveragesMappedByName();

   /**
    * Liefert das Produkt über die Menge aller Getränke.<br>
    * Beispiel:
    * <pre>
    *    Getränk1: Menge 10 Liter
    *    Getränk2: Menge 20 Liter
    *    Rechnung: 10 * 20 = 200
    * </pre>
    * (<b>Hinweis</b>: Hier soll die Methode {@link java.util.stream.Stream#reduce(Object, BinaryOperator)} verwendet werden.)
    */
   double getMultipliedAmountsOfBeverages();

   /**
    * Liefert die 5 Getränke mit dem geringsten Inhalt sortiert nach dem Inhalt absteigend zurück.<br>
    * Beispiel:
    * <pre>
    *    Getränk1: Menge 1 Liter
    *    Getränk2: Menge 5 Liter
    *    Getränk3: Menge 25 Liter
    *    Getränk4: Menge 17 Liter
    *    Getränk5: Menge 2 Liter
    *    Getränk6: Menge 9 Liter
    *    Getränk7: Menge 18 Liter
    *
    *    Ausgabe: [Getränk4, Getränk6, Getränk2, Getränk5, Getränk1]
    * </pre>
    */
   List<Beverage> getTopFiveBeveragesWithTheLeastAmountOrderedByAmountDescending();

   /**
    * Liefert eine Liste von Getränken für den übergebenen Filter zurück.
    *
    * @param filter Filter
    */
   List<Beverage> getListByFilter(final Predicate<Beverage> filter);


   /**
    * Liefert eine Liste mit allen Füllständen zurück. Die Füllstände soll eindeutig sein, d.h. es dürfen keine doppelten Einträge in der Liste
    * vorkommen.
    */
   List<Double> getListOfCurrentAmountsOfBeverages();

   /**
    * Liefert eine Liste aller Getränke zurück, die sich eine Person für das übergebene Budget leisten kann. Die Liste von Getränken soll vom
    * günstigsten zum teuersten sortiert sein.
    *
    * @param budget Budget des Käufers
    */
   List<Beverage> findAllAffordableBeverages(final double budget);
}
