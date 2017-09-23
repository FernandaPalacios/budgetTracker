import java.util.ArrayList;

public class Flight {
	private String name;
	private ArrayList airports;
	private String date;

	public Flight(String name, String date) {
	    this.name = name;
	    this.date = date;
	    this.airports = new ArrayList<Airport>();
	}


    public void addAirport(Airport newAirport){
	    // check flight doesn't have repeated airport
	    if(!airports.contains(newAirport)){
	        airports.add(newAirport);
	        newAirport.addFlight(this);
        }


    }

    public boolean equals(Flight otherFlight){
        return false;
    }

    public ArrayList getAirports() {
        return airports;
    }

    public String getName(){
        return this.name;
    }

    public String getDate(){
        return this.date;
    }

    public String toString(){
        return null;
    }
}
