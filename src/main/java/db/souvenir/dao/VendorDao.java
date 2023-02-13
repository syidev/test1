package db.souvenir.dao;

import db.souvenir.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorDao {
    Optional<List<Vendor>> findByItemWithPriceLessThen(double price) throws Exception;
    Optional<List<Vendor>> findByItemAndYear(String item, int year) throws Exception;
    Optional<Vendor> findByName(String vendorName) throws Exception;
    Optional<List<Vendor>> findAll() throws Exception;
    boolean delete(String vendorName) throws Exception;
    boolean save(Vendor vendor) throws Exception;
    boolean update(String vendorName, Vendor newVendor) throws Exception;
}
