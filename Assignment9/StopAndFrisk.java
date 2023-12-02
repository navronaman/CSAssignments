import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile ( String csvFile ) {

        // DO NOT remove these two lines
        StdIn.setFile(csvFile); // Opens the file
        StdIn.readLine();       // Reads and discards the header line

        // how do we know how many lines in the files
        while(StdIn.hasNextLine()){

            String[] recordEntries = StdIn.readLine().split(","); // splits the line into different things

            int year = Integer.parseInt(recordEntries[0]); // part of SFY or database[i]

            String description = recordEntries[2]; // part of SFR

            String gender = recordEntries[52]; // part of SFR

            String race = recordEntries[66]; // part of SFR

            String location = recordEntries[71]; // part of SFR

            Boolean arrested = recordEntries[13].equals("Y"); // part of SFR

            Boolean frisked = recordEntries[16].equals("Y"); // part of SFR

            SFRecord newSFR = new SFRecord(description, arrested, frisked, gender, race, location);

            for (int i = 0; i<database.size(); i++){
                if (year==database.get(i).getcurrentYear()){
                    database.get(i).addRecord(newSFR);
                }
                else {
                    SFYear newSFY = new SFYear(year);
                    newSFY.addRecord(newSFR);
                    database.add(newSFY);
                }
            }

            }
        
    }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {

        ArrayList<SFRecord> racey = new ArrayList<>();

        for (int i = 0; i<database.size(); i++){
            int tempy = database.get(i).getcurrentYear();
            // checks if the year matches with the user inputted year
            if (tempy==year){
                for (int j = 0; j<database.get(i).getRecordsForYear().size(); j++){
                    String tempr = database.get(i).getRecordsForYear().get(j).getRace();
                    // checks if the race is the same
                    if (tempr.equals(race)){
                        // add the object to the arraylist
                        racey.add(database.get(i).getRecordsForYear().get(j));
                    }
                }
            }
        }

        return racey;

    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        int countOfPopFrisked = 0;
        int countOfPopArrested = 0;

        int numberOfRecordsForThatYear = 0;

        for (int i = 0; i<database.size(); i++){
            if (database.get(i).getcurrentYear()==year){
                numberOfRecordsForThatYear = database.get(i).getRecordsForYear().size();
                for (int j = 0; j<database.get(i).getRecordsForYear().size(); j++){
                    if (database.get(i).getRecordsForYear().get(j).getFrisked()){
                        countOfPopFrisked++;
                    }
                    if (database.get(i).getRecordsForYear().get(j).getArrested()){
                        countOfPopArrested++;
                    }
                }                
            }
        }

        double percentageOfPopFrisked = (double) countOfPopFrisked/numberOfRecordsForThatYear;
        double percentageOfPopArrested = (double) countOfPopArrested/numberOfRecordsForThatYear;

        double[] hello = new double[2];
        hello[0] = percentageOfPopFrisked;
        hello[1] = percentageOfPopFrisked;

        return hello;
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {

        int white = 0;
        int black = 0;
        int whiteMale = 0;
        int blackMale = 0;
        int whiteFemale = 0;
        int blackFemale = 0;

        for (int i = 0; i<database.size(); i++){
            if(database.get(i).getcurrentYear()==year){
                for (int j = 0; j<database.get(i).getRecordsForYear().size(); j++){
                    if (database.get(i).getRecordsForYear().get(j).getRace().equals("W")){
                        white++;
                        if(database.get(i).getRecordsForYear().get(j).getGender().equals("M")){
                            whiteMale++;
                        }
                        else if(database.get(i).getRecordsForYear().get(j).getGender().equals("F")){
                            whiteFemale++;
                        }
                    }

                    else if (database.get(i).getRecordsForYear().get(j).getRace().equals("B")){
                        black++;
                        if(database.get(i).getRecordsForYear().get(j).getGender().equals("M")){
                            blackMale++;
                        }
                        else if(database.get(i).getRecordsForYear().get(j).getGender().equals("F")){
                            blackFemale++;
                        }
                    }
                }
            }
        }

        double[][] hey = new double[2][3];

        hey[0][0] = (double) (blackFemale/black)*0.5*100;
        hey[0][1] = (double) (whiteFemale/white)*0.5*100;
        hey[0][2] = (double) ((blackFemale+whiteFemale)/(black+white))*0.5*100;

        hey[1][0] = (double) (blackMale/black)*0.5*100;
        hey[1][1] = (double) (whiteMale/white)*0.5*100;
        hey[1][2] = (double) ((blackMale+whiteMale)/(black+white))*0.5*100;

        return hey; // update the return value
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        
        // WRITE YOUR CODE HERE

	return 0.0; // update the return value
    }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {

        // WRITE YOUR CODE HERE

        return null; // update the return value
    }

}
