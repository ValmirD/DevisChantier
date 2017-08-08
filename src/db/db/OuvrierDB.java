/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.OuvrierDto;
import db.exception.DevisChantierDbException;
import db.selDto.OuvrierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class OuvrierDB {

    public static List<OuvrierDto> getAllOuvrier() throws DevisChantierDbException {
        List<OuvrierDto> elements = getCollection(new OuvrierSel(0));
        return elements;
    }

    public static List<OuvrierDto> getCollection(OuvrierSel sel) throws DevisChantierDbException {
        List<OuvrierDto> al = new ArrayList<>();
        try {
            String query = "Select idOuvrier, nom, prenom, dateNaissance, numeroTelephone, email, remuneration, permis, entreeFonction, cout FROM Ouvrier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdOuvrier() != 0) {
                where = where + " idOuvrier = ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getNom()!= null && !sel.getNom().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " nom like ? ";
            }
            
            /*Pour une valeur string */
            if (sel.getPrenom()!= null && !sel.getPrenom().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " prenom like ? ";
            }
                        
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdOuvrier() != 0) {
                    stmt.setInt(i, sel.getIdOuvrier());
                    i++;
                }
                if (sel.getNom() != null && !sel.getNom().equals("")) {
                    stmt.setString(i, sel.getNom() + "%");
                    i++;
                }
                if (sel.getPrenom() != null && !sel.getPrenom().equals("")) {
                    stmt.setString(i, sel.getPrenom() + "%");
                    i++;
                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new OuvrierDto(
                        rs.getInt("idOuvrier"), 
                        rs.getInt("idChantier"), 
                        rs.getDouble("remuneration"), 
                        rs.getBoolean("permis"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateNaissance"),
                        rs.getString("numeroTelephone"),
                        rs.getString("email"),
                        rs.getDate("entreeFonction"),
                        rs.getDouble("cout")                      
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Ouvrier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Ouvrier where idOuvrier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Ouvrier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(OuvrierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Ouvrier set "
                    + "nom=? "
                    + "prenom=? "
                    + "dateNaissance=? "
                    + "numeroTelephone=? "
                    + "email=? "
                    + "remuneration=? "
                    + "permis=? "
                    + "entreeFonction=? "
                    + "cout=? "
                    + "where idOuvrier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getNom());
            update.setString(2, el.getPrenom());
            update.setDate(3, el.getDateNaissance());
            update.setString(4, el.getNumeroTelephone());
            update.setString(5, el.getEmail());
            update.setDouble(6, el.getRemuneration());
            update.setBoolean(7, el.isPermis());
            update.setDate(8, el.getEntreeFonction());
            update.setDouble(9, el.getCout());
            update.setInt(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Ouvrier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(OuvrierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.OUVRIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Ouvrier(idOuvrier, nom, prenom, dateNaissance, numeroTelephone, email, remuneration, permis, entreeFonction, cout) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getNom());
            insert.setString(3, el.getPrenom());
            insert.setDate(4, el.getDateNaissance());
            insert.setString(5, el.getNumeroTelephone());
            insert.setString(6, el.getEmail());
            insert.setDouble(7, el.getRemuneration());
            insert.setBoolean(8, el.isPermis());
            insert.setDate(9, el.getEntreeFonction());
            insert.setDouble(10, el.getCout());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Ouvrier: ajout impossible\n" + ex.getMessage());
        }
    }
}
