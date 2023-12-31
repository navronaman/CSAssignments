

import java.util.ArrayList;

/**
 * DNA is the carrier of genetic information of living things and is used quite
 * a few ways in todays age. What can we find out when looking at DNA?
 * To name a few, we can find out to whom it belongs and potential
 * relationships for families.
 * 
 * DNA is made up of a sequence of molecules, Some portions are the same or
 * similar amongst all humans and other portions have a higher diversity.
 * The areas we can look at to identify individuals are called STRs (Short
 * Tandem Repeats), this is where short DNA segments are repeated back to back
 * ex. AGATAGATAGATACGTACGT Here you see one STR called AGAT repeated three
 * times before being stopped by the STR ACGT repeated twice (this is just one
 * case out of many representations)
 * 
 * Using multiple STRs we can narrow our search down to be more specific to any
 * kind of information we are interested in.
 * 
 * @author Seth Kelley
 * @author Aastha Gandhi
 */

public class DNA {

    // These two instance variables are initialized and populated by 
    // createDatabaseOfProfiles() and readSTRsOfInterest()
    private Profile[] database;       // Holds all of the profile objects.
    private String[]  STRsOfInterest; // Holds all of the STRs as Strings we are interested in looking for.
                                      // These STRs are going to be used to process the DNA of everyone in the database.

    /**
     * Initialize the array of Profile objects and the STRs of interest.
     * 
     * @param databaseFile The file containing all of the names and their DNA
     *                     sequences
     * @param strsFile     The file with all the STRs of interest
     */
    public DNA ( String databaseFile, String STRsFile ) {

        /*** DO NOT EDIT ***/
        createDatabaseOfProfiles(databaseFile); // Calls createDatabase method to initialize the database array
        readSTRsOfInterest(STRsFile); // Calls readAllSTRs method to initialize the allSTRs array
    }

    /**
     * Create the database array of profiles and insert all the profiles from file.
     * 
     * Each profile includes a persons' name and two DNA sequences.
     * 
     * 1. Reads the number of profiles from the input file AND create the database array to
     *    hold that number profiles. 
     * 2. Reads people profiles from the input file @filename.
     * 3. For each person in the file
     *      a. Creates a Profile object with the information from file (see input file format below).
     *         Set the profile C1_STRs and C2_STRs to null.
     *      b. Insert the newly created profile into the next position in the database array (instance variable).
     * 
     * Input file format:
     *      - 1 line containing an integer with the number of profiles/people in the file, call that number p.
     *      - for each p profiles in the file
     *          - 1 line containing the person's name
     *          - 1 line containing the first sequence of STRs
     *          - 1 line containing the second sequence of STRs
     * 
     * You can use StdIn.readLine() to read 1 (one) line from the file.
     * StdIn.setFile() opens the file filename for reading.
     * 
     * @param filename The input file containing the persons name and DNA sequences
     */
    public void createDatabaseOfProfiles ( String filename ) {

        StdIn.setFile(filename); // DO NOT remove this line, keep it as the first line in the method.

        int p = Integer.parseInt(StdIn.readLine()); // casts the first line as an integer

        database = new Profile[p]; // updates the size of profiles array with the p
        // String[][] database2 = new String[p][3];

        for (int i = 0; i<p; i++){ // updates the profiles array with profiles
            String name = StdIn.readLine();
            String sequence1 = StdIn.readLine();
            String sequence2 = StdIn.readLine();

            // database2[i][0] = name;
            // database2[i][1] = sequence1;
            // database2[i][2] = sequence2;

            Profile profilei = new Profile(name, null, null, sequence1, sequence2); // stores the profile in the array
            database[i] = profilei;

        }

    }

    /**
     * Create the STRsOfInterest array of STR and insert all STRs from file.
     * 
     * 1. Reads the number of STRs from the input file AND create the STRsOfInterest array to
     *    hold that number STRs. 
     * 2. For each STR in the file
     *      Insert into STRsOfInterest
     * 
     * Input file format:
     *      - 1 line containing an integer with the number of STRs in the file, call that number s.
     *      - s lines of STRs
     * 
     * You can use StdIn.readLine() to read 1 (one) line from the file.
     * StdIn.setFile() opens the file filename for reading.
     * 
     * @param filename The input file containing all the STRs
     */
    public void readSTRsOfInterest ( String filename ) {

        StdIn.setFile(filename); // DO NOT remove this line, keep as the first line in the method.

        String firstline = StdIn.readLine(); // gets the firstline
        int n = Integer.parseInt(firstline); // casts to an integer

        STRsOfInterest = new String[n]; // updates the size of the string array

        for (int str = 0; str<n; str++){ // updates the string array
            STRsOfInterest[str] = StdIn.readLine();
        }

    }

