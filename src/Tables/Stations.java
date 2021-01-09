package Tables;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.activation.DataSource;

public class Stations {

    SQLServerDataSource dataSource;

    public Stations(SQLServerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<String> getAll() throws SQLServerException, SQLException {
        ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
            String sql = "SELECT S.st_id, S.st_name, S.st_county FROM Stations S";
            data.add(String.format("%-50s %-30s", "Stanice", "Okres"));
            data.add(String.format("%-80s", " ").replace(' ', '-'));
            try (PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    data.add(String.format("%-50s %-30s", r.getString("st_name"), r.getString("st_county")));
                }
                return data;
            }
        }
    }

    /**
     * This method returns row specified by ID
     * @return ArrayList with all data from the row.
     * @throws SQLServerException
     * @throws SQLException 
     */  
    public ArrayList<String> getRowById(int id) throws SQLServerException, SQLException {
        ArrayList<String> row = new ArrayList<String>();

        try (Connection c = dataSource.getConnection()) {
            String sql = "SELECT S.st_id, S.st_name,S.st_county FROM Stations S WHERE S.st_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id + "");
            row.add(String.format("%-50s %-30s", "Stanice", "Okres"));
            row.add(String.format("%-80s", " ").replace(' ', '-'));

            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    row.add(String.format("%-50s %-30s", r.getString("st_name"), r.getString("st_county")));
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
    public boolean insertTo(String st_name, String st_county) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "INSERT INTO Stations(st_name,st_county) VALUES(?,?)";
            PreparedStatement p = c.prepareStatement(sql);

            p.setString(1, st_name);
            p.setString(2, st_county);

            int n = p.executeUpdate();

            if (n > 0) {
                return true;
            } else {
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
            String sql = "DELETE FROM Stations WHERE st_id = ?";
            PreparedStatement p = c.prepareStatement(sql);

            p.setString(1, "" + id);

            int n = p.executeUpdate();

            if (n > 0) {
                return true;
            } else {
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
            String sql = "UPDATE Stations SET "+col+" = ? WHERE st_id = ?";
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
