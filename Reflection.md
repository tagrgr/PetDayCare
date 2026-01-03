https://github.com/tagrgr/PetDayCare

Name : Tiago de Gregori

Student Number:  20119203

- Chronology of implementation:


    1. Abstract Class: I started up the project by the directory models and the first .java was my abstract class. 
        Pet was created to define shared attributes and behavior such as Id, name, age, owner, 
        weekly attendance, fee calculation, etc...
        This ensured reuse and it's a consistent structure across all pet types.
    2. Subclasses: The subclasses Dog and Cat were next in line, each introducing type specific
        fields and validation rules such as dangerous breed, neutering for dogs and indoor status, favorite toys for cats.
        I guess calculateWeeklyFee() method has used what's called Polymorphism as it was created in Pet and the method 
        would adapt to either Dog or Cat with specific values. 
    3. Weekly Attendance Model: A Weekly Attendance Model using a fixed boolean array(7 days) was introduced to represent
        kennel attendance supporting accurate fee calculation and reporting.
    4. DayCare Implementation: The DayCare class was implemented to manage all pets. It became responsible for:
         - Storing and calling pets
         - CRUD operations
         - Reporting and search functionality
         - Persistence using XML serialization
    5. Driver Class:  The menu's console interface was then developed in the Driver class. This handled all user input and output
        while giving logic to DayCare.java, following the separation of concerns requirements.
    6. Sort (extra implementation): Implemented sorting operations (by ID, name, owner and age) in the system, allowing users to view
        pets in ordered way.
    7. Update functionality using setters: functionality was updated after all to modify objects using setters intead of replacing them.
    8. Validation and Reporting enhancements: Additional reporting features were added(counts, filters, income report), and validation
        methods (such as ID uniqueness checks) were integrated and actively used to ensure proper functionality.
    9. Final Clean up
        Unused or redundant code was deleted or integrated meaningfully to ensure all methods are being used. Few methods were created
        aligned with the assignment spec before I created the Driver class and at the end I ended up not using they all. 
    

- Main difficulties you came across in your development of solution and how you solved them:


    I couldn't cover all the methods required in the assignment classes uml as I didn't cover all our lab and video classes I tried to
    at least read all our classes documentation, and specially from week 9 everything became so hard to understand as I wasn't spending time on
    the labs, but anyway the system works properly, I copied some from these weeks to my code as I also used previous work of mine that I will
    reference below, this wasn't the first system I developed, I had developed a Library and a Zoologic system previously, they both with loads of
    help from my previous Lecturers.
    I still struggle to start any project, it's like I can't put the pieces together, I was looking at my previous projects to get to start it.
    I also struggle with the many information at same time it is with Java, I don't have the same feeling with HTML, CSS and JavaScript, but Java
    for me is overwhelming and yet I can't do such a project like this by myself.
    Designing an appoppriate attendance model was tricky and I've done my best as I would not know how to implement date dd/MM//yyyy.

- Any bugs remaining in the solution or unfinished elements of spec
    

    Nothing that I remember, not all the methods have an error handling I believe, the time I was writing it: "as for instance the days 
    the user will attribute to the pets in won't have any error handling", I decided to add a if block to handle this possible error in 
    captureWeeklyAttendance().

- Main learnings from your engagement with assignment
     

    Honestly that was the first time I really got to understand how to use setters and toString. :)
    I didn't use any other source apart from the ones I'm referencing, I don't use auto-complete in InteliJ asd I'm trying to "organic" learn.

- Answer the following questions:


1. Pat Purrs mentioned a problem their friend had with their pet system. What was the issue with that system, and how is your design intended to avoid the same problem?


    All the code was written in one giant class, everything was tied to the console interface which means that any change in the code will be almost impossible as there's 
    no separation of concerns. In our system we created a class for each animal which means that if we want to add a new pet we just need to add a new subclass and use the
    abstract class Pet that created to share the base information we need from any pet.
    Our system is extremly easy to maintain, flexible, well structured and lastly extensible.


2. Why does Pat want the Driver class to be simple and user-friendly, and how does this relate to the plan to eventually develop a mobile app?
  

    So the staff can you right now, but with the future in mind. The real logic should leave in the model and the controller, that way we will eventually a mobile app and we 
    can simply replace the console driver with a shiny new interface to do so.  

3. Pat talked about future expansion of the daycare. What kinds of growth does the system need to support, and how should your code design accommodate this?


    1- Increase number of pet types
    2- Avoid harcoded limits as she wants to have more pet types and animal numbers in.
    3- We designed a consolebased system, the plan is to evolve and turn it into a full mobile app without writting the whole system again.


4. Besides cats and dogs, what additional animal does Pat plan to include next, and what does this imply for how you structure your inheritance hierarchy?



    Rabbits inittially but more types after that.
    Adding a new pet has direct implications in our design and inheritance hierarchy.
    Pet super class will handle state/behavior that is required by the system to all pet types, calculateWeeklyFee() uses polymorphism for the behavior that varies.
    Hierarchy is not designed around features like indoor cat or neutered dog so future types may or may not share them.
    A new subclass for a new pet type usually requires its own validation rules, updates to add/update workflow (driver/daycare), updates to type-specific reports like list all indoor cats.
    If a new pet type is added, it should only require creating a new subclass that extends Pet super class and implementing its specific behavior.
    


- Mandatory : Please list any references used in your development/ implementation of your submission.


    Java Serialization pattern (oracle)
    https://docs.oracle.com/javase/8/docs/platform/serialization/spec/serial-arch.html
    
    XStream
    https://x-stream.github.io/tutorial.html
    https://x-stream.github.io/index.html

    Library Management System (my own work)
    https://github.com/tagrgr/library-management-system-

    Zoologic Animal Management System (my own work)
    https://github.com/tagrgr/animal-management-system


Please consider the following statements and choose one (delete the inappropriate one)

- This is my work apart from the specific references noted above (and any code from class notes). I understand the code and can decribe any parts of the solution if needs be;


