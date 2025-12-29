package models;
import utils.Utilities;

// abstract define shared behaviour, ideal for our super class
public abstract class Pet {
    // -------Fields-------
    // Static ID generation (shared state)
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
        // As above we ensure owner name does not exceed 20 characters
        this.owner = Utilities.truncateString(owner, 20);
        // Non-string field: update only if the value is valid; otherwise retain the default value
        if (age >= 0) {
            this.age = age;
        }
        // Sets the sex only if it is valid (m or f); otherwise keeps the default value
        if (isValidSex(sex)) {
            this.sex = Character.toLowerCase(sex);
        }
        // ID rules: >= 1000. If invalid, generate. Always advance nextId for uniqueness.
        if (id >= 1000) {
            this.id = id;
            // keep nextId ahead of any explicitly provided IDs
            if (id >= nextId) {
                nextId = id + 1;
            } else {
                nextId++;
            }
        } else {
            this.id = nextId;
            nextId++;
        }
    }




}