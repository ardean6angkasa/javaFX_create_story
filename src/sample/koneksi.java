package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
public class koneksi {

    private static Connection conn;
    private static Statement st;

    public koneksi(){
        try{
            String user="root";
            String password="";
            String url="jdbc:mysql://localhost:3306/timeline_javafx";
            conn = DriverManager.getConnection(url,user,password);
        } catch(SQLException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
    public static int manipulasiData(String query){
        try{
            st=conn.createStatement();
            return st.executeUpdate(query);
        } catch (SQLException e){
            System.out.println("Error: "+ e.getMessage());
            return 0;
        }
    }
    public static ResultSet getData(String query) {
        try {
            st = conn.createStatement();
            return st.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}