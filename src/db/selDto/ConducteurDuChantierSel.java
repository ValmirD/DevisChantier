/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.selDto;

import java.sql.Date;

/**
 *
 * @author Vali
 */
public class ConducteurDuChantierSel {
    
    private int idConducteurDuChantier;
    private Date dateDebut;
    private Date dateFin;

    public ConducteurDuChantierSel(int idConducteurDuChantier, Date dateDebut, Date dateFin) {
        this.idConducteurDuChantier = idConducteurDuChantier;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    public ConducteurDuChantierSel(int idConducteurDuChantier) {
        this.idConducteurDuChantier = idConducteurDuChantier;
    }    

    public int getIdConducteurDuChantier() {
        return idConducteurDuChantier;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
    
    
    
    
}
