package db.souvenir.service.menu.edit;

import db.souvenir.dao.ItemDao;
import db.souvenir.model.Item;
import db.souvenir.service.menu.Displayable;
import db.souvenir.ui.EditItemUi;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class EditItemMenuService implements Displayable {
    private final ItemDao itemDao;
    private final EditItemUi ui;

    public EditItemMenuService(ItemDao itemDao, EditItemUi ui) {
        this.itemDao = itemDao;
        this.ui = ui;
    }

    public void display() throws Exception {
        int input = -1;
        while(input != ui.EXIT) {
            input = ui.printMenu();

            if(ui.ADD == input) add();
            if(ui.UPDATE == input) update();
            if(ui.DELETE == input) delete();
            if(ui.GET_ALL == input) printData();
        }
    }

    private void add() throws Exception {
        Map<String, ?> data = ui.getAddForm();

        Item item = new Item();
        item.setName((String) data.get("name"));
        item.setMfgDate(LocalDate.of((int) data.get("year"), 1, 1));
        item.setPrice((double) data.get("price"));
        item.setVendorData((String) data.get("vendorData"));

        boolean isSaved = itemDao.save((String) data.get("vendorName"), item);
        ui.printNotification(isSaved ? "Added" : "Error");
    }

    private void delete() throws Exception {
        Map<String, String> data = ui.getDeleteForm();
        boolean isDeleted = itemDao.delete(data.get("vendorName"), data.get("itemName"));
        ui.printNotification(isDeleted ? "Deleted" : "Error");
    }

    private void update() throws Exception {
        Map<String, String> data = ui.getVendorAndItemForm();

        Optional<Item> item = itemDao.findByNameAndVendor(data.get("itemName"), data.get("vendorName"));

        if (item.isPresent()) {
            Item updatedItem = ui.getUpdateForm(item.get());
            boolean isUpdated = itemDao.update(data.get("vendorName"), item.get().getName(), updatedItem);
            ui.printNotification(isUpdated ? "Updated" : "Error");
        }
    }

    private void printData() throws Exception {
        ui.printData(itemDao.findAll());
    }
}
