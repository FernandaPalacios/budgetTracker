import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class FlightSchedule {

    public String searchAirport(String fileName){

        // print to the screen: Enter the name of an airport
        System.out.println("Enter the name of an airport ");

        Scanner scannerPrompt = new Scanner(System.in);
        String  searchedAirport  = scannerPrompt.nextLine();

        // exit
        if(searchedAirport.equals("exit")){
           // terminate program
            return null;
        }

        // Invalid airport
        else if(searchedAirport.length() != 3){
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

        // keep track of airports


        // accumulate airports

        while (scannerFile.hasNextLine()) {
            String currLine = scannerFile.nextLine();

            // split currLine by '|'
            String[] parts = currLine.split("\\|");

            // construct Flight
            String flightName = parts[0].split("\\s")[0];
            String flightDate = parts[0].split("\\s")[1];

            Flight currFlight = new Flight(flightName, flightDate);

            // Extract airports to be added to this Flight
            String[] airportsArr = Arrays.copyOfRange(parts, 1, parts.length);
            ArrayList<String> airports = new ArrayList<String>(Arrays.asList(airportsArr));


            // iterate over airports Strings

            // construct airport

            // check if airport was visited by currFlight

            // add this flight to airport
        }


            // look for searchedAirport in airports

            // if found, return such airport in the format 'PVG (AC123, WS592, AC225)'

             //  Otherwise,  prompt the user for more airports.


        return null;
    }


	public static void main(String[] args) {
        FlightSchedule fs = new FlightSchedule();

        fs.searchAirport("FlightList.txt");


	}
}