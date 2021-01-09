package Tables;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.activation.DataSource;

public class Tests {
    SQLServerDataSource dataSource;
    
    public Tests(SQLServerDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * This method returns every row in Table Patients
     * @return ArrayList with each row in Table
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> getAll() throws SQLServerException, SQLException {
        ArrayList<String> data = new ArrayList<String>();
        try( Connection c = dataSource.getConnection() ) {
            String sql = "SELECT T.test_id,T.test_name FROM Tests T";
            data.add(String.format("%-20s %-20s", "ID Testu", "Název testu"));
            data.add(String.format("%-40s"," ").replace(' ','-'));
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                while(r.next()) {
                    data.add(String.format("%-20s %-20s",r.getString("test_id"),r.getString("test_name")));
                }
                return data;
            }
        }
    }
    
    public ArrayList<String> getRowById(int id) throws SQLServerException, SQLException {
        ArrayList<String> row = new ArrayList<String>();
        
        try( Connection c = dataSource.getConnection() ) {
            String sql = "SELECT T.test_id, T.test_name FROM Tests T WHERE T.test_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id+"");
            row.add(String.format("%-20s %-20s", "ID testu", "Typ testu"));
            row.add(String.format("%-40s"," ").replace(' ','-'));
            
            try(ResultSet r = p.executeQuery()) {
                while(r.next()) {
                    row.add(String.format("%-20s %-20s",r.getString("test_id"),r.getString("test_name")));
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
    public boolean insertTo(String test_name) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "INSERT INTO Tests(test_name) VALUES(?)";
            PreparedStatement p = c.prepareStatement(sql);
            
            p.setString(1, test_name);
            
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
            String sql = "DELETE FROM Tests WHERE test_id = ?";
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
    public boolean updateRow(String col, String val,String id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "UPDATE Tests SET "+col+" = ? WHERE test_id = ?";
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
