import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class FlightSchedule {

    // Do I need a constructor? Don't think so

    public String searchAirport(String fileName){

        // print to the screen: Enter the name of an airport
        System.out.println("Enter the name of an airport ");

        Scanner scannerPrompt = new Scanner(System.in);
        String  currAirport  = scannerPrompt.nextLine();

        // exit
        if(currAirport.equals("exit")){
           // terminate program
            return null;
        }

        // Invalid airport
        else if(currAirport.length() != 3){
            // prompt user again

        }

        // Valid Airport, check if airport is in file

        File file = new File(fileName);
        Scanner scannerFile = null;
        try {
            scannerFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // This creates dictionary of the form:
        // key: Name, value: flights

        Map<String, ArrayList<String>> AirportToFlights= new HashMap<String, ArrayList<String>>();


        while (scannerFile.hasNextLine()) {
            String currLine = scannerFile.nextLine();

            // split currLine by '|'
            String[] parts = currLine.split( "\\|");

            // extract Airports names and add Airport: Flight to dictionary
           String flightName = parts[0].split("\\s")[0];
           String[] airportsArr = Arrays.copyOfRange(parts, 1, parts.length);
           ArrayList<String> airports = new ArrayList<String>(Arrays.asList(airportsArr));

           for(String a : airports) {
               // check if a is already in dictionary
               if (AirportToFlights.get(a) == null) {
                   AirportToFlights.put(a, new ArrayList<String>());
               }

               // TODO: check if repeated flight
               
               AirportToFlights.get(a).add(flightName);
           }

        }

        scannerFile.close();
        for ( String a : AirportToFlights.keySet() ) {
            System.out.println(a + AirportToFlights.get(a));
        }


        // if it is return such airport in the format 'PVG (AC123, WS592, AC225)'

        //  Otherwise, the program will continue to prompt the user for more airports.

        // String value = dictionary.get("key");

        return null;
    }


	public static void main(String[] args) {
        FlightSchedule fs = new FlightSchedule();

        fs.searchAirport("FlightList.txt");


	}
}