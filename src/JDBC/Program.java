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
        System.out.println("en - English");
        System.out.println("ua - Українська");
        String buff = "en";
        String buff2 = "ua";
        String menu = scan.next();
        while(true){
            if(menu.equals(buff)){
                printMenu();
            }else if (menu.equals(buff2)){
                printMenuUA();
            }
            //printMenu();
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
                    break;
                case "4":
                    DB.selectCountry();
                    break;
                case "5":
                    DB.selectCity();
                case "6":
                    DB.selectPerson();
                    break;
                case "7":
                    DB.selectCountryWithID();
                    break;
                case "8":
                    DB.selectCityWithID();
                    break;
                case "9":
                    DB.selectPersonWithID();
                    break;
                case "10":
                    DB.joinPersonCity();
                    break;
                case "11":
                    DB.cityFromContry();
                    break;
                case "12":
                    DB.personWhichCityAndCountry();
                    break;
                case "13":
                    DB.updateInfoPerson();
                    break;

                case "e":
                    System.out.println("Goodbay");
                    conn.close();
                    return;
            }
        }
    }

    private static void printMenu(){
        System.out.println("1 - Add country");
        System.out.println("2 - Add city");
        System.out.println("3 - Add person");
        System.out.println("4 - Output all information from country");
        System.out.println("5 - Output all information from city");
        System.out.println("6 - Output all information from person");
        System.out.println("7 - Output country by id");
        System.out.println("8 - Output city by id");
        System.out.println("9 - Output person by id");
        System.out.println("10 - Output information about people in the same city");
        System.out.println("11 - Output what cities are there in country");
        System.out.println("12 - Output from what country and city is person ");
        System.out.println("13 - Update person");
        System.out.println("e - EXIT");
    }
    private static void printMenuUA(){
        System.out.println("1 - Додати Країну");
        System.out.println("2 - Додати місто");
        System.out.println("3 - Додати людину");
        System.out.println("4 - Вивети всю інформацію про країни");
        System.out.println("5 - Вивети всю інформацію про міств");
        System.out.println("5 - Вивети всю інформацію про людей");
        System.out.println("6 - Вивети всю інформацію про людей");
        System.out.println("7 - Вивести країну за певним id");
        System.out.println("8 - Вивести місто за певним id");
        System.out.println("9 - Вивести людину за певним id");
        System.out.println("10 - Вивести інформацію про людей з одного міста");
        System.out.println("11 - Показати інформацію про міста які входять до вказаної країни");
        System.out.println("12 - Показати інформацію про місто та країну з якого є людина");
        System.out.println("13 - Змінити інформацію про людину");
        System.out.println("e - ВИХІД");

    }

    protected static void UpddateMenu(){
        System.out.println("1 - update first name");
        System.out.println("2 - update last name");
        System.out.println("3 - update age");
        System.out.println("4 - update hobby");
        System.out.println("5 - update all info ");
    }
    protected  static void UpdateMenuUA(){
        System.out.println("1 - змінити ім'я");
        System.out.println("2 - змінити прізвище");
        System.out.println("3 - змінити вік");
        System.out.println("4 - змінити хоббі");
        System.out.println("5 - змінити всю інформацію");
    }
}
