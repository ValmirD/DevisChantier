/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.selDto;

/**
 *
 * @author Vali
 */
public class MateriauSel {
    
    private int idMateriau;
    private String nom;
    private String type;
    private String reference;
    private String siteProduction;
    private String fourniture;

    public MateriauSel(int idMateriau, String nom, String type, String reference, String siteProduction, String fourniture) {
        this.idMateriau = idMateriau;
        this.nom = nom;
        this.type = type;
        this.reference = reference;
        this.siteProduction = siteProduction;
        this.fourniture = fourniture;
    }

        public MateriauSel(int idMateriau) {
        this.idMateriau = idMateriau;
    }

    public int getIdMateriau() {
        return idMateriau;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getReference() {
        return reference;
    }

    public String getSiteProduction() {
        return siteProduction;
    }

    public String getFourniture() {
        return fourniture;
    }
        
    
    
    
}
