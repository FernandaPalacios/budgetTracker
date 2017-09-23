import java.util.ArrayList;

public class Airport {
	private String name;
	private ArrayList flights;

	public Airport(String name) {
		this.name = name;
		this.flights = new ArrayList<Flight>();
	}

	public boolean wasVisitedBy(Flight flight){
		return false;
	}

	public boolean onSameFlight(Airport airport){
		return false;
	}

	public void addFlight(Flight newFlight){

	}

	public boolean equals(Airport other_airport){
		return false;
	}

	public String getName(){
		return this.name;
	}

	public String toString(){
		return null;
	}

}