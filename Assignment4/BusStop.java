/*
 * Write your program inside the main method to find the order
 * which the bus the student needs to take will arrive
 * according to the assignemnt description. 
 *
 * To compile:
 *        javac BusStop.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class BusStop {

    public static void main(String[] args) {

        // no of inputs from users
        int n = args.length;
        int buses [] = new int[n-1];
        for (int i = 0; i < n-1; i++){
            buses[i] = Integer.parseInt(args[i]);
        }
        // storing the buses that are on their way

        // This is the bus that Serena wants, the last bus in the input
        int bus = Integer.parseInt(args[n-1]);
        int order = 1000;

        for (int i = 0; i < buses.length; i++){
            if (buses[i] == bus){
                order = i + 1;
                break;
            }            
        }

        System.out.println(order);

        /* print function
        System.out.print("[");
        for (int i = 0; i < buses.length - 1; i++){
            System.out.print(buses[i] + ", ");
        }
        System.out.println(buses[(buses.length)-1] + "]");
        */

    }
}
