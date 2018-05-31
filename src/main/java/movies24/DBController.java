package movies24;

import java.sql.*;

public class DBController {

    private Connection connection = null;
    private static ResultSet rs = null;
    private static Statement stmt = null;

    public void connectToDB() {

        System.out.println("----Test polaczenia do bazy MySQL --------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println("Nie znaleziono drivera MySQL");
            e.printStackTrace();
        }

        System.out.println("MySQL JDBC Driver zarejestrowany");

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/movies24", "root", "Pasz6e6#6");
        } catch (SQLException e) {

            System.out.println("Blad polaczenia do bazy danych");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("==Tu mozna dodac operacje na DB==");

        } else {
            System.out.println("Polaczenie nieudane");
        }

    }

    public int addFinancialAccount(){

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.executeUpdate("INSERT INTO financial_account() VALUES()", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int bank_id=-1;
        try {
            if (rs.next()) {
                bank_id = rs.getInt(1);
            } else {

                System.out.println("get genratedKeys nie zwrocilo wartosci");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank_id;
    }

    public void registerUser(int abonamentId, String login, String password, String firstName, String lastName){

        int financialAccountId = addFinancialAccount();
        PreparedStatement pst = null;
        String stm = "INSERT INTO movies24.users (id_financial_account, id_abonament, login, user_password, first_name, last_name) VALUES(?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(stm);
            pst.setInt(1, financialAccountId);
            pst.setInt(2, abonamentId);
            pst.setString(3, login);
            pst.setString(4, password);
            pst.setString(5, firstName);
            pst.setString(6, lastName);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchMovie (String text){
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT title FROM movies24.movie WHERE title LIKE '%" + text + "%'");
            rs = pst.executeQuery();
            while (rs.next()){
            System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void downloadMovie(int movieId, int userId){
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO download_history (id_movie, id_user, dowload_date) VALUES (?,?,?)");
            pst.setInt(1, movieId);
            pst.setInt(2, userId);
            pst.setString(3, "2018-05-29 19:40:34");

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printDowloadedMovies(int userId){
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT id_user, title FROM movie m JOIN download_history dh ON m.id = dh.id_movie WHERE id_user = '" + userId + "'");
            rs = pst.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

