package exceptions;

public class WikipediaNoCityException extends Exception {
	private static final long serialVersionUID = 1L;
	
	static int numExcepetions=0;
	private String query;
	public WikipediaNoCityException(String query) {
		numExcepetions++;
		this.query = query;
	}
	
	public String getMessage() {
		return query + " is not a city, try again with an existing city's name.";
	}
	
}