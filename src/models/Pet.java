package models;
import utils.Utilities;

// abstract define shared behaviour, ideal for our super class
public abstract class Pet {
    // -------Fields-------
    // Static ID generation
    private static int nextId = 1000;

    private int id;
    private String name = "";
    private String owner = "";
    // a week has 7 days so our array will have 7 value's.
    private boolean[] daysAttending = new boolean[] {false, false, false, false, false, false, false};
    private char sex = 'f';
    private int age = 0;

    // -------Constructors-------
    public Pet(String name, String owner, char sex, int age) {
        this.id = nextId++;
        // truncate will enforce max length, use this. for validation, store in the object
        this.name = Utilities.truncateString(name, 30);



    }


}
