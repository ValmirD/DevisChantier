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
public class ConducteurDuChantierDto extends EntityDto<Integer> {
    
    private Date dateDebut;
    private Date dateFin;
    private double nombreHeures;
    private int idConducteur;
    private int idChantier;

    public ConducteurDuChantierDto(int idConducteurDuChantier, Date dateDebut, Date dateFin, double nombreHeures, int idConducteur, int idChantier) {
        this.id = idConducteurDuChantier;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreHeures = nombreHeures;
        this.idConducteur = idConducteur;
        this.idChantier = idChantier;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public double getNombreHeures() {
        return nombreHeures;
    }

    public int getIdConducteur() {
        return idConducteur;
    }

    public int getIdChantier() {
        return idChantier;
    }
    
    
    
    
}
