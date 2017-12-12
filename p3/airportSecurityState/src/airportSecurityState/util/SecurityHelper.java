package airportSecurityState.util;

import java.util.HashMap;

public class SecurityHelper {

	private SecurityHelper(){

	}

	/**
	 *	Get the average of a hashmap
	 *	@return double - The average
	 */
	public static double getAverage(HashMap<Integer, Integer> ls_in){
		int sum = 0;
		for(Integer key : ls_in.keySet()){
			sum += ls_in.get(key);
		}
		double average = (double)sum / ls_in.size();
		return average;
	}

	/**
	 *	Process a new traveler 
	 */
	public static void processTraveler(int day, String item, HashMap<Integer, Integer> travelers,
		HashMap<Integer, Integer> items){

		try{
			int prohib_items = item.matches("Gun|NailCutter|Blade|Knife") ? 1 : 0;

			if(travelers.containsKey(day)){
				travelers.put(day, travelers.get(day) + 1);
				items.put(day, items.get(day) + prohib_items);
			}else{
				travelers.put(day, 1);
				items.put(day, prohib_items);
			}

			
		}catch(NullPointerException e){
			System.err.println("Error while processing traveler");
			e.printStackTrace();
		}finally{

		}
		
	}


	@Override
	public String toString(){
		return "SecurityHelper";
	}
}