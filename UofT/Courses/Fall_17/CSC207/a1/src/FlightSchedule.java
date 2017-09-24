import java.io.File;
import java.util.Scanner;

public class FlightSchedule {

    // Do I need a constructor? Don't think so

    public String searchAirport(String fileName){

        // read file called FlightList.txt as input.

        File file = new File(fileName);

        // print to the screen: Enter the name of an airport
        System.out.println("Enter the name of an airport ");

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }

        scanner.close();

        // if the user types the word exit, your program should terminate.

        // check if airport is in file

        // if it is return such airport in the format 'PVG (AC123, WS592, AC225)'

        //  Otherwise, the program will continue to prompt the user for more airports.


        return null;
    }


	public static void main(String[] args) {
        FlightSchedule fs = new FlightSchedule();

        fs.searchAirport("FlightList.txt");


	}
}