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
public class CodeReferenceDuChantierDto extends EntityDto<Integer> {
    
    private double quantite;
    private int idChantier;
    private int idCodeReference;

    public CodeReferenceDuChantierDto(int idCodeReferenceDuChantier, double quantite, int idChantier, int idCodeReference) {
        this.id = idCodeReferenceDuChantier;
        this.quantite = quantite;
        this.idChantier = idChantier;
        this.idCodeReference = idCodeReference;
    }

    public double getQuantite() {
        return quantite;
    }

    public int getIdChantier() {
        return idChantier;
    }

    public int getIdCodeReference() {
        return idCodeReference;
    }
    
    
    
}
