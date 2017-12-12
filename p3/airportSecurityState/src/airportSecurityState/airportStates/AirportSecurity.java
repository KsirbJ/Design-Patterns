package airportSecurityState.airportStates;

import java.util.HashMap;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.SecurityHelper;

public class AirportSecurity {

	private AirportStateI cur_state;
	private HashMap<Integer, Integer> traffic_per_day;
	private HashMap<Integer, Integer> num_prohib_items;

	public AirportSecurity(){
		this(new LowRiskState());
	} 

	public AirportSecurity(AirportStateI asi_in){
		MyLogger.writeMessage("AirportSecurity constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		cur_state = asi_in;
		traffic_per_day = new HashMap<Integer, Integer>();
		num_prohib_items = new HashMap<Integer, Integer>();
	}

	public void setState(AirportStateI asi_in){
		cur_state = asi_in;
	}	

	/**
	 *	Get current state
	 *	@return the current state
	 */
	public AirportStateI getState(){
		return cur_state;
	}

	/**
	 *	Get the traffic per day list
	 *	@return HashMap - The traffic per day map
	 */
	public HashMap<Integer, Integer> getTrafficPerDay(){
		return traffic_per_day;
	}

	/**
	 *	Get the prohibited items per day list
	 *	@return HashMap - The list of prohibited items
	 */
	public HashMap<Integer, Integer> getProhibItems(){
		return num_prohib_items;
	}

	/**
	 *	Process a new traveler 
	 *	@param day - The day of the week
	 *	@param item - The item they're carrying
	 *	@return The operations performed
	 */
	public String processTraveler(int day, String item){

		SecurityHelper.processTraveler(day, item, traffic_per_day, num_prohib_items);
		cur_state = cur_state.tightenOrLoosenSecurity(traffic_per_day, num_prohib_items);
		return cur_state.getOps();		
	}

	@Override
	public String toString(){
		return "State " + cur_state.toString() +
			"\nTravelers per day: " + traffic_per_day.toString() +
			"\nProhibited items per day: " + num_prohib_items.toString();
	}


}