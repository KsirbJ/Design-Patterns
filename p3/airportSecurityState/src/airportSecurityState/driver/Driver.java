package airportSecurityState.driver;

import airportSecurityState.util.Results;
import airportSecurityState.util.FileProcessor;
import airportSecurityState.airportStates.LowRiskState;
import airportSecurityState.airportStates.ModerateRiskState;
import airportSecurityState.airportStates.HighRiskState;
import airportSecurityState.airportStates.AirportStateI;
import airportSecurityState.airportStates.AirportSecurity;
import airportSecurityState.util.MyLogger;

public class Driver {

	public static void main(String[] args) {

		if(args.length != 3){
			System.err.println("Program usage: ant -buildfile src/build.xml run " +
				"-Darg0=FIRST -Darg1=SECOND -Darg2=THIRD");
			System.exit(1);
		}

		// Set up objects and parse input
		int debug_level = 0;

		try{
			debug_level = Integer.parseInt(args[2]);
			if(debug_level < 0 || debug_level > 4)
				throw new IllegalArgumentException();
		}catch(IllegalArgumentException e){
			System.err.println("Invalid debug level " + args[2]);
			e.printStackTrace();
			System.exit(1);
		}finally{

		}

		// Set debug level
		MyLogger.setDebugValue(debug_level);
		
		AirportStateI asi = new LowRiskState();
		AirportSecurity as = new AirportSecurity(asi);
		Results res = new Results(args[1]);
		FileProcessor inf = new FileProcessor(args[0], 'r');

	
		while(true){
			MyLogger.writeMessage("Calling FileProcessor.readLine", MyLogger.DebugLevel.METHOD_CALL);
			String line = inf.readLine();
			if(line == null)
				break;

			try{
				MyLogger.writeMessage("Calling String.split", 
					MyLogger.DebugLevel.METHOD_CALL);

				String[] data = line.split(":|;");
				if(data.length == 9){
					MyLogger.writeMessage("Calling String.equals", 
						MyLogger.DebugLevel.METHOD_CALL);

					if(data[0].equals("Day") && data[2].equals("TOD") && 
						data[5].equals("Airline") && data[7].equals("Item")){

						MyLogger.writeMessage("Calling Integer.parseInt", 
							MyLogger.DebugLevel.METHOD_CALL);

						int day = Integer.parseInt(data[1]);
						if(day < 1)
							throw new IllegalArgumentException();
						// String tod = data[3];
						// String airline = data[5];
						String item = data[8];
						MyLogger.writeMessage("Calling AirportSecurity.processTraveler", 
							MyLogger.DebugLevel.METHOD_CALL);
						MyLogger.writeMessage("Calling Results.storeNewResult", 
							MyLogger.DebugLevel.METHOD_CALL);
						res.storeNewResult(as.processTraveler(day, item));

					}else{
						throw new IllegalArgumentException();
					}

				}else{
					throw new IllegalArgumentException();
				}
			}catch(IllegalArgumentException e){
				System.err.println("Ignoring invalid value: " + (line.length() > 0 ? line : "empty line"));
			}
			
		}
		// Clean up and print results
		MyLogger.writeMessage("Calling FileProcessor.closeFiles", MyLogger.DebugLevel.METHOD_CALL);
		inf.closeFiles();
		MyLogger.writeMessage("Calling Results.storeToFile", MyLogger.DebugLevel.METHOD_CALL);
		res.storeToFile();
		MyLogger.writeMessage("Calling Results.cleanUp", MyLogger.DebugLevel.METHOD_CALL);
		res.cleanUp();

	}
	
}