package Entities;

import com.sun.nio.sctp.AbstractNotificationHandler;

public class Employee {
    /*
    "id":"1",
         "firstName":"Tom",
         "lastName":"Cruise",
         "photo": "https://jsonformatter.org/img/tom-cruise.jpg"
     */

    private String id;
    private String firstName;
    private String lastName;
    private String photo;

    public Employee(String id, String firstName, String lastName, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
