/*
 *
 * Write the Buses program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac Buses.java
 * To execute:
 *        java Buses 7302
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class Buses {
    public static void main(String[] args) {

        int bus = Integer.parseInt(args[0]);

        if (bus>=1000 && bus<=9999){
            int sum = 0;
            for (int i = 0; i<4; i++){
                sum = sum + bus%10;
                bus = bus/10;
            }
            if (sum%2 == 0){
                System.out.println("LX");
            }
            else if (sum%2 == 1){
                System.out.println("H");
            }
            else {
                System.out.println("ERROR");
            }
        }

        else if (bus<=0){
            System.out.println("ERROR");
        }
        else{
            System.out.println("ERROR");
        }      

    }
}
