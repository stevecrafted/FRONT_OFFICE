package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.Entity.HttpMethod;
import org.Entity.ModelView;
import org.annotation.AnnotationContoller;
import org.annotation.AnnotationMethode;
import org.annotation.AnnotationRequestParam;
import org.annotation.GetMapping;
import org.annotation.GigaSession;
import org.annotation.Json;
import org.annotation.PostMapping;
import org.annotation.RequestMapping;
import org.annotation.security.Authorized;
import org.annotation.security.Role;
import org.example.Model.Employe;
import org.example.Model.User;

@AnnotationContoller()
public class Demo {

    // Sprint 6
    @AnnotationMethode(value = "/sprintSix")
    public ModelView getUser() {
        ModelView view = new ModelView();
        view.setView("user.jsp");
        return view;
    }

    // Sprint 6 bis
    // localhost:8080/test/user?id=45&age=22
    // Na koa le id post avy any am formulaire
    @AnnotationMethode(value = "/SprintSixBis")
    public String getUserString(String id, @AnnotationRequestParam("age") int agee) {
        return "User id is: " + id + " and age is: " + agee;
    }

    // Sprint 6 ter
    // localhost:8080/test/user/45/note/1
    @AnnotationMethode(value = "sprintSixTer/user/{id}/note/{idSemester}")
    public String getUserString(int idSemester, int id) {
        return "User id is: " + id + " semester " + idSemester;
    }

    /*
     * Eto zany id na avy any anaty get?id=zavatra
     * idSemester avy ao amle url /{idSemester}
     * age request Param
     */
    @AnnotationMethode(value = "/user/semester/{idSemester}")
    public ModelView anotherDemo(Integer id, Integer idSemester, @AnnotationRequestParam("age") Integer agee) {
        ModelView view = new ModelView();
        view.addAttribute("age", agee);
        view.addAttribute("id", id);

        view.setView("anotherDemo.jsp");
        return view;
    }
    // Tests supplémentaires pour le binding des path variables

    // 1) Binding explicite via annotation
    @AnnotationMethode(value = "/user/{id}/detail")
    public String getUserDetailAnnotated(int id) {
        return "Annotated path id: " + id;
    }

    // 2) Binding par nom (requiert javac -parameters pour fonctionner sans
    // annotation)
    @AnnotationMethode(value = "/userByName/{name}")
    public String getUserByName(String name) {
        return "Name: " + name;
    }

    // 3) Binding par position (ordre des paramètres correspond à l'ordre des { }
    // dans l'URL)
    @AnnotationMethode(value = "/userPos/{x}/{y}")
    public String getUserPos(String x, String y) {
        return "Pos x: " + x + " y: " + y;
    }

    // 4) Signature avec ordre inversé (teste comportement si nom non disponible)
    @AnnotationMethode(value = "/userPosReversed/{x}/{y}")
    public String getUserPosReversed(String y, String x) {
        return "Reversed x: " + x + " y: " + y;
    }

    // 5) Mix path variable + query param
    @AnnotationMethode(value = "/userMixed/{id}")
    public String getUserMixed(int id, @AnnotationRequestParam("age") int agee) {
        return "mixed id: " + id + " age: " + agee;
    }

    // 6) ModelView retournant un attribute issu d'un path variable
    @AnnotationMethode(value = "/userModel/{id}")
    public ModelView getUserModel(int id) {
        ModelView view = new ModelView();
        view.addAttribute("id", id);
        view.setView("userModel.jsp");
        return view;
    }

    // 7) Conversion de types (int et boolean)
    @AnnotationMethode(value = "/userTypes/{id}/{flag}")
    public String getUserTypes(int id, boolean flag) {
        return "id: " + id + " flag: " + flag;
    }

    // Sprint 7

    /**
     * Exemple avec @GetMapping
     * URL: GET /user/list
     */
    @GetMapping("/user/list")
    public String listUsers() {
        return "Liste des utilisateurs (GET)";
    }

    /**
     * Exemple avec @PostMapping
     * URL: POST /user/create
     */
    @PostMapping("/user/create")
    public String createUser(@AnnotationRequestParam String name, @AnnotationRequestParam String email) {
        return "Utilisateur créé: " + name + " (" + email + ")";
    }

    /**
     * Exemple avec @GetMapping et variables dans l'URL
     * URL: GET /user/{id}
     */
    @GetMapping("/user/{id}")
    public String getUserById(@AnnotationRequestParam String id) {
        return "Détails de l'utilisateur avec ID: " + id;
    }

    /**
     * Exemple avec @PostMapping et variables dans l'URL
     * URL: POST /user/{id}/update
     */
    @PostMapping("/user/{id}/update")
    public String updateUser(@AnnotationRequestParam String id, @AnnotationRequestParam String name) {
        return "Utilisateur " + id + " mis à jour avec le nom: " + name;
    }

    /**
     * Exemple avec @RequestMapping explicite
     * URL: POST /user/save
     */
    @RequestMapping(value = "/user/save", method = HttpMethod.POST)
    public String saveUser(@AnnotationRequestParam String username) {
        return "Utilisateur sauvegardé: " + username;
    }

