package dbs_semestral;
import Tables.Counties;
import Tables.Patients;
import Tables.Results;
import Tables.Stations;
import Tables.Tests;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * this class has access to every class that represents each table.
 * It also has methods that return every row in each table, also row defined by ID
 * Every special select is realised here (1. -6.)
 * @author Tomáš Malčík. Khai Ly, Martin Koňák
 */

public class DatabaseManager {
    private SQLServerDataSource dataSource;
    private Patients p;
    private Tests t;
    private Results r;
    private Stations s;
    private Counties c;
    public DatabaseManager() throws SQLException {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(Config.serverName);
        dataSource.setPortNumber(Config.port);
        dataSource.setDatabaseName(Config.databaseName);
        dataSource.setUser(Config.username);
        dataSource.setPassword(Config.password);
        
        //create instance of each table
        p = new Patients(dataSource);
        t = new Tests(dataSource);
        r = new Results(dataSource);
        c = new Counties(dataSource);
        s = new Stations(dataSource);
    }
    
    /**
     * This method calls inner method of Patiens, that returns every row in Table
     * @return ArrayList with results
     * @throws SQLException 
     */
    public ArrayList<String> getAllPatients() throws SQLException {
        return p.getAll();
    }

    /**
     * This method calls inner method of Patiens, that returns row specified by id
     * @return ArrayList with result
     * @throws SQLException 
     */    
    public ArrayList<String> getPatientById(int id) throws SQLException {
        return p.getRowById(id);
    }
    
    /**
     * This method calls inner method of Patiens, that inserts new row into table
     * @return true if insertion was successful, false otherwise
     * @throws SQLException 
     */    
    public boolean insertToPatients(String name, String surname, String email, String phone) throws SQLException {
        return p.insertTo(name, surname, email, phone);
    }

    /**
     * This method calls inner method of Patiens, that deletes specific row
     * @return true if deleted, false otherwise
     * @throws SQLException 
     */    
    public boolean deleteFromPatients(int id) throws SQLException {
        return p.deleteFrom(id);
    }
    /**
     * This method calls inner method of Patiens, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean updatePatients(String col, String val, String id) throws SQLException {
        return p.updateRow(col, val, id);
    }
    
    /**
     * This method calls inner method of Results, that selects every row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */  
    public ArrayList<String> getAllResults() throws SQLException {
        return r.getAll();
    }
    /**
     * This method calls inner method of Results, that returns row specified by id
     * @return ArrayList with result
     * @throws SQLException 
     */
    public ArrayList<String> getResultById(int id) throws SQLException {
        return r.getRowById(id);
    }
    /**
     * This method calls inner method of Results, that inserts new row into table
     * @return true if insertion was successful, false otherwise
     * @throws SQLException 
     */    
    public boolean insertToResults(String patient_id, String station_id, String test_id) throws SQLException {
        return r.insertTo(patient_id, station_id, test_id);
    }
    /**
     * This method calls inner method of Results, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean deleteFromResults(int id) throws SQLException {
        return r.deleteFrom(id);
    }
    /**
     * This method calls inner method of Results, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean updateResults(String col, String val, String id) throws SQLException {
        return r.updateRow(col, val, id);
    }

    /**
     * This method calls inner method of Tests, that selects every row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public ArrayList<String> getAllTests() throws SQLException {
        return t.getAll();
    }
    /**
     * This method calls inner method of Tests, that returns row specified by id
     * @return ArrayList with result
     * @throws SQLException 
     */
    public ArrayList<String> getTestById(int id) throws SQLException {
        return t.getRowById(id);
    }
    /**
     * This method calls inner method of Tests, that inserts new row into table
     * @return true if insertion was successful, false otherwise
     * @throws SQLException 
     */    
    public boolean insertToTests(String test_name) throws SQLException {
        return t.insertTo(test_name);
    }
    /**
     * This method calls inner method of Tests, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean deleteFromTests(int id) throws SQLException {
        return t.deleteFrom(id);
    }
    /**
     * This method calls inner method of Tests, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean updateTests(String col, String val, String id) throws SQLException {
        return t.updateRow(col, val, id);
    }

     /**
     * This method calls inner method of Tests, that selects every row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */   
    public ArrayList<String> getAllStations() throws SQLException {
        return s.getAll();
    }
    /**
     * This method calls inner method of Stations, that returns row specified by id
     * @return ArrayList with result
     * @throws SQLException 
     */
    public ArrayList<String> getStationById(int id) throws SQLException {
        return s.getRowById(id);
    }
    /**
     * This method calls inner method of Stations, that inserts new row into table
     * @return true if insertion was successful, false otherwise
     * @throws SQLException 
     */    
    public boolean insertToStations(String st_name, String st_county) throws SQLException {
        return s.insertTo(st_name, st_county);
    }
    /**
     * This method calls inner method of Stations, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean deleteFromStations(int id) throws SQLException {
        return s.deleteFrom(id);
    }
    /**
     * This method calls inner method of Stations, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean updateStations(String col, String val, String id) throws SQLException {
        return s.updateRow(col, val, id);
    }
    
    /**
     * This method calls inner method of Counties, that selects every row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public ArrayList<String> getAllCounties() throws SQLException {
        return c.getAll();
    }
    /**
     * This method calls inner method of Counties, that returns row specified by id
     * @return ArrayList with result
     * @throws SQLException 
     */
    public ArrayList<String> getCountieById(int id) throws SQLException {
        return c.getRowById(id);
    }
    /**
     * This method calls inner method of Counties, that inserts new row into table
     * @return true if insertion was successful, false otherwise
     * @throws SQLException 
     */    
    public boolean insertToCounties(String county_name) throws SQLException {
        return c.insertTo(county_name);
    }
    /**
     * This method calls inner method of Counties, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */
    public boolean deleteFromCounties(int id) throws SQLException {
        return c.deleteFrom(id);
    }
    /**
     * This method calls inner method of Counties, that updates specific row in table
     * @return true if updated, false otherwise
     * @throws SQLException 
     */    
    public boolean updateCounties(String col, String val, String id) throws SQLException {
        return c.updateRow(col,val,id);
    }
    
