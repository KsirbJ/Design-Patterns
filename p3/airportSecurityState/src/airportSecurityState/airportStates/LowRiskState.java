package airportSecurityState.airportStates;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.SecurityHelper;
import java.util.HashMap;

public class LowRiskState implements AirportStateI {

	private final String ops = "1 3 5 7 9";

	public LowRiskState(){
		MyLogger.writeMessage("LowRiskState constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	public AirportStateI tightenOrLoosenSecurity(HashMap<Integer, Integer> traffic_per_day, 
		HashMap<Integer, Integer> prohib_per_day){

		double avg_traffic = SecurityHelper.getAverage(traffic_per_day);
		double avg_prohib_items = SecurityHelper.getAverage(prohib_per_day);
		AirportStateI new_state = null; 

		if(avg_traffic >= 8 || avg_prohib_items >= 2){
			new_state = new HighRiskState(); 
			MyLogger.writeMessage("State changed to HighRiskState", MyLogger.DebugLevel.STATE_CHANGE);
		}else if(avg_traffic >= 4 || avg_prohib_items >= 1){
			new_state = new ModerateRiskState();
			MyLogger.writeMessage("State changed to ModerateRiskState", MyLogger.DebugLevel.STATE_CHANGE);
		}else{ 
			new_state = this;
		}
		return new_state;
	}

	public String getOps(){
		return ops;
	}


	@Override
	public String toString(){
		return "LowRiskState";
	}
}