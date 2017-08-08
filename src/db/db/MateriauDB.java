/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.MateriauDto;
import db.exception.DevisChantierDbException;
import db.selDto.MateriauSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class MateriauDB {

    public static List<MateriauDto> getAllMateriau() throws DevisChantierDbException {
        List<MateriauDto> elements = getCollection(new MateriauSel(0));
        return elements;
    }

    public static List<MateriauDto> getCollection(MateriauSel sel) throws DevisChantierDbException {
        List<MateriauDto> al = new ArrayList<>();
        try {
            String query = "Select idmateriau, nom, type_, reference, fourniture, siteProduction, prixHtva FROM Materiau ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdMateriau() != 0) {
                where = where + " idmateriau = ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getNom()!= null && !sel.getNom().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " nom like ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getType()!= null && !sel.getType().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " type_ like ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getReference()!= null && !sel.getReference().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " reference like ? ";
            }            
             
            /*Pour une valeur string */
            if (sel.getSiteProduction()!= null && !sel.getSiteProduction().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " siteProduction like ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getFourniture()!= null && !sel.getFourniture().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " fourniture like ? ";
            }            
            
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                
                if (sel.getIdMateriau() != 0) {
                    stmt.setInt(i, sel.getIdMateriau());
                    i++;
                }
                if (sel.getNom() != null && !sel.getNom().equals("")) {
                    stmt.setString(i, sel.getNom() + "%");
                    i++;
                }
                if (sel.getType() != null && !sel.getType().equals("")) {
                    stmt.setString(i, sel.getType() + "%");
                    i++;
                }
                if (sel.getReference() != null && !sel.getReference().equals("")) {
                    stmt.setString(i, sel.getReference() + "%");
                    i++;
                }
                if (sel.getSiteProduction() != null && !sel.getSiteProduction().equals("")) {
                    stmt.setString(i, sel.getSiteProduction() + "%");
                    i++;
                }
                if (sel.getFourniture() != null && !sel.getFourniture().equals("")) {
                    stmt.setString(i, sel.getFourniture() + "%");
                    i++;
                }            
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new MateriauDto(
                        rs.getInt("idMateriau"), 
                        rs.getString("nom"), 
                        rs.getString("type_"), 
                        rs.getString("reference"),
                        rs.getString("fourniture"),
                        rs.getString("siteProduction"),
                        rs.getDouble("prixHtva")              
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Materiau impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Materiau where idMateriau=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Materiau: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(MateriauDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Materiau set "
                    + "nom=? "
                    + "type_=? "
                    + "reference=? "
                    + "fourniture=? "
                    + "siteProduction=? "
                    + "prixHtva=? "
                    + "where idMateriau=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getNom());
            update.setString(2, el.getType());
            update.setString(3, el.getReference());
            update.setString(4, el.getFourniture());
            update.setString(5, el.getSiteProduction());
            update.setDouble(6, el.getPrixHtva());
            update.setInt(7, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Materiau, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(MateriauDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.MATERIAU);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Materiau(idMateriau, nom, type_, reference, fourniture, siteProduction, prixHtva) "
                    + "values(?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getNom());
            insert.setString(3, el.getType());
            insert.setString(4, el.getReference());
            insert.setString(5, el.getFourniture());
            insert.setString(6, el.getSiteProduction());
            insert.setDouble(7, el.getPrixHtva());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Materiau: ajout impossible\n" + ex.getMessage());
        }
    }
}

