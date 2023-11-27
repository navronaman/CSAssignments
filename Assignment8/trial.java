public class trial {

    public static int findSTRInSequence ( String sequence, String STR ) {
        int nor = 0;

        int n = STR.length();

        for (int i = 0; i<sequence.length()-n+1; i++){
            String temp = sequence.substring(i, i+n);
            if(STR.equals(temp)){
                nor++;
            }
        }


        return nor; // update the return value
    }


    public static void main(String[] args) {

        int n = findSTRInSequence("ABCABCABC", "ABC");
        System.out.println(n);

        
    }
    
}
