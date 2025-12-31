package models;
import utils.DogBreedUtility;
import utils.Utilities;

public class Dog extends Pet {

    // --------------------
    // -------Fields-------
    // instance fields
    private String breed = "UNKNOWN";
    private boolean dangerousBreed = false;
    private boolean neutered = false;
    // static final field (cannot be changed)
    public static final float dangerousDailyRate = 40f;
    // we're defining a higher rate for dangerous breeds and a lower rate for non-dangerous.
    public static final float nonDangerousDailyRate = 30f;


    // --------------------
    // ----Constructors----
    public Dog(int id, String name, int age, char sex, String owner,
               String breed, boolean dangerousBreed, boolean neutered) {

        // initialise common Pet fields using the superclass constructor
        super(id, name, age, sex, owner);

        // breed: max 20 chars; validate using DogBreedUtility
        if (breed != null) {
            String truncated = Utilities.truncateString(breed, 20);

            // make the input match what dog breed utility expects
            String normalisedForCheck = truncated.toUpperCase();
            if (DogBreedUtility.checkBreed(normalisedForCheck)) {
                this.breed = normalisedForCheck;
            }
        }

        this.dangerousBreed = dangerousBreed;
        this.neutered = neutered;
    }


    // ---------------------
    // -------Methods-------
    // instance methods, overrides abstract method from our superclass
    // calculates the weekly boarding fee for a dog based on daily rate and attendance
    @Override
    public double calculateWeeklyFee() {
        float rate;

        if (dangerousBreed) {
            rate = dangerousDailyRate;
        } else {
            rate = nonDangerousDailyRate;
        }
        return rate * numOfDaysInKennel();
    }


    // ---------------------
    // -------Getters-------
    public String getBreed() {
        return breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public boolean isNeutered() {
        return neutered;
    }


    // ---------------------
    // -------Setters-------
    public void setBreed(String breed) {
        if (breed != null) {
            String truncated = Utilities.truncateString(breed, 20);
            if (DogBreedUtility.checkBreed(truncated)) {
                this.breed = truncated;
            }
        }
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }


    // ---------------------
    // -------toString-------
    // returns a single-line string with pet and dog-specific details
    @Override
    public String toString() {
        return super.toString()
                + ", Type:Dog"
                + ", Breed:" + breed
                + ", Dangerous:" + dangerousBreed
                + ", Neutered:" + neutered;
    }
}