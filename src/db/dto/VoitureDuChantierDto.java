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
public class VoitureDuChantierDto extends EntityDto<Integer> {
    
    private Date debutDisponilibite;
    private Date finDisponilibite;
    private int idChantier;
    private int idVoiture;

    public VoitureDuChantierDto(int idVoitureDuChantier, Date debutDisponilibite, Date finDisponilibite, int idChantier, int idVoiture) {
        this.id = idVoitureDuChantier;
        this.debutDisponilibite = debutDisponilibite;
        this.finDisponilibite = finDisponilibite;
        this.idChantier = idChantier;
        this.idVoiture = idVoiture;
    }

    public Date getDebutDeDisponilibite() {
        return debutDisponilibite;
    }

    public Date getFinDeDisponilibite() {
        return finDisponilibite;
    }

    public int getIdChantier() {
        return idChantier;
    }

    public int getIdVoiture() {
        return idVoiture;
    }


    
    
    
}
