/* https://github.com/tagrgr/PetDayCare */

package main;

import controllers.DayCare;
import models.Pet;
import models.Dog;
import models.Cat;
import utils.ScannerInput;
import utils.Utilities;
import utils.CatToyUtility;

import java.util.ArrayList;

public class Driver {

    DayCare daycare;

    public static void main(String[] args) {

        new Driver();

    }

    public Driver() {

        // create and start up the daycare system
        daycare = new DayCare("Pet DayCare");

        //TODO - load all data
        daycare.load();
        runMenu();
    }
    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------


    private int mainMenu() {

        //TODO write menu that user will see

        // simple menu interface
        System.out.println("""
                ----------------------------------
                Pet DayCare Management System
                ----------------------------------
                1) Pets CRUD Menu
                2) Reports Menu
                3) Search Pets
                4) Sort Pets
                10) Save all
                11) Load all
                0) Exit
                """);

        return ScannerInput.readNextInt("==>> ");
    }

    // controls the main menu flow, calls the correct option,
    // exits the program when the user chooses to quit
    private void runMenu() {

        int option = mainMenu();
        //TODO - write code to call appropiate method based on value in option

        while (option != 0) {

            switch (option) {
                case 1:
                    petsCrudMenu();
                    break;
                case 2:
                    reportsMenu();
                    break;
                case 3:
                    searchMenu();
                    break;
                case 4:
                    sortMenu();
                    break;
                case 10:
                    daycare.save();
                    System.out.println("Successfully saved to Pets.xml");
                    break;
                case 11:
                    daycare.load();
                    System.out.println("Successfully loaded from Pets.xml");
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
            // get the next menu option
            option = mainMenu();
        }

        exitApp();
    }

    private void exitApp() {

        System.out.println("Exiting...");
        System.exit(0);
    }

//todo update methods counting methods





    //---------------------
    //  General Menu Items
    //---------------------
//  TODO - write all the methods that are called from your menu
    // handles the pets CRUD menu flow and calls the selected option
    private void petsCrudMenu() {
        // display the CRUD menu
        int option = mainPetsCrudMenu();

        // option 0 is handled by the loop condition, so it will not need a switch case
        while (option != 0) {

            switch (option) {
                case 1:
                    addPet();
                    break;
                case 2:
                    deletePetByIndex();
                    break;
                case 3:
                    System.out.println(daycare.listAllPets());
                    break;
                case 4:
                    updatePetById();
                    break;
                default:
                    System.out.println("Invalid option - please try again.");
            }
            // show the CRUD menu again and read the next option
            option = mainPetsCrudMenu();
        }
    }

    // prints the pets CRUD menu and returns the user's choice
    private int mainPetsCrudMenu() {
        System.out.println("""
        -------------------------
        Pets CRUD Menu
        -------------------------
        1) Add a new Pet
        2) Delete a Pet (by index)
        3) List all Pets
        4) Update Pet Information (by id)
        0) Return to Main Menu
        """);
        return ScannerInput.readNextInt("==>> ");
    }

    // handles the reports menu and calls the selected option
    private void reportsMenu() {
        // display the reports menu
        int option = mainReportsMenu();

        // keep showing the reports menu until option 0 is selected
        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.println(daycare.listAllPets());
                    break;
                case 2:
                    System.out.println(daycare.listAllDogs());
                    break;
                case 3:
                    System.out.println(daycare.listAllCats());
                    break;
                case 4:
                    System.out.println(daycare.listAllDangerousDogs());
                    break;
                case 5: {
                    String owner = ScannerInput.readNextLine("Enter owner name: ");
                    System.out.println(daycare.listAllPetsByOwner(owner));
                    break;
                }
                case 6: {
                    int numDays = ScannerInput.readNextInt("Enter number of days the Pet will be in the Kennel: ");
                    System.out.println(daycare.listAllPetsThatStayMoreThanDays(numDays));
                    break;
                }
                case 7:
                    System.out.println("Weekly Income: " + daycare.getWeeklyIncome());
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            // show the reports menu again and read the next option
            option = mainReportsMenu();
        }
    }

