package org.example.Controlleur;

import org.annotation.*;
import org.example.DAO.HotelDAO;
import org.example.Model.Hotel;
import org.Entity.ModelView;

import java.util.List;

@AnnotationContoller
public class HotelController {

    private HotelDAO hotelDAO = new HotelDAO();

    // ========== LISTE DES HOTELS ==========
    @GetMapping("/hotels")
    public ModelView listeHotels() {
        ModelView mv = new ModelView();
        mv.setView("hotels/liste.jsp");
        
        List<Hotel> hotels = hotelDAO.findAll();
        mv.addAttribute("hotels", hotels);
        
        return mv;
    }
    
}