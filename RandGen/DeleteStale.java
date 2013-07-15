import java.sql.*;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteStale{
	public static void main(String[] args){
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/random";
			Properties props = new Properties();
			props.setProperty("user","www");
			props.setProperty("password","www");
			Connection conn = DriverManager.getConnection(url, props);

			System.out.println("Deleting stale records...");

			long hours24 = 1000*60*60*24;
			long oneminute = 1000*60;
			Date redline = new Date(new Date().getTime()-oneminute);
			SimpleDateFormat postgresFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			PreparedStatement ps = null;
			ps = conn.prepareStatement("DELETE FROM profiles where timestamp < '" + postgresFormat.format(redline) + "'");
            ps.executeUpdate();
            ps.close();
			
			conn.close();
		}catch(Exception e){e.printStackTrace();}
	}
}
