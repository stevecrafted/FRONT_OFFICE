package org.example.Service;

import org.example.DAO.ReservationDAO;
import org.example.Model.Reservation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    
    private ReservationDAO reservationDAO;
    
    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }
    
    /**
     * Récupère la liste des réservations pour une date donnée
     * @param date La date à rechercher
     * @return Liste des réservations pour cette date
     */
    public List<Reservation> getListReservationByDate(Date date) {
        if (date == null) {
            System.err.println("La date ne peut pas être null");
            return new ArrayList<>();
        }
        
        System.out.println(" Recherche des réservations pour la date : " + date);
        return reservationDAO.findByDate(date);
    }
}