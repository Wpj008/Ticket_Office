package org.example;

import model.Event;
import model.Status;
import model.User;
import sql.bdd.DatabaseConnection;
import sql.bdd.EventManager;
import sql.bdd.UserManager;

import java.sql.Connection;
import java.time.LocalDateTime;
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

                        Event eventList = EventManager.getEvent();

                           System.out.println("Liste des Evenements !");

                           System.out.println("Titre : " + eventList.getTitlEvent());
                           System.out.println("Description : " + eventList.getDescriptionEvent());
                           System.out.println("Lieu : " + eventList.getLieuEvent());
                           System.out.println("Date et heure : " + eventList.getDatEvent());
                           System.out.println("Prix : " + eventList.getPricEvent());
                           System.out.println("Place Disponible : " + eventList.getPlaceTotalEvent());


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


                           Event eventListByUser = EventManager.getEvent();

                           System.out.println("Liste des Evenements !");

                           System.out.println("Titre : " + eventListByUser.getTitlEvent());
                           System.out.println("Description : " + eventListByUser.getDescriptionEvent());
                           System.out.println("Lieu : " + eventListByUser.getLieuEvent());
                           System.out.println("Date et heure : " + eventListByUser.getDatEvent());
                           System.out.println("Prix : " + eventListByUser.getPricEvent());
                           System.out.println("Place Disponible : " + eventListByUser.getPlaceTotalEvent());

                           break;

                       case 4:

                           System.out.println("Mes Réséervation");

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