/*
 *  
 * Write the DogWalker program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac DogWalker.java
 * To execute:
 *        java DogWalker 5
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 *
 */
public class DogWalker {

    public static void main(String[] args) {

       int n = Integer.parseInt(args[0]);

       int x = 0;
       int y = 0;

       if (n>=0){

        System.out.println("(" + x + "," + y + ")");

        for (int i = 1; i <= n; i++){
            
            int r = (int) (Math.random() * 4);

            if (r==0){
                // North
                y = y + 1;
            }

            else if (r==1){
                // South
                y = y - 1;
            }

            else if (r==2){
                // East
                x = x + 1;
            }

            else if (r==3){
                // South
                x = x - 1;
            }
            
            System.out.println("(" + x + "," + y + ")");

        }

        double distance = Math.pow(x, 2) + Math.pow(y, 2);
        System.out.println("Squared distance = " + distance);

       }

       else if (n<0){

        System.out.println("ERROR!");

       }

			   
    }
}
