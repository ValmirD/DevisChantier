/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.ClientDto;
import db.exception.DevisChantierDbException;
import db.selDto.ClientSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class ClientDB {

    public static List<ClientDto> getAllClient() throws DevisChantierDbException {
        List<ClientDto> elements = getCollection(new ClientSel(0));
        return elements;
    }

    public static List<ClientDto> getCollection(ClientSel sel) throws DevisChantierDbException {
        List<ClientDto> al = new ArrayList<>();
        try {
            String query = "Select idClient, nom, prenom, dateNaissance, numeroTelephone, email FROM Client ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdClient() != 0) {
                where = where + " idClient = ? ";
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
                
                if (sel.getIdClient() != 0) {
                    stmt.setInt(i, sel.getIdClient());
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
                al.add(new ClientDto(
                        rs.getInt("idClient"), 
                        rs.getString("nom"), 
                        rs.getString("prenom"), 
                        rs.getDate("dateNaissance"),
                        rs.getString("numeroTelephone"),
                        rs.getString("email"),
                        rs.getInt("idChantier")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Client impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Client where idClient=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Client: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(ClientDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Client set "
                    + "nom=? "
                    + "prenom=? "
                    + "dateNaissance=? "
                    + "numeroTelephone=? "
                    + "email=? "
                    + "where idClient=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getNom());
            update.setString(2, el.getPrenom());
            update.setDate(3, el.getDateNaissance());
            update.setString(4, el.getNumeroTelephone());
            update.setString(5, el.getEmail());
            update.setInt(6, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Client, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(ClientDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CLIENT);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Client(idClient, nom, prenom, dateNaissance, numeroTelephone, email) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setString(2, el.getNom());
            insert.setString(3, el.getPrenom());
            insert.setDate(4, el.getDateNaissance());
            insert.setString(5, el.getNumeroTelephone());
            insert.setString(6, el.getEmail());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Client: ajout impossible\n" + ex.getMessage());
        }
    }
}

