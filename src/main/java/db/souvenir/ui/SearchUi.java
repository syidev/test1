package db.souvenir.ui;

import db.souvenir.model.Item;
import db.souvenir.model.Vendor;

import java.util.*;

public interface SearchUi extends Ui {
    int SEARCH_ITEM_BY_VENDOR = 1;
    int SEARCH_ITEM_BY_COUNTRY = 2;
    int SEARCH_VENDOR_BY_ITEM_PRICE = 3;
    int SEARCH_VENDOR_BY_ITEM_NAME_AND_ITEM_MANUFACTURE_YEAR = 4;
    int GET_ALL_VENDORS_WITH_ITEMS = 5;
    int GET_ALL_ITEMS_GROUPED_BY_YEAR = 6;
    int EXIT = 9;

    int printMenu();
    String getItemsByVendorForm();
    String getItemsByCountryForm();
    double getVendorsByItemsPriceForm();
    Map<String, ?> getVendorsByItemNameAndItemYearForm();
    void printVendors(Optional<List<Vendor>> vendors);
    void printItemsGroupedByYear(Optional<Map<Integer, List<Item>>> items);
    void printVendorData(Optional<List<Vendor>> vendors);
    void printItemData(Optional<List<Item>> items);
}
