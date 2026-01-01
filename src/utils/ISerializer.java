package utils;

/*
 * This interface set up the behaviour required for saving and loading data
 * in the system. It does not contain any implementation logic, only method
 * that must be provided by a concrete serializer class.
 *
 * The purpose of this interface is to allow the DayCare class to persist
 * objects without knowing how or where the data is stored.
 * This improves flexibility and keeps persistence logic separate
 * from business logic.
 *
 * Any class that implements this interface must provide a way to write an
 * object for storage and read it back when needed.
 */
public interface ISerializer {
    void write(Object obj) throws Exception;
    Object read() throws Exception;
}
