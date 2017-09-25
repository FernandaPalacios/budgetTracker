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

	public boolean equals(Airport otherAirport){
		ArrayList<Flight> thisFlights = this.getFlights();
		ArrayList<Flight> otherFlights = otherAirport.getFlights();

		if(this.getName().equals(otherAirport.getName()) &&
		thisFlights.size() == otherFlights.size()){
				// flights' order doesn't matter
			for(Flight flight : thisFlights) {
				if (!otherAirport.flights.contains(flight)) {
					return false;
				}
			}
				return true;

		}

		return false;
	}

	public String getName(){
		return this.name;
	}

	public ArrayList<Flight> getFlights(){
		return this.flights;
	}

	public String toString() {
		// "YYZ (AC123)"
		// airport name (flight1, ... flightn)
		String flights = new String("");

		for(Flight flight: this.getFlights()){
			flights += flight.getName() + ", ";
		}
		// to avoid index out of range
		if(flights.length() != 0){
			flights = flights.substring(0, flights.length() - 2);
		}
		// delete last comma
		String name = new String(this.getName());
		return this.getName().trim() + " (" +  flights + ")";
	}

}