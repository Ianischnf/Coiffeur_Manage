package com.coiffeur.rdv.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hairdresser")
public class HairDresser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String FirstName;

    @Column(name = "lastname")
    private String LastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String Password;


    @OneToMany(mappedBy = "hairdresser", cascade = CascadeType.ALL, orphanRemoval = true) //Supprimer les RDV en cascades lors de la suppression d'un coiffeur
    private List<Appointment> appointments;


    public HairDresser() {}

    public HairDresser(Long id, String FirstName, String LastName, String Email, String Password) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = Email;
        this.Password = Password;
    }

    public HairDresser(String FirstName, String LastName, String Email, String Password ){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = Email;
        this.Password = Password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long hairDresserId) {
        id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }


}
