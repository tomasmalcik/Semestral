package dbs_semestral;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import printFunctions.Functions;

/**
 * COVID-19 test tracking
 * ----------------------
 * This application allows users to select, insert, modify and delete data from 5 tables.
 * Patiens,Results,Tests,Counties,Stations.
 * 
 * @author Tomáš Malčík, Khai Ly Dinh, Martin Koňák
 */
public class DBS_Semestral {
    
    static Scanner sc = new Scanner(System.in);
    static Functions f;
    public static void main(String[] args) throws SQLException {
        
        f = new Functions();
        
        printMainMenu();
        int n;
        while((n = sc.nextInt()) > 0){
            switch(n) {
                case 1:
                    patientsMenu();
                    break;
                case 2:
                    resultsMenu();
                    break;
                case 3:
                    TestsMenu();
                    break;
                case 4:
                    StationsMenu();
                    break;
                case 5:
                    CountiesMenu();
                    break; 
                case 6:
                    specialMenu();
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
            printMainMenu();
        }
    }

    private static void patientsMenu() {
        int n;
        printSubMenu("Patients");
        while( (n = sc.nextInt()) > 0 ) {
            switch(n) {
                case 1:
                    f.printAllPatientsRecords();
                    break;
                case 2:
                    f.printPatientRowById();
                    break;
                case 3:
                    f.insertNewPatientRow();
                    break;
                case 4:
                    f.updatePatientRow();
                    break;
                case 5:
                    f.deletePatientRow();
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
            printSubMenu("Patients");
        }
    }
    
    private static void resultsMenu() {
        int n;
        printSubMenu("Results");
        while( (n = sc.nextInt()) > 0 ) {
            switch(n) {
                case 1:
                    f.printAllResultsRecords();
                    break;
                case 2:
                    f.printResultRowById();
                    break;
                case 3:
                    f.insertNewResultRow();
                    break;
                case 4:
                    f.updateResultRow();
                    break;
                case 5:
                    f.deleteResultRow();
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
            printSubMenu("Results");
        }
    }

    private static void StationsMenu() {
        int n;
        printSubMenu("Stations");
        while( (n = sc.nextInt()) > 0 ) {
            switch(n) {
                case 1:
                    f.printAllStationsRecords();
                    break;
                case 2:
                    f.printStationRowById();
                    break;
                case 3:
                    f.insertNewStationRow();
                    break;
                case 4:
                    f.updateStationRow();
                    break;
                case 5:
                    f.deleteStationRow();
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
            printSubMenu("Stations");
        }
    }
    
    private static void CountiesMenu() {
        int n;
        printSubMenu("Counties");
        while( (n = sc.nextInt()) > 0 ) {
            switch(n) {
                case 1:
                    f.printAllCountiesRecords();
                    break;
                case 2:
                    f.printCountieRowById();
                    break;
                case 3:
                    f.insertNewCountieRow();
                    break;
                case 4:
                    f.updateCountieRow();
                    break;
                case 5:
                    f.deleteCountieRow();
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
            printSubMenu("Counties");
        }
    }    
    
    private static void TestsMenu() {
        int n;
        printSubMenu("Tests");
        while( (n = sc.nextInt()) > 0 ) {
            switch(n) {
                case 1:
                    f.printAllTestsRecords();
                    break;
                case 2:
                    f.printTestRowById();
                    break;
                case 3:
                    f.insertNewTestRow();
                    break;
                case 4:
                    f.updateTestRow();
                    break;
                case 5:
                    f.deleteTestRow();
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
            printSubMenu("Tests");
        }
    }

    private static void specialMenu() throws SQLException {
        printSpecialMenu();
        int n;
        
        while( (n = sc.nextInt()) > 0 ) {
            switch(n) {
                case 1:
                    f.special1();
                    break;
                case 2:
                    f.special2();
                    break;
                case 3:
                    f.special3();
                    break;
                case 4:
                    f.special4();
                    break;
                case 5:
                    f.special5();
                    break;
                case 6:
                    f.special6();
                    break;
            }
            System.out.println("");
            printSpecialMenu();
            System.out.println("");
        }
        
        
    } 

    private static void printMainMenu() {
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*  Vítejte v přehledu testování na COVID  *");
        System.out.println("*                                         *");
        System.out.println("* 1) Operace s tabulkou Patients          *");
        System.out.println("* 2) Operace s tabulkou Results           *");
        System.out.println("* 3) Operace s tabulkou Tests             *");
        System.out.println("* 4) Operace s tabulkou Stations          *");
        System.out.println("* 5) Operace s tabulkou Counties          *");
        System.out.println("* 6) Speciální selecty                    *");
        System.out.println("* 0) Konec                                *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
    }
    
    private static void printSubMenu(String table) {
         System.out.println("*****************************");
        System.out.println("*                           *");
        System.out.println("*      --- "+table+" ---     *");
        System.out.println("*                           *");
        System.out.println("* 1) Získat všechny záznamy *");
        System.out.println("* 2) Získej podle ID        *");
        System.out.println("* 3) Vlož nový záznam       *");
        System.out.println("* 4) Editovat záznam        *");
        System.out.println("* 5) Smazat záznam          *");
        System.out.println("* 0) Zpět                   *");
        System.out.println("*                           *");
        System.out.println("*****************************");       
    }
    
    private static void printSpecialMenu() {
        System.out.println("**************************************************************************************************************");
        System.out.println("*                                                                                                            *");
        System.out.println("*                                           -- Speciální dotazy --                                           *");
        System.out.println("* 1) Vyber jméno stanice a počet testů, které na ní byly provedeny                                           *");
        System.out.println("* 2) Vyber pacienty, kteří byly testování více než jednou a kolikrát                                         *");
        System.out.println("* 3) Vyber pacienty, kteří ještě nebyly testovaní                                                            *");
        System.out.println("* 4) Vypiš všechny stanice seskupené podle krajů                                                             *");
        System.out.println("* 5) Vyber uživatele, který byl již testován                                                                 *");
        System.out.println("* 6) Vyber všechny Testy - jméno testu a počet kolikrát byl proveden a jeho výsledek byl \"Pozitivní\"         *");
        System.out.println("* 0) Zpět                                                                                                    *");
        System.out.println("*                                                                                                            *");
        System.out.println("**************************************************************************************************************");
    }


    
}
