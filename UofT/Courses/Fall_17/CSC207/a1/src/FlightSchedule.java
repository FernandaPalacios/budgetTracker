import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class FlightSchedule {

    public String searchAirport(String fileName){


        // prompt user
        String userAirport = promptAirport();

        // Validate input

        // Case 1: exit
        if(userAirport.equals("exit")){
            // terminate program
            return null;
        }

        // Case 2: Invalid airport
        else if(userAirport.length() != 3){
            // prompt user again
            System.out.println("This is not a valid airport");
            searchAirport(fileName);

        }

        // Case 3: Valid Input
        else {
            ArrayList<Airport> seenAirports = readAirports(fileName);


            // Search for match
            boolean match = hasMatch(userAirport, seenAirports);
            // If found, return Airport Information
            if (match) {
                Airport finalAirport = getAirport(userAirport, seenAirports);
                System.out.println(finalAirport.toString());
            }
            // Otherwise, prompt user again
            else {
                searchAirport(fileName);
            }

        }
        return null;
    }

    public String promptAirport(){
        // print to the screen: Enter the name of an airport
        System.out.println("Enter the name of an airport ");
        Scanner scannerPrompt = new Scanner(System.in);
        String  answer  = scannerPrompt.nextLine();

        return answer;
    }

    public ArrayList<Airport> readAirports(String fileName){

        // Accumulate Airports
        ArrayList<Airport> answer = new ArrayList<Airport>();

        // start reading from file
        File file = new File(fileName);
        Scanner scannerFile = null;
        try {
            scannerFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scannerFile.hasNextLine()) {
            String currLine = scannerFile.nextLine();

            // split string by '|'
            String[] parts = currLine.split("\\|");

            // construct Flight
            String flightName = parts[0].split("\\s")[0];
            flightName = cleanStr(flightName);
            String flightDate = parts[0].split("\\s")[1];
            flightDate = cleanStr(flightDate);

            Flight currFlight = new Flight(flightName, flightDate);

            // Extract airports to be added to this Flight
            String[] airportNamesArr = Arrays.copyOfRange(parts, 1, parts.length);
            ArrayList<String> airportNames = new ArrayList<String>(Arrays.asList(airportNamesArr));

            for(String name : airportNames){
                name = cleanStr(name);
            }

            // iterate over airports
            for(String airportName : airportNames){
                Airport currAirport = null;
                // check if we've seen this airportName
                if(!hasMatch(airportName, answer)){
                    // create and Airport
                    currAirport = new Airport(airportName);
                    // add it to answer
                    answer.add(currAirport);
                }
                else{
                    // get airport with airportName


                    currAirport = getAirport(airportName, answer);
                }
                // update airport
                currAirport.addFlight(currFlight);
            }
        }
        return answer;
    }


    /*
    * Return the Airport in airports with name airportName.
    * Assumption: airports contain unique airports
     */
    public Airport getAirport(String airportName, ArrayList<Airport> airports){
        Airport airportAnswer = null;
        airportName = cleanStr(airportName);

        for(Airport a: airports) {
            String aName = a.getName();
            aName = cleanStr(aName);
            if (aName.equals(airportName)) {
                airportAnswer = a;
            }
        }

        if(airportAnswer == null){
            System.out.println("Airport Not Found");
        }

        return airportAnswer;
    }

    /*
    * Return whether Airports contains an airport with name AirportName.
    * Ignore whitespaces to compare names.
     */
    public boolean hasMatch(String AirportName, ArrayList<Airport> Airports){
        // accumulate names
        ArrayList<String> names = new ArrayList<String>();

        // iterate over airports to extract names
        for(Airport a: Airports){
            String currName = a.getName();
            currName = cleanStr(currName);
            if(!names.contains(currName)){
                names.add(currName);
            }
        }

        // return whether AirportName is in names
        return names.contains(cleanStr(AirportName));
    }


    public String cleanStr(String s){
        return s.replaceAll("\\s", "");
    }


	public static void main(String[] args) {
        FlightSchedule fs = new FlightSchedule();
        fs.searchAirport("FlightList.txt");

        // MIA
        // MIA (LA235)

        // ATL
        // ATL (LA235)

        // NYC
        // NYC (LA235)

        // SAO
        // SAO (AA128, AA137)

        // YYZ
        // YYZ (AC123) *******

        // PVG
        // PVG (AC123, AC123)

        // GYE
        //

        // Guayaquil
        //

    }
}