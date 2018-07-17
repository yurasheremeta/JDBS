package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    static Connection conn;
    public static void main(String[] args) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/human_db_2?useSSL=false";
        String userName = "root";
        String password = "root12345";
        conn = DriverManager.getConnection(dbURL, userName, password);
        System.out.println("Connecting: " + !conn.isClosed());
        Scanner scan = new Scanner(System.in);

        DB.Create_Tables();
        DB.alter_tables();

        while(true){

            printMenu();
            String fromUser = scan.next();
            switch(fromUser){
                case "1":
                    DB.addCountry();
                    break;
                case "2":
                    DB.addCity();
                    break;
                case "3":
                    DB.addHuman();
                default:
                    conn.close();
            }
        }
    }

    private static void printMenu(){
        System.out.println("1 - Add country");
        System.out.println("2 - Add city");
        System.out.println("3 - Add person");
        System.out.println("4 - Output all information from city");
        System.out.println("5 - Output all information from country");
        System.out.println("6 - Output all information from person");
        System.out.println("7 - Output person by id");
        System.out.println("8 - Output city by id");
        System.out.println("9 - Output country by id");
        System.out.println("10 - Output information about people in the same city");
        System.out.println("11 - join Country_city");
    }
}
