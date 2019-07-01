package sample;

//All the necessary imports
import java.sql.*;

public class DBConnect {
    //declaration for sql
    public Connection con;
    public Statement st;
    public ResultSet rs;

    //get connetction uel
    public static Connection getConnection() throws SQLException, ClassNotFoundException, SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaljava","root","");

        return connection;

    }

    DBConnect(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            // Create Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaljava","root","");
            st = con.createStatement();
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    //insert notes function to insert to database
    public void insertNotes(String title , String date){
        String sql = String.format("insert into notes(title , date ) VALUES('%s' , '%s' )" ,title , date);
        try{
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Table Inserted");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //update note function to refresh the table view
    public void updateNote(String body , String table , String i){
        String sql = String.format("update %s set body = '%s' , img = '%s' where id = 1;" ,table , body , i);
        System.out.println(sql);
        try{
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Table Updated");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //adding table and row to the database to make new notes
    public void addTableAndRow(String title){
        String sql = String.format("CREATE TABLE %s (\n" +
                "    id int NOT NULL AUTO_INCREMENT,\n" +
                "    title varchar(255),\n" +
                "    body varchar(255),\n" +
                "    img varchar(255),\n" +
                "    PRIMARY KEY(id)\n" +
                ");" , title) ;
        String ssql = String.format("insert into %s(title , body) VALUES('%s' , '')",title  , title);
        System.out.println(ssql);
        try{
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Table Inserted");
            st.executeUpdate(ssql);
            System.out.println("User Registered");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //delete item from sql database
    void deleteItem(String title){

        String sql = String.format("delete from notes where title = '%s' "  ,title);
        String ssql = String.format("drop table %s" , title);


        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Row Deleted");
            st = con.createStatement();
            st.executeUpdate(ssql);
            System.out.println("Table Deleted");


        } catch (Exception e){
            System.out.println(e);
        }
    }
}
