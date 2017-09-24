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

        ArrayList<Airport> seenAirports = readAirports(fileName);


        // Search for match
        // boolean match = hasMatch(userAirport, seenAirports);



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
            String flightDate = parts[0].split("\\s")[1];

            Flight currFlight = new Flight(flightName, flightDate);

            // Extract airports to be added to this Flight
            String[] airportNamesArr = Arrays.copyOfRange(parts, 1, parts.length);
            ArrayList<String> airportNames = new ArrayList<String>(Arrays.asList(airportNamesArr));


            // iterate over airports
            for(String airportName : airportNames){
                // check if we've seen this airportName
                if(!hasMatch(airportName, answer)){
                    System.out.println(airportName);
                }

            }


        }

        return null;
    }

    public boolean hasMatch(String AirportName, ArrayList<Airport> Airports){
        // accumulate names
        ArrayList<String> names = new ArrayList<String>();

        // iterate over airports to extract names
        for(Airport a: Airports){
            String currName = a.getName();
            if(!names.contains(currName)){
                names.add(currName);
            }
        }

        // return whether AirportName is in names
        return names.contains(AirportName);
    }



	public static void main(String[] args) {
        FlightSchedule fs = new FlightSchedule();
        fs.searchAirport("FlightList.txt");

        // Airport a1 = new Airport("YYZ");
        // Airport a2 = new Airport("YVR");
        // Airport a3 = new Airport("YHZ");
        // Airport a4 = new Airport("YUL");
        // Flight f1 = new Flight("AC123", "12-09-2017");
        //
        // ArrayList<Airport> airportsTester = new ArrayList<Airport>();
        // airportsTester.add(a1);
        // airportsTester.add(a2);
        // airportsTester.add(a3);
        // airportsTester.add(a4);
        //
        //
        // System.out.println(fs.hasMatch( "YYZ", new ArrayList<Airport>())); // False
        // System.out.println(fs.hasMatch( "GRU", airportsTester)); // False

    }
}