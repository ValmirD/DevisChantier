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
            String query = "Select id_codeReference, categorie, tonnage, capacite, location, marque, modele, numerodechassis, carburant, prixhtva, ctamortmois FROM CodeReference ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdCodeReference() != 0) {
                where = where + " id_codeReference = ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getMarque()!= null && !sel.getMarque().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " marque like ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getModele()!= null && !sel.getModele().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " modele like ? ";
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
                if (sel.getMarque() != null && !sel.getMarque().equals("")) {
                    stmt.setString(i, sel.getMarque() + "%");
                    i++;
                }
                if (sel.getModele() != null && !sel.getModele().equals("")) {
                    stmt.setString(i, sel.getModele() + "%");
                    i++;
                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new CodeReferenceDto(
                        rs.getInt("idCodeReference"), 
                        rs.getString("categorie"), 
                        rs.getInt("tonnage"), 
                        rs.getDouble("capacite"),
                        rs.getBoolean("location"),
                        rs.getString("marque"),
                        rs.getString("modele"),
                        rs.getString("numeroChassis"),
                        rs.getString("carburant"),
                        rs.getDouble("prixHtva"),
                        rs.getDouble("ctAmortMois")
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
                    + "categorie=? "
                    + "tonnage=? "
                    + "capacite=? "
                    + "location=? "
                    + "marque=? "
                    + "modele=? "
                    + "numeroChassis=? "
                    + "carburant=? "
                    + "prixHtva=? "
                    + "ctAmortMois=? "
                    + "where idCodeReference=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getCategorie());
            update.setInt(2, el.getTonnage());
            update.setDouble(3, el.getCapacite());
            update.setBoolean(4, el.isLocation());
            update.setString(5, el.getMarque());
            update.setString(6, el.getModele());
            update.setString(7, el.getNumeroChassis());
            update.setString(8, el.getCarburant());
            update.setDouble(9, el.getPrixHtva());
            update.setDouble(10, el.getCtAmortMois());
            update.setInt(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReference, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(CodeReferenceDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CAMION);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into CodeReference(idCodeReference, categorie, tonnage, capacite, location, marque, modele, numeroChassis, carburant, prixHtva, ctAmortMois) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getCategorie());
            insert.setInt(3, el.getTonnage());
            insert.setDouble(4, el.getCapacite());
            insert.setBoolean(5, el.isLocation());
            insert.setString(6, el.getMarque());
            insert.setString(7, el.getModele());
            insert.setString(8, el.getNumeroChassis());
            insert.setString(9, el.getCarburant());
            insert.setDouble(10, el.getPrixHtva());
            insert.setDouble(11, el.getCtAmortMois());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CodeReference: ajout impossible\n" + ex.getMessage());
        }
    }
}
