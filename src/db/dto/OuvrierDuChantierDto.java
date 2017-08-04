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
public class OuvrierDuChantierDto extends EntityDto<Integer> {
    
    private Date dateDebut;
    private Date dateFin;
    private double nombreHeures;
    private int idChantier;
    private int idOuvrier;
    
    public OuvrierDuChantierDto(int idOuvrierDuChantier, Date dateDebut, Date dateFin, double nombreHeures, int idChantier, int idOuvrier) {
        this.id = idOuvrierDuChantier;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreHeures = nombreHeures;
        this.idChantier = idChantier;
        this.idOuvrier = idOuvrier;
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

    public int getIdChantier() {
        return idChantier;
    }

    public int getIdOuvrier() {
        return idOuvrier;
    }


    
}
