

public class App {
    
	public static void main(String args[]) {
		
		Traveller me = new YoungTraveller(18, "Athens");
		Traveller he = new MiddleTraveller(34, "Athens");
		Traveller she = new ElderTraveller(60, "Athens");
		
		for (Traveller t : Traveller.getTravellers()) {
			System.out.println("Traveller with age : " + t.getAge());
			for( City c : t.getCities()) {
				System.out.println("city: " + c.getName() + "  similarity: " + c.getSimilarity());
			}
			System.out.println();
		}
		
		Traveller winner = City.freeTicketWinner(Traveller.getTravellers(), "Rome");
		System.out.println("Winner is the traveller with age: " + winner.getAge());
		
		City tmp = me.comprare_cities();
		System.out.println("The city with the biggest similarity is " + tmp.getName());
		
		City[] tmpArr= me.comprare_cities(2);
		for(City c : tmpArr) {
			System.out.println(c.getName());
		}
	}
	
}
