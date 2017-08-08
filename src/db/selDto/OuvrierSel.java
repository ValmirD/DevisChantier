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

    public OuvrierSel(int idOuvrier, String nom, String prenom) {
        this.idOuvrier = idOuvrier;
        this.nom = nom;
        this.prenom = prenom;
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
      
}
