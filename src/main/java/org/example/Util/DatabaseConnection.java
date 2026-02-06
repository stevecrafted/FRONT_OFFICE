package org.example.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ Driver PostgreSQL chargé");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver PostgreSQL non trouvé");
        }
    }

    public static Connection getConnection() throws SQLException {
        // FORCER l'URL Docker
        // String url = "jdbc:postgresql://postgres:5432/framework_test";
        // String user = "postgres";
        // String password = "steve";

        String url = "jdbc:postgresql://localhost:5432/framework_test";
        String user = "postgres";
        String password = "steve";
 

        System.out.println("==========================================");
        System.out.println(" CONNEXION DOCKER FORCÉE:");
        System.out.println("URL: " + url);
        System.out.println("User: " + user);
        System.out.println("==========================================");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ CONNEXION RÉUSSIE À POSTGRES!");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ ÉCHEC CONNEXION: " + e.getMessage());
            System.err.println("Vérifiez que:");
            System.err.println("1. Le service 'postgres' tourne dans Docker");
            System.err.println("2. La base 'framework_test' existe");
            System.err.println("3. L'utilisateur 'postgres' a le mot de passe 'steve'");
            throw e;
        }
    }
}