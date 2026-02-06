package org.example.Controlleur;

import org.annotation.*;
import org.example.DAO.ReservationDAO;
import org.example.DAO.HotelDAO;
import org.example.Model.Reservation;
import org.example.Model.Hotel;
import org.Entity.ModelView;

import java.util.List;

@AnnotationContoller
public class ReservationController {

    private ReservationDAO reservationDAO = new ReservationDAO();
    private HotelDAO hotelDAO = new HotelDAO();

    // ========== LISTE DES RESERVATIONS ==========
    @GetMapping("/reservations")
    public ModelView listeReservations() {
        ModelView mv = new ModelView();
        mv.setView("reservations/liste.jsp");
        
        
        List<Reservation> reservations = reservationDAO.findAll();
        mv.addAttribute("reservations", reservations);
        
        return mv;
    }

    // ========== FORMULAIRE DE CRÉATION ==========
    @GetMapping("/reservations/nouveau")
    public ModelView nouvelleReservation() {
        ModelView mv = new ModelView();
        mv.setView("reservations/form.jsp");
        mv.addAttribute("action", "create");
        
        // Liste des hotels pour le formulaire
        List<Hotel> hotels = hotelDAO.findAll();
        mv.addAttribute("hotels", hotels);
        
        return mv;
    }

    // ========== CRÉER UNE RESERVATION ==========
    @PostMapping("/reservations/create")
    public ModelView creerReservation(
        @AnnotationRequestParam("idHotel") int idHotel,
        @AnnotationRequestParam("idClient") String idClient,
        @AnnotationRequestParam("nbPassager") int nbPassager
    ) {
        Reservation reservation = new Reservation(idHotel, idClient, nbPassager);
        
        ModelView mv = new ModelView();
        
        if (reservationDAO.create(reservation)) {
            mv.setView("redirect:/reservations");
            mv.addAttribute("message", "Réservation créée avec succès");
        } else {
            mv.setView("reservations/form.jsp");
            mv.addAttribute("error", "Erreur lors de la création");
            mv.addAttribute("reservation", reservation);
            
            // Recharger la liste des hotels
            List<Hotel> hotels = hotelDAO.findAll();
            mv.addAttribute("hotels", hotels);
        }
        
        return mv;
    }
 
}