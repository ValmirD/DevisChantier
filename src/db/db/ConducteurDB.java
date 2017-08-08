/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.ConducteurDto;
import db.exception.DevisChantierDbException;
import db.selDto.ConducteurSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class ConducteurDB {

    public static List<ConducteurDto> getAllConducteur() throws DevisChantierDbException {
        List<ConducteurDto> elements = getCollection(new ConducteurSel(0));
        return elements;
    }

    public static List<ConducteurDto> getCollection(ConducteurSel sel) throws DevisChantierDbException {
        List<ConducteurDto> al = new ArrayList<>();
        try {
            String query = "Select id_conducteur, nom, prenom, dateDeNaissance, numeroTelephone, numeroTelephonePro, email, remuneration, permis, entreeEnFonction, cout FROM Conducteur ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdConducteur() != 0) {
                where = where + " id_conducteur = ? ";
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
                
                if (sel.getIdConducteur() != 0) {
                    stmt.setInt(i, sel.getIdConducteur());
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
                al.add(new ConducteurDto(
                        rs.getInt("idConducteur"), 
                        rs.getString("numeroTelephonePro"),
                        rs.getString("numeroTelephone"),
                        rs.getDouble("remuneration"),
                        rs.getString("nom"), 
                        rs.getString("prenom"), 
                        rs.getDate("dateNaissance"),
                        rs.getString("email"),
                        rs.getDate("entreeEnFonction"),
                        rs.getDouble("cout"),
                        rs.getBoolean("permis"),
                        rs.getInt("idChantier")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Conducteur impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Conducteur where idConducteur=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Conducteur: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(ConducteurDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Conducteur set "
                    + "nom=? "
                    + "prenom=? "
                    + "dateDeNaissance=? "
                    + "numeroTelephone=? "
                    + "numeroTelephonePro=? "
                    + "email=? "
                    + "remuneration=? "
                    + "permis=? "
                    + "entreeEnFonction=? "
                    + "cout=? "
                    + "where idConducteur=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getNom());
            update.setString(2, el.getPrenom());
            update.setDate(3, el.getDateNaissance());
            update.setString(4, el.getNumeroTelephone());
            update.setString(5, el.getNumeroTelephonePro());
            update.setString(6, el.getEmail());
            update.setDouble(7, el.getRemuneration());
            update.setBoolean(8, el.isPermis());
            update.setDate(9, el.getEntreeFonction());
            update.setDouble(10, el.getCout());
            update.setInt(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Conducteur, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(ConducteurDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CONDUCTEUR);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Conducteur(idConducteur, nom, prenom, dateNaissance, numeroTelephone, numeroTelephonePro, email, remuneration, permis, entreeEnFonction, cout) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getNom());
            insert.setString(3, el.getPrenom());
            insert.setDate(4, el.getDateNaissance());
            insert.setString(5, el.getNumeroTelephone());
            insert.setString(6, el.getNumeroTelephonePro());
            insert.setString(7, el.getEmail());
            insert.setDouble(8, el.getRemuneration());
            insert.setBoolean(9, el.isPermis());
            insert.setDate(10, el.getEntreeFonction());
            insert.setDouble(11, el.getCout());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Conducteur: ajout impossible\n" + ex.getMessage());
        }
    }
}
