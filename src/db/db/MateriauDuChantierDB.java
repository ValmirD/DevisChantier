/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.MateriauDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.MateriauDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class MateriauDuChantierDB {

    public static List<MateriauDuChantierDto> getAllMateriauDuChantier() throws DevisChantierDbException {
        List<MateriauDuChantierDto> elements = getCollection(new MateriauDuChantierSel(0));
        return elements;
    }

    public static List<MateriauDuChantierDto> getCollection(MateriauDuChantierSel sel) throws DevisChantierDbException {
        List<MateriauDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idMateriauDuChantier, idChantier, idMateriau, debutDisponibilite, finDisponibilite, quantite FROM MateriauDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdMateriauDuChantier() != 0) {
                where = where + " idMateriauDuChantier = ? ";
            }
                        
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdMateriauDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdMateriauDuChantier());
                    i++;
                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new MateriauDuChantierDto(
                        rs.getInt("idMateriauDuChantier"), 
                        rs.getDouble("quantite"), 
                        rs.getDate("debutDisponibilite"),
                        rs.getDate("finDisponibilite"),
                        rs.getInt("idChantier"),
                        rs.getInt("idMateriau")                       
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de MateriauDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from MateriauDuChantier where idMateriauDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("MateriauDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(MateriauDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update MateriauDuChantier set "
                    + "idChantier=? "
                    + "idMateriau=? "
                    + "debutDisponibilite=? "
                    + "finDisponibilite=? "
                    + "quantite=? "
                    + "where idMateriauDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdMateriau());
            update.setDate(3, el.getDebutDisponibilite());
            update.setDate(3, el.getFinDisponibilite());
            update.setDouble(4, el.getQuantite());
            update.setInt(11, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("MateriauDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(MateriauDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.MATERIAUDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into MateriauDuChantier(idMateriauDuChantier, idChantier, idMateriau, debutDisponibilite, finDisponibilite, quantite) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, el.getId());
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdMateriau());
            insert.setDate(4, el.getDebutDisponibilite());
            insert.setDate(5, el.getFinDisponibilite());
            insert.setDouble(6, el.getQuantite());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("MateriauDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}

