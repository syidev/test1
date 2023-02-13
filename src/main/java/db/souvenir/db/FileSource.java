package db.souvenir.db;

import db.souvenir.model.Item;
import db.souvenir.model.Vendor;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FileSource {
    private static FileSource instance;
    private static String dbUrl;

    private FileSource() throws Exception {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            dbUrl = prop.getProperty("db.url");
        } catch (Exception e) {
            throw new Exception("Cannot get DB url");
        }
    }

    public void write(Map<Vendor, List<Item>> data) throws Exception {
        try (var out = new ObjectOutputStream((
                new BufferedOutputStream(
                    new FileOutputStream(dbUrl))))) {
            out.writeObject(data);
        } catch (Exception e) {
            throw new Exception("Cannot save data to database");
        }
    }

    public Map<Vendor, List<Item>> read() throws Exception {
        try (var in = new ObjectInputStream((
                new BufferedInputStream(
                    new FileInputStream(dbUrl))))) {
            return ((Map<Vendor, List<Item>>) in.readObject());
        } catch (Exception e) {
            throw new Exception("Cannot read data from database");
        }
    }

    public static FileSource getInstance() throws Exception {
        if (instance == null) {
            instance = new FileSource();
        }
        return instance;
    }
}
