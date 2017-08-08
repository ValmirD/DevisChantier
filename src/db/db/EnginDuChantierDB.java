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
            String query = "Select idenginDuChantier, idchantier, idengin, debutDisponibilite, finDisponibilite, nombreHeures, quantite FROM EnginDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdEnginDuChantier() != 0) {
                where = where + " idenginDuChantier = ? ";
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
            throw new DevisChantierDbException("Instanciation de EnginDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from EnginDuChantier where idEnginDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("EnginDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(EnginDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update EnginDuChantier set "
                    + "idChantier=? "
                    + "idEngin=? "
                    + "debutDisponibilite=? "
                    + "finDisponibilite=? "
                    + "nombreHeures=? "
                    + "quantite=? "
                    + "where idEnginDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdEngin());
            update.setDate(3, el.getDebutDisponibilite());
            update.setDate(4, el.getFinDisponibilite());
            update.setDouble(5, el.getQuantite());
            update.setDouble(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("EnginDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(EnginDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.ENGINDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into EnginDuChantier(idEnginDuChantier, idChantier, idEngin, debutDisponibilite, finDisponibilite, nombreHeures, quantite) "
                    + "values(?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdEngin());
            insert.setDate(4, el.getDebutDisponibilite());
            insert.setDate(5, el.getFinDisponibilite());
            insert.setDouble(6, el.getNombreHeures());
            insert.setDouble(7, el.getQuantite());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("EnginDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
