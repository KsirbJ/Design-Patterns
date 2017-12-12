package airportSecurityState.airportStates;
import java.util.HashMap;

public interface AirportStateI {

	public AirportStateI tightenOrLoosenSecurity(HashMap<Integer, Integer> traffic_per_day, 
		HashMap<Integer, Integer> prohib_per_day);
	public String getOps();
	
}