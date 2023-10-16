/*
 * Write your program inside the main method to build
 * a staicase in a 2D array of characters according
 * to the assignment description
 *
 * To compile:
 *        javac StaircaseBuilder.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class StaircaseBuilder {
    
    public static void main(String[] args) {
        int d = 3;
        int bricks = 6;

        /*
        // no of bricks per row
        int nr = d;

        
        String [][] staircase = new String[d][d];

        for (int i = 0; i<staircase.length; i++){
            for (int j = d-1; j>=0; j = j - 1){
                for(int k = 0; k<nr; nr = nr - 1){
                    staircase[i][k] = "X";
                }
                
            }
        }

        
        */

        String [][] staircase = new String[d][d];

        for (int i = 0;i<staircase.length; i++){
            for (int j = staircase[i].length-1; j < i; j++){
                staircase[i][j] = "X";
            }
        }


        for (int i = 0; i<staircase.length; i++){
            for (int j = 0; j<staircase[i].length; j++){
                System.out.print(staircase[i][j]);
            }
            System.out.println();
        }


        // WRITE YOUR CODE HERE
    }
}
