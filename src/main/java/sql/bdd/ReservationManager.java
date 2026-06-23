package sql.bdd;

import model.Event;
import model.Reservation;
import model.Status;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {

    static int id;
    static Connection connection =  DatabaseConnection.getConnection();


    public static void registerReservation(Reservation reservation){

        try {

            String sql_request = "INSERT INTO reservations(user_id, event_id, nombre_places, total_price_reservation, reservation_date, status_reservation, created_at, updated_at) VALUES(?,?,?,?,NOW(),?, NOW(), NOW())";

            String request = "SELECT * FROM statuts WHERE name_status = 'CONFIRME'";

            PreparedStatement stm = connection.prepareStatement(request);
            ResultSet result = stm.executeQuery();

            //étape 5: extraire les données
            while(result.next()){
                //Récupérer par nom de colonne
                id = result.getInt("id_status");
            }

            PreparedStatement pstmt = connection.prepareStatement(sql_request);

            pstmt.setLong(1, reservation.getUserId().getIdUser());
            pstmt.setLong(2, reservation.getEventId().getIdEvent());
            pstmt.setInt(3, reservation.getNombrePlaces());
            pstmt.setDouble(4,reservation.getTotalPrice());
            pstmt.setInt(5, id);

            if (!pstmt.execute()) {
                System.out.println("Réservation créée avec succès !");
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


    public static List<Reservation> getReservationByUser(User user){


        try {
            PreparedStatement querySelect = connection.prepareStatement("SELECT * FROM reservations WHERE user_id = ?");

            querySelect.setLong(1, user.getIdUser());

            ResultSet result = querySelect.executeQuery();

            List<Reservation> reservations = new ArrayList<>();

            while (result.next()) {

               // long id = result.getLong("id_reservations");
                String userId = result.getString("user_id");
                long eventId = result.getLong("event_id");
                int nombrePlaces = result.getInt("nombre_places");

                double totalPriceReservation = result.getDouble("total_price_reservation");


                long statusId = result.getLong("status_reservation");


                Reservation reservation = new Reservation();
               // reservation.setIdUse(id);
                reservation.setUserId(user);
                Event event =  EventManager.getEventById(eventId);
                reservation.setEventId(event);

                reservation.setNombrePlaces(nombrePlaces);
                reservation.setTotalPrice(totalPriceReservation);

                Status status = StatusManager.getStatusById(statusId);
                reservation.setStatusReservation(status);


                reservations.add(reservation);
            }

            return reservations;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }



}
