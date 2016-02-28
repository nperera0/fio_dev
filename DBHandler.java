package fio_dev;

// Import required packages
import java.sql.*;

public class DBHandler {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DEVdb_URL = "jdbc:mysql://localhost/DEVdb";

	// Database credentials
	static final String USER = "username";
	static final String PASS = "password";

	// Database Handler constructor
	public DBHandler() {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3: Create a Database
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			String sql = "CREATE DATABASE IF NOT EXISTS DEVdb";
			stmt.executeUpdate(sql);

			// STEP 4: Switch to created Database
			sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			System.out.println("Database created successfully...");

			// STEP 5: Create new tables in Database
			//STEP 5.1: create demographics table
			sql = "CREATE TABLE IF NOT EXISTS demographics_tbl"
					+ "(patient_id INT NOT NULL AUTO_INCREMENT,"
					+ "first_name VARCHAR(20)," + "last_name VARCHAR(20),"
					+ "age INT," + "birth_date DATE,"
					+ "PRIMARY KEY ( patient_id ));";
			stmt.executeUpdate(sql);
			
			//STEP 5.2: create documents table
			sql = "CREATE TABLE IF NOT EXISTS documents_tbl"
					+ "(document_type VARCHAR(20),"
					+ "document_issuer VARCHAR(20) NOT NULL," + "document_id INT NOT NULL,"
					+ "patient_id INT NOT NULL,"
					+ "PRIMARY KEY ( document_id, document_issuer ),"
					+ "FOREIGN KEY ( patient_id ) REFERENCES demographics_tbl( patient_id ) );";
			stmt.executeUpdate(sql);
			
			System.out.println("Table created successfully...");

			// STEP 6: Clean-up environment
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}// end constructor

	public void createNewDemograhipcInfo(String[] DemographicInfo) {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// STEP 3: Switch to created Database
			String sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			// STEP 4: create and execute the prepared statement
			String query = "INSERT INTO demographics_tbl (first_name,last_name,age,birth_date)"
					+ "VALUES (?,?,?,?);";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, DemographicInfo[0].toString().equals("") ? null:DemographicInfo[0].toString());
			preparedStmt.setString(2, DemographicInfo[1].toString().equals("") ? null:DemographicInfo[1].toString());
			preparedStmt.setString(3, DemographicInfo[2].toString().equals("") ? null:DemographicInfo[2].toString());
			preparedStmt.setString(4, DemographicInfo[3].toString().equals("") ? null:DemographicInfo[3].toString());

			preparedStmt.execute();

			System.out.println("Inserted data successfully...");

