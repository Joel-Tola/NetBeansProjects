/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zoo;

/**
 *
 * @author Lecturer
 */
public class Animal {
    
//    These behaviours could be for any number of animal types
    private String name; // "Parrot" "Lion" the name of the animal
    private int age; // 12 the age of the animal
    private double weight; // 170 the weight of the animal
    private String habitat; // "Jungle" where the animal likes to live 
    
    // If we want to set the animals attributes values we will
    // use this constructor 
//    Animal("Lion", 12, 170, "Jungle")
    public Animal(String name, int age, double weight, String habitat){
        
        // This will take in the new values and will assign them to the animal
        this.name = name; // "Parrot" "Lion" the black names are the inputs 
        this.age = age; // 12 the pink names are the attibutes 
        this.weight = weight; // 170 this is the most current value for an attribute
        this.habitat = habitat; // "Jungle"

    }
    
//     Getters and Setters for these attributes 
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    
    
    
    
    
    
    
    
}
