/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import operator.entity.DocumentRequest;

/**
 *
 * @author Bogdan
 */
public class RequestFormData {
    public static final String STATUS_CREATED = "Kreiran";
    public static final String STATUS_IN_PRODUCTION = "U produkciji";
    public static final String STATUS_WAITING_FOR_DELIVERY = "Ceka na urucenje";
    public static final String STATUS_DELIVERED = "Urucen";
    
    private String jmbg;
    private String name;
    private String surname;
    private String nameOfMother;
    private String surnameOfMother;
    private String nameOfFather;
    private String surnameOfFather;
    private String gender;
    private String dateOfBirth;
    private String nationality;
    private String profession;
    private String maritalStatus;
    private String municipality;
    private String street;
    private String streetNumber;
    
    private final String status;
    
    public RequestFormData(String status) {
        this.status = status;
    }
    
    public boolean checkData() {
        if (jmbg == null
                || name == null
                || surname == null
                || nameOfMother == null
                || surnameOfMother == null
                || nameOfFather == null
                || surnameOfFather == null
                || gender == null
                || dateOfBirth == null
                || nationality == null
                || profession == null
                || maritalStatus == null
                || municipality == null
                || street == null
                || streetNumber == null) {
            return false;
        }
        
        if (jmbg.length() != 13) {
            return false;
        }
        
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        } catch (ParseException ex) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "RequestFormData{" + "jmbg=" + jmbg + ", name=" + name + ", surname=" + surname + ", nameOfMother=" + nameOfMother + ", surnameOfMother=" + surnameOfMother + ", nameOfFather=" + nameOfFather + ", surnameOfFather=" + surnameOfFather + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality + ", profession=" + profession + ", maritalStatus=" + maritalStatus + ", municipality=" + municipality + ", street=" + street + ", streetNumber=" + streetNumber + ", status=" + status + '}';
    }
    
    public DocumentRequest toDocumentRequest() {
        DocumentRequest docReq = new DocumentRequest();
        docReq.setIme(name);
        docReq.setPrezime(surname);
        docReq.setImeMajke(nameOfMother);
        docReq.setPrezimeMajke(surnameOfMother);
        docReq.setImeOca(nameOfFather);
        docReq.setPrezimeOca(surnameOfFather);
        docReq.setBracnoStanje(maritalStatus);
        docReq.setBrojPrebivalista(streetNumber);
        docReq.setDatumRodjenja(dateOfBirth);
        docReq.setJmbg(jmbg);
        docReq.setNacionalnost(nationality);
        docReq.setOpstinaPrebivalista(municipality);
        docReq.setPol(gender);
        docReq.setProfesija(profession);
        docReq.setUlicaPrebivalista(street);
        docReq.setStatus(status);
        return docReq;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNameOfMother(String nameOfMother) {
        this.nameOfMother = nameOfMother;
    }

    public void setSurnameOfMother(String surnameOfMother) {
        this.surnameOfMother = surnameOfMother;
    }

    public void setNameOfFather(String nameOfFather) {
        this.nameOfFather = nameOfFather;
    }

    public void setSurnameOfFather(String surnameOfFather) {
        this.surnameOfFather = surnameOfFather;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    
}
