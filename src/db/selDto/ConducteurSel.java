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

public class ConducteurSel {
    
    private int idConducteur;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String email;

    public ConducteurSel(int idConducteur, String nom, String prenom, String numeroTelephone, String email) {
        this.idConducteur = idConducteur;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
    }
    
    public ConducteurSel(int idConducteur) {
        this.idConducteur = idConducteur;
    }    

    public int getIdConducteur() {
        return idConducteur;
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