import java.io.IOException;




import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weather.OpenWeatherMap;
import wikipedia.MediaWiki;


public class City implements Comparable<City>{
	
	private static HashMap<String, City> citiesSearched = new HashMap<String, City>();
	
	static final String[] terms = {"cafe", "sea", "museums", "reastaurant", "stadium", "bars", "park", "shopping center", "zoo", "galleries"};
	
	private String name ;
	private double similarity;
    
	private int termsVector[] = new int[10];
    private double[] geodesicVector = new double[2];
    
    public City( String name ){
    	
        this.name = name;
		this.insertData(name);
		City.getCitiesSearched().put(this.name, this);
    }
    
    public City(String name,int t[],double d[]) {
    	this.name=name;
    	this.termsVector=t;
    	this.geodesicVector=d;
    	
    }
    
    /** Retrieves data from OpenWeatherMap and Wikipedia and sets values to the Object's attributes. Uses the countOccurences function.
     @param city The city's name
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
  
    
    public void insertData(String city) {

        //Create threads
        WikipediaThread t1 = new WikipediaThread(city);
        OpenWeatherThread t2 = new OpenWeatherThread(city);
        //Create controllers
        Thread wikiCtrl = new Thread(t1);
        Thread weatherCtrl = new Thread(t2);
        //start
        wikiCtrl.start();
        weatherCtrl.start();
        //wait for them to finish
        try {
            wikiCtrl.join();
            weatherCtrl.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        } 
        this.setGeodesicVector(t2.getGeoVector());
        this.setTermsVector(t1.getTmpTerms());

    }
    
    
    
    /** Counts the number of times a criterion occurs in the city wikipedia article
     * @param cityArticle The string of the wikipedia article
     * @param criterion The String of the word we are looking for
     * @return An integer, the number of times the word appeared
     */
    public static int countOccurences(String cityArticle, String criterion) {
    	cityArticle=cityArticle.toLowerCase();
    	int index = cityArticle.indexOf(criterion);
    	int count = 0;
    	while (index != -1) {
    	    count++;
    	    cityArticle = cityArticle.substring(index + 1);
    	    index = cityArticle.indexOf(criterion);
    	}
    	return count;
    }
    
    
    public static double calculate_similarity(Traveller t, City c) {
    	 double tmp = 0; 
    	 try {
    	    tmp = t.getP() * t.similarity_terms_vector(c) + ( 1 - t.getP()) * Traveller.similarity_geodesic_vector(t, c); 
    	 } catch (ArithmeticException ex) {
    		 System.out.println(" Something went wrong, please try again ");
    	 }
		 return tmp;
    }
    
    
    /**
     * Makes an object for the target city. Sets max to the first Traveller object in the ArrayList. Iterates through every Traveller object in the ArrayList,
     * and finds the Traveller with the biggest similarity for the city. Then returns it.
     * @param travellers An ArrayList of Traveller objects
     * @param cityName A string, the city's name 
     * @return
     */
    public static Traveller freeTicketWinner(ArrayList<Traveller> travellers, String cityName) {
    	City tmp = new City(cityName);
    	Traveller max = travellers.get(0);
    	for ( Traveller t : travellers) {
    		if( City.calculate_similarity(t, tmp) > City.calculate_similarity(max, tmp) ) {
    			max = t;
    		}
    	}
    	return max;
    }
    
   //GETTERS AND SETTERS ----------------------------------------------------------------------------------------------------------------------------
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getTermsVector() {
		return termsVector;
	}

	public void setTermsVector(int[] termsVector) {
		this.termsVector = termsVector;
	}

	public double[] getGeodesicVector() {
		return geodesicVector;
	}

	public void setGeodesicVector(double[] geodesicVector) {
		this.geodesicVector = geodesicVector;
	}
	
	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	
	
	public static HashMap<String, City> getCitiesSearched() {
		return citiesSearched;
	}

	public static void setCitiesSearched(HashMap<String, City> citiesSearched) {
		City.citiesSearched = citiesSearched;
	}
	
	public int compareTo(City c) {
		if( this.getSimilarity() > c.getSimilarity() ) {
			return 1;
		} else if( this.getSimilarity() < c.getSimilarity() ) {
			return -1;
		} else {
			return 0;
		}
		
	}

	

}
