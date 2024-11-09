/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lecturer
 */
public class Zoo_Imp implements Zoo{
    
    
    
    // Collections is the first step to processing data 
    // Create a list of animals according to their types 
    
    private List<Animal> animals;
//     animls = [Lion, Tiger,Elephant,Parrot] an ordered list of elements 
//     index =   [0,    1,      2,      3, ]
    // 3 types of animals : Fish Mammal Bird 
    // create a constructor to add the initial set of animals 
    
    public Zoo_Imp(){
        
        // Construct the ArrayLists
        this.animals = new ArrayList<>();
        // animals = [Mammal[[Lion,2,190,jungle],[Zebra,120,23,Dessert]],Fish[],Bird[]]
    
        // initialise the list of animals by adding some animals
        animals.add(new Mammal("Lion", 13, 170, "Jungle")); // Mammal Lion
        animals.add(new Bird("Parrot", 9, 10, "Trees"));
        animals.add(new Fish("Baby Shark", 2, 10, "Ocean"));
    
    }
    
    
    @Override
    public void addAnimal(Animal animal){ // must be an instance of an animal class
        
            // That adding the animal will require specifying 
            // What type of animal it is 
            
            animals.add(animal); // add the animal to its corresponding class
            System.out.println("Animal has been added successfully!");
    }
    
    // The scond one will be for generating the random animal
    
    @Override 
    public void getRandomAnimal(){
    
//        Now we need to generate a random animal from the list 
//    First of all we want to know how many animals we have in the zoo
//      Then we want to get the size or length of the animals 
    // We will set this length or size as the maximum value to randomise within 
    
    List<String> animalNames = new ArrayList<>();
    // now the next we need to do is to iterate over the 
    // number of animals and add them to the list
    for(Animal animal: animals){
        // We have captured all of the animals anmes and we can iterate 
        // throgh them to get a random one
        animalNames.add(animal.getName());
    }
    
    // Call on the Random library to get a random number
    Random random = new Random();
    // use the order of the elements to randomly get a position from the list
    int index = random.nextInt(animalNames.size()); // this will generate a random number
    // starting from the first element and ending at the last element of the animalNames list
    // by size animalsNames.size() == 15 ->       0>= index <size()
        System.out.println(animalNames.get(index)); // print the animal at the random index position

    };
    
    
 
    
    @Override
    public void listAllAnimals(){
    
        // here we want to list all the animals in the zoo
        
        System.out.println("List of All ANimals in the Zoo");
        System.out.println("--------------------------------");
        
//        In order for us to list out all the animals in the different animal classes
//        Iterate and check if the animal is an instance of the animal class

        for(Animal animal: animals){
//            Check for the animal type 
//              Print the animal type and other information
            if(animal instanceof Mammal){     
                
                Mammal mammal = (Mammal) animal;    
                System.out.println("Name: " + mammal.getName());
                System.out.println("Type: Mammal");
                System.out.println("Age: " + mammal.getAge());
                System.out.println("Weight: " + mammal.getWeight());
                System.out.println("Habitat: " + mammal.getHabitat());
            
            }else if(animal instanceof Bird){ 
                
                Bird bird = (Bird) animal;    
                System.out.println("Name: " + bird.getName());
                System.out.println("Type: Bird");
                System.out.println("Age: " + bird.getAge());
                System.out.println("Weight: " + bird.getWeight());
                System.out.println("Habitat: " + bird.getHabitat());
            
            }else if(animal instanceof Fish){ 
                
                Fish fish = (Fish) animal;    
                System.out.println("Name: " + fish.getName());
                System.out.println("Type: Fish");
                System.out.println("Age: " + fish.getAge());
                System.out.println("Weight: " + fish.getWeight());
                System.out.println("Habitat: " + fish.getHabitat());
            
            }
            
                System.out.println("-------------------------------");
            
//            System.out.println("Name: " + animal.getName());
//            System.out.println("Age: " + animal.getAge());
//            System.out.println("Weight: " + animal.getWeight());
//            System.out.println("Habitat: " + animal.getHabitat());
        }
    }

    
    
