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
            throw new RuntimeException("Driver PostgreSQL requis", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // ⚠️ IMPORTANT : À externaliser en production
        // Configuration pour Supavisor (Connection Pooling)
        String host = "aws-1-eu-west-1.pooler.supabase.com";
        String port = "6543"; // Port spécifique au pooler
        String database = "postgres";
        String user = "postgres.xgkghnstlcghlcctxgiw"; // Format spécifique à Supavisor
        String password = "nomenjanahary"; // Remplacez par votre mot de passe
        
        // Construction de l'URL avec paramètres SSL
        String url = String.format(
            "jdbc:postgresql://%s:%s/%s?ssl=true&sslmode=require",
            host, port, database
        );

        System.out.println("==========================================");
        System.out.println(" TENTATIVE DE CONNEXION À SUPABASE:");
        System.out.println("Mode: Connection Pooling (Supavisor)");
        System.out.println("URL: " + url);
        System.out.println("User: " + user);
        System.out.println("Host: " + host);
        System.out.println("Port: " + port);
        System.out.println("==========================================");

        try {
            // Note: DriverManager.getConnection(String url, String user, String password)
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ CONNEXION RÉUSSIE À SUPABASE!");
            
            // Vérification supplémentaire
            System.out.println("Mode de connexion: Supavisor (Session Pooling)");
            
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ ÉCHEC CONNEXION À SUPABASE: " + e.getMessage());
            System.err.println("Détails de l'erreur:");
            System.err.println("1. Code d'erreur: " + e.getErrorCode());
            System.err.println("2. État SQL: " + e.getSQLState());
            
            System.err.println("\nVérifiez que:");
            System.err.println("1. Le mot de passe est correct pour l'utilisateur 'postgres.xgkghnstlcghlcctxgiw'");
            System.err.println("2. Le port 6543 n'est pas bloqué par votre firewall");
            System.err.println("3. Le projet Supabase est actif (non suspendu)");
            System.err.println("4. Vous utilisez bien 'aws-1-eu-west-1.pooler.supabase.com' et non l'ancienne URL");
            System.err.println("5. Le format d'utilisateur 'postgres.votre-ref' est correct");
            System.err.println();
            throw e;
        }
    }
   
}