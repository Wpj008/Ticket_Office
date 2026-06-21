package sql.bdd;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserManager {
    static int id;

   static Connection connection =  DatabaseConnection.getConnection();


    //id_user	last_name	first_name	email_user	password_user	role_id	created_at	updated_at
    public static void register(String last_name, String first_name, String email_user, String password_user, int role){
       // public static void getting(){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        try {
            // iterations=2, memory=65536 KB, parallelism=1
           String hash = argon2.hash(2, 65536, 1, password_user);
            // System.out.println(hash);

           // Connection connection =  DatabaseConnection.getConnection();
            String sql_request = "INSERT INTO users(last_name, first_name, email_user, password_user, role_id,  created_at, updated_at) VALUES(?,?,?,?,?, NOW(), NOW())";

            String request = "SELECT * FROM roles WHERE name_role = 'UTILISATEUR'";

            PreparedStatement stm = connection.prepareStatement(request);
            ResultSet result = stm.executeQuery();

            //étape 5: extraire les donnéesQ
            while(result.next()){
                //Récupérer par nom de colonne
                 id = result.getInt("id_role");
            }

            PreparedStatement pstmt = connection.prepareStatement(sql_request);

            pstmt.setString(1, last_name);
            pstmt.setString(2, first_name);
            pstmt.setString(3, email_user);
            pstmt.setString(4, hash);
            pstmt.setInt(5, id);

                    if(pstmt.execute()){
                        System.out.println("User created with successfull !");
                    }

            //return fail;


            // Vérifier
            //  boolean isMatch = argon2.verify(hash, password);
            // System.out.println("Valide : " + isMatch);



        }catch (SQLException e) {

            throw new RuntimeException(e);

        }



}


  /*  public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }*/

    public static User connexion(String email_user, String passLog){


        try {
            PreparedStatement querySelect = connection.prepareStatement("SELECT * FROM users WHERE email_user = ?");

            querySelect.setString(1, email_user);

            ResultSet result = querySelect.executeQuery();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

          //  if(email_user.equals(result.getNString("email_user"))) {

                while (result.next()) {

                    boolean pass_hash = argon2.verify(result.getNString("password_user"), passLog);


                    if (pass_hash) {


                        int idUser = result.getInt("id_user");
                        String firstName = result.getNString("first_name");
                        String lastName = result.getNString("last_name");
                        String email = result.getNString("email_user");

                        User user = new User();
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setEmail(email);
                        user.setIdUser(idUser);




                       // System.out.println("Prenom : " + user.getFirstName());
                       // System.out.println("Nom : " + user.getLastName());
                       // System.out.println("Email : " + user.getEmail());

                        return user;

                    } else {
                        System.out.println("Email ou Mot de passe incorrect !");
                    }
                }
           /* } else {
                System.out.println("Email incorrect !");
            }*/
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


        return null;
    }
}
