package persistance;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostgresBaseDao {
	
	private static long count;
	
	protected final Connection getConnection() {
		count++;
		Connection result = null;
		System.out.println("times connected to the database: " + count);
		
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
			
			result = ds.getConnection();
		} catch(Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return result;
	}
}
