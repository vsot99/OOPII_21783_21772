import java.util.Collections;

public class YoungTraveller extends Traveller {
	
    public YoungTraveller(String name, int age, String city) {
        super(name, age, city);
        this.setP(0.95);
        
        for ( City c : this.getCities() ) {
        	c.setSimilarity(City.calculate_similarity(this, c)); //sets the similarity for each city
        }
        Collections.sort(this.getCities(), Collections.reverseOrder());
        this.setVisit();
        Traveller.getTravellers().add(this);
        
    }
    
    
    public double similarity_terms_vector(City city) {
           
           int sum=0;

           for(int i=0;i < this.getPreferencesVector().length; i++){
             sum+= Math.pow(this.getPreferencesVector()[i]-city.getTermsVector()[i], 2);
           }
           return 1/(1+Math.sqrt(sum));
    }

}
