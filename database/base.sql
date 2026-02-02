-- Créer la base de données
CREATE DATABASE framework_test;

-- Se connecter à la base
\c framework_test;

-- Créer la table utilisateurs
CREATE TABLE utilisateurs (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'user',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insérer quelques données de test
INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, role) VALUES
('Dupont', 'Jean', 'jean.dupont@email.com', 'password123', 'admin'),
('Martin', 'Marie', 'marie.martin@email.com', 'password123', 'user'),
('Bernard', 'Pierre', 'pierre.bernard@email.com', 'password123', 'user');

