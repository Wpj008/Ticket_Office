package org.example;

import model.Event;
import model.Reservation;
import model.Status;
import model.User;
import sql.bdd.DatabaseConnection;
import sql.bdd.EventManager;
import sql.bdd.ReservationManager;
import sql.bdd.UserManager;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        Connection connection = DatabaseConnection.getConnection();
        System.out.println("bdd OK");

        Scanner scanByte = new Scanner(System.in);

        System.out.println("Bienvenue dans l'interface de connexion !");
        System.out.println("[1]: Register ; [2] Connexion : ");;

        byte choice = scanByte.nextByte();


        switch (choice){
            case 1:
                System.out.println("******************************");
                System.out.println("Quel est votre Nom ?");
                Scanner scanLastName = new Scanner(System.in);
                String last_name = scanLastName.nextLine();

                System.out.println("******************************");
                System.out.println("Quel est votre Prenom ?");
                Scanner scanFirstName = new Scanner(System.in);
                String first_name = scanFirstName.nextLine();

                System.out.println("******************************");
                System.out.println("Quel est votre Mail ?");
                Scanner scanMail = new Scanner(System.in);
                String mail = scanMail.nextLine();



                System.out.println("******************************");
                System.out.println("Quel est votre mot de passe ?");
                Scanner scanPassword = new Scanner(System.in);
                String password = scanPassword.nextLine();
                
                int role = 0;

                UserManager.register(last_name, first_name, mail, password, role);
                

                break;

            case 2:

                System.out.println("******************************");
                System.out.println("Quel est votre mail ?");
                Scanner scanLogMail = new Scanner(System.in);
                String LogMail = scanLogMail.nextLine();

                System.out.println("******************************");
                System.out.println("Quel est votre mot de passe ?");
                Scanner scanLogPassword = new Scanner(System.in);
                String LogPassword = scanLogPassword.nextLine();



               User connectedUser =  UserManager.connexion(LogMail, LogPassword);



               if(connectedUser != null){

                   System.out.println("Bienvenue " + connectedUser.getFirstName());

                   System.out.println("1 - Voir les événements");
                   System.out.println("2 - Créer un événement");
                   System.out.println("3 - Mes événements");
                   System.out.println("4 - Mes réservations");
                   System.out.println("5 - Déconnexion");

                   System.out.println("Quelle option choisissez vous : ");
                   Scanner scan = new Scanner(System.in);
                   byte option = scan.nextByte();

                   switch (option){

                       case 1:


                           List<Event> events = EventManager.getEvent(connectedUser);

                           System.out.println("Liste des Evenements !");

                           for (Event event : events) {

                               System.out.println("=====================================");

                               System.out.println("ID Event : " + event.getIdEvent());
                               System.out.println("Titre : " + event.getTitlEvent());
                               System.out.println("Description : " + event.getDescriptionEvent());
                               System.out.println("Lieu : " + event.getLieuEvent());
                               System.out.println("Date et heure : " + event.getDatEvent());
                               System.out.println("Prix : " + event.getPricEvent());
                               System.out.println("Place Disponible : " + event.getPlaceTotalEvent());
                               System.out.println("=====================================");
                           }


                           System.out.println("Faire une réservation ? 1. [oui]/2.[Non] : ");
                           Scanner scanReservation = new Scanner(System.in);
                           byte ResChoice = scanReservation.nextByte();

                           if(ResChoice == 1){

                               System.out.println("ID de l'événement : ");
                               Scanner scanIdEventRes = new Scanner(System.in);
                               long choiceEvent = scanIdEventRes.nextLong();

                               System.out.println("Nombre de place : ");
                               Scanner scanNombrePlace = new Scanner(System.in);
                               byte choicePlace = scanNombrePlace.nextByte();

                               Status status = new Status();

                               Event eventRes = EventManager.getEventById(choiceEvent);

                               double totalPrice = eventRes.getPricEvent() * choicePlace ;

                               Reservation reservation = new Reservation(connectedUser,eventRes, choicePlace,totalPrice,status );

                               ReservationManager.registerReservation(reservation);

                           } else if(ResChoice == 2){
                               return;
                           } else {
                               System.out.println("Saisie Incorrecte !!");
                           }



                           break;

                       case 2:
                           System.out.println("Saisissez les informations de l'événement !");
                           System.out.println("Titre : ");
                           Scanner scanTitre = new Scanner(System.in);
                           String titre = scanTitre.nextLine();


                           System.out.println("Description : ");
                           Scanner scanDescription = new Scanner(System.in);
                           String description = scanDescription.nextLine();


                           System.out.println("Lieu : ");
                           Scanner scanLieu = new Scanner(System.in);
                           String lieu = scanLieu.nextLine();


                           System.out.println("Date et heure de l'événement (format éxigé : AAAA-MM-JJTHH:MM)!N'oubliez pas le 'T': ");
                           Scanner scanDatEvent = new Scanner(System.in);
                           LocalDateTime datEvent = LocalDateTime.parse(scanDatEvent.nextLine());


                           System.out.println("Prix du billet : ");
                           Scanner scanPricEvent = new Scanner(System.in);
                           double pricEvent = scanPricEvent.nextDouble();


                           System.out.println("Nombre de place total : ");
                           Scanner scanPlaceTotalEvent = new Scanner(System.in);
                           int placeTotalEvent = scanPlaceTotalEvent.nextInt();

                           Status status = new Status();

                           Event event = new Event(titre, description, lieu, datEvent, pricEvent, placeTotalEvent, status, connectedUser);
                           EventManager.registerEvent(event);

                           break;

                       case 3:

                           System.out.println("Mes Evénements");


                           List<Event> eventbyUser = EventManager.getEventByUser(connectedUser);

                           System.out.println("Liste des Evenements !");

                           for (Event eventUser : eventbyUser) {

                               System.out.println("=====================================");

                               System.out.println("ID Event : " + eventUser.getIdEvent());
                               System.out.println("Titre : " + eventUser.getTitlEvent());
                               System.out.println("Description : " + eventUser.getDescriptionEvent());
                               System.out.println("Lieu : " + eventUser.getLieuEvent());
                               System.out.println("Date et heure : " + eventUser.getDatEvent());
                               System.out.println("Prix : " + eventUser.getPricEvent());
                               System.out.println("Place Disponible : " + eventUser.getPlaceTotalEvent());

                               System.out.println("=====================================");
                           }
                           break;

                       case 4:

                           System.out.println("Mes Réséervation");


                           List<Reservation> reservationbyUser = ReservationManager.getReservationByUser(connectedUser);

                           System.out.println("Liste des Réservations !");

                           for (Reservation reservationUser : reservationbyUser) {

                               System.out.println("=====================================");

                               System.out.println(" User : " + reservationUser.getUserId().getFirstName());
                               System.out.println("Titre Event : " + reservationUser.getEventId().getTitlEvent());
                               System.out.println("Nombre de place réservé : " + reservationUser.getNombrePlaces());
                               System.out.println("Prix Total de la réservation : " + reservationUser.getTotalPrice());
                               System.out.println("Status de la réservation : " + reservationUser.getStatusReservation().getNameStatus());


                               System.out.println("=====================================");
                           }
                           break;

                       case 5:

                           System.out.println("Déconnexion");

                            break;

                       default:

                           System.out.println("Mauvaise saisie !");
                   }


               }else {

                   System.out.println("L'user n'existe pas !!");
               }



                break;

            default:

                System.out.println("Saisie incorrecte !!");



        }


    }
}