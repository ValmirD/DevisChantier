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
            String query = "Select idConducteur, password, nom, prenom, dateDeNaissance, numeroTelephone, numeroTelephonePro, email, remuneration, permis, entreeFonction, cout FROM Conducteur ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";

            /*Pour une valeur numerique */
            if (sel.getIdConducteur() != 0) {
                where = where + " idConducteur = ? ";
            }

            /*Pour une valeur string */
            if (sel.getPassword() != null && !sel.getPassword().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " password like ? ";
            }

            /*Pour une valeur string */
            if (sel.getNom() != null && !sel.getNom().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " nom like ? ";
            }

            /*Pour une valeur string */
            if (sel.getPrenom() != null && !sel.getPrenom().equals("")) {
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
                if (sel.getPassword() != null && !sel.getPassword().equals("")) {
                    stmt.setString(i, sel.getPassword() + "%");
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
                        rs.getString("password"),
                        rs.getString("numeroTelephonePro"),
                        rs.getString("numeroTelephone"),
                        rs.getDouble("remuneration"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateNaissance"),
                        rs.getString("email"),
                        rs.getDate("entreeFonction"),
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
                    + "password=? "
                    + "nom=? "
                    + "prenom=? "
                    + "dateDeNaissance=? "
                    + "numeroTelephone=? "
                    + "numeroTelephonePro=? "
                    + "email=? "
                    + "remuneration=? "
                    + "permis=? "
                    + "entreeFonction=? "
                    + "cout=? "
                    + "where idConducteur=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getPassword());
            update.setString(2, el.getNom());
            update.setString(3, el.getPrenom());
            update.setDate(4, el.getDateNaissance());
            update.setString(5, el.getNumeroTelephone());
            update.setString(6, el.getNumeroTelephonePro());
            update.setString(7, el.getEmail());
            update.setDouble(8, el.getRemuneration());
            update.setBoolean(9, el.isPermis());
            update.setDate(10, el.getEntreeFonction());
            update.setDouble(11, el.getCout());
            update.setInt(12, el.getId());
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
                    "Insert into Conducteur(idConducteur, password, nom, prenom, dateNaissance, numeroTelephone, numeroTelephonePro, email, remuneration, permis, entreeFonction, cout) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getPassword());
            insert.setString(3, el.getNom());
            insert.setString(4, el.getPrenom());
            insert.setDate(5, el.getDateNaissance());
            insert.setString(6, el.getNumeroTelephone());
            insert.setString(7, el.getNumeroTelephonePro());
            insert.setString(8, el.getEmail());
            insert.setDouble(9, el.getRemuneration());
            insert.setBoolean(10, el.isPermis());
            insert.setDate(11, el.getEntreeFonction());
            insert.setDouble(12, el.getCout());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Conducteur: ajout impossible\n" + ex.getMessage());
        }
    }
}
