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
public class VoitureSel {
    
    private int idVoiture;
    private String marque;
    private String modele;
    private String numeroChassis;

    public VoitureSel(int idVoiture, String marque, String modele, String numeroChassis) {
        this.idVoiture = idVoiture;
        this.marque = marque;
        this.modele = modele;
        this.numeroChassis = numeroChassis;
    }
    
    public VoitureSel(int idVoiture) {
        this.idVoiture = idVoiture;
    }    

    public int getIdVoiture() {
        return idVoiture;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getNumeroChassis() {
        return numeroChassis;
    }
    
    
    
}
