package org.example.Controlleur;

import org.annotation.*;
import org.example.DAO.UtilisateurDAO;
import org.example.Model.Utilisateur;
import org.Entity.ModelView;

import java.util.List;

@AnnotationContoller
public class UtilisateurController {

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    // ========== LISTE DES UTILISATEURS ==========
    @GetMapping("/utilisateurs")
    public ModelView listeUtilisateurs() {
        ModelView mv = new ModelView();
        mv.setView("utilisateurs/liste.jsp");
        
        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();
        mv.addAttribute("utilisateurs", utilisateurs);
        
        return mv;
    }

    // ========== FORMULAIRE DE CRÉATION ==========
    @GetMapping("/utilisateurs/nouveau")
    public ModelView nouveauUtilisateur() {
        ModelView mv = new ModelView();
        mv.setView("utilisateurs/form.jsp");
        mv.addAttribute("action", "create");
        return mv;
    }

    // ========== CRÉER UN UTILISATEUR ==========
    @PostMapping("/utilisateurs/create")
    public ModelView creerUtilisateur(
        @AnnotationRequestParam("nom") String nom,
        @AnnotationRequestParam("prenom") String prenom,
        @AnnotationRequestParam("email") String email,
        @AnnotationRequestParam("motDePasse") String motDePasse,
        @AnnotationRequestParam("role") String role
    ) {
        Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse, role);
        
        ModelView mv = new ModelView();
        
        if (utilisateurDAO.create(utilisateur)) {
            mv.setView("redirect:/utilisateurs");
            mv.addAttribute("message", "Utilisateur créé avec succès");
        } else {
            mv.setView("utilisateurs/form.jsp");
            mv.addAttribute("error", "Erreur lors de la création");
            mv.addAttribute("utilisateur", utilisateur);
        }
        
        return mv;
    }

    // ========== VOIR UN UTILISATEUR ==========
    @GetMapping("/utilisateurs/{id}")
    public ModelView voirUtilisateur(int id) {
        ModelView mv = new ModelView();
        
        Utilisateur utilisateur = utilisateurDAO.findById(id);
        
        if (utilisateur != null) {
            mv.setView("utilisateurs/detail.jsp");
            mv.addAttribute("utilisateur", utilisateur);
        } else {
            mv.setView("error.jsp");
            mv.addAttribute("message", "Utilisateur non trouvé");
        }
        
        return mv;
    }

    // ========== FORMULAIRE DE MODIFICATION ==========
    @GetMapping("/utilisateurs/{id}/edit")
    public ModelView editerUtilisateur(int id) {
        ModelView mv = new ModelView();
        
        Utilisateur utilisateur = utilisateurDAO.findById(id);
        
        if (utilisateur != null) {
            mv.setView("utilisateurs/form.jsp");
            mv.addAttribute("action", "update");
            mv.addAttribute("utilisateur", utilisateur);
        } else {
            mv.setView("error.jsp");
            mv.addAttribute("message", "Utilisateur non trouvé");
        }
        
        return mv;
    }

    // ========== METTRE À JOUR UN UTILISATEUR ==========
    @PostMapping("/utilisateurs/update")
    public ModelView mettreAJourUtilisateur(
        @AnnotationRequestParam("id") int id,
        @AnnotationRequestParam("nom") String nom,
        @AnnotationRequestParam("prenom") String prenom,
        @AnnotationRequestParam("email") String email,
        @AnnotationRequestParam("motDePasse") String motDePasse,
        @AnnotationRequestParam("role") String role
    ) {
        Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse, role);
        utilisateur.setId(id);
        
        ModelView mv = new ModelView();
        
        if (utilisateurDAO.update(utilisateur)) {
            mv.setView("redirect:/utilisateurs");
            mv.addAttribute("message", "Utilisateur mis à jour");
        } else {
            mv.setView("utilisateurs/form.jsp");
            mv.addAttribute("error", "Erreur lors de la mise à jour");
            mv.addAttribute("utilisateur", utilisateur);
        }
        
        return mv;
    }

    // ========== SUPPRIMER UN UTILISATEUR ==========
    @GetMapping("/utilisateurs/{id}/delete")
    public ModelView supprimerUtilisateur(int id) {
        ModelView mv = new ModelView();
        
        if (utilisateurDAO.delete(id)) {
            mv.setView("redirect:/utilisateurs");
            mv.addAttribute("message", "Utilisateur supprimé");
        } else {
            mv.setView("error.jsp");
            mv.addAttribute("message", "Erreur lors de la suppression");
        }
        
        return mv;
    }
}