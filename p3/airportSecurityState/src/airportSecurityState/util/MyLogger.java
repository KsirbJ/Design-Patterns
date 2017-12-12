
package airportSecurityState.util;

public class MyLogger{

    /*DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
      DEBUG_VALUE=3 [Print to stdout everytime the state is changed]
      DEBUG_VALUE=2 [Print to stdout everytime the driver calls a method]
      DEBUG_VALUE=1 [Print to stdout everytime a new result is calculated]
      DEBUG_VALUE=0 [No output should be printed from the applicatio to stdout. It is ok to write to the output file though]
    */

    public static enum DebugLevel {RELEASE, FROM_RESULTS, METHOD_CALL, STATE_CHANGE, CONSTRUCTOR};

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
		switch (levelIn) {
			case 4: 
				debugLevel = DebugLevel.CONSTRUCTOR; 
				break;
			case 3:
				debugLevel = DebugLevel.STATE_CHANGE;
				break;
			case 2:
				debugLevel = DebugLevel.METHOD_CALL;
				break;
		    case 1:
		    	debugLevel = DebugLevel.FROM_RESULTS;
		    	break;
			case 0: 
				debugLevel = DebugLevel.RELEASE; 
				break;
			default:
				debugLevel = DebugLevel.RELEASE;
		}
    }

    public static void setDebugValue (DebugLevel levelIn) {
		debugLevel = levelIn;
    }

    public static DebugLevel getDebugLevel(){
    	return debugLevel;
    }

    // @return None
    public static void writeMessage (String  message, DebugLevel levelIn ) {
		if (levelIn == debugLevel)
		    System.out.println(message);
    }

    /**
	 * @return String
	 */
    public String toString() {
		return "Debug Level is " + debugLevel;
    }
}