/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author mootaz
 */
public class Hotel
{
    private int code;
    private String dateReservation;
    private String checkin;
    private String checkout;
    private String nomhotel;
    private String endroit; 
    
    public Hotel(){}

    public Hotel(int code, String dateReservation, String checkin,String checkout, String nomhotel, String endroit) {
        this.code = code;
        this.dateReservation = dateReservation;
        this.checkin = checkin;
        this.checkout = checkout;
        this.nomhotel = nomhotel;
        this.endroit = endroit;
    }

    public Hotel(String dateReservation, String checkin, String checkout,String nomhotel, String endroit) {
        this.dateReservation = dateReservation;
        this.checkin = checkin;
        this.checkout = checkout;
        this.nomhotel = nomhotel;
        this.endroit = endroit;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getNomhotel() {
        return nomhotel;
    }

    public void setNomhotel(String nomhotel) {
        this.nomhotel = nomhotel;
    }

    public String getEndroit() {
        return endroit;
    }

    public void setEndroit(String endroit) {
        this.endroit = endroit;
    }

    @Override
    public String toString() {
        return "Hotel{" + "dateReservation=" + dateReservation + ", checkin=" + checkin + ", checkout=" + checkout + ", nomhotel=" + nomhotel + ", endroit=" + endroit + '}';
    }

   

   

   



   
}
    

