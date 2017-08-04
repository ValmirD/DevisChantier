/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dto;

import java.sql.Date;

/**
 *
 * @author Vali
 */
public class MateriauDuChantierDto extends EntityDto<Integer> {
    
    private double quantite;
    private Date debutDisponibilite;
    private Date finDisponibilite;
    private int idChantier;
    private int idMateriau;

    public MateriauDuChantierDto(int idMateriauDuChantier, double quantite, Date debutDisponibilite, Date finDisponibilite, int idChantier, int idMateriau) {
        this.id = idMateriauDuChantier;
        this.quantite = quantite;
        this.debutDisponibilite = debutDisponibilite;
        this.finDisponibilite = finDisponibilite;
        this.idChantier = idChantier;
        this.idMateriau = idMateriau;
    }

    public double getQuantite() {
        return quantite;
    }

    public Date getDebutDisponibilite() {
        return debutDisponibilite;
    }

    public Date getFinDisponibilite() {
        return finDisponibilite;
    }

    public int getIdChantier() {
        return idChantier;
    }

    public int getIdMateriau() {
        return idMateriau;
    }
    
    
    
    
}
