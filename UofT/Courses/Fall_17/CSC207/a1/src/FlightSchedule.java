import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class FlightSchedule {

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

        Map<Airport, ArrayList<Flight>> AirportToFlights= new HashMap<Airport, ArrayList<Flight>>();


        while (scannerFile.hasNextLine()) {
            String currLine = scannerFile.nextLine();

            // split currLine by '|'
            String[] parts = currLine.split( "\\|");

            // construct Flight
           String flightName = parts[0].split("\\s")[0];
           String flightDate = parts[0].split("\\s")[1];

           Flight currFlight = new Flight(flightName, flightDate);

           // Extract airports to be added to this Flight
           String[] airportsArr = Arrays.copyOfRange(parts, 1, parts.length);
            ArrayList<String> airports = new ArrayList<String>(Arrays.asList(airportsArr));

           for(String a : airports) {
               // construct Airport
               Airport airport = new Airport(a);

               // add airport to Flight, to compare flights (with Flight's equal method later)
               airport.addFlight(currFlight);

               // check if a is already in dictionary
               if (AirportToFlights.get(airport) == null){
                   AirportToFlights.put(airport, new ArrayList<Flight>());
               }
               // check if repeated flight
               if(!airport.wasVisitedBy(currFlight)) {
                   AirportToFlights.get(airport).add(currFlight);
               }
           }

        }

        scannerFile.close();
        for (Airport a : AirportToFlights.keySet() ) {
            System.out.println(a.getName()+ AirportToFlights.get(a));
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