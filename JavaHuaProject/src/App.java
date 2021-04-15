

public class App {
    
	public static void main(String args[]) {
		
		Traveller me = new ElderTraveller(23, "Athens");
		
		System.out.println(me.comprare_cities().getSimilarity());
			
	}
	
}
