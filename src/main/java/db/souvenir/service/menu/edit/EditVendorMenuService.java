package db.souvenir.service.menu.edit;

import db.souvenir.dao.VendorDao;
import db.souvenir.model.Vendor;
import db.souvenir.service.menu.Displayable;
import db.souvenir.ui.EditVendorUi;

import java.util.Map;
import java.util.Optional;

public class EditVendorMenuService implements Displayable {
    private final VendorDao vendorDao;
    private final EditVendorUi ui;

    public EditVendorMenuService(VendorDao vendorDao, EditVendorUi ui) {
        this.vendorDao = vendorDao;
        this.ui = ui;
    }

    public void display() throws Exception {
         int input = -1;
         while(input != ui.EXIT) {
             input = ui.printMenu();

             if (ui.ADD == input) add();
             if (ui.UPDATE == input) update();
             if (ui.DELETE == input) delete();
             if (ui.GET_ALL == input) printData();
         }
    }

    private void add() throws Exception {
        Map<String, String> data = ui.getAddForm();
        boolean isSaved = vendorDao.save(new Vendor(data.get("name"), data.get("country")));
        ui.printNotification(isSaved ? "Added" : "Error");
    }

    private void delete() throws Exception {
        Map<String, String> data = ui.getDeleteForm();
        boolean isDeleted = vendorDao.delete(data.get("name"));
        ui.printNotification(isDeleted ? "Deleted" : "Error");
    }

    private void update() throws Exception {
        String vendorName = ui.getVendorForm();

        Optional<Vendor> vendor = vendorDao.findByName(vendorName);

        if (vendor.isPresent()) {
            Vendor updatedVendor = ui.getUpdateForm(vendor.get());

            boolean isUpdated = vendorDao.update(vendorName, updatedVendor);
            ui.printNotification(isUpdated ? "Updated" : "Error");
        }
    }

    private void printData() throws Exception {
        ui.printData(vendorDao.findAll());
    }
}
