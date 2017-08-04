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
public class CodeReferenceDto extends EntityDto<Integer> {
    
    private String reference;
    private String typeTravail;
    private double prixHtva;

    public CodeReferenceDto(int idCodeReference, String reference, String typeTravail, double prixHtva) {
        this.id = idCodeReference;
        this.reference = reference;
        this.typeTravail = typeTravail;
        this.prixHtva = prixHtva;
    }

    public String getReference() {
        return reference;
    }

    public String getTypeTravail() {
        return typeTravail;
    }

    public double getPrixHtva() {
        return prixHtva;
    }
    
    
    
}
