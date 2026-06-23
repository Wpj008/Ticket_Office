package sql.bdd;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import model.Event;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    static int id;

    static Connection connection =  DatabaseConnection.getConnection();


    public static void registerEvent(Event event){

        try {

            String sql_request = "INSERT INTO events(title_event, description_event, lieu_event, date_event, price_event, place_total_event, status_event, organizer_id, created_at, updated_at) VALUES(?,?,?,?,?,?,?,?, NOW(), NOW())";

            String request = "SELECT * FROM statuts WHERE name_status = 'PLANIFIE'";

            PreparedStatement stm = connection.prepareStatement(request);
            ResultSet result = stm.executeQuery();

            //étape 5: extraire les donnéesQ
            while(result.next()){
                //Récupérer par nom de colonne
                id = result.getInt("id_status");
            }

            PreparedStatement pstmt = connection.prepareStatement(sql_request);

            pstmt.setString(1, event.getTitlEvent());
            pstmt.setString(2, event.getDescriptionEvent());
            pstmt.setString(3, event.getLieuEvent());
            pstmt.setTimestamp(4, Timestamp.valueOf(event.getDatEvent()));
            pstmt.setDouble(5, event.getPricEvent());
            pstmt.setInt(6, event.getPlaceTotalEvent());
            pstmt.setInt(7, id);
            pstmt.setLong(8, event.getOrganizerId().getIdUser());

            if (!pstmt.execute()) {
                System.out.println("Evénement crée avec succès !");
            }

        }catch (SQLException e) {

            throw new RuntimeException(e);

        }



    }


    public static List<Event> getEvent(User user){


        try {
            PreparedStatement querySelect = connection.prepareStatement("SELECT * FROM events WHERE  organizer_id <> ? AND status_event = 1");

            querySelect.setLong(1, user.getIdUser());

            ResultSet result = querySelect.executeQuery();


            List<Event> events = new ArrayList<>();

            while (result.next()) {

                    long id = result.getLong("id_event");
                    String titre = result.getNString("title_event");
                    String description = result.getNString("description_event");
                    String lieu = result.getNString("lieu_event");
                    Timestamp timestamp = result.getTimestamp("date_event");
                    LocalDateTime datEvent = timestamp.toLocalDateTime();
                    double  price = result.getDouble("price_event");
                    int  placeTotalEvent = result.getInt("place_total_event");

                    Event event = new Event();
                    event.setIdEvent(id);
                    event.setTitlEvent(titre);
                    event.setDescriptionEvent(description);
                    event.setLieuEvent(lieu);
                    event.setDatEvent(datEvent);
                    event.setPricEvent(price);
                    event.setPlaceTotalEvent(placeTotalEvent);


                  events.add(event);

            }

            return events;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }


    public static List<Event> getEventByUser(User user){


        try {
            PreparedStatement querySelect = connection.prepareStatement("SELECT * FROM events WHERE organizer_id = ?");

             querySelect.setLong(1, user.getIdUser());

            ResultSet result = querySelect.executeQuery();

            List<Event> events = new ArrayList<>();

            while (result.next()) {

                long id = result.getLong("id_event");
                String titre = result.getString("title_event");
                String description = result.getString("description_event");
                String lieu = result.getString("lieu_event");

                Timestamp timestamp = result.getTimestamp("date_event");
                LocalDateTime datEvent = timestamp.toLocalDateTime();

                double price = result.getDouble("price_event");
                int placeTotalEvent = result.getInt("place_total_event");

                Event event = new Event();
                event.setIdEvent(id);
                event.setTitlEvent(titre);
                event.setDescriptionEvent(description);
                event.setLieuEvent(lieu);
                event.setDatEvent(datEvent);
                event.setPricEvent(price);
                event.setPlaceTotalEvent(placeTotalEvent);

                events.add(event);
            }

            return events;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }


    public static Event getEventById(long idEvent){


        try {
            PreparedStatement querySelect = connection.prepareStatement("SELECT * FROM events WHERE id_event = ?");

            querySelect.setLong(1, idEvent);

            ResultSet result = querySelect.executeQuery();



            while (result.next()) {

                long id = result.getLong("id_event");
                String titre = result.getString("title_event");
                String description = result.getString("description_event");
                String lieu = result.getString("lieu_event");

                Timestamp timestamp = result.getTimestamp("date_event");
                LocalDateTime datEvent = timestamp.toLocalDateTime();

                double price = result.getDouble("price_event");
                int placeTotalEvent = result.getInt("place_total_event");

                Event eventbyId = new Event();
                eventbyId.setIdEvent(id);
                eventbyId.setTitlEvent(titre);
                eventbyId.setDescriptionEvent(description);
                eventbyId.setLieuEvent(lieu);
                eventbyId.setDatEvent(datEvent);
                eventbyId.setPricEvent(price);
                eventbyId.setPlaceTotalEvent(placeTotalEvent);

                return eventbyId;
            }



        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    return null;

    }


}
