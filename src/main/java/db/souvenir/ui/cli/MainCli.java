package db.souvenir.ui.cli;

import db.souvenir.ui.MainUi;

import java.util.Map;
import java.util.TreeMap;

public class MainCli extends Cli implements MainUi {
    @Override
    public int printMenu() {
        return printMenu("SouvenirDB", new TreeMap<>(Map.of(EDIT, "Edit", SEARCH,"Search")), EXIT);
    }
}
