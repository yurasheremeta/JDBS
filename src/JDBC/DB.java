package JDBC;

import com.sun.scenario.effect.impl.prism.PrReflectionPeer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DB  {
    protected static void Create_Tables() throws SQLException{
        String delete_country = "drop table if exists country;";
        String create_country = "create table country (" +
                "id int not null primary key auto_increment," +
                "country_name varchar(30)" +
                ");";
        String delete_city = "drop table if exists city;";
        String create_city = "create table city(" +
                "id int not null primary key auto_increment," +
                "city_name varchar(30)," +
                "country_id int" +
                ");";
        String delete_human = "drop table if exists person";
        String create_human = "create table person(" +
                "id int NOT NULL primary key auto_increment," +
                "first_name varchar(30), " +
                "last_name varchar(30)," +
                "age int, " +
                "hobby varchar(30)," +
                "city_id int" +
                ");";
        Statement stm = Program.conn.createStatement();
        stm.execute(delete_human);
        stm.execute(create_human);



        stm.execute(delete_city);
        stm.execute(create_city);
        stm.execute(delete_country);
        stm.execute(create_country);



        stm.close();
    }
    protected static void alter_tables()throws SQLException{
        String country_city = "alter table city add foreign key (country_id)" +
                "references country(id); ";
        String person_city = "alter table person add foreign key (city_id) " +
                "references city(id);";

        Statement stm = Program.conn.createStatement();

        stm.execute(country_city);
        stm.execute(person_city);


        stm.close();
    }
    protected static void addCountry()throws SQLException{
        Scanner scan = new Scanner(System.in);
        String query = "Insert into country(country_name) values(?)";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        String a = scan.next();
        ps.setString(1,a);
        ps.executeUpdate();
        ps.close();
        System.out.println("Country aded!");
    }
    protected static void addCity()throws SQLException{
        Scanner scan = new Scanner(System.in);
        String query = "Insert into city(city_name , country_id) values(?,?)";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        String a = scan.next();
        int country_id = scan.nextInt();
        ps.setString(1,a);
        ps.setInt(2,country_id);
        ps.executeUpdate();
        ps.close();
        System.out.println("City aded!");
    }
    protected static void addHuman() throws SQLException{
        Scanner scan = new Scanner(System.in);
        String query = "Insert into person(first_name ,last_name , age , hobby , city_id ) values(?,?,?,?,?)";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        System.out.println("Enter the name: ");
        String first_name  = scan.next();
        System.out.println("Enter the last_name");
        String last_name = scan.next();
        System.out.println("Enter age");
        int age = scan.nextInt();
        System.out.println("Enter the hobby of " +first_name);
        String hobby = scan.next();
        System.out.println("From what city: ");
        int city_id = scan.nextInt();
//        System.out.println("From what city: ");
//        int city_id = scan.nextInt();
        ps.setString(1,first_name);
        ps.setString(2,last_name);
        ps.setInt(3,age);
        ps.setString(4,hobby);
        ps.setInt(5,city_id);

        ps.executeUpdate();
        ps.close();
        System.out.println(first_name+" is aded!");
    }
    protected static void selectCity() throws SQLException{
        String query = "select *  from city";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> cities  = new ArrayList<>();
        while(rs.next()){
            cities.add("id: "+rs.getInt("id")+"\t |" +
                    "name: "+ rs.getString("city_name")+"\t |" +
                    "country: "+ rs.getInt("country_id")+"\t |");
        }
        cities.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected static void selectPerson()throws SQLException{
        String query = "select * from person";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> persons = new ArrayList<>();
        while(rs.next()){
            persons.add("id: "+rs.getInt("id")+"\t |" +
                    "first_name: "+rs.getString("first_name")+"\t |" +
                    "last_name: "+rs.getString("last_name")+"\t |" +
                    "age: "+rs.getInt("age")+"\t |" +
                    "hobby: "+rs.getString("hobby")+"\t |" +
                    "city: "+ rs.getInt("city_id")+"\t |" );
        }
        persons.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected static void selectCountry() throws SQLException{
        String query = "select * from country";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> country = new ArrayList<>();
        while(rs.next()){
            country.add("id: "+rs.getInt("id")+"\t |" +
                    "name: "+rs.getString("country_name")+"\t |");
        }
        country.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected static void selectPersonWithID() throws SQLException{
        Scanner scan = new Scanner(System.in);
        String query = "Select * from person where id = ?;";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        System.out.println("Enter the id");
        int id = scan.nextInt();
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<String> person = new ArrayList<>();
        while(rs.next()){
            person.add("id: "+rs.getInt("id")+"\t |" +
                    "first_name: "+rs.getString("first_name")+"\t |" +
                    "last_name"+rs.getString("last_name")+"\t |" +
                    "age: "+rs.getInt("age")+"\t |" +
                    "hobby: "+rs.getString("hobby")+"\t |" +
                    "city: "+ rs.getString("city_id")+"\t |");
        }
        person.forEach(System.out::println);
        rs.close();
        ps.close();

    }
    protected static void selectCityWithID() throws SQLException{
        Scanner scan = new Scanner(System.in);
        String query = "Select * from city where id = ?;";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        System.out.println("Enter id: ");
        int id = scan.nextInt();
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        List<String> city = new ArrayList<>();
        while(rs.next()){
            city.add("id: "+rs.getInt("id")+"\t |" +
                    "name: "+rs.getString("city_name")+"\t |" +
                    "country:"+rs.getInt("country_id")+"\t |");
        }
        city.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected static void selectCountryWithID() throws SQLException{
        Scanner scan = new Scanner(System.in);
        String query = "Select * from country where id = ?;";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        System.out.println("Enter id: ");
        int id = scan.nextInt();
        ps.setInt( 1, id);
        ResultSet rs = ps.executeQuery();
        List<String> country = new ArrayList<>();
        while(rs.next()){
            country.add("id: "+rs.getInt("id")+"\t |" +
                    "name: "+rs.getString("country_name")+"\t |");
        }
        country.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected static void joinPersonCity() throws SQLException{
        String query = "Select * from person p join city c on  p.city_id = c.id";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> person = new ArrayList<>();
        while(rs.next()){
            person.add("id"+rs.getInt("id")+"\t |" +
                    "first_name"+rs.getString("first_name")+"\t |" +
                    "city_id"+rs.getInt("city_id")+"\t |" +
                    "city_name: "+rs.getString("city_name")+"\t |");
        }
        person.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected static void cityFromContry()throws SQLException{
        Scanner scan = new Scanner(System.in);
        String query = "select * from city where country_id = ?";
        PreparedStatement ps = Program.conn.prepareStatement(query);
        System.out.println("Enter the country_id: ");
        int country_id = scan.nextInt();
        ps.setInt(1,country_id);
        ResultSet  rs = ps.executeQuery();
        List<String> city  = new ArrayList<>();
        while(rs.next()){
            city.add("id: "+rs.getInt("id")+"\t |" +
                    "city name: "+ rs.getString("city_name")+"\t |" +
                    "country id"+rs.getInt("country_id")+"\t |");
        }
        city.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected static void personWhichCityAndCountry() throws SQLException{
        String query = "select p.id, p.first_name,p.last_name,p.city_id,c.city_name,c.country_id, co.country_name  from person p " +
                "join city c on p.city_id = c.id " +
                "join country co on c.country_id = co.id;";
        PreparedStatement ps  = Program.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> persons = new ArrayList<>();
        while(rs.next()){
            persons.add("id: "+rs.getInt("id")+"\t |" +
                    "first_name: "+rs.getString("first_name")+"\t |" +
                    "last_name: "+rs.getString("last_name")+"\t |" +
                 //   "age: "+rs.getInt("age")+"\t |" +
                    "country: "+rs.getInt("country_id")+"\t |"+
                    "country_name "+rs.getString("country_name")+"\t |"+
                    "city: "+ rs.getInt("city_id")+"\t |" +
                    "city_name "+rs.getString("city_name")+"\t |" );
        }
        persons.forEach(System.out::println);
        rs.close();
        ps.close();
    }
    protected  static void updateInfoPerson() throws SQLException{
        Scanner scan = new Scanner(System.in);
        String menu = scan.next();
        String buff = "en";
        String buff2 = "ua";
        if(menu.equals(buff)){
            Program.UpddateMenu();
        }else if(menu.equals(buff2)){
            Program.UpdateMenuUA();
        }
        String fromUser = scan.next();
            switch(fromUser){
                case "1":
                    String query = "update person set first_name = ? where id = ? ";
                    PreparedStatement ps = Program.conn.prepareStatement(query);
                    System.out.println("Input first name: ");
                    String first_name = scan.next();
                    System.out.println("What person you want to update: ");
                    int id = scan.nextInt();
                    ps.setString(1,first_name);
                    ps.setInt(2,id);
                    ps.executeUpdate();
                    ps.close();
                    break;
                case "2":
                    String update_lastName = "update person set last_name = ? where id = ? ";
                    PreparedStatement pstm = Program.conn.prepareStatement(update_lastName);
                    System.out.println("Input last name: ");
                    String last_name = scan.next();
                    System.out.println("What person you want to update: ");
                    int id1 = scan.nextInt();
                    pstm.setString(1,last_name);
                    pstm.setInt(2,id1);
                    pstm.executeUpdate();
                    pstm.close();

                    break;
                case "3":
                    String update_age = "update person set age = ? where id = ? ";
                    PreparedStatement ps1 = Program.conn.prepareStatement(update_age);
                    System.out.println("Input age: ");
                    int age = scan.nextInt();
                    System.out.println("What person you want to update: ");
                    int id2 = scan.nextInt();
                    ps1.setInt(1,age);
                    ps1.setInt(2,id2);
                    ps1.executeUpdate();
                    ps1.close();
                    break;
                case "4":
                    String update_hobby = "update person set hobby = ? where id = ? ";
                    PreparedStatement ps2 = Program.conn.prepareStatement(update_hobby);
                    System.out.println("Input hobby: ");
                    String hobby = scan.next();
                    System.out.println("What person you want to update: ");
                    int id3 = scan.nextInt();
                    ps2.setString(1,hobby);
                    ps2.setInt(2,id3);
                    ps2.executeUpdate();
                    ps2.close();
                    break;
                case "5":
                    String update_All = "update person set first_name = ?," +
                            "last_name = ?," +
                            "age = ?," +
                            "hobby = ?" +
                            "where id = ?;";
                    PreparedStatement pstmt1 = Program.conn.prepareStatement(update_All);
                    System.out.println("Input first_name: ");
                    String first_name1 = scan.next();
                    System.out.println("Input last_name: ");
                    String last_name1 = scan.next();
                    System.out.println("Input age: ");
                    int age1 = scan.nextInt();
                    System.out.println("Input hobby: ");
                    String hobby1 = scan.next();
                    System.out.println("What person you want to update: ");
                    int id4 = scan.nextInt();
                    pstmt1.setString(1,first_name1);
                    pstmt1.setString(2,last_name1);
                    pstmt1.setInt(3,age1);
                    pstmt1.setString(4,hobby1);
                    pstmt1.setInt(5,id4);
                    pstmt1.executeUpdate();
                    pstmt1.close();
                    break;


            }
    }


}
