package model;

import java.time.LocalDateTime;

public class Event {

   private long idEvent;
    private String titlEvent;
    private String descriptionEvent;
    private String lieuEvent;
    private LocalDateTime datEvent;
    private double pricEvent;
    private int placeTotalEvent;
    private Status statusEvent;
    private User organizerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Event(){

    }


    public Event(String titlEvent, String descriptionEvent, String lieuEvent, LocalDateTime datEvent, double pricEvent, int placeTotalEvent, Status statusEvent, User organizerId) {

        this.titlEvent = titlEvent;
        this.descriptionEvent = descriptionEvent;
        this.lieuEvent = lieuEvent;
        this.datEvent = datEvent;
        this.pricEvent = pricEvent;
        this.placeTotalEvent = placeTotalEvent;
        this.statusEvent = statusEvent;
        this.organizerId = organizerId;
    }
    public long getIdEvent() {
        return idEvent;
    }

    public String getTitlEvent() {
        return titlEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public LocalDateTime getDatEvent() {
        return datEvent;
    }

    public double getPricEvent() {
        return pricEvent;
    }

    public int getPlaceTotalEvent() {
        return placeTotalEvent;
    }

    public Status getStatusEvent() {
        return statusEvent;
    }

    public User getOrganizerId() {
        return organizerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }



    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public void setTitlEvent(String titlEvent) {
        this.titlEvent = titlEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public void setDatEvent(LocalDateTime datEvent) {
        this.datEvent = datEvent;
    }

    public void setPricEvent(double pricEvent) {
        this.pricEvent = pricEvent;
    }

    public void setPlaceTotalEvent(int placeTotalEvent) {
        this.placeTotalEvent = placeTotalEvent;
    }

    public void setStatusEvent(Status statusEvent) {
        this.statusEvent = statusEvent;
    }

    public void setOrganizerId(User organizerId) {
        this.organizerId = organizerId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



}
