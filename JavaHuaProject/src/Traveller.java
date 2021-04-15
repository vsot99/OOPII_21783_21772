import java.util.ArrayList;

public abstract class Traveller{
        
	    private static ArrayList<Traveller> travellers = new ArrayList<Traveller>();
        private double p; 
        private int age;
        private String residenceCity;
        private String[] candidateCities = {"London", "Rome", "Lisbon"};   //hardcoded
        private int[] preferencesVector = {5, 8, 8, 3, 2, 6, 6, 7, 9, 10}; //hardcoded
        private double[] locationVector = new double[2];
        private ArrayList<City> cities = new ArrayList<City>(); 
        
        public Traveller(int age, String city) {
        	this.age = age;
        	this.residenceCity = city;
        	this.insertLocation();
        	this.produceCities();
        	Traveller.travellers.add(this);       	
        }
        
        /** Produces a temporary City object with the users city of residence,
           and sets the location vector of the traveller using the opendata.
        */
        public void insertLocation() {
        	City tmp = new City(this.getResidenceCity());
        	this.setLocationVector(tmp.getGeodesicVector());
        	
        }
        
        /** For each candidate city, makes a City object, and adds it 
           to the ArrayList
        */
        public void produceCities(){
            for (int i = 0; i < this.getCandidateCities().length; i++ ) {
            	City c = new City(this.getCandidateCities()[i]);
            	this.getCities().add(c);
            }
        }
        
        public abstract double similarity_terms_vector(City city);
        
        public static double similarity_geodesic_vector(Traveller t,City c){
        	
            int maxdist= 15317;
            return (Math.log(2/(2-distance(t,c)/maxdist))/Math.log(2));

        }


        public static double distance(Traveller t,City c) { 
            double result= 0;
            final int r=6371; //ακτίνα της γης.

            double latDistance = Math.toRadians(c.getGeodesicVector()[0]-t.getLocationVector()[0]);
            double lonDistance = Math.toRadians(c.getGeodesicVector()[1]-t.getLocationVector()[1]);

            double a= Math.sin(latDistance/2)*Math.sin(latDistance/2)+ Math.cos(Math.toRadians(t.getLocationVector()[0]))*Math.cos(Math.toRadians(c.getGeodesicVector()[0]))*Math.sin(lonDistance/2)*Math.sin(lonDistance/2);

            double cr = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            result=r*cr; //αποτελεσμα
            
            return  result;

        }
        
        
        
        //comprare_cities method Overloading--------------------------------------------------------------------------------------------------
        
        public City comprare_cities() {
             return getCities().get(0);
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

		public void setCandidateCities(String[] candidateCities) {
			this.candidateCities = candidateCities;
		}

		public int[] getPreferencesVector() {
			return preferencesVector;
		}

		public void setPreferencesVector(int[] preferencesVector) {
			this.preferencesVector = preferencesVector;
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

		
}






	