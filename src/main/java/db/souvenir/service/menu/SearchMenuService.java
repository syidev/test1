package db.souvenir.service.menu;

import db.souvenir.dao.ItemDao;
import db.souvenir.dao.VendorDao;
import db.souvenir.ui.SearchUi;

import java.util.Map;

public class SearchMenuService implements Displayable {
    private final VendorDao vendorDao;
    private final ItemDao itemDao;
    private final SearchUi ui;

    public SearchMenuService(VendorDao vendorDao, ItemDao itemDao, SearchUi ui) {
        this.vendorDao = vendorDao;
        this.itemDao = itemDao;
        this.ui = ui;
    }

    public void display() throws Exception {
        int input = -1;
        while (input != ui.EXIT) {
            input = ui.printMenu();

            if (ui.SEARCH_ITEM_BY_VENDOR == input) getItemsByVendor();
            if (ui.SEARCH_ITEM_BY_COUNTRY == input) getItemsByCountry();
            if (ui.SEARCH_VENDOR_BY_ITEM_PRICE == input) getVendorsByItemsPrice();
            if (ui.SEARCH_VENDOR_BY_ITEM_NAME_AND_ITEM_MANUFACTURE_YEAR == input) getVendorsByItemNameAndItemYear();
            if (ui.GET_ALL_VENDORS_WITH_ITEMS == input) getVendors();
            if (ui.GET_ALL_ITEMS_GROUPED_BY_YEAR == input) getItemsGroupedByYear();
        }
    }

    private void getItemsByVendor() throws Exception {
        String vendor = ui.getItemsByVendorForm();
        ui.printItemData(itemDao.findByVendor(vendor));
    }

    private void getItemsByCountry() throws Exception {
        String country = ui.getItemsByCountryForm();
        ui.printItemData(itemDao.findByCountry(country));
    }

    private void getVendorsByItemsPrice() throws Exception {
        double price = ui.getVendorsByItemsPriceForm();
        ui.printVendorData(vendorDao.findByItemWithPriceLessThen(price));
    }

    private void getVendorsByItemNameAndItemYear() throws Exception {
        Map<String, ?> itemNameAndItemYear = ui.getVendorsByItemNameAndItemYearForm();

        ui.printVendorData(
            vendorDao.findByItemAndYear(
                (String) itemNameAndItemYear.get("name"),
                (int) itemNameAndItemYear.get("year")));
    }

    private void getVendors() throws Exception {
        ui.printVendors(vendorDao.findAll());
    }

    private void getItemsGroupedByYear() throws Exception {
        ui.printItemsGroupedByYear(itemDao.getGroupedByYear());
    }
}