    /**
     * Creates the Profile for the unknown DNA sequence from filename.
     * 
     * 1. Set the Profile name to "Unknown" because they are currently Unknown.
     * 2. Set the Profile S1_STRs and S2_STRs to null (later to be calculated).
     * 3. Set the Profile sequence1 to be the first line of the file.
     * 4. Set the Profile sequence2 to be the second line of the file.
     * 5. Return the Profile object
     * 
     * File format (only two lines):
     *      - first line containing a DNA sequence
     *      - second line containing a DNA sequence
     * 
     * @param filename The input file for the unknown DNA sequence
     * @return Returns a Profile object for the unknown DNA sequence
     */
    public Profile createUnknownProfile ( String filename ) {

	    StdIn.setFile(filename); // DO NOT remove this line, keep as the first line in the method.

        String sequence1 = StdIn.readLine(); // since each profile only have 2 sequences, we only the first two lines
        String sequence2 = StdIn.readLine(); 

        Profile Unknown = new Profile("Unknown", null, null, sequence1, sequence2); // new profile with nulls for ST_STRs

        return Unknown; // update the return value
    }

    /**
     * Given a DNA sequence and a singular STR, this method will create a
     * STR Object with the STR name and the longest number of repeats of that STR 
     * within the DNA sequence.
     * 
     * @param sequence The DNA sequence (String) to be looked at
     * @param STR      The STR (String) to look for in the DNA sequence
     * @return         The STR object with the name and longest number of repeats
     */
    public STR findSTRInSequence ( String sequence, String STR ) {
        
        int nor = 0; // number of consecutive repeats
        int mr = 0; // number of maximum consecutive repeats

        int n = STR.length(); // usually 3 or 4

        for (int i = 0; i<sequence.length(); i++){ // go through the sequence
            if (sequence.startsWith(STR, i)){ // startswith compares the first n letter from STR and sequence [starting from i]
                nor++; // if this is true, we add 1 to nor
                i = n - 1 + i; // now we shift the index from i to i+2 or i+3 
            }
            else{
                if (nor>mr){ // after 2-3 times the first if block is true, we update the max repeats
                    mr = nor;
                }
            nor = 0; // nor becomes zero everytime the if block is false, so we can run the procedure again
            }
        }

        if (nor>mr){ // a check, just in case
            mr = nor;
        }

        STR newSTR = new STR(STR, mr); // creating a new STR

        return newSTR;

        }
    

    /**
     * Compute the STRs (S1_STRs and S2_STRs) for the profile.
     * 
     * USE the findSTRInSequence method.
     * 
     * @param profile The profile of the that the method will compute the STRs array for
     * @param allSTRs The list of STRs to be looked for in the profiles DNA sequences
     */
    public void createProfileSTRs ( Profile profile, String[] allSTRs ) {
        // our profile has name, "Jake", sequence1, sequence2
        // allSTRs is an array of STRs like "AGAT", "CACA", etc
        // our job is to update the null values, calculate STR_S1 and STR_S2    
        
        String sequence1 = profile.getSequence1(); //get seq1
        String sequence2 = profile.getSequence2(); //get seq2

        int n = allSTRs.length; 
        
        STR[] S1_STRs = new STR[n]; // we're gonna use these STR arrays to update the profile
        STR[] S2_STRs = new STR[n];

        for (int i = 0; i<n; i++){
            S1_STRs[i] = findSTRInSequence(sequence1, allSTRs[i]); // use the method to generate STRs for sequence1
            S2_STRs[i] = findSTRInSequence(sequence2, allSTRs[i]); // same for sequence2                   
        }

        profile.setS1_STRs(S1_STRs); //update the profiles
        profile.setS2_STRs(S2_STRs);

    }

