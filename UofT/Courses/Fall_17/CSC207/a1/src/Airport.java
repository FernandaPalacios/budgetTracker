import java.util.ArrayList;

public class Airport {
	private String name;
	private ArrayList flights;

	public Airport(String name) {
		this.name = name;
		this.flights = new ArrayList<Flight>();
	}

	public boolean wasVisitedBy(Flight flight){
		return flights.contains(flight);
	}

	public boolean onSameFlight(Airport airport){
		// copy these flights
		ArrayList thisFlightsCopy = new ArrayList<Flight>(this.flights);

		// retain intersection between these flights and others' flights
		thisFlightsCopy.retainAll(airport.flights);

		// return true if size > 0
		return thisFlightsCopy.size() > 0;

	}

	public void addFlight(Flight newFlight){
		// check this Airport doesn't have repeated flight
		if(!flights.contains(newFlight)){
			flights.add(newFlight);
			newFlight.addAirport(this);
		}

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