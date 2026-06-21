package sql.bdd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {

    //Parametre de connexion

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ticket_office";//url du serveur Mysql
    private static final String USER = "root";//user mysql
    private static final String PASSWORD = "";//password user mysql

    //Method for connection

    public static Connection getConnection(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la bdd !");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return connection;
    }
}
