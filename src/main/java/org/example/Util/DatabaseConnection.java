package org.example.Util;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/framework_test";
    private static final String DEFAULT_USER = "postgres";
    private static final String DEFAULT_PASSWORD = "steve"; // A modifier

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        DbConfig config = resolveConfig();
        URL = config.url;
        USER = config.user;
        PASSWORD = config.password;
    }
    
    // Charger le driver une seule fois
    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver PostgreSQL charge");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL non trouve");
            e.printStackTrace();
        }
    }

    /**
     * Cree une NOUVELLE connexion a chaque appel
     * Cette connexion DOIT etre fermee apres utilisation
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Nouvelle connexion PostgreSQL creee");
            return connection;
        } catch (SQLException e) {
            System.err.println("Erreur de connexion a la base de donnees");
            throw e;
        }
    }

    private static DbConfig resolveConfig() {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null && !databaseUrl.trim().isEmpty()) {
            try {
                URI uri = new URI(databaseUrl);
                String userInfo = uri.getUserInfo();
                String user = "";
                String password = "";
                if (userInfo != null) {
                    String[] parts = userInfo.split(":", 2);
                    user = parts.length > 0 ? parts[0] : "";
                    password = parts.length > 1 ? parts[1] : "";
                }
                String host = uri.getHost();
                int port = uri.getPort() == -1 ? 5432 : uri.getPort();
                String db = uri.getPath() != null ? uri.getPath().replaceFirst("^/", "") : "";
                String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + db;

                return new DbConfig(jdbcUrl, user, password);
            } catch (Exception e) {
                System.err.println("DATABASE_URL invalide, utilisation de la configuration par defaut");
            }
        }

        String jdbcUrl = envOrDefault("JDBC_DATABASE_URL", DEFAULT_URL);
        String user = envOrDefault("DB_USER", DEFAULT_USER);
        String password = envOrDefault("DB_PASSWORD", DEFAULT_PASSWORD);
        return new DbConfig(jdbcUrl, user, password);
    }

    private static String envOrDefault(String name, String defaultValue) {
        String value = System.getenv(name);
        return (value == null || value.trim().isEmpty()) ? defaultValue : value;
    }

    private static class DbConfig {
        final String url;
        final String user;
        final String password;

        DbConfig(String url, String user, String password) {
            this.url = url;
            this.user = user;
            this.password = password;
        }
    }
}
