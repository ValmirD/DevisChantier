/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.CodeReferenceDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.CodeReferenceDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class CodeReferenceDuChantierDB {

    public static List<CodeReferenceDuChantierDto> getAllCodeReferenceDuChantier() throws DevisChantierDbException {
        List<CodeReferenceDuChantierDto> elements = getCollection(new CodeReferenceDuChantierSel(0));
        return elements;
    }

    public static List<CodeReferenceDuChantierDto> getCollection(CodeReferenceDuChantierSel sel) throws DevisChantierDbException {
        List<CodeReferenceDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idCodeReferenceDuChantier, idChantier, idCodeReference, quantite  FROM CodeReferenceDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            
            if (sel.getIdCodeReferenceDuChantier() != 0) {
                where = where + " idCodeReferenceDuChantier = ? ";
            }
            
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdCodeReferenceDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdCodeReferenceDuChantier());
                    i++;
                }

            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new CodeReferenceDuChantierDto(
                        rs.getInt("idCodeReferenceDuChantier"), 
                        rs.getDouble("quantite"), 
                        rs.getInt("idChantier"), 
                        rs.getInt("idCodeReference")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de CodeReferenceDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from CodeReferenceDuChantier where idCodeReferenceDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReferenceDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(CodeReferenceDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update CodeReferenceDuChantier set "
                    + "idChantier=? "
                    + "idCodeReference=? "
                    + "quantite=? "
                    + "where idCodeReferenceDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdCodeReference());
            update.setDouble(3, el.getQuantite());
            update.setInt(4, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReferenceDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(CodeReferenceDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CODEREFERENCEDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into CodeReferenceDuChantier(idCodeReferenceDuChantier, idChantier, idCodeReference, quantite) "
                    + "values(?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdCodeReference());
            insert.setDouble(4, el.getQuantite());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReferenceDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
