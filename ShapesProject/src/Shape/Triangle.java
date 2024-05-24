/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shape;

/**
 *
 * @author sljoe
 */
public class Triangle {

    int height;
    int length;

    public double getArea() {
        return 0.0;
    }

    public void test(){

        Triangle myTriangle = new Triangle();
        myTriangle.getClass();
        myTriangle.getArea();
        myTriangle.toString();
    }

    @Override
    public String toString() {
        return "Este es un triangulo con " + height + "y" + "length";
    }
    
}
