public class StaircaseBuilder {
    
    public static void main(String[] args) {
        int d = Integer.parseInt(args[0]);
        int bricks = Integer.parseInt(args[1]);
        char [][] staircase = new char[d][d];

        // storing the values
        // the key is to make sure that we store from the column up
        for (int i = 0; i<staircase.length; i++){
            for (int j = staircase[i].length - 1; j >= 0; j--){
                if (j >= staircase[i].length - 1 - i && bricks > 0){
                    staircase[j][i] = 'X';
                    bricks--;
                }
                else {
                    staircase[j][i] = ' ';
                }
            }
        }

        
        // print the array
        for (int i = 0; i<staircase.length; i++){
            for (int j = 0; j<staircase[i].length; j++){
                System.out.print(staircase[i][j]);
            }
            System.out.println();
        }
        System.out.println("Bricks remaining: " + bricks);

    }
}
