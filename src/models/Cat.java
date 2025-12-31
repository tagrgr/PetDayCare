package models;
import utils.CatToyUtility;

public class Cat extends Pet {

    // --------------------
    // -------Fields-------
    // tell us if the cat is an indoor-only cat (default: true)
    private boolean indoorCat = true;
    // stores the cat’s favourite toy, default to a valid placeholder value which is "Interactive toy for all ages"
    private String favouriteToy = "FEATHER WAND";


    // --------------------
    // ----Constructors----
    public Cat(int id, String name, int age, char sex, String owner,
               boolean indoorCat, String favouriteToy) {

        // initialise common Pet fields using the superclass constructor
        super(id, name, age, sex, owner);

        // assign the validated indoorCat value to the object field
        this.indoorCat = indoorCat;

        // validate and normalise favourite toy before storing it in the object
        if (favouriteToy != null) {
            String upperToy = favouriteToy.trim().toUpperCase();
            if (CatToyUtility.isCatToy(upperToy)) {
                this.favouriteToy = upperToy;
            }
        }
    }


    // ---------------------
    // -------Methods-------
    // calculates the weekly boarding fee for a cat based on daily rate and attendance
    @Override
    public double calculateWeeklyFee() {
        double daily = 20;
        if (indoorCat) daily += 5;
        return daily * numOfDaysInKennel();
    }


    // ---------------------
    // -------Getters-------
    public boolean isIndoorCat() {
        return indoorCat;
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }


    // ---------------------
    // -------Setters-------
    // update indoorCat value for this cat
    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    // validate and update favourite toy before assigning it to the object
    public void setFavouriteToy(String favouriteToy) {
        if (favouriteToy != null) {
            String upperToy = favouriteToy.trim().toUpperCase();
            if (CatToyUtility.isCatToy(upperToy)) {
                this.favouriteToy = upperToy;
            }
        }
    }


    // ---------------------
    // -------toString-------
    // returns a single-line string with pet and cat-specific details
    @Override
    public String toString() {
        return super.toString()
                + ", Type:Cat"
                + ", Indoor:" + indoorCat
                + ", Toy:" + favouriteToy;
    }
}