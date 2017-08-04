/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dto;

/**
 *
 * @author Vali
 */
public class MateriauDto extends EntityDto<Integer> {
    
    private String nom;
    private String type;
    private String reference;
    private String fourniture;
    private String siteProduction;
    private double prixHtva;

    public MateriauDto(int idMateriau, String nom, String type, String reference, String fourniture, String siteProduction, double prixHtva) {
        this.id = idMateriau;
        this.nom = nom;
        this.type = type;
        this.reference = reference;
        this.fourniture = fourniture;
        this.siteProduction = siteProduction;
        this.prixHtva = prixHtva;
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

    public String getFourniture() {
        return fourniture;
    }

    public String getSiteProduction() {
        return siteProduction;
    }

    public double getPrixHtva() {
        return prixHtva;
    }
    
    
    
    
}
