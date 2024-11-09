/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zoo;

/**
 *
 * @author Lecturer
 */
public class Fish extends Animal{
    
    // Since this will extend animal it will inherent the animal properties
    
    // Create a constructor to set these attributes for Fish
    
    public Fish(String name, int age, double weight, String habitat){
    
        super(name, age, weight, habitat); 
// this will allow the parent class to acccess and set these attribute values
    
    }
    
    
    
}