    // displays the reports menu and returns the user's choice
    private int mainReportsMenu() {
        System.out.println("""
        -------------------------
        Reports Menu
        -------------------------
        1) List all Pets
        2) List all Dogs
        3) List all Cats
        4) List all Dangerous Dogs
        5) List Pets by Owner
        6) List Pets staying more than X days
        7) Weekly Income Report
        0) Return to Main Menu
        """);

        // read and return the selected menu option
        return ScannerInput.readNextInt("==>> ");
    }

    //---------------------
    //  Search
    //---------------------
// TODO search by different criteria i.e. look at the list methods and give options based on that.
    // handles the search menu and calls the selected search option
    private void searchMenu() {

        // display the search menu
        int option = mainSearchMenu();

        // keep showing the search menu until option 0 is selected
        while (option != 0) {

            switch (option) {
                // find a dog by owner, breed, and age
                case 1: {
                    String owner = ScannerInput.readNextLine("Enter owner name: ");
                    String breed = ScannerInput.readNextLine("Enter dog breed: ");
                    int age = ScannerInput.readNextInt("Enter dog age: ");

                    Pet result = daycare.findDogByOwnerAndBreedAndAge(owner, breed, age);

                    if (result != null) {
                        // display the matching pet details if a result is found
                        System.out.println("Match found: " + result.toString());
                    } else {
                        System.out.println("No match found.");
                    }
                    break;
                }
                // list all pets belonging to a specific owner
                case 2: {
                    String owner = ScannerInput.readNextLine("Enter owner name: ");
                    System.out.println(daycare.getPetsByOwnersName(owner));
                    break;
                }
                // handle invalid menu options
                default:
                    System.out.println("Invalid option - please try again.");
            }
            // show the search menu again and read the next option
            option = ScannerInput.readNextInt("==>> ");
        }
    }

    // displays the search menu options and returns the user's selection
    private int mainSearchMenu() {

        System.out.println("""
        -------------------------
        Search Menu
        -------------------------
        1) Find Dog by Owner, Breed and Age
        2) List Pets by Owner
        0) Return to Main Menu
        """);

        return ScannerInput.readNextInt("==>> ");
    }

    //---------------------
    //  Helper Methods
    //---------------------
//  TODO- write any helper methods that are required
    // collects user input and adds a new pet (dog or cat) to the daycare
    private void addPet() {

        System.out.println("Add what type of pet?");
        System.out.println("1) Dog");
        System.out.println("2) Cat");
        int type = ScannerInput.readNextInt("==>> ");

        String name = ScannerInput.readNextLine("Enter pet name: ");
        int age = ScannerInput.readNextInt("Enter age: ");
        char sex = ScannerInput.readNextChar("Enter sex (m/f): ");
        String owner = ScannerInput.readNextLine("Enter owner name: ");
        int id = ScannerInput.readNextInt("Enter id (>=1000) or 0 to auto-generate: ");

        // if the user selected dog:
        if (type == 1) {

            // read dog specific details
            String breed = ScannerInput.readNextLine("Enter breed: ");
            boolean dangerousBreed = Utilities.YNtoBoolean(ScannerInput.readNextChar("Is this a dangerous breed? (y/n): "));
            boolean neutered = Utilities.YNtoBoolean(ScannerInput.readNextChar("Is the dog neutered? (y/n): "));

            // create a new dog object
            Dog dog = new Dog(id, name, age, sex, owner, breed, dangerousBreed, neutered);

            // adds this dog object to the daycare if provided data is valid
            boolean added = daycare.addPet(dog);
            if (added) {
                System.out.println("Dog added.");
            } else {
                System.out.println("Dog was not added.");
            }
            return;
        }

        // if the user selected cat:
        if (type == 2) {

            // read cat specific details
            boolean indoorCat = Utilities.YNtoBoolean(ScannerInput.readNextChar("Is the cat indoor only? (y/n): "));

            // display valid cat toys before asking for input
            System.out.println(CatToyUtility.getCatToys());
            String favouriteToy = ScannerInput.readNextLine("Enter favourite toy (use listed name): ");

            // create a new cat object
            Cat cat = new Cat(id, name, age, sex, owner, indoorCat, favouriteToy);

            // adds this cat object to the daycare if provided data is valid
            boolean added = daycare.addPet(cat);
            if (added) {
                System.out.println("Cat added.");
            } else {
                System.out.println("Cat was not added.");
            }
            return;
        }
        // handles invalid pet type selection
        System.out.println("Invalid pet type selected.");
    }

