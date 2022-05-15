/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Achraf
 */
public class Vol
{
    private int id;
    private String datedepart;
    private String datearrive;
    private String destination;
    private int nbrplace; 
    private String heuredepart;
    private String heurearrive;
    private String nom;

    public Vol(int id, String datedepart, String datearrive, String destination, int nbrplace, String heuredepart, String heurearrive, String nom) {
        this.id = id;
        this.datedepart = datedepart;
        this.datearrive = datearrive;
        this.destination = destination;
        this.nbrplace = nbrplace;
        this.heuredepart = heuredepart;
        this.heurearrive = heurearrive;
        this.nom = nom;
    }

    public Vol(String datedepart, String datearrive, String destination, int nbrplace, String heuredepart, String heurearrive, String nom) {
        this.datedepart = datedepart;
        this.datearrive = datearrive;
        this.destination = destination;
        this.nbrplace = nbrplace;
        this.heuredepart = heuredepart;
        this.heurearrive = heurearrive;
        this.nom = nom;
    }

    public Vol() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(String datedepart) {
        this.datedepart = datedepart;
    }

    public String getDatearrive() {
        return datearrive;
    }

    public void setDatearrive(String datearrive) {
        this.datearrive = datearrive;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(String heuredepart) {
        this.heuredepart = heuredepart;
    }

    public String getHeurearrive() {
        return heurearrive;
    }

    public void setHeurearrive(String heurearrive) {
        this.heurearrive = heurearrive;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Vol{" + "id=" + id + ", datedepart=" + datedepart + ", datearrive=" + datearrive + ", destination=" + destination + ", nbrplace=" + nbrplace + ", heuredepart=" + heuredepart + ", heurearrive=" + heurearrive + ", nom=" + nom + '}';
    }
    
    
  
  
    
}
