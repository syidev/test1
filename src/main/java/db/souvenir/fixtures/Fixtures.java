package db.souvenir.fixtures;

import db.souvenir.db.FileSource;
import db.souvenir.model.Vendor;
import db.souvenir.model.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fixtures {
    public static void run() throws Exception {
        try {
            Vendor apple = new Vendor("Apple", "USA");
            String appleSlogan = "Think Different";
            Item appleItem1 = new Item("T-shirt", appleSlogan, LocalDate.of(2020, 1, 1), 15);
            Item appleItem2 = new Item("Mug", appleSlogan, LocalDate.of(2021, 1, 1), 10);
            Item appleItem3 = new Item("Pen", appleSlogan, LocalDate.of(2022, 1, 1), 5);
            List<Item> appleValue = new ArrayList<>();
            appleValue.add(appleItem1);
            appleValue.add(appleItem2);
            appleValue.add(appleItem3);

            Vendor huawei = new Vendor("Huawei", "China");
            String huaweiSlogan = "Make it Possible";
            Item huaweiItem1 = new Item("Shirt", huaweiSlogan, LocalDate.of(2022, 1, 1), 9);
            Item huaweiItem2 = new Item("Thermal bottle", huaweiSlogan, LocalDate.of(2023, 1, 1), 6);
            Item huaweiItem3 = new Item("Pen", huaweiSlogan, LocalDate.of(2021, 1, 1), 3);
            List<Item> huaweiValue = new ArrayList<>();
            huaweiValue.add(huaweiItem1);
            huaweiValue.add(huaweiItem2);
            huaweiValue.add(huaweiItem3);

            Vendor samsung = new Vendor("Samsung", "South Korea");
            String samsungSlogan = "Samsung, Do What You Can't";
            Item samsungItem1 = new Item("Baseball cap", samsungSlogan, LocalDate.of(2019, 1, 1), 12);
            Item samsungItem2 = new Item("Notepad", samsungSlogan, LocalDate.of(2020, 1, 1), 8);
            Item samsungItem3 = new Item("Pen", samsungSlogan, LocalDate.of(2022, 1, 1), 4);
            List<Item> samsungValue = new ArrayList<>();
            samsungValue.add(samsungItem1);
            samsungValue.add(samsungItem2);
            samsungValue.add(samsungItem3);

            Map<Vendor, List<Item>> data = new HashMap<>();

            data.put(apple, appleValue);
            data.put(huawei, huaweiValue);
            data.put(samsung, samsungValue);

            FileSource.getInstance().write(data);
        } catch (Throwable e) {
            throw new Exception("Fixtures loading failed");
        }
    }
}
