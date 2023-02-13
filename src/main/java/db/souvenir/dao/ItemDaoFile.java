package db.souvenir.dao;

import db.souvenir.db.FileSource;
import db.souvenir.model.Vendor;
import db.souvenir.model.Item;

import java.util.*;
import java.util.stream.Collectors;

public class ItemDaoFile implements ItemDao {
    @Override
    public Optional<List<Item>> findByVendor(String vendor) throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        List<Item> items = data.entrySet().stream()
            .filter(e -> e.getKey().getName().equals(vendor))
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        if (!items.isEmpty()) {
            return Optional.of(items);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Item>> findByCountry(String country) throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        List<Item> items = data.entrySet().stream()
            .filter(e -> e.getKey().getCountry().equals(country))
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        if (!items.isEmpty()) {
            return Optional.of(items);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Map<Integer, List<Item>>> getGroupedByYear() throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        Map<Integer, List<Item>> result = data.values().stream()
            .flatMap(Collection::stream)
            .collect(Collectors.groupingBy(i -> i.getMfgDate().getYear()));

        if (!result.isEmpty()) {
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Item> findByNameAndVendor(String name, String vendorName) throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        List<Item> items = data.entrySet().stream()
            .filter(e -> e.getKey().getName().equals(vendorName))
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .filter(i -> i.getName().equals(name))
            .collect(Collectors.toList());

        if (!items.isEmpty()) {
            return Optional.of(items.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Item>> findAll() throws Exception {
        Map<Vendor, List<Item>> data = FileSource.getInstance().read();

        List<Item> items = data.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

        if (!items.isEmpty()) {
            return Optional.of(items);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean save(String vendorName, Item item) {
        try {
            Map<Vendor, List<Item>> data = FileSource.getInstance().read();

            List<Map.Entry<Vendor, List<Item>>> entries = data.entrySet().stream()
                .filter(e -> e.getKey().getName().equals(vendorName))
                .collect(Collectors.toList());

            if (entries.isEmpty()) {
                return false;
            }

            entries.get(0).getValue().add(item);
            data.remove(entries.get(0).getKey());
            data.put(entries.get(0).getKey(), entries.get(0).getValue());

            FileSource.getInstance().write(data);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(String vendorName, String itemName, Item updatedItem) {
        try {
            Map<Vendor, List<Item>> data = FileSource.getInstance().read();

            List<Map.Entry<Vendor, List<Item>>> entries = data.entrySet().stream()
                .filter(e -> e.getKey().getName().equals(vendorName))
                .collect(Collectors.toList());

            if (entries.isEmpty()) {
                return false;
            }

            List<Item> items = entries.get(0).getValue().stream()
                .filter(i -> !i.getName().equals(itemName))
                .collect(Collectors.toList());

            data.remove(entries.get(0).getKey());
            items.add(updatedItem);
            data.put(entries.get(0).getKey(), items);

            FileSource.getInstance().write(data);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String vendorName, String itemName) {
        try {
            Map<Vendor, List<Item>> data = FileSource.getInstance().read();

            List<Map.Entry<Vendor, List<Item>>> entries = data.entrySet().stream()
                .filter(e -> e.getKey().getName().equals(vendorName))
                .collect(Collectors.toList());

            if (entries.isEmpty()) {
                return false;
            }

            List<Item> items = entries.get(0).getValue().stream()
                .filter(i -> !i.getName().equals(itemName))
                .collect(Collectors.toList());

            data.remove(entries.get(0).getKey());
            data.put(entries.get(0).getKey(), items);

            FileSource.getInstance().write(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
