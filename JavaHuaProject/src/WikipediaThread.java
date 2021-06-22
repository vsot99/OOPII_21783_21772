import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import wikipedia.MediaWiki;

public class WikipediaThread implements Runnable{
	
	private String city;
	private int[] tmpTerms = new int[10];

	public WikipediaThread(String city) {
		this.city = city;
	}
	
	
	@Override
	public void run() {
		ObjectMapper mapper = new ObjectMapper(); 
		MediaWiki mediaWiki_obj = null;
		try {
			mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    for(int i = 0; i < tmpTerms.length; i++){
	    	tmpTerms[i] = countOccurences(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), City.terms[i]);
	    }
	     
		
	}
	
	
	 /** Counts the number of times a criterion occurs in the city wikipedia article
     * @param cityArticle The string of the wikipedia article
     * @param criterion The String of the word we are looking for
     * @return An integer, the number of times the word appeared
     */
    public int countOccurences(String cityArticle, String criterion) {
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


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int[] getTmpTerms() {
		return tmpTerms;
	}


	public void setTmpTerms(int[] tmpTerms) {
		this.tmpTerms = tmpTerms;
	}

    

	
    
}
