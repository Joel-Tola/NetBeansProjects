/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package zoo;

/**
 *
 * @author Lecturer
 */
public interface Zoo {

    
        enum MenuOption{
            // THese are the menu options we would like to present to the user
            
            LIST_ANIMALS, // 0
            LIST_TYPES, // 1
            ADD_ANIMAL,
            GETRANDOM_ANIMAL,
            EXIT // 2
        }
        
        // THese are abstract methods
        
        void listAllAnimals();
        
        void listAnimalTypes();
        
        void addAnimal(Animal animal); // get the list [] reference and .add()
        
        void getRandomAnimal(); // generate a random data point from the existing records
               
  
        
}
