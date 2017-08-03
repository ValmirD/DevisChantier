package db.db;

import db.exception.DevisChantierDbException;

/**
 * Classe d'accès au gestionnaire de persistance pour les Séquences
 */
public class SequenceDB {

    static final String CAMION = "CAMION";
    static final String CAMION_DU_CHANTIER = "CAMION_DU_CHANTIER";
    static final String CHANTIER = "CHANTIER";
    static final String CLIENT = "CLIENT";
    static final String CODE_REFERENCE = "CODE_REFERENCE";
    static final String CODE_REFERENCE_DU_CHANTIER = "CODE_REFERENCE_DU_CHANTIER";
    static final String CONDUCTEUR = "CONDUCTEUR";
    static final String CONDUCTEUR_DU_CHANTIER = "CONDUCTEUR_DU_CHANTIER";
    static final String DEVIS = "DEVIS";
    static final String ENGIN = "ENGIN";
    static final String ENGIN_DU_CHANTIER = "ENGIN_DU_CHANTIER";
    static final String MATERIAU = "MATERIAU";
    static final String MATERIAU_DU_CHANTIER = "MATERIAU_DU_CHANTIER";
    static final String OUVRIER = "OUVRIER";
    static final String OUVRIER_DU_CHANTIER = "OUVRIER_DU_CHANTIER";
    static final String PATRON = "PATRON";
    static final String PETIT_MATERIEL = "PETIT_MATERIEL";
    static final String PETIT_MATERIEL_DU_CHANTIER = "PETIT_MATERIEL_DU_CHANTIER";
    static final String SEQUENCES = "SEQUENCES";
    static final String VOITURE = "VOITURE";
    static final String VOITURE_DU_CHANTIER = "VOITURE_DU_CHANTIER";

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