    @Override
    public void listAnimalTypes(){
    
//      List the animal types
//      For every animal type in the zoo we want to know
//      How many animal types we have 
        
        System.out.println("List of all Animal types: ");
        
        
//    Construct a list of animal classes
//    For each animal type and get the counts of them 
        
        List<Mammal> mammal = new ArrayList<>();
        List<Bird> bird = new ArrayList<>();
        List<Fish> fish = new ArrayList<>();
    
//        Iterate through all the animal classes and append them to their correspending 
//      Class type
        for(Animal animal: animals){
            
//            Check the class of the animal and append to the correct list above
                if(animal instanceof Mammal){
                    mammal.add((Mammal) animal); // add Lion to the mammal list
                }else if(animal instanceof Bird){
                    bird.add((Bird) animal);
                }else if(animal instanceof Fish){
                    fish.add((Fish) animal);
                }
        }
    
//        Print out the sizes of each of the lists and their animal types
        System.out.println("Mammals: " + mammal.size()); // mammal [[1],[2],[3],[4]] -> size=4
        System.out.println("Fish: " + fish.size());
        System.out.println("Birds: " + bird.size());
    
    }
    
    
    
    
//     THe main method will employ the logic to process the user choice by providing 
//     Some menu options based off the enums in the interface 
        
    public static void main(String[] args){
    

//      Create a constructor reference for the Zoo_Imp
        Zoo_Imp zoo = new Zoo_Imp();

//      Create a link to the interface
        Zoo.MenuOption selectOption = null;

//        Create the scanner instance
        Scanner scanner = new Scanner(System.in);
        
        
//        Create a do while block to handle the user choice
//        and menu options
        do{
//         Execute the following as long as the user wants to keep interacting with the system
        
//            Print out a number of options
            System.out.println("Please select fromt he following options: ");
            System.out.println("1. List All Animals");
            System.out.println("2. List Animal Types");
            System.out.println("3. Add an Animal");
            System.out.println("4. Get a Random Animal");
            System.out.println("5. Exit");
        
            
//            Process the user input  //      Check the user choice 
            while(!scanner.hasNextInt()){
                System.out.println("Please make sure to select an option using a number");
                scanner.next();
            }
            
//             Now if the user has given us a number we will process the request

            int option = scanner.nextInt();
            
//      Check the number of options in enums  
//      This will dynamically check the user input to be a minimum value 1 or
//      higher up to whatever the number of enums is    
            if(option < 1 || option > MenuOption.values().length){
            
                System.out.println("Please select from all the available options");
            }else{
                
//      Now we assume that the user input is validated and ready to process

                // because the number of options start at 1
                //  the number of enums start at 0
                // we take 1 away from each option to match its corresponding enum
                  
            selectOption = Zoo.MenuOption.values()[option - 1]; // 0 enum 1 from user 0 == 1-1
           // select option has a correct and corresponding value to the enums 
           // if user selcts 1 then 1-1 == enum 0 
           // create a case switch to match these enums to methods 
           
           switch(selectOption){
               // option 1
                case LIST_ANIMALS:
                   zoo.listAllAnimals();
                   break;
                case LIST_TYPES:
                    zoo.listAnimalTypes();
                    break;
                //add the new case for adding animals
                case ADD_ANIMAL:
                    // there a couple of animal arguments 
                    // first we need to make sure we add the animal 
                    // to the correct animal class
                    // ask the user for the name of the animal 
                    // then ask the user for the type of the animal 
                    System.out.println("Enter the name of the animal: ");
                    String animalname = scanner.next(); // the animal name form user input
                    System.out.println("Enter the animal type: \n1: Mammal\n2: Bird\n3: Fish\n");
                    int type = scanner.nextInt();
                    // create a case for the type of animal based on 
                    // the user choice from the menu 
                    switch(type){
                            // the first case is for: Mammal
                        case 1: // add mammal here
                            zoo.addAnimal(new Mammal(animalname,12,120,"Jungle")); // a new class animal: Mammal animalname
                            break;
                        case 2:
                            zoo.addAnimal(new Bird(animalname,10,9,"Trees"));
                            break;
                        case 3:
                            zoo.addAnimal(new Fish(animalname,20,120,"Water"));
                            break;
                        // Default case is to exit
                        default:
                            System.out.println("Invalid input!");
                    }
                    break;
                case GETRANDOM_ANIMAL:
                    zoo.getRandomAnimal();
                    break;
                case EXIT:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid Option please select again!");
            }
          }
        }while(selectOption != Zoo.MenuOption.EXIT);
    }
}
