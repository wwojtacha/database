package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//połączenie z bazą danych. Najpierw pobieramy driver.

public class JDBC1 {
    public static void main(String[] argv) {

        System.out.println("----Test polaczenia do bazy MySQL --------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println("Nie znaleziono drivera MySQL");
            e.printStackTrace();
            return;

        }

        System.out.println("MySQL JDBC Driver zarejestrowany");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/kontrola", "root", "Pasz6e6#6");
        } catch (SQLException e) {

            System.out.println("Blad polaczenia do bazy danych");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("==Tu mozna dodac operacje na DB==");
        } else {
            System.out.println("Polaczenie nieudane");
        }
    }

}