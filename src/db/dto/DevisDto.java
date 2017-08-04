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
public class DevisDto extends EntityDto<Integer> {
    
    private String designationDevis;
    private String statut;
    private Date dateDevis;
    private int idChantier;

    public DevisDto(int idDevis, String designationDevis, String statut, Date dateDevis, int idChantier) {
        this.id = idDevis;
        this.designationDevis = designationDevis;
        this.statut = statut;
        this.dateDevis = dateDevis;
        this.idChantier = idChantier;
    }

    public String getDesignationDevis() {
        return designationDevis;
    }

    public String getStatut() {
        return statut;
    }

    public Date getDateDevis() {
        return dateDevis;
    }

    public int getIdChantier() {
        return idChantier;
    }
    
    
    
}
