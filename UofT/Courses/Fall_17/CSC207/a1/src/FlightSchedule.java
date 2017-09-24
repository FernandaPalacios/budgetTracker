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
            promptAirport();

        }

        // Case 3: Valid Input

        ArrayList<Airport> seenAirports = readAirports(fileName);



        // Search for match
        boolean match = hasMatch(userAirport, seenAirports);



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
        return null;
    }

    public boolean hasMatch(String AirportName, ArrayList<Airport> Airports){
        return false;
    }


	public static void main(String[] args) {
        FlightSchedule fs = new FlightSchedule();

        fs.searchAirport("FlightList.txt");

	}
}