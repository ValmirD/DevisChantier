package db.db;

import db.exception.DevisChantierDbException;

/**
 * Classe d'accès au gestionnaire de persistance pour les Séquences
 */
public class SequenceDB {

    static final String CAMION = "CAMION";
    static final String CAMIONDUCHANTIER = "CAMIONDUCHANTIER";
    static final String CHANTIER = "CHANTIER";
    static final String CLIENT = "CLIENT";
    static final String CODEREFERENCE = "CODEREFERENCE";
    static final String CODEREFERENCEDUCHANTIER = "CODEREFERENCEDUCHANTIER";
    static final String CONDUCTEUR = "CONDUCTEUR";
    static final String CONDUCTEURDUCHANTIER = "CONDUCTEURDUCHANTIER";
    static final String DEVIS = "DEVIS";
    static final String ENGIN = "ENGIN";
    static final String ENGINDUCHANTIER = "ENGINDUCHANTIER";
    static final String MATERIAU = "MATERIAU";
    static final String MATERIAUDUCHANTIER = "MATERIAUDUCHANTIER";
    static final String OUVRIER = "OUVRIER";
    static final String OUVRIERDUCHANTIER = "OUVRIERDUCHANTIER";
    static final String PATRON = "PATRON";
    static final String PETITMATERIEL = "PETITMATERIEL";
    static final String PETITMATERIELDUCHANTIER = "PETITMATERIELDUCHANTIER";
    static final String SEQUENCES = "SEQUENCES";
    static final String VOITURE = "VOITURE";
    static final String VOITUREDUCHANTIER = "VOITUREDUCHANTIER";

    static synchronized int getNextNum(String sequence) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            String query = "Update SEQUENCES set sValue = sValue+1 where id='" + sequence + "'";
            java.sql.PreparedStatement update = connexion.prepareStatement(query);
            update.execute();
            java.sql.Statement stmt = connexion.createStatement();
            query = "Select sValue FROM Sequences where id='" + sequence + "'";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("sValue");
                return nvId;
            } else {
                throw new DevisChantierDbException("Nouveau n° de séquence inaccessible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
        }
    }

}
