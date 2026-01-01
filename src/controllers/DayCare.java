package controllers;

import models.Pet;
import models.Cat;
import models.Dog;
import utils.ISerializer;
import utils.Utilities;
import utils.XMLSerializer;
import java.io.File;
import java.util.ArrayList;

public class DayCare {

 //TODO Create a list to store the Pets

 // array list used to store and manage pet objects, allowing dynamic operations
 private ArrayList<Pet> pets;

    //TODO Create a field to store the Kennel Name

    // stores the name of the kennel/daycare
    private String name;
    // serializer used to save and load daycare data without locking to a specific format
    private ISerializer serializer;

    //TODO create constructor initialise the kennel name and to instantiate the pets list

    // constructor to initialise the daycare name, pet list, and serializer
    public DayCare(String name) {
        this.name = Utilities.truncateString(name, 20);
        this.pets = new ArrayList<>();
        this.serializer = new XMLSerializer(new File("Pets.xml"));
    }

    //TODO - CRUD Methods

    // Adds a Pet to the pets collection.
    // parameter pet the Pet object to add
    // return true if the pet was added; false otherwise
    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    // Deletes and returns the Pet at the specified index.
    // parameter index the position of the Pet in the pets list
    // return the deleted Pet if index is valid; null otherwise
    public Pet deletePetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.remove(index);
        }
        return null;
    }

    // Deletes and returns the Pet with the specified id.
    // parameter id the id of the Pet to delete
    // return the deleted Pet if found; null otherwise
    public Pet deletePetById(int id) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                return pets.remove(i);
            }
        }
        return null;
    }

    // Returns the Pet at the specified index.
    // parameter index the position of the Pet in the pets list
    // return the Pet if index is valid; null otherwise
    public Pet getPetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.get(index);
        }
        return null;
    }

    // Returns the Pet with the specified id.
    // parameter id the id of the Pet to find
    // return the Pet if found; null otherwise
    public Pet getPetById(int id) {
        for (Pet pet : pets) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    // TODO Reporting Methods

    // Returns a list of all pets with their index numbers.
    // return a String containing all pets or "No Pets" if none exist
    public String listAllPets() {
        if (pets.isEmpty()) {
            return "No Pets";
        }

        String report = "";
        for (int i = 0; i < pets.size(); i++) {
            report += i + ": " + pets.get(i).toString();
            if (i < pets.size() - 1) {
                report += "\n";
            }
        }
        return report;
    }

    // Returns a list of all cats with their index numbers.
    // return a String containing all cats or "No cats" if none exist
    public String listAllCats() {
        String report = "";
        int count = 0;

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Cat) {
                report += i + ": " + pets.get(i).toString() + "\n";
                count++;
            }
        }

        if (count == 0) {
            return "No cats";
        }

        // remove trailing newline
        return report.substring(0, report.length() - 1);
    }

    // Returns a list of all dogs with their index numbers.
    // return a String containing all dogs or "No Dogs" if none exist
    public String listAllDogs() {
        String report = "";
        int count = 0;

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog) {
                report += i + ": " + pets.get(i).toString() + "\n";
                count++;
            }
        }

        if (count == 0) {
            return "No Dogs";
        }

        // remove trailing newline
        return report.substring(0, report.length() - 1);
    }

    // Returns a list of all dangerous dogs with their index numbers.
    // return a String of dangerous dogs, or appropriate message if none exist
    public String listAllDangerousDogs() {
        String report = "";
        int dogCount = 0;
        int dangerousCount = 0;

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog) {
                dogCount++;
                Dog dog = (Dog) pets.get(i);
                if (dog.isDangerousBreed()) {
                    report += i + ": " + dog.toString() + "\n";
                    dangerousCount++;
                }
            }
        }

        if (dogCount == 0) {
            return "No Dogs";
        }

        if (dangerousCount == 0) {
            return "No Dangerous Dogs in the Kennels";
        }

        // remove trailing newline
        return report.substring(0, report.length() - 1);
    }

    // Returns a list of all pets for a given owner.
    // parameter owner the owner's name
    // return a String listing pets for that owner, or a message if none exist
    public String listAllPetsByOwner(String owner) {
        String report = "";
        int count = 0;

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getOwner().equalsIgnoreCase(owner)) {
                report += i + ": " + pets.get(i).toString() + "\n";
                count++;
            }
        }

        if (count == 0) {
            return "No Pet with owner " + owner;
        }

        // remove trailing newline
        return report.substring(0, report.length() - 1);
    }

    // Returns a list of all pets that stay more than a specified number of days.
    // parameter numDays the minimum number of days (exclusive)
    // return a String listing matching pets, or a message if none exist
    public String listAllPetsThatStayMoreThanDays(int numDays) {
        String report = "";
        int count = 0;

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).numOfDaysInKennel() > numDays) {
                report += i + ": " + pets.get(i).toString() + "\n";
                count++;
            }
        }

        if (count == 0) {
            return "No Pet stays longer than " + numDays;
        }

        // remove trailing newline
        return report.substring(0, report.length() - 1);
    }

    // TODO number methods

    // Returns the number of pets in the system.
    // return the number of Pet objects in pets
    public int numberOfPets() {
        return pets.size();
    }

    // Returns the number of cats in the system.
    // return number of Cat objects in pets
    public int numberOfCats() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                count++;
            }
        }
        return count;
    }

    // Returns the number of dogs in the system.
    // return number of Dog objects in pets
    public int numberOfDogs() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                count++;
            }
        }
        return count;
    }

    // Returns the number of dangerous dogs in the system
    // return number of dangerous Dog objects in pets
    public int numberOfDangerousDogs() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
                if (dog.isDangerousBreed()) {
                    count++;
                }
            }
        }
        return count;
    }

    // Returns the number of indoor cats in the system.
    // return number of indoor Cat objects in pets
    public int numberOfIndoorCats() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                Cat cat = (Cat) pet;
                if (cat.isIndoorCat()) {
                    count++;
                }
            }
        }
        return count;
    }

    //TODO validation method below:
    //the following is isValidId can be updated
    //to suit your code - checks is the id already there in the list
    /*
    public boolean isValidId(int id) {
        for (Pet p : whateverYouCalledYourList) {
            if (p.getId().equals(id))
                return false;
        }
            return true;
        }
*/
    public boolean isValidId(int id) {
        for (Pet p : pets) {
            if (p.getId() == (id))
                return false;
        }
        return true;
    }

    //TODO get Pets methods

    // Get Pets Methods
    // Returns the pets collection. return ArrayList of Pet objects
    public ArrayList<Pet> getPets() {
        return pets;
    }

    // Replaces the pets collection.
    // parameter pets the new pets collection
    public void setPets(ArrayList<Pet> pets) {
        if (pets != null) {
            this.pets = pets;
        }
    }

    //TODO - delete methods
    // Delete functionality is provided via:
    // - deletePetByIndex(int index)
    // - deletePetById(int id)

    // TODO Persistence methods

    // Saves all Pet objects in the pets collection to Pets.xml.
    public void save() {
        try {
            serializer.write(pets);
        } catch (Exception e) {
            // no console input or output here, as required by the specification
        }
    }

    // Loads all Pet objects from Pets.xml in the pets collection.
    @SuppressWarnings("unchecked")
    public void load() {
        try {
            Object obj = serializer.read();
            if (obj instanceof ArrayList) {
                pets = (ArrayList<Pet>) obj;
            }
        } catch (Exception e) {
            // no console input or output here, as required by the specification
        }
    }

    // -------------------
    // Update Methods
    // -------------------
    public Dog updateDog(int id, Dog updatedDetails) {
        if (updatedDetails == null) {
            return null;
        }

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog && pets.get(i).getId() == id) {
                pets.set(i, updatedDetails);
                return updatedDetails;
            }
        }
        return null;
    }

    public Cat updateCat(int id, Cat updatedDetails) {
        if (updatedDetails == null) {
            return null;
        }

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Cat && pets.get(i).getId() == id) {
                pets.set(i, updatedDetails);
                return updatedDetails;
            }
        }
        return null;
    }

    // ----------------------
    // Other / Search Methods
    // ----------------------
    public double getWeeklyIncome() {
        double total = 0;
        for (Pet pet : pets) {
            total += pet.calculateWeeklyFee();
        }
        return total;
    }

    public Pet findDogByOwnerAndBreedAndAge(String owner, String breed, int age) {
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
                if (dog.getOwner().equalsIgnoreCase(owner)
                        && dog.getBreed().equalsIgnoreCase(breed)
                        && dog.getAge() == age) {
                    return dog;
                }
            }
        }
        return null;
    }

    public String getPetsByOwnersName(String owner) {
        String report = "";
        int count = 0;

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getOwner().equalsIgnoreCase(owner)) {
                report += i + ": " + pets.get(i).toString() + "\n";
                count++;
            }
        }

        if (count == 0) {
            return "No Pets for " + owner;
        }

        return report.substring(0, report.length() - 1);
    }
}