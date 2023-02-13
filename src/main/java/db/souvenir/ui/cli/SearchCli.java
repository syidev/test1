package db.souvenir.ui.cli;

import db.souvenir.model.Item;
import db.souvenir.model.Vendor;
import db.souvenir.ui.SearchUi;

import java.util.*;

public class SearchCli extends Cli implements SearchUi {
    @Override
    public int printMenu() {
        return printMenu("SEARCH", new TreeMap<>(Map.of(
            SEARCH_ITEM_BY_VENDOR, "Search item by vendor",
            SEARCH_ITEM_BY_COUNTRY, "Search item by country",
            SEARCH_VENDOR_BY_ITEM_PRICE, "Search vendor by item's price",
            SEARCH_VENDOR_BY_ITEM_NAME_AND_ITEM_MANUFACTURE_YEAR, "Search vendor by item's name and item's manufacture year",
            GET_ALL_VENDORS_WITH_ITEMS, "Get all vendors with items",
            GET_ALL_ITEMS_GROUPED_BY_YEAR, "Get all items grouped by year"
        )), EXIT);
    }

    @Override
    public String getItemsByVendorForm() {
        printHeader("Search item by vendor");

        return getStringInput("Enter vendor");
    }

    @Override
    public String getItemsByCountryForm() {
        printHeader("Search item by country");

        return getStringInput("Enter country");
    }

    @Override
    public double getVendorsByItemsPriceForm() {
        printHeader("Search vendor by item's price");

        return getDoubleInput("Enter price");
    }

    @Override
    public Map<String, ?> getVendorsByItemNameAndItemYearForm() {
        printHeader("Search vendor by item's name and item's manufacture year");
        String name = getStringInput("Enter item name");
        int year = getIntegerInput("Enter year");

        return new HashMap<>(Map.of("name", name, "year", year));
    }

    @Override
    public void printVendors(Optional<List<Vendor>> vendors) {
        printHeader("Get all vendors with items");
        vendors.ifPresentOrElse(this::printSimpleList, () -> printNotification("Vendors not found"));
    }

    @Override
    public void printItemsGroupedByYear(Optional<Map<Integer, List<Item>>> items) {
        printHeader("Get all items grouped by year");
        items.ifPresentOrElse(this::printNestedList, () -> printNotification("Items not found"));
    }

    @Override
    public void printVendorData(Optional<List<Vendor>> vendors) {
        vendors.ifPresentOrElse(this::printSimpleList, () -> printNotification("Vendors not found"));
    }

    @Override
    public void printItemData(Optional<List<Item>> items) {
        items.ifPresentOrElse(this::printSimpleList, () -> printNotification("Items not found"));
    }
}
