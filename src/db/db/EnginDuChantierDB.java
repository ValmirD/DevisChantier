/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.EnginDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.EnginDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class EnginDuChantierDB {

    public static List<EnginDuChantierDto> getAllEnginDuChantier() throws DevisChantierDbException {
        List<EnginDuChantierDto> elements = getCollection(new EnginDuChantierSel(0));
        return elements;
    }

    public static List<EnginDuChantierDto> getCollection(EnginDuChantierSel sel) throws DevisChantierDbException {
        List<EnginDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select id_engin_Du_Chantier, id_chantier, id_engin, debutDisponibilite, finDisponibilite, nombreHeures, quantite FROM Engin_Du_Chantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdEnginDuChantier() != 0) {
                where = where + " id_engin_Du_Chantier = ? ";
            }
            
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdEnginDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdEnginDuChantier());
                    i++;
                }

            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new EnginDuChantierDto(
                        rs.getInt("idEnginDuChantier"), 
                        rs.getDouble("quantite"), 
                        rs.getDouble("nombreHeures"), 
                        rs.getDate("debutDisponibilite"),
                        rs.getDate("finDisponibilite"),
                        rs.getInt("idChantier"),
                        rs.getInt("idEngin")                             
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Engin_Du_Chantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Engin_Du_Chantier where id_Engin_Du_Chantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("EnginDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(EnginDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update EnginDuChantier set "
                    + "categorie=? "
                    + "tonnage=? "
                    + "capacite=? "
                    + "location=? "
                    + "marque=? "
                    + "modele=? "
                    + "numeroChassis=? "
                    + "carburant=? "
                    + "prixHtva=? "
                    + "ctAmortMois=? "
                    + "where idEnginDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getCategorie());
            update.setInt(2, el.getTonnage());
            update.setDouble(3, el.getCapacite());
            update.setBoolean(4, el.isLocation());
            update.setString(5, el.getMarque());
            update.setString(6, el.getModele());
            update.setString(7, el.getNumeroChassis());
            update.setString(8, el.getCarburant());
            update.setDouble(9, el.getPrixHtva());
            update.setDouble(10, el.getCtAmortMois());
            update.setInt(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("EnginDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(EnginDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CAMION);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into EnginDuChantier(idEnginDuChantier, categorie, tonnage, capacite, location, marque, modele, numeroChassis, carburant, prixHtva, ctAmortMois) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getCategorie());
            insert.setInt(3, el.getTonnage());
            insert.setDouble(4, el.getCapacite());
            insert.setBoolean(5, el.isLocation());
            insert.setString(6, el.getMarque());
            insert.setString(7, el.getModele());
            insert.setString(8, el.getNumeroChassis());
            insert.setString(9, el.getCarburant());
            insert.setDouble(10, el.getPrixHtva());
            insert.setDouble(11, el.getCtAmortMois());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("EnginDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