    // removes a pet from the daycare list using the selected index
    private void deletePetByIndex() {

        // display all pets with their indexes
        System.out.println(daycare.listAllPets());

        // read the index of the pet to delete
        int index = ScannerInput.readNextInt("Enter index to delete: ");
        Pet deleted = daycare.deletePetByIndex(index);

        // confirm if the item was successfully deleted
        if (deleted != null) {
            System.out.println("Deleted: " + deleted.toString());
        } else {
            System.out.println("Invalid index - no pet deleted.");
        }
    }

    // updates an existing pet by id by collecting new details from the user
    private void updatePetById() {

        // read the id of the pet to update
        int id = ScannerInput.readNextInt("Enter the pet id to update: ");
        // gets the existing pet from the daycare
        Pet existing = daycare.getPetById(id);

        // stop if no pet is found with the id
        if (existing == null) {
            System.out.println("No pet found with id " + id);
            return;
        }

        // print the current pet details before updating
        System.out.println("Current details: " + existing.toString());

        // read updated common pet details
        String name = ScannerInput.readNextLine("Enter new name: ");
        int age = ScannerInput.readNextInt("Enter new age: ");
        char sex = ScannerInput.readNextChar("Enter new sex (m/f): ");
        String owner = ScannerInput.readNextLine("Enter new owner name: ");

        // if the pet is a dog
        if (existing instanceof Dog) {

            // read dog specific updated details
            String breed = ScannerInput.readNextLine("Enter new breed: ");
            boolean dangerousBreed = Utilities.YNtoBoolean(ScannerInput.readNextChar("Is this a dangerous breed? (y/n): "));
            boolean neutered = Utilities.YNtoBoolean(ScannerInput.readNextChar("Is the dog neutered? (y/n): "));

            // create a new dog object with updated values
            Dog updatedDog = new Dog(id, name, age, sex, owner, breed, dangerousBreed, neutered);
            // add the updated dog in the daycare
            Dog result = daycare.updateDog(id, updatedDog);

            // again we confirm if the update in this case was successful
            if (result != null) {
                System.out.println("Dog updated.");
            } else {
                System.out.println("Update failed.");
            }
            return;
        }

        // if the pet is a cat
        if (existing instanceof Cat) {

            // read cat specific updated details
            boolean indoorCat = Utilities.YNtoBoolean(ScannerInput.readNextChar("Is the cat indoor only? (y/n): "));
            System.out.println(CatToyUtility.getCatToys());
            String favouriteToy = ScannerInput.readNextLine("Enter new favourite toy (use listed name): ");

            // create a new cat object with updated values
            Cat updatedCat = new Cat(id, name, age, sex, owner, indoorCat, favouriteToy);
            // add the updated cat in the daycare
            Cat result = daycare.updateCat(id, updatedCat);

            // again we confirm if the update in this case was successful
            if (result != null) {
                System.out.println("Cat updated.");
            } else {
                System.out.println("Update failed.");
            }
            return;
        }
        // in case the pet type is not recognized gets an alert
        System.out.println("Pet type not recognised - update cancelled.");
    }

