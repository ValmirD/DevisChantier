/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.ChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.ChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class ChantierDB {

    public static List<ChantierDto> getAllChantier() throws DevisChantierDbException {
        List<ChantierDto> elements = getCollection(new ChantierSel(0));
        return elements;
    }

    public static List<ChantierDto> getCollection(ChantierSel sel) throws DevisChantierDbException {
        List<ChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idChantier, idOuvrier, idClient, idDevis, idConducteur, localisation, designationduprojet, commentaire, dateCreationprojet, datedebutprevue, datedebuteffective, datefinprevue, datefineffective FROM Chantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdChantier() != 0) {
                where = where + " idChantier = ? ";
            }
            
            if (sel.getIdClient() != 0) {
                where = where + " idClient = ? ";
            }            
            
            /*Pour une valeur string */
            if (sel.getDesignationProjet()!= null && !sel.getDesignationProjet().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " designationduprojet like ? ";
            }
            
          
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                
                if (sel.getIdChantier() != 0) {
                    stmt.setInt(i, sel.getIdChantier());
                    i++;
                }
                
                if (sel.getIdClient() != 0) {
                    stmt.setInt(i, sel.getIdClient());
                    i++;
                }                

                if (sel.getDesignationProjet() != null && !sel.getDesignationProjet().equals("")) {
                    stmt.setString(i, sel.getDesignationProjet() + "%");
                    i++;
                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new ChantierDto(
                        rs.getInt("idChantier"), 
                        rs.getInt("idOuvrier"), 
                        rs.getInt("idClient"), 
                        rs.getInt("idDevis"),
                        rs.getInt("idConducteur"),
                        rs.getString("localisation"),
                        rs.getString("designationProjet"),
                        rs.getString("commentaire"),
                        rs.getDate("dateCreationProjet"),
                        rs.getDate("dateDebutPrevue"),
                        rs.getDate("dateDebutEffective"),
                        rs.getDate("dateFinPrevue"),                        
                        rs.getDate("dateFinEffective")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Chantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Chantier where idChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Chantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(ChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Chantier set "
                    + "idOuvrier=? "
                    + "idClient=? "
                    + "idDevis=? "
                    + "idConducteur=? "
                    + "localisation=? "
                    + "designationDuProjet=? "
                    + "commentaire=? "
                    + "dateCreationProjet=? "
                    + "dateDebutPrevue=? "
                    + "dateDebutEffective=? "
                    + "dateFinPrevue=? "
                    + "dateFinEffective=? "
                    + "where idChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdOuvrier());
            update.setInt(2, el.getIdClient());
            update.setInt(3, el.getIdDevis());
            update.setInt(4, el.getIdConducteur());
            update.setString(5, el.getLocalisation());
            update.setString(6, el.getDesignationProjet());
            update.setString(7, el.getCommentaire());
            update.setDate(8, el.getDateDebutPrevue());
            update.setDate(10, el.getDateDebutEffective());
            update.setDate(9, el.getDateFinPrevue());            
            update.setDate(11, el.getDateFinEffective());
            update.setInt(12, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Chantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(ChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Chantier(idChantier, idOuvrier, idClient, idDevis, idConducteur, localisation, designationProjet, commentaire, dateCreationProjet, dateDebutprevue, dateDebutEffective, dateFinPrevue, dateFinEffective) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdOuvrier());
            insert.setInt(3, el.getIdClient());
            insert.setInt(4, el.getIdDevis());
            insert.setInt(5, el.getIdConducteur());
            insert.setString(6, el.getLocalisation());
            insert.setString(7, el.getDesignationProjet());
            insert.setString(8, el.getCommentaire());
            insert.setDate(9, el.getDateCreationProjet());
            insert.setDate(10, el.getDateDebutPrevue());
            insert.setDate(11, el.getDateDebutEffective());
            insert.setDate(12, el.getDateFinPrevue());
            insert.setDate(13, el.getDateFinEffective());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Chantier: ajout impossible\n" + ex.getMessage());
        }
    }
}

