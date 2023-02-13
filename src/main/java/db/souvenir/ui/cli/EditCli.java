package db.souvenir.ui.cli;

import db.souvenir.ui.EditUi;

import java.util.Map;
import java.util.TreeMap;

public class EditCli extends Cli implements EditUi {
    @Override
    public int printMenu() {
        return printMenu("EDIT", new TreeMap<>(Map.of(EDIT_VENDORS, "Edit vendors", EDIT_ITEMS,"Edit items")), EXIT);
    }
}
