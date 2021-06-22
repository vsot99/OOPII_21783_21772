

import java.io.File;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Traveller implements Comparable<Traveller>{
        
	    private static ArrayList<Traveller> travellers = new ArrayList<Traveller>();
        private double p;
        private String name;
        private int age;
        private long timestamp;
        private String visit;
        private String residenceCity;
        private String[] candidateCities ={"rome","lisbon","berlin"};   //hardcoded
        private  int[] preferencesVector = new int[10];
        	//{5, 8, 8, 3, 2, 6, 6, 7, 9, 10}; //hardcoded
        private double[] locationVector = new double[2];
        private ArrayList<City> cities = new ArrayList<City>(); 
        
        public Traveller(String name, int age, String city) {
        	this.name = name;
        	this.age = age;
        	this.residenceCity = city;
        	Date date = new Date();
        	this.setTimestamp(date.getTime());
        	this.insertLocation();
        	this.produceCities();
        	
        	
        }
        
       
        
        /** Produces a temporary City object with the users city of residence,
           and sets the location vector of the traveller using the opendata.
        */
        public void insertLocation() {
        	
        	
        	/*if ( Traveller.searched(this.getResidenceCity()) ) {
        		
        		this.setLocationVector( City.getCitiesSearched().get(this.residenceCity).getGeodesicVector() );
        		
        	} else { */
        	
        		
        		City tmp = new City(this.getResidenceCity());
        		
        		City.getCitiesSearched().put(tmp.getName(), tmp);
            	this.setLocationVector(tmp.getGeodesicVector());
        	//}
        	
        	
        }
        
        /** For each candidate city, makes a City object, and adds it 
           to the ArrayList
        */
        public void produceCities(){
        
            for (int i = 0; i < this.getCandidateCities().length; i++ ) {
            	
            	
            	  /*if ( Traveller.searched(getCandidateCities()[i]) ) {
            		this.getCities().add( City.getCitiesSearched().get(getCandidateCities()[i]) );
            	} else { */
            	
            	
            		
            		
            		City c = new City(this.getCandidateCities()[i]);
            		this.getCities().add(c);
            		
            	    City.getCitiesSearched().put(c.getName(), c);
            	//}
            		
            }
        }
        
        public abstract double similarity_terms_vector(City city);
        
        public static double similarity_geodesic_vector(Traveller t,City c){
        	
            int maxdist= 15317;
            return (Math.log(2/(2-distance(t,c)/maxdist))/Math.log(2));

        }


        public static double distance(Traveller t,City c) { 
            double result= 0;
            final int r=6371; 

            double latDistance = Math.toRadians(c.getGeodesicVector()[0]-t.getLocationVector()[0]);
            double lonDistance = Math.toRadians(c.getGeodesicVector()[1]-t.getLocationVector()[1]);

            double a= Math.sin(latDistance/2)*Math.sin(latDistance/2)+ Math.cos(Math.toRadians(t.getLocationVector()[0]))*Math.cos(Math.toRadians(c.getGeodesicVector()[0]))*Math.sin(lonDistance/2)*Math.sin(lonDistance/2);

            double cr = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            result=r*cr; 
            
            return  result;

        }
        
        public static void printTravellers() {
        	ArrayList<Traveller> tmp = new ArrayList<Traveller>(getTravellers());
        	Collections.sort(tmp);
        	
        	ArrayList<Traveller> noDupl = new ArrayList<Traveller>();
        	for(Traveller t : tmp) {
        		if(!noDupl.contains(t)) {
        			noDupl.add(t);
        		}
        	}
        	
        	for(Traveller t : noDupl) {
        		System.out.println("Name: " + t.getName() + "  Timestamp: " + t.getTimestamp());
        	}
        	
        }
        
        
        
        //comprare_cities method Overloading--------------------------------------------------------------------------------------------------
        
        public City comprare_cities() {
             return this.getCities().get(0);
        }
        
        /** If the number the user inputs is from 2 to 5, it returns an array of the requested number of cities with the biggest similarity. 
           (συμπεριλαμβανομένου της πρώτης θεσης)
           @param num, the number of cities the user wants to display
        */
        public City[] comprare_cities(int num) {
        	if(num >= 2 && num <=5) {
        		City[] tmp = new City[num];       	
        		for(int i = 0; i < num; i++) {
        			tmp[i] = this.getCities().get(i);
        		}
        		return tmp;
        	}
        	return null;
        	
        }
        
        
      //saving and reading ArrayList travellers to/from a Json File------------------------------------------------------------------------------------------
        
        public static void writeJSON(ArrayList<Traveller> travellers) throws JsonGenerationException, JsonMappingException, IOException {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.writeValue(new File("travellers.json"), travellers);
        	
        }
        
       /* public static void readJSON() throws JsonParseException, JsonMappingException, IOException {
        	ObjectMapper mapper = new ObjectMapper();
        	
        }
        */
        
        
        
        public static void fixTravellers() {
        	for(Traveller t : Traveller.getTravellers()) {
        		for(City c : t.getCities()) {
        			c.setSimilarity(City.calculate_similarity(t, c));
        		}
        	}
        }
        
        
      //COLLABORATIVE FILTERING-------------------------------------------------------------------------------------------------------------------------

        public String getAlternative() {

            Map<String, Integer> cityRanks = Traveller.getTravellers().stream().collect(Collectors.toMap(i->i.getVisit(), i->Traveller.innerDot(this, i)));
            Map.Entry<String, Integer> maxEntry = null;
            for(Map.Entry<String, Integer> entry : cityRanks.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
                    maxEntry = entry;
                }
            }
            return maxEntry.getKey();

        }

        public static int innerDot(Traveller curTraveller, Traveller traveller) {

            int sum = 0;
            int curTravellerPref[] = curTraveller.getPreferencesVector();
            int travellerPref[] = traveller.getPreferencesVector();
            for(int i =0; i < curTravellerPref.length; i++) {
                sum += curTravellerPref[i] * travellerPref[i];
            }
            return sum;
        }
        
        
        //GETTERS AND SETTERS---------------------------------------------------------------------------------------------------------------------------
        
		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String[] getCandidateCities() {
			return candidateCities;
		}

		public void setCandidateCities(String candidateCities) {
			this.candidateCities = candidateCities.split(",");
		}

		public int[] getPreferencesVector() {
			return preferencesVector;
		}

		public void setPreferencesVector(int trm,int i) {
			this.preferencesVector[i] =trm;
		}

		public double[] getLocationVector() {
			return locationVector;
		}

		public void setLocationVector(double[] locationVector) {
			this.locationVector = locationVector;
		}

		public ArrayList<City> getCities() {
			return cities;
		}

		public void setCities(ArrayList<City> cities) {
			this.cities = cities;
		}

		public String getResidenceCity() {
			return residenceCity;
		}

		public void setResidenceCity(String residenceCity) {
			this.residenceCity = residenceCity;
		}

		public static ArrayList<Traveller> getTravellers() {
			return travellers;
		}

		public static void setTravellers(ArrayList<Traveller> travellers) {
			Traveller.travellers = travellers;
		}

		public double getP() {
			return p;
		}

		public void setP(double p) {
			this.p = p;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public String getVisit() {
			return visit;
		}

		public void setVisit() {
			this.visit = this.getCities().get(0).getName();
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int compareTo(Traveller t) {
			if( this.getTimestamp() > t.getTimestamp() ) {
				return 1;
			} else if( this.getTimestamp() < t.getTimestamp() ) {
				return -1;
			} else {
				return 0;
			}
			
		}
		
		@Override
		public boolean equals(Object o) {
			Traveller t = (Traveller) o;
			return this.getName() == t.getName() && this.getAge() == t.getAge();
		}
		
		
}






	