    /**
     * Call createProfileSTRs() for each profile in the database.
     */
    public void createDatabaseSTRs() {

        // we just update all the profiles of the database to have S1_STRs and S2_STRs
        for (int i = 0; i<database.length; i++){
            createProfileSTRs(database[i], STRsOfInterest);
        }


    }
    

    /**
     * Compares two STR arrays to determines if they are identical.
     * 
     * Two STR arrays are identical if for every i in the array, the objects 
     * at s1[i] and s2[i] contain the same information. 
     *      - s1[0] matches s2[0], and
     *      - s1[1] matches s2[1], and so on.
     * 
     * Assume the @s1 and @s2 are of the same length.
     * 
     * @param s1 STR array from one profile.
     * @param s2 STR array from another profile.
     * @return Returns true if the objects in the arrays are a complete match, otherwise false
     */
    public boolean identicalSTRs ( STR[] s1, STR[] s2 ) {

        // if the arrays don't have the sem length, or they're null, default false
        if (s1.length!=s2.length || s1==null || s2==null){
            return false;
        }

        int n = s1.length; // assuming they both have same length
        // STR has two things, int and STR and we'll compare both of them for each STR in the array
        for (int i = 0; i<n; i++){
            String tempstr1 = s1[i].getSTR(); // temp strings from STR[i]
            String tempstr2 = s2[i].getSTR();
            int tempnor1 = s1[i].getRepeats(); // temp integers from STR[i]
            int tempnor2 = s2[i].getRepeats();

            if (!(tempnor1==tempnor2 && tempstr1.equals(tempstr2))){ // if not true
                return false;
            }

        }

        return true; 
    }

    /**
     * Attempts to find a profile in the database that matches the
     * unkown profile's array of STRs found in sequence1.
     * 
     * Use identicalSTRs()
     * 
     * @param unknownProfileS1_STRs The sequence1 STRs of the person the method is searching for.
     * @return                      Returns an ArrayList with all matching profile(s). It will return 
     *                              an empty ArrayList if no match is found.
     */
    public ArrayList<Profile> findMatchingProfiles ( STR[] unknownProfileS1_STRs ) {

        ArrayList<Profile> alp = new ArrayList<>();

        for (int i = 0; i<database.length; i++){
            STR [] tempS1_STRs = database[i].getS1_STRs(); // we get S1_STRs from the profile database[i]
            if (identicalSTRs(tempS1_STRs, unknownProfileS1_STRs)){ // we compare them, if true add
                alp.add(database[i]);
            }
            
        }

	return alp; 
    }

    /**
     * 
     * A punnet square is a simple way of discovering all of the potential combinations of 
     * genotypes that can occur in children, given the genotypes of their parents.
     * 
     * This method acts as a punnet square checker to check if all the STRs in 
     * the array match between the parents and offspring for any one square in the
     * punnet square.
     * 
     * This method used in the findPossibleParents method. 
     *
     * @param firstParent                The STRs of one parent
     * @param inheritedFromFirstParent   The one pairing of STRs for the offspring
     * @param secondParent               The STRs of the other parent
     * @param inheritedFromSecondParent  The second pairing of STRs for the offspring
     * @return Returns true if:
     *           - the STRs from the first parent matches the offspring STRs inherited from the first parent.
     *           AND
     *          - the STRs from the second parent matches the offspring STRs inherited from the second parent.
     */
    public boolean punnetSquare( STR[] firstParent,  STR[] inheritedFromFirstParent, 
                                 STR[] secondParent, STR[] inheritedFromSecondParent ) {

        /* DO NOT EDIT */

        for ( int i = 0; i < firstParent.length; i++ ) {

            if ( !(firstParent[i].equals(inheritedFromFirstParent[i]) && secondParent[i].equals(inheritedFromSecondParent[i])) ) {
                return false; // Returns false if there is a discrepency
            }
        }
        return true; 
    }

