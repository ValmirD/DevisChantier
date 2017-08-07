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
public class OuvrierSel {
    
    private int idOuvrier;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String email;

    public OuvrierSel(int idOuvrier, String nom, String prenom, String numeroTelephone, String email) {
        this.idOuvrier = idOuvrier;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
    }

    public OuvrierSel(int idOuvrier) {
        this.idOuvrier = idOuvrier;
    }

    public int getIdOuvrier() {
        return idOuvrier;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getEmail() {
        return email;
    }
    
    
    
    
}
