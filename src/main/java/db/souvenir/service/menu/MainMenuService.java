package db.souvenir.service.menu;

import db.souvenir.dao.ItemDaoFile;
import db.souvenir.dao.VendorDaoFile;
import db.souvenir.ui.MainUi;
import db.souvenir.ui.cli.EditCli;
import db.souvenir.ui.cli.SearchCli;

public class MainMenuService implements Displayable {
    private final MainUi ui;

    public MainMenuService(MainUi ui) {
        this.ui = ui;
    }

    public void display() throws Exception {
        int input = -1;
        while (input != ui.EXIT) {
            input = ui.printMenu();

            if (ui.EDIT == input) (new EditMenuService(new EditCli())).display();
            if (ui.SEARCH == input) (new SearchMenuService(new VendorDaoFile(), new ItemDaoFile(), new SearchCli())).display();
        }
    }
}
