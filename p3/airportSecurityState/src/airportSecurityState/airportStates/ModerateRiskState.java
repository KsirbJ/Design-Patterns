package airportSecurityState.airportStates;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.SecurityHelper;
import java.util.HashMap;

public class ModerateRiskState implements AirportStateI {
	
	private final String ops = "2 3 5 8 9";

	public  ModerateRiskState(){ 
		MyLogger.writeMessage("ModerateRiskState constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
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
			new_state = this;
		}else{
			new_state = new LowRiskState();
			MyLogger.writeMessage("State changed to LowRiskState", MyLogger.DebugLevel.STATE_CHANGE);
		}
		return new_state;
	}

	public String getOps(){
		return ops;
	}

	@Override
	public String toString(){
		return "ModerateRiskState";
	}
}