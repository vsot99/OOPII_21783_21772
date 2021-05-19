import java.util.Collections;

public class ElderTraveller extends Traveller {
   
    public ElderTraveller(String name, int age, String city) {
        super(name, age, city);
        this.setP(0.05);
        
        for ( City c : this.getCities() ) {
        	c.setSimilarity(City.calculate_similarity(this, c)); //sets the similarity for each city
        }
        Collections.sort(this.getCities(), Collections.reverseOrder()); 
        this.setVisit();
        Traveller.getTravellers().add(this);
    }
   

	@Override
	public double similarity_terms_vector(City city) throws ArithmeticException{
		int sumintersection=0;
        int sumunion=0;
          for(int i=0;i<this.getPreferencesVector().length;i++){
             if(this.getPreferencesVector()[i]>=1 && city.getTermsVector()[i]>=1){
               sumintersection+=1;
             }
             if(this.getPreferencesVector()[i]>=1 || city.getTermsVector()[i]>=1){
                sumunion+=1;
             }
          }
          
         return sumintersection/sumunion;
          
	}
    

}