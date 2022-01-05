package sample;

import java.sql.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;

// Reference: https://www.tutorialspoint.com/sqlite/sqlite_java.htm

public class SQLiteJDBC extends Application {

  @Override
  public void start(Stage stage) {
    // Creating a Text object
    Text text = new Text();

    // Setting the text to be added.
    text.setText("Hello how are you");

    // setting the position of the text
    text.setX(50);
    text.setY(50);

    // Creating a Group object
    Group root = new Group(text);

    // Creating a scene object
    Scene scene = new Scene(root, 600, 300);

    // Setting title to the Stage
    stage.setTitle("Sample Application");

    // Adding scene to the stage
    stage.setScene(scene);

    // Displaying the contents of the stage
    stage.show();
  }

  public static void main(String[] args) {
    Connection c;
    Statement stmt;

    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      // Create a table
      stmt = c.createStatement();
      /*String sql = "CREATE TABLE COMPANY " +
              "(ID INT PRIMARY KEY     NOT NULL," +
              " NAME           TEXT    NOT NULL, " +
              " AGE            INT     NOT NULL, " +
              " ADDRESS        CHAR(50), " +
              " SALARY         REAL)";
      stmt.executeUpdate(sql);
      stmt.close();

      // Insert records
      stmt = c.createStatement();
      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();

      System.out.println("Records created successfully");*/

      // Select records

      ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String address = rs.getString("address");
        float salary = rs.getFloat("salary");

        System.out.println("ID = " + id);
        System.out.println("NAME = " + name);
        System.out.println("AGE = " + age);
        System.out.println("ADDRESS = " + address);
        System.out.println("SALARY = " + salary);
        System.out.println();
      }
      rs.close();
      stmt.close();

      ResultSet rs2 = stmt.executeQuery("SELECT * FROM MYDATA;");

      while (rs2.next()) {
        int id = rs2.getInt("id");
        String fname = rs.getString("first_name");
        String lname = rs.getString("last_name");
        String email = rs.getString("email");
        String gender = rs.getString("gender");
        String ip = rs.getString("ip_address");


        System.out.println("ID = " + id);
        System.out.println("FNAME = " + fname);
        System.out.println("LNAME = " + lname);
        System.out.println("EMAIL = " + email);
        System.out.println("GENDER = " + gender);
        System.out.println("IP = " + ip);
        System.out.println();
      }
      rs2.close();
      stmt.close();

      c.close();
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }

    launch(args);
  }
}
