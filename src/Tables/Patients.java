package Tables;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.activation.DataSource;

public class Patients {
    SQLServerDataSource dataSource;
    
    public Patients(SQLServerDataSource dataSource) {
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
            String sql = "SELECT P.name,P.surname,P.email,P.phone,P.p_id FROM Patients P";
            data.add(String.format("%-20s %-20s %-20s %-60s", "Jméno", "Příjmení", "Telefon","Email"));
            data.add(String.format("%-120s"," ").replace(' ','-'));
            try(PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet r = p.executeQuery();
                while(r.next()) {
                    data.add(String.format("%-20s %-20s %-20s %-60s",r.getString("name"),r.getString("surname"),r.getString("phone"),r.getString("email")));
                }
                return data;
            }
        }
    }
    
    public ArrayList<String> getRowById(int id) throws SQLServerException, SQLException {
        ArrayList<String> row = new ArrayList<String>();
        
        try( Connection c = dataSource.getConnection() ) {
            String sql = "SELECT P.name,P.surname,P.email,P.phone,P.p_id FROM Patients P WHERE P.p_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id+"");
            row.add(String.format("%-20s %-20s %-20s %-60s", "Jméno", "Příjmení", "Telefon","Email"));
            row.add(String.format("%-120s"," ").replace(' ','-'));
            
            try(ResultSet r = p.executeQuery()) {
                while(r.next()) {
                    row.add(String.format("%-20s %-20s %-20s %-60s",r.getString("name"),r.getString("surname"),r.getString("phone"),r.getString("email")));
                }
                return row;
            }
        }
    }
    
    public boolean insertTo(String name, String surname, String email, String phone) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "INSERT INTO Patients(name,surname,email,phone) VALUES(?,?,?,?)";
            PreparedStatement p = c.prepareStatement(sql);
            
            p.setString(1, name);
            p.setString(2, surname);
            p.setString(3, email);
            p.setString(4, phone);
            
            int n = p.executeUpdate();
            
            if(n > 0) {
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean deleteFrom(int id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "DELETE FROM Patients WHERE p_id = ?";
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
    
    public boolean updateRow(String col, String val,String id) throws SQLServerException, SQLException {
        try (Connection c = dataSource.getConnection()) {
            String sql = "UPDATE Patients SET "+col+" = ? WHERE p_id = ?";
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
