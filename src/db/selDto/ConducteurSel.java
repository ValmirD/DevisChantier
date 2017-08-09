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
    private String password;
    private String nom;
    private String prenom;

    public ConducteurSel(int idConducteur, String password, String nom, String prenom) {
        this.idConducteur = idConducteur;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;

    }
    
    public ConducteurSel(int idConducteur) {
        this.idConducteur = idConducteur;
    }    

    public int getIdConducteur() {
        return idConducteur;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

 
    

    
}