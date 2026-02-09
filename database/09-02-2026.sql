-- =============================================
-- SCRIPT DE RÉINITIALISATION DES DONNÉES
-- Date: 09-02-2026
-- =============================================

-- Supprimer les tables existantes
DROP TABLE IF EXISTS reservations CASCADE;
DROP TABLE IF EXISTS hotel CASCADE;

-- Recréer les tables
CREATE TABLE hotel (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(250)
);

CREATE TABLE reservations (
    id SERIAL PRIMARY KEY,
    id_hotel INT NOT NULL,
    id_client VARCHAR(250),
    nbPassager INT NOT NULL,
    date_heure TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_hotel) REFERENCES hotel (id)
);

-- =============================================
-- INSERTION DES HÔTELS
-- =============================================
INSERT INTO hotel (id, nom) VALUES (1, 'Colbert');
INSERT INTO hotel (id, nom) VALUES (2, 'Novotel');
INSERT INTO hotel (id, nom) VALUES (3, 'Ibis');
INSERT INTO hotel (id, nom) VALUES (4, 'Lokanga');

-- Réinitialiser la séquence pour hotel
SELECT setval('hotel_id_seq', 4);

-- =============================================
-- INSERTION DES RÉSERVATIONS
-- =============================================
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (1, 3, '4631', 11, '2026-02-05 00:01:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (2, 3, '4394', 1, '2026-02-05 23:55:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (3, 1, '8054', 2, '2026-02-09 10:17:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (4, 2, '1432', 4, '2026-02-01 15:25:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (5, 1, '7861', 4, '2026-01-28 07:11:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (6, 1, '3308', 5, '2026-01-28 07:45:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (7, 2, '4484', 13, '2026-02-28 08:25:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (8, 2, '9687', 8, '2026-02-28 13:00:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (9, 1, '6302', 7, '2026-02-15 13:00:00');
INSERT INTO reservations (id, id_hotel, id_client, nbPassager, date_heure) VALUES (10, 4, '8640', 1, '2026-02-18 22:55:00');

-- Réinitialiser la séquence pour reservations
SELECT setval('reservations_id_seq', 10);

-- =============================================
-- FIN DU SCRIPT
-- =============================================

