package com.coiffeur.rdv.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "hairdresser")
public class HairDresser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long HairDresserId;

    @Column(name = "firstname")
    private String FirstName;

    @Column(name = "lastname")
    private String LastName;

    public HairDresser() {}

    public HairDresser(Long HairDresserId, String FirstName, String LastName) {
        this.HairDresserId = HairDresserId;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public HairDresser(String FirstName, String LastName){
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public Long getHairDresserId() {
        return HairDresserId;
    }

    public void setHairDresserId(Long hairDresserId) {
        HairDresserId = hairDresserId;
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
}
