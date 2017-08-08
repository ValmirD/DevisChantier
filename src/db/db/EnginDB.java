/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.EnginDto;
import db.exception.DevisChantierDbException;
import db.selDto.EnginSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class EnginDB {

    public static List<EnginDto> getAllEngin() throws DevisChantierDbException {
        List<EnginDto> elements = getCollection(new EnginSel(0));
        return elements;
    }

    public static List<EnginDto> getCollection(EnginSel sel) throws DevisChantierDbException {
        List<EnginDto> al = new ArrayList<>();
        try {
            String query = "Select idEngin, nom, type_, reference, location, prixHeure  FROM Engin ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdEngin() != 0) {
                where = where + " idEngin = ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getNom()!= null && !sel.getNom().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " nom like ? ";
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
                
                if (sel.getIdEngin() != 0) {
                    stmt.setInt(i, sel.getIdEngin());
                    i++;
                }
                if (sel.getNom() != null && !sel.getNom().equals("")) {
                    stmt.setString(i, sel.getNom() + "%");
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
                al.add(new EnginDto(
                        rs.getInt("idEngin"), 
                        rs.getString("nom"), 
                        rs.getString("type"), 
                        rs.getString("reference"),
                        rs.getBoolean("location"),
                        rs.getDouble("prixHeure")                  
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Engin impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Engin where idEngin=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Engin: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(EnginDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Engin set "
                    + "nom=? "
                    + "type=? "
                    + "reference=? "
                    + "location=? "
                    + "prixHeure=? "
                    + "where idEngin=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getNom());
            update.setString(2, el.getType());
            update.setString(3, el.getReference());
            update.setBoolean(4, el.isLocation());
            update.setDouble(5, el.getPrixHeure());
            update.setInt(6, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Engin, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(EnginDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.ENGIN);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Engin(idEngin, nom, type, reference, location, prixHeure) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getNom());
            insert.setString(3, el.getType());
            insert.setString(4, el.getReference());
            insert.setBoolean(5, el.isLocation());
            insert.setDouble(6, el.getPrixHeure());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Engin: ajout impossible\n" + ex.getMessage());
        }
    }
}

