package db.souvenir.ui;

import db.souvenir.model.Vendor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EditVendorUi extends Ui {
    int ADD = 1;
    int UPDATE = 2;
    int DELETE = 3;
    int GET_ALL = 4;
    int EXIT = 9;

    int printMenu();
    Map<String, String> getAddForm();
    Map<String, String> getDeleteForm();
    Vendor getUpdateForm(Vendor vendor);
    String getVendorForm();
    void printData(Optional<List<Vendor>> vendors);
}
