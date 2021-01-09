package Tables;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.activation.DataSource;

public class Counties {

    SQLServerDataSource dataSource;

    public Counties(SQLServerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * This function loads all data from Counties
     * @return ArrayList with formatted data
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> getAll() throws SQLServerException, SQLException {
        ArrayList<String> data = new ArrayList<String>();
        try (Connection c = dataSource.getConnection()) {
            String sql = "SELECT C.county_id, C.county_name FROM Counties C";
            data.add(String.format("%-30s", "Okres"));
            data.add(String.format("%-30s", " ").replace(' ', '-'));
            try (PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    data.add(String.format("%-30s", r.getString("county_name")));
                }
                return data;
            }
        }
    }

    /**
     * This function loads specific row from Counties
     * @param id - what County should be printed
     * @return ArrayList with formated result
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<String> getRowById(int id) throws SQLServerException, SQLException {
        ArrayList<String> row = new ArrayList<String>();

        try (Connection c = dataSource.getConnection()) {
            String sql = "SELECT C.county_id, C.county_name FROM Counties C WHERE C.county_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id + "");
            row.add(String.format("%-10s %-30s", "ID", "Okres"));
            row.add(String.format("%-40s", " ").replace(' ', '-'));

            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    row.add(String.format("%-10s %-30s", r.getString("county_id"), r.getString("county_name")));
                }
                return row;
            }
        }
    }

    /**
     * This function inserts new county into Counties
     * @param county_name - name of station
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public boolean insertTo(String county_name) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "INSERT INTO Counties(county_name) VALUES(?)";
            PreparedStatement p = c.prepareStatement(sql);

            p.setString(1, county_name);
            
            int n = p.executeUpdate();

            if (n > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    /**
     * This function deletes specific row from Counties
     * @param id - what row should be deleted
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public boolean deleteFrom(int id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "DELETE FROM Counties WHERE county_id = ?";
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
     * This function updates specific row in Counties
     * @param col - what column
     * @param val - new value
     * @param id - id of county
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public boolean updateRow(String col, String val,String id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "UPDATE Counties SET "+col+" = ? WHERE county_id = ?";
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
