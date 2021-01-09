package Tables;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.activation.DataSource;

public class Results {
    SQLServerDataSource dataSource;
    
    public Results(SQLServerDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * This method returns every row in Table Results
     * @return ArrayList with each row in Table
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> getAll() throws SQLServerException, SQLException {
        ArrayList<String> data = new ArrayList<String>();
        try( Connection c = dataSource.getConnection() ) {
            String sql = "SELECT R.id_result, R.patient_id, R.station_id, R.test_id FROM Results R";
            data.add(String.format("%-20s %-20s %-20s %-20s", "TD výsledku", "ID pacienta", "ID stanice","ID testu"));
            data.add(String.format("%-80s"," ").replace(' ','-'));
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                while(r.next()) {
                    data.add(String.format("%-20s %-20s %-20s %-20s",r.getString("id_result"),r.getString("patient_id"),r.getString("station_id"),r.getString("test_id")));
                }
                return data;
            }
        }
    }

    /**
     * This method returns row specified by ID
     * @return ArrayList with each row in Table
     * @throws SQLServerException
     * @throws SQLException 
     */    
    public ArrayList<String> getRowById(int id) throws SQLServerException, SQLException {
        ArrayList<String> row = new ArrayList<String>();
        
        try( Connection c = dataSource.getConnection() ) {
            String sql = "SELECT R.id_result, R.patient_id, R.station_id, R.test_id FROM Results R WHERE R.id_result = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id+"");
            row.add(String.format("%-20s %-20s %-20s %-20s", "TD výsledku", "ID pacienta", "ID stanice","ID testu"));
            row.add(String.format("%-80s"," ").replace(' ','-'));
            
            try(ResultSet r = p.executeQuery()) {
                while(r.next()) {
                    row.add(String.format("%-20s %-20s %-20s %-20s",r.getString("id_result"),r.getString("patient_id"),r.getString("station_id"),r.getString("test_id")));
                }
                return row;
            }
        }
    }
    
    /**
     * This method inserts new row into the table
     * @return true if inserted, false otherwise
     * @throws SQLServerException
     * @throws SQLException 
     */
    public boolean insertTo(String patient_id, String station_id, String test_id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "INSERT INTO Results(patient_id,station_id,test_id) VALUES(?,?,?)";
            PreparedStatement p = c.prepareStatement(sql);
            
            p.setString(1, patient_id);
            p.setString(2, station_id);
            p.setString(3, test_id);
            
            int n = p.executeUpdate();
            
            if(n > 0) {
                return true;
            }else {
                return false;
            }
        }
    }
    /**
     * This method deletes row specified by ID
     * @return true if deleted, false otherwise
     * @throws SQLServerException
     * @throws SQLException 
     */
    public boolean deleteFrom(int id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "DELETE FROM Results WHERE id_result = ?";
            PreparedStatement p = c.prepareStatement(sql);
            
            p.setString(1, ""+id);
            
            int n = p.executeUpdate();
            
            if(n > 0) {
                return true;
            }else {
                return false;
            }
        }        
    }
    /**
     * This method updates one row specified by ID. Updates column to a new value
     * @return true if updated, false otherwise
     * @throws SQLServerException
     * @throws SQLException 
     */    
    public boolean updateRow(String col, String val, String id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "UPDATE Results SET "+col+" = ? WHERE id_result = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, val);
            p.setString(2, id);
            
            int n = p.executeUpdate();
            if(n > 0) {
                return true;
            }else {
                return false;
            }
        }
         
    }
}
