
package printFunctions;

import dbs_semestral.DatabaseManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public  class Functions {    
    DatabaseManager d;
    Scanner sc;

    public Functions() {
        try {
            d = new DatabaseManager();
            sc = new Scanner(System.in);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se připojit k db");
        }   
    }
    
    /**
     * This function prints out already formated data from ArrayList, that is given by another function
     */
    public void printAllPatientsRecords() {
        try {
            ArrayList<String> data = new ArrayList<String>();
            data = d.getAllPatients();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" +e);
        }
            System.out.println("");
    }

    /**
     * This function loads ID of County, then sends it to another function that returns ArrayList with a formatted string ready to be printed out.
     */    
    public void printPatientRowById() {
        ArrayList<String> data = new ArrayList<String>();
        System.out.println("Zadej ID uživatele");
        int s = sc.nextInt();
        try {
            data = d.getPatientById(s);
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" +e);
        }
        System.out.println("");
    }
    
    /**
     * This function loads data from user, then sends them to another function that inserts them into Patients
     * Prints out whether the row was added or not and why.
     */
    public void insertNewPatientRow() {
        String name,surname,email,phone;
        System.out.println("Vložení nového pacienta");
        System.out.println("Zadej: jméno, příjmení, email, telefon");
        sc.nextLine();
        name = sc.nextLine();
        surname = sc.nextLine();
        email = sc.nextLine();
        phone = sc.nextLine();
        try {
            if(d.insertToPatients(name, surname, email, phone)) {
                System.out.println("Nový pacient byl přidán");
            }
        }catch(SQLException e) {
            System.out.println("Nepodařilo se přidat uživatele "+e);
        }
        System.out.println("");
    }

    /**
     * This function loads ID of row that is going to be deleted, then sends it to another function that performs the delete.
     * Prints out whether or not was the delete successful
     */
    public void deletePatientRow() {
        System.out.println("Smazání pacienta");
        System.out.println("Zadej: ID pacienta, který bude smazán");
        int n = sc.nextInt();
        try {
            if(d.deleteFromPatients(n)) {
                System.out.println("Pacient byl úspěšně smazán");
            }
        }catch(SQLException e) {
            System.out.println("Pacienta se nepodařilo smazat. \n"+e);
        }
        System.out.println("");
    }

    /**
     * This function loads data from user (ID, what column and what value). This data is then sent to another function that updates the table
     * Prints out whether was the update succesfull or not and why
     */    
    public void updatePatientRow() {
        String id,col,val = "";
        System.out.println("Změna dat Pacienta");
        System.out.println("Zadej: ID pacienta, sloupeček, novou hodnotu");
        sc.nextLine();
        id = sc.nextLine();
        col = sc.nextLine();
        val = sc.nextLine();
        
        try {
            if( d.updatePatients(col, val, id) ) {
                System.out.println("Pacientova data byla změněna");
            }
        }catch(SQLException e) {
            System.out.println("Pacientova data nebyla změněna \n" +e);
        }
    }
 
        
    /**
     * This function prints out already formated data from ArrayList, that is given by another function
     */
    public void printAllResultsRecords() {
        try {
            ArrayList<String> data = new ArrayList<String>();
            data = d.getAllResults();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" +e);
        }
            System.out.println("");
    }

    /**
     * This function loads ID of Result, then sends it to another function that returns ArrayList with a formatted string ready to be printed out.
     */    
    public void printResultRowById() {
        ArrayList<String> data = new ArrayList<String>();
        System.out.println("Zadej ID výsledku");
        int s = sc.nextInt();
        try {
            data = d.getResultById(s);
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" +e);
        }
        System.out.println("");
    }

    /**
     * This function loads data from user, then sends them to another function that inserts them into Results
     * Prints out whether the row was added or not and why.
     */        
    public void insertNewResultRow() {
        String patient_id, station_id, test_id;
        System.out.println("Vložení nového výsledku");
        System.out.println("Zadej: ID pacienta, ID stanice, ID testu");
        sc.nextLine();
        patient_id = sc.nextLine();
        station_id = sc.nextLine();
        test_id = sc.nextLine();
        try {
            if(d.insertToResults(patient_id, station_id, test_id)) {
                System.out.println("Nový výsledek byl přidán");
            }
        }catch(SQLException e) {
            System.out.println("Nepodařilo se přidat výsledek "+e);
        }
        System.out.println("");
    }

    /**
     * This function loads ID of row that is going to be deleted, then sends it to another function that performs the delete.
     * Prints out whether or not was the delete successful
     */    
    public void deleteResultRow() {
        System.out.println("Smazání výsledku");
        System.out.println("Zadej: ID výsledku, který bude smazán");
        int n = sc.nextInt();
        try {
            if(d.deleteFromResults(n)) {
                System.out.println("Výsledek byl úspěšně smazán");
            }
        }catch(SQLException e) {
            System.out.println("Výsledek se nepodařilo smazat. \n"+e);
        }
        System.out.println("");
    }
    
    /**
     * This function loads data from user (ID, what column and what value). This data is then sent to another function that updates the table
     * Prints out whether was the update succesfull or not and why
     */
    public void updateResultRow() {
        String id,col,val = "";
        System.out.println("Změna dat výsledku");
        System.out.println("Zadej: ID výsledku, sloupeček, novou hodnotu");
        id = sc.nextLine();
        col = sc.nextLine();
        val = sc.nextLine();
        
        try {
            if( d.updatePatients(col, val, id) ) {
                System.out.println("Data výsledku byla změněna");
            }
        }catch(SQLException e) {
            System.out.println("Data výsledku nebyla změněna \n" +e);
        }
    }
    
    /**
     * This function prints out already formated data from ArrayList, that is given by another function
     */    
    public void printAllTestsRecords() {
        try {
            ArrayList<String> data = new ArrayList<String>();
            data = d.getAllTests();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" +e);
        }
            System.out.println("");
    }

    /**
     * This function loads ID of Test, then sends it to another function that returns ArrayList with a formatted string ready to be printed out.
     */    
    public void printTestRowById() {
        ArrayList<String> data = new ArrayList<String>();
        System.out.println("Zadej ID výsledku");
        int s = sc.nextInt();
        try {
            data = d.getTestById(s);
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" +e);
        }
        System.out.println("");
    }

    /**
     * This function loads data from user, then sends them to another function that inserts them into Tests
     * Prints out whether the row was added or not and why.
     */    
    public void insertNewTestRow() {
        String test_name;
        System.out.println("Vložení nového testu");
        System.out.println("Zadej: Název testu");
        sc.nextLine();
        test_name = sc.nextLine();
        try {
            if(d.insertToTests(test_name)) {
                System.out.println("Nový test byl přidán");
            }
        }catch(SQLException e) {
            System.out.println("Nepodařilo se přidat nový test "+e);
        }
        System.out.println("");
    }

    /**
     * This function loads ID of row that is going to be deleted, then sends it to another function that performs the delete.
     * Prints out whether or not was the delete successful
     */    
    public void deleteTestRow() {
        System.out.println("Smazání testu");
        System.out.println("Zadej: ID testu, který bude smazán");
        int n = sc.nextInt();
        try {
            if(d.deleteFromTests(n)) {
                System.out.println("Test byl úspěšně smazán");
            }
        }catch(SQLException e) {
            System.out.println("Test se nepodařilo smazat. \n"+e);
        }
        System.out.println("");
    }
    
    /**
     * This function loads data from user (ID, what column and what value). This data is then sent to another function that updates the table
     * Prints out whether was the update succesfull or not and why
     */
    public void updateTestRow() {
        String id,col,val = "";
        System.out.println("Změna dat testu");
        System.out.println("Zadej: ID testu, sloupeček, novou hodnotu");
        id = sc.nextLine();
        col = sc.nextLine();
        val = sc.nextLine();
        
        try {
            if( d.updateTests(col, val, id) ) {
                System.out.println("Data testu byla změněna");
            }
        }catch(SQLException e) {
            System.out.println("Data testu nebyla změněna \n" +e);
        }
    }    

    /**
     * This function prints out already formated data from ArrayList, that is given by another function
     */    
    public void printAllStationsRecords() {
        try {
            ArrayList<String> data = new ArrayList<String>();
            data = d.getAllStations();
            printRecords(data);
        } catch (SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" + e);
        }
        System.out.println("");
    }

    /**
     * This function loads ID of Station, then sends it to another function that returns ArrayList with a formatted string ready to be printed out.
     */    
    public void printStationRowById() {
        ArrayList<String> data = new ArrayList<String>();
        System.out.println("Zadej ID stanice");
        int s = sc.nextInt();
        try {
            data = d.getStationById(s);
            printRecords(data);
        } catch (SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" + e);
        }
        System.out.println("");
    }

    /**
     * This function loads data from user, then sends them to another function that inserts them into Stations
     * Prints out whether the row was added or not and why.
     */    
    public void insertNewStationRow() {
        String st_name, st_county;
        System.out.println("Vložení nové stanice");
        System.out.println("Zadej: stanici, okres");
        sc.nextLine();
        st_name = sc.nextLine();
        st_county = sc.nextLine();
        try {
            if (d.insertToStations(st_name, st_county)) {
                System.out.println("Stanice byla přidána");
            }
        } catch (SQLException e) {
            System.out.println("Nepodařilo se přidat stanici " + e);
        }
        System.out.println("");
    }

    /**
     * This function loads ID of row that is going to be deleted, then sends it to another function that performs the delete.
     * Prints out whether or not was the delete successful
     */    
    public void deleteStationRow() {
        System.out.println("Smazání stanice");
        System.out.println("Zadej: ID stanice, která bude smazána");
        int n = sc.nextInt();
        try {
            if (d.deleteFromStations(n)) {
                System.out.println("Stanice byla smazána");
            }
        } catch (SQLException e) {
            System.out.println("Stanici se nepodařilo smazat. \n" + e);
        }
        System.out.println("");
    }
    
    /**
     * This function loads data from user (ID, what column and what value). This data is then sent to another function that updates the table
     * Prints out whether was the update succesfull or not and why
     */
    public void updateStationRow() {
        String id, col, val = "";
        System.out.println("Změna dat stanice");
        System.out.println("Zadej: ID stanice, sloupeček, novou hodnotu");
        
        id = sc.nextLine();
        col = sc.nextLine();
        val = sc.nextLine();
        try {
            if (d.updateStations(col, val, id)) {
                System.out.println("Data stanice byla změněna");
            }
        } catch (SQLException e) {
            System.out.println("Data stanice nebyla změněna \n" + e);
        }
    }

    /**
     * This function prints out already formated data from ArrayList, that is given by another function
     */
    public void printAllCountiesRecords() {
        try {
            ArrayList<String> data = new ArrayList<String>();
            data = d.getAllCounties();
            printRecords(data);
        } catch (SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" + e);
        }
        System.out.println("");
    }

    /**
     * This function loads ID of County, then sends it to another function that returns ArrayList with a formatted string ready to be printed out.
     */
    public void printCountieRowById() {
        ArrayList<String> data = new ArrayList<String>();
        System.out.println("Zadej ID okresu");
        int s = sc.nextInt();
        try {
            data = d.getCountieById(s);
            printRecords(data);
        } catch (SQLException e) {
            System.out.println("Nepodařilo se získat záznamy, error: \n" + e);
        }
        System.out.println("");
    }

    /**
     * This function loads data from user, then sends them to another function that inserts them into Counties
     * Prints out whether the row was added or not and why.
     */    
    public void insertNewCountieRow() {
        String county_name;
        System.out.println("Vložení nového okresu");
        System.out.println("Zadej: okres");
        sc.nextLine();
        county_name = sc.nextLine();
        try {
            if (d.insertToCounties(county_name)) {
                System.out.println("Okres byl přidán");
            }
        } catch (SQLException e) {
            System.out.println("Nepodařilo se přidat okres " + e);
        }
        System.out.println("");
    }

    /**
     * This function loads ID of row that is going to be deleted, then sends it to another function that performs the delete.
     * Prints out whether or not was the delete successful
     */    
    public void deleteCountieRow() {
        System.out.println("Smazání okresu");
        System.out.println("Zadej: ID okresu, krerý má být smazán");
        int n = sc.nextInt();
        try {
            if (d.deleteFromCounties(n)) {
                System.out.println("Okres byla smazána");
            }
        } catch (SQLException e) {
            System.out.println("Okres se nepodařilo smazat. \n" + e);
        }
        System.out.println("");
    }
    
    /**
     * This function loads data from user (ID, what column and what value). This data is then sent to another function that updates the table
     * Prints out whether was the update succesfull or not and why
     */
    public void updateCountieRow() {
        String id, col, val = "";
        System.out.println("Změna dat okresu");
        System.out.println("Zadej: ID okresu, sloupeček, novou hodnotu");

        id = sc.nextLine();
        col = sc.nextLine();
        val = sc.nextLine();
        
        try {
            if (d.updateCounties(col, val, id)) {
                System.out.println("Data okresu byla změněna");
            }
        } catch (SQLException e) {
            System.out.println("Data okresu nebyla změněna \n" + e);
        }
    }

    /**
     * This function is used for printing special data selection from database, that is formatted and added to ArrayList.
     * @throws SQLException 
     */
    public void special1() throws SQLException{
        ArrayList<String> data = new ArrayList<String>();
        try {
            data = d.special1();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat data \n"+e);
        }
        
    }
    /**
     * This function is used for printing special data selection from database, that is formatted and added to ArrayList.
     * @throws SQLException 
     */    
    public void special2() throws SQLException{
        ArrayList<String> data = new ArrayList<String>();
        try {
            data = d.special2();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat data \n"+e);
        }
        
    } 
    /**
     * This function is used for printing special data selection from database, that is formatted and added to ArrayList.
     * @throws SQLException 
     */    
    public void special3() throws SQLException{
        ArrayList<String> data = new ArrayList<String>();
        try {
            data = d.special3();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat data \n"+e);
        }
        
    }
    /**
     * This function is used for printing special data selection from database, that is formatted and added to ArrayList.
     * @throws SQLException 
     */    
    public void special4() throws SQLException{
        ArrayList<String> data = new ArrayList<String>();
        try {
            data = d.special4();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat data \n"+e);
        }
        
    }
    /**
     * This function is used for printing special data selection from database, that is formatted and added to ArrayList.
     * @throws SQLException 
     */    
    public void special5() throws SQLException{
        ArrayList<String> data = new ArrayList<String>();
        try {
            data = d.special5();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat data \n"+e);
        }
        
    }
    /**
     * This function is used for printing special data selection from database, that is formatted and added to ArrayList.
     * @throws SQLException 
     */
    public void special6() throws SQLException{
        ArrayList<String> data = new ArrayList<String>();
        try {
            data = d.special6();
            printRecords(data);
        }catch(SQLException e) {
            System.out.println("Nepodařilo se získat data \n"+e);
        }
        
    }
    /**
     * This function is used to print out everything from given ArrayList
     * @param r
     * @throws SQLException 
     */
    private static void printRecords(ArrayList<String> r) throws SQLException {
        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i));
        }
    }    
}
