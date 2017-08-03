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
public class PetitMaterielDto extends EntityDto<Integer> {
    
    private String nom;
    private String type;
    private String reference;
    private double prixHtva;
    
    public PetitMaterielDto(int idPetitMateriel, String nom, String type, String reference, double prixHtva) {
        this.id = idPetitMateriel;
        this.nom = nom;
        this.type = type;
        this.reference = reference;
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

    public double getPrixHtva() {
        return prixHtva;
    }


    
}
