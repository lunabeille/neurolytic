package swing;
import java.sql.*;
import java.util.LinkedHashMap;

/**
 * 
 * @author Lucy
 * Instore a connection with sqlite db and retrieves different kind of information 
 * depending to the request
 *
 */
public class Database 
{
	/**
	 * The database name in order to change it only here if we change it
	 */
	private static final String DB_NAME = "neurolytic.db";
	
	/**
	 * this number can be change according to the amount of columns we know having in th db
	 * N.B : in order not to eat too much memory space
	 */
	private static final int MAX_COLUMN_NUMBER = 10;
	private static final String SELECT_ALL = "SELECT * FROM ";
	
	private Connection c;
	private Statement stmt;
//	private String query;
	
	public static void main( String args[] )
	{
		Database test = new Database();
		test.getColumns("Service");
	}
	
	public Database()
	{
		this.c = null;
	    this.stmt = null;
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      this.c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
	      this.c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      this.stmt = this.c.createStatement();
	    }
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	      
	}
	
	/**
	 * 
	 * @param tableName : String : the name of the table to retrieve content
	 * @return HashMap :  column_name => value
	 * N.B : we use LinkedHashMap in order to preserve the order of the columns
	 */
	public LinkedHashMap<String, String> getTableByName(String tableName)
	{
		
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		try
		{
			ResultSet rs = this.stmt.executeQuery(SELECT_ALL + tableName + ";" );

			while(rs.next()) 
			{	
				ResultSetMetaData rsmd = rs.getMetaData();
				int nbColumn = rsmd.getColumnCount();
				for(int i = 1; i <= nbColumn; i++)
				{
					String column = rsmd.getColumnName(i);
					String name = rs.getString(column);
					results.put(column, name);
				}
				System.out.println(results);	
			}
			System.exit(0);
			rs.close();
			stmt.close();
			c.close();	
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
		System.out.println("Operation done successfully");
		return results;
	}
	
	public String[] getColumns(String tableName)
	{	
		String[] results = new String[MAX_COLUMN_NUMBER];
		try
		{
			ResultSet rs = this.stmt.executeQuery(SELECT_ALL + tableName + ";" );

			while(rs.next()) 
			{	
				ResultSetMetaData rsmd = rs.getMetaData();
				int nbColumn = rsmd.getColumnCount();
				for(int i = 1; i <= nbColumn; i++)
				{
					String column = rsmd.getColumnName(i);
					results[i] = column;
					System.out.println(results[i]);
				}
				// crado, à faire mieux à la fin
				break;	
			}
			
			rs.close();
			stmt.close();
			c.close();	
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
		System.out.println("Operation done successfully");
		return results;
	}
	public int getServicesCount()
	{
		int rowsNumber = 0;
		try
		{
			ResultSet rs = this.stmt.executeQuery("SELECT COUNT(*) FROM Service;");
			rs.next();
			rowsNumber = rs.getInt(1);
			rs.close();
			stmt.close();
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	

		return rowsNumber;
	}
	public String[] getServices(int rows)
	{
		String[] results = new String[0];
		int i = 0;
		try
		{
			ResultSet rs = this.stmt.executeQuery(SELECT_ALL + "Service;");			
			results = new String[rows];
			
			while(rs.next()) 
			{	
				String name = rs.getString("name");
				results[i] = name;
				i++;
			}
			
			rs.close();
			stmt.close();
			c.close();	
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
		System.out.println("Operation done successfully");
		return results;
	}
}

