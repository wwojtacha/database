package movies24;

import java.util.Scanner;

public class MainMovies24 {
    public static void main(String[] args) {
        DBController dbc = new DBController();
        dbc.connectToDB();
        Scanner sc = new Scanner(System.in);
//        System.out.println("Choose abonament type: '1 = Standard' or '2 - Premium'");
//        int abonament = sc.nextInt();
//        System.out.println("Enter login: ");
//        String login = sc.next();
//        System.out.println("Enter password: ");
//        String password = sc.next();
//        System.out.println("Enter first name: ");
//        String firstName = sc.next();
//        System.out.println("Enter last name: ");
//        String lastName = sc.next();
//
//        dbc.registerUser(abonament, login, password, firstName, lastName);
        dbc.searchMovie("exo");
        //dbc.downloadMovie(2, 2);
        dbc.printDowloadedMovies(2);
    }
}
