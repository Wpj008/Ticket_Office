package model;

import java.time.LocalDateTime;

public class User {

    private long idUser;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;


    public User(String lastName, String firstName, String email, String password, Role role) {

       this.lastName = lastName;
       this.firstName =firstName;
       this.email = email;
       this.password = password;
       this.role = role;

    }

    public User() {

    }


    public String getLastName(){

        return lastName;
    }


    public String getFirstName(){

        return firstName;
    }

    public String getEmail(){

        return email;
    }

    public Role getRole(){

        return role;
    }

    public void setLastName(String lastName){

      this.lastName = lastName;
    }

    public void setFirstName(String firstName){

         this.firstName = firstName;
    }
    public void setEmail(String email) {

         this.email = email;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
