package db.souvenir.ui;

import db.souvenir.model.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EditItemUi extends Ui {
    int ADD = 1;
    int UPDATE = 2;
    int DELETE = 3;
    int GET_ALL = 4;
    int EXIT = 9;

    int printMenu();
    Map<String, ?> getAddForm();
    Map<String, String> getDeleteForm();
    Item getUpdateForm(Item item);
    Map<String, String> getVendorAndItemForm();
    void printData(Optional<List<Item>> items) throws Exception;
}
