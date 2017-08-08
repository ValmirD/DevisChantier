/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.OuvrierDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.OuvrierDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class OuvrierDuChantierDB {

    public static List<OuvrierDuChantierDto> getAllOuvrierDuChantier() throws DevisChantierDbException {
        List<OuvrierDuChantierDto> elements = getCollection(new OuvrierDuChantierSel(0));
        return elements;
    }

    public static List<OuvrierDuChantierDto> getCollection(OuvrierDuChantierSel sel) throws DevisChantierDbException {
        List<OuvrierDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idOuvrierDuChantier, idChantier, idOuvrier, dateDebut, dateFin, nombreHeures FROM OuvrierDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdOuvrierDuChantier() != 0) {
                where = where + " idOuvrierDuChantier = ? ";
            }
            
                        
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdOuvrierDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdOuvrierDuChantier());
                    i++;
                }

            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new OuvrierDuChantierDto(
                        rs.getInt("idOuvrierDuChantier"), 
                        rs.getDate("dateDebut"), 
                        rs.getDate("dateFin"), 
                        rs.getDouble("nombreHeures"),
                        rs.getInt("idChantier"),
                        rs.getInt("idOuvrier")                     
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de OuvrierDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from OuvrierDuChantier where idOuvrierDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("OuvrierDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(OuvrierDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update OuvrierDuChantier set "
                    + "idChantier=? "
                    + "idOuvrier=? "
                    + "dateDebut=? "
                    + "dateFin=? "
                    + "nombreHeures=? "
                    + "where idOuvrierDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdOuvrier());
            update.setDate(3, el.getDateDebut());
            update.setDate(4, el.getDateFin());
            update.setDouble(11, el.getNombreHeures());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("OuvrierDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(OuvrierDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.OUVRIERDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into OuvrierDuChantier(idOuvrierDuChantier, idChantier, idOuvrier, dateDebut, dateFin, nombreHeures) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdOuvrier());
            insert.setDate(4, el.getDateDebut());
            insert.setDate(5, el.getDateFin());
            insert.setDouble(6, el.getNombreHeures());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("OuvrierDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
