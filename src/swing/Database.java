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
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM ";
	
	private Connection c;
	private Statement stmt;
//	private String query;
	
	public static void main( String args[] )
	{
		Database test = new Database();
		test.buildService("Ressources Humaines");
	}
	
	/**
	 * Initiates the connection and statement
	 */
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
	 * Retrieve the service ID from a given name
	 * @param serviceName String
	 * @return int 
	 */
	public int getServiceId(String serviceName)
	{
		int id = 0;
		try
		{
			String query = "SELECT id_service FROM Service WHERE name = '" + serviceName + "';";
			ResultSet rs = this.stmt.executeQuery(query);
			rs.next();
			id = rs.getInt(1);
			rs.close();
			stmt.close();
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
		System.out.println(id);
		return id;
	}
	
	/**
	 * Build a Service from a given name and fills it with the employees'list
	 * @param Servicename String
	 * @return Service object 
	 */ 
	public Service buildService(String Servicename)
	{
		Service service = new Service();
		int serviceId = this.getServiceId(Servicename);
		int employeeNumber = this.countEmployee(serviceId);
		
		Employee[] list = new Employee[employeeNumber];
		
		
		try
		{
			ResultSet rs = this.stmt.executeQuery(SELECT_ALL + "Employee WHERE id_service = " + serviceId +  ";" );
			int i = 0;
			while(rs.next()) 
			{	
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String firstname = rs.getString(3);
				String sexe = rs.getString(4);
				String bdate = rs.getString(5);
				Employee e = new Employee(id, name, firstname,sexe, bdate);
				list[i] = e;	
				i++;
			}
			rs.close();
			stmt.close();	
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
		service.setTeam(list);
		service.setName(Servicename);
		System.out.println(service);
		return service;
	}
	
	/**
	 * Retrieves the number of employees in a given service
	 * @param serviceName String
	 * @return int 
	 */
	public int countEmployee(int serviceId)
	{
		int nbEmployee = 0;		
		try
		{
			String query  = SELECT_COUNT + "Employee WHERE id_service = "  + serviceId + ";"; 
			ResultSet rs = this.stmt.executeQuery(query);
					
			rs.next();
			nbEmployee = rs.getInt(1);
			rs.close();
			stmt.close();
			System.out.println(nbEmployee);	
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
		return nbEmployee;
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
				
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
		System.out.println("Operation done successfully");
		return results;
	}
	
	/**
	 * Retreives the name of the columns of a table
	 * @param tableName
	 * @return
	 */
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
				
		}
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }	
		System.out.println("Operation done successfully");
		return results;
	}
	
	/**
	 * Retreives the number of services in the db in order to instantiate the treeMenu
	 * @return
	 */
	public int getServicesCount()
	{
		int rowsNumber = 0;
		try
		{
			ResultSet rs = this.stmt.executeQuery(SELECT_COUNT +  "Service;");
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
	
	/**
	 * Retrieves the names of the different services in the db
	 * @param rows int
	 * @return array of names
	 */
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

