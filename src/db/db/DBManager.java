/*
 * DBManager.java
 */
package db.db;

import db.exception.DevisChantierDbException;
import java.sql.*;
/**
 * Offre les outils de connexion et de gestion de transaction. 
 */
public class DBManager {

    private static Connection connection;

    /**
     * Retourne la connexion établie ou à défaut, l'établit
     * @return la connexion établie.
     * @throws db.DevisChantierDbException
     */
    public static Connection getConnection() throws DevisChantierDbException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/DevisChantier", "root", "root");
            } catch (SQLException ex) {
                throw new DevisChantierDbException("Connexion impossible: " + ex.getMessage());
            }
        }
        return connection;
    }

    /**
     * Débute une transaction
     * @throws db.DevisChantierDbException
     */
    public static void startTransaction() throws DevisChantierDbException {
        try {

            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new DevisChantierDbException("Impossible de démarrer une transaction: "+ex.getMessage());
        }
    }

    /**
     * Débute une transaction en spécifiant son niveau d'isolation
     * @throws db.DevisChantierDbException
     */
    public static void startTransaction(int isolationLevel) throws DevisChantierDbException {
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new DevisChantierDbException("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
            throw new DevisChantierDbException("Impossible de démarrer une transaction: "+ex.getMessage());
        }
    }

    /**
     * Valide la transaction courante
     * @throws db.DevisChantierDbException
     */
    public static void validateTransaction() throws DevisChantierDbException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new DevisChantierDbException("Impossible de valider la transaction: "+ex.getMessage());
        }
    }

    /**
     * Annule la transaction courante
     * @throws db.DevisChantierDbException
     */
    public static void cancelTransaction() throws DevisChantierDbException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new DevisChantierDbException("Impossible d'annuler la transaction: "+ex.getMessage());
        }
    }
}