    // Extra functionality / Enhancement
    //---------------------
    //  Sort Menu Items
    //---------------------
    // handles the sort menu flow and sorts the pets list based on the selected option
    private void sortMenu() {
        int option = mainSortMenu();

        while (option != 0) {

            switch (option) {
                case 1:
                    sortPetsByIdAscending(daycare.getPets());
                    System.out.println("Sorted by ID.");
                    System.out.println(daycare.listAllPets());
                    break;
                case 2:
                    sortPetsByNameAscending(daycare.getPets());
                    System.out.println("Sorted by Name.");
                    System.out.println(daycare.listAllPets());
                    break;
                case 3:
                    sortPetsByOwnerAscending(daycare.getPets());
                    System.out.println("Sorted by Owner.");
                    System.out.println(daycare.listAllPets());
                    break;
                case 4:
                    sortPetsByAgeAscending(daycare.getPets());
                    System.out.println("Sorted by Age.");
                    System.out.println(daycare.listAllPets());
                    break;
                default:
                    System.out.println("Invalid option - please try again.");
            }
            option = mainSortMenu();
        }
    }

    // displays the sort menu options and returns the user's choice
    private int mainSortMenu() {

        System.out.println("""
        -------------------------
        Sort Menu
        -------------------------
        1) Sort by ID (ascending)
        2) Sort by Name (A-Z)
        3) Sort by Owner (A-Z)
        4) Sort by Age (ascending)
        0) Return to Main Menu
        """);

        return ScannerInput.readNextInt("==>> ");
    }

    //---------------------
    //  Helper Methods
    //---------------------
    // sorts the pets list by id in ascending order
    private void sortPetsByIdAscending(ArrayList<Pet> pets) {

        // outer loop controls how many passes are needed
        for (int i = 0; i < pets.size() - 1; i++) {

            // inner loop compares pets
            for (int j = 0; j < pets.size() - 1 - i; j++) {

                // swap pets if the current id is greater than the next one
                if (pets.get(j).getId() > pets.get(j + 1).getId()) {

                    // temporary variable used to swap the two pets
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                }
            }
        }
    }

    // sorts the pets list by name in alphabetical order (A–Z)
    private void sortPetsByNameAscending(ArrayList<Pet> pets) {

        // loop through the list multiple times to compare names
        for (int i = 0; i < pets.size() - 1; i++) {

            // compare each pet name with the next one
            for (int j = 0; j < pets.size() - 1 - i; j++) {

                // swap pets if the current name comes after the next name
                if (pets.get(j).getName().compareToIgnoreCase(pets.get(j + 1).getName()) > 0) {

                    // temporary variable used to swap the two pets
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                }
            }
        }
    }

    // sorts the pets list by owner name in alphabetical order (A–Z)
    private void sortPetsByOwnerAscending(ArrayList<Pet> pets) {

        // loop through the list to compare owner names
        for (int i = 0; i < pets.size() - 1; i++) {

            // compare each owner name with the next one
            for (int j = 0; j < pets.size() - 1 - i; j++) {

                // swap pets if the current owner name comes after the next one
                if (pets.get(j).getOwner().compareToIgnoreCase(pets.get(j + 1).getOwner()) > 0) {

                    // temporary variable used to swap the two pets
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                }
            }
        }
    }

    // sorts the pets list by age in ascending order
    private void sortPetsByAgeAscending(ArrayList<Pet> pets) {

        // loop through the list to compare pet ages
        for (int i = 0; i < pets.size() - 1; i++) {

            // compare each pet age with the next one
            for (int j = 0; j < pets.size() - 1 - i; j++) {

                // swap pets if the current age is greater than the next age
                if (pets.get(j).getAge() > pets.get(j + 1).getAge()) {

                    // temporary variable used to swap the two pets
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                }
            }
        }
    }

}
