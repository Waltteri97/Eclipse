package conn;

import java.sql.Connection;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionsPizza {
	private static DataSource pool=null;
	public static Connection getProductionConnection() throws Exception{
		if (pool!=null) {
			return pool.getConnection();
		}
		// The configuration object specifies behaviors for the connection pool.
		HikariConfig config = new HikariConfig();
		 // Configure which instance and what database user to connect with.
		config.setJdbcUrl(String.format("jdbc:mysql:///%s", "pizzadb")); //e.g. hellogoogle1 //Muista vaihtaa oikea tietokanta!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		config.setUsername("root"); // e.g. "root", "postgres"
		config.setPassword("salasana"); // e.g. "my-password"
		
		  // For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections.
		  // See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for details.	
		config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
		config.addDataSourceProperty("cloudSqlInstance", "pizzaapp-290908:europe-north1:pizzamysqlproject");
		config.addDataSourceProperty("useSSL", "false");
		
		  // ... Specify additional connection properties here.
		  // ...
		  // Initialize the connection pool using the configuration object.
		pool = new HikariDataSource(config);
		  
		Connection conn=null;
		conn = pool.getConnection();
		return conn;
	}
	
	public static Connection getDevConnection() throws Exception{
		if (pool!=null) {
			return pool.getConnection();
		}
		// The configuration object specifies behaviors for the connection pool.
		HikariConfig config = new HikariConfig();
		 // Configure which instance and what database user to connect with.
		config.setDriverClassName(System.getProperty("drivername")); // see appengine-web.xml
		config.setJdbcUrl("jdbc:mysql://localhost:3306/"+System.getProperty("databasename")); // see appengine-web.xml
		config.setUsername(System.getProperty("localusername")); // see appengine-web.xml
		config.setPassword(System.getProperty("localpassword")); // see appengine-web.xml
		
		  // Initialize the connection pool using the configuration object.
		pool = new HikariDataSource(config);
		  
		Connection conn=null;
		conn = pool.getConnection();
		return conn;
	}
}