    /**
     * Looks at the STR sequences of any given person and tries to find the
     * potential relatives (parents) of that person based on their STR sequences
     * 
     * @param S1_STRs  The first list of STRs contained by the offspring that one
     *                 parent passed down
     * @param S2_STRs  The second list of STRs contained by the offspring that the
     *                 other parent passed down
     * @return Returns the array of profiles that are related
     */
    public ArrayList<Profile> findPossibleParents ( STR[] S1_STRs, STR[] S2_STRs ) { 

        // S1-STRs and S2_STRs are the inherited from first parent and inherited from second parent
        // 😭😭😭

         ArrayList<Profile> possibleParent1 = new ArrayList<>();
         ArrayList<Profile> possibleParent2 = new ArrayList<>();

         for ( int i = 0; i < database.length; i++ ) {

            // green checkmark is parent2 and yellow checkmark is parent1

            // S1 and S1 is parent 1? no maybe parent2
            if (identicalSTRs(database[i].getS1_STRs(), S1_STRs)) {
                possibleParent2.add(database[i]);
            }

            // S1 and S2 (bottom left) is parent 1 [yellow mark] (20)
            if (identicalSTRs(database[i].getS1_STRs(), S2_STRs)) {
                possibleParent1.add(database[i]);
             }

            // S1 and S2 (top right) is parent 2 [green checkmark] (25)
             if (identicalSTRs(database[i].getS2_STRs(), S1_STRs)) {
                possibleParent2.add(database[i]);
             }

             // S2 and S2 is parent 2? well no they're parent 1 now
             if (identicalSTRs(database[i].getS2_STRs(), S2_STRs)) {
                possibleParent1.add(database[i]);
             }
        }

        ArrayList<Profile> parentList = new ArrayList<>();

         for ( int p1 = 0; p1 < possibleParent1.size(); p1++ ) {

            for ( int p2 = 0; p2 < possibleParent2.size(); p2++ ) {

                if ( !possibleParent1.get(p1).equals(possibleParent2.get(p2)) ) {

                    // NAMAN WAY - 63/80
                    /*
                    if ( punnetSquare(
                        possibleParent2.get(p2).getS2_STRs(), S1_STRs, 
                        possibleParent1.get(p1).getS1_STRs(), S1_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } 
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS2_STRs(), S2_STRs, 
                            possibleParent1.get(p1).getS1_STRs(), S2_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    }
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS2_STRs(), S2_STRs, 
                            possibleParent1.get(p1).getS1_STRs(), S1_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } 
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS2_STRs(), S1_STRs, 
                            possibleParent1.get(p1).getS1_STRs(), S2_STRs))
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    }
                    */
                
                    
                    // HET WAY - 73/80

                    /*
                    if ( punnetSquare(
                        possibleParent2.get(p2).getS1_STRs(), S1_STRs, 
                        possibleParent1.get(p1).getS1_STRs(), S1_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } 
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS2_STRs(), S2_STRs, 
                            possibleParent1.get(p1).getS2_STRs(), S2_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    }
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS2_STRs(), S2_STRs, 
                            possibleParent1.get(p1).getS1_STRs(), S1_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } 
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS1_STRs(), S1_STRs, 
                            possibleParent1.get(p1).getS2_STRs(), S2_STRs))
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    }
                    */

                    // doesn't work

                    // first condition checks parents2's s1 with child's s1 and parent1's s1 with child's s2
                    if ( punnetSquare(
                        possibleParent2.get(p2).getS1_STRs(), S1_STRs, 
                        possibleParent1.get(p1).getS1_STRs(), S2_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } 
                    // second condition checks parents2's s1 with child's s1 and parent1's s2 with child's s2
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS1_STRs(), S1_STRs, 
                            possibleParent1.get(p1).getS2_STRs(), S2_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    }
                    // third condition checks parents2's s2 with child's s1 and parent1's s1 with child's s2
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS2_STRs(), S2_STRs, 
                            possibleParent1.get(p1).getS1_STRs(), S1_STRs)) 
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } 
                    // fourth condition checks parents2's s2 with child's s1 and parent1's s2 with child's s2
                    else if (punnetSquare(
                            possibleParent2.get(p2).getS2_STRs(), S1_STRs, 
                            possibleParent1.get(p1).getS2_STRs(), S2_STRs))
                    {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    }

                    
                }
            }
        }
        return parentList;
    }

    /**
     * Getter for the database/profiles instance variable
     * 
     * @return The database instance variable
     */
    public Profile[] getDatabase() {

        /* DO NOT EDIT */

        return database;
    }

    /**
     * Getter for allSTRs instance variable
     * 
     * @return The allSTRs instance variable
     */
    public String[] getSTRsOfInterest() {

        /* DO NOT EDIT */

        return STRsOfInterest;
    }
}
