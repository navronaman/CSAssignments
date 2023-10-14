/*
 * Write your program inside the main method to compute the 
 * Gravitational Force according to the assignment description.
 * 
 * To compile:
 *        javac GravitationalForce.java
 * To execute:
 *        java GravitationalForce 36000.0 1080.0 50.0
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class GravitationalForce {

    public static void main(String[] args) {
       
        double m1 = Double.parseDouble(args[0]);
        double m2 = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);
        double g = 6.6743e-11;

        if (r>0 && m1>=0 && m2 >=0){

            System.out.println((m1*m2*g)/(r*r));

        }

        else {

            System.out.println("Error!");
        }


    }
}
