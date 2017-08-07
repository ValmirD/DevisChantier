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
public class PetitMaterielSel {
    
    private int idPetitMateriel;
    private String nom;
    private String type;
    private String reference;

    public PetitMaterielSel(int idPetitMateriel, String nom, String type, String reference) {
        this.idPetitMateriel = idPetitMateriel;
        this.nom = nom;
        this.type = type;
        this.reference = reference;
    }
    
    public PetitMaterielSel(int idPetitMateriel) {
        this.idPetitMateriel = idPetitMateriel;
    }

    public int getIdPetitMateriel() {
        return idPetitMateriel;
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

    
    
}
