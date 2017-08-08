/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.PetitMaterielDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.PetitMaterielDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class PetitMaterielDuChantierDB {

    public static List<PetitMaterielDuChantierDto> getAllPetitMaterielDuChantier() throws DevisChantierDbException {
        List<PetitMaterielDuChantierDto> elements = getCollection(new PetitMaterielDuChantierSel(0));
        return elements;
    }

    public static List<PetitMaterielDuChantierDto> getCollection(PetitMaterielDuChantierSel sel) throws DevisChantierDbException {
        List<PetitMaterielDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idPetitMaterielDuChantier, idChantier, idPetitMateriel, debutDisponibilite, finDisponibilite, quantite  FROM PetitMaterielDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getidPetitMaterielDuChantier() != 0) {
                where = where + " idPetitMaterielDuChantier = ? ";
            }
                        
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getidPetitMaterielDuChantier() != 0) {
                    stmt.setInt(i, sel.getidPetitMaterielDuChantier());
                    i++;
                }

            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PetitMaterielDuChantierDto(
                        rs.getInt("idPetitMaterielDuChantier"), 
                        rs.getDouble("quantite"), 
                        rs.getDate("debutDisponibilite"), 
                        rs.getDate("finDisponibilite"),
                        rs.getInt("idChantier"),
                        rs.getInt("idPetitMateriel")                     
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de PetitMaterielDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from PetitMaterielDuChantier where idPetitMaterielDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("PetitMaterielDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(PetitMaterielDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update PetitMaterielDuChantier set "
                    + "idChantier=? "
                    + "idPetitMateriel=? "
                    + "debutDisponibilite=? "
                    + "finDisponibilite=? "
                    + "quantite=? "
                    + "where idPetitMaterielDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdPetitMateriel());
            update.setDate(3, el.getDebutDisponibilite());
            update.setDate(4, el.getFinDisponibilite());
            update.setDouble(5, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("PetitMaterielDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(PetitMaterielDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.PETITMATERIELDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into PetitMaterielDuChantier(idPetitMaterielDuChantier, idChantier, idPetitMateriel, debutDisponibilite, finDisponibilite, quantite) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdPetitMateriel());
            insert.setDate(4, el.getDebutDisponibilite());
            insert.setDate(5, el.getFinDisponibilite());
            insert.setDouble(5, el.getQuantite());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("PetitMaterielDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
