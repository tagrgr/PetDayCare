package models;
import utils.Utilities;

// abstract define shared behaviour, ideal for our super class
public abstract class Pet {


    // --------------------
    // -------Fields-------
    // static field(belongs to this class), ID generation(shared state by all Pet objects)
    private static int nextId = 1000;

    private int id;
    private String name = "";
    private String owner = "";
    // a week has 7 days so our array will have 7 value's.
    private boolean[] daysAttending = new boolean[] {false, false, false, false, false, false, false};
    private char sex = 'f';
    private int age = 0;


    // --------------------
    // ----Constructors----
    public Pet(String name, String owner, char sex, int age) {
        this.id = nextId++;
        // truncate will enforce max length, use this. for validation, store in the object
        this.name = Utilities.truncateString(name, 30);
        // as above we ensure owner name does not exceed 20 characters
        this.owner = Utilities.truncateString(owner, 20);
        // non-string field: update only if the value is valid; otherwise retain the default value
        if (age >= 0) {
            this.age = age;
        }
        // sets the sex only if it is valid (m or f); otherwise keeps the default value
        if (isValidSex(sex)) {
            this.sex = Character.toLowerCase(sex);
        }
        // ID rules: >= 1000. If invalid, generate. Always advance nextId for uniqueness.
        if (id >= 1000) {
            this.id = id;
            // keep nextId ahead of any specifically provided IDs
            if (id >= nextId) {
                nextId = id + 1;
            } else {
                nextId++;
            }
        // attribute next valid ID value to new ID
        } else {
            this.id = nextId;
            nextId++;
        }
    }


    // ---------------------
    // -------Methods-------
    // abstract method
    // calculates the weekly fee for a pet, implemented by subclasses
    public abstract double calculateWeeklyFee();

    // instance methods
    // sets the pet as attending on the given weekday if the index is valid
    public void checkIn(int dayIndex) {
        if (Utilities.validRange(dayIndex, 0, daysAttending.length - 1)) {
            daysAttending[dayIndex] = true;
        }
    }

    // sets the pet as not attending on the given weekday if the index is valid
    public void checkOut(int dayIndex) {
        if (Utilities.validRange(dayIndex, 0, daysAttending.length - 1)) {
            daysAttending[dayIndex] = false;
        }
    }

    // Counts how many days the pet is checked in
    public int numOfDaysInKennel() {
        int count = 0;
        for (boolean attending : daysAttending) {
            if (attending) count++;
        }
        return count;
    }

    // helper method
    // returns true if the sex is m or f
    private boolean isValidSex(char s) {
        char c = Character.toLowerCase(s);
        return (c == 'm' || c == 'f');
    }


    // ---------------------
    // -------Getters-------
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
    }

    public char getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }


    // ---------------------
    // -------Setters-------
    public void setId(int id) {
        // only update if valid (no defaulting)
        if (id >= 1000) {
            this.id = id;
            if (id >= nextId) {
                nextId = id + 1;
            }
        }
    }

    public void setName(String name) {
        // only update if valid, for strings we apply truncation rule to define max length
        if (name != null) {
            this.name = Utilities.truncateString(name, 30);
        }
    }

    public void setOwner(String owner) {
        if (owner != null) {
            this.owner = Utilities.truncateString(owner, 20);
        }
    }

    public void setDaysAttending(boolean[] daysAttending) {
        // only update if valid
        if (daysAttending != null && daysAttending.length == 5) {
            this.daysAttending = daysAttending;
        }
    }

    public void setSex(char sex) {
        if (isValidSex(sex)) {
            this.sex = Character.toLowerCase(sex);
        }
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }


    // ---------------------
    // -------toString-------
    @Override
    public String toString() {
        // single-line, no \n
        return "ID:" + id
                + ", Name:" + name
                + ", Owner:" + owner
                + ", Age:" + age
                + ", Sex:" + sex
                + ", DaysIn:" + numOfDaysInKennel();
    }
}