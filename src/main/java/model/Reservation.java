package model;

import java.time.LocalDateTime;

public class Reservation {

   private long idReservation;
   private User userId;
   private Event eventId;
   private int  nombrePlaces;
   private double totalPrice;
   private LocalDateTime reservationDate;
   private Status statusReservation;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;

   public Reservation(){

   }

   public Reservation(User userId, Event eventId, int nombrePlaces,double totalPrice, Status statusReservation){

       this.userId = userId;
       this.eventId = eventId;
       this.nombrePlaces = nombrePlaces;
       this.totalPrice = totalPrice;
       this.statusReservation = statusReservation;

   }

    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Status getStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(Status statusReservation) {
        this.statusReservation = statusReservation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
