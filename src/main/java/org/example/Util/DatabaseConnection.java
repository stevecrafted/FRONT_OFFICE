package org.example.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/framework_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "steve"; // À modifier
    
    // Charger le driver une seule fois
    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ Driver PostgreSQL chargé");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver PostgreSQL non trouvé");
            e.printStackTrace();
        }
    }

    /**
     * Crée une NOUVELLE connexion à chaque appel
     * Cette connexion DOIT être fermée après utilisation
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Nouvelle connexion PostgreSQL créée");
            return connection;
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion à la base de données");
            throw e;
        }
    }
}