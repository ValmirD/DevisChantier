/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.CodeReferenceDto;
import db.exception.DevisChantierDbException;
import db.selDto.CodeReferenceSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class CodeReferenceDB {

    public static List<CodeReferenceDto> getAllCodeReference() throws DevisChantierDbException {
        List<CodeReferenceDto> elements = getCollection(new CodeReferenceSel(0));
        return elements;
    }

    public static List<CodeReferenceDto> getCollection(CodeReferenceSel sel) throws DevisChantierDbException {
        List<CodeReferenceDto> al = new ArrayList<>();
        try {
            String query = "Select idCodeReference, reference, typeTravail, prixHtva FROM CodeReference ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdCodeReference() != 0) {
                where = where + " idCodeReference = ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getReference()!= null && !sel.getReference().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " reference like ? ";
            }
            
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdCodeReference() != 0) {
                    stmt.setInt(i, sel.getIdCodeReference());
                    i++;
                }
                if (sel.getReference() != null && !sel.getReference().equals("")) {
                    stmt.setString(i, sel.getReference() + "%");
                    i++;
                }

            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new CodeReferenceDto(
                        rs.getInt("idCodeReference"), 
                        rs.getString("reference"), 
                        rs.getString("typeTravail"), 
                        rs.getDouble("prixHtva")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de CodeReference impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from CodeReference where idCodeReference=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReference: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(CodeReferenceDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update CodeReference set "
                    + "reference=? "
                    + "typeTravail=? "
                    + "prixHtva=? "
                    + "where idCodeReference=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getReference());
            update.setString(2, el.getTypeTravail());
            update.setDouble(3, el.getPrixHtva());
            update.setInt(4, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReference, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(CodeReferenceDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CODEREFERENCE);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into CodeReference(idCodeReference, reference, typeTravail, prixHtva) "
                    + "values(?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getReference());
            insert.setString(3, el.getTypeTravail());
            insert.setDouble(11, el.getPrixHtva());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReference: ajout impossible\n" + ex.getMessage());
        }
    }
}
