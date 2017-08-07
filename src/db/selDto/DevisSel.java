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
public class DevisSel {
    
    private int idDevis;
    private String designationDevis;
    private Date dateDevis;

    public DevisSel(int idDevis, String designationDevis, Date dateDevis) {
        this.idDevis = idDevis;
        this.designationDevis = designationDevis;
        this.dateDevis = dateDevis;
    }
    
    public DevisSel(int idDevis) {
        this.idDevis = idDevis;
    }    

    public int getIdDevis() {
        return idDevis;
    }

    public String getDesignationDevis() {
        return designationDevis;
    }

    public Date getDateDevis() {
        return dateDevis;
    }
    
    
    
    
    
}
