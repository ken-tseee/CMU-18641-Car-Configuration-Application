package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;
import util.SQL;

public class Database implements DatabaseInterface {
	private SQL cmd = new SQL();
	private final String fileName = "data/db.properties";
	private String db;
	private String url;
	private String username;
	private String password;
	
	public Database() {
		try {
			FileInputStream in = new FileInputStream(fileName);
			Properties prop = new Properties();
			prop.load(in);
			
			System.setProperty("jdbc.drivers", prop.getProperty("jdbc.drivers"));
			url = prop.getProperty("jdbc.url");
			db = prop.getProperty("jdbc.db");
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialize the database
	 */
	public void initialize() {
		createDB();
		createTables();
	}
	/**
	 * Get connection to the database.
	 */
	@Override
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url + "/" + db, username, password);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * Create database.
	 */
	private void createDB() {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			stmt.execute(cmd.createDB());
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create tables in the database.
	 */
	private void createTables() {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(cmd.createAutomobilesTable());
			stmt.execute(cmd.createOptionSetsTable());
			stmt.execute(cmd.createOptionsTable());
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Insert the records of an Automobile into its table.
	 */
	@Override
	public void insertAutomobile(Automobile auto) {
		String modelName = auto.getModel();
		int id = getAutomobileID(modelName);
		if(id != -1)
			return;
		float basePrice = auto.getBasePrice();
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.insertAutomobile());
			pstmt.setString(1, modelName);
			pstmt.setFloat(2, basePrice);
			pstmt.executeUpdate();
			
			ArrayList<String> optionSetList = auto.getOptionSetList();
			int len = optionSetList.size();
			for (int i = 0; i < len; ++i) {
				String setName = optionSetList.get(i);
				insertOptionSet(modelName, setName);
				ArrayList<String> optionList = auto.getOptionList(i);
				ArrayList<Float> optionPriceList = auto.getOptionPriceList(i);
				int len2 = optionList.size();
				for (int j = 0; j < len2; ++j) {
					insertOption(modelName, setName, optionList.get(j), optionPriceList.get(j));
				}
			}
	        conn.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Insert an option set into its table. 
	 */
	@Override
	public void insertOptionSet(String modelName, String setName) {
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.insertOptionSet());
			pstmt.setString(1, setName);
			pstmt.setString(2, modelName);
			pstmt.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Insert an option into its table.
	 */
	@Override
	public void insertOption(String modelName, String setName, String optionName, float price) {
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.insertOption());
			pstmt.setString(1, optionName);
			pstmt.setFloat(2, price);
			pstmt.setString(3, modelName);
			pstmt.setString(4, setName);
			pstmt.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Get the id of the Automobile record according to its model name. 
	 * @param modelName
	 * @return
	 */
	private int getAutomobileID(String modelName) {
		int id = -1;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.getAutomobileID());
			pstmt.setString(1, modelName);
			pstmt.executeQuery();
			
			ResultSet set = pstmt.getResultSet();
			if (set.next())
				id = set.getInt(1);
	        conn.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * Get an instance of an Automobile from the database according to its model name.
	 */
	@Override
	public Automobile getAutomobile(String modelName) {
		Automobile auto = new Automobile(modelName, 0);
		int id = getAutomobileID(modelName);
		if(id == -1)
			return null;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.getAutomobile());
			pstmt.setInt(1, id);
			ResultSet set = pstmt.executeQuery();
			if(set.next()) {
				auto.setModel(set.getString(1));
				auto.setBasePrice(set.getFloat(2));
			}
			ArrayList<String> optionSetList = getOptionSetList(id);
			for(String optionSetName : optionSetList) {
				auto.setOptionSet(optionSetName);
				ArrayList<String> optionList = getOptionList(id, optionSetName);
				for(String optionName : optionList) {
					float price = getOptionPrice(id, optionSetName, optionName);
					auto.setOption(optionSetName, optionName, price);
				}
			}
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return auto;
	}
	/**
	 * Get the list of the option sets according to the id of the Automobile.
	 */
	@Override
	public ArrayList<String> getOptionSetList(int autoID) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.getOptionSets());
			pstmt.setInt(1, autoID);
			ResultSet set = pstmt.executeQuery();
			while(set.next()) {
				result.add(set.getString(1));
			}
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Get the list of the options according to the id of the Automobile and the name
	 * of its option set.
	 */
	@Override
	public ArrayList<String> getOptionList(int autoID, String setName) {
		ArrayList<String> result = new ArrayList<String>(); 
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.getOptions());
			pstmt.setInt(1, autoID);
			pstmt.setString(2, setName);
			ResultSet set = pstmt.executeQuery();
			while(set.next()) {
				result.add(set.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Get the list of the prices of the options according to the id of the Automobile, 
	 * the name of its option set and the option name.
	 */
	@Override
	public float getOptionPrice(int autoID, String setName, String optionName) {
		float price = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.getOptionPrice());
			pstmt.setInt(1, autoID);
			pstmt.setString(2, setName);
			pstmt.setString(3, optionName);
			ResultSet set = pstmt.executeQuery();
			if(set.next()) {
				price = set.getFloat(1);
			}
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
	/**
	 * Delete the records of an Automobile according to its model name.
	 */
	@Override
	public void deleteAutomobile(String modelName) {
		int id = getAutomobileID(modelName);
		if(id == -1)
			return;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(cmd.deleteAutomobile());
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Update an instance of an Automobile.
	 */
	@Override
	public void updateAutomobile(String modelName, Automobile auto) {
		deleteAutomobile(modelName);
		insertAutomobile(auto);
	}
}
