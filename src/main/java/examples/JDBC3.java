package examples;

import java.sql.*;

// select z bazy danych

public class JDBC3 {
    private static Connection connection = null;
    private static Statement st = null;
    private static ResultSet rs = null;

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


            st = connection.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

            PreparedStatement pst = null;
            pst = connection.prepareStatement("SELECT * FROM kontrola.straznik");
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2)); // albo rs.getString("imie")
            }


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
