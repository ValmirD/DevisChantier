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
            String query = "Select id_devis, categorie, tonnage, capacite, location, marque, modele, numerodechassis, carburant, prixhtva, ctamortmois FROM Devis ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdDevis() != 0) {
                where = where + " id_devis = ? ";
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
                if (sel.getIdDevis() != 0) {
                    stmt.setInt(i, sel.getIdDevis());
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
                al.add(new DevisDto(
                        rs.getInt("idDevis"), 
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
                    + "where idDevis=?";
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
            throw new DevisChantierDbException("Devis, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(DevisDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CAMION);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Devis(idDevis, categorie, tonnage, capacite, location, marque, modele, numeroChassis, carburant, prixHtva, ctAmortMois) "
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
            throw new DevisChantierDbException("Devis: ajout impossible\n" + ex.getMessage());
        }
    }
}

