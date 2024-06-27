package cbamz.storage;

import java.io.*;
import java.util.Map;

public class FileStorage {
    public static void write(String filename, Map<String, ?> map) {
        try {
            // Create a file for data map storage if it does not exist.
            File file = new File(filename);
            if(file.createNewFile())
                System.out.println("File " + filename + " created for data map storage.");

            // Serializing the map object to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);

            // Closing streams.
            fos.close();
            oos.close();

        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public static Object read(String filename) {
        try {
            // Deserializing the map object from file.
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();

            // Closing streams.
            ois.close();
            fis.close();
            return obj;

        } catch(FileNotFoundException e) {
            System.out.println("File " + filename + " not found.");
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Generally this would only happen on the first run OR if file was deleted manually for some reason.
        return null;
    }
}
