/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.PetitMaterielDto;
import db.exception.DevisChantierDbException;
import db.selDto.PetitMaterielSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class PetitMaterielDB {

    public static List<PetitMaterielDto> getAllPetitMateriel() throws DevisChantierDbException {
        List<PetitMaterielDto> elements = getCollection(new PetitMaterielSel(0));
        return elements;
    }

    public static List<PetitMaterielDto> getCollection(PetitMaterielSel sel) throws DevisChantierDbException {
        List<PetitMaterielDto> al = new ArrayList<>();
        try {
            String query = "Select idPetitMateriel, nom, type_, reference, prixHtva FROM PetitMateriel ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdPetitMateriel() != 0) {
                where = where + " idPetitMateriel = ? ";
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
                        
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                
                if (sel.getIdPetitMateriel() != 0) {
                    stmt.setInt(i, sel.getIdPetitMateriel());
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
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PetitMaterielDto(
                        rs.getInt("idPetitMateriel"), 
                        rs.getString("nom"), 
                        rs.getString("type"), 
                        rs.getString("reference"),
                        rs.getDouble("prixHtva")                      
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de PetitMateriel impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from PetitMateriel where idPetitMateriel=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("PetitMateriel: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(PetitMaterielDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update PetitMateriel set "
                    + "nom=? "
                    + "type_=? "
                    + "reference=? "
                    + "prixHtva=? "
                    + "where idPetitMateriel=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getNom());
            update.setString(2, el.getType());
            update.setString(3, el.getReference());
            update.setDouble(4, el.getPrixHtva());
            update.setInt(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("PetitMateriel, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(PetitMaterielDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.PETITMATERIEL);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into PetitMateriel(idPetitMateriel, nom, type_, reference, prixHtva) "
                    + "values(?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getNom());
            insert.setString(3, el.getType());
            insert.setString(4, el.getReference());
            insert.setDouble(5, el.getPrixHtva());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("PetitMateriel: ajout impossible\n" + ex.getMessage());
        }
    }
}
