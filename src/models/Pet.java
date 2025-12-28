package models;

// abstract define shared behaviour, ideal for our super class
public abstract class Pet {
    // -------Fields-------
    private int id = 1000;
    private String name = "";
    private String owner = "";
    // a week has 7 days so our array will have 7 value's.
    private boolean[] daysAttending = new boolean[] {false, false, false, false, false, false, false};
    private char sex = 'f';
    private int age = 0;
    // Static ID generation
    private static int nextId = 1000;

    // -------Constructors-------
    public Pet(int id, String name, String owner, char sex, int age) {

        
    }


}
