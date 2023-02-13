package db.souvenir.dao;

import db.souvenir.model.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemDao {
    Optional<List<Item>> findByVendor(String vendor) throws Exception;
    Optional<List<Item>> findByCountry(String country) throws Exception;
    Optional<Map<Integer, List<Item>>> getGroupedByYear() throws Exception;
    Optional<Item> findByNameAndVendor(String name, String vendorName) throws Exception;
    Optional<List<Item>> findAll() throws Exception;
    boolean save(String vendorName, Item item) throws Exception;
    boolean update(String vendorName, String itemName, Item item) throws Exception;
    boolean delete(String vendorName, String itemName) throws Exception;
}
