package utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XMLSerializer implements ISerializer {

    // object used as the storage location for serialized data
    private final File file;

    // constructor
    public XMLSerializer(File file) {
        this.file = file;
    }

    // methods
    // saves the given object to the file using XML format
    // try-with-resources is used so all streams are closed automatically
    @Override
    public void write(Object obj) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             XMLEncoder encoder = new XMLEncoder(bos)) {
                encoder.writeObject(obj);
             }
    }

    // reads and returns the stored object from the file, or null if the file does not exist
    @Override
    public Object read() throws Exception {
        if (!file.exists()) return null;

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             XMLDecoder decoder = new XMLDecoder(bis)) {
            return decoder.readObject();
        }
    }
}
