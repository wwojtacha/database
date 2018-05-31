package examples;

import java.sql.*;

//insert do bazy danych

public class JDBC4 {
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
            if (connection == null) {
                System.out.println("Polaczenie nieudane");
                return;
            }

            PreparedStatement pst = null;
            String stm = "INSERT INTO kontrola.straznik(straznik_id,imie,nazwisko,stopien,pensja) VALUES(?,?,?,?,?)";
            pst = connection.prepareStatement(stm);

            pst.setInt(1, 105);
            pst.setString(2, "Jan");
            pst.setString(3, "Jeziorański");
            pst.setString(4, "Szeregowiec");
            pst.setDouble(5, 2300.32);
            pst.executeUpdate();


        } catch (SQLException e) {

            System.out.println("Blad polaczenia do bazy danych");
            e.printStackTrace();
            return;

        }

    }
}
