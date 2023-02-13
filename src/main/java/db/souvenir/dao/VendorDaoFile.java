package db.souvenir.dao;

import db.souvenir.db.FileSource;
import db.souvenir.model.Item;
import db.souvenir.model.Vendor;

import java.util.*;
import java.util.stream.Collectors;

public class VendorDaoFile implements VendorDao {
    @Override
    public Optional<List<Vendor>> findByItemWithPriceLessThen(double price) throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        List<Vendor> vendors = data.entrySet().stream()
            .filter(e -> e.getValue().stream().anyMatch(i -> i.getPrice() < price))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        if (!vendors.isEmpty()) {
            return Optional.of(vendors);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Vendor>> findByItemAndYear(String item, int year) throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        List<Vendor> vendors = data.entrySet().stream()
            .filter(
                e -> e.getValue().stream().anyMatch(i -> i.getName().equals(item)) &&
                e.getValue().stream().anyMatch(i -> i.getMfgDate().getYear() == year)
            )
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        if (!vendors.isEmpty()) {
            return Optional.of(vendors);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vendor> findByName(String vendorName) throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        List<Vendor> vendors = data.keySet().stream()
            .filter(v -> v.getName().equals(vendorName))
            .collect(Collectors.toList());
        
        if (!vendors.isEmpty()) {
            return Optional.of(vendors.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Vendor>> findAll() throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();
        List<Vendor> vendors = data.keySet().stream().collect(Collectors.toCollection(ArrayList::new));

        if (!vendors.isEmpty()) {
            return Optional.of(vendors);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(String vendorName) {
        try {
            Map<Vendor, List<Item>> data = FileSource.getInstance().read();
            data.entrySet().removeIf(e -> e.getKey().getName().equals(vendorName));
            FileSource.getInstance().write(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(Vendor vendor) {
        try {
            Map<Vendor, List<Item>> data = FileSource.getInstance().read();
            List<Item> items = new ArrayList<>();
            data.put(vendor, items);
            FileSource.getInstance().write(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(String vendorName, Vendor newVendor) {
        try {
            Map<Vendor, List<Item>> data = FileSource.getInstance().read();
            Optional<Vendor> vendor = findByName(vendorName);

            if (vendor.isEmpty()) {
                return false;
            }

            List<Item> items = data.entrySet().stream()
                .filter(e -> e.getKey().getName().equals(vendorName))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

            data.entrySet().removeIf(e -> e.getKey().getName().equals(vendorName));
            data.put(newVendor, items);
            FileSource.getInstance().write(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
