import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FlightScheduleTest {

    @Test
    public void testAirportOneFlightBegginging() {
        FlightSchedule fs = new FlightSchedule();
        fs.searchAirport("FlightList.txt");
        // MIA
        // MIA (LA235)
    }

    @Test
    public void testAirportOneFlightMiddle() {
        // ATL
        // ATL (LA235)
    }

    @Test
    public void testAirportOneFlightEnding() {
        // NYC
        // NYC (LA235)
    }

    @Test
    public void testAirportTwoFlights() {
        // SAO
        // SAO (AA128, AA137)
    }

    @Test
    public void testRepeatedFlight() {
        // YYZ
        // YYZ (AC123)

    }

    @Test
    public void testRepeatedFlightName() {
        // PVG
        // PVG (AC123, AC123)

    }
    @Test
    public void testInvalidAirport() {
        // GYE
        //
    }
}