package db.souvenir.ui;

import java.util.List;
import java.util.Map;

public interface Ui {
    void printSimpleList(List<?> list);
    void printNestedList(Map<?, ? extends List> map);
    void printHeader(String header);
    int printMenu(String header, Map<Integer, String> menu, int exitCode);
    void printNotification(String notification);
}
