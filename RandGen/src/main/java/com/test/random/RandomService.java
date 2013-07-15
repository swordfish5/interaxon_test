package com.test.random;
import java.util.Random;
import java.util.Properties;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;

import org.springframework.stereotype.Service;
//import org.springframework.context.annotation.Bean;

@Service
public class RandomService{
	
	

	
	public Integer genByRange(Integer min, Integer max, Integer seed){
		double doublerand = 0.0;
		if(seed==null){
			Random generator = new Random();
			doublerand = generator.nextDouble();
		}else{
			Random generator = new Random(seed);
			doublerand = generator.nextDouble();
		}
		int range = max-min+1;
		int randint = min + (int)(doublerand*range);
		return new Integer(randint);
	}
	
	public Integer genByProfile(Integer profileid){
		Integer min = 0;
		Integer max = 0;
		Integer seed = 0;
		int range = 0;
		double doublerand = 0.0;
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/random";
			Properties dbprops = new Properties();
			dbprops.setProperty("user","www");
			dbprops.setProperty("password","www");
			Connection conn = DriverManager.getConnection(url, dbprops);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT min, max, seed FROM profiles");
			while(rs.next()){
				min = rs.getInt(1);
				max = rs.getInt(2);
				seed = rs.getInt(3);
				range = max-min+1;
			}
			rs.close();
			st.close();
			conn.close();
		}catch(Exception e){e.printStackTrace();}
		
		if(seed==null){
			Random generator = new Random();
			doublerand = generator.nextDouble();
		}else{
			Random generator = new Random(seed);
			doublerand = generator.nextDouble();
		}
		
		int randint = min + (int)(doublerand*range);
		return new Integer(randint);
	}
	
	public Integer createProfile(Integer min, Integer max, Integer seed){
        int pid = 0;
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/random";
			Properties dbprops = new Properties();
			dbprops.setProperty("user","www");
			dbprops.setProperty("password","www");
			Connection conn = DriverManager.getConnection(url, dbprops);
			
			Date now = new Date();
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			PreparedStatement ps = null;
			if(seed!=null){
				ps = conn.prepareStatement("INSERT INTO profiles VALUES (DEFAULT, " + min + ", " + max + ", " + seed + ", '" + timeFormat.format(now) + "')");
			}else{
				ps = conn.prepareStatement("INSERT INTO profiles VALUES (DEFAULT, " + min + ", " + max + ", NULL, '" + timeFormat.format(now) + "')");
			}
            ps.executeUpdate();
            ps.close();
            
            
            
			// The JDBC driver does not support the RETURNING SQL STATEMENT
            Statement statement = conn.createStatement();
            String query = "SELECT currval('profiles_pid_seq')";
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
            	pid = rs.getInt(1);
            }
            statement.close();
            
			
			conn.close();
		}catch(Exception e){e.printStackTrace();}
		return pid;
	}
	
	public void deleteProfile(Integer profileid){
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/random";
			Properties dbprops = new Properties();
			dbprops.setProperty("user","www");
			dbprops.setProperty("password","www");
			Connection conn = DriverManager.getConnection(url, dbprops);
			
			PreparedStatement ps = null;
			ps = conn.prepareStatement("DELETE FROM profiles WHERE pid=" + profileid.intValue());
            ps.executeUpdate();
            ps.close();
            conn.close();
		}catch(Exception e){e.printStackTrace();}
	}
}
