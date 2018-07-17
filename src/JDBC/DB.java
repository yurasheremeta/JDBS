package JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}
