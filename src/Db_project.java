


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Db_project {
    static Connection db_con_obj = null;
    
    static PreparedStatement db_prep_obj = null;
    
    public static void makeJDBConnection(){
       try{
          Class.forName("oracle.jdbc.driver.OracleDriver");
          System.out.println("Congrats - Seems your oracle JDBC Driver Registered:!");
       
       }catch(ClassNotFoundException e){
          System.out.println("Sorry,couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
          e.printStackTrace();
          return;
       }
       
       try{
         db_con_obj = DriverManager.getConnection("jdbc:oracle:thin:@oracle12c.hua.gr:1521:orcl","it21772","it21772");
         if(db_con_obj != null){
           System.out.println("Connection Successful!");
         }else {
           System.out.println("Failed to make connection");
          
         }
       }catch(SQLException e){
        System.out.println("Oracle Connection Failed!");
        e.printStackTrace();
        return;

      }
    }
    
    public static void ReadData() throws SQLException {
       db_prep_obj = db_con_obj.prepareStatement("select * from CITIES");
       ResultSet rs = db_prep_obj.executeQuery();
       
       while (rs.next()){
    	   
    	   
         String cityname = rs.getString("cityname");
         double lat = rs.getDouble("LAT");
         double lon = rs.getDouble("LON");
         int term1 = rs.getInt("TERM1");
         int term2 = rs.getInt("TERM2");
         int term3 = rs.getInt("TERM3");
         int term4 = rs.getInt("TERM4");
         int term5 = rs.getInt("TERM5");
         int term6 = rs.getInt("TERM6");
         int term7 = rs.getInt("TERM7");
         int term8 = rs.getInt("TERM8");
         int term9 = rs.getInt("TERM9");
         int term10 = rs.getInt("TERM10");
         
         int terms[] = {term1,term2,term3,term4,term5,term6,term7,term8,term9,term10};
         
         double geoVector[]= {lat,lon};
         City tmpc = new City(cityname,terms,geoVector);
         
         City.getCitiesSearched().put(tmpc.getName(), tmpc);
         
         
         
        
         
         
       }
    }
    
  
    
 
    
    public static void addDataToDB(City c){
        try{String insertQueryStatement = "INSERT INTO CITIES VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        db_prep_obj = db_con_obj.prepareStatement(insertQueryStatement);
        db_prep_obj.setString(1,c.getName());
        db_prep_obj.setDouble(2,c.getGeodesicVector()[0]);
        db_prep_obj.setDouble(3, c.getGeodesicVector()[1]);
        db_prep_obj.setInt(4, c.getTermsVector()[0]);
        db_prep_obj.setInt(5, c.getTermsVector()[1]);
        db_prep_obj.setInt(6, c.getTermsVector()[2]);
        db_prep_obj.setInt(7, c.getTermsVector()[3]);
        db_prep_obj.setInt(8, c.getTermsVector()[4]);
        db_prep_obj.setInt(9, c.getTermsVector()[5]);
        db_prep_obj.setInt(10, c.getTermsVector()[6]);
        db_prep_obj.setInt(11, c.getTermsVector()[7]);
        db_prep_obj.setInt(12, c.getTermsVector()[8]);
        db_prep_obj.setInt(13, c.getTermsVector()[9]);
        
        
        
        int numRowChanged = db_prep_obj.executeUpdate();
        System.out.println("Rows "+numRowChanged+" changed.");
        
        } catch(SQLException e){
           e.printStackTrace();
        }
    }
    
      
  
    
}