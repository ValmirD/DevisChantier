/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.DevisDto;
import db.exception.DevisChantierDbException;
import db.selDto.DevisSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class DevisDB {

    public static List<DevisDto> getAllDevis() throws DevisChantierDbException {
        List<DevisDto> elements = getCollection(new DevisSel(0));
        return elements;
    }

    public static List<DevisDto> getCollection(DevisSel sel) throws DevisChantierDbException {
        List<DevisDto> al = new ArrayList<>();
        try {
            String query = "Select idDevis, designationDevis, statut, dateDevis FROM Devis ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdDevis() != 0) {
                where = where + " idDevis = ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getDesignationDevis()!= null && !sel.getDesignationDevis().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " designationDevis like ? ";
            }
            
                       
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdDevis() != 0) {
                    stmt.setInt(i, sel.getIdDevis());
                    i++;
                }
                if (sel.getDesignationDevis() != null && !sel.getDesignationDevis().equals("")) {
                    stmt.setString(i, sel.getDesignationDevis() + "%");
                    i++;
                }

            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new DevisDto(
                        rs.getInt("idDevis"), 
                        rs.getString("designationDevis"), 
                        rs.getString("statut"), 
                        rs.getDate("dateDevis"),
                        rs.getInt("idChantier") 
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Devis impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Devis where idDevis=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Devis: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(DevisDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Devis set "
                    + "designationDevis=? "
                    + "statut=? "
                    + "dateDevis=? "
                    + "where idDevis=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getDesignationDevis());
            update.setString(2, el.getStatut());
            update.setDate(3, el.getDateDevis());
            update.setInt(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Devis, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(DevisDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.DEVIS);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Devis(idDevis, designationDevis, statut, dateDevis) "
                    + "values(?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getDesignationDevis());
            insert.setString(3, el.getStatut());
            insert.setDate(4, el.getDateDevis());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Devis: ajout impossible\n" + ex.getMessage());
        }
    }
}

