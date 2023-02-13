package db.souvenir.service.menu;

import db.souvenir.dao.ItemDaoFile;
import db.souvenir.dao.VendorDaoFile;
import db.souvenir.service.menu.edit.EditItemMenuService;
import db.souvenir.service.menu.edit.EditVendorMenuService;
import db.souvenir.ui.EditUi;
import db.souvenir.ui.cli.EditItemCli;
import db.souvenir.ui.cli.EditVendorCli;

public class EditMenuService implements Displayable {
    private final EditUi ui;

    public EditMenuService(EditUi ui) {
        this.ui = ui;
    }

    public void display() throws Exception {
         int input = -1;
         while (input != ui.EXIT) {
             input = ui.printMenu();

             if (ui.EDIT_VENDORS == input) (new EditVendorMenuService(new VendorDaoFile(), new EditVendorCli())).display();
             if (ui.EDIT_ITEMS == input) (new EditItemMenuService(new ItemDaoFile(), new EditItemCli())).display();
         }
     }
}