    /**
     * Exemple avec ModelView et @GetMapping
     * URL: GET /user/profile
     */
    @GetMapping("/user/profile")
    public ModelView getUserProfile() {
        ModelView mv = new ModelView();
        mv.setView("user-profile.jsp");
        mv.addAttribute("username", "JohnDoe");
        mv.addAttribute("email", "john@example.com");
        return mv;
    }

    /**
     * Même URL mais méthode HTTP différente
     * URL: POST /user/profile (pour mettre à jour le profil)
     */
    @PostMapping("/user/profile")
    public String updateUserProfile(@AnnotationRequestParam String username, @AnnotationRequestParam String email) {
        return "Profil mis à jour: " + username + " - " + email;
    }

    // Sprint 8
    @GetMapping("/sprint8")
    public ModelView getUserProfile(Map<String, String> Details) {
        ModelView mv = new ModelView();
        mv.addAttribute("name", Details.get("name"));
        mv.addAttribute("numero", Details.get("numero"));

        mv.setView("user.jsp");
        return mv;
    }

    // Vue sprint 8 bis Tableau Controlleur(Emp[] e)
    @GetMapping("/getViewSprintHuitBisTableau")
    public ModelView getViewSprintHuitBisTableau(Employe[] e) {
        ModelView mv = new ModelView();
        mv.setView("sprint8bisTableau.jsp");

        return mv;
    }

    // Resultat sprint 8 tableau
    @GetMapping("/sprintHuitBisTableau")
    public String getUserProfileTableau(Employe[] e) {

        return Arrays.stream(e)
                .map(Employe::getName)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/getViewSprintHuitBis")
    public ModelView getViewSprintHuitBis() {
        ModelView mv = new ModelView();
        mv.setView("sprint8Bis.jsp");

        return mv;
    }

    @GetMapping("/sprintHuitBis")
    public String sprintHuitBis(Employe e) {
        /*
         * <input type="text" name="e.name">
         * <input type="text" name="e.departement[0].name">
         * <input type="text" name="e.departement[1].name">
         * 
         * <input type="text" name="e.departement[0].manager.name">
         * <input type="text" name="e.departement[1].manager.name">
         */

        String EmpName = e.getName();
        String EmpDepZeroName = e.getDepartement().get(0).getName();
        String EmpDepOneName = e.getDepartement().get(1).getName();
        String EmpDepManagerZeroName = e.getDepartement().get(0).getManager().getName();
        String EmpDepManagerOneName = e.getDepartement().get(1).getManager().getName();

        return "EmpName : " + EmpName + " EmpDepOneName : " + EmpDepZeroName + " EmpDepOneName : " + EmpDepOneName
                + " EmpDepManagerOneName : " + EmpDepManagerOneName + " EmpDepManagerZeroName : "
                + EmpDepManagerZeroName;
    }

    // Sprint 9
    @Json
    @GetMapping("/api/user/{id}")
    public User getUser(int id) {
        return new User(id, "Jean");
    }

    @Json
    @GetMapping("/api/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Jean"));
        users.add(new User(2, "Marie"));
        users.add(new User(3, "Paul"));
        return users;
    }

    @GetMapping("/sprint10")
    public ModelView getViewSprintDix() {
        ModelView mv = new ModelView();
        mv.setView("sprint10.jsp");

        return mv;
    }

    // Sprint 10
    @PostMapping("/upload")
    public String upload(Map<String, byte[]> files) throws IOException {

        Path uploadsDir = Paths.get("uploads");
        if (!Files.exists(uploadsDir)) {
            Files.createDirectories(uploadsDir);
        }

        System.out.println("===== DOSSIER UPLOADS : " + uploadsDir.toAbsolutePath() + " =====");

        for (String key : files.keySet()) {
            byte[] content = files.get(key);

            // Décoder : fieldName::originalFilename
            String[] parts = key.split("::", 2);
            String fieldName = parts[0];
            String filename = parts.length > 1 ? parts[1] : (key + ".bin");

            System.out.println("Field: " + fieldName + " -> Fichier: " + filename);

            Path filePath = uploadsDir.resolve(filename);
            Files.write(filePath, content);

            System.out.println("Fichier sauvegardé : " + filePath.toAbsolutePath());
        }

        return "Upload OK - " + files.size() + " fichier(s) uploadé(s)";
    }

    // -------------------- SPRINT 11 --------------------
    // Définir le rôle de l'utilisateur
    @AnnotationMethode(value = "/auth/set-role/{role}")
    public String registerRole(
            String role,
            @GigaSession Map<String, Object> session) {
        session.put("role", role);
        session.put("user", "connected"); // Marquer comme connecté
        return "Rôle défini : " + role;
    }

    // Accessible uniquement si connecté
    @GetMapping("/auth/authorized")
    @Authorized
    public String authorizedMethod() {
        return "Vous êtes autorisé";
    }

    // Accessible uniquement aux admins
    @GetMapping("/auth/admin")
    @Role("admin")
    public String adminOnly() {
        return "Vous êtes admin !";
    }

    @GetMapping("/auth/logout")
    @Authorized
    public String logout(@GigaSession  Map<String, Object> session) {
        session.clear();

        return "user deconnecte";
    }

}
