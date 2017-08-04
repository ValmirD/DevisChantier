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
public class EnginDuChantierDto extends EntityDto<Integer> {
    
    private double quantite;
    private double nombreHeures;
    private Date debutDisponibilite;
    private Date finDisponibilite;
    private int idChantier;
    private int idEngin;

    public EnginDuChantierDto(int idEnginDuChantier, double quantite, double nombreHeures, Date debutDisponibilite, Date finDisponibilite, int idChantier, int idEngin) {
        this.id = idEnginDuChantier;
        this.quantite = quantite;
        this.nombreHeures = nombreHeures;
        this.debutDisponibilite = debutDisponibilite;
        this.finDisponibilite = finDisponibilite;
        this.idChantier = idChantier;
        this.idEngin = idEngin;
    }

    public double getQuantite() {
        return quantite;
    }

    public double getNombreHeures() {
        return nombreHeures;
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

    public int getIdEngin() {
        return idEngin;
    }
    
    
    
    
}
