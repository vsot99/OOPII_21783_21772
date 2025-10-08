import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import weather.OpenWeatherMap;

public class OpenWeatherThread implements Runnable {
	
	private String city;
	private String appid = "1e6bd9bafc4701c7857329e884075be7";
	private double[] geoVector = new double[2];
	
	public OpenWeatherThread(String city) {
		this.city = city;
	}
	
	
	
	@Override
	public void run(){
		
		ObjectMapper mapper = new ObjectMapper(); 
    	
	   	OpenWeatherMap weather_obj = null;
		try {
			weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID="+appid+""), OpenWeatherMap.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	   	
	   	double[] tmpGeo = new double[2];
	 	tmpGeo[0] = weather_obj.getCoord().getLat();
	 	tmpGeo[1] = weather_obj.getCoord().getLon();
	 	setGeoVector(tmpGeo);
		
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getAppid() {
		return appid;
	}



	public void setAppid(String appid) {
		this.appid = appid;
	}



	public double[] getGeoVector() {
		return geoVector;
	}



	public void setGeoVector(double[] geoVector) {
		this.geoVector = geoVector;
	}
	
	
}
