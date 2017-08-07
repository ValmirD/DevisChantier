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
public class EnginSel {
    
    private int idEngin;
    private String nom;
    private String type;
    private String reference;

    public EnginSel(int idEngin, String nom, String type, String reference) {
        this.idEngin = idEngin;
        this.nom = nom;
        this.type = type;
        this.reference = reference;
    }

    public EnginSel(int idEngin) {
        this.idEngin = idEngin;
    }    

    public int getIdEngin() {
        return idEngin;
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