    /**
     * This function selects station name and how many tests were conducted there
     * @return ArrayList with already formatted data
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> special1() throws SQLServerException, SQLException {
        ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
        String sql = "SELECT S.st_name, pocetTestu = (SELECT COUNT(R.station_id)" +
"                                  FROM Results R" +
"                                  WHERE R.station_id = S.st_id)" +
"                    FROM Stations S ORDER BY pocetTestu DESC";
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                data.add(String.format("%-50s %-20s", "Název odběrového místa", "počet testů"));
                data.add(String.format("%-70s"," ").replace(' ','-'));
                
                while(r.next()) {
                    data.add(String.format("%-50s %-20s", r.getString("st_name"),r.getString("pocetTestu")));
                }
                return data;
            }
        }
    }
    
    /**
     * This function selects name,surname,email,phone and number of tests from patients that were tested more than once
     * @return ArrayList with already formatted data
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> special2() throws SQLServerException, SQLException {
         ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
        String sql = "  SELECT P.[name],P.surname,P.email,P.phone, X.pocet FROM Patients P,\n" +
"  (SELECT R.patient_id, COUNT(R.patient_id) AS pocet FROM Results R GROUP BY R.patient_id HAVING COUNT(R.patient_id) > 1) X\n" +
"  WHERE P.p_id = X.patient_id";
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                data.add(String.format("%-20s %-20s %-60s %-20s %-20s", "Jméno", "Příjmení","Email","Telefon","počet testů"));
                data.add(String.format("%-140s"," ").replace(' ','-'));
                
                while(r.next()) {
                    data.add(String.format("%-20s %-20s %-60s %-20s %-20s", r.getString("name"),r.getString("surname"),r.getString("email"),r.getString("phone"),r.getString("pocet")));
                }
                return data;
            }
        }
    }
    
    /**
     * This function selects name,surname,email,phone from patients, that were not tested yet.
     * @return ArrayList with already formatted data
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> special3() throws SQLServerException, SQLException {
         ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
        String sql = "SELECT P.[name],P.surname, P.email,P.phone FROM Patients P WHERE P.p_id NOT IN (SELECT R.patient_id FROM Results R)";
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                data.add(String.format("%-20s %-20s %-60s %-20s", "Jméno", "Příjmení","Email","Telefon"));
                data.add(String.format("%-120s"," ").replace(' ','-'));
                
                while(r.next()) {
                    data.add(String.format("%-20s %-20s %-60s %-20s", r.getString("name"),r.getString("surname"),r.getString("email"),r.getString("phone")));
                }
                return data;
            }
        }        
    }
    
    /**
     * This function county name and station name grouped by county
     * @return ArrayList with already formatted data
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> special4() throws SQLServerException, SQLException {
         ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
        String sql = "SELECT C.county_name,S.st_name FROM Stations S, Counties C GROUP BY C.county_name, S.st_name";
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                data.add(String.format("%-20s %-20s", "Kraj", "Stanice"));
                data.add(String.format("%-120s"," ").replace(' ','-'));
                
                while(r.next()) {
                    data.add(String.format("%-20s %-20s", r.getString("county_name"),r.getString("st_name")));
                }
                return data;
            }
        }        
    }
    
    /**
     * This function selects name,surname,email,phone from patients that were tested at least once
     * @return ArrayList with already formatted data
     * @throws SQLException 
     */
    public ArrayList<String> special5() throws SQLException {
         ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
        String sql = "  SELECT P.[name],P.surname,P.email,P.phone FROM Patients P, (SELECT P.p_id FROM Patients P\n" +
"									INTERSECT\n" +
"									SELECT R.patient_id FROM Results R ) X\n" +
"  WHERE X.p_id = P.p_id";
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                data.add(String.format("%-20s %-20s %-60s %-20s", "Jméno", "Příjmení", "Email", "Telefon"));
                data.add(String.format("%-120s"," ").replace(' ','-'));
                
                while(r.next()) {
                    data.add(String.format("%-20s %-20s %-60s %-20s", r.getString("name"),r.getString("surname"),r.getString("email"),r.getString("phone")));
                }
                return data;
            }
        }          
    }
    
    /**
     * This function resturns test name and how many of them were already conducted.
     * @return ArrayList with already formatted data
     * @throws SQLException 
     */
    public ArrayList<String> special6() throws SQLException {
         ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
        String sql = "  SELECT T.test_id,T.test_name,COUNT(R.test_id) AS pocet FROM Tests T "
                     + "LEFT JOIN Results R ON R.test_id = T.test_id "
                     + "WHERE R.status = 'Pozitivní' "
                     + "GROUP BY R.test_id,T.test_name,T.test_id";
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                data.add(String.format("%-5s %-20s %-20s", "ID testu", "Název testu", "počet"));
                data.add(String.format("%-45s"," ").replace(' ','-'));
                
                while(r.next()) {
                    data.add(String.format("%-5s %-20s %-20s", r.getString("test_id"),r.getString("test_name"),r.getString("pocet")));
                }
                return data;
            }
        }         
    }

        
}