			// STEP 5: Clean-up environment
			stmt.close();
			preparedStmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return;
	}

	public void getDemographicInfo(int id) {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// STEP 3: Switch to created Database
			String sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			// STEP 4: create and execute the prepared statement
			String query = "SELECT * FROM demographics_tbl WHERE patient_id = ?;";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, id);

			// STEP 5: execute the query, and get a java resultSet
			ResultSet resultSet = preparedStmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();

			System.out.println("Query successful...");

			System.out.println("\nPrinting Search Results");

			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(" , ");
					String columnValue = resultSet.getString(i);
					System.out
							.print(rsmd.getColumnName(i) + ": " + columnValue);
				}
				System.out.println(" ");
			}
			System.out.println("End of Search Results\n");

			// STEP 6: Clean-up environment
			stmt.close();
			preparedStmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return;
	}

	public void deleteDemographicInfo(int id) {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// STEP 3: Switch to created Database
			String sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			// STEP 4: create and execute the prepared statement
			String query = "DELETE FROM demographics_tbl WHERE patient_id = ?;";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, id);

			// STEP 5: execute the query, and get a java resultSet
			preparedStmt.executeUpdate();
		
			System.out.println("Query successful...");

			// STEP 6: Clean-up environment
			stmt.close();
			preparedStmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return;
	}

	public void demographicInfoSearch(String[] DemographicInfo) {

		Connection conn = null;
		Statement stmt = null;

		System.out.println(DemographicInfo[0].toString());

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3: Execute a query
			stmt = conn.createStatement();

			// STEP 4: Switch to created Database
			String sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			// STEP 5: create the prepared statement
			// NOTE: here we query ony if it matches on
			String query = "SELECT * FROM demographics_tbl WHERE "
					+ "(first_name = ? AND first_name IS NOT NULL) OR "
					+ "(last_name = ? AND last_name IS NOT NULL)  OR "
					+ "(age = ? AND age IS NOT NULL) OR"
					+ "(birth_date = ? AND birth_date IS NOT NULL) ;";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, DemographicInfo[0].toString().equals("") ? null:DemographicInfo[0].toString());
			preparedStmt.setString(2, DemographicInfo[1].toString().equals("") ? null:DemographicInfo[1].toString());
			preparedStmt.setString(3, DemographicInfo[2].toString().equals("") ? null:DemographicInfo[2].toString());
			preparedStmt.setString(4, DemographicInfo[3].toString().equals("") ? null:DemographicInfo[3].toString());

			// STEP 5: execute the query, and get a java resultSet
			ResultSet resultSet = preparedStmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();

			System.out.println("Query successful...");

			System.out.println("\nPrinting Search Results");

			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(" , ");
					String columnValue = resultSet.getString(i);
					System.out
							.print(rsmd.getColumnName(i) + ": " + columnValue);
				}
				System.out.println(" ");
			}
			System.out.println("End of Search Results\n");

			// STEP 6: Clean-up environment
			stmt.close();
			preparedStmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

	}

	public void getAllDemographicInfo() {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3: Execute a query
			stmt = conn.createStatement();

			// STEP 4: Switch to created Database
			String sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			// STEP 5: create and execute the prepared statement
			String query = "SELECT * FROM demographics_tbl ;";

			// execute the query, and get a java resultSet
			ResultSet resultSet = stmt.executeQuery(query);
			ResultSetMetaData rsmd = resultSet.getMetaData();

			System.out.println("Query successful...");

			System.out.println("\nPrinting Search Results");

			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(" , ");
					String columnValue = resultSet.getString(i);
					System.out
							.print(rsmd.getColumnName(i) + ": " + columnValue);
				}
				System.out.println(" ");
			}
			System.out.println("End of Search Results\n");

			// STEP 6: Clean-up environment
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

	}
	
	public void createDocumentReference(String[] Documentinfo) {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// STEP 3: Switch to created Database
			String sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			// STEP 4: create and execute the prepared statement
			String query = "INSERT INTO documents_tbl (document_type, document_issuer,document_id,patient_id)"
					+ " VALUES (?,?,?,?);";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, Documentinfo[0].toString().equals("") ? null:Documentinfo[0].toString());
			preparedStmt.setString(2, Documentinfo[1].toString().equals("") ? null:Documentinfo[1].toString());
			preparedStmt.setString(3, Documentinfo[2].toString().equals("") ? null:Documentinfo[2].toString());
			preparedStmt.setString(4, Documentinfo[3].toString().equals("") ? null:Documentinfo[3].toString());

			preparedStmt.executeUpdate();

			System.out.println("Inserted data successfully...");

			// STEP 5: Clean-up environment
			stmt.close();
			preparedStmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return;
	}
	
	public void getDocumentReference(String issuer, int id) {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// STEP 3: Switch to created Database
			String sql = "USE DEVdb";
			stmt.executeUpdate(sql);

			// STEP 4: create and execute the prepared statement
			String query = "SELECT * FROM documents_tbl WHERE document_id = ? AND "
					+ "document_issuer = ?;";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, issuer);

			// STEP 5: execute the query, and get a java resultSet
			ResultSet resultSet = preparedStmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();

			System.out.println("Query successful...");

			System.out.println("\nPrinting Search Results");

			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(" , ");
					String columnValue = resultSet.getString(i);
					System.out
							.print(rsmd.getColumnName(i) + ": " + columnValue);
				}
				System.out.println(" ");
			}
			System.out.println("End of Search Results\n");

			// STEP 6: Clean-up environment
			stmt.close();
			preparedStmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return;
	}

}// end dbHandler