import java.util.Collections;

public class MiddleTraveller extends Traveller {

    public MiddleTraveller(String name, int age, String city) {
        super(name, age, city);
        this.setP(0.65);
        
        for ( City c : this.getCities() ) {
        	c.setSimilarity(City.calculate_similarity(this, c)); //sets the similarity for each city
        }
        Collections.sort(this.getCities(), Collections.reverseOrder());
        this.setVisit();
        Traveller.getTravellers().add(this);
    }
    
    
    @Override
    public double similarity_terms_vector(City city) throws ArithmeticException {
          int suma=0;
          int sumb=0;
          int sumc=0;
          
          for(int i=0;i<this.getPreferencesVector().length;i++){
            suma += this.getPreferencesVector()[i] * city.getTermsVector()[i];
            sumb += Math.pow(this.getPreferencesVector()[i], 2);
            sumc += Math.pow(city.getTermsVector()[i], 2);

          }
          
          return suma/(Math.sqrt(sumb) * Math.sqrt(sumc)); 
          
    }
    
}