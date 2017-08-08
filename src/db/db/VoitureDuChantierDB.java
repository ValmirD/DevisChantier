/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.VoitureDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.VoitureDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class VoitureDuChantierDB {

    public static List<VoitureDuChantierDto> getAllVoitureDuChantiers() throws DevisChantierDbException {
        List<VoitureDuChantierDto> elements = getCollection(new VoitureDuChantierSel(0));
        return elements;
    }

    public static List<VoitureDuChantierDto> getCollection(VoitureDuChantierSel sel) throws DevisChantierDbException {
        List<VoitureDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idVoitureDuChantier, idChantier, idVoiture, debutDisponibilite, finDisponibilite FROM VoitureDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdVoitureDuChantier() != 0) {
                where = where + " idVoitureDuChantier = ? ";
            }
                        
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdVoitureDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdVoitureDuChantier());
                    i++;
                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new VoitureDuChantierDto(
                        rs.getInt("idVoitureDuChantier"), 
                        rs.getDate("debutDisponilibite"), 
                        rs.getDate("finDisponilibite"), 
                        rs.getInt("idChantier"),
                        rs.getInt("idVoiture")
                )                      
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de VoitureDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from VoitureDuChantier where idVoitureDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("VoitureDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(VoitureDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update VoitureDuChantier set "
                    + "idChantier=? "
                    + "idVoiture=? "
                    + "debutDisponilibite=? "
                    + "finDisponilibite=? "
                    + "where idVoitureDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdVoiture());
            update.setDate(3, el.getDebutDeDisponilibite());
            update.setDate(4, el.getFinDeDisponilibite());
            update.setInt(5, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("VoitureDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(VoitureDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.VOITUREDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into VoitureDuChantier(Select idVoitureDuChantier, idChantier, idVoiture, debutDisponibilite, finDisponibilite) "
                    + "values(?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdVoiture());
            insert.setDate(4, el.getDebutDeDisponilibite());
            insert.setDate(5, el.getFinDeDisponilibite());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("VoitureDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}

