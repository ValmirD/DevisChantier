/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.ConducteurDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.ConducteurDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class ConducteurDuChantierDB {

    public static List<ConducteurDuChantierDto> getAllConducteurDuChantier() throws DevisChantierDbException {
        List<ConducteurDuChantierDto> elements = getCollection(new ConducteurDuChantierSel(0));
        return elements;
    }

    public static List<ConducteurDuChantierDto> getCollection(ConducteurDuChantierSel sel) throws DevisChantierDbException {
        List<ConducteurDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idConducteurDuChantier, idChantier, idConducteur, dateDebut, dateFin, nombreHeures FROM ConducteurDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdConducteurDuChantier() != 0) {
                where = where + " idConducteurDuChantier = ? ";
            }
            
               
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdConducteurDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdConducteurDuChantier());
                    i++;
                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new ConducteurDuChantierDto(
                        rs.getInt("idConducteurDuChantier"), 
                        rs.getDate("dateDebut"), 
                        rs.getDate("dateFin"), 
                        rs.getDouble("nombreHeures"),
                        rs.getInt("idConducteur"),
                        rs.getInt("idChantier")
                  
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de ConducteurDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from ConducteurDuChantier where idConducteurDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("ConducteurDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(ConducteurDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update ConducteurDuChantier set "
                    + "idChantier=? "
                    + "idConducteur=? "
                    + "dateDebut=? "
                    + "dateFin=? "
                    + "nombreHeures=? "
                    + "where idConducteurDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdConducteur());
            update.setDate(3, el.getDateDebut());
            update.setDate(4, el.getDateFin());
            update.setDouble(5, el.getNombreHeures());
            update.setInt(6, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("ConducteurDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(ConducteurDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CONDUCTEURDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into ConducteurDuChantier(idConducteurDuChantier, idChantier, idConducteur, dateDebut, dateFin, nombreHeures) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdConducteur());
            insert.setDate(4, el.getDateDebut());
            insert.setDate(5, el.getDateFin());
            insert.setDouble(6, el.getNombreHeures());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("ConducteurDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
