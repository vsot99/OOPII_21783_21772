import java.io.IOException;



import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class App {
    
	public static void main(String args[]) throws InterruptedException, JsonGenerationException, JsonMappingException, IOException , SQLException{
		
		
		
		new Frame();
		
		
		/*Db_project.makeJDBConnection();
		City klmt = new City("paris");
		City klmt1 = new City("london");
		Db_project.addDataToDB(klmt);
		Db_project.addDataToDB(klmt1);
		Db_project.ReadData();
		
		
		System.out.println(City.getCitiesSearched().get("london").getName());
		*/
		
		
		
		//Traveller me = new YoungTraveller("Bill", 18, "Athens");
		//Traveller duplicate = new YoungTraveller("Bill", 19, "Sidney");
		//Traveller duplicate1 = new YoungTraveller("Bill", 19, "Athens");
		//Traveller he = new MiddleTraveller("John", 34, "Athens");
		//Traveller she = new ElderTraveller("George", 60, "Athens");  */
		
		//System.out.println("Traveller : " + me.getName());
		//System.out.println("Visit : " + me.getVisit());
		/*Traveller.printTravellers();
		System.out.println();
		
		for (Traveller t : Traveller.getTravellers()) {
			
			System.out.println("Traveller : " + t.getName());
			System.out.println("Timestamp : " + t.getTimestamp());
			System.out.println("Visit : " + t.getVisit());
			for( City c : t.getCities()) {
				System.out.println("city: " + c.getName() + "  similarity: " + c.getSimilarity());
			}
			System.out.println();
		} 
		
		/*Traveller winner = City.freeTicketWinner(Traveller.getTravellers(), "Rome");
		System.out.println("Winner is the traveller with age: " + winner.getAge());
		
		City tmp = me.comprare_cities();
		System.out.println("The city with the biggest similarity is " + tmp.getName());
		
		City[] tmpArr= me.comprare_cities(2);
		for(City c : tmpArr) {
			System.out.println(c.getName());
		} */
		
	}
	
}
