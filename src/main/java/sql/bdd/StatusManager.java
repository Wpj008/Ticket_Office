package sql.bdd;

import model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusManager {

    static int id;
    static Connection connection =  DatabaseConnection.getConnection();

        public static Status getStatusById(long idStatus) {

            try {

                PreparedStatement querySelect =
                        connection.prepareStatement(
                                "SELECT * FROM statuts WHERE id_status = ?"
                        );

                querySelect.setLong(1, idStatus);

                ResultSet result = querySelect.executeQuery();

                while (result.next()) {

                    long id = result.getLong("id_status");
                    String name = result.getString("name_status");

                    Status status = new Status();

                    status.setIdStatus(id);
                    status.setNameStatus(name);

                    return status;
                }

                return null;

            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }
    }

